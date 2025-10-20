# Java command-line tools

### Content
* [Path Separator](#path-separator)
* [Quick command names](#quick-command-names)
* [Building and running java](#building-and-running-java)

### Path Separator
When you need to pass multiple paths to `javac/java/jar` commands you have to separate paths using path separator. And this separator is system dependent:
* Windows - we use semicolon `;`
* Linux/MacOS - we use colon `:`
You need to keep this in mind, because code is written, especially in `code` folder for Linux, and may not compile in Windows, you will have to change. If you try to run with invalid separator, only first dependency would be loaded, and others left, and code won't compile/run.
```shell
# Windows separator
jdeps --module-path="compiled/printer.jar;compiled/app.jar" compiled/app.jar
java --module-path="compiled/printer.jar;compiled/app.jar" --module module.app/com.java.app.App

# Linux & MacOS separator
jdeps --module-path=compiled/printer.jar:compiled/app.jar compiled/app.jar
java --module-path=compiled/printer.jar:compiled/app.jar --module module.app/com.java.app.App
```

### Quick command names
Don't confuse following short commands:
* `-cp` (java & javac) => `--class-path`, and also `-classpath` - this is the only command that has 2 short form: `-cp/-classpath`
* `-p` (java & javac) => `--module-path`, specify path of the module (either jar or folder with jar, or folder with compiled files), used in both compilation & running
* `-m` (java & javac) => `--module`, specify module name, used in both compilation & running
* `-d` (javac) => no full command, specify output directory for compilated files
* `-d` (java) => `--describe-module`, describe module and exit
* `--module-source-path` (javac), specify compilation path of required modules
* `--list-modules, --show-module-resolution` (java) => doesn't have a short form

### Building and running java
There are 3 commands available for java cmd:
* `javac` stands for java compiler - tool to compile java file into binary file with `.class` extension
  * we add `-d {directory}` option to build proper package structure into file - java expects us that package name is corresponding to file structure
  * if your project is lacking proper folder structure `javac` would build it:
    * if you have file `App.java` declared with `package com.java.test`, compiler would create full path for compiled file with `com/java/test/App.class`
    * it doesn't matter if your original file as `App.java` had this file structure or not, compile would create it anyway based on the package name - Yet it's considered good practice to structure code according to package name, so your file should be `com/java/test/App.java`
* `jar` stands for java archiver - tool to build special executable file with `.jar` extension
  * we use 3 commands: `cvf` => `--create --verbose --file`
* `java` - tool to run `.java/.class/.jar` files

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
javac -d . App.java util/Calculator.java
# run with java
java com.java.test.App
```

4. Compile and Run with `--add-exports`: We use package `jdk.internal.misc` from `java.base` module, which is not exported by default, so you can't even compile this code
```java
package com.java.test;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting the app...");
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
# just running without --add, app would run, but once it get to the accessing the class - it would fail
java com.java.test.App
Starting the app...
Exception in thread "main" java.lang.IllegalAccessError: class com.java.test.App (in unnamed module @0x4617c264) cannot access class jdk.internal.misc.Unsafe (in module java.base) because module java.base does not export jdk.internal.misc to unnamed module @0x4617c264
        at com.java.test.App.main(App.java:5)
        
# for running both add-opens & add-exports would work
java --add-exports java.base/jdk.internal.misc=ALL-UNNAMED com.java.test.App
```

5. Compile and Run with `--add-opens`: use this flag only if you require deep reflection - like this code to fetch private field from java class in private package:
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
You have to compile with `-add-exports` without it, code won't compile
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
jar --create --verbose --file=app.jar App.class 
# short version for create/verbose/file
jar cvf app.jar App.class 
# because jar is not executable you will get error: no main manifest attribute, in app.jar
java -jar app.jar
# you can run with class path, in his case it would work
java --class-path=app.jar App
# you can make jar executable
jar --create --verbose --file=app.jar --main-class=App App.class
# recompile with manifest (short version)
jar cvfe app.jar App App.class
```
Above example without package, but you can do the same with package
```shell
# compile class with full package name
javac -d . App.java
# build jar with main class
jar --create --verbose --file=app.jar --main-class=com.java.test.App com/java/test/App.class
```
You can also create manifest manually and create jar with this manifest file:
```shell
# add this file META-INF/MANIFEST.MF
Main-Class: App

# call this 
jar -cmvf META-INF/MANIFEST.MF app.jar App.class
# call
java -jar app.jar
```

7. Create modular jar file
Add following file: module-info.java
```java
module app{
    exports com.java.test;
}
```
Now create modular jar
```shell
# compile file with full package name
javac -d . *.java
# create jar
jar --create --verbose --file=app.jar --main-class=com.java.test.App com/java/test/App.class module-info.class

# compile 2 files with full package structure into compiled directory
javac -d compiled App.java module-info.java
# create jar from the source files in compiled folder and put jar into current directory: maybe nicer then previous where you have to supply all files manually
jar --create --verbose --file=app.jar --main-class=com.java.test.App -C compiled .

# you can run it the old way
java -jar app.jar
# run with class path
java --class-path=app.jar com.java.test.App
# you can run as module
java --module-path=app.jar --module=app
```
Check jar content
```shell
# check what is inside your jar (module should include module-info.class)
jar -tf app.jar
```

8. Compile module - [modular](/code/modular)
There are 2 ways you can compile java module:
* using standard java command - in this case you compile all files just like regular java files
* using new module options - in this case you force compiler to compile java files as single module. Basically it's the same compilation, but it also checks if your project is a proper module:
  * all 3 name for the module name should correspond: name passed into `--module` param, directory that holds the module, name inside `module-info.java` file
  * inside directory there should be file `module-info.java` with same module name as passed param and directory
  * modular compilation create folder with module name, while simple is just put everything into output folder
    * if you have output directory as `compiled` and module name as `module.app` - `javac` would compile into `compiled/module.app`, but with simple non-modular compilation, all files would be just in `src` folder
  * `--module-path` - expect 2 things: either directory with same name as module or jar file with `module-info.java` with same name as module (name of jar file is not important):
    * if compiled file - path where our module directory is located
    * if jar file - path where jar file is located
  * `--module-path` - can be used with both `java/javac` commands:
    * when compiling if you need another already built module as dependency (you can specify both jar file or directory that contains jar file): `javac --module-path=module.printer -d compiled --module-source-path=src --module=module.app`
    * when running - you specify the path for jar or folder with compiled files: `java --module-path=compiled --module=module.abc/com.java.test.App`
* Modular compilation can give you hints if what you are trying to compile is not real module - you can get following errors:
  * `error: module module.xyz not found in module source path` - if there is no directory with name `module.xyz` in the `src` directory, or directory exists, but there is no `module-info.java` inside `module.xyz` directory
  * `src\module.xyz\module-info.java:1: error: module name module.app does not match expected name module.xyz` - module name in the file `module-info.java` doesn't correspond to the name passed to `--module` param
* `--show-module-resolution` applied to `java` to show resolution while running the app
```shell
# regular compilation with all files listed manually
javac -d compiled src/module.abc/module-info.java src/module.abc/com/java/test/App.java
# regular compilation with recursive for all files in all sub dirs (all files inside all packages inside module.app folder)
javac -d compiled src/module.abc/module-info.java src/module.abc/**/*.java
# modular compilation: name of --module param, directory name, and inside module-info.java - all 3 should be the same
javac -d compiled --module-source-path=src --module=module.abc
# modular compilation: you can have multiple modules in single dir and compile them one-by-one
javac -d compiled --module-source-path=src --module=module.xyz
# modular compilation: compile 2 modules at once. if you use --module=module.app --module=module.xyz - then second param overwrite the first, and only module.xyz would be compiled
javac -d compiled --module-source-path=src --module=module.abc,module.xyz

# run as java
java --class-path=compiled/module.abc com.java.test.App
# run as module
java --module-path=compiled --module=module.abc/com.java.test.App
```