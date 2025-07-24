# Content
Here I would add details specific to java 21.

###### JVM basic
* each thread has its own stack yet there is single heap shared across all the threads
* stack size is limited to 64KB, but you can increase with `-Xss` settings, yet heap is unlimited from java perspective and is limited only by available RAM
  * `-Xms` - set initial heap size
  * `-Xmx` - set max heap size, example `java -Xms1024 -Xmx2048m app.jar` - set heap size of 1GB and up to 2GB
* type - refers to class/enum/interface/record or any primitive type, basically any variable is a type. 2 types exists: reference type and primitive type
* `import` keyword - doesn't import anything, it jus help user to avoid FQCN (fully qualified class name) to use in the code, so to keep code cleaner and shorter. Yet under-the-hood java uses import statements as FQCN if you declare just class names in java code. That's why you can have duplicate imports and there is no error, because no import happens, all import statements are just declarations
* there is no subpackage concept, if you do `import com.java.*` it will import only all classes, not all subpackages, so `import com.java.*.*` is invalid statement. you can't import package/class that doesn't exist
* java expect project structure to map to package name, so when you compile with `javac` use `-d` option - it will direct compiler to create directory structure based on package names
* if you have multiple java files and need to compile, first you need to compile independent class and then dependent. But you can pass all classes to compiler, and it would decide on dependency itself. You can call `javac -d . A.java B.java C.java` - compiler would compile all off them. Even simpler solution is `javac -d . *.java`
* java has `pass-by-value` semantics because in both cases for primitive & reference type you pass actual value (either primitive value or value of memory address for reference type). Any variable just contain raw-number, and it's JVM job to interpret this number as either real number int/long/char in case for primitive type, or as memory address on the heap in case of reference type. But variable in java would always contain just raw number.
* `int x = y = 1` is invalid, because initialization happens from left to right, `y` should be declared first. Yet this code is fine `int y; int x = y = 1;` because we declared `y` first, so `y=1` just works because `y` was already declared before.
* `var` is a shortcut, just like `import`, when you use it, under-the-hood JVM just translate your code into full name. Because it's shortcut you can write `var var = 1`.
* variable created from auto-generated code starts from `_` or `$`.
* literal - something that literally represent fixed value and can't be changed (for example value 5 can't be changed to anything else except integer value 5)
* formats:
  * binary - start with `0B` or `Ob`
  * decimal - normal format 10-based, starts from any number 1-9
  * octal format (8-based) - should start with `0`
  * hex `0X` or `0x`
```java
int binary = 0b111;
int octal = 0111;
int decimal = 111;
int hex = 0x111;
System.out.println("binary=" + binary + ", octal=" + octal + ", decimal=" + decimal + ", hex=" + hex);
```
result, as you see the higher the base the bigger the actual decimal number would be
```
binary=7, octal=73, decimal=111, hex=273
```

Don't confuse:
* statically-typed language - data type is defined during compile-time and can't change during runtime (in Java int can't be converted to boolean)
* dynamically-typed language - where data type can be changed during runtime (in JavaScript there are no strong types, you can assign any value like int/boolean/String to variable). This is perfectly valid code for JS:
```javascript
var v = 1;
v = true;
v = "hello";
console.log(v);
```
This code would print `hello` without any errors. Of course, you can't do this in java, cause there are strong types

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

###### Assignment rules
* `widening conversion` - assigning smaller type to larger type
* If a type can fit into another, no casting is required. If byte/short/char is smaller than `int` you can just assign it to int
```java
byte b = 100;
short s = b;
int i = s;
```
* since byte/short/int/long are signed and char is unsigned, you can:
  * assign char to int/long
  * can't assign without casting anything to `char`
```java
char c = 100;
int i = c;
long l = c;
```
* floating point are the highest numbers:
  * you can assign byte/short/char/int/long to float
  * you can assign all to double
  * you can't assign float to int/long, although float is 4 bytes and long is 8, float storing higher number
```java
int i = 100;
long l = 100;
float f1 = i;
float f2 = l;
double d = f1;
```
* you can assign larger value to smaller if it's known on compile time - declared as `final`.
```java
final int i = 10;
byte b = i;
short s = i;
char c = i;
```
Above code works fine for `int`, but won't compile for `long`. This is specific design of [JLS](https://docs.oracle.com/javase/specs/jls/se7/html/jls-5.html#jls-5.2), look into this statement:
```
In addition, if the expression is a constant expression (§15.28) of type byte, short, char, or int:
A narrowing primitive conversion may be used if the type of the variable is byte, short, or char, and the value of the constant expression is representable in the type of the variable.
```
As you `long` is missed here on purpose. The reasoning is that long is used for very large numbers, and probably would never store small values like `byte/short/char`
* you can use cast to assign any primitive value to any other primitive. Be careful of overflow. Result of below code is -128. It's simple - when convert to lower value, since int is 4 bytes and byte only 1, casting cut off 3 bytes leaving only last byte which is for 128 is `10000000`. Then java try to put this value into signed `byte`, but for signed byte this value is -128.
```java
int i = 128;
byte b = (byte) i;
System.out.println(b);
```
Why 128 and -128 are both `10000000` - you can refer to [BinaryMath](../BinaryMath.md)

###### Weird Behavior
compiler doesn't execute the code, only check the code for possible var declaration and initialization, that's explain this difference: in first and third example you can run code mentally in your head, and understand that it should compile, but compiler can't do it, so compiler never run the code and check execution, it just sees example 1 & 3, and understand that there maybe a case where value y is not initialized. Because even for third example compiler just see 2 if statement, which again doesn't assure that value `y` would be initialized. Only second example is clear to compiler, no matter what the value of `x`, the `if-else` statement guarantee 100% that value of `y` would be initialized. Keep this in mind - compiler never execute the code, code is executed only during runtime, so compiler can't deduce anything from code execution, it only looks into code syntax itself. And from code syntax it's completely unclear that example 1 and 3 would init value `y`.
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

Unreachable statement. If we have this code as below, it won't compile:
```java
int x = 5;
throw new RuntimeException("oops");
x = 10; // compile error: unreachable statement
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
So in both cases you have `static final` var that is not supposed to change, yet in one case compilation works fine, yet in another it fails. This is because java designed it in such way, cause many devs use `if` statements for debug purposes, so they change one value for `DEBUG` and all code inside `if` would be visible or not. That's why java designers decided to let `if` statement with `static final` still be reachable and not produce compile error. Yet with `while` here is obviously no utility, that's why in this case it shows compilation error of unreachable code. If you remove `final` it would work fine.

###### Nested Types
Since java16 with `JEP-395`, you can have static members for inner classes, and behavior may look a bit confusing
```java
public class App {
    public static void main(String[] args) {
        Outer.Inner inner1 = new Outer().new Inner();
        Outer.Inner inner2 = new Outer().new Inner();
        inner1.i = 1;
        System.out.println("inner1.i => " + inner1.i);
        inner2.i = 2;
        System.out.println("inner1.i => " + inner1.i);
    }
}

class Outer {
    class Inner {
        public static int i = 1;
    }
}
```
See that `inner1` and `inner2` are 2 completely unrelated classes, each has its own parent, which are also unrelated. You should expect that static fields inside `Inner` would also be unrelated, but they actually are and shared across all instances of `Inner` class. This may look confusing at first, that's why java designers originally prohibit this behavior to avoid such confusion, but since adding `record` they have to relax this rule, and now you can have static members inside inner classes.
```
inner1.i => 1
inner1.i => 2
```