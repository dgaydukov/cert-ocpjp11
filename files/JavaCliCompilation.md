# Java command-line tools

### Content
* [Building and running java](#building-and-running-java)

### Building and running java
There are 3 commands available for java cmd:
* `javac` stands for java compiler - tool to compile java file into binary file with `.class` extension
  * we add `-d {directory}` option to build proper package structure into file - java expects us that package name is corresponding to file structure
* `jar` stands for java archiver - tool to build special executable file with `.jar` extension
  * we use 3 commands: `cvf` => `--create --verbose --file`
* `java` - tool to run jar files



1. Compile and Run single file without package: 
```java
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```
If we have this file `App.java` without any defined package - you can compile and run
```shell
# compile into App.class - bytecode file
javac App.java
# run java - by name of the class: no extension is needed
java App
```

2. Compile and Run single file with proper package
```java
package com.java.test;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```
If we have class with package, previous approach won't work. It would compile, but when you run, you will get exception:
```
Error: Could not find or load main class App
Caused by: java.lang.NoClassDefFoundError: com/java/test/App (wrong name: App)
```
This is because, java expect package to be translated into directory hierarchy. And `javac` allows you to compile code where package would be transformed into directory
```shell
# compile with package into directory
javac -d . App.java
# you can run java
java com.java.test.App
```

3. Compile and Run multiple files with package
Main class
```java
package com.java.test;

import com.java.test.util.Printer;

public class App {
    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.print("Hello World");
    }
}
```
Printer class
```java
package com.java.test.util;

public class Printer {
    public void print(String msg){
        System.out.println("printing... => " + msg);
    }
}
```
Now
```shell
# compile both files
javac -d . App.java util/Printer.java
# run with java
java com.java.test.App
```

4. Compile and Run with `--add-exports`:
We use package `jdk.internal.misc` from `java.base` module, which is not exported by default, so you can't even compile this code
```java
package com.java.test;

public class App {
    public static void main(String[] args) {
        jdk.internal.misc.Unsafe.getUnsafe();
        System.out.println("Hello World");
    }
}
```
If you try to compile this code, you will get compilation error:
```
App.java:5: error: package jdk.internal.misc is not visible
        jdk.internal.misc.Unsafe.getUnsafe();
                    ^
  (package jdk.internal.misc is declared in module java.base, which does not export it to the unnamed module)
1 error
```
Keep in mind that only `--add-exports` works on compilation time, if you try to use other you get: `--add-opens has no effect at compile time`.
```shell
# success compilation
javac -d . --add-exports java.base/jdk.internal.misc=ALL-UNNAMED App.java
# calling java will faile
java com.java.test.App
Exception in thread "main" java.lang.IllegalAccessError: class com.java.test.App (in unnamed module @0x4617c264) cannot access class jdk.internal.misc.Unsafe (in module java.base) because module java.base does not export jdk.internal.misc to unnamed module @0x4617c264
        at com.java.test.App.main(App.java:5)
        
# for running both add-opens & add-exports would work
java --add-exports java.base/jdk.internal.misc=ALL-UNNAMED com.java.test.App
```

5. Compile and Run with `--add-opens`: use this flag only if you require deep reflection
You have this code to fetch private field from java class in private package
```java
package com.java.test;

import jdk.internal.misc.Signal;
import java.lang.reflect.Field;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting the app...");
        try {
            Signal obj = new Signal("BREAK");
            Field f = Signal.class.getDeclaredField("name");
            f.setAccessible(true);
            String name = (String) f.get(obj);
            System.out.println("name => " + name);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            System.out.println("ERR => " + ex);
        }
    }
}
```
You have to compile with `-add-exports` without it code won't compile
```shell
# success compilation
javac -d . --add-exports java.base/jdk.internal.misc=ALL-UNNAMED App.java
# run without flags: once we get private class on line 10, exception is thrown
java com.java.test.App
Starting the app...
Exception in thread "main" java.lang.IllegalAccessError: class com.java.test.App (in unnamed module @0x4617c264) cannot access class jdk.internal.misc.Signal (in module java.base) because module java.base does not export jdk.internal.misc to unnamed module @0x4617c264
        at com.java.test.App.main(App.java:10)
        
# run with exports option: now it would work, but throw exception on f.setAccessible(true), you are trying to use deep reflection you need wider access
java --add-exports java.base/jdk.internal.misc=ALL-UNNAMED com.java.test.App
Starting the app...
Exception in thread "main" java.lang.reflect.InaccessibleObjectException: Unable to make field private java.lang.String jdk.internal.misc.Signal.name accessible: module java.base does not "opens jdk.internal.misc" to unnamed module @38af3868
        at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:354)
        at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:297)
        at java.base/java.lang.reflect.Field.checkCanSetAccessible(Field.java:178)
        at java.base/java.lang.reflect.Field.setAccessible(Field.java:172)
        at com.java.test.App.main(App.java:12)

# try with opens: now it would work fine
java --add-opens java.base/jdk.internal.misc=ALL-UNNAMED com.java.test.App
Starting the app...
name => BREAK
```

6. Compile and Run jar
```shell
# shortcut to run single file
java App.java
# compile java file into class file
javac App.java
# build jar file
jar cvf app.jar App.class 
# run jar => you will get error: no main manifest attribute, in app.jar
java -jar app.jar
# error is because jar is not executable, but you can run this way
java -cp app.jar App
```
Make jar executable
```shell
# add this file META-INF/MANIFEST.MF
Main-Class: App

# call this 
jar -cmvf META-INF/MANIFEST.MF app.jar App.class
# call
java -jar app.jar
```
Rules for class/file -names:
* file can contain only 1 public class with the same name as file - if you declare public class name different from filename you get compilation error: `error: class MyApp is public, should be declared in a file named MyApp.java`
* you can have many classes - all classes would compile into separate `.class` name
* you can have non-public class with different name - it would compile fine
