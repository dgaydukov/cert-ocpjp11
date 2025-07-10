# Content



### Weird Behavior
In this section we would take a look at java weird behavior and try to explain why it behaves this way.

###### JVM basic
* each thread has its own stack yet there is single heap shared across all the threads
* stack size is limited to 64KB, but you can increase with `-Xss` settings, yet heap is unlimited from java perspective and is limited only by available RAM
* type - refers to class/enum/interface/record or any primitive type, basically any variable is a type. 2 types exists: reference type and primitive type
* `import` keyword - doesn't import anything, it jus help user to avoid FQCN (fully qualified class name) to use int the code, so to keep code cleaner and shorter. Yet under-the-hood java uses import statements as FQCN if you declare just class names in java code. That's why you can have duplicate imports and there is no error, because no import happens, all import statements are just declarations
* there is no subpackage concept, if you `import com.java.*` it will import only all classes, not all subpackages, so `import com.java.*.*` is invalid statement. you can't import package/class that doesn't exist
* java expect project structure to map to package name, so when you compile with `javac` use `-d` option - it will direct compiler to create directory structure based on package names
* if you have multiple java files and need to compile, first you need to compile independent class and then dependent. But you can pass all classes to compiler, and it would decide on dependency itself. You can call `javac -d . A.java B.java C.java` - compiler would compile all off them. Even simpler solution is `javac -d . *.java`

###### Compilation unreachable statement
```
int x = 5;
throw new RuntimeException("oops");
// won't compile => unreachable statement
x = 10;
```
This code works fine
```java
class WeirdCompiler {
    public static final boolean DEBUG = false;
    public void method(){
        if(DEBUG){
            System.out.println("debug");
        } 
    }
}
```
But this code won't compile
```java
class WeirdCompiler {
    public static final boolean DEBUG = false;
    public void method(){
        // not compiled => unreachable statement
        while(DEBUG){
            System.out.println("debug");
        }
    }
}
```
So in both cases you have `static final` var that is not supposed to change, yet in one case compilation works fine, yet in another it fails. This is because java designed it in such way, cause many devs use `if` statements for debug purposes, so they change one value for `DEBUG` and all code inside `if` would be visible or not. That's why java designers decided to let `if` statement with `static final` still be reachable and not produce compile error. Yet with `while` here is obviously no utility, that's why in this case it shows compilation error of unreachable code.