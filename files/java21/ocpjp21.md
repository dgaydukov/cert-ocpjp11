# Content
Here I would add details specific to java 21.


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
* java has `pass-by-value` semantics because in both cases for primitive & reference type you pass actual value (either primitive value or value of memory address for reference type). Any variable just contain raw-number, and it's JVM job to interpret this number as either real number int/long/char in case for primitive type, or as memory address on the heap in case of reference type. But variable in java would always contain just raw number.
* `int x = y = 1` is invalid, because initialization happens from left to right, `y` should be declared first.
* variable created from auto-generated code starts from `_` or `$`.
* literal - something that literally represent fixed value and can't be changed (for example value 5 can't be changed to anything else except integer value 5)
* formats: binary - start with `0B` or `Ob` and hex `0X` or `0x` and in octal format (8-based) - should start with `0`. The result is `o=511, h=1911, b=7`:
```
int o = 0777;
int h = 0x777;
int b = 0b111;
System.out.println("o="+o+", h="+h+", b="+b);
```
* 


compiler doesn't execute the code, only check the code for possible var declaration and initialization, that's explain this difference: in first and third example you can run code mentally in your head, and understand that it should compile, but compiler can't do it, so compiler never run the code and check execution, it just see example 1 & 3, and understand that there maybe a case where value y is not initialized. Because even for third example compiler just see 2 if statement, which again doesn't assure that value `y` would be initialized. Only second example is clear to compiler, no matter what the value of `x`, the `if-else` statement guarantee 100% that value of `y` would be initialized. Keep this in mind - compiler never execute the code, code is executed only during runtime, so compiler can't deduce anything from code execution, it only look into code syntax itself. And from code syntax it's completely unclear that example 1 and 3 would init value `y`.
```
// not compiled
int x = 0, y;
if (x == 0){
    y = 5;
}
System.out.println(y);

// compiled fine
int x = 0, y;
if (x == 0){
    y = 5;
} else {
    y = 10;
}
System.out.println(y);

// not compiled
int x = 0, y;
if (x == 0){
    y = 5;
}
if (x != 0){
    y = 10;
}
System.out.println(y);
```

Don't confuse:
* statically-typed language - data type is defined during compile-time and can't change during runtime (in Java int can't be converted to boolean)
* dynamically-typed language - where data type can be changed during runtime (in JavaScript there are no strong types, you can assign any value like int/boolean/String to variable)

Java use the concept of default initialization. Look here: `a` and `b` would be initialized as 0. Yet `c` would not be initialized at all. This code would compile, but if you try later to use `c`, you will get compilation error: `java: variable c might not have been initialized`
```java
public class App {
    static int a;
    int b;
    public static void main(String[] name){
        int c;
    }
}
```

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