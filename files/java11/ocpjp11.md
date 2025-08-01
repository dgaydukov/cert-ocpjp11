# Content

1. [Basics](#basics)
* 1.1 [Variable Declarations](#variable-declarations)
* 1.2 [Numeric promotion](#variable-declarations)
* 1.3 [Short circuit boolean](#wrapper-classes)
* 1.4 [Wrapper classes](#wrapper-classes)
* 1.5 [String and StringBuilder](#string-and-stringbuilder)
* 1.6 [Arrays](#-arrays)
* 1.7 [Arrays.compare and Arrays.mismatch](#arrayscompare-and-arraysmismatch)
2. [Classes and Objects](#classes-and-objects)
* 2.1 [toString, equals, hashcode, clone](#tostring-equals-hashcode-clone)
* 2.2 [Classes](#classes)
* 2.3 [Interfaces](#interfaces)
* 2.4 [Enums](#enums)
* 2.5 [Exceptions](#exceptions)
* 2.6 [Nested Types](#nested-types)
* 2.6 [Anonymous classes](#anonymous-classes)
3. [Date and Time](#date-and-time)
* 3.1 [Old date api (java.util.Date & java.util.Calendar)](#old-date-api-javautildate--javautilcalendar)
* 3.2 [SQL date api (java.sql package)](#sql-date-api-javasql-package)
* 3.3 [New date api (java.time package)](#new-date-api-javatime-package)
* 3.4 [Period and Duration](#period-and-duration)
* 3.5 [Timezone and DST](#timezone-and-dst)
* 3.6 [Formatting](#formatting)
4. [Generics](#generics)
* 4.1 [Type erasure](#type-erasure)
* 4.2 [PECS (producer extends consumer super)](#-pecs-producer-extends-consumer-super)
* 4.3 [Generic method overriding](#generic-method-overriding)
* 4.4 [Generic arrays](#generic-arrays)
5. [Collections](#collections)
* 5.1 [List and Set](#list-and-set)
* 5.2 [Array and Enumeration to List and back](#array-and-enumeration-to-list-and-back)
* 5.3 [Queue and Stack](#queue-and-stack)
* 5.4 [Map](#map)
* 5.5 [binarySearch](#binarysearch)
* 5.6 [Order and duplicates](#order-and-duplicates)
6. [Functional Programming and Stream API](#functional-programming-and-stream-api)
* 6.1 [Functional interfaces](#functional-interfaces)
* 6.2 [Method reference](#method-reference)
* 6.3 [Comparator and Comparable](#comparator-and-comparable)
* 6.4 [Primitive streams](#primitive-streams)
* 6.4 [Parallel streams](#parallel-streams)
* 6.5 [Collectors](#collectors)
7. [Concurrency](#concurrency)
* 7.1 [Threads](#threads)
* 7.2 [ExecutorService](#executorservice)
* 7.3 [wait/notify and await/signal](#waitnotify-and-awaitsignal)
* 7.4 [fork/join framework](#forkjoin-framework)
* 7.5 [Synchronizers](#synchronizers)
* 7.6 [Concurrent collections](#concurrent-collections)
* 7.7 [Deadlock and Livelock](#deadlock-and-livelock)
* 7.8 [Readers–writers problem](#readerswriters-problem)
* 7.9 [Synchronized on ID](#synchronized-on-id)
* 7.10 [Future & CompletableFuture](#future--completablefuture)
* 7.11 [ReentrantLock/ReentrantReadWriteLock/StampedLock](#reentrantlockreentrantreadwritelockstampedlock)
* 7.12 [Reactive Streams](#reactive-streams)
8. [JDBC and SQl](#jdbc-and-sql)
* 8.1 [Connection](#connection)
* 8.2 [Statement and PreparedStatement](#statement-and-preparedstatement)
* 8.3 [CallableStatement](#callablestatement)
* 8.4 [Transactions](#transactions)
9. [Serialization](#serialization)
* 9.1 [Java serialization](#java-serialization)
* 9.2 [XML serialization](#xml-serialization)
* 9.3 [JSON serialization](#json-serialization)
10. [IO and NIO](#io-and-nio)
* 10.1 [InputStream/OutputStream and Reader/Writer](#inputstreamoutputstream-and-readerwriter)
* 10.2 [Console](#console)
* 10.3 [5 ways to read file](#5-ways-to-read-file)
* 10.4 [NIO channels](#nio-channels)
* 10.5 [Directory searching](#directory-searching)
* 10.6 [Path resolve and relativise](#path-resolve-and-relativise)
* 10.7 [mark, reset, skip](#mark-reset-skip)
* 10.8 [Files copy, move, delete](#files-copy-move-delete)
* 10.9 [Data traversal](#data-traversal)
* 10.10 [BasicFileAttributes](#basicfileattributes)
* 10.11 [DirectByteBuffer vs HeapByteBuffer](#directbytebuffer-vs-heapbytebuffer)
11. [Miscellaneous](#miscellaneous)
* 11.1 [Modules](#modules)
* 11.2 [Random numbers](#random-numbers)
* 11.3 [Locale and ResourceBundle](#locale-and-resourcebundle)
* 11.4 [Assertions](#assertions)
* 11.5 [Object interning](#object-interning)
* 11.6 [Annotations](#annotations)
* 11.7 [Reflection API](#reflection-api)
    * 11.7.1 [Get param names](#get-param-names)
* 11.8 [Compile Time Annotation Processor](#compile-time-annotation-processor)
* 11.9 [JDK Proxy, Cglib, Javassist](#jdk-proxy-cglib-javassist)
* 11.10 [JMX (java management extension)](#jmx-java-management-extension)
* 11.11 [Custom ClassLoader](#custom-classloader)
* 11.12 [Desktop](#desktop)
* 11.13 [Java Servlet WebApp](#java-servlet-webapp)
* 11.14 [Java Virtual Methods](#java-virtual-methods)
* 11.15 [Class Diagram](#class-diagram)
* 11.16 [Remote debugging](#remote-debugging)
12. [New Java Versions](#new-java-versions)
* 12.1 [Java 15](#java-15)
* 12.2 [Java 16](#java-16)
* 12.3 [Java 17](#java-17)
* 12.4 [Java 21](#java-21)
* 12.5 [Java 25](#java-25)


#### Basics
###### Variable Declarations
Line separator. Java allows as many underscores as you want when separating digits. But you can't put underscore in the begginin or in the end.
```java
class App {
    public static void main(String[] args) {
        int i1 = 1_0;
        int i2 = 1__0;
        int i3 = 1___0;
        int i4 = 1_____0;
        System.out.println(i1 + " " + i2 + " " + i3 + " " + i4);

      float f1 = 1_0.0f;
      // won't compile: illegal underscore
      float f2 = 10_.0f;
    }
}
```
```
10 10 10 10
```

Java variable declaration & initialization
```java
public class App{
    public static void main(String[] args) {
        int y = 5;
        // it's the same as `int x = (y = 10)`, so both of them are 10
        int x = y = 10;
        
        int x1 = y1 = 10; // won't compile cause y1 is not defined
        
        int x2 = (x2 = 3) + 4;
        // it's the same as int x2 = 3; x2 = x2 + 4; so x2 = 7
        System.out.println(x);

        int a, b, c;
        a = b = c = 10;
        
        int b1, c1;
        // the same as `int a1 = (b1=(c1=10))`, so first c1=10, then b1=10, then a1=10
        int a1 = b1 = c1 = 10;
    }
}
```

All variables must be initialized before used
```java
public class App {
    public static void main(String[] args) {
        String s;
        int n = Integer.parseInt(args[0]);
        if(n > 0){
            s = "yes";
        }
        if (n <= 0){
            s = "no";
        }
        System.out.println(s); // won't compile
    }
}
```
Although we clearly see that we set variables `s` anyway (if n greater than 0, s = "yes", if not s = "no"), but compiler doesn't execute the code, and it just sees it as 2 different `if` statement without else, and thinks that we don't initialize `s`. To fix it you need to add `else` to any if and set `s` there.

Constants in java are made with `static final` keywords. They are used different naming conventions.
```java
private static final int NUM_BUCKETS = 45;
```

Division by zero. When we divide double by zero (or int by 0.0) we don't get exception
```java
public class App {
    public static void main(String[] args){
        int i = 1;
        double d = 1;
        System.out.println("d/0 => " + d/0);
        System.out.println("i/0.0 => " + i/0.0);
        System.out.println(i/0);
    }
}
```
```
d/0 => Infinity
i/0.0 => Infinity
Exception in thread "main" java.lang.ArithmeticException: / by zero
```
When we divide `int` by zero we got `ArithmeticException`, but when we divide `double` we got `Infinity`. You can also add positive/negative `Infinity` to variable using `Double` class
```
double d1 = 1/0d;
double d2 = Double.POSITIVE_INFINITY;
System.out.println("d1="+d1+", d2="+d2);
```
```
d1=Infinity, d2=Infinity
```

Both `int/double` use first bit to store sign, `+/-`, that's why values are:
* int: -2**31 => 2**31-1
* double: -2**63 => 2**63-1
So because of this first bit reserved for sign, we call them `signed`, all numeric and floating point numbers in java are signed, except `char` it's unsigned 2 byte primitive type. So if you assign 2**32 to int, or 2**64 to long you will get overflow. Look at example below:
```
int i = (1 << 31) - 1;
System.out.println(i);
i++;
System.out.println(i);
```
If you look the first time we assign max Integer value in java as 2**31-1, but when we increment on 1, we got overflow, and returning to min integer value.
```
2147483647
-2147483648
```
Pay attention that no overflow exception is thrown. During overflow java doesn't throw any exception (compare to C# that throws `OverflowException` in `checked` context), java use silent wrapping. But you can get exception if you use these methods: `Math.addExact(), Math.subtractExact(), Math.multiplyExact(), Math.toIntExact()` throws `ArithmeticException`
```java
public class App{
  public static void main(String[] args){
    int i = (1 << 31) - 1;
    i = Math.addExact(i, 1);
  }
}
```
```
Exception in thread "main" java.lang.ArithmeticException: integer overflow
```

We can convert int=>float without cast, but vice versa we should cast. That happens because float store more than int. They both 4 bytes, both use 1 bit to store sign(+ or -), but int store all digit so max value is 2**31-1, but float use 8 bit store exponent (so max exponent value = 127, and 2**127 is the max value to store) and 23 bits to store floating value. Since floating value would be 1-2, so max is 1.9999, then max float value is 2**128 or  3.4*10**38.
```java
int i = 1;
float f = i;
int i2 = f; // won't compile, need cast
int i3 = (int)f;
```
conversion example: `2**20 => (2**10)**2 => 1024**2 ≈ (10**3)**2 ≈ 10**6`
max values:
* int => 2**31 ≈ 10**9
  * `2**31 = 2**(10*3.1) = (2**10)**3.1 = 1024**3.1 ≈ = (10**3)**3.1 ≈ 10**9`
* long => 2**63 ≈ 10**19
  * `2**63 = (2**10)**6.3 = 10**(3*6.3) = 10**19`
* float => 2**127 ≈ 10**38
  * 8 bit for exponent => 2**-126...2**127, so max value is `2**127 = 2**(10*12.7) = 1024**12.7 ≈ 10**(3*12.7) ≈ 10**38`
* double => 2**1023 ≈ 10**307
  * 11 bit for exponent => 2**-1022...2**1023, so max value `2**1023 = 2**(10*102.3) = 1024**102.3 ≈ 10**(3*102.3) ≈ 10**308`
So we can assign int/long to float/double without actual cast, but vice versa - compile error

Only String, byte, short, int, char (and their wrappers Byte, Short, Integer, Char) and enums can be a type of switch variable. All switch labels must be compile-time constants. The reason is that Java/C++/C implement switch as jump (branch) table, `TABLESWITCH` instruction in Java, where the location to jump to is effectively looked up in a table. Switch can be empty-bodied (not labels & default). `long` can't be used in:
* array index - because it's too much for continuous array, and max array size in java is `Integer.MAX_VALUE`
* label in case statement - again problem is that long is too long, even int is too high value, but it's used because int is default value in java
Variable declared inside `case` are visible throughout switch only if they are in order. As you see `a` is declared before it's used in second case, so it's valid, but `b` is used before it would be declared in second case, so it's invalid.
```java
public class App {
    static public final void main(String[] args) {
        int x = 1;
        switch (x) {
            case 0:
                int a = 1;
                b = 2; // won't compile
                break;
            case 1:
                int b = 1;
                a = 2;
                break;
        }
    }
}
```

Naming convention. Following code is valid
```java
public class App {
    public static void main(String[] args) {
        String String = "hello";
        System.out.println(String.length());
    }
}
```
```
5
```
Java doesn’t allow variables to have the same name as keywords(if, else, while, class...) and literals (true, false, null). All others like class name, var ... perfectly fine. In case of conflict between variable name and className, if variable in scope it’s variable name.


char=>int conversion: Although we can assign `char` to `int`. We can’t assign `char` to `Integer`, autoboxing can’t do this.
```java
int i = 'a';
Integer i2 = 'a'; //wont' compile
```
This is general problem, you can't assign `int` to `Long`. You get following error: `java: incompatible types: int cannot be converted to java.lang.Long`
```
int i = 1;
long l = i;
// won't compile
Long l2 = i; 
```


Even though multiplication is associative, if somewhere we have assignment code, associative is lost. Keep in mind that execution is happens from left to right. That's why in below example associativity is lost and we have 2 completely different results.
```java
public class App{
    public static void main(String[] args) {
        int a = 1;
        System.out.println((a + 1) * (a = 4));
        a = 1;
        System.out.println((a = 4) * (a + 1));
    }
}
```
```
8
20
```
In first case we first have (1+1) * (4) = 8
in second = (4) * (4+1) = 20

We cant use both int & char in `switch` statement, if they are equal.
```java
public class App {
    public static void main(String[] args) {
        char a = 97;
        switch (a) {
            case 'a':
                System.out.println("a");
                break;
            case 'b':
                System.out.println("b");
                break;
            case 97:
                System.out.println("97"); //compile error: duplicate case label, cause a=97
                break;
            case 1:
                System.out.println("1");
                break;
        }
    }
}
```
As you see, since `a=97`, we can’t use char & int here, cause they are basically duplicates.

###### Numeric promotion
Numeric promotion - result of math operation (+,-,*,/,&,>>) is promoted to the highest type (short+long => long), if two types are equal and less than int (byte, short) they are promoted to int.
```java
public class App{
    public static void main(String[] args) {
        short s1 = 1;
        short s2 = 2;
        short s3 = s1 + s2; // won't compile cause s1+s2 - integer
        short s4 = (short)(s1 + s2);

        short s1 = 1;
        short s2 = -s1; // won't compile, -s1 - already int
    }
}
```
But it works only for variables, cause compiler doesn’t execute code. If we have constants (and type is byte, short, int, char), compiler know them at compiler time, so above rules not apply. But it doesn't work for:
* float/double
* long
* don’t apply to compound operators (*=, +=, ++, --, -=, /=)
```java
class App {
    public static void main(String[] args) {
        final int i = 10;
        byte b = i;
        short s = i;
        char c = i;

        final double d = 1;
        float f = d; // won't compile
        
        final float f1 = 1;
        int i1 = f1; // won't compile

        long l1 = 1;
        final long l2 = 1;
        int i1 = l1; // won't compile
        int i2 = l2; // won't compile
    }
}
```

Return type promotion - same rules applied for return types:
* for numeric return type:
  * numeric - you can return smaller type: for `long` return type you can return `int/short/char/byte`
  * wrapper - you can return smaller type: for `long` return type you can return `Integer/Short/Character/Byte`
* for wrapper return type:
  * numeric - only same value: for `Integer` return type you can return only `int`
  * wrapper - only same value: for `Integer` return type you can return only `Integer`

The key here is that `final` is the reference to memory, not the value itself, so rules with constants don't apply here.
```java
final Short s1 = 1;
short s2 = -s1; // won't compile, s1 is cast to int by default 
```
When you create long with multiplication, it would be treated as int.
Intellij also show you warning `Numeric overflow Exception`. Although `long` can hold more than 10B, `int` is only 2B. By default, all operations are converted to int, then cast to long, so in below example 10B would be converted to int (which will cause overflow), then overflown value would be converted to long. To Avoid this, add `L` in the end:
```java
public class App{
  public static void main(String[] args){
    long l1 = 10 * 1024 * 1024 * 1024;
    System.out.println("l1 => " + l1);
    long l2 = 10 * 1024 * 1024 * 1024L;
    System.out.println("l2 => " + l2);
  }
}
```
```
l1 => -2147483648
l2 => 10737418240
```

###### Short circuit boolean
* AND - true only if both true
* OR - true if one is true
Short-circuit logical operators. Operators &&/& and ||/| result the same, when applied to boolean. The difference is that double - is called short-circuit. For example since (true||false) will always return true, if first is true, it won’t even execute second operand. But in case of single operator (& or |), it will execute both.
```java
public class App {
    public static void main(String[] args) {
        System.out.println(f1() || f2());
        System.out.println();
        System.out.println(f1() | f2());
    }
    private static boolean f1(){
        System.out.println("f1");
        return true;
    }
    private static boolean f2(){
        System.out.println("f2");
        return true;
    }
}
```
```
f1
true

f1
f2
true
```
Even if we have 3 operands like this `v1() || v2() && v3()`, if `v1()` - returns `true`, others 2 won't execute.
As you see below, both are return true, but in first case we don’t execute function `f2()`. Since we use short-circuit operator. 
The same true for `&&`.
```java
public class App {
    public static void main(String[] args) {
        System.out.println(f1() && f2());
        System.out.println();
        System.out.println(f1() & f2());
    }
    private static boolean f1(){
        System.out.println("f1");
        return false;
    }
    private static boolean f2(){
        System.out.println("f2");
        return false;
    }
}
```
```
f1
false

f1
f2
false
```
As you see, the same result, but in second case we execute all operands, but in first only first - cause it’s already enough to determine the output.

Although you can compare only boolean value, be careful of following code. As you see we omit one `=`, and hence code works not as we expected, cause in condition we just reassign b to true and it always work. In production code it’s better to use `true == b`. In this case if you omit one `=`, compiler will show error, cause that would mean you are trying to assign value to keyword `true`.
```java
public class App {
    public static void main(String[] args) {
        boolean b = false;
        System.out.println("b => " + b);
        if(b = true){
            System.out.println("got it");
        }
        System.out.println("b => " + b);
    }
}
```
```
b => false
got it
b => true
```

###### Wrapper classes
```java
public class App{
    public static void main(String[] args) {
        // Decimal declaration and possible chars are [0-9]
        int decimal =  495;
        System.out.println("decimal => " + decimal);
        // HexaDecimal declaration starts with 0X or 0x and possible chars are [0-9A-Fa-f]
        int hex =  0X1EF;
        System.out.println("hex => " + hex);
        // Octal declaration starts with 0 and possible chars are [0-7]
        int octal =  0757;
        System.out.println("octal => " + octal);
        // Binary representation starts with 0B or 0b and possible chars are [0-1]
        int binary =  0b111101111;
        System.out.println("binary => " + binary);
    }
}
```
```
decimal => 495
hex => 495
octal => 495
binary => 495
```
    
All wrapper classes has also static method `valueOf`:
* `Character` - 1 method that accepts primitive `char`
* `Boolean/Float/Double` - 2 methods that accept primitive & String
* `Byte/Short/Integer/Long` - 3 methods that accept primitive & String & `string+radix`
  * `Float f = Float.valueOf(1.1);` - generate compile error, cause 1.1 - double.
  * radix - is param for numeric system, in range 2..36, so using it we can convert not only to 10-based system, but all the way from 2 to 36
Radix example:
```java
public class App {
    public static void main(String[] args) {
        System.out.println("1_000_000 based 2 => "+Integer.valueOf("1000000", 2));
        System.out.println("abcde based 36 => " + Integer.valueOf("abcde", 36));
    }
}
```
```
1_000_000 based 2 => 64
abcde based 36 => 17325410
```
Try to always use `valueOf` - the reason is that constructor - always create new object in memory, but valueOf will either return singleton (in case of Boolean.TRUE & Boolean.FALSE) or may return cached object. For `Integer`, by default cached valued -128..127, but you can add more cached value using `-XX:AutoBoxCacheMax`. Then you can get 2 integers and compare them use `obj1==obj2`, and although they are object, because they are cached, they would refer to the same underlying object in memory. So `valueOf` and autoboxing using cached objects, but `constructor` always create new object.
```java
public class App{
    public static void main(String[] args) {
        float f = 3; //ok
        float f1 = 1.1; // wrong, cause 1.1 is double
        float f = 1.1f; // explicit cast to float
        float f = 2 * 1.5; // although it's 3, but the compiler calculate it as int * double = double, and double can't be float without cast
        Float f2 = new Float(1.1); // ok, cause Float has 3 constructors for float/double/String
        short s1 = 10; // ok, because of type casting
        Short s2 = new Short(10); // wrong, cause Short has 2 constructors for short/String
        Short s3 = new Short((short)10);
        // since there is no special letter for short, only (l-long, f-float, d-double), we have to use explicit cast
        byte b1 = 10; // ok, because of implicit type casting
        Byte b2 = new Byte(10); // wrong, cause Byte has 2 constructors for byte/String
        Byte b3 = new Byte((byte)10);

        char ch1 = 10;
        Character ch2 = new Character(10); // wrong, cause Char has 1 constructor for char
        
        long l = 1;
        float f = 1;
        long l2 = l / f; // won't compile, since float larger than long => expression cast to float
        float f2 = l / f;

        boolean b1 = false, b2 = false;
        /**
         * won't compile cause boolean operation has precedence over assign
         * so it would be like (b1==b2)=true => true=true => which is invalid exception
         */
        if(b1 == b2 = true){}

    }
}
```

`String.valueOf` - very versatile method, can take all primitive types, char[], and Object.

If you want to convert wrapper class into primitive, all wrappers have method `xxxVAlue`, like
```java
Integer i = new Integer(10);
int x = i.intValue();
```
Unboxing - is the opposite of autoboxing (transform primitive to box(reference))
```java
Integer i = 10; // autoboxing
int x = i; // unboxing
```
Autoboxing won't happen when we call `System.out.println(1)`, cause `println` overloaded to take both int and Object.

When convert large integer to float, lost of precision happens, that’s why the result is -3, not 0.
```java
int i = 123456789;
float f = i;
System.out.println(i + " " + f + " " +(i-(int)f));
```
```
123456789 1.23456792E8 -3
```

Object to primitive casting - you can cast `Object` to any primitive, and if underlying object is `Integer/Boolean/Double` it would cast to primitive `int/boolean/double`
So when we cast object to primitive 2 operation happens:
* object cast to specific primitive wrapper object
* unboxing of wrapper object
```java
public class App{
    public static void main(String[] args) {
        Object b = true;
        Object i = 1;
        Object f = 1.1f;
        boolean b2 = (boolean) b;
        int i2 = (int) i;
        float f2 = (float) f;
        System.out.println("b2=" + b2 + ", i2=" + i2 + ", f2=" + f2);
        Object obj = "hello";
        int v = (int) obj;
    }
}
```
```
b2=true, i2=1, f2=1.1
Exception in thread "main" java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer
```
Autoboxing - is implicit conversion between primitive types like int => Integer, boolean => Boolean and so on (totally 8 types). We can use it when working with List of integers, boolean and so on. 

We can remove by value when we pass value. But since we can remove by index also, when we remove from integer, autoboxing doesn’t work. List understand that we remove by index. So if you want to remove integer from a list by value, use conversion
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1,2,3));
        // remove 2, autoboxing doesn't work and remove by index
        list.remove(1);
        // for integers only use this syntax
        list.remove(Integer.valueOf(1));

        /**
         * java can't autobox different types
         */
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1); // won't compile, can't autobox int to Double.
    }
}
```

###### String and StringBuilder
Strings are immutable, so all operations on them return always new object. `a += b => new StringBuilder().append(a).append(b).toString()` - always return new object.
```java
public class App {
    public static void main(String[] args) {
        String s = "java";
        System.out.println(s == "java"); // true
        s += "";
        System.out.println(s == "java"); // false
        s = "java";
        // concat with empty string return the same object
        s = s.concat("");
        System.out.println(s == "java"); // true
    }
}
```
```
true
false
true
```

`String` & `StringBuilder` method `.substring(start, end)`, throws `StringIndexOutOfBounds` exception if start < end or start < 0 or end > length()

String will concatenate with `+` only if one of them is string.
That’s why first statement compiled correctly, cause it works like Object + String => Object.toString + String. But second failed, cause it works like Object + Integer, which can’t work
```java
Object obj = 1;
obj += "2";

Object obj2 = "1";
obj2 += 2; // won't compiled

String str = "abc"+5;
System.out.println(str); // compile and print "abc5"


Object obj = null;
System.out.println(obj + "hello"); // compile and print "nullhello"
```
That’s the reason, why we can concatenate with null, cause when stringify null became just `null` string.
String interning - capture the link to string pool object.
```java
String s1 = "hello";
String s2 = "hello";
System.out.println("s1 == s2 => " + (s1 == s2));

String s3 = new String("hello");
String s4 = new String("hello");
System.out.println("s3 == s4 => " + (s3 == s4));

String s5 = s3.intern();
System.out.println("s5 == s1 => " + (s5 == s1)); // string interning, returns true
```
```
s1 == s2 => true
s3 == s4 => false
s5 == s1 => true
```
First is obvious true, cause when we write `String s2 = “hello”`, java looks in string pool, and there is a string “hello” there, so java return this reference to object, that’s why `s1==s2` always true. Second is also clear, here we have 2 references that point to two different objects in heap, that’s why result is always false. In third case we're interning (get reference from string pool, not from object in heap), that’s why `s5` pointing to the same object in string pool as `s1` and `s2`. Internally java uses hash table to store all strings and understand if such string already exists or not. You can rewrite this behavior by using `ConcurrentHashMap`.

`trim` vs. `strip`
`trim` - old method to trim a string, strip - is java11, the difference is working with Unicode. 
```java
Character c = '\u2000';
String s = c + "abc" + c;
System.out.println(s);
System.out.println(s.trim());
System.out.println(s.strip());
```
```
 abc 
 abc 
abc
```

As you see, `trim` doesn’t remove whitespaces, cause this Unicode whitespace is not considered whitespace by `trim`.  Also for strip we can compare if string is blank, that because strip in case of string consisting only of whitespaces, will return empty string as literal from string pool, just `””`, but trim work differently. Yet it's not the best solution for production code, cause javadoc says `If this String object represents an empty string, or if all code points in this string are white space, then an empty string is returned.`, it's not clear whether the new empty string created or not. That's why for this check use `isBlank()` - true if no chars, only whitespaces. `.isEmpty()` - true if size 0.
```java
public class App {
    public static void main(String[] args){
        System.out.println(isBlankStrip(" "));      // true
        System.out.println(isBlankTrim(" "));   // false
    }
    private static boolean isBlankStrip(String str){
        String empty = str.strip();
        return empty == "";
    }
    private static boolean isBlankTrim(String str){
        String empty = str.trim();
        return empty == "";
    }
}
```

`+` create new string object, that’s why following code return false
```java
public class App {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hell";
        s2 += "o";
        System.out.println("s1 => " + s1 + ", s2 => " + s2);
        System.out.println("s1.equals(s2) =>" + s1.equals(s2));
        System.out.println("s1 == s2 => " + (s1 == s2));
    }
}
```
```
s1 => hello, s2 => hello
s1.equals(s2) =>true
s1 == s2 => false
```

When `replace` chars, in case of the same char it returns the same object, but if we replace substring, and in case the same substring it returns new object
```java
String str = "hello";
System.out.println(str.replace('h', 'h')==str); //true
System.out.println(str.replace("h", "h")==str); //false
```

`StringBuilder` capacity - is the size of underlying `byte[]`, by default is 16, and grow together as string length grows. So it either equal or greater than `str.length()`.
```java
var sb = new StringBuilder("hello world");
System.out.println("capacity => " + sb.capacity());
System.out.println("length => " + sb.length());
```
```
capacity => 27
length => 11
```

We can also set:
* capacity with `ensureCapacity` - if new capacity is bigger than old, new array is allocated with new capacity length
* length with `setLength` - call to `ensureCapacity` + fill underlying `byte[]` with 0 bytes
```java
class App {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.length() + " " + sb.capacity());
        sb.append("hello world");
        System.out.println(sb.length() + " " + sb.capacity());
        sb.setLength(50);
        sb.ensureCapacity(50);
        System.out.println(sb.length() + " " + sb.capacity());
        System.out.println(sb);
        sb.setLength(5);
        System.out.println(sb);
        sb.ensureCapacity(5);
        System.out.println(sb.length() + " " + sb.capacity());
    }
}
```
```
0 16
11 16
50 50
hello world                                       
hello
5 50
```
Pay attention, that we can only increase capacity, so setting value less than current, will not affect capacity. We can also use `trimToSize()` to trim StringBuilder to current length. Setting capacity in no way affect `StrinbBuilder` you can set it to improve performance. Setting length affect the `StrinbBuilder`. If it more than current length it fills `StrinbBuilder` with empty data, if less it trims sb to set value.
```java
public class App {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("hello world");
        sb.setLength(5);
        sb.setLength(10);
        sb.append("end");
        System.out.println(sb);
    }
}
```
```
hello     end
```

String’s `substring` - chainable, but StringBuilder’s `substring` - is not, cause both return `String`.
```java
String s = "";
s.concat("1").substring(1).concat("1");

StringBuilder sb = new StringBuilder();
sb.append("1").substring(1).append("1"); //won't compile, substring => returns String
```

You can add characters to each other or int, when you do this, character is transformed into int
```java
System.out.println((int)'a');
System.out.println((int)'b');
System.out.println('a' + 'b');
```
```
97
98
195
```

Compare 2 `StringBuilder` classes: Because `StringBuilder` doesn’t implement `equals` method and use it from `Object.equals` it returns true only if we compare the same instances. To compare them by their content, we can use `compareTo` method. `Objects.equals` also won’t work since it’s just a safe call to a.equals(b). So we can use 2 ways to compare
```java
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("test");
        StringBuilder sb2 = new StringBuilder("test");
        System.out.println("sb1.equals(sb2) => " +  sb1.equals(sb2));
        System.out.println("Objects.equals(sb1, sb2) => " + Objects.equals(sb1, sb2));
        System.out.println("sb1.toString().equals(sb2.toString()) => " +  sb1.toString().equals(sb2.toString()));
        System.out.println("sb1.compareTo(sb2) == 0 => " + (sb1.compareTo(sb2) == 0));
    }
}
```
```
sb1.equals(sb2) => false
Objects.equals(sb1, sb2) => false
sb1.toString().equals(sb2.toString()) => true
sb1.compareTo(sb2) == 0 => true
```
**`StringBuilder.compareTo` works the same as String.compareTo and close to `Arrays.compare`.

`compareTo` works as `left - right` and return int difference between 2 char. since upper letters precedes lower letters, `a` is higher than `A`.
```java
public class App{
    public static void main(String[] args) {
      System.out.println("A => " + (int)'A' + ", a => " + (int)'a');
      System.out.println("difference between a & A => " + "amy".compareTo("Amy"));
      System.out.println("difference between 9 & 6 => " +"97".compareTo("65"));
    }
}
```
```
A => 65, a => 97
difference between a & A => 32
difference between 9 & 6 => 3
```

###### Arrays
Valid array declarations
```java
class App {
    public static void main(String[] args) {
      int[] arr1 = new int[3];
      int[] arr2 = new int[]{1, 2, 3};
      int[] arr3 = {1, 2, 3};
      int arr4[] = new int[3];
      var arr5 = new int[]{1,2,3};
      int[][] arr6 = {{1,2}, {}, new int[5]};
      int[][] arr7[][] = new int[10][][][]; // four-dimension array with size of first array of 10
      int[][] arr8 = new int[3][5]; // initialize array of 3 elements, where each element is array of 5 ints
      int[][] arr9 = new int[3][]; // initialize array of 3 elements, where each element is null
      // invalid declarations
      var arr10 = {1,2,3}; // wont' compile, also var[] arr or var arr[] - invalid
      int[] arr11 = new int[3]{1,2,3}; // won't compile, size should be missing in this case => array creation with both dimension expression and initialization is illegal

      
      Integer[] arr = new Integer[10];
      Object[] objArr = arr;
      int [] intArr = new int [10];
      // won't compile: incompatible types: int[] cannot be converted to java.lang.Object[]
      Object[] objArr2 = intArr;
      // compiles fine
      Object[] objArr3 = new int[10][];
    }
}
```

Jagged array - for multidimensional array, java doesn't have a concept of matrix like 2x3, so it has array of arrays, and memory layout not clear defined. This is why in java it's called jagged array. But in C# we can have 2 types of array, kind of matrix-based and jagged. Java doesn’t have the concept of single or multidimensional arrays, If the elements of an array are themselves arrays, we call it a multidimensional array but from the language perspective, it is still just an array.

Array class name - java make up class name using followint notatioin: `{dimensions}{letter_of_type}`
Below is the list for letters:
```
boolean Z
byte B
char C
short S
int I
long J
float F
double D
any Object L
```
If you run this code:
```java
int[] intArr = new int[10];
String[][] strArr = new String[10][];
Object[][][] objArr = new Object[10][][];
System.out.println("intArr => " + intArr.getClass().getName());
System.out.println("strArr => " + strArr.getClass().getName());
System.out.println("objArr => " + objArr.getClass().getName());
```
You will get this output, where bracket `[` denotes how many dimensions array has, leter denotes type of array
```
intArr => [I
strArr => [[Ljava.lang.String;
objArr => [[[Ljava.lang.Object;
```


Array index can be int/short/byte/char, you can't use long as index. Such code won't compile. And this is clear, since array store data in the heap, you can't get unlimited chunk of data with long, cause it's too big number. If you need such a big array you can use `Unsafe` - but this is advanced java. As of now, just remember that long index won't compile:
```java
long index = 5;
int[] arr = new int[index]; // this line won't compile
int[] arr2 = new int[(int)index]; // here we cast to int, so it would compile normally
```

`arr.clone` and `Arrays.copyOf` - copy only array itself (shallow copy), not objects inside.
```java
public class App {
    public static void main(String[] args) {
        Person[] arr = {new Person("Mike"), new Person("Jack")};
        Person[] copy = arr.clone();
        System.out.println(arr[0].getName());
        copy[0].setName("John");
        System.out.println(arr[0].getName());
    }
}
class Person{
    private String name;
    public Person(String name){
        setName(name);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
```
```
Mike
John
```
As you see, after we change name of object inside cloned array, it was also changed inside main array.

Java arrays are reified - their types are checked in runtime, but generics are not, that's why you can assign array of one type to another, and no compilation error, but it won't work with generics, you get compilation error.
What you need to understand regarding array reify: this is opposite to generics, because generics check type on compile-time, so your code won't compile, but arrays check only in runtime, that's why code would compile, but throw exception.
```java
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Integer[] arr = {1};
        Object[] objects = arr;
        objects[0] = new Object(); // throws java.lang.ArrayStoreException
        List<Integer> list = new ArrayList<>(List.of(1));
        List<Object> objectList = list; // won't compile
        // for the same reason you can't create array of generics
        List<String> genericArr[] = new List<String>[10]; // won't compile
        List arr[] = new List[10];
    }
}
```

You can't create array of generics, but you can use it as param type, for this reason all methods like `m1(List<String>[] ls)` and `m1(List<String>... ls)` will generate warnings. The reason is that it's easy to mess up and get `ClassCastException`, and compiler can do nothing about (due to array reification).
```java
import java.util.ArrayList;
import java.util.List;

public class App{
    public static void main(String[] args) {
        List<Integer> ls = new ArrayList<>();
        modify(ls);
    }
    private static void modify(List<Integer>... arr){ // warning: unchecked or unsafe operation
        Object[] objArr = arr;
        objArr[0] = new ArrayList<>(List.of("hello"));
        Integer i = arr[0].get(0);
    }
}
```
```
Exception in thread "main" java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer
```

Compare and stringify arrays. Since `equals` on array doesn’t get desired result and `toString` return name+hash, we should use `Arrays` helper class
```java
import java.util.Arrays;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {1,2,3,4,5};
        System.out.println("arr1.toString() => " + arr1.toString());
        System.out.println("Arrays.toString(arr1) => " + Arrays.toString(arr1));
        System.out.println("arr1.equals(arr2) => " + arr1.equals(arr2));
        // Objects.equals call object's equals method + handling null
        System.out.println("Objects.equals(arr1, arr2) => " + Objects.equals(arr1, arr2));
        System.out.println("Arrays.equals(arr1, arr2) => " + Arrays.equals(arr1, arr2));
        System.out.println("Arrays.compare(arr1, arr2) => " + Arrays.compare(arr1, arr2));
    }
}
```
```
arr1.toString() => [I@85ede7b
Arrays.toString(arr1) => [1, 2, 3, 4, 5]
arr1.equals(arr2) => false
Objects.equals(arr1, arr2) => false
Arrays.equals(arr1, arr2) => true
Arrays.compare(arr1, arr2) => 0
```

If you need to split string on multiple keys, you can use regex `[]`
```java
import java.util.Arrays;
public class App{
    public static void main(String[] args) {
        // split on space,comma,dot
        System.out.println(Arrays.toString("hello world,cool.main".split("[ ,.]")));
    }
}
```
```
[hello, world, cool, main]
```

Don't confuse decimal & hex arrays
We can convert hex string into 2 types of byte array:
* hex array - where 2 symbols - single byte. Since max hex ff - 256 but byte is 127, our range for each symbol: -128 => 127
* decimal array - where each symbol - separate byte. so each symbol is value of: 0 => 127
As you see hex range is twice the size of decimal range, that's why it's size is twice smaller
So if we have 6 length hex string then:
* hex array - length 3
* decimal array - length 6
computer terminology:
* octet - 8 bits, basically a byte, but used if byte is ambiguous
* nibble/semi-octet/quadbit - half-byte, or 4 bits
```java
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class App{
    public static void main(String[] args) {
        String hex = "123abc";
        byte[] charBytes = hex.getBytes();
        byte[] hexBytes = hexToBytes(hex);
        System.out.println("charBytes => " + Arrays.toString(charBytes));
        System.out.println("hexBytes => " + Arrays.toString(hexBytes));
        System.out.println();
        System.out.println("char of 99 => "+(char)99);
        System.out.println("hex representation of 99 => "+Integer.toHexString(99));
        System.out.println("decimal representation of 99 => "+Integer.parseInt("99", 16));
    }

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

  /**
   * first character is shift on 4 bits to store in higher nibble
   * second character is stored in lower nibble
   * then 2 nibbles are combined into single byte
   * The bytes are 8 bit signed integers in Java. Therefore, we need to convert each 4-bit segment to hex separately and concatenate them. Consequently, we’ll get two hexadecimal characters after conversion
   */
    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
```
```
charBytes => [49, 50, 51, 97, 98, 99]
hexBytes => [18, 58, -68]

char of 99 => c
hex representation of 99 => 63
decimal representation of 99 => 153
```

###### Arrays.compare and Arrays.mismatch
These 2 functions are used to compare 2 arrays and found their common prefix.
```java
import java.util.Arrays;
import java.util.stream.Collectors;

public class App{
    public static void main(String[] args) {
        /**
         * When compare alphabet arrays:
         * Arrays.compare and str.compareTo => difference between these letters as left - right
         * Array.mismatch => length of common prefix
         */
        char[] arrHello = {'h', 'e', 'l', 'l', 'o'};
        char[] arrHelp = {'h', 'e', 'l', 'p'};
        String strHello = new String(arrHello);
        String strHelp = new String(arrHelp);
        System.out.println("l => " + (int) 'l' + ", p => " + (int) 'p');
        System.out.println("arr1 => " + Arrays.toString(arrHello) +", arr2 => " + Arrays.toString(arrHelp));
        System.out.println("Arrays.compare => " + Arrays.compare(arrHello, arrHelp));
        System.out.println("compareTo => " + strHello.compareTo(strHelp));
        System.out.println("Arrays.mismatch => " + Arrays.mismatch(arrHello, arrHelp));

        System.out.println();
        /**
         * In case one array is just subarray of another
         * Arrays.compare and compareTo => difference of how many chars one array longer than other
         */
        char[] arrHel = {'h','e','l'};
        String strHel = new String(arrHel);
        System.out.println("arr1 => " + Arrays.toString(arrHello) +", arr2 => " + Arrays.toString(arrHel));
        System.out.println("Arrays.compare => " + Arrays.compare(arrHello, arrHel));
        System.out.println("compareTo => " + strHello.compareTo(strHel));
        System.out.println("Arrays.mismatch => " + Arrays.mismatch(arrHello, arrHel));

        System.out.println();
        /**
         * In case of numeric arrays
         * Arrays.compare => 1/-1 which array is larger
         * Arrays.compare and compareTo if we transform ints to strings => difference between numbers
         * Array.mismatch => length of common prefix
         */
        int[] arr129 = {1,2,9};
        int[] arr1234 = {1,2,3,4};
        String str129 = Arrays.stream(arr129).mapToObj(String::valueOf).collect(Collectors.joining());
        String str234 = Arrays.stream(arr1234).mapToObj(String::valueOf).collect(Collectors.joining());
        String[] strArr129 = Arrays.stream(arr129).mapToObj(String::valueOf).toArray(String[]::new);
        String[] strArr1234 = Arrays.stream(arr1234).mapToObj(String::valueOf).toArray(String[]::new);
        System.out.println("arr1 => " + Arrays.toString(arr129) +", arr2 => " + Arrays.toString(arr1234));
        System.out.println("Arrays.compare => " + Arrays.compare(arr129, arr1234));
        System.out.println("Arrays.compare (strings) => " + Arrays.compare(strArr129, strArr1234));
        System.out.println("compareTo => " + str129.compareTo(str234));
        System.out.println("Arrays.mismatch => " + Arrays.mismatch(arr129, arr1234));

        /**
         * In case of empty array
         * Arrays.compare and compareTwo => length of non-empty array
         */
        System.out.println();
        int[] arrEmpty = {};
        String strEmpty = "";
        System.out.println("arr1 => " + Arrays.toString(arr129) +", arr2 => " + Arrays.toString(arrEmpty));
        System.out.println("Arrays.compare => " + Arrays.compare(arr129, arrEmpty));
        System.out.println("compareTo => " + str129.compareTo(strEmpty));
        System.out.println("Arrays.mismatch => " + Arrays.mismatch(arr129, arrEmpty));
    }
}
```
```
l => 108, p => 112
arr1 => [h, e, l, l, o], arr2 => [h, e, l, p]
Arrays.compare => -4
compareTo => -4
Arrays.mismatch => 3

arr1 => [h, e, l, l, o], arr2 => [h, e, l]
Arrays.compare => 2
compareTo => 2
Arrays.mismatch => 3

arr1 => [1, 2, 9], arr2 => [1, 2, 3, 4]
Arrays.compare => 1
Arrays.compare (strings) => 6
compareTo => 6
Arrays.mismatch => 2

arr1 => [1, 2, 9], arr2 => []
Arrays.compare => 3
compareTo => 3
Arrays.mismatch => 0
```

#### Classes and Objects
###### toString, equals, hashcode, clone
These are methods of `Object` class itself, and since every class in java in the end is inherited from it, they all can override these methods. Pay attention that `equals` take `Object`, not Person. If you try to override it with Person param, If you are using `@Override` annotation you will get compile time error, if not you've created method overloading. It would work, but whenever `equqls` called , it won't use your version, but from `Object` itself. 
By default `Object.clone` is `protected`, so you have to implement `Cloneable` interface and  override the method to be able to call this method. If you just try to override the method without implementing `Cloneable` you will get `CloneNotSupportedException`. If you need to clone object you can override `clone` from object (class should implement `Cloneable`), or you can create your own version of clone like constructor cloning or `customClone` method.
```java
public class App{
    public static void main(String[] args) {
        Person p1 = new Person("Mike", 30);
        Person p2 = new Person("Jack", 30);
        System.out.println("p1 => " + p1 + ", p2 => " + p2);
        System.out.println("p1.hashCode => " + p1.hashCode() + ", p2.hashCode => " + p2.hashCode());
        System.out.println("p1.equals(p2) => " + p1.equals(p2));

        Person clone = p1.clone();
        System.out.println("p1 == clone => " + (p1 == clone));
        System.out.println("p1.equals(clone) => " + p1.equals(clone));
    }
}


class Person implements Cloneable{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    // custom way to create clone with constructor
    public Person(Person person){
        this.name = person.name;
        this.age = person.age;
    }
    // custom clone method
    public Person customClone(){
        return new Person(this.name, this.age);
    }

    @Override
    public Person clone(){
        try{
            return (Person)super.clone();
        } catch (CloneNotSupportedException ex){
            throw new RuntimeException(ex); // will throw if object doesn't implement Cloneable
        }
    }

    @Override
    public String toString() {
        return "Person[name=" + name + ", age=" + age + "]";
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Person) {
            Person p = (Person) obj;
            return p.name.equals(name) && p.age == age;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 31 + name.hashCode() + age;
    }
}
```
```
p1 => Person[name=Mike, age=30], p2 => Person[name=Jack, age=30]
p1.hashCode => 2398291, p2.hashCode => 2300988
p1.equals(p2) => false
p1 == clone => false
p1.equals(clone) => true
```

###### Classes
Type vs state: classes, interfaces and enums - types. Since java support multiple interface implementation => java support multiple inheritance of types. 
State - instance variables of class, so only class can have state. Since java support only one class inheritance => java support one state inheritance. 
One reason why the Java programming language does not permit you to extend more than one class is to avoid the issues of multiple inheritance of state, 
which is the ability to inherit fields from multiple classes. [Multiple Inheritance of State, Implementation, and Type](https://docs.oracle.com/javase/tutorial/java/IandI/multipleinheritance.html).
Java methods' visibility:
```
_____________________________________________________________
|           │ Class │ Package │ Subclass │ Subclass │ World  |
|           │       │         │(same pkg)│(diff pkg)│        |
|───────────┼───────┼─────────┼──────────┼──────────┼────────|
|public     │   +   │    +    │    +     │     +    │   +    | 
|───────────┼───────┼─────────┼──────────┼──────────┼────────|
|protected  │   +   │    +    │    +     │     +    │        | 
|───────────┼───────┼─────────┼──────────┼──────────┼────────|
|no modifier│   +   │    +    │    +     │          │        | 
|───────────┼───────┼─────────┼──────────┼──────────┼────────|
|private    │   +   │         │          │          │        |
|___________|_______|_________|__________|__________|________|
 + : accessible         blank : not accessible
```

Immutable class - is a class without setters. We can still set values in constructor, the idea is that we can’t change values after instantiation. One caveat is when dealing with complex object, create and return value using new or unmodifiable objects. Otherwise, initial values can be changed using values passed to constructor or get from `get` method, by changing them.
```java
public class App {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("java");
        Immutable immutable = new Immutable(sb);
        sb.append("1");
        StringBuilder sb2 = immutable.getSb();
        sb2.append("2");
        System.out.println(immutable.getSb());
    }
}
class Immutable{
    private StringBuilder sb;
    public Immutable(StringBuilder sb){
        this.sb = new StringBuilder(sb);
    }

    /**
     * If you just return sb, you will lose immutability, because such object can be changed, and by this internal state would be changed
     */
    public StringBuilder getSb(){
        return new StringBuilder(sb);
    }
}
```

You can create instance of class with private constructor from within the class itself (for example in main or some static method)
```java
public class App {
    private App(){
        System.out.println("private constructor");
    }
    public static void main(String[] args) {
        App app = new App();
    }
}
```

Whenever we don’t supply any constructor, by default, java creates public one with no argument. Java also call parent constructor inserting `super()` into our one. That’s the reason why this code won’t compile. Inside `B`, java create public no-argument constructor and call super - constructor inside `A`, but since in `A` we added 1 argument constructor, java didn’t inject default one. So it can’t find default constructor in A and show you an error.
```java
class A{
    public A(int v){
    }
}
class B extends A{}  // won’t compile
```
You can fix it by manually creating any constructor in B and call super(int) from A. (if you don’t call super it won’t compile cause by default it would insert super() - calling no argument constructor of A, which doesn’t exists)
```java
class B extends A{
   public B(String s){
        super(1);
    }
}
```

Method overriding vs method hiding: 
* overriding - override non-static method
* hiding - override static method
The difference is - when we override method it’s overridden in both child and parent class, but when we hide it - it’s only for child class. 
Notice when we call `printA`, the `getName` return B not A, since it’s overridden in B, so it’s call method from B, but `getStaticName` call it from A.
```java
public class App {
    public static void main(String[] args) {
        B b = new B();
        b.printB();
        b.printA();
    }
}
class A {
    public String getName() {
        return "A";
    }
    public static String getNameStatic() {
        return "A";
    }
    public void printA() {
        System.out.println("printA => name: " + getName() + ", staticName: " + getNameStatic());
    }
}
class B extends A {
    public String getName() {
        return "B";
    }
    public static String getNameStatic() {
        return "B";
    }
    public void printB() {
        System.out.println("printB => name: " + getName() + ", staticName: " + getNameStatic());
    }
}
```
```
printB => name: B, staticName: B
printA => name: B, staticName: A
```

We can’t override variables, we can only hide them
```java
public class App {
    public static void main(String[] args) {
        B b = new B();
        b.printB();
        b.printA();
    }
}
class A {
    protected int i = 1;
    public void printA(){
        System.out.println("printA => i: " + i);
    }
}
class B extends A {
    protected int i = 2;
    public void printB(){
        System.out.println("printB => i: " + i);
    }
}
```
```
printB => i: 2
printA => i: 1
```

Polymorphism is the ability to pass one object as type of another. Here instance `b` is reference to object `B`, but its type is `A`. Since it’s type is A it’s value is limited, so we can call only methods available in A. 
The point here if we override some method from A in B, then when we call these methods, they will be called from B. To call all B methods, we must cast it to type B.
```java
public class App {
    public static void main(String[] args) {
        A b = new B();
        System.out.println(b.getValue1()); // 1
        System.out.println(b.getName()); // won't compile
        
        B b2 = (B)b;
        System.out.println(b2.getName()); // B
    }
}
class A {
    public int getValue1(){
        return 1;
    }
}
class B extends A {
    public String getName(){
        return "B";
    }
}
```
There are 2 types of polymorphism:
* compile-time polymorphism (overloading) - when methods have the same name, but different arguments. So we can call them, and java knows in compile time what method we are calling, based on arguments. Although there is an argument, that there is no strict compile-time polymorphism in java, so method overloading we can count as compile-time polymorphism.
* runtime polymorphism (overriding) - this is classical polymorphism where we pass reference. The problem is that on compile time, java can't know what reference would be passed. If both classes A & B, implements interface X, and we pass X as param, java can't know what implementation would be used, because we can change it dynamically during execution. 
That's why we can say that java supports both:
* compile-time polymorphism - when we overload methods
* runtime polymorphism - when we override (extend/implement) and pass reference

We can cast to parent without parenthesis (without explicit casting). But casting to child requires explicit casting, otherwise it won't compile with `java: incompatible types: com.java.test.A cannot be converted to com.java.test.B`.
```java
public class App {
  public static void main(String[] args) {
    B b = new B();
    A a = b;
    B b2 = (B) a;
  }
}
class A{}
class B extends A{}
```

If 2 classes extend from same base class, or implement same interface, we can cast them with so-called "double cast"
```java
class A{}
interface X{}
class B extends A implements X{}
class C extends A implements X{}

public class App {
    public static void main(String[] args) {
        B b = new B();
        C c1 = (C)(X)b; // first cast to X (common interface), then to class C
        C c2 = (C)(A)b; // first cast to base class A (common class), then to C
    }
}
```
But since `B` and `C` are unrelated, we get `ClassCastException`
```
Exception in thread "main" java.lang.ClassCastException: class com.example.demo.B cannot be cast to class com.example.demo.C (com.example.demo.B and com.example.demo.C are in unnamed module of loader 'app')
	at com.example.demo.App.main(App.java:12)
```

If object is null, you can still call static method on it. The compiler checks for the type of the reference and uses that instead of the object.
```java
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.printStatic("static");
        app.print("non-static");
        app = null;
        app.printStatic("static"); // static
        app.print("non-static"); // Exception in thread "main" java.lang.NullPointerException
    }
    public static void printStatic(String str){
        System.out.println(str);
    }
    public void print(String str){
        System.out.println(str);
    }
}
```

Static and instance initializers - order of execution:
* first - static variable/block in order they are in code
* second - instance variable/block of code, in order they are in code
* third - constructor
* In case one class extends other, fist all initializers static/instance/constructor of parent fires than of child.
```java
public class App {
    public static final int NUM_VALUE;
    public App(){
        System.out.println("Constructor");
    }
    // runs only once when class is loaded
    static {
        System.out.println("Static Initializer");
        NUM_VALUE = 1;
    }
    // runs every time we create new instance
    {
        System.out.println("Instance Initializer");
    }
    public static void main(String[] args) {
        System.out.println(App.NUM_VALUE);
        App app = new App();
    }
}
```
```
Static Initializer
1
Instance Initializer
Constructor
```

illegal forward reference:
* instance initializer can't access variable that declared after the block (same true for static)
* you can access methods from initializers even though such methods were declared after the initializer
* yet method can access such variable
```java
class Person {
  static {
    // compile error: illegal forward reference
    System.out.println(name);
    name = "Jack";
  }
  {
    // compile error: illegal forward reference
    System.out.println(age);
    init();
    age = 30;
  }
  void init() {
    System.out.println(age);
    age = 20;
  }
  private int age;
  private static String name;
}
```

Java can import static members from class
```java
import static java.util.Arrays.asList;
import java.util.List;
public class App {
    public static void main(String[] args) {
        List<Integer> list = asList(1,2,3);
    }
}
```

There are 2 ways to create singleton. 
First - create instance the same time we load our class. In this case field is `final`.
```java
class Singleton {
    private Singleton() {
        System.out.println("Singleton::constructor");
    }
    private static final Singleton instance = new Singleton();
    public static Singleton getInstance() {
        return instance;
    }
}
public class App {
    public static void main(String[] args) {
        int n = 10;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(n);
        ExecutorService service = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            service.execute(() -> {
                wait(start);
                Singleton.getInstance();
                end.countDown();
            });
        }
        System.out.println("start");
        start.countDown();
        wait(end);
        System.out.println("finish");
        service.shutdown();
    }
    private static void wait(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException ex) {
            System.out.println("ERR: " + ex);
        }
    }
}
```

Second  - lazy loading, create instance the first time we call static method `.getInstance`. The downside is that we can’t make instance `final`. So it’s up to the code to guarantee that we won’t reassign new value.
```java
public class App {
    public static void main(String[] args) {
        int n = 10;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(n);
        ExecutorService service = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            service.execute(() -> {
                wait(start);
                LazySingleton.getInstance();
                end.countDown();
            });
        }
        System.out.println("start");
        start.countDown();
        wait(end);
        System.out.println("finish");
        service.shutdown();
    }
    private static void wait(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException ex) {
            System.out.println("ERR: " + ex);
        }
    }
}
class LazySingleton {
    private static LazySingleton singleton;
    private LazySingleton() {
        System.out.println("Singleton::constructor");
    }
    public static LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
```
```
start
Singleton::constructor
Singleton::constructor
Singleton::constructor
Singleton::constructor
Singleton::constructor
Singleton::constructor
Singleton::constructor
Singleton::constructor
Singleton::constructor
Singleton::constructor
finish
```

But this approach is not thread save, cause a few thread may create instance at the same time. To make it thread-safe add `syncronized` keyword to `getInstance` method. The problem to make the whole method `syncronized` is that whenever it’s called it can be called only by one thread. 
We don’t want it, what we would like is to synchronize it the first time, all other times we would like just to return object. You can do this with so called `double-checked locking`. Notice we’ve also made instance as `volatile` just to prevent compiler to rearrange source code.
Yet if we remove `volatile`, code would still be thread-safe, and only 1 instance would be created.
```java
class LazySingleton {
    private static volatile LazySingleton instance;
    private LazySingleton() {
        System.out.println("Singleton::constructor");
    }
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
```

We can also use `Enum` to create instance
```java
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) {
        int n = 10;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(n);
        ExecutorService service = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            service.execute(() -> {
                wait(start);
                Singleton.INSTANCE.print();
                end.countDown();
            });
        }
        System.out.println("start");
        start.countDown();
        wait(end);
        System.out.println("finish");
        service.shutdown();
    }
    private static void wait(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException ex) {
            System.out.println("ERR: " + ex);
        }
    }
}
enum Singleton {
    INSTANCE;
    Singleton(){
        System.out.println("Singleton::constructor");
    }
    public void print(){}
}
```
```
start
Singleton::constructor
finish
```
We can also use lazy-loading singleton with internal class. As you see we don't need to have any `synchronized` code inside `getInstance` method.
```java
class Singleton {
    private Singleton() {}
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder {
        static final Singleton INSTANCE = new Singleton();
        private Holder() {}
    }
}
```

When we use method overloading java itself can determine the most close method to use, but sometimes it’s not the case, so we can get compile error
```java
public class App {
    public static void main(String[] args) {
        int i = 1;
        short s = 2;
        print(i, s); // reference to print is ambiguous: both method print(java.lang.Integer,short) in com.java.test.Main and method print(int,java.lang.Short) in com.java.test.Main match
    }
    public static void print(Integer i, short s){
        System.out.println(1);
    }
    public static void print(int i, Short s){
        System.out.println(2);
    }
}
```
Use of volatile can decrease performance, and in case of lazy Singleton you need it only once, and in 99% cases your object would be already created and just used. To write more performant code you can introduce local variable. Look at `localRef`, it seems redundant, but since init happens only once, and in most cases it won't be `null`, and we return it, rather than `instance`, perfromance significatnly improved.
```java
class Singleton{
    private Singleton(){}

    private static volatile Singleton instance;
    public static Singleton getInstance() {
        Singleton localRef = instance;
        if (localRef == null) {
            synchronized (Singleton.class) {
                localRef = instance;
                if (localRef == null) {
                    instance = localRef = new Singleton();
                }
            }
        }
        return localRef;
    }
}
```
Similar to this logic we can use `VarHandle` to create interface
```java
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

class Singleton{
    private Singleton(){}

    private static volatile Singleton instance;
    private static final VarHandle VALUE;
    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            VALUE = lookup.findVarHandle(Singleton.class, "instance", Singleton.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Singleton getInstance() {
        Singleton localRef = (Singleton) VALUE.getAcquire(instance);
        if (localRef == null) {
            synchronized (Singleton.class) {
                localRef = (Singleton) VALUE.getAcquire(instance);
                if (localRef == null) {
                    localRef = new Singleton();
                    VALUE.setRelease(instance, localRef); // release
                }
            }
        }
        return localRef;
    }
}
```

If we have:
* final static field - it should be initialized in all static{} blocks
* final instance field and multiple constructors - it should be initialized in all of them
The following code won’t compile (cause final var `x` not initialized in third constructor)
```java
class A{
    private final int x;
    public A(){
        x = 1;
    }
    public A(int x){
        this.x = x;
    }
    public A(int x, int y){} // won't compile
}
```

When casting values, static members get shadowed. That happens because static members - bound in compiler time (static binding), and all other in runtime (dynamic bindings).
```java
public class App {
    public static void main(String[] args) {
        Lion lion = new Lion();
        System.out.println(lion.getAge() + " " + lion.getInstanceAge());
        Animal animal = lion;
        System.out.println(animal.getAge() + " " + animal.getInstanceAge());
    }
}
class Animal{
    public static int getAge(){
        return 1;
    }
    public int getInstanceAge(){
        return 1;
    }
}
class Lion extends Animal{
    public static int getAge(){
        return 2;
    }
    public int getInstanceAge(){
        return 2;
    }
}
```
```
2 2
1 2
```

Static initializer fires when we load class.
```java
public class App {
    public static void main(String[] args) {
        B b = null; // B static initializer not run at this stage
        System.out.println(B.NAME); // B static initializer not run at this stage
        System.out.println(B.B_NAME);
    }
}
class A{
    static String NAME = "John";
}
class B extends A{
    static String B_NAME = "Jack";
    static {
        System.out.println("in B");
    }
}
```
```
John
in B
Jack
```
So when we declare `B b = null;`, no loading of class B happens. The same true when we call static instance member (both variables & methods) of its parent. But when we call static variable of its own, it's loading class and fire static initializer.

`instanceof` will always compile if one of argument is interface. The reason is that `B` can have children, that can implement interface, so it’s allowable. 
If you make class `B` final, first 2 lines won’t compile as well. Also autoboxing won't work, so you can't use primitives with `instanceof`.
```java
public class App {
    public static void main(String[] args) {
        X x = new A();
        A a = new A();
        B b = new B();
        System.out.println(x instanceof B); // false
        System.out.println(a instanceof B); // won't compile
        System.out.println(b instanceof X); // false
        System.out.println(a instanceof A); // true
        a = null;
        System.out.println(a instanceof A); // false

        int i = 1;
        System.out.println(i instanceof Integer); // won't compile, i is a primitive
        System.out.println((Integer)i instanceof Integer);
        System.out.println((Integer)i instanceof Short); // won't compile cause Integer and Short unrelated types
        System.out.println((Short)i instanceof Short); // won't compile cause int can't be casted to Short
        System.out.println((Short)(short)i instanceof Short);
    }
}
interface X{}
class A implements X{}
class B{}
```

Overloading resolution. Java tries to find the `exactMatch => widening => autoboxing => varags`
```java
public class App {
    void print(int x){}
    void print(long x){}
    void print(Integer x){}
    void print(int... x){}
    public static void main(String[] args) {
        App app = new App();
        app.print(5);
    }
}
```
by default `(int x)` would be called. If we comment it, `(long x)` would be called, if we comment that, `(Integer x)` would be called, and finally `(int... x)` would be called.

varags method param:
* it is just synthesis sugar for array, under-the-hood it converts all arguments passed into array
* if you don't pass any argument empty array is passed
* if you pass null - `null` object is used
* method can't have more than 1 varags - if you pass 2 varags params - it won't compiled
* varags param should be the last param in the method
```java
public class App {
    public static void main(String[] args) {
        System.out.println(sum(1,2,3));
        System.out.println(sum());
        // you will get NPE, cause instead of array null is passed
        System.out.println(sum(null));
    }

    public static int sum(int... values) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }
}
```
```
6
0
Exception in thread "main" java.lang.NullPointerException: Cannot read the array length because "<local2>" is null
```

Class with `main` method also can be `abstract`. The reason, is that we can call `static` methods on `abstract` class. Notice that field/method can't be `abstract static`.
```java
abstract class App {
    abstract void calculate();
    abstract static void print(); // won't compile, abstract static - not valid
    public static void main(String[] args) {
        System.out.println("cool");
        App app = null;
        app.calculate();
    }
}
```
```
cool
Exception in thread "main" java.lang.NullPointerException
```

Order of execution. In `A` constructor `App.print` would be called, but since `App` is not initialized yet, than `i=0`. But on second time, `i` would be properly initialized and display 4.
```java
class A {
    {
        System.out.println("init A");
    }
    A() {
        print();
    }
    void print() {
        System.out.println("A");
    }
}
class App extends A {
    {
        System.out.println("init App");
    }
    int i = 4;
    public static void main(String[] args) {
        A a = new App();
        a.print();
    }
    void print() {
        System.out.println(i);
    }
}
```
```
init A
0
init App
4
```
Liskov substitution principle for java inheritance:
1. method argument types - overridden method argument should be identical or wider. But in java we support only identical. As you see java not fully supported this rule. Using subtype is overloading, that's why in Java only identical method can be used for overriding.
```java
interface Printer{
  void print(A a);
}

class ConsolePrinter implements Printer{
  @Override
  public void print(B a) {} // compile-time error
}

class A{}
class B extends A{}
```
2. return type should be identical or subtype - called covariance in java
3. exception - overridden method can throw fewer/narrower exception. Java support this on compile level for checked exception. Yet for unchecked like `RuntimeException` it can't enforce it, so we can see code smell (see my task for interview questions on accounts)

If you necessarily have to use overriding with subtype param, you can use generics
```java
public class App {
    public static void main(String[] args) {
        Printer<A> printer = new PrinterA();
        printer.print(new A());
        Printer<B> printer2 = new PrinterB();
        printer2.print(new B());
    }
}

interface Printer<T extends A>{
    void print(T t);
}
class PrinterA implements Printer<A>{
    @Override
    public void print(A a) {}
}
class PrinterB implements Printer<B>{
    @Override
    public void print(B b) {}
}

class A{}
class B extends A{}
```

This is done on purpose to protect you. Look at below example, now we have overloading, and you can call 2 `print` on `child` instance. But imagine that we could override. Then the question, which exactly out of 2 were we allowed to call? At first, you could only call `print(B b)` - because it's overriding. But this would break Liskov principle, cause you should be able to call on child `print(A a)`. To avoid any confusion in java params should be identical for overriding.
```java
public class App {
    public static void main(String[] args) {
        Child child = new Child();
        child.print(new A());
        child.print(new B());
    }
}

class Parent{
    public void print(A a){
        System.out.println("parent");
    }
}

class Child extends Parent{
    public void print(B b){
        System.out.println("child");
    }
}

class A{}
class B extends A{}
```

###### Interfaces
All variables in interface are always `public static final`, and can be called both from interface or from its instance. Which is different from `static` methods that can be called only from interface.
```java
public class App {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(I.i1 + " " + I.i2 + " " + I.i3 + " " + I.i4);
        System.out.println(a.i1 + " " + a.i2 + " " + a.i3 + " " + a.i4);
    }
}
interface I {
    int i1 = 1;
    static int i2 = 2;
    final static int i3 = 3;
    public static final int i4 = 4;
}
class A implements I{}
```
```
1 2 3 4
1 2 3 4
```

Interfaces can have 2 methods:
* `default` - can be only called on instance of class that implements interface. Were added in java 8, for backward compatibility, when your interface already have implementations, and if you just add abstract method, the code is broken and won't compile until you implement such abstract method in all classes. But if you add default method, nothing is broken (assuming no name collision).
* `static` - can only be called on interface itself.
```java
public class App {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.m1());
        System.out.println(a.m2()); // won't compile
        System.out.println(I.m2());
        System.out.println(I.m1()); // won't compile
    }
}
interface I {
    default int m1(){
        return 1;
    }
    static int m2(){
        return 2;
    }
}
class A implements I{}
```
```
1
2
```

So unlike with classes there is no such thing as method hiding for interface. If we overwrite static method, it behaves like newly created static method
```java
public class App {
    public static void main(String[] args) {
        A.print(); // prints A
        B.print(); // prints B
        B b = new B();
        b.print(); // prints B
        A a = new B();
        // you can call static interface method only on interface itself
        a.print(); // compiler's error: illegal static interface method call
    }
}
interface A{
    static void print(){
        System.out.println("A");
    }
}
class B implements A {
    static void print() {
        System.out.println("B");
    }
}
```

when a class implements 2 interfaces with the same:
* variable (static) - it would compile, but if you try to access such static variable from instance of that class, you will get compilation error: `reference to size is ambiguous; both variable size in com.java.test.A and variable size in com.java.test.B match`
* default method - code won't compile: `types com.java.test.A and com.java.test.B are incompatible; class com.java.test.C inherits unrelated defaults for print() from types com.java.test.A and com.java.test.B`
```java
public class App {
    public static void main(String[] args) {
        C c = new C();
        System.out.println(((A)c).size); // 1
        System.out.println(((B)c).size); // 2
        System.out.println(c.size); // won't compile
    }
}
interface A{
    int size = 1;
}
interface B{
    int size = 2;
}
class C implements A, B{}
```
2 same default methods
```java
interface A {
    default void print(){
        System.out.println("A");
    }
}
interface B {
    default void print(){
        System.out.println("B");
    }
}
class C implements A, B{} // won't compile
```

method signature:
* inclde only `name` + `ordered list of arguments`
* all other like modifier, return type, name of params not counted as signature
* all the below 4 methods have the same signature
```java
int sum (int a, int b){}
void sum (int a, int b){}
private void sum (int a, int b){}
int sum (int value1, int value2){}
```
* method "overloading" - same name but different set of params, so your method is "overloaded" - means there are multiple possibilities with the same name
* method overloading compilation error: happens only if you try to call it with ambiguous params, by default those 2 `sum` function are overloaded, and no errors, only if you try to call with ambiguous param, it became an issue
```java
public class App {
  public static void main(String[] args) {
    int a = 5;
    short b = 10;
    // compilation error: reference to sum is ambiguous
    sum(a, b);

    // compile fine
    sum((short) a, b); // calls sum(int, Short)
    sum((Integer) a, b); // calls sum(Integer, short)
    sum(a, (Short) b); // calls sum(int, Short)
  }

  public static void sum(Integer a, short b) {}
  public static void sum(int a, Short b) {}
}
```
* subtype for primitives:
  * `double > float > long > int > char`
  *                         `int > short > byte`
  * so if you have 2 methods that take `int` and `short` and you pass `byte` then method with `short` would be called as the more specific

If class implements 2 interfaces with same signature default method, but one override another, no compile error.
```java
public class App {
    public static void main(String[] args) {
        A a = new C();
        B b = new C();
        C c = new C();
        System.out.println(a.getValue());
        System.out.println(b.getValue());
        System.out.println(c.getValue());
    }
}
interface A{
    default String getValue(){
        return "A";
    }
}
interface B extends A{
    default String getValue(){
        return "B";
    }
}
class C implements A, B{}
```
```
B
B
B
```
Since `B` is `A`. implementing both A, B is redundant so it the same as just implement only `B`. That’s why from compile perspective it’s like implementing only `B`, since no compile error. And if you try to call this method on class instance, you will get it from `B`.

If class implements 2 interfaces, and both have the method with same signature, but one is abstract, another one is default, class is required to redeclare it: `com.java.test.A is not abstract and does not override abstract method print() in com.java.test.X`
```java
interface X{
    void print();
}

interface Y{
    default void print(){
        System.out.println("Y.print");
    }
}

class A implements X, Y{} // compilation error
```
The reason, is that JLS protect you, in case you accidentally add default method to some interface, you should still stick to contract in another implementation of this interface. Yet it works fine in case class has method's implementation, or if `Y extends X`
```java
interface X{
    void print();
}

class A{
    public void print(){}
}

class B extends A implements X{}
```

Same interface can't have abstract and default/static method of same name - compilation error:
```java
interface X{
    void print(); // won't compile
    default void print(){}
}
```

When both class and interface has default implementation, class implementation takes priority. In this case there is no way to call this method of interface.
```java
public class App {
    public static void main(String[] args) {
        B b = new B();
        b.print();
        ((A)b).print();
        ((X)b).print();
    }
}

class A{
    public void print(){
        System.out.println("A");
    }
}
interface X{
    default void print(){
        System.out.println("X");
    }
}

class B extends A implements X{}
```
```
A
A
A
```
As you see, no matter how we call it, we always call method print from class A.

In java we can cast to intersection of 2 types:
* You can only have 1 class but have multiple interfaces
* If you have a class it must be the first one specified
* this heavily used in generics with `List<T extends X & Y>`
* There is no way to imitate OR, anything like `X | Y` is invalid, except for multi-catch block with several exceptions
```java
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        class My implements X, Y{};
        My my = new My();
        print(my);
    }
    private static void print(Object obj){
        var my = (X & Y) obj;
        my.printY();
        my.printY();
        
        // same before `var` keyword
        Optional.of((X & Y) obj).ifPresent(x->{
            x.printX();
            x.printY();
        });
    }
}

interface X{
    default void printX(){}
}
interface Y{
    default void printY(){}
}
```

You can override final variables, but not methods
```java
class A{
    public static final int i = 1;
    public final int i1 = 1;

    public static final void m1(){}
    public final void m2(){}
}
class B extends A{
    public static final int i = 2;
    public final int i1 = 2;

    public static final void m1(){} // won't compile can't hide final
    public final void m2(){} // won't compile, can't override final
}
```

If class extends other class and implement interface with same final static variable we got compile error. The same true if class implement 2 interfaces with same variable.
```java
public class App{
    public static void main(String[] args) {
        System.out.println(B.i); // compile error, ambiguous call
    }
}

interface X{
    int i = 1;
}
class A{
    public static final int i = 1;
}
class B extends A implements X{}
```

Class and interface can’t have static & instance method with the same signature. The same applies to overriding, it's illegal to override instance with static & vice versa.
```java
interface X{
    default void print(){}
    static void print(){} // compile error
}

class A{
    void print(){}
    static void print(){} // compile error
}
```

If class implements 2 interfaces with same signature, but one is static and one is default, no compilation error. The reason is for interface static can only be visible from interface, not from instance, so that's why no ambiguity.
As you see, compare to classes, interface static method can't be called on instance, this is limitation of [JLS 8.4.8](http://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.8) where it's said `A class does not inherit static methods from its superinterfaces.`. This is because class can extend only 1 class, in this case for static method simple shadowing rule is working. But if class implements multiple interfaces with same static method, it would be unclear which one to call. And generally calling static method on instance is a "code smell", and should be avoided.
```java
interface X {
    static void print(){}
}
interface Y{
    default void print(){}
}
class A implements X, Y{}
```
If we try to call print on instance of A, it will use it from Y.
For the same reason we can have default method in subinterface with the same signature as static method in parent (cause static method is not visible in subinterface), but not vice versa.
```java
interface X{
    default void m1(){}
    static void m2(){}
}

interface Y extends X{
    static void m1(){} // won't compile
    default void m2(){}
}
```

Interface can have private static and instance methods, but not private default
```java
interface X{
    void m1();
    default void m2(){}
    private void m3(){}
    private default void m4(){} // won't compile
    static void m5(){}
    private static void m6(){}
}
```

We can call `default` interface method with `InterfaceName.super.methodName` keyword
```java
public class App {
    public static void main(String[] args) {
        A a = new A();
        a.print();
    }
}
interface X{
    default void print(){
        System.out.println("x");
    }
}
interface Y{
    default void print(){
        System.out.println("y");
    }
}
class A implements X, Y{
    public void print(){
        System.out.println("a");
        X.super.print();
        Y.super.print();
    }
}
```
```
a
x
y
```
As you see, we call super on interface with its name.

###### Enums
Features:
* typesafe enum pattern - pattern to implement enums before Java5. Actually java devs saw the popularity of this pattern and decided to add this feature into java5
* can't be instantiated with `new` keyword, even wihtin the enum itself - this is different from class with private constructor, inside such a class you can create new instance with `new` keyword, but inside enum you can't instantiate new enum instance
* can't extend other class, because all enums extends `java.lang.Enum`, but it can implement interface, can have methods and constructor
* is implicitly final or sealed, you can't extend it
* you can create anonymous subclasses for specific enum constants - see below for 2 enum constants we create anonymous class that implement abstract methods of enums
```java
public class App {
  public static void main(String[] args) {
    System.out.println("ADD(10, 5) => " + Math.ADD.apply(10, 5));
    System.out.println("SUBTRACT(10, 5) => " + Math.SUBTRACT.apply(10, 5));
  }
}

enum Math {
  ADD {
    @Override
    public int apply(int x, int y) {
      return x + y;
    }
  },
  SUBTRACT {
    @Override
    public int apply(int x, int y) {
      return x - y;
    }
  };

  abstract int apply(int x, int y);
}
```
This is the same code if we move `apply` into interface
```java
interface IMath {
  int apply(int a, int b);
}
enum Math implements IMath {
  ADD {
    @Override
    public int apply(int x, int y) {
      return x + y;
    }
  },
  SUBTRACT {
    @Override
    public int apply(int x, int y) {
      return x - y;
    }
  }
}
```
But you can also directly implement method of interface, but in this case all your instances would have the same behavior
```java
public class App {
    public static void main(String[] args) {
        System.out.println("ADD(10, 5) => " + Math.ADD.apply(10, 5));
        System.out.println("SUBTRACT(10, 5) => " + Math.SUBTRACT.apply(10, 5));
    }
}

interface IMath {
    int apply(int a, int b);
}
enum Math implements IMath {
    ADD,
    SUBTRACT;
    public int apply(int a, int b) {
        return a + b;
    }
}
```
```
ADD(10, 5) => 15
SUBTRACT(10, 5) => 15
```

Typesafe enum pattern
```java
class Day {
    private String day;
    private Day (String day) {
        this.day = day;
    }
    @Override
    public String toString() {
        return day;
    }

    public static final Day MON = new Day("Monday");
    public static final Day TUE = new Day("Tuesday");
    public static final Day WED = new Day("Wednesday");
    public static final Day THU = new Day("Thursday");
    public static final Day FRI = new Day("Friday");
    public static final Day SAT = new Day("Saturday");
    public static final Day SUN = new Day("Sunday");
}
```

all enums implement `java.lang.Comparable` so you can pass it into the map: below code would return -5 as `0-5= -5`
```java
public class App {
    public static void main(String[] args) {
        System.out.println(Day.MON.compareTo(Day.SAT));
    }
}

enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN;
}
```

Enum extension. There is no such thing as enum extension in java, but you can employ interface for this purpose.
```java
public class App {
  public static void main(String[] args) {
    Day mon = WorkingDay.MON;
    Day sun = WeekendDay.SUN;
    System.out.println(Day.valueOf("MON"));
    System.out.println(Day.valueOf("SAT"));
    System.out.println(Day.valueOf("Unknown"));
  }
}
enum WorkingDay implements Day{
  MON,
  TUE,
  WED,
  THU,
  FRI,
}
enum WeekendDay implements Day{
  SAT,
  SUN,
}
interface Day {
  static Day valueOf(String value) {
    try {
      return WorkingDay.valueOf(value);
    } catch (IllegalArgumentException ex) {
      try {
        return WeekendDay.valueOf(value);
      } catch (IllegalArgumentException ex2) { }
    }
    throw new IllegalArgumentException("No enum constant `" + value + "` found in WorkingDay or WeekendDay enums");
  }
}
```
```
MON
SAT
Exception in thread "main" java.lang.IllegalArgumentException: No enum constant `Unknown` found in WorkingDay or WeekendDay enums
```
As you can see, both enums implements interface, and we can pass this interface into both of them.

By default, enum keys are the same as values
```java
enum WeekDays{
    SATURDAY,
    SUNDAY;
}
```
But we can override it and can have values differ from keys. Here both constant and value are the same - SATURDAY. If we want to customize text we should add private constructor. If we want to get string values (not constants), we should define 2 additional methods
```java
import java.util.Arrays;

public class App{
    public static void main(String[] args) {
        System.out.println("WeekDays.SATURDAY => " + WeekDays.SATURDAY);
        System.out.println("WeekDays.SATURDAY.name() => " + WeekDays.SATURDAY.name());
        System.out.println("WeekDays.SATURDAY.ordinal() => " + WeekDays.SATURDAY.ordinal());
        System.out.println("WeekDays.values() => " + Arrays.toString(WeekDays.values()));
        System.out.println("WeekDays.valueOf(SATURDAY) => " + WeekDays.valueOf("SATURDAY"));
        System.out.println("WeekDays.fromValue(Saturday) => " + WeekDays.fromValue("Saturday"));
    }
}

enum WeekDays{
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    //this is needed to support custom string values
    private final String value;

    //this is needed to support custom string values
    private WeekDays(String value){
        this.value = value;
    }

    // we can create new method to get text value, but it's good practice to override toString
    @Override
    public String toString(){
        return value;
    }

    // this method to get enum value from custom string value
    public static WeekDays fromValue(String str){
        for(WeekDays value: WeekDays.values()){
            if(value.toString().equals(str)){
                return value;
            }
        }
        throw new IllegalArgumentException("No enum value " + str);
    }
}
```
```
WeekDays.SATURDAY => Saturday
WeekDays.SATURDAY.name() => SATURDAY
WeekDays.SATURDAY.ordinal() => 0
WeekDays.values() => [Saturday, Sunday]
WeekDays.valueOf(SATURDAY) => Saturday
WeekDays.fromValue(Saturday) => Saturday
```
**Pay attention that `valueOf` expects enum constant, and if passed string (like Saturday) IllegalArgumentException would be thrown. That's why we have implemented method `fromValue` to search by string value.
`values()` - returns array of keys, not list or array of values. But if we override `toString` then when you call `Arrays.toString(WeekDays.values())` you will get array of values, cause you get array of keys, and then they all transform to string.

Another important issue, is that `enum` is a static nested class. So it can't be inside inner class or be declared as local variable. But it was changed in java 16 as part of `JEP-395`
```java
public class App {
    enum My{}
    static class A{
        enum My{}
    }
    class Person{
        enum My{} // compiler error (inner classes can't have static declaration)
    }
    public static void main(String[] args){
        class MyClass{};
        enum MyEnum{}; // compile error (enum must not be local)
    }
}
```
**Enum can't be extended from other classes (cause all enums are implicitly extended from `java.lang.Enum`, yet it can implement interfaces). Class can't be extended from enum (cause all enums are implicitly final).

Unlike a regular class in enum's constructor you can't access non-final static fields. This is because `enum` constructor called before all `static` fields have been initialized, so to avoid partial initialization we have such rule.
```java
enum Days{
    SAT("Sat"),
    SUN("Sun");
    String s1;
    final String s2 = "";
    static String s3;
    final static String s4 ="";
    Days(String s){
        String str = s1 + s2 + s3 + s4; // s3 will cause compilation error
    }
    
    public void m1(String s){
        String str = s1 + s2 + s3 + s4;
    }
    public static void m2(String s){
        String str = s3 + s4;
    }
}
```

In enum declaration constants should go first. 
```java
enum Days{
    String s1; // won't compile. Enum constants should be first
    
    SAT,
    SUN;
    
    String s;
}
```
Yet this code is fine. It's normal enum with empty declaration. The ; indicates the end of the enum identifiers list. Apparently you can have an empty enum list, but you must have one.
```java
enum Days{
    ;
    String s1;
}
```

Enum constants can have multiple values, but all should be declared in constructor
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Days.values()));
    }
}
enum Days{
    SAT(6, "Sat"){
        @Override
        public String toString(){ // overriding parent toString method
            return n + ":" + value; 
        }
    },
    SUN(7, "Sun");
    int n;
    String value;
    Days(int n, String value){
        this.n = n;
        this.value = value;
    }
    @Override
    public String toString(){
        return value + ":" + n;
    }
}
```
```
[6:Sat, Sun:7]
```

###### Exceptions
If parent exception goes before child, the `catch` block with child exception won’t compile with the error: `exception com.java.test.MyException has already been caught`
```java
public class App{
    public static void main(String[] args) {
        try{
            throw new MyException();
        } catch (RuntimeException ex){
            System.out.println("RuntimeException");
        } catch (MyException ex){ // won't compile since it's already been caught by it's parent
            System.out.println("A_Exception");
        } finally {
            System.out.println("finally");
        }
    }
}
class MyException extends RuntimeException{}
```

If we throw exception in `finally` block it will swallow(overwrite) all other exceptions. Here the first exception is handled, and we go into catch, there we throw another exception, but then we go into `finally` where we throw third. The result is only third would be shown.
```java
public class App{
  public static void main(String[] args) {
    try{
      System.out.println("try");
      throw new RuntimeException("1");
    } catch (RuntimeException ex){
      System.out.println("catch");
      throw new RuntimeException("2");
    } finally {
      System.out.println("finally");
      throw new RuntimeException("3");
    }
  }
}
```
```
try
catch
finally
Exception in thread "main" java.lang.RuntimeException: 3
	at com.java.test.App.main(App.java:13)
```

If you have checked exception you can’t catch them until they are clearly thrown from the method itself
```java
public class App {
    public static void main(String[] args) {
        try{
            print();
        } catch (MyException ex){ // won't compile since print will never explicitly throw such an exception
            System.out.println(ex);
        }
    }
    private static void print(){}
}
class MyException extends Exception{}
```
This can be fixed by throwing exception from the method itself. It just declares that this method may throw checked exception `private static void print() throws MyException{}` - so you don't need to actually throw `MyException` inside this function.
This only true to checked exception - because we are forced to catch them, for unchecked (runtime) you can catch them.

You can catch any type of exception: unchecked(RuntimeException), checked(Exception) and errors(Error). So technically you can handle any of `Throwable` subclasses. But it’s better never to catch Errors, cause of some you will not be able to recover
```java
public class App {
  public static void main(String[] args) {
    try {
      new A();
    } catch (Throwable ex){
      Throwable cause = ex;
      while (cause != null) {
        System.out.println("ex=" + cause);
        cause = cause.getCause();
      }
    } finally {
      System.out.println("done");
    }
  }
}
class A{
  static {
    String[] arr = new String[0];
    System.out.println(arr[0]); // throws java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
  }
}
```
```
ex=java.lang.ExceptionInInitializerError
ex=java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
done
```

Exceptions thrown inside `catch` block can’t be handled by next `catch` block
```java
public class App {
    public static void main(String[] args) {
        try {
            System.out.println(1);
            throw new MyException();
        } catch (MyException ex) {
            System.out.println(2);
            throw new RuntimeException();
        } catch (RuntimeException ex) {
            System.out.println(3);
            throw new RuntimeException();
        } finally {
            System.out.println("done");
        }
    }
}
class MyException extends RuntimeException{}
```
```
1
2
done
Exception in thread "main" java.lang.RuntimeException
	at com.java.test.App.main(App.java:15)
```
As you can see when we throw `throw new RuntimeException();` it won’t be caught by the next catch, so this code `System.out.println(3);` will never be executed. Yet `finally` would be executed right before throwing exception.

When you overriding method that throws checked exception you are allowed:
* not to throw any exception
* throw any unchecked exception or error, but not `Throwable` - because it's parent for checked exception class `Exception`
* throw itself or any child of itself
```java
class MyException extends Exception {}
interface Bird {
    void fly() throws MyException;
}
class Egret1 implements Bird {
    public void fly(){}
}
class Egret2 implements Bird {
    public void fly() throws RuntimeException{}
}
class Egret3 implements Bird {
    public void fly() throws Error{}
}
class Egret4 implements Bird {
    //  Error:(29, 17) java: fly() in com.java.test.Egret4 cannot implement fly() in com.java.test.Bird
    //  overridden method does not throw java.io.IOException
    public void fly() throws IOException{}
}
```
Here `IOException` is not child of `MyException`, that's why you get compiler error.

Multi-catch block. If you want to catch multiple exceptions there are 2 cases. Either catch common parent or `Exception`, or even `Throwable`. But the downside, is that it would catch all exceptions. Or write multiple catch statement - which is redundant. So the solution is to use one `catch` with multiple exceptions in there.
```java
public class App {
    public static void main(String[] args) {
        try{
            
        } catch (Exception1 | Exception2 | Exception3 ex){
            
        }
    }
}
class Exception1 extends RuntimeException{}
class Exception2 extends RuntimeException{}
class Exception3 extends RuntimeException{}
```
Beware that exceptions should be unrelated, if you try to declare child and parent, it won’t compile
```java
public class App {
  public static void main(String[] args) {
    try{
    } catch (UncheckedException | RuntimeException ex){
    }
  }
}
class UncheckedException extends RuntimeException{}
```
You get this compilation error:
```
Alternatives in a multi-catch statement cannot be related by subclassing
  Alternative com.java.test.UncheckedException is a subclass of alternative java.lang.RuntimeException
```
  
If you have multiple catch you can't reassign ex, because the type is the OR of several exceptions
```java
public class App{
  public static void main(String[] args){
    try{

    } catch (Ex1 | Ex2 ex){
      ex = new IllegalArgumentException(); // won't compile cause ex of type Ex1 | Ex2, and they don't have any common child
    }
  }
}

class Ex1 extends RuntimeException{}
class Ex2 extends RuntimeException{}
```

For checked only (not for `RuntimeException`) exception when overriding method there is a rule:
* any method - only more specific exception
* constructor - only more generic exception
As you see `B1.method` - compile error, cause we try to throw more generic (and we can throw only less - children and exception itself), but for C1.constructor - compile error, cause we can only throw less generic. The reason is for method we have to use liskov principle and to throw less generic, but construct make call to super, so here we can call only more generic. So with method we go-down, but with constructor we call-up.
```java
class MyException extends RuntimeException{}
class MySecondException extends MyException{}
class A{
    public A() throws MyException{}
    public void method() throws MyException{}
}
class B extends A{
    public B() throws RuntimeException{}
    @Override
    public void method() throws RuntimeException {}
}
class C extends A{
    public C() throws MySecondException{}
    @Override
    public void method() throws MySecondException {}
}
class MyCheckedException extends Exception{}
class MySecondCheckedException extends MyCheckedException{}
class A1{
    public A1() throws MyCheckedException{}
    public void method() throws MyCheckedException{}
}
class B1 extends A1{
    public B1() throws Exception{}
    @Override
    public void method() throws Exception {} // Error:(43, 17) java: method() in com.java.test.B1 cannot override method() in com.java.test.A1; overridden method does not throw java.lang.Exception
}
class C1 extends A1{
    public C1() throws MySecondCheckedException{} // Error:(46, 48) java: unreported exception com.java.test.MyCheckedException; must be caught or declared to be thrown
    @Override
    public void method() throws MySecondCheckedException {}
}

public class App {
    public static void main(String[] args) throws Exception {
        A1 b = new B1();
        doStuff(b);
    }
    public static void doStuff(A1 a){
        try{
            a.method();
        } catch (MyCheckedException ex) {
            System.out.println(ex);
        }
    }
}
```
As you see we catch `MyCheckedException`, so if child throws it parent, the catch would be broken, that’s why child can throw only more specific (children) exceptions. For constructors, it’s the other way around.
```java
try{
    new A1();
} catch (MyCheckedException ex) {
    System.out.println(ex);
}
```
We can catch `MyCheckedException` itself, it’s parent Exception, but not child `MySecondCheckedException`. And this is the exact reason, why when we override constructor we can throw only exception itself or more generic, cause when we create object, it will invoke its parent that throws more specific exception.

Constructor more generic is easily explained when you look into below code: When you create `new B()`, inside first constructor of `A` is called, so that's why `B` constructor should be either identical or wider. Otherwise it's illogical. But methods called in inverted way, like up-down, so for method only less-generic exception is allowed.
```java
class A{
    public A(){}
}
class B extends A{
    public B(){
        super();
    }
}
```

When overriding constructor you can also throw from child any other unrelated checked & unchecked exceptions, but for method - only checked
```java
class A{
    public A() throws IOException{}
    public void getIt() throws IOException{}
}
class B extends A{
    public B() throws MyException, Exception{}
    @Override
    public void getIt() throws MyException, FileNotFoundException {} //won't compile
}

class MyException extends Exception{}
```

Calling overridden method with exception
```java
public class App {
    public static void main(String[] args){
        A b1 = new B();
        b1.getValue(); //compile error, unchecked exception

        B b2 = new B();
        b2.getValue();
    }
}
class A{
    public void getValue() throws Exception{}
}
class B extends A{
    @Override
    public void getValue(){}
}
```
*Although we override method without exception, and when we call, we call it from B (dynamic binding - polymorphism), it works as static binding during compilation, and compiler thinks that we invoke `A.getValue`, and since it throw exception, compile force you to catch this exception.

We can `throw null` is just the same as `throw new NullPointerException()`.
```java
class App {
    public static void main(String[] args) {
        try{
            RuntimeException ex = null;
            throw ex;
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
```
```
java.lang.NullPointerException
```

Try with resources guarantees that resources would be closed. In order to work with try-with-resource your class should implement either `Closeable` or `AutoCloseable`. The Java 7 team wanted a mechanism to label objects as be auto-closeable for the "try with resources" construct. Unfortunately the API spec for the Closeable.close() method is too strict. It requires the close() method to be idempotent(if you call it twice result should be the same) but this is not necessary in the "try with resources" use-case. So they introduced the `AutoClosable` interface with a less restrictive close() semantic ... and retro-fitted `Closeable` as a subtype of `AutoCloseable`.                                                        
When variable declared inside try with resource it becomes final.
```java
import java.io.Closeable;

public class App {
  public static void main(String[] args) throws Exception {
    Resource r1 = new Resource();
    try(r1){}

    Resource r2;
    try(r2 = new Resource()){} // won't compile, cause r2 is not effectively final

    try(new Resource()){} // won't compile cause this declaration doesn't create final variable

    try(Resource r3 = new Resource()){
      r3 = new Resource(); // won't compile, r3 - final
    }

    Resource r4 = new Resource();
    try(r4){} // won't compile, since r4 is not effectively final
    r4 = new Resource();
  }
}

class Resource implements Closeable {
  public void close(){
    System.out.println("closed");
  }
}
```

Old-way resource handling (closing every resource in distinct try-catch block).
```java
public class App implements AutoCloseable {
    private String name;
    public App(String name) {
        this.name = name;
    }
    @Override
    public void close() {
        System.out.println("closing " + name);
        throw new RuntimeException("something went wrong with " + name);
    }
    public static void main(String[] args) {
        tryFinally();
        System.out.println();
        tryWithResources();
    }
    public static void tryWithResources() {
        try (
            App app1 = new App("app1");
            App app2 = new App("app2");
        ) {
            System.out.println("done tryWithResources");
        }
    }
    public static void tryFinally() {
        App app1 = null;
        App app2 = null;
        try {
            app1 = new App("app1");
            app2 = new App("app2");
            System.out.println("done tryFinally");
        } finally {
            if (app1 != null) {
                try {
                    app1.close();
                } catch (RuntimeException ex) {
                    System.out.println(ex);
                }
            }
            if (app2 != null) {
                try {
                    app2.close();
                } catch (RuntimeException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
```
```
done tryFinally
closing app1
java.lang.RuntimeException: something went wrong with app1
closing app2
java.lang.RuntimeException: something went wrong with app2

done tryWithResources
closing app2
closing app1
Exception in thread "main" java.lang.RuntimeException: something went wrong with app2
	at com.java.test.App.close(App.java:16)
	at com.java.test.App.tryWithResources(App.java:32)
	at com.java.test.App.main(App.java:22)
	Suppressed: java.lang.RuntimeException: something went wrong with app1
		at com.java.test.App.close(App.java:16)
		at com.java.test.App.tryWithResources(App.java:27)
		... 1 more
```
In the old way, we have to write finally and close resources manually, moreover we had to close every resource in separate try-catch to ensure, that if one close throw exception, another would be executed. With try-with-resources it’s way simpler. In order to be compiled in try-with-resources, class should implement `AutoClosable` or  `Closeable` interface. The diff is that auto - is new one, and all new classes better to start with it. `Closable` is done to work for better compatibility with pre java7 code. Pay attention, that by default try-with-resources closes resources from bottom-to-up and then execute finally if this block is present. So basically closing happens right after try, before any `catch` or `finally` in case they are present.

Suppressed exception - exception that is thrown in try-with-resources. If both try and close throws exception, exception from try would be thrown with suppressed exception from close.
```java
import java.util.Arrays;

public class App {
    public static void main(String[] args){
        try{
            run();
        } catch (Exception ex){
            System.out.println("ERR: " + ex);
            System.out.println("suppressed: " + Arrays.toString(ex.getSuppressed()));
        }
    }
    public static void run() throws Exception{
        Connection c = new Connection();
        try(MyResource r = new MyResource()){
            c.getConnection();
        }
    }
}

class MyResource implements AutoCloseable{
    public void close() throws CloseResourceException{
        System.out.println("closing...");
        throw new CloseResourceException();
    }
}

class Connection{
    public void getConnection() throws GetConnectionException{
        System.out.println("getting connection...");
        throw new GetConnectionException();
    }
}
class CloseResourceException extends Exception{}
class GetConnectionException extends Exception{}
```
```
getting connection...
closing...
ERR: com.java.test.GetConnectionException
suppressed: [com.java.test.CloseResourceException]
```
Although close method throws CloseResourceException after getConnection, this exception is not propagated, but instead appends as suppressed exception. All suppressed exception are stored in `ex.getSuppressed()` - which returns a `Throwable[]`.
**Pay attention that if we throw exception in finally, it will overwrite getConnection exception.
Resource are closed before catch/finally
```java
public class App implements AutoCloseable{
    public static void main(String[] args) {
        try(App app = new App()){
            System.out.println("start");
            throw new RuntimeException();
        } catch (RuntimeException ex){
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");
        }
    }
    @Override
    public void close(){
        System.out.println("close");
    }
}
```
```
start
close
catch block
finally block
```

Resource closing happens before catch or finally
```java
class App {
    public static void main(String[] args) {
        try(MyResource resource = new MyResource()){
            resource.run();
        } catch(Exception ex){
            System.out.println("catch: " + ex);
        } finally{
            System.out.println("finally");
        }
    }
}

class MyResource implements Closeable{
    public MyResource(){
        System.out.println("MyResource created");
    }
    public void run(){
        System.out.println("run");
        throw new RuntimeException("xxx");
    }
    public void close() throws IOException {
        System.out.println("MyResource closed");
    }
}
```
```
MyResource created
run
MyResource closed
catch: java.lang.RuntimeException: xxx
finally
```

**If we didn't call `run` method, and no exception in try block, so only resources would be closed, and after finally would run
```
MyResource created
MyResource closed
finally
```

###### Nested Types
Classes, interfaces, enums, can be declared inside classes and have modifiers.
Nested classes can be of 3 types:
* member class - class defined as member of parent as opposite to local and anonymous (can be static & inner):
  * static nested classes (called just static classes) - those declared with `static` keyword
  * instance nested classes (called just inner classes) - those declared without `static` keyword
    * interface/enum/record can never be called "inner class" because they always implicitly static, but anonymous is always inner class
* local class - class defined within method or initializer block, can't have access modifier, be static, be sealed
  * yet local interface/enum/record are always implicitly static
* anonymous class - class without a name that extends other class or implement interface

nested type:
* nested class by default is inner class, but we can add `static` keyword to make it static class
* nested interface/enum/record always static
```java
public class App {
    public static void main(String[] args) {
        Outer.Inner inner = new Outer().new Inner();
        Outer.R rec = new Outer.R(30);
        Outer.E e = Outer.E.A;
    }
}

class Outer {
    class Inner {}
    interface I {} // implicitly static
    enum E {A} // implicitly static
    record R(int age) {} // implicitly static
}
```


By keeping the inner class within the outer class, you hide the inner class from outside code, maintaining better encapsulation. It allows you to group classes that should only be used within the context of the outer class. Because they are tied to an instance of the outer class, non-static inner classes can increase memory consumption, as they hold references to their outer class instances.
Why Inner class doesn’t have any static declarations: The reason why inner classes cannot have static members is because static members are usually instantiated when the program starts. However, an inner class depends on having an instance of its enclosing class in order to create it and then access its members. So the decision to not have static members for an inner class makes sense due to this dependency. The static member cannot be accessed until something has been created i.e. the enclosing class instance, so why have it?

Correct declaration of member and static classes:
```java
public class App {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner1 = outer.new Inner();
        Outer.StaticInner inner2 = new Outer.StaticInner();

        // invalid declarations: not compiled
        Outer.Inner inner3 = new outer.Inner(); // package outer does not exist
        Outer.StaticInner inner4 = new outer.StaticInner(); // package outer does not exist
        Outer.StaticInner inner5 = outer. new StaticInner(); // qualified new of static class
    }
}

class Outer {
    class Inner {}
    static class StaticInner {}
}
```

By default, java expects every class to have its own file and be declared `public` there. But you can declare many classes in single file (but only one that matches filename can be public). When you compile file with public class, and it includes inner classes, java compile them into separate files.
```java
public class App {
    public class A {}
    private class B {}
    protected class C {}
    class D {}
    public static class A1 {}
    private static class B1 {}
    protected static class C1 {}
    static class D1 {}
    public static void main(String[] args) {
    }
}
```
```shell script
# run this command
javac src/com/java/app/App.java && ls src/com/java/app/ | sort

App$A1.class
App$A.class
App$B1.class
App$B.class
App$C1.class
App$C.class
App.class
App$D1.class
App$D.class
App.java
```
As you see for every class it creates separate file with template like `{MainClassName}${InnerClassName}.class`

Static classes are not related with parent classes. You can create them only using parent class name with new keyword. They are just like separate classes that can access static members from parent classes. Inner classes on the other hand are associated with instances of outer class. So we can't create them without instance of outer class. Pay attention, that inside class we can instantiate static & inner classes the same way `new Outer.Inner()`
```java
public class App
{
    public static void main(String[] args)
    {
        Outer outer = new Outer();
        Outer.B b1 = new Outer.B();
        Outer.B b2 = new Outer().new B(); // won't compile
        Outer.B b3 = outer.B(); // won't compile

        Outer.A a1 = new Outer().new A();
        Outer.A a2 = outer.new A();
        Outer.A a3 = new Outer.A(); // won't compile
    }
}

class Outer{
    class A{}
    static class B{}

    public void m1()
    {
        // following 3 lines are equivalent, all compiles because we have intrinsic instance of Outer as this
        A a1 = new A();
        A a2 = new Outer.A();
        A a3 = new Outer().new A();

        B b1 = new B();
        B b2 = new Outer.B();
        B b3 = new Outer().new B(); // wont' compile
    }
    public static void m2()
    {
        A a1 = new A();   // won't compile, need instance of Outer
        A a2 = new Outer.A();  // won't compile, need instance of Outer
        A a3 = new Outer().new A();

        B b1 = new B();
        B b2 = new Outer.B();
        B b3 = new Outer().new B(); // wont' compile
    }
}
```

Nested non-static class can’t have static members, except `final static` methods. (this rule is relevant to up to java 16, because in java 16 `Record` was added and this rule was relaxed. Started from java 16 with `JEP-395` you can have static members for non-static class. Now member class can have `main` method too.
Static inner classes can't access parent instance members. Since inner class not `static`, it belongs to instance of outer. Since we can have many instances of outer class and each has its own instance of inner, they can't have static members. Inner class can extend other classes and implement interfaces.
```java
class A {
    private int i1=1;
    private static int i2=1;
    static class InnerStatic{
        public void print(){
            System.out.println(i1); //won't compile 
            System.out.println(i2);
        }
    }
    class Inner {
        public void print(){
            System.out.println(i1);
            System.out.println(i2);
        }
        public static int x = 1; // won't compile, would compile from java16
        public final static int y = 2;
        public static int getValue() { // won't compile, would compile from java16
            return 2;
        }
        public final static String getString() { // won't compile, would compile from java16
            return null;
        }
    }
}
```

Default constructor access modifies. By default, if we not explicitly add any constructor, java add default no-arg constructor with the same modifier as class itself. You can check it with `javap` disassembler tool.
```java
public class Outer{
    // public default no-arg constructor
    class A{} // package-private no-arg constructor
    protected class B{} // protected no-arg constructor
    private class C{} // private no-arg constructor
}
```

Nested Inner classes can access parent class variables, including private. (static classes only static variables, inner classes both static & instance).
```java
class App {
    public static void main(String[] args) {
        A.B b = (new A()).new B();
        b.print();
        A.C c = new A.C();
        c.print();
    }
}
class A{
    private int i1 = 1;
    private static int i2 = 2;
    class B{
        public void print(){
            System.out.println(A.i2 + " " + A.this.i1);
        }
    }
    static class C{
        public void print(){
            System.out.println(A.i2);
        }
    }
}
```
```
2 1
2
```

For member class: 
* `this` refers to the class itself, not to the parent
* `Outer.this` - should be used if you want to access `this` of parent class 
```java
class Outer {
  private int i;
  class Inner {
    private int i;
    public void work() {
      System.out.println("local i => " + i);
      System.out.println("local i => " + this.i);
      System.out.println("parent i => " + Outer.this.i);
    }
  }
}
```

By default, we can call static classes as `Outer.Inner`, but if we import them, as static, we can call them directly
```java
import static com.java.test.Outer.Inner;

public class App{
    public static void main(String[] args) {
        Outer.Inner b1 = new Outer.Inner();
        Inner b2 = new Inner(); // if you remove import static, this won't compile
    }
}

class Outer{
    public static class Inner{}
}
```

Method class:
* you can have a class defined inside the method
* it can't be static, cause there is no `static` keyword for method scope
* such class can access `effectively final` variables from the method
```java
class Outer {
    public void print() {
        class Inner {
            void print() {}
        }
        Inner inner = new Inner();
        inner.print();
    }
}
```

Method class and lambda can access only `effectively final` variables:
* this limitation is related to scope and lifetime and made on purpose
* lifetime of method variables is method execution, and when method is returned, all variables are cleared from the stack. But lambda/class may outlive the method, they may be returned and continue to live after method returned. In this case having non-final method variables may be confusing. Moreover, lambda/class create new scope, and copy values. So if we pass non-final method variables to lambda/class, it's confusing what should happen with them, if we are changing them inside lambda/class. For this reason to avoid confusion there is hard requirement to use either final or effectively final variables inside lambda/class

###### Anonymous classes
Anonymous classes - can be created out of interface/(abstract)class by implementing class body on the fly. They can't:
* extends other classes or implement interfaces
* add constructors
* have static fields (yet they can have final static fields)
* have static methods (both final & non-final)

You can add private & public members, but only public members declared in original type would be accessible.
```java
public class App {
    public static void main(String[] args) {
        Person p = new Person() {
            private String name;
            @Override
            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String getName() {
                return name;
            }
            static int i = 1; // won't compile
            final static int i2 = 2;
            public static void m1(){} // won't compile
            public final static void m2(){} // won't compile
            public void m3(){} //fine
        };
        p.setName("John");
        System.out.println(p.getName());
    }
}

interface Person{
    void setName(String name);
    String getName();
}
```
```
John
```

Anonymous class inside instance method can get access to parent this using `{ParentClass}.this`
```java
public class App {
    public static void main(String[] args) {
        Person p = new Person("Jack");
        p.print();
    }
}

interface Printer{
    void print();
}
class Person{
    private String name;
    public Person(String name){
        this.name = name;
    }
    public void print(){
        Printer printer = new Printer() {
            private String name = "MyPrinter";
            @Override
            public void print() {
                System.out.println(this.name + " " + Person.this.name);
            }
        };
        printer.print();
    }
}
```
```
MyPrinter Jack
```

Anonymous class can be static if it declared from class context
```java
public class App {
    public static void main(String[] args) {
        Person.p.print();
    }
}

interface Printer{
    void print();
}
class Person{
    private String name;
    public Person(String name){
        this.name = name;
    }
    static Printer p = new Printer() {
        @Override
        public void print() {
            System.out.println("printing...");
        }
    };
}
```
```
printing...
```


#### Date and Time
###### Old date api (java.util.Date & java.util.Calendar)
Before 2014 (introduction of `java.time`) java used these 2 classes `Date/Calendar` to work with dates. But it has a lot of problems:
* no time zone
* no formatting
* month starts from 0
* classes are mutable
* `Date` represents DateTime, but `java.sql.Date` represents single day

```java
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App{
    public static void main(String[] args) {
        Date currentDate = new Date();
        System.out.println("currentDate => " + currentDate);
        Date dateFromNanoSec = new Date(1234567899123L);
        System.out.println("dateFromNanoSec => "+ dateFromNanoSec);

        // constructor is protected, so create by calling static getInstance
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar => "+ calendar);

        // since GregorianCalendar extends Calendar, getInstance returns Calendar object, that's why we use constructor
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        System.out.println("gregorianCalendar => "+ gregorianCalendar);

        /**
         * converting to new api
         */

        Date date = new Date();
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        System.out.println("localDate => "+ localDate);
        // second way to convert ot LocalDate
        LocalDate localDate2 = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
```

###### SQL date api (java.sql package)
There are 3 types in `java.sql` package
`Date` => corresponds to SQL DATE
`Time` => corresponds to SQL_TIME
`Timestamp` => corresponds to SQL_TIMESTAMP
You can convert easily between them and `java.time` packages using `.toLocal...` method or `.valueOf` method.
```java
import java.sql.*;
import java.time.*;

public class App {
    public static void main(String[] args) {
        long ms = Instant.now().toEpochMilli();
        Date sqlDate = new Date(ms);
        Time sqlTime = new Time(ms);
        Timestamp sqlTimestamp = new Timestamp(ms);

        System.out.println(sqlDate + " " + sqlTime + " " + sqlTimestamp);

        LocalDate date = sqlDate.toLocalDate();
        LocalTime time = sqlTime.toLocalTime();
        LocalDateTime dateTime = sqlTimestamp.toLocalDateTime();

        System.out.println(date + " " + time + " " + dateTime);

        System.out.println(Date.valueOf(LocalDate.now()));
        System.out.println(Time.valueOf(LocalTime.now()));
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }
}
```
```
2020-02-04 19:22:20 2020-02-04 19:22:20.287
2020-02-04 19:22:20 2020-02-04T19:22:20.287
2020-02-04
19:22:20
2020-02-04 19:22:20.342169
```

###### New date api (java.time package)
`LocalDate`, `LocalTime`, `LocalDateTime` => all have private constructor to force you to use methods like `.of`. They also immutable like String, so when make operation on them (plus, minus), make sure to reassign them back.
`LocalDate.of` - has 2 version, one take month as numbers 1-12, another as `java.time.Month` enum value
```java
import java.time.LocalDate;
import java.time.Month;

public class App {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2018, 1, 1);
        LocalDate date2 = LocalDate.of(2018, Month.JANUARY, 1);
        System.out.println("date => " + date);
        date = date.plusYears(1);
        date = date.plusMonths(2);
        date = date.plusDays(3);
        System.out.println("date => " + date);
    }
}
```
```
date => 2018-01-01
date => 2019-03-04
```

You can iterate over dates like this with interval of 1 month
```java
import java.time.LocalDate;
import java.time.Period;

public class App {
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2018, 1, 1);
        LocalDate end = start.plusYears(1);
        LocalDate upTo = start;
        while (upTo.isBefore(end)){
            System.out.println(upTo);
            upTo = upTo.plusMonths(1);
        }
        
        // the same with period
        upTo = start;
        Period period = Period.ofMonths(1);
        while (upTo.isBefore(end)){
            System.out.println(upTo);
            upTo = upTo.plus(period);
        }
    }
}
```
```
2018-01-01
2018-02-01
2018-03-01
2018-04-01
2018-05-01
2018-06-01
2018-07-01
2018-08-01
2018-09-01
2018-10-01
2018-11-01
2018-12-01
```

Convert to time (in seconds) since linux epoch
```java
import java.time.*;

public class App {
    public static void main(String[] args) {
        System.out.println("days since 01-01-1970 UTC => " + LocalDate.now().toEpochDay());
        LocalDateTime now = LocalDateTime.now();
        ZoneOffset zoneOffSet = ZoneId.systemDefault().getRules().getOffset(now);
        System.out.println("current time zone => " + zoneOffSet);
        System.out.println("seconds since 01-01-1970 UTC => " + now.toEpochSecond(ZoneOffset.UTC));
        System.out.println("seconds since 01-01-1970 UTC [OffsetDateTime.now().toEpochSecond()] => " + OffsetDateTime.now().toEpochSecond());

    }
}
```
```
days since 01-01-1970 UTC => 18331
current time zone => +08:00
seconds since 01-01-1970 UTC => 1583843813
seconds since 01-01-1970 UTC [OffsetDateTime.now().toEpochSecond()] => 1583815013
```

###### Period and Duration
For time intervals we have 2 classes:
* `Period` - used for days, months, years
* `Duration` - for days, hours, minutes and seconds. Although you can set duration for 365 days - so a year, it’s not the best practise. Also you can easily set a duration for 1 year, 2 days and 3 seconds, but it has no constructor for this. You should convert to the least measure and pass it to duration.
`Duration.toString` always return `PT` (stands for Period of Time) and then duration itself.
```java
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class App{
    public static void main(String[] args) {
        Period period = Period.of(1, 2, 3);
        System.out.println("period => " + period);
        long durationInterval = 1 * 3600 + 2 * 60 + 3;
        Duration duration = Duration.ofSeconds(durationInterval);
        System.out.println("duration => " + duration);
        System.out.println("duration with ChronoUnit => " + Duration.of(durationInterval, ChronoUnit.SECONDS));
    }
}
```
```
period => P1Y2M3D
duration => PT1H2M3S
duration with ChronoUnit => PT1H2M3S
```

We have several types of duration:
```java
import java.time.Duration;

public class App{
    public static void main(String[] args) {
        System.out.println(Duration.ofDays(1));
        System.out.println(Duration.ofHours(1));
        System.out.println(Duration.ofMinutes(1));
        System.out.println(Duration.ofSeconds(1));
        System.out.println(Duration.ofMillis(1));
        System.out.println(Duration.ofNanos(1));
    }
}
```
```
PT24H
PT1H
PT1M
PT1S
PT0.001S
PT0.000000001S
```

Period works only with dates, duration with time.
```
            LocalDate    LocalTime   LocalDateTime    ZonedDateTime
Period          +           -             -                 -
Duration        -           +             +                 +
```

Calculate difference between two localtime and compare it to n seconds
```java
import java.time.Duration;
import java.time.LocalTime;

public class App{
    public static void main(String[] args) {
        int sec = 2;
        LocalTime start = LocalTime.now();
        sleep(sec + 1);
        LocalTime end = LocalTime.now();
        if(Duration.between(start, end).compareTo(Duration.ofSeconds(sec)) > 0){
            System.out.println("more than " + sec + " seconds");
        }
    }
    private static void sleep(long sec){
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
```

We can easily convert between `TimeUnit` and `ChronoUnit`. Since `TimeUnit` only includes up to hour, but `ChronoUnit` is up to forever, if there is no suitable conversion `IllegalArgumentException` is thrown.
```java
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class App{
    public static void main(String[] args) {
        ChronoUnit chronoUnit = ChronoUnit.SECONDS;
        TimeUnit timeUnit = TimeUnit.of(chronoUnit);
        ChronoUnit fromTimeUnit = timeUnit.toChronoUnit();
        System.out.println("timeUnit => " + timeUnit);
        System.out.println("fromTimeUnit => " + fromTimeUnit);

        System.out.println(TimeUnit.of(ChronoUnit.MONTHS));
    }
}
```
```
timeUnit => SECONDS
fromTimeUnit => Seconds
Exception in thread "main" java.lang.IllegalArgumentException: No TimeUnit equivalent for Months
```

###### Timezone and DST
`Instant` - specific moment in time in UTC. UTC(Coordinated universal time) and GMT(Greenwhich mean time) practically the same thing, the difference is how they calculate seconds, gmt - using solar time, utc - atomic clock.
```java
import java.time.Duration;
import java.time.Instant;

public class App{
    public static void main(String[] args) {
        Instant now = Instant.now();
        for(int i = 0; i < 1_000_000_000; i++){}
        Instant later = Instant.now();
        Duration duration = Duration.between(now, later);
        System.out.println("duration => " + duration);
    }
}
```
```
duration => PT0.004315S
```

We can easily convert `OffsetDateTime/ZonedDateTime` to `Instant`, but not way back, cause `Instant` is not timezone aware.
```java
import java.time.*;

public class App{
    public static void main(String[] args) {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zonedDateTime => " + zonedDateTime);
        System.out.println("zonedDateTime.toInstant => " + zonedDateTime.toInstant());
        System.out.println("offsetDateTime => " + offsetDateTime);
        System.out.println("offsetDateTime.toInstant => " + offsetDateTime.toInstant());
    }
}
```
```
zonedDateTime => 2020-03-10T19:08:25.597014+08:00[Asia/Hong_Kong]
zonedDateTime.toInstant => 2020-03-10T11:08:25.597014Z
offsetDateTime => 2020-03-10T19:08:25.596795+08:00
offsetDateTime.toInstant => 2020-03-10T11:08:25.596795Z
```
As you can see it the same time, but `Instant` is in UTC, while `ZonedDateTime` has a timezone.

DST(Daylight saving time) is a practice to move time by one hour forward in spring and 1 hour back in fall(november).
```java
import java.time.*;
import java.time.temporal.ChronoUnit;

public class App{
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time = LocalTime.of(1, 30);
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime1 = ZonedDateTime.of(date, time, zone);
        System.out.println("dateTime1 => " + dateTime1);
        ZonedDateTime dateTime2 = dateTime1.plusHours(1);
        System.out.println("dateTime2 => " + dateTime2);
        System.out.println("diff => " + ChronoUnit.HOURS.between(dateTime1, dateTime2));
        System.out.println("diff => " + Duration.between(dateTime1, dateTime2));
    }
}
```
```
dateTime1 => 2016-03-13T01:30-05:00[US/Eastern]
dateTime2 => 2016-03-13T03:30-04:00[US/Eastern]
diff => 1
diff => PT1H
```
On the 13th of March 2016, USA moved 1 hour forward, so after 1.59 it was 3.00 pm. So when you add 1 hour to 1.30, you don’t get 2.30 but 3.30. Also notice change in utc offset. Please note, that diff is still 1 hour.

Don't confuse:
* `Instant` - just store current datetime from UTC
* `OffsetDateTime` - `Instant` + UTC offset
* `ZonedDateTime` - `OffsetDateTime` + time zone
```java
import java.time.*;

public class App{
    public static void main(String[] args) {
        System.out.println("Instant =>        " + Instant.now());
        System.out.println("OffsetDateTime => " + OffsetDateTime.now());
        System.out.println("ZonedDateTime =>  " + ZonedDateTime.now());
    }
}
```
```
Instant =>        2019-11-18T08:37:56.893718Z
OffsetDateTime => 2019-11-18T16:37:56.937434+08:00
ZonedDateTime =>  2019-11-18T16:37:56.938073+08:00[Asia/Hong_Kong]
```
There is a big difference between timeOffset and timeZone. TimeOffset - is just time compare to UTC, like +8.00. TimeZone - is geographical time like `[Asia/Hong_Kong]`. TimeZone - is broader, cause it includes DST (day save time) + it’s political concept. Let’s say tomorrow government decide that now this timezone should have offset not +8, but +9. So in these terms timeZone is broader concept than timeOffset.
Java & DB time practice (we use mysql here as example):
* timestamp - the number of milliseconds since the `epoch` namely midnight, January 1, 1970 UTC
* you have 3 classes in java to map to SQL, under `java.sql` package 
    * Date - only dates
    * Time - only time
    * Timestamp - stores both date & time
* in mysql you have 3 main data types
    * date - store date only
    * datetime - store both date & time (end range `9999-12-31 23:59:59`)
    * timestamp - store both date & time as millisec (end range `2038-01-19 03:14:07`)
        * MySQL converts TIMESTAMP values from the current time zone to UTC for storage, and back from UTC to the current time zone for retrieval. (This does not occur for other types such as DATETIME)
        * If you store a TIMESTAMP value, and then change the time zone and retrieve the value, the retrieved value is different from the value you stored
        * pay attention that it display time in local timezone, that's why mysql uses conversion internally
* we have the following mapping (java => mysql)
    * java.sql.Date => date
    * java.sql.Timestamp => datetime/timestamp
* there is no native way to store timezone, so best practice assume to store it in another string column
    * best way to store timezone is IANA format (don't store as offset, cause it error prone)
* best way to store dates is to store
    * timezone as IANA string
    * store time as millisec in bigint
        * this is better, cause timestamp - number of millisec from jan 1970, would always be the same, tz no matter
        yet when you need to display exact time, depending upon your TZ you will see different time,
        so best approach just store time as timestamp, and it always utc by default
        * this would avoid any problems with `timestamp` field like https://stackoverflow.com/questions/71346404/mysql-timestamp-throwing-incorrect-datetime-value-on-one-specific-datetime
        where your server timezone is bermuda, and due to DST, they switch and some time is missing, and you insert missing time.
    * or store datetime + LocalDate, but for localdate whenever you call `now()`, make sure you call it with UTC timezone, so you always get UTC time
        * don't use `Instant` here, cause althougn `now()` returns UTC time by default, it also store timezone inside as Z letter, and you may have issues applying datetimeformat, if you don't want to store zone inside date
* LocalDateTime doesn't store timezone, internally it stores timestamp as seconds, but when displayed, it use current TZ to display time
    * since TZ is not present in LocalDateTime, there is no way to convert it to timestamp with millisec
    * yet you can add timezone, convert to Instant and get timestamp from there
    `LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();`
    * same true with opposite, if you have epochmillisec, then convert it into Instant, and then get LocalDateTime from it
    * this class useful if you have users in different timezones, then you can display for each his exact time by applying his TZ
```java
import java.sql.Timestamp;
import java.time.Instant;

public class App{
    public static void main(String[] args) {
        final long timestamp = System.currentTimeMillis();
        final Instant instant = Instant.now();
        System.out.println("timestamp       => " + timestamp);
        System.out.println("toEpochMilli    => " + instant.toEpochMilli());
        System.out.println("getEpochSecond  => " + instant.getEpochSecond());

        java.sql.Timestamp sqlDatetime = new Timestamp(timestamp);
        System.out.println("sqlDatetime     => " + sqlDatetime);
        System.out.println("instant         => " + Instant.ofEpochMilli(timestamp));
    }
}
```
```
timestamp       => 1674294464706
toEpochMilli    => 1674294464707
getEpochSecond  => 1674294464
sqlDatetime     => 2023-01-21 17:47:44.706
instant         => 2023-01-21T09:47:44.706Z
```

###### Formatting
Formatting: There are 3 classes to format date and 1 for numbers
`java.text.DateFormat extends Format` - private constructor
`java.text.NumberFormat extends Format` - class for number formatting including currency, private constructor
`java.text.SimpleDateFormat extends DateFormat` - extends `DateFormat`, 3 constructors (empty, pattern, pattern & locale)
`java.time.format.DateTimeFormatter` - java8 time library new class, private constructor use `.ofPattern`
```java
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class App {
    public static void main(String[] args) {
        Locale locale = Locale.FRENCH;
        Date date = new Date();
        String pattern = "yyyy-MMM-dd";
        LocalDate localDate = LocalDate.now();

        // the same as DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
        System.out.println("DateFormat.getInstance() => " + DateFormat.getInstance().format(date));
        System.out.println("DateFormat.getDateInstance() => " + DateFormat.getDateInstance().format(date));
        System.out.println("DateFormat.getDateTimeInstance() => " + DateFormat.getDateTimeInstance().format(date));
        // we can set int value from 0 to 3 to get different date format, default - medium - 2
        System.out.println("DateFormat.getDateInstance(DEFAULT) => " + DateFormat.getDateInstance(DateFormat.DEFAULT).format(date));
        System.out.println("DateFormat.getDateInstance(SHORT) => " + DateFormat.getDateInstance(DateFormat.SHORT).format(date));
        System.out.println("DateFormat.getDateInstance(MEDIUM) => " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(date));
        System.out.println("DateFormat.getDateInstance(LONG) => " + DateFormat.getDateInstance(DateFormat.LONG).format(date));
        System.out.println("DateFormat.getDateInstance(FULL) => " + DateFormat.getDateInstance(DateFormat.FULL).format(date));
        // we can pass second params as locale to getDate/Time/DateTimeInstance
        System.out.println("DateFormat.getDateInstance(DEFAULT) => " + DateFormat.getDateInstance(DateFormat.DEFAULT, locale).format(date));
        // we can also obtain time instance
        System.out.println("DateFormat.getTimeInstance() => " + DateFormat.getTimeInstance().format(date));
        System.out.println("DateFormat.getTimeInstance(FULL) => " + DateFormat.getTimeInstance(DateFormat.FULL).format(date));
        System.out.println("DateFormat.getTimeInstance(FULL) => " + DateFormat.getTimeInstance(DateFormat.FULL, locale).format(date));
        // we can pass 2 params (form 0 to 3) to indicate what kind of output do we want
        System.out.println("DateFormat.getDateTimeInstance(FULL, FULL) => " + DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(date));
        // we can pass locale as third param
        System.out.println("DateFormat.getDateTimeInstance(FULL, FULL) => " + DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale).format(date));

        System.out.println();
        // we can call empty constructor, default format and locale
        System.out.println("new SimpleDateFormat() => " + new SimpleDateFormat().format(date));
        // we can set pattern as first param
        System.out.println("new SimpleDateFormat("+pattern+") => " + new SimpleDateFormat(pattern).format(date));
        // we can set local as second param
        System.out.println("new SimpleDateFormat("+pattern+", "+locale+") => " + new SimpleDateFormat(pattern, locale).format(date));


        System.out.println();
        // new class accept either LocalDate, LocalTime, LocalDateTime
        System.out.println("DateTimeFormatter.ofPattern("+pattern+") => " + DateTimeFormatter.ofPattern(pattern).format(localDate));
        // we can set locale as second param
        System.out.println("DateTimeFormatter.ofPattern("+pattern+", "+locale+") => " + DateTimeFormatter.ofPattern(pattern, locale).format(localDate));


        System.out.println();
        System.out.println("NumberFormat.getInstance(locale) => " + NumberFormat.getInstance(locale).format(99.99));
        System.out.println("NumberFormat.getCurrencyInstance(locale) => " + NumberFormat.getCurrencyInstance(locale).format(99.1));
    }
}
```
```
DateFormat.getInstance() => 10/3/2020, 12:51 PM
DateFormat.getDateInstance() => 10 Mar 2020
DateFormat.getDateTimeInstance() => 10 Mar 2020, 12:51:09 PM
DateFormat.getDateInstance(DEFAULT) => 10 Mar 2020
DateFormat.getDateInstance(SHORT) => 10/3/2020
DateFormat.getDateInstance(MEDIUM) => 10 Mar 2020
DateFormat.getDateInstance(LONG) => 10 March 2020
DateFormat.getDateInstance(FULL) => Tuesday, 10 March 2020
DateFormat.getDateInstance(DEFAULT) => 10 mars 2020
DateFormat.getTimeInstance() => 12:51:09 PM
DateFormat.getTimeInstance(FULL) => 12:51:09 PM Hong Kong Standard Time
DateFormat.getTimeInstance(FULL) => 12:51:09 heure normale de Hong Kong
DateFormat.getDateTimeInstance(FULL, FULL) => Tuesday, 10 March 2020 at 12:51:09 PM Hong Kong Standard Time
DateFormat.getDateTimeInstance(FULL, FULL) => mardi 10 mars 2020 à 12:51:09 heure normale de Hong Kong

new SimpleDateFormat() => 10/3/2020, 12:51 PM
new SimpleDateFormat(yyyy-MMM-dd) => 2020-Mar-10
new SimpleDateFormat(yyyy-MMM-dd, fr) => 2020-mars-10

DateTimeFormatter.ofPattern(yyyy-MMM-dd) => 2020-Mar-10
DateTimeFormatter.ofPattern(yyyy-MMM-dd, fr) => 2020-mars-10

NumberFormat.getInstance(locale) => 99,99
NumberFormat.getCurrencyInstance(locale) => 99,10 ¤
```

All `.parse` (except for `DateTimeFormatter`) throw checked exception `ParseException`. There is also `.parseObject` method in `Format` class, that takes string and throws `ParseException`.
```java
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class App {
    public static void main(String[] args) {
        double d = 99.99;
        String formatted = NumberFormat.getInstance(Locale.FRENCH).format(d);
        System.out.println(formatted);
        try {
            // parse returns Number.
            double d2 = (Double)NumberFormat.getInstance().parse(formatted);
            System.out.println(d2);
        } catch (ParseException ex){
            System.out.println(ex);
        }
    }
}
```
```
99,99
java.lang.ClassCastException: class java.lang.Long cannot be cast to class java.lang.Double
```
Here we catch `ParseException`, but cause we use new `NumberFormat` instance with default locale (first time we were using French locale), we got `ClassCastException`.

[List](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) of most common patterns 
```
y/Y - year   (yy/YY-20 or yyyy/YYYY-2020)
q   - quarter of the year (q-1, qq-01, qqqq-1st quarter)
M/L - month  (M/L-2, MM/LL-02, MMM/LLL-Feb, MMMM/LLLL-February)
w/F - week of the year (w/F-7, ww-07)
W   - week of the month (W-3)
d   - day in month (d-9, dd-09)
e/c - localized day in week (e/c-6, ee-06, eee-Fri, eeee-Friday)
E   - day in week (E/EE/EEE - Fri, EEEE-Friday)
h/K - hour (0-12)  (h/K-5, hh/KK-05)
H/k - hour (0-23)  (H/k-17, HH/k-17)
a   - am/pm (a-PM)
m   - minute in hour (m-7, mm-07)
s   - seconds (s-9, ss-09)
S   - milliseconds (S-2, SS-23, SSS-235)
z   - time zone  text (z/zz/zzz-HKT, zzzz-Hong Kong Standard Time)
Z   - time offset (Z/ZZ/ZZZ-+0800, ZZZZ-GMT+08:00)
```

We can have arbitrary text inside pattern, we just need to enclose it in single quotes ''
```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("eeee 'is' d'th day of' MMMM yyyy");
        System.out.println(formatter.format(localDate));
    }
}
```
```
Friday is 14th day of February 2020
```

Both date and formatter have `format` method
```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App{
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.format(formatter));
        System.out.println(formatter.format(localDate));
    }
}
```
```
2019/11/18
2019/11/18
```


#### Generics
###### Type erasure
Type erasure - java by default remove types from generics and change it for object, and later make class casts like
```java
List<String> list = new ArrayList<String>();
list.add("Hi");
String x = list.get(0);

//is compiled into

List list = new ArrayList();
list.add("Hi");
String x = (String) list.get(0);
```

###### PECS (producer extends consumer super)
In a nutshell, three easy rules to remember PECS:
* use covariance `<? extends T>` wildcard if you need to retrieve object of type T from a collection (producer - returns values)
* use Contravariance `<? super T>` wildcard if you need to put objects of type T in a collection (consumer - add values)
* use Invariance/non-variance `T` if you need to satisfy both
Generic subtyping rule:
* This code won't compile `List<Number> numbers = new ArrayList<Integer>();` to avoid potential problem, when you can assign to `List<Number>` a list of integers, and then you would be able to add double, because `Double` is subtype of `Number`, but adding double to list of integers is wrong. So with generic special rules applied.
* Because of this rule, you can't put generic subtype into main type.
```java
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        consume(numbers);
        consume(integers); // compile error
    }
    
    static void consume(List<Number> list){
        
    }
}
```
* So if we need to pass sybtype generic into parent we have to use PECS rules. Now this code compiles well
```java
public class App {
    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        consume(numbers);
        consume(integers);
    }
    
    static void consume(List<? extends Number> list){
        
    }
}
```
* Using `super`
```java
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        consume(numbers, 1);
        consume(integers, 1);
    }

    static void consume(List<? super Integer> list, int n){
        list.add(n);
    }
}
```

```java
import java.util.*;

public class App {
    public static void main(String[] args){
        // we can get Object and B(and above), but can add only null
        List<? extends B> extendsB = new ArrayList<>();
        extendsB.add(null);
        extendsB.add(new B()); // won't compile
        B b = extendsB.get(0);
        A a = extendsB.get(0);
        Object obj = extendsB.get(0);

        // in unbounded generic we can get only Object, and put only null, cause List<?> is the same as List<? extends Object>
        List<?> unbounded = new ArrayList<>(); 
        Object obj = unbounded.get(0);
        unbounded.add(null);

        // we can get only object, but can add B(and below)
        List<? super B> superB = new ArrayList<>();
        B b2 = superB.get(0); // won't compile
        Object obj2 = superB.get(0);
        superB.add(new B());
        superB.add(new C());


        List<B> listB = new ArrayList<>();
        D d = new D();
        m1(listB, d);
        m2(listB, d); // won't compile
    }
    // here the problem that we can pass List<B> and any child of A (including those unrelated to B), m2 solves this problem
    private static void m1(List<? extends A> list , A a){}
    private static <T extends A> void m2(List<T> list , T a){}

    /**
     * Why to use List<? super A> instead of just List<A>
     * The reason is that in future you may want to pass list of higher objects
     */
    private static void copy(List<? extends A> from, List<? super A> to){
        for(A a: from){
            to.add(a);
        }
    }
}

class A{}
class B extends A{}
class D extends A{}
class C extends B{}

interface X{}

/**
 * We can inherent generics from multiple interfaces
 * A - can be class or interface, X (and so on) - can be only interfaces
 * So we can add everything that extends A and implements X. If A itself implements X, we can add just A and all it's children
 * Yet you can't use it like List<? extends A & X> - won't compile
 */
class Container<T extends A & X>{
    private List<T> list;
    public void add(T value){
        list.add(value);
    }
    public T get(int index){
        return list.get(index);
    }
}

/**
 * recursive generics
 * If we omit A1<T> and just write <T extends A1>, than we could write
 * class B1 extends A1<C1>{}
 * class C1 extends A1<B1>{};
 * 
 */
class A1<T extends A1<T>> implements Comparable<T>{
    @Override
    public int compareTo(T t) {
        return 0;
    }
}
class B1 extends A1<B1>{}
class C1 extends A1<C1>{}
```
Because a compile error: both methods have same erasure. This also won’t work in parent-child. Cause from compiler perspective it’s overloading, but from jvm it’s overriding, that’s why compiler won’t accept if one method in parent class and another in child.
The exception if overriding method has `List<anything>` but overridden just `List`.

Be careful with new generic variable. Here in method m2 we declare new generic variable, but name it the same as main one T. So inside this method T is different generic than outside.
```java
class A<T>{
    T value;
    public void m1(T value){
        this.value = value;
    }
    public <T> void m2(T value){
        this.value = value; // won't compile, cause T is redeclared generic
    }
}
```

Be careful. Since we create MyType without generic (raw) compiler stripe all generic from test method, and you can pass List<String> to List<Integer>.
If you add type during creation like `MyType<Object> type = new MyType();` you will get compile error. This example shows, that if you are using generic set some type at least <?>.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        MyType type = new MyType();
        List<String> list = new ArrayList<>();
        type.test(list);
    }
}

class MyType<T>{
    public void test(List<Integer> list){
        System.out.println("list => " + list);
    }
}
```
```
list => []
```

Pay attention when we extend or implement generic interface and don't set its generic type. As you see although MusicPlayer can take only Music (or it's children) as generic param, this generic doesn't go to Player. And since Player has no generic we can pass any object into `musicPlayer.play`. Also even though Both interfaces set generic boundaries, you can still declare both of them without generics. In this case you can pass anything you want into play.
```java
public class App {
    public static void main(String[] args) {
        Game g = new Football();
        Music m = new Jazz();
        Object obj = new Object();

        GamePlayer<Game> gamePlayer = null;
        gamePlayer.play(g);
        gamePlayer.play(obj); // won't compile

        MusicPlayer<Music> musicPlayer = null;
        musicPlayer.play(m);
        musicPlayer.play(obj);
    }
}
class Game{}
class Football extends Game{}

class Music{}
class Jazz extends Music{}

interface Player<T>{
    void play(T value);
}

interface GamePlayer<T extends Game> extends Player<T>{}
interface MusicPlayer<T extends Music> extends Player{}
```

`Collections.checkedList` - can be used to ensure that unchecked modification won't happen
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App{
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        modify(integerList);
        System.out.println("integerList => " + integerList);

        List<Integer> checkedIntegerList = Collections.checkedList(new ArrayList<>(), Integer.class);
        checkedIntegerList.add(1);
        modify(checkedIntegerList);
    }
    private static void modify(List ls){
        ls.add("hello"); // warning: Unchecked call to add(E) as a member of raw type java.util.List
    }
}
```
```
integerList => [1, hello]
Exception in thread "main" java.lang.ClassCastException: Attempt to insert class java.lang.String element into collection with element type class java.lang.Integer
```

###### Generic method overriding
Naming convention:
* overriding method (B.m1) - method that overrides another method from parent class.
* overridden method (A.m1) - method from parent class that is being overriding by method from child class.
```java
class A{
    public void m1(){} // overridden method
}
class B extends A{
    @Override
    public void m1() {} // overriding method
}
```
For one method to correctly override another they should have the same signature (method name + params) and covariant return type. Covariant return (let's use <=) - return of the same type, or it's subtype.
Covariant return:
* `B` <= `A`
* `ArrayList<String>` <= `List<String>`
* `List<B> <= List<? extends B> <= List<? extends A>`
* `List<A> <= List<? super A> <= List<? super B>`
Unlike arrays, generic collections are not reified, which means that all generic information is removed from the compiled class, so `List<String>` would be just `List` on runtime. For example `void m(List<CharSequence> cs)`, `void m(List<String> s)`, and `void m(List<SomeOtherClass> o)` are not different method signatures at all. If you remove the type specification, they all resolve to the same signature i.e. `void m(List x)`. So if you put them into one class you will got compile error, due to the type erasure. But if you put them into parent-child class you will also get error, but reason is different. From compile perspective - it's valid overloading, but from JVM it's valid overriding, that's why compiler won't compile to avoid confusion. The exception is when you override generic type with non-generic for example you can override `List<String>` with just `List`, but not vice versa.
Don't get confused by the presence of <T> in the code. The same rules of overriding still apply. The T in <T> is called as the `type` parameter. It is used as a placeholder for whatever type is actually used while invoking the method. For example, if you call the method `<T> List<T> transform(List<T> list)` with `List<String>`, T will be typed to String. Thus, it will return List<String>. If, in another place, you call the same method with `Integer`, 
T will be typed to `Integer` and therefore, the return type of the method for that invocation will be `List<Integer>`. Overriding example
```java
import java.util.*;

public class App{
    public static void main(String[] args) {
        List<A> list = new ArrayList<>();
        A a = null;
        m2(list, a);
    }
    public static  <T extends A> void m2(List<T> ls, T t){}
}

class A{}
class B extends A{}
class C extends B{}
class X{
    public <T extends A> List<T> m1(T t){
        return new ArrayList<>();
    }
    public <T extends A> void m2(List<T> ls, T t){}
}
class Y extends X {
    @Override
    public List<A> m1(A t) { // compile warning, but it's ok, return type is covariant
        return new ArrayList<>();
    }

    @Override
    public void m2(List<A> ls, A t) { // compile error, params not matching List<A> (list of concrete class) is not the same as List<T> (list of any class)
    } 
}

class X1<T extends A>{
    public List<T> m1(T t){
        return new ArrayList<>();
    }
    public void m2(List<T> ls, T t){}
}
class Y1 extends X1<A>{
    @Override
    public List<A> m1(A t) {
        return new ArrayList<>();
    }

    @Override
    public void m2(List<A> ls, A t) {
    }
}
```
Pay attention, although we can pass to B.m1 return type all 4
```
List
List<A>
List<B>
List<C>
```
param inside `B.getList can` be only of type A, otherwise it’s not overriding, but overloading.

`List<Integer>` is not subtype of `List<Number>`. So we should use `List<? extends Number>`. For `getNumberListWithParams` we can’t use this trick, params should match exactly. But if you want pass list of integers you can set param as `List<? extends Number>`, in both parent and child, and in this way you can pass list of integers.
```java
public class App {
    public static void main(String[] args) {
        var list = new ArrayList<Integer>();
        B b = new B();
        b.getNumberListWithParams(list);
    }
}
class A{
    public Number getNumber(){
        return null;
    }
    public List<? extends Number> getNumberList(){
        return null;
    }
    public List<? extends Number> getNumberListWithParams(List<? extends Number> list){
        return null;
    }
}
class B extends A{
    @Override
    public Integer getNumber(){
        return null;
    }
    @Override
    public List<Integer> getNumberList(){
        return null;
    }
    // return type can be covariant, but params should match exactly
    @Override
    public List<Integer> getNumberListWithParams(List<? extends Number> list){
        return null;
    }
}
```
Pay attention that due to type erasure we can set return value and params just as `List`. In that case we can pass any list.
```
@Override
public List getNumberListWithParams(List list){
    return null;
}
```
Pay attention, that due to type erasure, 2 methods inside same class, can’t be considered overloaded
```java
class A{
    public void getList(List<Number> list){} //won't compile
    public void getList(List<Integer> list){}
}
```

###### Generic arrays
You can't create generic array, this code `T[] arr = new T[10]` won't compile. The main reasons is that arrays store and check types on runtime, while generic on compile time change everything to `Object`. So if above was possible, you would get `ClassCastException` whenever you substitute T with anything rather than Object.
Yet there are 2 ways to create genetic array. Below we consider real-world example where you want to build your queue, which use array as internal storage.
Queue should be generic and handle any data type. Both ways act the same, cause `newInstnace` return object, and since first version is shorter, it's more preferable.
```java
class CustomQueue<T>{
    private T[] buffer;

    public CustomQueue(int capacity){
        buffer = (T[]) new Object[capacity];
    }
    
    public CustomQueue(Class<T> clz, int capacity){
        buffer = (T[]) Array.newInstance(clz, capacity);
    }
}
```
Another way is just to use array of objects, and convert when we add/fetch elements
```java
class CustomQueue<T>{
    private Object[] buffer;

    public CustomQueue(int capacity){
        buffer = new Object[capacity];
    }
    public T getItem(){
        return (T) buffer[0];
    }
}
```
Same with instantiation, we can't do just `T t = new T[]`, we should call something like `T t = (T)clz.newInstance()`
First you need to make sure that T is class, that why `Class<T> clz` is required, then you can use reflection to instantiate the class.

#### Collections
`Vector` - is synchronized array (almost all methods has `syncronized` keyword), only one thread at a time can use it. `ArrayList` - is not synchronized.
Collections lambda methods
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a","b","c","a","b","a"));
        System.out.println("original list => " + list);

        // replaceAll take UnaryOperator<E>
        list.replaceAll(s -> s.equals("a") ? "x" : s); // returns void
        System.out.println("replace all a with x => " + list);

        // removeIf takes Predicate<? super E>
        list.removeIf(s -> s.equals("x")); // returns boolean
        System.out.println("remove all x => " + list);

        System.out.println();

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        System.out.println("original map => " + map);

        // takes BiFunction
        map.replaceAll((k, v) -> v * 2); // replace values
        System.out.println("double values => " + map);

        // takes BiFunction (key and value) and returns new value
        map.computeIfPresent("a", (k, v) -> {
            System.out.println("computeIfPresent => " + k+":"+v);
            return 1;
        });
        // takes Function (key) and return new value, since there is no old value, only key is passed to function
        map.computeIfAbsent("f", k ->{
            System.out.println("computeIfAbsent => " + k);
            return 2;
        });
        // takes BiFunction (key and value) and returns new value, if no entry, value (second param) is null
        map.compute("b", (k, v) -> 0);
        // if key doesn't exist, set value, otherwise apply BiFunction and set new value for this key
        map.merge("c", 5, (oldValue, newValue) -> 0);
        System.out.println(map);
    }
}
```
```
original list => [a, b, c, a, b, a]
replace all a with x => [x, b, c, x, b, x]
remove all x => [b, c, b]

original map => {a=1, b=2, c=3, d=4}
double values => {a=2, b=4, c=6, d=8}
{a=0, b=0, c=0, d=8, x=0}
```

`compute` - work for both cases if element is present or absent(in this case value in lambda would be null). If you need to do something only in case if key is present or absent you can use `computeIfPresent` or `computeIfAbsent`(this one takes only one param - key).
```java
import java.util.*;

public class App {
    public static void main(String[] args){
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.compute("a", (k, v)->{
            System.out.println(k +" " + v);
            return 2;
        });
        map.compute("b", (k, v)->{
            System.out.println(k +" " + v);
            return 2;
        });
        System.out.println(map);
    }
}
```
```
a 1
b null
{a=2, b=2}
```

Don't confuse (all of them return value, or newValue with applied BiFunction):
* `put(key, value)` - just put new element into map, if it exists, just overwrite
* `merge(key, value, BiFunction(oldValue, newValue))` - put new element into the map, if element exists, apply function to old & new values, and overwrite it
    * good example is calculate occurrences of letters in the string => merge(letter, 1, (oldValue, newValue)->oldValue+newValue)
    so first time we encounter letter `a` we just set 1, but on each subsequent we apply our function, in this case sum old value with newValue=1
* `compute(key, BiFunction(key, oldValue))` - apply function to oldValue (which can be null) and update key with newly computed value
* computeIfAbsent/computeIfPresent - same as compute, but apply function only if value is nonPresent/present
```java
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        String str = "abcaba";
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(char ch: str.toCharArray()){
            map1.merge(ch, 1, (oldValue, newValue) -> oldValue+newValue);
            map2.compute(ch, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
        }
        System.out.println("map1 => " + map1);
        System.out.println("map2 => " + map2);
    }
}
```
```
map1 => {a=3, b=2, c=1}
map2 => {a=3, b=2, c=1}
```

###### List and Set
All collections have a method `contains(Object o)` to check if element in collection. But `Map` doesn’t have such a method. Instead, it has 2 methods `containsKey(Object o)` and `containsValue(Object o)`. If you pass wrong object, you won't get `ClassCastException`, just false. The reason, is that inside they compare by `equals` method.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>(List.of(new Person("Mike"), new Person("Jack")));
        Person p = new Person("Mike");
        System.out.println(list.contains(p));
    }
}

class Person{
    private String name;
    public Person(String name){
        this.name = name;
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof Person) {
            return Objects.equals(name, ((Person) o).name);
        }
        return false;
    }
}
```
```
true
```

`List.of` & `List.copyOf` - static methods that create unmodifiable (can’t add, remove, change) list, and can’t take null (they throw NPE if any element passed is null), `List.of` can takes 0 to n params, varags (so you can pass array). If you pass list there it will be treated as regular object - list of 1 list will be created. `List.copyOf` - can take only collection.
Immutable vs Unmodifiable. Unmodifiable - is just a view, although you can't modify it directly, if underlying collection, has been modified, this view will reflect changes. 
Immutable - completely separate unmodifiable collection. It can't be modified, and it doesn't reflect changes in case of modification of underlying collection.
`Arrays.asList` - you can't add new values to list, but can modify existing. The reason is that list still backed by array, and array has a fixed size.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a", "b"));
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        List<String> immutableList = List.copyOf(list);
        try{
            unmodifiableList.add("c");
        } catch (UnsupportedOperationException ex){
            System.out.println("unmodifiableList.add(\"c\") => " + ex);
        }
        try{
            immutableList.add("c");
        } catch (UnsupportedOperationException ex){
            System.out.println("immutableList.add(\"c\") => " + ex);
        }
        list.add("c");
        System.out.println("unmodifiableList => " + unmodifiableList);
        System.out.println("immutableList => " + immutableList);


        List<String> fromArray = Arrays.asList("a", "b");
        try{
            fromArray.add("c");
        } catch (UnsupportedOperationException ex){
            System.out.println("fromArray.add(\"c\") => " + ex);
        }
        fromArray.set(0, "c");

        var arr = new Integer[]{1,2};
        // immutable you can't modify it, and changes made in original data structure are not reflected here
        var listOf = List.of(arr);
        // unmodifiable, you can't modify it, but changes made in original data structure are reflected here
        var unmodifiable = Collections.unmodifiableList(Arrays.asList(arr));
        arr[0] = 2;
        System.out.println(listOf+" "+unmodifiable);
    }
}
```
```
unmodifiableList.add("c") => java.lang.UnsupportedOperationException
immutableList.add("c") => java.lang.UnsupportedOperationException
unmodifiableList => [a, b, c]
immutableList => [a, b]
fromArray.add("c") => java.lang.UnsupportedOperationException
```

`List.sublist` create view of original list, so all changes are reflected in original list. 
Yet if you modify (add/remove) original list and then try to traverse sublist you will get `ConcurrentModificationException`.
```java
import java.util.*;

class App {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a","b","c","d"));
        List<String> subList = list.subList(0, 3);
        subList.add("x");
        subList.set(0, "z");
        list.set(1, "z");
        System.out.println("subList => " + subList);
        System.out.println("list => " + list);

        list.add("y");
        System.out.println("subList => " + subList);
    }
}
```
```
subList => [z, z, c, x]
list => [z, z, c, x, d]
Exception in thread "main" java.util.ConcurrentModificationException
```
Since stream check for concurrent modification in the end, if we remove element from the list, underlying array [a,b,c] would become [b,c,null], but since we already pass position 0 and go to position 1 we got c, and then at position 2 we encounter null, where we try to call null.equals(a) - so we got NPE
```java
import java.util.ArrayList;
import java.util.List;

public class App {
    private static String str;
    public static void main(String[] args) {
        try{
            List<String> list = new ArrayList<>(List.of("a","b","c"));
            list.forEach(v->{
                System.out.println(v);
                if(v.equals("a")){
                    list.remove("a");
                }
            });
        } catch (RuntimeException ex){
            System.out.println(ex);
        }
        System.out.println();
        try{
            List<String> list = new ArrayList<>(List.of("a","b","c"));
            list.stream().forEach(v->{
                System.out.println(v);
                if(v.equals("a")){
                    list.remove("a");
                }
            });
        } catch (RuntimeException ex){
            System.out.println(ex);
        }
    }
}
```
```
a
java.util.ConcurrentModificationException

a
c
null
java.lang.NullPointerException
```
There are a few methods in `List` that are not generic and take `Object` as param. The reason is that these methods first run `equals(Object o)`, so they take Object and not generic param.
```java
import java.util.*;

class App{
    public static void main(String[] args){
        List<Double> al = new ArrayList<>();
        Object obj = "hello";
        System.out.println(al.contains(obj));
        System.out.println(al.indexOf(obj));
        System.out.println(al.lastIndexOf(obj));
        System.out.println(al.remove(obj));
    }
}
```
```
false
-1
-1
false
```
Don't confuse:
* fail-fast iterator - fails (throw exception) if it detects that collection is modified while iterating (`ArrayList`)
* fail-safe iterator -  doesn't throw concurrent modification exception (`ConcurrentHashMap` and `CopyOnWriteArrayList`)
There are 3 ways you can iterate over fail-fast iterator in java and get:
* exception
* incorrect behavior
* correct behavior without exception using `Iterator`
```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a","b","c"));
        /**
         * under-the-hood native iterator for ArrayList is used, which causes exception if we try to modify the list during iteration
         */
        for (String s: list){
            if(s.equals("a")){
                list.remove(s);
            }
            System.out.println(s);
        }
        /**
         * This is wrong solution, although we don't catch any exception here, and first element is deleted
         * yet since index is not updated correctly, we skip second element
         */
        for(int i = 0; i < list.size(); i++){
            String s = list.get(i);
            System.out.println(s);
            if(s.equals("a")){
                list.remove(s);
            }
        }
        System.out.println(list);
        /**
         * correct way to iterate and remove elements, cause in this case internal counters like `modCount` updated accordingly
         */
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String s = iterator.next();
            System.out.println(s);
            if(s.equals("a")){
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}
```

###### Array and Enumeration to List and back
Array to List. When convert from array to list, they both become linked and change in one is reflecting in another. Moreover, such a list is fixed like an array. You can change values, but can’t add new one. If you don’t want this use `new ArrayList<>(Arrays.asList(arr))`.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        String[] arr = {"apple", "banana", "kiwi"};
        List<String> list = Arrays.asList(arr);
        arr[0] = "mango";
        System.out.println("list => " + list);
        list.set(0, "watermelon");
        System.out.println("arr => " + Arrays.toString(arr));
    }
}
```
```
list => [mango, banana, kiwi]
arr => [watermelon, banana, kiwi]
```

Beware of the following code. It won’t compile because of incompatible types. `Arrays.asList` doesn’t do unboxing, so it returns like `List<int[]>`, but we need `List<Integer>`. 
```java
int[] arr = {1,2,3};
List<Integer> list = Arrays.asList(arr); // won't compile:
```
Unfortunately there is no easy solution. You have to do it yourself like:
```java
import java.util.*;
import java.util.stream.*;

public class App {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        // old way with the loop
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        System.out.println(list);
        // new way with java8 streams
        List<Integer> list2 = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(list2);
    }
}
```

List to Array conversion. List has a method `toArray` to convert any List to array. As you can see by default `toArray` return array of objects. If you want to specify type, you can pass type of array into `toArray` method. 
0 - means to create array the same size as list. If you need more space you can set any int value. If you specify less size, anyway returned array would be the size of list. Important thing is that array is not bound in any way to a list. Since run, modifications on list is not visible on array.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>(List.of("apple", "banana", "kiwi"));
        Object[] arr = fruits.toArray();
        String[] typedArr = fruits.toArray(new String[0]);
        // using jdk11 new syntax
        String[] typedArr2 = fruits.toArray(String[]::new);
        // changes on list are not affecting array
        fruits.set(0, "mango");
        System.out.println("arr => " + Arrays.toString(arr));
    }
}
```
```
arr => [apple, banana, kiwi]
```

We can easily convert `Enumeration` to `List` and vice versa
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5));
        Enumeration<Integer> enumeration = Collections.enumeration(list);
        List<Integer> newList = Collections.list(enumeration);
    }
}
```

###### Map
`HashMap` and `HashTable` are not iteration-deterministic. So in 2 iterations, you can get different order. If you need to preserve order use:
* `LinkedHashMap` - ordered, use order in which elements where inserted 
* `TreeMap` - ordered and sorted, sort elements on insert
`Set` - unordered collection, but `List` - ordered.
Constructor of `HashMap/LinkedHashMap` can have 2 and 3 params: 
* capacity - default size of underlying array (number of buckets)
* loadFactor - when to resize map. if .75 resize when size above 3/4 of it's capacity
    * so if you have 16 elements, once you inserted 12, it would resize
    * once map size is increased, rehashing happens to equally distribute items across the map
    * usually we set values 0-1, yet if you set 2, that would mean once we reach 200% elements, resize & rehashing happen (we insert twice many elements as capacity)
* accessOrder (only for LinkedHashMap cause we have order here):
    * by default false - you access your elements in the order they were inserted (re-insert doesn't affect the order)
    * true - now you have access-ordered map, so each time you call get/put, internally map is reordered, and they called last during iteration
    so least accessed called first:
```java
import java.util.LinkedHashMap;
import java.util.Map;

public class App{
    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedHashMap<>(16, 0.75F, true);
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        System.out.println(map);
        map.put(1, "x");
        map.get(2);
        System.out.println(map);
    }
}
```
```
{1=a, 2=b, 3=c, 4=d}
{3=c, 4=d, 1=x, 2=b}
```
As you can see, since we accessed elements with indexed 1 and 2, they moved to bottom
Giving these 3 params you can easily create your own LRU cache.
How HashMap works:
* inside there is list of buckets (backed by array of `HashMap.Node`)
* each bucket is instance of `java.util.HashMap.Node`
* for each distinct hashcode there is separate bucket
* bucket number grows proportionally, or you can set `capacity` in constructor
* if `hashcode` is same for 2 objects, then `equals` is used. If it differs then in inside bucket not single object but linked list (not `java.util.LinkedList`, some simple implementation) or `TreeMap` is stored.
Objects are searched from this linked list by using `equals` method.
So below is rule:
    * if 2 objects has same `hashcode` - 2 objects in same bucket
    * if 2 objects has same `equals` - 2 objects in 2 different buckets (cause `hashcode` is different)
    * if 2 object has same `hashcode` & `equals` - 1 object in 1 bucket
```java
import java.util.HashMap;
import java.util.Map;

public class App{
    public static void main(String[] args) {
        Map<Person, Integer> map = new HashMap<>(100);
        map.put(new Person(), 1);
        map.put(new Person(), 1);
        System.out.println(map);
    }
}

class Person{
    @Override
    public int hashCode(){
        return 0;
    }
}
```
```
{com.java.test.Person@0=1, com.java.test.Person@0=1}
```
Although hashcode are same, yet we still have 2 objects
* if you have poorly written `hashcode`, then bucket would use `TreeMap` otherwise `LinkedList`
How ConcurrentHashMap works:
* null are not allowed as key/value. Cause in concurrent implementation when `get` return null is not clear if object mapped to null or object doesn't exist
* in simple map we can call `containsKey` to check if key exists, but in concurrent the value can be modified in between this call
* it provides functionality of `HashTable` but with speed comparable with `HashMap`
* `HashTable` is slow, cause it locks whole map to perform update/delete/read/create
* `concurrencyLevel` - third param to constructor (first two `capacity` & `loadFactor` same as for `HashMap`) - estimated number of concurrently updating threads (by default 16)
* so all buckets divided into 16 (or more) lock zones and only specific zone is blocked during create/update (reads are not blocked). So 16 threads can modify map given they work on different buckets
* under-the-hood it uses a combination of `synchronized` for writing (put/remove/clear) and `Unsafe` for reading(get) to ensure data consistency. Using of `Unsafe` for get, allows to read data without any locking. For `Unsafe` 3 methods are used `getReferenceAcquire/putReferenceRelease/compareAndSetReference`
* so we use `ReentranLock` for writing, but volatile semantic with `Unsafe` for lock-free reading. That's why it may be confusing, why do you need synchronized+volatile, but exactly for this reason. You need `volatile` semantic for lock-free reading, but for correc writing you still use `synchronized`.
* `HashMap` is fail-fast, but `ConcurrentHashMap` is fail-safe
```java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class App{
    public static void main(String[] args) {
        Map<String, Integer> people = new HashMap<>();
        people.put("Mike", 25);
        people.put("Jack", 30);
        Iterator iterator = people.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println("nex => "+ people.get(iterator.next()));
            people.put("Kelvin", 35);
        }
    }
}
```
```
nex => 25
Exception in thread "main" java.util.ConcurrentModificationException
```
Another example
```java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class App{
    public static void main(String[] args) {
        System.out.println("failSafe");
        failSafe();
        System.out.println("failFast");
        failFast();
    }

    private static void failSafe(){
        Map<String, Integer> people = new ConcurrentHashMap<>();
        people.put("Mike", 25);
        people.put("Jack", 30);
        Iterator iterator = people.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println(people.get(iterator.next()));
            people.put("Kelvin", 35);
        }
    }
    private static void failFast(){
        Map<String, Integer> people = new HashMap<>();
        people.put("Mike", 25);
        people.put("Jack", 30);
        Iterator iterator = people.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println(people.get(iterator.next()));
            people.put("Kelvin", 35);
        }
    }
}
```
```
failSafe
25
30
failFast
25
Exception in thread "main" java.util.ConcurrentModificationException
```
In case of `HashMap` if we try to modify object we got exception, but for concurrent - it's ok
Simple example of RingBuffer
```java
import java.util.*;

public class App {
    public static void main(String[] args){
        LruCache<Integer, Integer> cache = new LruCache<>(3);
        for(int i = 1; i <= 3; i++){
            cache.put(i, 1);
        }
        System.out.println(cache);
        cache.put(4, 1);
        System.out.println(cache);
    }
}

class LruCache<K, V> extends LinkedHashMap<K, V>{
    private final int capacity;
    public LruCache(int capacity){
        super(capacity+1, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > capacity;
    }
}
```
```
{1=1, 2=1, 3=1}
{3=1, 1=1, 4=1}
```
`NavigableMap` - has ability to navigate back and forth.
There are 4 methods to navigate in both `NavigableMap` and `NavigableSet`
higher(key) - move up (null if last)
lower(key) - move down (null if first)
ceiling(key) - move up (current if last)
floor(key) - move down (current if first)
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        NavigableMap<String, String> treeMap = new TreeMap<>();

        treeMap.put("a", "One");
        treeMap.put("b", "Two");
        treeMap.put("c", "Three");

        String key = "b";
        System.out.println("key => " + key);

        System.out.println("firstKey => " + treeMap.firstKey() + ", firstEntry => " + treeMap.firstEntry());
        System.out.println("lastKey => " + treeMap.lastKey() + ", lastEntry => " + treeMap.lastEntry());

        System.out.println("lowerKey => " + treeMap.lowerKey(key) + ", lowerEntry => " + treeMap.lowerEntry(key)); // less than key or null
        System.out.println("higherKey => " + treeMap.higherKey(key) + ", higherEntry => " + treeMap.higherEntry(key)); // higher than key or null
        System.out.println("floorKey => " + treeMap.floorKey(key) + ", floorEntry => " + treeMap.floorEntry(key)); // less then or equal
        System.out.println("ceilingKey => " + treeMap.ceilingKey(key) + ", ceilingEntry => " + treeMap.ceilingEntry(key)); // higher than or equal

        // these 3 returns SortedMap
        System.out.println("subMap => " + treeMap.subMap("a","c"));
        System.out.println("headMap => " + treeMap.headMap(key)); // map below this key (key is excluded)
        System.out.println("tailMap => " + treeMap.tailMap(key)); // map starting from this key (key is included) and up the end
        /**
         * tailMap and headMap (for NavigableMap), can take second boolean param, denoting should we include element itself
         * By default for headMap - false, tailMap - true
         */
        System.out.println("headMap (inclusive) => " + treeMap.headMap(key, true));
        System.out.println("tailMap (not inclusive) => " + treeMap.tailMap(key, false));

        System.out.println("pollFirstEntry => " + treeMap.pollFirstEntry()); // remove and return first element from the map or null
        System.out.println("pollLastEntry => " + treeMap.pollLastEntry()); // remove and return last element from the map or null
    }
}
```
```
key => b
firstKey => a, firstEntry => a=One
lastKey => c, lastEntry => c=Three
lowerKey => a, lowerEntry => a=One
higherKey => c, higherEntry => c=Three
floorKey => b, floorEntry => b=Two
ceilingKey => b, ceilingEntry => b=Two
subMap => {a=One, b=Two}
headMap => {a=One}
tailMap => {b=Two, c=Three}
headMap (inclusive) => {a=One, b=Two}
tailMap (not inclusive) => {c=Three}
pollFirstEntry => a=One
pollLastEntry => c=Three
```

Since subMap, headMap, tailMap - return view of the original map, we can't put new values (throws IllegalArgumentException), but we can remove and change existing values - all these are reflected in original map.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        NavigableMap<String, String> treeMap = new TreeMap<>();

        treeMap.put("a", "One");
        treeMap.put("b", "Two");
        treeMap.put("c", "Three");

        var subMap = (NavigableMap<String, String>)treeMap.subMap("a","c"); // subMap returns SortedMap, that's why we are using cast
        System.out.println("subMap => " + subMap);
        subMap.put("a","xxx");
        subMap.pollLastEntry();
        try {
            subMap.put("d", "newValue");
        } catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
        System.out.println("treeMap => " + treeMap);
    }
}
```
```
subMap => {a=One, b=Two}
java.lang.IllegalArgumentException: key out of range
treeMap => {a=xxx, c=Three}
```

`Map.put` - returns old value if key existed, otherwise null. So we can use this property to change 2 map's values
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        Map<String, Integer> oldMap = new HashMap<>(Map.of("a", 1, "b", 2));
        Map<String, Integer> newMap = new HashMap<>(Map.of("a", 5, "b", 6));
        oldMap.replaceAll(newMap::put);
        System.out.println("oldMap => " + oldMap);
        System.out.println("newMap => " + newMap);
    }
}
```
```
oldMap => {a=5, b=6}
newMap => {a=1, b=2}
```

`Map.merge` very versatile method. We can solve a lot of tasks. Just imagine you need to calculate number of occurrences of names. The same can be achieved by using `Collectors` of stream.
```java
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Jack", "Bob", "Janet", "Melanie", "Bob", "Melanie", "Bob", "Jack", "Melanie", "Bob"));
        Map<String, Integer> map = new HashMap<>();
        names.forEach(name -> map.merge(name, 1, (prev, curr) -> prev + curr));
        System.out.println("map => " + map);
        Map<String, Integer> collectorMap = names
                .stream()
                .collect(Collectors.toMap(s -> s, s -> 1, (s1, s2) -> s1 + s2));
        System.out.println("collectorMap => " + collectorMap);
    }
}
```
```
map => {Bob=4, Melanie=3, Janet=1, Jack=2}
collectorMap => {Bob=4, Melanie=3, Janet=1, Jack=2}
```

As you see, no need for condition and use of `put` and `putIfAbsent` or `computeIfAbsent`. We can also rewrite it as `map.merge(name, 1, Integer::sum)`.
Here is more complicated example. Imagine you have list of operations with start and end balance. And you need to calculate total balance for all days. (balance per day = start - end, and then sum all balances for all days per account)
```java
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        var operations = List.of(
            new Operation("1", new BigDecimal("10"), new BigDecimal("20")),
            new Operation("2", new BigDecimal("10"), new BigDecimal("20")),
            new Operation("3", new BigDecimal("10"), new BigDecimal("20")),
            new Operation("1", new BigDecimal("10"), new BigDecimal("20")),
            new Operation("1", new BigDecimal("10"), new BigDecimal("20")),
            new Operation("2", new BigDecimal("10"), new BigDecimal("20"))
        );
        Map<String, BigDecimal> mergeMap = new HashMap<>();
        operations.forEach(op -> {
            mergeMap.merge(op.getAccount(), op.getEndAmount().subtract(op.getStartAmount()), BigDecimal::add);
        });
        System.out.println("mergeMap => " + mergeMap);

        Map<String, BigDecimal> collectorMap = operations.stream().collect(Collectors.toMap(Operation::getAccount, op->op.getEndAmount().subtract(op.getStartAmount()), BigDecimal::add));
        System.out.println("collectorMap => " + collectorMap);
    }
}
class Operation {
    private final String account;
    private final BigDecimal startAmount;
    private final BigDecimal endAmount;
    public Operation(String account, BigDecimal startAmount, BigDecimal endAmount) {
        this.account = account;
        this.startAmount = startAmount;
        this.endAmount = endAmount;
    }
    public String getAccount() {
        return account;
    }
    public BigDecimal getStartAmount() {
        return startAmount;
    }
    public BigDecimal getEndAmount() {
        return endAmount;
    }
}
```
```
mergeMap => {1=30, 2=20, 3=10}
collectorMap => {1=30, 2=20, 3=10}
```

###### binarySearch
`Collections.binarySearch` and `Arrays.binarySearch` when element not found, doesn’t return just -1 (yet it can). It returns  -(possiblePosition+1), where possiblePosition is the position inside sorted list if element would be inside. So in case searching element could be inserted at position 0, result would be -1. Pay attention there is no such method as search or linearSearch, only binarySearch. Possible range of answers for array/list length of n is from -(n+1) to (n-1). So if we have 5 elements, possible results are from -6 to 4. e can pass `Comparator` into both `sort` and `binarySearch` - and it should be the same comparator, otherwise - unpredictable result. And objects inside list or array should implement `Comparable` or you should pass `Comparator` (if you pass null as comparator, natural sort would be used, no NPE) otherwise `ClassCastException`.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        String[] arr = {"d", "c", "b", "a"};
        List<String> list = new ArrayList<>(Arrays.asList(arr)); // wrap into new list so underlying array won't be sorted
        Collections.sort(list); //collection must be sorted, otherwise output is unpredictable
        // you may pass null as comparator, and in this case natural order sorting would be used, no NPE
        Collections.sort(list, null);
        System.out.println("b => " + Collections.binarySearch(list, "b"));
        System.out.println("bb => " + Collections.binarySearch(list, "bb"));

        Arrays.sort(arr);
        System.out.println("b => " + Arrays.binarySearch(arr, "b"));
        System.out.println("bb => " + Arrays.binarySearch(arr, "bb"));

        System.out.println();
        Arrays.sort(arr, Comparator.naturalOrder());
        System.out.println("bb => " + Arrays.binarySearch(arr, "bb", Comparator.reverseOrder()));

        A[] arrA = {new A(), new A()};
        Arrays.sort(arrA); //throws ClassCastException: class com.java.test.A cannot be cast to class java.lang.Comparable
        System.out.println("bb => " + Arrays.binarySearch(arrA, new A())); //throws ClassCastException: class com.java.test.A cannot be cast to class java.lang.Comparable
        
        List<A> lsA = new ArrayList<>(List.of(arrA));
        Collections.sort(lsA); // won't compile, cause A doesn't extends Comparable
    }
}
class A{}
```
```
b => 1
bb => -3
b => 1
bb => -3
```
If bb was inside array, its position would be 2, right after b and before c. But since it’s not in array index returned is -(2+1) => -3
Although `Object` doesn't implement `Comparable` directly, when you pass list of objects to sort, if all objects of same type, this type would be used.
So if you have list of objects, where all integers, sort would work fine and use comparable from integer. 
But in case you have different types (for example Integer and String) you will get `ClassCastException`, Integer can't be cast to String.
```java
import java.util.*;

public class App{
    public static void main(String[] args){
        List<Object> list = new ArrayList<>(List.of(2,1));
        List<Object> list2 = new ArrayList<>(List.of(2,"1"));
        Collections.sort(list, null);
        System.out.println(list);
        Collections.sort(list2, null);
        System.out.println(list2);
    }
}
```
```
[1, 2]
Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
```

###### Order and duplicates
 `Array` and `ArrayList` are an ordered collection (also known as a sequence) that allows duplicates. `List` is a type of ordered collection that maintains the elements in insertion order while `Set` is a type of unordered collection so elements are not maintained any order. If you need to preserve order of insertion for set use `LinkedHashSet`.  All elements inside set are unique. Internally set using map, and use its keys as values.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        Set<Integer> hashSet = new HashSet<>(List.of(3,2,1));
        Set<Integer> treeSet = new TreeSet<>(List.of(3,2,1));
        Set<Integer> linkedHashSet = new LinkedHashSet<>(List.of(3,2,1));
        System.out.println("hashSet => " + hashSet);
        System.out.println("treeSet => " + treeSet);
        System.out.println("linkedHashSet => " + linkedHashSet);
    }
}
```
```
hashSet => [1, 2, 3]
treeSet => [1, 2, 3]
linkedHashSet => [3, 2, 1]
```
Also `TreeSet` and all started from `Tree` doesn't allow null values. You can put into `TreeSet` as values and `TreeMap` as keys only objects that implement `Comparable<T>` or to pass `Comparator<T>` directly into constructor. If neither of this, `ClassCastException` is thrown. This is also the case for `PriorityQueue`
```java
import java.util.*;

public class App {
    public static void main(String[] args){
        // throws exception cause class A doesn't implements Comparable
        try{
            Set<A> set2 = new TreeSet<>();
            set2.add(new A());
        } catch (ClassCastException ex){
            System.out.println(ex);
        }

        try{
            Queue<A> queue = new PriorityQueue<>();
            queue.add(new A());
        } catch (ClassCastException ex){
            System.out.println(ex);
        }


        Comparator<A> comparator = (a, b)->{
            return 0;
        };
        Set<A> set3 = new TreeSet<>(comparator);
        set3.add(new A());

        // only 1 element would be there, cause comparator always return 0 - that's mean whatever we put they all the same
        Set<Integer> set = new TreeSet<>((a,b)->0);
        set.add(1);
        set.add(2);
        set.add(1);
        System.out.println("set with compatator that returns 0 => " + set);
    }
}

class A{}
```
```
java.lang.ClassCastException: class com.java.test.A cannot be cast to class java.lang.Comparable (com.java.test.A is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
java.lang.ClassCastException: class com.java.test.A cannot be cast to class java.lang.Comparable (com.java.test.A is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
set with compatator that returns 0 => [1]
```

How sets work:
* CopyOnWriteArraySet (use internally `CopyOnWriteArrayList`) - uniqueness is guaranteed by `equals` method. CopyOnWriteArrayList (thread-safe implementation of `ArrayList`):
    * use volatile array as internal structure
    * all write methods `add/set/remove` are `synchronized`, inside they add/remove new value and then replace array
    * get is not synchronized, it just returns element from array. Since array is volatile, once write operation is done, it would be replaced, and volatile guarantee happened-before, so read would always read the latest value
    * since under-the-hood implementation is based on array, `contains` takes O(n) time
    * if you want `contains` to run O(1) you have to use `ConcurrentHashMap.newKeySet` or combine `AtomicReference` with `HashSet` and replace set on each modification (atomic use volatile inside, so on replace it would guarantee happened-before)
    Don't confuse:
    * volatile array - means whole object is volatile. If you just change one element in one thread, it may be not visible in another. But if you replace whole array (with 1 new element) in first thread, then new value would be seen from second thread. Basically `CopyOnWriteArrayList` is doing this
    * array of volatile elements - you can use one of `AtomicLongArray/AtomicIntegerArray/AtomicReferenceArray`
* HashSet (use internally `HashMap`) - uniqueness is guaranteed by `hashcode/equals` method (first compare by `hashcode` and if they match then use `equals`, so it would be same bucket with LinkedList)
* TreeSet (use internally `TreeMap`) - uniqueness is guaranteed by `compare/compareTo` methods
```java
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class App{
    public static void main(String[] args) {
        List<Person> people = List.of(new Person(1, "Mike"), new Person(2, "Mike"));
        Set<Person> treeSet = new TreeSet<>();
        treeSet.addAll(people);
        System.out.println("treeSet => " + treeSet);
        Set<Person> hashSet = new HashSet<>();
        hashSet.addAll(people);
        System.out.println("hashSet => " + hashSet);
    }
}


class Person implements Comparable<Person>{
    private int id;
    private String name;
    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString(){
        return "Person[id=" + id + ", name=" + name + "]";
    }
    @Override
    public int hashCode(){
        return 0;
    }
    @Override
    public boolean equals(Object o){
        return true;
    }
    @Override
    public int compareTo(Person person) {
        return id + name.hashCode() - person.id - person.name.hashCode();
    }
}
```
```
treeSet => [Person[id=1, name=Mike], Person[id=2, name=Mike]]
hashSet => [Person[id=1, name=Mike]]
```
As you see simple hash has single object cause `hashcode/equlas` return same value, but `TreeSet` use comparable to compare objects, that's why there are 2 objects there.
`SortedSet` has 2 methods to finds subsets `headSet` and `tailSet`
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        SortedSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < 10; i++){
            set.add(i);
        }
        System.out.println(getNextEl(set, 5));
        System.out.println(getPrevEls(set, 5));
        System.out.println("descendingSet => " + ((NavigableSet)set).descendingSet());
        
        // calling TreeSet.subSet(1, true, 5, false)
        SortedSet<Integer> sub = set.subSet(1,5);
        System.out.println("sub => " + sub);
        // 4 params subSet take second and fourth param boolean values indicating should we include from/to values or not.
        NavigableSet<Integer> subTree = ((TreeSet)set).subSet(1,true, 5, true);
        System.out.println("subTree => " + subTree);
        // throws IllegalArgumentException, cause you can add, change values only within range
        subTree.add(53);
    }
    private static Integer getNextEl(SortedSet<Integer> set, int v){
        return set.tailSet(v).first();
    }
    private static Set<Integer> getPrevEls(SortedSet<Integer> set, int v){
        return set.headSet(v);
    }
}
```
```
5
[0, 1, 2, 3, 4]
descendingSet => [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
sub => [1, 2, 3, 4]
subTree => [1, 2, 3, 4, 5]
Exception in thread "main" java.lang.IllegalArgumentException: key out of range
```

###### Queue and Stack
Don't confuse (see [javadoc for queue](https://docs.oracle.com/javase/6/docs/api/java/util/Queue.html))
* head - start of the queue, from where you read & remove elements
* tail - end of the queue, where you add new elements.
Same applied to maps and sets. Head is the beginning, and tail where newly-added elements appends.
how to remember: What people in the United States commonly call a line, as in the thing you stand in at the post office, people in other English speaking countries call a queue. So, it's easier for Americans to keep the terminology straight if you substitute "line" for "queue." In other words, when you are in the head, or front, of the line, you are the next to be called.
So if we have a queue[1,2,3], 1 - is head, 3 - tail. If we add 4, it would be added to tail, after 3
If you want to implement queue(FIFO) - use `Queue` interface or `Deque`, if you want to implement stack(LIFO) - use `Deque` interface. `Deque` is child of `Queue` it extends it to work from both ends.
All methods divide on 2 types. Some throws exception, others just return null/false
6 basic method of `Queue` interface (actually there are 3 and they duplicated with one which throws exception):
* boolean add(IllegalStateException)/offer(false) - add to the tail
* T remove(NoSuchElementException)/poll(null)     - remove from the head
* T element(NoSuchElementException)/peek(null)    - view element from the head
For `BlockingQueue` there are blocking operations:
* T take (InterruptedException) - wait until elements appear in queue and poll it. Keep in mind that simple `poll` would immediately return null if queue is empty
* void put (InterruptedException) - wait until there is space in queue and add new element
```java
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
    public static void main(String[] args) {
        try{
            // we can't create LinkedBlockingQueue with capacity <= 0
            Queue<Integer> queue = new LinkedBlockingQueue<>(1);
            System.out.println(queue.add(1));
            System.out.println(queue.offer(1));
            System.out.println(queue.add(1));
        } catch (IllegalStateException ex){
            System.out.println(ex);
        }
        System.out.println();
        try{
            Queue<Integer> queue = new ArrayDeque<>();
            System.out.println(queue.poll());
            System.out.println(queue.remove());
        } catch (NoSuchElementException ex){
            System.out.println(ex);
        }
        System.out.println();
        try{
            Queue<Integer> queue = new ArrayDeque<>();
            System.out.println(queue.peek());
            System.out.println(queue.element());
        } catch (NoSuchElementException ex){
            System.out.println(ex);
        }
    }
}
```
```
true
false
java.lang.IllegalStateException: Queue full

null
java.util.NoSuchElementException

null
java.util.NoSuchElementException
```
Deque/Stack (Deque - name of java interface, stack - just data structure)
void push - add to the head (IllegalStateException)
pop - remove from the head (NoSuchElementException)
getFirst/getLast - peek first/last element from the deque (NoSuchElementException if empty)
```java
class App {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("queue => ");
        while(queue.size() > 0){
            System.out.println(queue.poll());
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("stack => ");
        while(stack.size() > 0){
            System.out.println(stack.poll());
        }
    }
}
```
```
queue => 
1
2
3
stack => 
3
2
1
```

`ArrayDeque` is generally consider a faster implementation then `LinkedList`. Be careful using `PriorityQueue`. It sorts elements based on some internal sorting or on passed comparator. LinkedList is good for stack/queue data structures (so that's why it implements `Deque` interface). So if you want to store some history (of web pages visited), linked list is good choice. Yet `LinkedHashMap/LinkedHashSet` just extends `HashMap/HashSet` and guarantee order of processing - it stores internally double linked list and maintain order on iteration using this list.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(3);
        queue.add(2);
        queue.add(1);
        System.out.println("queue => ");
        while(queue.size() > 0){
            System.out.println(queue.poll());
        }
    }
}
```
```
queue => 
1
2
3
```
As you see we add elements with order 3,2,1. But queue return them sorted.

If we want to poll elements in some predefined order (for example first even numbers than odd in their descending order) we can pass custom `Comparator<T>` to `PriorityQueue` constructor
Any number & 1 = either 1(if it's odd) or 0 (if it's even)
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>((a,b)->(a&1)==(b&1)?b-a:a%2==0?-1:1);
        for(int i = 0; i <= 9; i++){
            queue.offer(i);
        }
        while (!queue.isEmpty()){
            System.out.print(queue.poll());
        }
    }
}
```
```
8642097531
```

RingBuffer - is special type of queue with limited size, when you add element, and it’s full it add it and remove first.
```java
public class App {
    public static void main(String[] args) throws Exception {
        RingBuffer<String> ringBuffer = new MyRingBuffer<>(4);
        ringBuffer.add("A");
        ringBuffer.add("B");
        ringBuffer.add("C");
        ringBuffer.add("D"); //ABCD
        System.out.println(ringBuffer);
        ringBuffer.add("E"); //BCDE
        System.out.println(ringBuffer);
        ringBuffer.add("F"); //CDEF
        System.out.println(ringBuffer);
    }
}
interface RingBuffer<T>{
    void add(T value);
}
class MyRingBuffer<T> implements RingBuffer<T> {
    private int size;
    private List<T> list;
    public MyRingBuffer(int size) {
        this.size = size;
        list = new ArrayList<>();
    }
    @Override
    public void add(T value) {
        if (list.size() == size) {
            list.remove(0);
        }
        list.add(value);
    }
    @Override
    public String toString() {
        return list.toString();
    }
}
```
```
[A, B, C, D]
[B, C, D, E]
[C, D, E, F]
```
Below is fixed-size queue custom implementation
```java
public class App{
    public static void main(String[] args) {
        SimpleQueue<Integer> queue = new FixedSizeArraySimpleQueue<>(3);
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        System.out.println(queue.offer(4));
        queue.poll();
        queue.poll();
        System.out.println(queue.offer(5));
        System.out.println(queue.offer(6));
        System.out.println(queue.offer(7));
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}

interface SimpleQueue<T>{
    boolean offer(T t);
    T poll();
}
class FixedSizeArraySimpleQueue<T> implements SimpleQueue<T> {
    private T[] buffer;
    private int head = 0;
    private int tail = 0;

    public FixedSizeArraySimpleQueue(int capacity) {
        buffer = (T[]) new Object[capacity];
    }

    @Override
    public boolean offer(T t) {
        if (t == null || tail - buffer.length + 1 > head) {
            return false;
        }
        buffer[tail % buffer.length] = t;
        tail++;
        return true;
    }

    @Override
    public T poll() {
        T obj = buffer[head];
        if (obj != null) {
            head++;
            if (head == buffer.length) {
                head = 0;
            }
        }
        return obj;
    }
}
```
```
true
true
true
false
true
true
false
3
5
```
This is unlimited queue, with array increasing in size, once we reach full capacity. Queue should act as ring buffer, and only when there is no space (tail==head), we should grow it. Almost same implementation is used by `ArrayDeque`, they have more checks, but idea is the same, since I was writing it first, then take a look at java queue implementation, I assume this is the most standard way to implement this.
You can try to build many-to-many concurrent queue:
* many writers - no problem, same as multithreaded single writer, just make head/tail volatile
* many readers - a bit difficult, cause now each reader should have their own offset, we also should set elements to null after all readers have read
Take a look at lmax disruptor, it basically many-to-many queue
```java
public class App{
    public static void main(String[] args) {
        SimpleQueue<Integer> queue = new ArraySimpleQueue<>(3);
        System.out.println(queue.poll());
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        System.out.println(queue.offer(4));
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}

interface SimpleQueue<T>{
    boolean offer(T t);
    T poll();
}
class ArraySimpleQueue<T> implements SimpleQueue<T>{
    private T[] buffer;
    private final int capacity;
    private int head = 0;
    private int tail = 0;

    public ArraySimpleQueue(int capacity){
        this.capacity = capacity;
        buffer = (T[]) new Object[capacity];
    }

    int resizeNumber;
    public int getArraySize(){
        return buffer.length;
    }
    public int getResizeNumber(){
        return resizeNumber;
    }

    /**
     * if array is full, create new array, change tail/head values
     */
    @Override
    public boolean offer(T t) {
        if (t == null){
            return false;
        }
        if (tail == head && buffer[head] != null){
            T[] newBuffer = (T[]) new Object[buffer.length + capacity];
            int i = 0, j = head;
            while (buffer[j] != null){
                newBuffer[i++] = buffer[j];
                buffer[j] = null;
                j++;
                if (j == buffer.length){
                    j = 0;
                }
            }
            buffer = newBuffer;
            head = 0;
            tail = i;
        }
        buffer[tail] = t;
        tail++;
        if (tail == buffer.length){
            tail = 0;
        }
        return true;
    }

    @Override
    public T poll() {
        if (head == tail && buffer[head] == null){
            return null;
        }
        T obj = buffer[head];
        buffer[head] = null;
        head++;
        if(head == buffer.length){
            head = 0;
        }
        return obj;
    }
}
```

#### Functional Programming and Stream API
###### Functional interfaces
Automatic variables - those that declared inside block of code (named like that because they would be gone automatically when we exit the block). Instance & static variables shouldn't be effectively final in order to be used inside lambda.
```java
class Scope{
    int global = 1; // instance variable
    static int globalStatic = 1; // static variable
    public void print() {
        final int local1 = 1; // final automatic variable
        int local2 = 1; // effectively final automatic variable (cause we don't explicitly change it's value)
        int local3 = 1; // automatic variable
        global = 2;
        local3 = 2;
        Runnable r1 = ()->{
            System.out.println(global + " " + globalStatic + " " + local1 + " " + local2);
            System.out.println(local3); //won't compile cause local3 - not effectively final
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(global + " " + globalStatic + " " + local1 + " " + local2);
                System.out.println(local3); //won't compile cause local3 - not effectively final
            }
        };
        class MyClass{
            public void doIt(){
                System.out.println(global + " " + globalStatic + " " + local1 + " " + local2);
                System.out.println(local3); //won't compile cause local3 - not effectively final
            }
        }
        static class MyStaticClass{} // won't compile, static classes can be only in static methods
    }
}
```

You can pass lambda to method in 2 ways:
1 - using custom interface with one method (called functional interface)
2 - using one of standard functional interfaces like `Predicate` or `Consumer`
```java
interface Validator {
    boolean test(Integer i);
}
public class App {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        printInterface(list, i -> i % 2 == 0);
        System.out.println();
        printPredicate(list, i -> i % 2 == 0);
        System.out.println();
        printConsumer(list, i -> {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        });
    }
    private static void printInterface(List<Integer> list, Validator validator) {
        for (Integer v : list) {
            if (validator.test(v))
                System.out.println(v);
        }
    }
    private static void printPredicate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer v : list) {
            if (predicate.test(v))
                System.out.println(v);
        }
    }
    private static void printConsumer(List<Integer> list, Consumer<Integer> fn) {
        for (Integer v : list) {
            fn.accept(v);
        }
    }
}
```

Functional interface have only one abstract method (but you can have as much as you want static and default methods there). Abstract class with one abstract method can't work as functional interface.
```java
public class App {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("apple", "mandarin"));
        Counter counter = s -> {
            System.out.println(s + ": " + s.length());
        };
        list.forEach(s -> counter.count(s));

        NewCounter newCounter = s -> {
            System.out.println(s + ": " + s.length());
        };
        list.forEach(s -> newCounter.count(s));
    }
}

interface Counter{
    void count(String s);
}
interface NewCounter{
    default void count(String s){
        System.out.println("hello world");
    }
    void doIt(String s);
}
```
```
apple: 5
mandarin: 8
hello world
hello world
```
Although NewCounter is valid functional interface (has only one abstract method doIt), we call not doIt, but count method => which is default and already defined. So that's why in second `forEach` we calling not lambda defined in newCounter, but default method. If you declare count `static` it won't compiled, cause static can be called from interface object directly.

You can use annotation `@FunctionalInterface` to check whether `interface` is functional or not
```java
@FunctionalInterface
interface X{
    void m1();
}

@FunctionalInterface //compile error (2 abstract methods instead of 1)
interface Y{
    void m1();
    void m2();
}
```

`Supplier` - takes nothing, returns value
```java
import java.time.LocalDate;
import java.util.function.Supplier;

public class App {
    public static void main(String[] args) {
        // Supplier is used to create new objects, following 2 lines are equivalent
        Supplier<LocalDate> localDateSupplier = LocalDate::now;
        Supplier<LocalDate> localDateSupplier2 = () -> LocalDate.now();
        System.out.println(localDateSupplier.get());
    }
}
```
```
2020-02-12
```

We have 3 special suppliers for primitive types
```java
import java.util.function.*;

class App{
    public static void main(String[] args) {
        Supplier<Integer> integerSupplier = ()->1;
        System.out.println("integerSupplier => " + integerSupplier.get());
        IntSupplier intSupplier = ()->2;
        System.out.println("intSupplier => " + intSupplier.getAsInt());
        LongSupplier longSupplier = ()->3;
        System.out.println("longSupplier => " + longSupplier.getAsLong());
        DoubleSupplier doubleSupplier = ()->4;
        System.out.println("doubleSupplier => " + doubleSupplier.getAsDouble());
    }
}
```
```
integerSupplier => 1
intSupplier => 2
longSupplier => 3
doubleSupplier => 4.0
```

`Consumer` (one argument) and `BiConsumer` (two arguments, map.forEach - takes BiConsumer<K, V>, not Consumer<Map.Entry<K,V>> ) - takes param, returns nothing.
```java
import java.util.*;
import java.util.function.*;

public class App {
    public static void main(String[] args) {
        Consumer<String> print = System.out::println;
        print.accept("hello world");
        BiConsumer<String, Integer> printAge = (name, age) -> System.out.println("My name is " + name + ", my age is " + age);
        printAge.accept("Jack", 24);

        /**
         * 15.26.3. Type of a Lambda Expression
         * If the body of a lambda is a statement expression (that is, an expression that would be allowed to stand alone as a statement), 
         * it is compatible with a void-producing function type; any result is simply discarded. So, for example, both of the following are legal:
         */
        List<String> list = new ArrayList<>();
         //Predicate has a boolean result
        Predicate<String> p = s -> list.add(s);
        // Consumer has a void result
        Consumer<String> c = s -> list.add(s);
    }
}
```
```
hello world
My name is Jack, my age is 24
```
**Pay attention, that types of arguments for lambda should match arguments of functional interface. In case functional interface doesn't directly state its type, lambda params should be Object
```java
import java.util.*;
import java.util.function.Consumer;

public class App {
    public static void main(String[] args) {
        Consumer<String> c1 = (String s) -> System.out.println(s);
        Consumer c2 = (String s) -> System.out.println(s); // won't compile since Consumer is not of String type
        Consumer c3 = (Object s) -> System.out.println(s);
        Consumer c4 = s -> System.out.println(s);
        var c5 = s -> System.out.println(s); // can't resolve type
        List.of("a", "b", "c").forEach(c1);
    }
}
```
`Predicate` (one argument) and `BiPredicate` (two arguments)  - take params, return boolean (used to check something).
```java
import java.util.function.*;

public class App {
    public static void main(String[] args) {
        Predicate<Integer> isAdult = (age) -> age >= 18;
        BiPredicate<Integer, Integer> isOlder = (age1, age2) -> age1 > age2;
        if (isAdult.test(20)) {
            System.out.println("Adult");
        }
        if (isOlder.test(21, 20)) {
            System.out.println("Older");
        }
    }
}
```
```
Adult
Older
```
`Function` and `BiFunction` take 2 (one param, 1 return) and 3 (2 params, 1 return) params.
```java
import java.util.function.*;

public class App {
    public static void main(String[] args) {
        Function<String, Integer> calcLen = (s) -> s.length();
        BiFunction<String, String, String> concat = (s1, s2) -> s1 + " " + s2;
        System.out.println(calcLen.apply("hello"));
        System.out.println(concat.apply("hello", "world"));
    }
}
```
```
5
hello world
```
`UnaryOperator` and `BinaryOperator` are special types of `Function` and `BinaryFunction`, when both input param and output are the same.
```java
import java.util.function.*;

public class App {
    public static void main(String[] args) {
        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;
        System.out.println(binaryOperator.apply(2,3));
        System.out.println(biFunction.apply(2,3));

        UnaryOperator<Integer> squareUnary = a -> a * a;
        Function<Integer, Integer> squareFunc = a -> a * a;
        System.out.println(squareUnary.apply(3));
        System.out.println(squareFunc.apply(3));
    }
}
```
```
5
5
9
9
```
If we don't want to autobox/unbox value we can use functions that work directly with primitives. In some cases working with these functions can speed up your program, cause no need to spend time for autoboxing/unboxing.
There are 5 types of functions that work with primitives. `xxx` - can be int, long, double (not byte,short,char,float,boolean).
`XxxUnaryOperator` - takes xxx, returns xxx, call - applyAsInt
`XxxBinaryOperator` - takes 2 xxx, returns xxx, call - applyAsInt
`XxxFunction<T>` - takes xxx, returns T, call - apply
`ToXxxFunction<T>` - takes T, return xxx, call - applyAsInt
`ToXxxBiFunction<T, U>` - takes 2 params T and U, returns xxx, call - applyAsInt
We have 4 types of primitive supplier: `XxxSupplier`, you can call it as `getAsXxx`, (xxx - int, long, double, boolean)
We have 3 types of primitive consumer `XxxConsumer`, you can call it as `accept`. (xxx - int, long, double)
we have 3 types of object-&-primitive consumer `ObjXxxConsumer<T>`, it accepts 2 params (T, xxx), you can call it as `accept`. (xxx - int, long, double). Can be useful when you need BiConsumer with one object and one primitive.
We have 3 types of primitive predicate `XxxPredicate`, you can call it as `test`, (xxx - int, long, double)
we have 3 types of primitive-to-primitive functions `XxxToYyyFunction`, you can call it with `getAsYyy`. (xxx, yyy - int, long, double).
```java
import java.util.function.*;

public class App {
    public static void main(String[] args) {
        IntUnaryOperator intUnaryOperator = i -> i * 2;
        System.out.println("intUnaryOperator => " + intUnaryOperator.applyAsInt(2));
        IntBinaryOperator intBinaryOperator = (i1, i2) -> i1 + i2;
        System.out.println("intBinaryOperator(2,3) => " + intBinaryOperator.applyAsInt(2, 3));
        IntFunction<String> intFunction = i -> "int: " + i;
        System.out.println("intFunction => " + intFunction.apply(2));
        ToIntFunction<String> toIntFunction = s -> s.length();
        System.out.println("toIntFunction => " + toIntFunction.applyAsInt("hello"));
        ToIntBiFunction<String, String> toIntBiFunction = (s1, s2) -> s1.length() + s2.length();
        System.out.println("toIntBiFunction(hello, world) => " + toIntBiFunction.applyAsInt("hello", "world"));
    }
}
```
```
intUnaryOperator => 4
intBinaryOperator(2,3) => 5
intFunction => int: 2
toIntFunction => 5
toIntBiFunction(hello, world) => 10
```

 `addThen` and `compose` are equivalent. In other words: `x.andThen(y)` is the same as `y.compose(x)`.
For `BiFunction` we also have `addThen` only, which combine BiFunction & Function, and return BiFunction
```java
import java.util.function.BiFunction;
import java.util.function.Function;

public class App {
    public static void main(String[] args) {
        Function<String, Integer> getLen = s -> s.length();
        Function<Integer, String> showLen = s -> "len: " + s;
        System.out.println(getLen.andThen(showLen).apply("hello"));
        System.out.println(showLen.compose(getLen).apply("hello"));

        BiFunction<Integer, Integer, Integer> mul = (a, b) -> a * b;
        Function<Integer, Integer> times2 = (a) -> a * 2;
        BiFunction<Integer, Integer, Integer> mulAndTimes2 = mul.andThen(times2);
        System.out.println(mulAndTimes2.apply(3, 4));
    }
}
```
```
len: 5
len: 5
24
```

###### Method reference
Lambda double colon `::`, also called method reference.
If you have static method you can call it only from class => MyClass::staticMethod,
if you have instance method you can call it from instance variable => my::instanceMethod, but also from static context, but in this case you should use Function and pass instance of that object
```java
import java.util.function.Function;
import java.util.function.Supplier;

public class App {
    public static void main(String[] args) {
        // constructor reference
        Supplier<My> s1 = My::new;
        My my = s1.get();
        // static reference
        Supplier<Integer> s2 = My::staticMethod;
        Supplier<Integer> s3 = my::instanceMethod;
        Supplier<Integer> s4 = (new My())::instanceMethod;

        Function<Integer, Integer> f1 = My::getLength; // won't compile, cause getLength expects String, but Integer is inferred to be given
        Function<String, Integer> f2 = My::getLength;
        
        Supplier<Integer> s5 = My::instanceMethod; // won't compile: non-static methods can't be referenced from static context
        Function<My, Integer> f5 = My::instanceMethod; // fine, cause we will pass instance of My into function
        int r1 = f5.apply(new My()); // return 0
    }
}

class My{
    public static Integer staticMethod(){
        return 0;
    }
    public Integer instanceMethod(){
        return 0;
    }
    public static Integer getLength(String str){
        return 0;
    }
}
```
Method reference works a little bit different from lambda. It creates reference of the current object (if we change object, method would be called for old value). Lambda - just wrap(delay) object invocation (so if we change object, method would be called on new object). Notice that since `str` is static variable we can change it freely (only local variables must be final or effectively final in order to participate in lambda)
```java
import java.util.function.Supplier;

public class App {
    private static String str;
    public static void main(String[] args) {
        str = "hello";
        Supplier<String> s1 = str::toUpperCase;
        Supplier<String> s2 = ()->str.toUpperCase();
        str = "world";
        System.out.println(s1.get());
        System.out.println(s2.get());
    }
}
```
```
HELLO
WORLD
```
Here is nice example where method reference useful to write neat code. `ValidatorFactory.getValidator` - return single validator, but in reality it returns all validators with `validate` method, that run it on all validators
```java
import java.util.ArrayList;
import java.util.List;

public class App{
    public static void main(String[] args) {
        int n = 10;
        Validator validator = new ValidatorFactory().getValidator();
        System.out.println(validator.validate(n));
    }
}

enum RejectReason {
    TOO_BIG,
    TOO_SMALL,
    OK;
}
interface Validator{
    RejectReason validate(int n);
}
class ValidatorFactory{
    List<Validator> validators = new ArrayList<>();
    public ValidatorFactory(){
        validators.add(new TooBigValidator());
        validators.add(new TooSmallValidator());
    }
    public Validator getValidator(){
        return this::check;
    }
    public RejectReason check(int n){
        for (Validator v: validators){
            RejectReason reason = v.validate(n);
            if(reason != RejectReason.OK){
                return reason;
            }
        }
        return RejectReason.OK;
    }
}
class TooBigValidator implements Validator{
    @Override
    public RejectReason validate(int n) {
        if (n > 100){
            return RejectReason.TOO_BIG;
        }
        return RejectReason.OK;
    }
}
class TooSmallValidator implements Validator{
    @Override
    public RejectReason validate(int n) {
        if (n < 10){
            return RejectReason.TOO_SMALL;
        }
        return RejectReason.OK;
    }
}
```

###### Comparator and Comparable
Remember Java sort order `empty > space > number > uppercase > lowercase` (natural sorting)
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("", " ", "1", "A", "a"));
        Collections.shuffle(list);
        System.out.println("shuffled => " + list);
        Collections.sort(list);
        System.out.println("sorted => " + list);
    }
}
```
```
shuffled => [a, A,  , , 1]
sorted => [,  , 1, A, a]
```
Don't confuse:
* `Comparable` - just interface with one method `int compareTo(T var1)`.
* `Comparator` - functional interface with method `int compare(T var1, T var2)`
`Collections.sort` just as constructor of all sortable maps/sets like `TreeMap/TreeSet` - takes `Comparator` as second argument. If class implements `Comparable` we can omit second param(class should explicitly implement this interface, otherwise `ClassCastException` would be thrown). 
Since `Comparator` is functional interface we can pass lambda that takes 2 params.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Jack", 180, 80));
        people.add(new Person("Jack", 181, 75));
        Collections.sort(people, Person::sort1);
        Collections.sort(people, Person.sort2());
        // equivalent to
        people.sort(Person::sort1);
        people.sort(Person.sort2());
        // equivalent to lambda
        Collections.sort(people, (p1, p2)->{
            return 0;
        });
        System.out.println(people);
    }
}
class Person {
    private String name;
    private int height;
    private int weight;
    public Person(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return name + "-" + height + "-" + weight;
    }

    //Sort by name, then by height, then by weight
    public static int sort1(Person p1, Person p2){
        int name = p1.name.compareTo(p2.name);
        if(name == 0){
            int height = p1.height - p2.height;
            if(height == 0){
                return p1.weight - p2.weight;
            }
            return height;
        }
        return name;
    }
    
    //Sort by name, then by height, then by weight
    public static Comparator<Person> sort2() {
        Comparator<Person> comparator = Comparator.comparing(person -> person.name);
        comparator = comparator.thenComparingInt(person -> person.height);
        comparator = comparator.thenComparingInt(person -> person.weight);
        return comparator;
    }
}
```
If you want to sort by height in reversed order you can add `reversed()` to the code
```java
public static Comparator<Person> sort() {
    Comparator<Person> comparator = Comparator.comparing(person -> person.name);
    comparator = (comparator.thenComparingInt(person -> person.height)).reversed();
    comparator = comparator.thenComparingInt(person -> person.weight);
    return comparator;
}
```
You can also achieve this by implementing `Comparable<T>` interface
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Jack", 180, 80));
        people.add(new Person("Jack", 181, 75));
        Collections.sort(people);
        System.out.println(people);
    }
}
class Person implements Comparable<Person>{
    private String name;
    private int height;
    private int weight;
    public Person(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return name + "-" + height + "-" + weight;
    }
    @Override
    public int compareTo(Person p1) {
        Comparator<Person> comparator = Comparator.comparing(person -> person.name);
        comparator = comparator.thenComparingInt(person -> person.height);
        comparator = comparator.thenComparingInt(person -> person.weight);
        return comparator.compare(p1, this);
    }
}
```
If we remove `implements Comparable<Person>` we would get compile time error, cause sort expects list of T extends Comparable. 
If we change `List<Person> people` => `List people`, code will compile but throw `java.lang.ClassCastException: class com.java.test.Person cannot be cast to class java.lang.Comparable`.

###### Primitive streams
If you want to work with stream use `Stream`. If you want primitive type streams use one of these
`IntStream`
`LongStream`
`DoubleStream`

To convert use function `mapToObj`, `mapToInt`, `mapToDouble`, `mapToLong`.
```java
import java.util.stream.*;

public class App {
    public static void main(String[] args) {
        // We can convert IntStream to Stream in 2 ways
        Stream<Integer> stream = IntStream.range(1, 10).mapToObj(Integer::valueOf);
        Stream<Integer> stream2 = IntStream.range(1, 10).boxed();

        // following 2 lines are equivalent. Due to type erasure we still have to pass ToIntFunction to convert int to int
        IntStream is1 = Stream.of(1,2,3).mapToInt(i->i);
        IntStream is2 = Stream.of(1,2,3).mapToInt(Integer::intValue);
    }
}
```
Streams are lazy loading, they start to execute when terminate operation called, that's why if you create stream from source and then changed source, when you call stream terminal operation it would be called on modified source. Also called late binding.
```java
import java.util.*;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a","b"));
        Stream<String> stream = list.stream();
        list.add("c");
        stream.forEach(System.out::print);
    }
}
```
```
abc
```
We can also get statistics on primitive streams. `getSum()` - returns long for int and long, double - for double
```java
import java.util.*;
import java.util.stream.*;

public class App{
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(3, 5, 1, 2, 4));
        IntStream stream = list.stream().mapToInt(k -> k);
        IntSummaryStatistics statistics = stream.summaryStatistics();
        System.out.println("statistics => " + statistics);
        System.out.println("stream range (diff between max and min) is => " + (statistics.getMax() - statistics.getMin()));
        System.out.println("average => " + statistics.getAverage()); //double
        System.out.println("sum => " + statistics.getSum()); //long
        System.out.println("count => " + statistics.getCount()); //long
    }
}
```
```
statistics => IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}
stream range (diff between max and min) is => 4
average => 3.0
sum => 15
count => 5
```
Pay attention, that this method, sort values automatically. We also have such classes for long and double `LongSummaryStatistics` and `DoubleSummaryStatistics`. We can work with optional as with streams. Suppose we want to print Integer if it only 3 digits.
```java
import java.util.*;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Optional<Integer> optional = Optional.of(101);
        optional
                .map(n -> "" + n)
                .filter(s -> s.length() == 3)
                .ifPresent(System.out::println);

        /**
         * If you pass null to Optional.of you will get NullPointerException
         * for null-safe operation use Optional.ofNullable
         */
        Integer i = null;
        try{
            Optional<Integer> op1 = Optional.of(i); // throws NullPointerException
        } catch (NullPointerException ex){
            System.out.println("Optional.of(null) => " + ex);
        }
        Optional<Integer> op2 = Optional.ofNullable(i);

        // same true for stream
        List<String> list = null;
        Stream.of(i).forEach(System.out::println);
        Stream.ofNullable(i).forEach(System.out::println);
    }
}
```
```
101
Optional.of(null) => java.lang.NullPointerException
null
```

We can check if elements in stream matching a specific criteria by using one of these 3 methods. Pay attention that all of them are short-circuiting and take predicate as param:
* `anyMatch` - true if you find any object that match. Once found match - stop searching, return true.
* `allMatch` - true if all items are match. Once found non-match - stop searching, return false, otherwise go to the end and return true.
* `noneMatch` - true if none of the items are match. Once found match - stop searching, return false, otherwise go to end return true. 
```java
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args){
        /**
         * We have 2 versions of iterate, in second we can pass predicate as second param, otherwise iterate would run forever
         */
        Stream.iterate(0, v->v+1).limit(10).forEach(System.out::print);
        System.out.println();
        Stream.iterate(0, v->v<10, v->v+1).forEach(System.out::print);
        /**
         * If we have some custom type (supplier) and need generate infinite
         * stream, like stream of guids, we can use generate for this
         */
        System.out.println();
        Stream.generate(UUID::randomUUID).limit(3).forEach(System.out::println);
        System.out.println();


        Predicate<? super String> predicate = s -> s.length() > 1;
        boolean anyMatch = Stream.of("a","bb","ccc","dddd").peek(System.out::println).anyMatch(predicate);
        System.out.println("anyMatch => " + anyMatch);
        boolean allMatch = Stream.of("a","bb","ccc","dddd").peek(System.out::println).allMatch(predicate);
        System.out.println("allMatch => " + allMatch);
        boolean noneMatch = Stream.of("a","bb","ccc","dddd").peek(System.out::println).noneMatch(predicate);
        System.out.println("noneMatch => " + noneMatch);
    }
}
```
```
0123456789
0123456789
440a348b-b741-4d4f-b64b-f50025de8a39
ab48bf85-ba8e-466d-9f87-8968cad3f3e2
e27383fc-a36d-45bf-ad46-c1689cd2124b

a
bb
anyMatch => true
a
allMatch => false
a
bb
noneMatch => false
```
Be careful with infinite streams. If infinite streams all match, then `allMatch` will hang.

If we have list of lists, we can turn it into list using `flatMap` function
```java
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args){
        List<List<String>> listOfLists = new ArrayList<>(List.of(new ArrayList<>(List.of("a")), new ArrayList<>(List.of("a", "b")), new ArrayList<>(List.of("a","b","c"))));
        List<String> list = listOfLists.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(list);
    }
}
```
```
[a, a, b, a, b, c]
```

`findAny` and `findFirst` return the same element for single-threaded stream, but in parallel, `findFirst` return 1st element, while `findAny` return random element.
```java
public class App {
    public static void main(String[] args) {
        List.of(5, 4, 3, 2, 1)
            .stream()
            .parallel()
            .sorted()
            .findFirst()
            .ifPresent(System.out::println);

        List.of(5, 4, 3, 2, 1)
            .stream()
            .parallel()
            .sorted()
            .findAny()
            .ifPresent(System.out::println);
    }
}
```
```
1
3
```
As you see, although we have sorted array, `findAny` just return random number, but `findFirst` return first element of sorted array.

By default, stream `reduce` returns the same type, if you want to change it, you have to use version of `reduce` with 3 arguments.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Jack", "John", "Melanie", "David"));
        // standard reduce
        String joining = names.stream().reduce("", (acc, el) -> acc + el);
        System.out.println("joining => " + joining);

        // in case we don't set identity value, we got optional
        Optional<String> optionalJoining = names.stream().reduce((acc, el) -> acc + el);
        System.out.println("optionalJoining => " + optionalJoining);

        // type changing reduce, we should use reduce with 3 params, pay attention that third param is never called
        // so we can replace it with (a,b)->0
        // yet in parallel execution we need combiner, that's why it's used here, in single-threaded env you don't need it,
        // but in parallel, many chunks need to be combined, so it used there. And if you use (a,b)->0 in parallel stream you get wrong results
        Integer sum = names.stream().reduce(0, (acc, el) -> acc + el.length(), (a, b) -> a + b);
        System.out.println("sum => " + sum);

        // type changing reduce (parallel stream)
        Integer parallelSum = names.parallelStream().reduce(0,(acc, el) -> acc + el.length(), (a, b) -> a + b);
        System.out.println("parallelSum => " + parallelSum);
    }
}
```
```
joining => JackJohnMelanieDavid
optionalJoining => Optional[JackJohnMelanieDavid]
sum => 20
parallelSum => 20
```
We need for parallel execution third function (combiner), that will combine partial results into one. We don't need it for sequential execution, but because streams designed in such a way that both sequential and parallel executions should work the same way, 
compiler force us to use combiner for sequential execution, although it's never called. That's why for sequential you can change combiner to `(a, b) => 0`, and it would work fine, but if you change it for parallel you will got 0 as result.

###### Parallel streams
By default, for `parallelStream` terminate operator - `forEach` show values in undetermined order, cause we can’t control in which order they were executed. If we want to get initial order we can use `forEachOrdered`.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        long start;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        start = System.currentTimeMillis();
        list.stream().map(App::process).forEach(System.out::print);
        System.out.println("\nsequential time => " + (System.currentTimeMillis()-start)/1000);
        start = System.currentTimeMillis();
        list.parallelStream().map(App::process).forEach(System.out::print);
        System.out.println("\nparallel time => " + (System.currentTimeMillis()-start)/1000);
        start = System.currentTimeMillis();
        list.parallelStream().map(App::process).sequential().forEach(System.out::print);
        System.out.println("\nparallel time (sequential) => " + (System.currentTimeMillis()-start)/1000);
        start = System.currentTimeMillis();
        list.parallelStream().map(App::process).forEachOrdered(System.out::print);
        System.out.println("\nparallel time (ordered) => " + (System.currentTimeMillis()-start)/1000);
    }
    private static int process(int i){
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex);
        }
        return i;
    }
}
```
```
0123456789
sequential time => 5
6584271390
parallel time => 1
0123456789
parallel time (sequential) => 5
0123456789
parallel time (ordered) => 1
```
Take note, that although we terminate in ordered manner it takes the same time of 1 sec to finish.
Also when we transform stream from parallel to sequential all stream would be sequential. The reason is that stream parallel/sequential is just boolean field inside stream, so it doesn't matter where you put parallel/sequential,
at the beginning or before terminal, whole stream would work either parallel or sequential.

###### Collectors
`Collectors` - list of functions that terminate streams and allow you to transform your stream into string, list, set, map. `Stream.collect` take `Collector` interface as input parameter. There is no `.toArray` collector, but you can collect toList, and then call `toArray()` from stream.
So almost all methods in `Collectors` class that we pass into `collect` return `Collector` object. Generally there should be enough methods in `Collectors` class, but if you need you can create our own by implementing this interface.
`Collector/Collection` - interfaces, `Collectors/Collections` - concrete classes with many static factories to solve common problems.

Collectors.mapping takes a function and another collector, and creates a new collector which first applies the function and then collects the mapped elements using the given collectors. Thus, the following are equivalent:
```
items.stream().map(f).collect(c);
items.stream().collect(Collectors.mapping(f, c));
```
Collectors.mapping is most useful in situations where you do not have a stream, but you need to pass a collector directly. An example of such a situation is when using `Collectors.groupingBy`:
* `items.stream().collect(Collectors.groupingBy(Item::getPrice, Collectors.toSet()))` returns a `Map<BigDecimal, Set<Item>>` 
* `items.stream().collect(Collectors.groupingBy(Item::getPrice, Collectors.mapping(Item::getName, Collectors.toSet())))` returns a `Map<BigDecimal, Set<String>>`, (before collecting the items, it first applies `getName` to them)

This is the case with `Collectors.filtering`
```java
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

public class App{
    public static void main(String[] args) {
        Predicate<String> p = s -> s.length() >= 2;
        var f = Collectors.groupingBy(String::length);
        List<String> list = new ArrayList<>(List.of("a","bb","cc","aaa"));
        Map<Integer, List<String>> filterMap = list.stream().filter(p).collect(f);
        System.out.println("filterMap => " + filterMap);
        Map<Integer, List<String>> filteringMap = list.stream().collect(Collectors.filtering(p, f));
        System.out.println("filteringMap => " + filteringMap);
    }
}
```
```
filterMap => {2=[bb, cc], 3=[aaa]}
filteringMap => {2=[bb, cc], 3=[aaa]}
```
The same is true for all other repeated collectors like `minBy,maxBy,counting,mapping,filtering,averagingInt/Long/Double,summingInt/Long/Double` and so on.
```java
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<String> list = List.of("Jack", "John", "Janet");

        // stringify with `joining()`
        String names = list
            .stream()
            .collect(Collectors.joining(","));
        System.out.println("stringify => " + names);

        // same can be accomplished with String.join
        String namesJoin = String.join(", ", list);
        System.out.println("namesJoin => " + namesJoin);


        //We can calculate average length of names
        double averageNameLength = list.
            stream()
            .collect(Collectors.averagingInt(String::length));
        System.out.println("averageNameLength => " + averageNameLength);
    
        // save can be accomplished by mapping to primitive stream, and calling average(). In this case return is OptionalDouble
        OptionalDouble averageNameLengthPrimitive = list.
            stream()
            .mapToInt(String::length)
            .average();
        System.out.println("averageNameLengthPrimitive => " + averageNameLengthPrimitive);


        // we can also use averagingInt/Long/Double to get convert stream to int/long/double and get average
        double averagingInt = Stream.of("a","bb","ccc","dddd").collect(Collectors.averagingDouble(v->v.length()));
        System.out.println("averagingInt => " + " " +averagingInt);

        /**
          * average method exists only in Int/Long/DoubleStream and return OptionalDouble
          * in Stream we have 3 collectors averagingInt/Long/Double, that takes ToInt/Long/DoubleFunction and return double
          */
        IntStream intStream = IntStream.range(0, 5);
        OptionalDouble optionalDouble = intStream.average();
        System.out.println(optionalDouble + " " + optionalDouble.getAsDouble());

        //if we have duplicates we need third function to merge them, otherwise we will get (IllegalStateException: Duplicate key a)
        Map<String, Integer> duplicates = Stream
            .of("a","b","c","a","b","a")
            .collect(Collectors.toMap(s -> s, s->s.length(), (s1, s2)-> s1+ s2));
        System.out.println("duplicates => " + duplicates);


        // We can do the other way around, a `Map<Integer, String>`. But doing so would throw `IllegalStateException`, cause 2 names have the same key (4 is the length for both Jack and John). In such a case, we should pass third function, where we write what to do. For example we can concatenate such names.
        Map<Integer, String> invertedMap = list
            .stream()
            .collect(Collectors.toMap(s -> s.length(), s -> s, (s1, s2) -> s1 + ", " + s2));
        System.out.println("invertedMap => " + invertedMap);


        //Suppose we want to preserve order. For this we have to use `LinkedHashMap`. By default `toMap` return `HashMap`, but we can pass the fourth argument to `toMap` function.
        Map<Integer, String> invertedLinkedMap = list
            .stream()
            .collect(Collectors.toMap(s -> s.length(), s -> s, (s1, s2) -> s1 + ", " + s2, LinkedHashMap::new));
        System.out.println("invertedLinkedMap => " + invertedLinkedMap);

        System.out.println();

        //Use `groupingBy` if you want to group items by some criteria.
        Map<Integer, List<String>> groupedByNameLength = list
            .stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println("groupedByNameLength => " + groupedByNameLength);

        /**
         * partitioningBy - special case of groupBy => creates map with Boolean as key
         * and with only 2 values, true => those that satisfy predicate, false => all other
         */
        Map<Boolean, List<String>> partitionByNameLength = list
            .stream()
            .collect(Collectors.partitioningBy(s->s.length()<5));
        System.out.println("partitionByNameLength => " + partitionByNameLength);


        //when we need to return same value, instead of v->v we can use Function.identity()
        Map<String, Integer> mapWithNameLengthIdentity = list
            .stream()
            .collect(Collectors.toMap(Function.identity(), s -> s.length()));
        System.out.println("mapWithNameLengthIdentity => " + mapWithNameLengthIdentity);

        Map<Integer, Long> groupedByNameLengthCont = list
            .stream()
            .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("groupedByNameLengthCont => " + groupedByNameLengthCont);

        Map<Integer, Long> groupedByNameLengthContLinkedHashMap = list
            .stream()
            .collect(Collectors.groupingBy(String::length, LinkedHashMap::new, Collectors.counting()));
        System.out.println("groupedByNameLengthContLinkedHashMap => " + groupedByNameLengthContLinkedHashMap);


        System.out.println();
        List<Person> people = new ArrayList<>(List.of(
            new Person("Jack", 30),
            new Person("John", 20),
            new Person("Mike", 30),
            new Person("Jenifer", 20),
            new Person("Jack", 30)
        ));

        /**
         * toMap and groupingBy very similar, both return new map. But there is a general rule
         * To collect into a Map that contains a single value by key (Map<MyKey, MyObject>), use Collectors.toMap().
         * To collect into a Map that contains list/set/map value by key (Map<MyKey, List<MyObject>>), use Collectors.groupingBy().
         */
        // default grouping return list of objects as value of map
        Map<Integer, List<Person>> groupByAge = people.stream().collect(Collectors.groupingBy(k->k.age));
        System.out.println("groupByAge => " + groupByAge);
        // we can change value by supplying custom collector
        Map<Integer, List<String>> groupNamesByAge = people.stream().collect(Collectors.groupingBy(k->k.age, Collectors.mapping(p->p.name, Collectors.toList())));
        System.out.println("groupNamesByAge => " + groupNamesByAge);

        /**
        * You can use toMap for multiple values, but code would be clunky
        * especially merge function, you should accumulate values into list and retun it (there is no way to do it single line)
        */
        Map<Integer, List<Person>> toMapByAge = people.stream()
                    .collect(Collectors.toMap(k->k.age, 
                            v->new ArrayList<>(List.of(v)), 
                            (v, acc)->{
                                acc.addAll(v);
                                return acc;
                            }));
        System.out.println("toMapByAge => " + toMapByAge);
        Map<Integer, List<Person>> groupByAgeCustomCollector = people.stream().collect(HashMap::new, (map, s) -> {
            map.merge(s.age, new ArrayList<>(List.of(s)), (acc, v)->{
                acc.addAll(v);
                return acc;
            });
        }, (a, b) -> {
            // collect data here when stream is parallel
        });
        System.out.println("groupByAgeCustomCollector => " + groupByAgeCustomCollector);

        System.out.println();
        // min & max await comparator as input param and return Optional
        Optional<Integer> max = List.of(1,2,3,4,5).stream().max((a, b)->a-b);
        System.out.println("max => " + max);
        /**
         * Pay attention that min/max expect comparator, if you pass other function you get wrong result
         * In this case Integer::max -> always return max, so it would be 2,3,4,5. But since comparator work like this
         * if result >=0 return first, otherwise return second, for all iteration it always return first value
         * So 1 and 2 => return 1
         * 1 and 3 => return 1 and so on..., until the end
         * That's why result is 1
         */
         Optional<Integer> wrongMax = List.of(1,2,3,4,5).stream().max(Integer::max);
         System.out.println("wrongMax => " + wrongMax);
        Optional<Integer> min = List.of(1,2,3,4,5).stream().max((a,b)->b-a);
        System.out.println("min => " + min);
        // we can achieve min&max with reduce
        Optional<Integer> maxReduce = List.of(1,2,3,4,5).stream().reduce((a,b)->a>b?a:b);
        System.out.println("maxReduce => " + maxReduce);
        // we can get min&max from Int/Long/DoubleStream, it doesn't need comparator, but return OptionalInt/Long/Double
        OptionalInt intStreamMax = Stream.of(1,2,3,4,5).mapToInt(a->a).max();
        System.out.println("longStreamMax => " + intStreamMax +", value =>" + intStreamMax.getAsInt());
        OptionalDouble doubleStreamMax = Stream.of(1,2,3).mapToDouble(d->d).max();
        System.out.println("longStreamMax => " + doubleStreamMax +", value =>" + doubleStreamMax.getAsDouble());
        OptionalLong longStreamMax = Stream.of(1,2,3).mapToLong(l->l).max();
        System.out.println("longStreamMax => " + longStreamMax +", value =>" + longStreamMax.getAsLong());

        System.out.println();
        // find number of letters before letter c. Both methods return long
        long count = List.of("a","b","aa","c","x").stream().filter(s->s.compareTo("c")<0).count();
        System.out.println("count => " + count);
        long counting = List.of("a","b","aa","c","x").stream().filter(s->s.compareTo("c")<0).collect(Collectors.counting());
        System.out.println("counting => " + counting);

        // both Stream and primitive Streams has distinct that filters duplicates
        long streamDistinct = Stream.of(1,2,3,1,2).distinct().count();
        System.out.println("streamDistinct => " + streamDistinct);
        long primitiveStreamDistinct = IntStream.of(1,2,3,1,2).distinct().count();
        System.out.println("primitiveStreamDistinct => " + primitiveStreamDistinct);


        /**
         * Both Stream and primitive streams have sorted(). Stream additionally has sorted(Comparator c)
         * primitive streams has only one version of collect collect(Supplier<R> var1, ObjIntConsumer<R> var2, BiConsumer<R, R> var3)
         * so you can't pass collector there
         */
        System.out.println();
        List<Integer> sortedList = IntStream.of(5,4,3,2,1).sorted().collect(ArrayList::new, ArrayList::add, (a, b)->{});
        System.out.println("sortedList => " + sortedList);
        List<Integer> streamSortedList = Stream.of(5,4,3,2,1).sorted().collect(Collectors.toList());
        System.out.println("streamSortedList => " + streamSortedList);
        List<Integer> streamSortedReversedList = Stream.of(5,4,3,2,1).sorted((a,b)->b-a).collect(Collectors.toList());
        System.out.println("streamSortedReversedList => " + streamSortedReversedList);

        // there are 2 ways we can transform between primitive streams. If we don't want to map we can use asInt/Long/DoubleStream if we want mapping use mapToInt/Long/Double
        DoubleStream ds = IntStream.of(1,2,3).asDoubleStream();
        DoubleStream ds2 = IntStream.of(1,2,3).mapToDouble((int i)->i/3);

        /**
         * There is no function sum() on Stream, but you can use Collectors.summingInt to get sum (0 if stream is empty)
         * and Collectors.summarizingInt to get IntSummaryStatistics. These functions exists also for long and double
         * For primitive streams we have sum terminal operation that return int/long/double and summaryStatistics that return Int/Long/DoubleSummaryStatistics
         * If stream is empty sum return int/long/double
         * For Stream we can use reduce operation or convert it into Int/Long/DoubleStream and call sum
         */
        System.out.println();
        int intStreamSum = IntStream.of(1,2,3).sum();
        System.out.println("intStreamSum => " + intStreamSum);
        long longStreamSum = LongStream.of(1,2,3).sum();
        System.out.println("longStreamSum => " + longStreamSum);
        double doubleStreamSum = DoubleStream.of(1,2,3).sum();
        System.out.println("doubleStreamSum => " + doubleStreamSum);
        int streamSum = Stream.of(1,2,3).reduce(0,(acc, v)->acc+v);
        System.out.println("streamSum => " + streamSum);

        Integer summingInt = Stream.of(1,2,3).collect(Collectors.summingInt(x->(Integer)x));
        System.out.println("summingInt => " + summingInt);
        IntSummaryStatistics summarizingInt = Stream.of(1,2,3).collect(Collectors.summarizingInt(x->(Integer)x));
        System.out.println("summarizingInt => " + summarizingInt);
        System.out.println("summarizingInt => " + summarizingInt);


    }
}
class Person{
    public String name;
    public int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString(){
        return name+":"+age;
    }
}
```
```
stringify => Jack,John,Janet
averageNameLength => 4.333333333333333
averagingInt =>  2.5
OptionalDouble[2.0] 2.0
duplicates => {a=3, b=2, c=1}
invertedMap => {4=Jack, John, 5=Janet}
invertedLinkedMap => {4=Jack, John, 5=Janet}

groupedByNameLength => {4=[Jack, John], 5=[Janet]}
partitionByNameLength => {false=[Janet], true=[Jack, John]}
mapWithNameLengthIdentity => {Janet=5, John=4, Jack=4}
groupedByNameLengthCont => {4=2, 5=1}
groupedByNameLengthContLinkedHashMap => {4=2, 5=1}

groupByAge => {20=[John:20, Jenifer:20], 30=[Jack:30, Mike:30, Jack:30]}
groupNamesByAge => {20=[John, Jenifer], 30=[Jack, Mike, Jack]}
toMapByAge => {20=[John:20, Jenifer:20], 30=[Jack:30, Mike:30, Jack:30]}
groupByAgeCustomCollector => {20=[John:20, Jenifer:20], 30=[Jack:30, Mike:30, Jack:30]}

max => Optional[5]
min => Optional[1]
maxReduce => Optional[5]
longStreamMax => OptionalInt[5], value =>5
longStreamMax => OptionalDouble[3.0], value =>3.0
longStreamMax => OptionalLong[3], value =>3

count => 3
counting => 3
streamDistinct => 3
primitiveStreamDistinct => 3

sortedList => [1, 2, 3, 4, 5]
streamSortedList => [1, 2, 3, 4, 5]
streamSortedReversedList => [5, 4, 3, 2, 1]

intStreamSum => 6
longStreamSum => 6
doubleStreamSum => 6.0
streamSum => 6
```

By default `filter` is stateless. And if we want to filter by distinct values, we need to have state. Fortunately we have `distinct()` function. But what if we want filter only those that are met exactly 3 times. For this we need custom filter.
```java
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Stream.of(1,2,3,1,2,1).filter(distinct(3)).forEach(System.out::println);
    }

    /**
     * This function is typical example of closure
     * For every filter operation it creates new closure with ConcurrentHashMap variable in it
     */
    private static <T> Predicate<T> distinct(int depth){
        Map<T, Integer> map = new ConcurrentHashMap<>();
        return v->{
            map.merge(v, 1, (a,b)->a+b);
            return map.get(v) == depth;
        };
    }
}
```
```
1
```

Beware that `min/max` take comparator as param. So if you put some other operation you will get wrong result. Inside `max` calling `BinaryOperator.maxBy`, that checks if result is greater or equal to 0, return first otherwise return second.
```java
import java.util.*;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Optional<Integer> max1 = Stream.of(1,2,3,4,5).max(Math::max);
        Optional<Integer> max2 = Stream.of(1,2,3,4,5).max(Comparator.naturalOrder());
        System.out.println("max1 => " + max1);
        System.out.println("max2 => " + max2);
        Stream.of(1,2,3,4,5).max((a,b)->{
            System.out.println(a+" "+b);
            return Math.max(a,b);
        });
    }
}
```
```
max1 => Optional[1]
max2 => Optional[5]
1 2
1 3
1 4
1 5
```


#### Concurrency
###### Threads
Constructor is thread-safe, and it's guranteed by JVM.
Extending `Thread` vs implementing `Runnable`. Generally it's better to implement interface, cause you have to override one method `run` to run it. If you extend from `Thread`, but don't implement `run`, and then start new thread, nothing would be executed.
```java
public class App {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new MyThread();
        t1.start();
        t2.start();
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run(){
        System.out.println("MyRunnable => " + Thread.currentThread().getName());
    }
}
class MyThread extends Thread{
    public void run(){
        System.out.println("MyThread => " + Thread.currentThread().getName());
    }
}
```
```
MyThread => Thread-1
MyRunnable => Thread-0
```
There are a few ways to handle error inside thread:
* we can set exception handler for thread using `setUncaughtExceptionHandler` method
* when we call `get` it usually throws 2 errors `InterruptedException` and `ExecutionException` - so this is our common exception for any exception inside thread 
```java
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class App {
    public static void main(String[] args)  {
        Thread thread = new Thread(() -> {
            System.out.println("start thread");
            throw new RuntimeException("oops");
        });
        thread.setUncaughtExceptionHandler((th, ex) -> {
            System.out.println(th + " " + ex);
        });
        thread.start();
        // wait until this thread finished
        try{
            thread.join();
        } catch (InterruptedException ex){
            System.out.println("Failed to wait thread completion");
        }
        System.out.println();

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("futureTask start");
            // since we return value, we should use this hack, otherwise we get compile error: unreachable statement
            if (true) {
                throw new RuntimeException("oops");
            }
            return "done";
        });
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println("futureTask result: " + result);
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println("futureTask: " + ex);
        }
    }
}
```
```
start thread
Thread[Thread-0,5,main] java.lang.RuntimeException: oops

futureTask start
futureTask: java.util.concurrent.ExecutionException: java.lang.RuntimeException: oops
```
By default `Thread` takes `Runnable`, but we can also pass `Callable` wrapped into `FutureTask`, cause `FutureTask(class)=>RunnableFuture(interface)=>Runnable`. `Future` - interface returned by ExecutorService `submit,invoke` methods, `FutureTask` - concrete task. You can pass it into new Thread or ExecutorService submit/invoke methods.
Java doesn’t wait for daemon threads, only for normal threads. To make a thread a daemon just set it property to true `setDaemon(true)`. Below code will exit immediately, but if you comment out `daemon=true`, then app would never exit, cause thread will run forever. This is similar to `detach`, so when you set `daemon=true` you kind of detach your thread from main app (it would run for some time as your main app runs, but main process won't wait for such thread). Yet `thread::detach` doesn't exist in java, so once you set `daemon=true`, if your main process stops, all daemon threads would be killed
So there is no way in JVM to create thread that would continue to run while your main app is dead (yet you can start new JVM with new PID from your app)
Keep in mind if you call `.join` on the daemon thread, your app would wait for such thread (in our case forever, cause of `while(true)`)
```java
import java.lang.management.ManagementFactory;

public class App {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            int i = 0;
            while(true){
                System.out.println(Thread.currentThread() + " => " + i);
                sleep(1);
                i++;
            }
        });
        System.out.println("Start app: PID="+ManagementFactory.getRuntimeMXBean().getName());
        thread.setDaemon(true);
        thread.start();
        System.out.println("done");
    }
    private static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex);
        }
    }
}
```
You can set thread priority with `setPriority` method. Yet it doesn't guarantee to run, cause in the end it's OS who schedule threads.
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Thread t1 = new Thread(()->{
            System.out.println("t1 starts");
            sleep(2000);
            System.out.println("t1 finished");
        });
        Thread t2 = new Thread(()->{
            System.out.println("t2 starts");
            sleep(3000);
            System.out.println("t2 finished");
        });

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        service.submit(t1);
        service.submit(t2);
    }
    public static void sleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
```
```
t1 starts
t2 starts
t1 finished
t2 finished
```
Although t2 has higher priority, `ExecutorService` still runs t1 first. Thread priority does not guarantee execution order. It comes into play when resources are limited. If the System is running into constraints due to memory or CPU, then the higher priority threads will run first.
Yet if you try to set priority more than max (10) you will get this error. Priority should be in this interval `1-10`
```
Exception in thread "main" java.lang.IllegalArgumentException
	at java.base/java.lang.Thread.setPriority(Thread.java:1141)
	at com.java.test.App.main(App.java:21)
```
Don't confuse:
* `Thread.yield()` - suspends current thread and gives way to another. As with `setPriority` it doesn't guarantee to execute. Since it's not guaranteed, and it behaves differently for windows/linux, you should avoid using it , yield just suspend thread and notify OS scheduler that it would like to resume ASAP, hence it's non-deterministic, while sleep, always suspend thread for specified amount of time. That's wy you should never use it, cause it behaviour non-deterministic and can't be used correctly
* `Thread.onSpinWait()` - useful inside `while(!get()){}` blocking types. Empty while called spin wait (cause it spinning processor millions of time per sec). Adding this inside `while` would notify that processor shouldn't spin that fast, so saving cycles & electricity
* `Thread.sleep(1000)` - force scheduler to suspend execution of current thread for specified amount of time (compare to yield which would suspend for brief moment, but ask scheduler to resume it ASAP)
* wait - can be invoked only inside `synchronized` block, can be awakened by calling `notify/notifyAll`
* join - call on other thread and make current thread wait until other thread finish execution
    * you can run new thread with `start`, but if you want current thread to wait until new thread finished you call `join`
Don’t confuse:
* `run` - run in the current thread
* `start` - run in another thread
```java
class App {
  public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      System.out.println("thread1 start");
      sleep(1);
      System.out.println("thread1 finish");
    });
    Thread thread2 = new Thread(() -> {
      System.out.println("thread2 start");
      sleep(1);
      System.out.println("thread2 finish");
    });
    System.out.println("__start__");
    thread.run();
    thread2.run();
    System.out.println("__finish_");
  }

  private static void sleep(long seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException ex) {
      System.out.println("sleep ERR: " + ex);
    }
  }
}
```
As you see, threads were executed sequentially.
```
__start__
thread1 start
thread1 finish
thread2 start
thread2 finish
__finish_
```
There is no way to interrupt a thread, until thread itself has some logic to handle interruption. What you can do is to call `thread.interrupt` to send interrupt event to thread. Interrupted thread should have logic for checking `.interrupted()` and make some action on it. 
Blocking methods like `Object.wait` or `Thread.sleep` has default implementation of interruption (that's why they throw `InterruptedException`).
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(() -> {
            System.out.println("start " + Thread.currentThread());
            sleep(3);
        });
        service.shutdownNow();
        System.out.println("done " + Thread.currentThread());
    }
    private static void sleep(long sec) {
        try {
            System.out.println("sleep " + Thread.currentThread());
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex + " " + Thread.currentThread());
        }
    }
}
```
```
done Thread[main,5,main]
start Thread[pool-1-thread-1,5,main]
sleep Thread[pool-1-thread-1,5,main]
sleep ERR: java.lang.InterruptedException: sleep interrupted Thread[pool-1-thread-1,5,main]
```
Pay attention that `interrupt` works only if we have code that waits and catch for `InterruptedException`, if we don't have such code and have some heavy computation, it won't interrupt, but will go on until done
```java
public class App {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("start thread");
            for(long i = 0; i < 100_000_000_000L; i++){}
            System.out.println("done thread");
        });
        thread.start();
        thread.interrupt();
        System.out.println("finish");
    }
}
```
As you see after calling `interrupt` thread still goes on.
```
finish
start thread
done thread
```
Here we are explicitly have code that check interrupted variable.
```java
public class App {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("start thread");
            for(long i = 0; i < 100_000_000_000L; i++){
                // fired only once (clears the status of the current thread). If we remove break, thread will continue, interrupted - would be false
                if(Thread.interrupted()) {
                    System.out.println("interrupted on => " + i);
                    break;
                }
            }
            System.out.println("done thread");
        });
        thread.start();
        // wait some time before interruption
        for(long i = 0; i < 1_000_000_000L; i++){}
        thread.interrupt();
        // check the flag on Thread instance
        System.out.println("isInterrupted => " + thread.isInterrupted());
        System.out.println("finish");
    }
}
```
```
start thread
isInterrupted => true
finish
interrupted on => 275567377
done thread
```

###### ExecutorService
Has 3 versions of submit:
* `submit(Runnable)` - take nothing, return `Future<?>`
* `submit(Runnable, T)` - return `Future<T>` when done
* `submit(Callable<T>)` - return `Future<T>` from callable
```java
import java.util.concurrent.*;

public class App {
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("MyRunnable start");
            sleep(1);
            System.out.println("MyRunnable finish");
        }
    }
    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("MyCallable start");
            sleep(1);
            System.out.println("MyCallable finish");
            return "MyCallable done";
        }
    }
    public static void main(String[] args) throws Exception {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        Future<?> future1 = service.submit(new MyRunnable());
        Future<String> future2 = service.submit(new MyRunnable(), "MyRunnable done");
        Future<String> future3 = service.submit(new MyCallable());
        System.out.println("future1 => " + future1.get());
        System.out.println("future2 => " + future2.get());
        System.out.println("future3 => " + future3.get());
        service.shutdown();
    }
    private static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex);
        }
    }
}
```
```
MyRunnable start
MyRunnable start
MyCallable start
MyRunnable finish
MyRunnable finish
MyCallable finish
future1 => null
future2 => MyRunnable done
future3 => MyCallable done
```
`invokeAll` and `invokeAny` takes only callables and return results. The difference is that `invokeAll` wait until all threads are executed and return ordered list (order in which initial list of callables was passed) as array of Futures. `invokeAny` - run all threads, but wait only the first one and return it (not future) and terminates all unfinished threads.
```java
import java.util.*;
import java.util.concurrent.*;

public class App {
    static class MyCallable implements Callable<String> {
        private int number;
        public MyCallable(int number) {
            this.number = number;
        }
        @Override
        public String call() throws Exception {
            System.out.println("MyCallable number " + number + " start");
            sleep(number);
            System.out.println("MyCallable number " + number + " finish");
            return "MyCallable number " + number + " done";
        }
    }
    public static void main(String[] args) throws Exception {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            callables.add(new MyCallable(i));
        }
        System.out.println("invokeAll => ");
        List<Future<String>> invokeAll = service.invokeAll(callables);
        invokeAll.forEach(future -> {
            try {
                System.out.println("result: " + future.get());
            } catch (InterruptedException | ExecutionException ex) {
              System.out.println("ex: " + ex);
            }
        });
        System.out.println("\ninvokeAny => ");
        String value = service.invokeAny(callables);
        System.out.println("result: " + value);

        service.shutdown();
    }
    private static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex);
        }
    }
}
```
Take note, that `invokeAny` terminates(send interrupt) all unfinished threads once anyone is finish.
```
invokeAll => 
MyCallable number 1 start
MyCallable number 3 start
MyCallable number 2 start
MyCallable number 1 finish
MyCallable number 2 finish
MyCallable number 3 finish
result: MyCallable number 1 done
result: MyCallable number 2 done
result: MyCallable number 3 done

invokeAny => 
MyCallable number 1 start
MyCallable number 2 start
MyCallable number 3 start
MyCallable number 1 finish
result: MyCallable number 1 done
sleep ERR: java.lang.InterruptedException: sleep interrupted
MyCallable number 2 finish
sleep ERR: java.lang.InterruptedException: sleep interrupted
MyCallable number 3 finish
```

For scheduling we can use `ScheduledExecutorService` method `schedule` (run once at some point in future). We can pass either `Runnable` or `Callable` to this method.
```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfThreads);
        Runnable runnable = () -> {
            System.out.println("Runnable start " + Thread.currentThread().getName());
            sleep(1);
            System.out.println("Runnable finish " + Thread.currentThread().getName());
        };
        Callable<String> callable = () -> {
            System.out.println("Callable start " + Thread.currentThread().getName());
            sleep(1);
            System.out.println("Callable finish " + Thread.currentThread().getName());
            return "done it";
        };
        ScheduledFuture<?> runnableFuture = service.schedule(runnable, 2, TimeUnit.SECONDS);
        ScheduledFuture<String> callableFuture = service.schedule(callable, 3, TimeUnit.SECONDS);
        try {
            System.out.println("runnableFuture => " + runnableFuture.get());
            System.out.println("callableFuture => " + callableFuture.get());
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println("ERR: " + ex);
        }
        service.shutdown();
    }

    private static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex);
        }
    }
}
```
```
Runnable start pool-1-thread-1
Callable start pool-1-thread-2
Runnable finish pool-1-thread-1
runnableFuture => null
Callable finish pool-1-thread-2
callableFuture => done it
```
Don't confuse:
* `.get()` throws 2 checked exceptions: `InterruptedException` and `ExecutionException`
* we call `.get()` to get results (in case of callable) or if we want to catch exceptions
* if any `run()` methods, throws any exception, we would catch `ExecutionException`
* there is overloaded get(long time, TimeUnit unit) -> throws 3 exceptions InterruptedException, ExecutionException, TimeoutException

We can use `scheduleAtFixedRate` and `scheduleWithFixedDelay` if we want to execute some task endlessly with interval. Take note that both method accept only `Runnable`. And it’s logical, otherwise it would generate endless array of Futures. There is a subtle difference between them.
`scheduleAtFixedRate` - put into queue new task after delay. So if we have 1 sec delay, but task runs 2 seconds, that mean next task will start immediately.
`scheduleWithFixedDelay` - wait delay after previous executed and execute next. So if we have 1 sec delay, but task runs 2 seconds, next task will run after 1 sec.
If we want to schedule something to run once in future we can use `schedule`. There are 2 overloaded methods, one for callable and one for runnable.
```java
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfThreads);
        Runnable runnable = () -> {
            System.out.println("Runnable start " + Thread.currentThread().getName());
            sleep(2);
            System.out.println("Runnable finish " + Thread.currentThread().getName());
        };
        service.scheduleAtFixedRate(runnable, 1000, 100, TimeUnit.MILLISECONDS);
        service.scheduleWithFixedDelay(runnable, 1000, 200, TimeUnit.MILLISECONDS);
        sleep(5);
        service.shutdown();
    }
    private static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex);
        }
    }
}
```
```
Runnable start pool-1-thread-1
Runnable start pool-1-thread-2
Runnable finish pool-1-thread-2
Runnable finish pool-1-thread-1
Runnable start pool-1-thread-2
Runnable start pool-1-thread-3
Runnable finish pool-1-thread-2
Runnable finish pool-1-thread-3
```
If we remove `service.shutdown();` they will run forever.

###### wait/notify and await/signal
`wait/notify` is the old way to build concurrency, but `await/signal` is new one from `java.util.concurrent.locks` package. We can implement BlockingQueue using old and new way
```java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyLowLevelQueue<>();
        new Thread(()->{
            System.out.println("Getting queue value");
            System.out.println("queue value: " + queue.poll());
        }).start();
        sleep(1);
        new Thread(()->{
            int value = 1;
            System.out.println("Putting " + value + " into queue");
            queue.put(value);
        }).start();
        sleep(1);
        MyQueue<Integer> highLevelQueue = new MyHighLevelQueue<>();
        new Thread(()->{
            System.out.println("Getting highLevelQueue value");
            System.out.println("highLevelQueue value: " + highLevelQueue.poll());
        }).start();
        sleep(1);
        new Thread(()->{
            int value = 1;
            System.out.println("Putting " + value + " into highLevelQueue");
            highLevelQueue.put(value);
        }).start();
    }
    private static void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex);
        }
    }
}
interface MyQueue<T>{
    void put(T t);
    T poll();
}
/**
 * We are using low-level constructs wait/notify
 */
class MyLowLevelQueue<T> implements MyQueue<T> {
    private List<T> queue = new ArrayList<>();
    @Override
    public void put(T value) {
        synchronized (queue) {
            queue.add(value);
            queue.notify();
        }
    }
    @Override
    public T poll() {
        synchronized (queue) {
            try {
                if (queue.isEmpty()) {
                    queue.wait();
                }
                return queue.remove(0);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
/**
 * We are using high-level constructs like ReentrantLock/Condition
 * @param <T>
 */
class MyHighLevelQueue<T> implements MyQueue<T> {
    private List<T> queue = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    @Override
    public void put(T value) {
        try {
            lock.lock();
            queue.add(value);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    @Override
    public T poll() {
        try {
            lock.lock();
            if (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove(0);
        } catch (InterruptedException | RuntimeException ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }
}
```
```
Getting queue value
Putting 1 into queue
queue value: 1
Getting highLevelQueue value
Putting 1 into highLevelQueue
highLevelQueue value: 1
```
It’s better to use new API, cause you can set additional methods like `awaitUntil` - wait for some time and `awaitUninterruptibly` - to wait until signal, even if someone interrupted process.
`lockInterruptibly` will throw `lockInterruptibly`, while simple `lock` can be interrupted, but won't throw exception.
`SynchronousQueue` - special type of queue with one element. put - is waiting until take is done. Useful when you have 2 threads that need to change data and continue.
```java
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        Thread t1 = new Thread(()->{
            try{
                Thread.sleep(100);
                System.out.println("putting element... " + Thread.currentThread().getName());
                queue.put(100);
                System.out.println("put done " + Thread.currentThread().getName());
            } catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        });
        Thread t2 = new Thread(()->{
            try{
                System.out.println("taking element...");
                Thread.sleep(2000);
                System.out.println("put done " + queue.take() + " " + Thread.currentThread().getName());
            } catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        });
        t1.start();
        t2.start();
    }
}
```
```
taking element...
putting element... Thread-0
put done Thread-0
put done 100 Thread-1
```

###### fork/join framework
The `fork/join` framework famous for it work stealing - it is specifically design to tackle recursive algorithms. Although you can write you own recursive traverse (which would definitely be prone to error) and use ThreadPool, `fork/join` has such logic out of the box.
```java
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class App {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        var task = new CountRecursiveTask(4, 0, 20);
        int count = pool.invoke(task);
        pool.shutdown();
        System.out.println("CountRecursiveTask: " + count);
    }
    public static int calc(int value) {
        System.out.println("value: " + value + ", thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(value * 1000);
        } catch (InterruptedException ex) {
            System.out.println("calc ERR: " + ex);
        }
        return value;
    }
}
class CountRecursiveTask extends RecursiveTask<Integer> {
    private int min;
    private int start;
    private int end;
    public CountRecursiveTask(int min, int start, int end) {
        this.min = min;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int count;
        int diff = end - start;
        if (diff <= min) {
            count = App.calc(diff);
        } else {
            int mid = (start + end) / 2;
            var left = new CountRecursiveTask(min, start, mid);
            var right = new CountRecursiveTask(min, mid, end);
            
            left.fork();
            int rightResult = right.compute();
            int leftResult = left.join();
            count = rightResult + leftResult;
            
            // we can replace code with this
//            ForkJoinTask.invokeAll(left, right);
//            count = left.join() + right.join();

        }
        return count;
    }
}
```
```
value: 3, thread: ForkJoinPool-1-worker-7
value: 3, thread: ForkJoinPool-1-worker-13
value: 2, thread: ForkJoinPool-1-worker-1
value: 2, thread: ForkJoinPool-1-worker-15
value: 2, thread: ForkJoinPool-1-worker-9
value: 2, thread: ForkJoinPool-1-worker-11
value: 3, thread: ForkJoinPool-1-worker-5
value: 3, thread: ForkJoinPool-1-worker-3
CountRecursiveTask: 20
```
As you can see `fork/jon` framework always divide task on 2, and start to run first task, but second put into queue. And do it until task is small enough to complete. At the same time other threads can read this queue and take tasks from it (work stealing) and others can steal from others, so we have a tree, where all threads always running until all queues (for every thread) is empty and we can get result.

###### Synchronizers
Don't confuse:
* `Exchanger<T>` - exchange object of type T between 2 threads (you can create pipeline between threads and transfer data to and fro)
* `Semaphore` - use it when you want a resource to be shared between n number of threads concurrently. A Semaphore is initialized with a specific number of permits, representing the maximum number of threads that can concurrently access the shared resource. Threads that wish to access the resource must first acquire() a permit. If no permits are available, the thread blocks until a permit is released by another thread.
* `CyclicBarrier` - method `await` - waits until all threads come to the barrier and when final come, barrier is broken, and they all proceed further. If at least of threads is broken(or was interrupted) nobody will proceed.
* `CountDownLatch` - similar to barrier, yet for barrier you specify number of threads that it waits to finish, then all threads releases, but here - you specify number of latches, that you count down, and then all threads are released
you don't need to wait until threads reach some point, you can count down on any algo, but once you count down to 0, all your threads proceed further
```java
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) {
        int threads = 10;
        ExecutorService service = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < threads; i++) {
            final int index = i;
            service.submit(() -> {
                try {
                    latch.await();
                    System.out.print(index);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        System.out.println("countdown...");
        latch.countDown();

        sleep(1);
        System.out.println();

        Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < threads; i++) {
            final int index = i;
            service.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.print(index);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        System.out.println("semaphore release all...");
        semaphore.release(threads);

        sleep(1);
        System.out.println();

        CyclicBarrier barrier = new CyclicBarrier(threads);
        for (int i = 0; i < threads; i++) {
            final int index = i;
            service.submit(() -> {
                try {
                    barrier.await();
                    System.out.print(index);
                } catch (InterruptedException|BrokenBarrierException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        System.out.println("barrier run...");

        service.shutdown();
    }

    private static void sleep(long sec){
        try{
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
```
```
countdown...
0254731869
semaphore release all...
1230897645
barrier run...
9012345678
```
We can pass second param `Runnable` to `CyclicBarrier`, if we need to run some code, after barrier has been passed. Notice that this runnable will run exactly once by final thread that would reach barrier.
```java
import java.util.concurrent.*;

public class App{
    public static void main(String[] args) {
        int threads = 3;
        ExecutorService service = Executors.newFixedThreadPool(threads);
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName()  + ": finish work");
        };
        CyclicBarrier barrier = new CyclicBarrier(threads, r);
        for(int i = 0; i < threads; i++){
            service.submit(()->{
                try{
                    System.out.println(Thread.currentThread().getName()  + ": starting...");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName()  + ": done");
                } catch (InterruptedException|BrokenBarrierException ex){
                    throw new RuntimeException(ex);
                }
            });
        }
    }
}
```
```
pool-1-thread-2: starting...
pool-1-thread-1: starting...
pool-1-thread-3: starting...
pool-1-thread-3: finish work
pool-1-thread-2: done
pool-1-thread-3: done
pool-1-thread-1: done
```
You can also implement your own synchronizer logic using `AtomicInteger` and round-robin design
```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class App{
    public final static int NUM_THREADS = 4;
    private final static String THREAD_NAME_PREFIX = "Worker__";
    private final static AtomicInteger lock = new AtomicInteger();
    public static void main(String[] args) {
        Worker[] workers = new Worker[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++){
            workers[i] = new Worker(lock, i);
            new Thread(workers[i], THREAD_NAME_PREFIX + i)
                .start();
        }

        int roundRobin = 0;
        for(int i = 0; i < 12;i ++){
            workers[roundRobin++].add("str-"+i);
            if(roundRobin == NUM_THREADS){
                roundRobin = 0;
            }
        }
    }
    public static void block(){
        final String threadName = Thread.currentThread().getName();
        int lockId = Integer.parseInt(threadName.substring(threadName.indexOf(THREAD_NAME_PREFIX)+THREAD_NAME_PREFIX.length()));
        while (lockId != lock.get()){
            //busy wait
        }
    }

    public static void sleep(int ms) {
        try{
            Thread.sleep(ms);
        } catch (InterruptedException ex){
            System.out.println("OOPS => " + ex);
        }
    }
}

class Worker implements Runnable{
    private AtomicInteger lock;
    private final int lockId;
    private final int nextId;
    private Queue<String> queue = new LinkedList<>();

    public Worker(AtomicInteger lock, int lockId){
        this.lock = lock;
        this.lockId = lockId;
        nextId = lockId + 1 >= App.NUM_THREADS ? 0 : lockId + 1;
    }

    public boolean add (String str){
        return queue.add(str);
    }

    public void run(){
        while (true) {
            String str = queue.poll();
            if (str != null) {
                System.out.println(str + " => thread:" + Thread.currentThread().getId());
                App.sleep(1000);
                // wait to run sequentially
                App.block();
                // sequential code here
                System.out.println("sequential => " + str + " => thread:" + Thread.currentThread().getId());
                boolean r = lock.compareAndSet(lockId, nextId);
                if (!r){
                    System.out.println("__FAILED__");
                }
            }
        }
    }
}
```
```
str-0 => thread:13
str-1 => thread:14
str-2 => thread:15
str-3 => thread:16
sequential => str-0 => thread:13
str-4 => thread:13
sequential => str-1 => thread:14
str-5 => thread:14
sequential => str-2 => thread:15
str-6 => thread:15
sequential => str-3 => thread:16
str-7 => thread:16
sequential => str-4 => thread:13
str-8 => thread:13
sequential => str-5 => thread:14
str-9 => thread:14
sequential => str-6 => thread:15
str-10 => thread:15
sequential => str-7 => thread:16
str-11 => thread:16
sequential => str-8 => thread:13
sequential => str-9 => thread:14
sequential => str-10 => thread:15
sequential => str-11 => thread:16
```

###### Concurrent collections
`ConcurrentMap` and `ConcurrentSkipListMap` and `Hashtable` are not allowing null keys or values. The reason is that in simple `HashMap` you can have null key/value, and if you run `map.get` and get null you can easily check it with `containsKey`. But since value may be deleted this won't work. So any attempt to add null key/value with throw NPE.

###### Deadlock and Livelock
Deadlock - when 2 threads acquire 2 resources in different order. In such a scenario it’s possible that Thread1 has acquired first resource and wait to acquire second, Thread2 has acquired second and wait to acquire 1. In such case they will wait each other forever.
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        ExecutorService service = Executors.newFixedThreadPool(2);
        Runnable r1 = () -> {
            synchronized (o1) {
                print("r1 has acquired o1");
                sleep();
                synchronized (o2) {
                    print("r1 has acquired o2");
                }
            }
        };
        Runnable r2 = () -> {
            synchronized (o2) {
                print("r2 has acquired o2");
                sleep();
                synchronized (o1) {
                    print("r2 has acquired o1");
                }
            }
        };
        service.submit(r1);
        service.submit(r2);

        service.shutdown();
    }

    private static void print(String str){
        System.out.println(str + ": " + Thread.currentThread().getName());
    }

    private static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex + " " + Thread.currentThread());
        }
    }
}
```
```
r1 has acquired o1: pool-1-thread-1
r2 has acquired o2: pool-1-thread-2
```
As you see r1 got o1 and trying to get o2. At the same time r2 got o2 and trying to get o1. sleep for 100ms is used to insure that both will get hold of resources.
Real-world example of deadlock is transfer balance in account. If 2 users decided to transfer money to each other, and it would be processed by 2 different threads you may risk deadlock. That's why in trading systems, we prefer to use single-threaded app to process all balances.
```java
public class App {
    public static void main(String[] args) {
        Account acc1 = new Account();
        Account acc2 = new Account();
        new Thread(()->{
            transfer(acc1, acc2, 50);
        }).start();
        new Thread(()->{
            transfer(acc2, acc1, 50);
        }).start();
    }
    public static void transfer(Account from, Account to, long amount){
        synchronized (from){
            synchronized (to){
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }
}

class Account {
    private long balance;
    public void withdraw(long amount){
        balance -= amount;
    }
    public void deposit(long amount){
        balance += amount;
    }
}
```
There are 3 ways to get jvm process thread dump. But first we need to get processId.
We can get it in 2 ways (As you see jvm can help us by showing the problems):
1. run `jps` - will display all java processes id and names
```
22634 Main
23307 App
23308 Launcher
23359 Jps
```
2. run `ps aux | grep java | awk '{print $2}'`
3 ways to get thread dump
1. `jstack 22711`
2. `kill -3 22711`
3. `jconsole 22711` => go to Threads tab => click detect deadlock
run `jstack 22711`:
```
Java stack information for the threads listed above:
===================================================
"pool-1-thread-1":
        at com.java.test.App.lambda$main$0(App.java:22)
        - waiting to lock <0x00000007171c6be0> (a java.lang.Object)
        - locked <0x00000007171c6bd0> (a java.lang.Object)
        at com.java.test.App$$Lambda$14/0x0000000800066840.run(Unknown Source)
        at java.util.concurrent.Executors$RunnableAdapter.call(java.base@11.0.4/Executors.java:515)
        at java.util.concurrent.FutureTask.run(java.base@11.0.4/FutureTask.java:264)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.4/ThreadPoolExecutor.java:1128)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.4/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.4/Thread.java:834)
"pool-1-thread-2":
        at com.java.test.App.lambda$main$1(App.java:31)
        - waiting to lock <0x00000007171c6bd0> (a java.lang.Object)
        - locked <0x00000007171c6be0> (a java.lang.Object)
        at com.java.test.App$$Lambda$15/0x0000000800066c40.run(Unknown Source)
        at java.util.concurrent.Executors$RunnableAdapter.call(java.base@11.0.4/Executors.java:515)
        at java.util.concurrent.FutureTask.run(java.base@11.0.4/FutureTask.java:264)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.4/ThreadPoolExecutor.java:1128)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.4/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.4/Thread.java:834)

Found 1 deadlock.
```
How to resolve deadlock problem:
* instead of locks - use high-level concurrency objects like `BlockingQueue` or `ConcurrentHashMap`
* minimize the use of nested locks including `synchronized` keyword - because deadlock only possible in nested locks
* if you have to use nested locks - establish global order for lock acquisition.
  * we can refactor our code based on `hashcode` - see example below
* use `tryLock` or `tryLock(long time, TimeUnit unit)` from the `Lock` interface - this is non-blocking version of `lock`, it returns immediately (or wait for timeout). So you have to add `if/else` condition, but using it would prevent deadlock, because if thread is busy, then it would just return false, and you may proceed forward. This code is non-blocking, so you don't wait.
* If your app stuck in deadlock - the only way is to restart (assuming you fixed the code, otherwise after restart, the app would be stuck again)

Global order of calling to avoid deadlock
```java
public void transfer(Account from, Account to, int amount) {
    int fromHash = System.identityHashCode(fromAccount);
    int toHash = System.identityHashCode(toAccount);

    if (fromHash < toHash) {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                // do actual transfer
            }
        }
    } else if (fromHash > toHash) {
        synchronized (toAccount) {
            synchronized (fromAccount) {
              // do actual transfer
            }
        }
    } else {
        // if hashcode equals, introduce third locking
        synchronized (sameHashCodeLock) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                  // do actual transfer
                }
            }
        }
    }
}
```

Double-checked locking:
* design pattern mostly used in lazy initialization (usually for singleton pattern)
* the idea is that we put not whole method into `synchronized` but only critical part, moreover have double check within this critical part
* The double-checked pattern is used to avoid obtaining the lock every time the code is executed. If the call are not happening together then the first condition will fail and the code execution will not execute the locking thus saving resources
* use of local variable `localRef` may offer performance optimization: local variable makes sure we read `volatile` value only once. Because each read of volatile variable call memory barrier, if we rewrite code without local varables, then we would have 3 times call for `volaile` variable. This is not good for performance. So using local variable helps to avoid this and instead call it only once. The basic thing is that accessing the volatile variable is slower, than accessing the local one. When you declare a local variable, you are basically caching the value of a volatile variable inside the method
```java
// Works with acquire/release semantics for volatile in Java 1.5 and later
// Broken under Java 1.4 and earlier semantics for volatile
class Foo {
    private volatile Helper helper;
    public Helper getHelper() {
        Helper localRef = helper;
        if (localRef == null) {
            synchronized (this) {
                localRef = helper;
                if (localRef == null) {
                    helper = localRef = new Helper();
                }
            }
        }
        return localRef;
    }

    // other functions and members...
}
```

Livelock - is a special case of deadlock, can occur, when you try to solve deadlock by checking if another lock is unlocked inside `while` loop.
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        AtomicBoolean isLocked1 = new AtomicBoolean(false);
        AtomicBoolean isLocked2 = new AtomicBoolean(false);
        ExecutorService service = Executors.newFixedThreadPool(2);
        Runnable r1 = () -> {
            lock1.lock();
            isLocked1.set(true);
            try {
                print("r1 has acquired lock1");
                sleep(0.1);
                while (isLocked2.get()){
                    print("r1 waiting for lock2");
                    sleep(1);
                }
                lock2.lock();
                try {
                    print("r1 has acquired lock2");
                } finally {
                    lock2.unlock();
                }
            } finally {
                lock1.unlock();
            }
        };
        Runnable r2 = () -> {
            lock2.lock();
            isLocked2.set(true);
            try {
                print("r2 has acquired lock2");
                sleep(0.1);
                while (isLocked1.get()) {
                    print("r2 waiting for lock1");
                    sleep(1);
                }
                lock1.lock();
                try {
                    print("r2 has acquired lock1");
                } finally {
                    lock1.unlock();
                }
            } finally {
                lock2.unlock();
            }
        };
        service.submit(r1);
        service.submit(r2);

        service.shutdown();
    }

    private static void print(String str){
        System.out.println(str + ": " + Thread.currentThread().getName());
    }

    private static void sleep(double s) {
        try {
            Thread.sleep((int)s * 1000);
        } catch (InterruptedException ex) {
            System.out.println("sleep ERR: " + ex + " " + Thread.currentThread());
        }
    }
}
```
```
r1 has acquired lock1: pool-1-thread-1
r2 has acquired lock2: pool-1-thread-2
r1 waiting for lock2: pool-1-thread-1
r2 waiting for lock1: pool-1-thread-2
r1 waiting for lock2: pool-1-thread-1
r2 waiting for lock1: pool-1-thread-2
r1 waiting for lock2: pool-1-thread-1
r2 waiting for lock1: pool-1-thread-2
r1 waiting for lock2: pool-1-thread-1
r2 waiting for lock1: pool-1-thread-2
r1 waiting for lock2: pool-1-thread-1
r2 waiting for lock1: pool-1-thread-2
...
```
To check if current thread holding any object on lock you can use `Thread.holdsLock()`.
```java
public class App {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Runnable r = () -> {
            System.out.println("o1 lock => " + Thread.holdsLock(o1) + ", o2 lock => " + Thread.holdsLock(o2));
            synchronized (o1) {
                System.out.println("o1 lock => " + Thread.holdsLock(o1) + ", o2 lock => " + Thread.holdsLock(o2));
                synchronized (o2) {
                    System.out.println("o1 lock => " + Thread.holdsLock(o1) + ", o2 lock => " + Thread.holdsLock(o2));
                }
            }
        };
        new Thread(r).start();
    }
}
```
```
o1 lock => false, o2 lock => false
o1 lock => true, o2 lock => false
o1 lock => true, o2 lock => true
```
If we need an application were we can have many reads but only one write (during which all reads are locked) we can use `ReadWriteLock`
```java
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class App {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(8);
        MultipleReadsSingleWrite<Integer> list = new ReadWriteLockMRSW<>();
        //every second 4 threads read, every 2 seconds 1 thread writes
        for(int i = 0; i < 10; i++){
            if(i%2 == 0){
                System.out.println("queue size => " + ((ThreadPoolExecutor)service).getQueue().size());
            }
            for(int reads = 0; reads < 4; reads++){
                service.execute(()->{
                    int index = list.size() - 1;
                    if (index > 0) {
                        System.out.println("reading... => " + index + ", value => " + list.read(index));
                    }
                });
            }
            final int value = i;
            for(int writes = 0; writes < 1; writes++){
                service.submit(()->{
                    list.write(value);
                    System.out.println("written => " + value);
                });
            }
            sleep(1000);
        }
        System.out.println("__DONE__");
    }

    public static void sleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}

interface MultipleReadsSingleWrite<T>{
    T read(int index);
    void write(T value);
    int size();
}

class ReadWriteLockMRSW<T> implements MultipleReadsSingleWrite<T>{
    private final List<T> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public T read(int index){
        lock.readLock().lock();
        try{
            App.sleep(1000);
            return list.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void write(T value){
        lock.writeLock().lock();
        try{
            App.sleep(3000);
            list.add(value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public int size() {
        return list.size();
    }
}

class CustomMRSW<T> implements MultipleReadsSingleWrite<T>{
    private final List<T> list = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    @Override
    public T read(int index){
        if(Thread.holdsLock(lock)){
            System.out.println("locking");
        }
        App.sleep(5000);
        return list.get(index);
    }

    @Override
    public void write(T value){
        lock.lock();
        App.sleep(1000);
        list.add(value);
        lock.unlock();
    }

    @Override
    public int size() {
        return list.size();
    }
}
```

###### Readers–writers problem
Mutex (mutual exclusion) - logical concept in concurrent programming that allows exclusive access to the section of the code by only 1 thread-at-a-time
There are several access pattern for concurrent app:
* single writer - single reader: no problem here, just use `mutex`, only 1 thread can access resource
* many writers - single reader: no problem, just use `mutex`, only 1 thread can access resource
* single/many writers - many readers: here we may have a problem - if we have many readers, it doesn't make sense for them to wait each other. Because they only read, they can all access the object at the same time.
To resave this problem with multiple readers we have come up with 3 solutions. But before we goes deeper, let's set up 2 constraints:
* writing is exclusive operation - when a thread is writing, nobody can write/read
* reading is shared with other reading but exclusive to writing - many threads can read at the same time, but if at least one is reading not writes are possible. Important point here is that read-write are exclusive. This is done to avoid partial reading. Imagine a thread writing and another thread reading or vice-versa. In this case reading thread may read partial/incomplete data.
3 ways to solve readers-writers problem:
* Reader preference:
  * if writing thread writes, nobody can read/write
  * if at least one thread reading, writing thread waiting to acquire the lock
  * once no thread reading, writing thread may acquire the lock and start writing
  * this is achieved with 2 locks, one for write another for read, and when we read, we use variable `readCount` to see how many readers currently reading, and if at least one is reading we keep the write lock. Once we see that no readers we release write lock 
  * this solution gives priority to readers, and may lead to write starvation - writing thread wait long enough to get lock on resource and start writing
  * java `ReadWriteLock` with Non-fair mode use this design pattern
* Writer preference:
  * similar concept as first one, but now writes has priority
  * the code is similar with 4 locks, where all writes write first then readers can read
  * may lead to reader starvation, because writes always have the priority
* Fair preference (no thread shall be allowed to starve):
  * both readers and writes access the resource fair
  * The code is similar to first one, but we introduce third mutex - fairness, and based on this reader/writer get access to critical section

Reader preference solution source code:
```java
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) {

    }

    private ReentrantLock writeLock = new ReentrantLock();
    private ReentrantLock readLock = new ReentrantLock();
    private int readCount = 0;
    void criticalSection(){

    }

    void write(){
        writeLock.lock();
        criticalSection();
        writeLock.unlock();
    }

    void read(){
        readLock.lock();
        if (readCount++ == 1){
            writeLock.lock();
        }
        readLock.unlock();
        criticalSection();

        readLock.lock();
        if (readCount-- == 0){
            writeLock.unlock();
        }
        readLock.unlock();
    }
}
```

###### Synchronized on ID
Sometimes you have to do some non-idempotent operation, for this you set some flag in db, and if second request came you throw exception. But what if several requests came at the same time. In this case they all will read flag as false. For this you should use `syncronized` keyword. This keyword synchronize objects not based on hashcode/equals, but base on internal monitor of each object (so 2 string can be absolutely same yet `syncronized` would see them as 2 different object). But if you set it to method level, then all requests for all objects would wait each other. You have to synchronized on each object separately. For this purpose it's better to use some id. Plz note that sometimes for each string new object created (for example you use this logic to synchronize inside spring controller method where you parse user input, in this case each time method is called new object string is created, but value can be the same)
in this case there are 2 solutions:
* use `intern` method for string (convert either whole object or particular fields `obj.toString().intern()`)
* use `ConcurrentHashMap` with key as your string and value just `new Object()`. This would guarantee that each time same string came, you already have this value in your amp, and then use `synchronized (map.get(yourString)){}`
```java
class User{}
class Handler{
    private static final Map<User, Object> map = new ConcurrentHashMap<>();
    public void handle(User user){
        map.putIfAbsent(user, new Object());
        synchronized (map.get(user)) {
            
        }
    }
}
```
Below example use integer as object.
```java
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App{
    public static void main(String[] args) {
        App app = new App();

        int threads = 4;
        ExecutorService service = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++){
            service.submit(()->{
                app.doWork(1);
            });
            service.submit(()->{
                app.doWork(2);
            });
            service.submit(()->{
                app.doWork(3);
            });
        }
        service.shutdown();
    }

    /**
     * syncronized on method => same as syncronized(this)
     * That's why we need some id on which to syncronize, in this case it's personId
     * So all threads go to this method and stopped on id and from there start execute one by one
     */
    public void doWork(Integer id) {
        synchronized (id){
            var person = getPerson(id);
            //System.out.println(id + " => " + person);
            if (person != null) {
                throw new RuntimeException("Person with id=" + id + " already exists");
            }
            savePerson(id);
            sleep(2);
            System.out.println("Job Done => " + id);
        }
    }

    private void sleep(long sec) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }

    private Map<Integer, Person> db = new ConcurrentHashMap<>();
    public void savePerson(int id){
        Map<Integer, String> people = new HashMap<>();
        people.put(1, "Jack");
        people.put(2, "Mike");
        people.put(3, "Dennis");
        Person person = new Person(id, people.get(id));
        db.put(id, person);
    }
    public Person getPerson(int id){
        return db.get(id);
    }
}

class Person{
    private int id;
    private String name;

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }
}
```
```
Job Done => 1
Job Done => 2
Job Done => 3
```

###### Future & CompletableFuture
* Future - java5 interface to help with async computation
* CompletableFuture - java8 class with over 50 functions for async computation (so it's basically whole mini-framework)
You can get result with completable future in 2 ways:
* get - throws `InterruptedException, ExecutionException`
* join - doesn't throw any exception
```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) {
        // consecutive execution
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> fetchResourceA())
                .thenApplyAsync(r -> r + fetchResourceB());
        try{
            System.out.println("future.get => " + future.get());
        } catch (InterruptedException | ExecutionException ex){
            throw new RuntimeException(ex);
        }

        /**
         * You can also use async call
         */
        future.whenComplete((res, err)->{
            System.out.println("res=" + res + ", err=" + err);
        });
        /**
         * imitate program running, otherwise program would exit
         * the reason is that CompletableFuture use ForkJoinPool which create daemon threads, and as you know JVM doesn't wait for daemon threads to finish
         */
        sleep(10);
    }

    public static String fetchResourceA(){
        System.out.println("thread=" + Thread.currentThread() + ", isDaemon=" + Thread.currentThread().isDaemon());
        sleep(2);
        return "A";
    }
    public static String fetchResourceB(){
        sleep(3);
        return "B";
    }
    public static void sleep(long sec){
        try{
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
```
```
thread=Thread[ForkJoinPool.commonPool-worker-19,5,main], isDaemon=true
future.get => AB
res=AB, err=null
```
You can run futures in parallel with `allOf`, so it would be completed when last futures from list would be done. Yet it returns void, so if you need results from these futures, you should call get/join to each of the future separately.
```java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class App {
    public static void main(String[] args) {
        List<CompletableFuture<String>> futures = new ArrayList<>(List.of(
            CompletableFuture.supplyAsync(() -> fetchResourceA()),
            CompletableFuture.supplyAsync(() -> fetchResourceB())
        ));
        CompletableFuture<Void> future = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        future.whenComplete((res, err)->{
            System.out.println("res=" + res + ", err=" + err);
            // since out future is completed, join on all children futures will complete immediately, cause they all completed by this time
            futures.forEach(f -> System.out.println(f.join()));
        });
        System.out.println("done");
        sleep(10);
    }

    public static String fetchResourceA(){
        sleep(2);
        return "A";
    }
    public static String fetchResourceB(){
        sleep(3);
        return "B";
    }
    public static void sleep(long sec){
        try{
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
```
```
done
res=null, err=null
A
B
```

###### ReentrantLock/ReentrantReadWriteLock/StampedLock
There are 3 main class to work with `Lock` interface:
* ReentrantLock - basic implementation of `Lock` interface. This is same as using `synchronized` on each method. All locks in java including locks created by `synchronized` keyword are reentrant. That means same thread can re-acquire the lock several times, but in this case thread would need to release it exact number of times as it acquired it. Reentrancy achieved by JVM which maintain internal counter for each lock per thread, and if thread try to acquire lock second time, counter increased. If thread release lock, counter decreased, if counter == 0, then lock is released. This is done on purpose, so you don't get exception, otherwise if you call `synchronized` method from another `synchronized` method for the same thread you would get exception. But since all locks are reentrant you don't get it.
* ReentrantReadWriteLock - implementation of `ReadWriteLock` with 2 locks for read & write (implemented as inner static classes and implementing `Lock` interface). This can be same as using `synchronized` + `volatile` and not synchronized read. We need readLock - to ensure multiple readers can read at the same time (compare to `synchronized` keyword where each reader would wait another reader until it release lock), and also readLock ensure that once writeLock is happening no read should be possible
* StampedLock - same as ReentrantReadWriteLock, but using a few additional methods:
    * `tryConvertToWriteLock` - convert read lock to write lock
    * `tryOptimisticRead` - use optimistic read
```java
class BankAccount {
    private final StampedLock sl = new StampedLock();
    private int balance;

    public void deposit(int amount) {
        long stamp = sl.writeLock();
        try {
            balance += amount;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    public void withdraw(int amount) {
        long stamp = sl.writeLock();
        try {
            balance -= amount;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    public int getBalance() {
        long stamp = sl.tryOptimisticRead();
        int balance = this.balance;
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                balance = this.balance;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return balance;
    }
}
```

###### Reactive Streams
Don't confuse:
* java stream API - working with data as sequence of events, using functional interfaces
* java reactive streams - way of communication between producer & consumer, where consumer request data, and producer push data to consumer based on such request
There are 2 reactive implementations: RxJava & Akka, yet starting from java9, jdk provides reactive interfaces `Flow`. And one implementation of concurrent publisher `SubmissionPublisher`. There are 2 ways for producer-consumer problem:
* pull (no backpressure) - when consumer polls producer, upside - consumer controls speed, downside - waste of resources for polling
* push (backpressure on consumer) - when producer pushes to consumer, upside - no waste of resources, downside - if consumer slower than producer, we may have data loss
* push-on-request (backpressure on producer) - when producers pushed to consumer based on consumer request, so we combine upsides from both pull/push approach
To summarize reactive is when consumer decide when to handle next item, by signalling it to producer, and it's the job of producer to handle multiple consumers, based on their speed. Notice that java9 is very open, for `Publisher` there is only 1 method to implement `subscribe`, the actual `publish` implementation. is up to you, in case of `SubmissionPublisher` it called `submit`. It's under `java.util.concurrent` package, cause handle multiple consumers based on their speed, required some concurrency/buffering handling. For transformation, we have `Processor` interface which is both consumer & producer, and you need to implement both, first to consume items,
transform inside, and then publish to another consumer. Backpressure handling - 2 ways to handle this:
* increase buffer size
* drop some items
```java
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class App{
    public static void main(String[] args) throws InterruptedException {
        Flow.Subscriber<String> subscriber = new SimpleSubscriber<>();
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        publisher.subscribe(subscriber);
        List.of("a","aa","aaa","aaaa").forEach(publisher::submit);
        publisher.close();
        Thread.sleep(100);
        System.out.println(((SimpleSubscriber)subscriber).getList());
    }
}

class SimpleSubscriber<T> implements Flow.Subscriber<T>{
    private Flow.Subscription sub;
    @Getter
    private List<T> list = new ArrayList<>();

    @Override
    public void onSubscribe(Flow.Subscription sub) {
        System.out.println("init...");
        this.sub = sub;
        this.sub.request(1);
    }

    @Override
    public void onNext(T t) {
        list.add(t);
        // we need this to signal to publisher that we are ready to receive new element
        // this make it reactive, so push-on-request
        sub.request(1);
    }

    @Override
    public void onError(Throwable ex) {
        System.out.println("ERR => " + ex);
    }

    @Override
    public void onComplete() {
        System.out.println("DONE");
    }
}
```
```
init...
DONE
[a, aa, aaa, aaaa]
```

#### JDBC and SQL
###### Connection
Mysql driver is not part of SDK so you have to manually add it to `pom.xml`
```
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.18</version>
</dependency>
```
Older jdbc driver used this code `Class.forName("com.mysql.jdbc.Driver")` to load driver to classLoader. Since jdbc4 we don't need this code anymore, it's automatically loaded. 
`getConnection` and `getDrivers` were rewritten to support Service Provider mechanism. jdbc4 drivers must include `META-INF/services/java.sql.drivers` and this entry must have a name of driver implementation.
There are 2 ways you can get `Connection`
* Using `Driver.connect` (this is bad, cause this would tightly couple your code to exact driver implementation)
* Using `DriverManager.getConnection` (which inside using `driver.connect`)

```java
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        String url = "jdbc:mysql://localhost:3306/ocpjp";
        // we can pass props either in Properties or directly in String url
        //String url = "jdbc:mysql://localhost:3306/ocpjp?autoReconnect=true&useSSL=false&user=root&password=";
        // we can also create connection using Driver
        try{
            // this approach couple your app to exact driver implementation
            Driver driver = new com.mysql.cj.jdbc.Driver();
            /**
             * You can also obtain driver from DriverManager  =>  Driver driver = DriverManager.getDriver(url);
             */
            System.out.println(driver.getClass());
            Connection conn = driver.connect(url, props);
        } catch (SQLException ex){
            // logic here
        }
        try (Connection conn = DriverManager.getConnection(url, props);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select id, name from animals");
        ) {
            // initially ResultSet point before first row, we call next and get first row and so on..
            while (rs.next()) {
                // we can query by index (starting from 1) or by column names (names case insensitive)
                System.out.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt("Id") + " " + rs.getString("name"));
                System.out.println();
                /**
                 * ResultSet has methods to get not only strings, but other data
                 * getObject
                 * getInt/Short/Byte/Long/Float/Double - all primitives
                 */
            }
            /**
             * calling here rs.getString(1); would cause SQLException: After end of result set
             */

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }
}
```
```
1 cat pat 1 
2 jiraffe jack 2 
3 hippo math 3 
4 lion lily 4 
```

Once connection is closed, all resources that was gotten form it are unavailable
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        String url = "jdbc:mysql://localhost:3306/ocpjp";
        Statement stmt;
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(url, props);
        ) {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from animals");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(stmt);
        System.out.println(rs);
    }
}
```

You can also use `DataSource` interface. Compare 2 examples
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.MysqlDataSource;

public class App {
    public static void main(String[] args) throws Exception {
        String dbUser = "root";
        String dbPassword = "";
        String dbHost = "localhost";
        String dbName = "ocpjp";
        String url = "jdbc:mysql://"+dbHost+":3306/"+dbName+"?user="+dbUser+"&password="+dbPassword;

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("select * from people");
             ResultSet rs = stmt.executeQuery();) {
            while (rs.next()){
                System.out.println("name => " + rs.getString("firstname") + " " + rs.getString("lastname"));
            }
        }


        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl(url);
//        ds.setUser(dbUser);
//        ds.setPassword(dbPassword);
//        ds.setServerName(dbHost);
//        ds.setDatabaseName(dbName);
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement("select * from people");
             ResultSet rs = stmt.executeQuery();) {
            while (rs.next()){
                System.out.println("name => " + rs.getString("firstname") + " " + rs.getString("lastname"));
            }
        }
    }
}
```
```
name => Gary Larson
name => Gary Larson
```

How many db connections can be created. It will vary between 150 and 280, but on average around 200. Here is a nice way to check it out. 
```java
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        int n = 10000;
        String url = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&user=root&password=";
        for (int i = 0; i < n; i++) {
            try {
                // we intentionally don't close connections
                DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getSQLState() + ": " + e.getMessage() + ", numberOfConnections: " + i);
                break;
            }
        }
    }
}
```
```
08001: Could not create connection to database server. Attempted reconnect 3 times. Giving up., numberOfConnections: 193
```

Pay attention, that limit is only on `Connection` resource, for Statement and ResultSet we can create as much as our memory allow, without closing
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        int n = 100000;
        long start = System.currentTimeMillis();
        String url = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&user=root&password=";
        Connection conn = DriverManager.getConnection(url);
        for (int i = 0; i < n; i++) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from people");
            } catch (SQLException e) {
                System.out.println(e.getSQLState() + ": " + e.getMessage() + ", connection: " + i);
                break;
            }
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) / 1000 + " sec");
    }
}
```
```
time: 14 sec
```
As you see we have created 100k statements and resultsets without closing, and it’s ok. Creating and closing connections is also expensive
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        int n = 10000;
        long start = System.currentTimeMillis();
        String url = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&user=root&password=";
        for (int i = 0; i < n; i++) {
            try(Connection conn = DriverManager.getConnection(url);){
            } catch (SQLException e) {
                System.out.println(e.getSQLState() + ": " + e.getMessage() + ", connection: " + i);
                break;
            }
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) / 1000 + " sec");
    }
}
```
```
time: 24 sec
```
It took us 24s to create and close 10k connection. All the above clearly shows us, that we need a connection pool. Why not to use just one connection then? Answer is simple - it’s very slow.

```java
public class App {
    public static void main(String[] args) {
        int n = 200000;
        long start = System.currentTimeMillis();
        String url = "jdbc:mysql://126.0.0.1:3306/mydb?user=root&password=";
        Connection conn = DriverManager.getConnection(url);
        for (int i = 0; i < n; i++) {
            runQuery(conn);
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) / 1000 + " sec");
    }
    private static Connection runQuery(Connection conn) {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PEOPLE")) {
            while (rs.next()) {
                rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + ": " + e.getMessage());
        }
        return conn;
    }
}
```
time: 17 sec

As you see, to execute 200k queries it took us 17sec. Maybe we can speed it up by using threads, let’s see
```java
public class App {
    public static void main(String[] args) {
        int n = 200000;
        long start = System.currentTimeMillis();
        String url = "jdbc:mysql://126.0.0.1:3306/mydb?user=root&password=";
        int threadNumber = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        Connection conn = DriverManager.getConnection(url);
        for (int i = 0; i < n; i++) {
            service.execute(() -> {
                runQuery(conn);
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("time: " + (System.currentTimeMillis() - start) / 1000 + " sec");
    }
    private static Connection runQuery(Connection conn) {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PEOPLE")) {
            while (rs.next()) {
                rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + ": " + e.getMessage());
        }
        return conn;
    }
}
```
```
time: 20 sec
```
As you see, it took even more time. The reason is that although we run in separate threads, connection internally can be used only by one of it, so basically it worked like `syncronized`. That’s why we can’t share one connection to speed up - it will anyway run sequentially. Here is why we need to have pool of them.

```java
import java.sql.*;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) {
        int n = 200000;
        long start = System.currentTimeMillis();
        String url = "jdbc:mysql://126.0.0.1:3306/mydb?user=root&password=";
        int threadNumber = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        BlockingDeque<Connection> queue = new LinkedBlockingDeque<>();
        for (int i = 0; i < 3 * threadNumber; i++) {
            try {
                queue.add(DriverManager.getConnection(url));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        for (int i = 0; i < n; i++) {
            Connection conn;
            try {
                conn = queue.take();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            service.execute(() -> {
                queue.add(runQuery(conn));
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) / 1000 + " sec");
    }
    private static Connection runQuery(Connection conn) {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PEOPLE")) {
            while (rs.next()) {
                rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + ": " + e.getMessage());
        }
        return conn;
    }
}
```
```
time: 7 sec
```
Here we are using pool of connections, and once we have used it, return it to db. We almost triple speed by this.

###### Statement and PreparedStatement
It's better to always use `PreparedStatement` cause you can:
* avoid sql injection
* reuse query and by that get performance hit
* use blob/clob additional datatypes (ps.setBlob(i, Blob b), ps.setClob(i, Clob c))
```java
public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ocpjp?autoReconnect=true&useSSL=false&user=root&password=";
        try (Connection conn = DriverManager.getConnection(url)
        ) {
            String sql = "update people set date=? , time=?, timestamp = ? where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setTime(2, Time.valueOf(LocalTime.now()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setInt  (4, 1);

            preparedStatement.setObject(1, LocalDate.now());
            preparedStatement.setObject(2, LocalTime.now());
            preparedStatement.setObject(3, LocalDateTime.now());
            preparedStatement.setInt  (4, 1);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("updated: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }
}
```
As you see you can set date, time, timestamp with both `setDate, setTime, setTimestamp` or with `setObject` ans pass directly `LocalDate, LocalTime, LocalDateTime`.

SQL Injection. `PreparedStatement` by default handles sql injection. For `Statement` there are 3 enquotes method
```java
Statement st = conn.createStatement();
String value = "";
String safeValue = st.enquoteIdentifier(value, true);
safeValue = st.enquoteLiteral(value);
```

###### CallableStatement
There are 3 interfaces to execute queries:
`Statement` (extends AutoClosable) - can be obtained `Statement s = conn.createStatement("");`
`PreparedStatement` (extends Statement) - can be obtained `PreparedStatement s = conn.prepareStatement("");`
`CallableStatement` (extends PreparedStatement) - can be obtained `CallableStatement s = conn.prepareCall("");` 
Sql example of stored procedure to fetch lastname by id. Although it's pretty useless, cause you can achieve the same with simple query, you can write more complex procedures to get some complex data.
```
# create procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS `getLastName` $$
CREATE PROCEDURE `getLastName` 
   (IN PERSON_ID INT, OUT LAST_NAME VARCHAR(255))
BEGIN
   SELECT lastname INTO LAST_NAME
   FROM people
   WHERE ID = PERSON_ID;
END $$
DELIMITER ;

# call stored procedure
CALL getLastName(1, @out_value);

# get final value
SELECT  @out_value;
```

Here is example how to use `CallableStatement` to get return values from stored procedure. Also an example of doing the same from simple query to db table.
```java
import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ocpjp?autoReconnect=true&useSSL=false&user=root&password=";
        try(Connection con = DriverManager.getConnection(url);
            CallableStatement call = con.prepareCall("call getLastName(?, ?)");
            PreparedStatement stmt = con.prepareStatement("select lastname from people where id=?");){

            call.setInt(1, 1);
            call.registerOutParameter(2, Types.VARCHAR);
            call.execute();
            System.out.println("call => " + call.getString(2));

            stmt.setInt(1, 1);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                System.out.println("stmt => " + rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
```

###### Transactions
It’s easy to work with transaction in JDBC. By default `autoCommit` is true, so after each `executeUpdate` we flush data to db. But we can set it to false, and in the end, call `conn.commit` (will throw SQLException if autoCommit=true) or `conn.setAutoCommit(true);` (this will commit everything as side effect, if autoCommit=true no exception is thrown). This will ensure that only after all code executed successfully data would be flushed into db. As you see in the third query we make a mistake (ids=3 instead of id=3), so all 3 updates won’t be executed against db.
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ocpjp?autoReconnect=true&useSSL=false&user=root&password=";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
        ) {
            System.out.println("start tx");
            conn.setAutoCommit(false);
            // set isolation level
            conn.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);
            int result1 = stmt.executeUpdate("update animals set name = 'name-11' where id = 1");
            System.out.println("result1: " + result1);
            int result2 = stmt.executeUpdate("update animals set name = 'name-22' where id = 2");
            System.out.println("result2: " + result2);
            int result3 = stmt.executeUpdate("update animals set name = 'name-33' where ids = 3");
            System.out.println("result1: " + result3);
            System.out.println("commit tx");
            conn.commit();
            // the below code also will flush all changes to db
            //conn.setAutoCommit(true);
            /**
             * Generally it's good to call rollback, cause even if you get exception you tx still be in memory and will be rolled back by timeout
             * But in reality you will never have to call it manually (until you are writing framework)
             * Since we call rollback after commit it has no effect on the flow
             */
            Savepoint savepoint = conn.setSavepoint("MySavePoint");
            conn.rollback();
            conn.rollback(savepoint);
            // after release you can't reuse it again
            conn.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            System.out.println("Get exception: message=" + e.getMessage() + ", SQLState=" + e.getSQLState() + ", errorCode=" + e.getErrorCode());
            conn.rollback();
        }
    }
}
```
```
start tx
result1: 1
result2: 1
Get exception: message => Unknown column 'ids' in 'where clause', SQLState => 42S22, errorCode => 1054
```

#### Serialization
###### Java serialization
When deserialization of new object happens only static initializer fires (if class wasn't loaded before deserialization), constructors & instance initializer are not executed. First uncomment line to serialize object, then comment and run and you will see that only static initializers are called.
Deserialization doesn't invoke constructor & instance initializer because the point of deserialization is to recover an object as it was before serialization. Calling constructor or instance initializers may tamper with object.
It searches all parents until it found one that doesn't implement `Serializable` and have default constructor,
(if it doesn't have such a class it goes all way up to `Object`, if it has such class, but that class doesn't have no-arg constructor, exception is thrown `java.lang.RuntimeException: java.io.InvalidClassException: com.java.test.Person; no valid constructor`), 
and jvm creates class from that default constructor. But compare with `new` initialization, jvm didn't go further to class constructor.
Pay attention that static fields don't serialize. If you want to serialize class into file or deserialize it use `ObjectInputStream/ObjectOutputStream`.
Always use serialVersionUID variable, if doubt just set `private static final long serialVersionUID = 1;`.
Otherwise, compiler will generate version for you, but if you change something like adding `transient` field, what is not obstructing deserialization, java may regenerate your serialVersionUID and you deserialization will fail.
Java serialization is binary serialization, so your file would have binary content (compare to xml/json).
You can decode this binary file for one of below example with following command
```
$ hexdump -C text

00000000  ac ed 00 05 73 72 00 14  63 6f 6d 2e 6a 61 76 61  |....sr..com.java|
00000010  2e 74 65 73 74 2e 50 65  72 73 6f 6e 00 00 00 00  |.test.Person....|
00000020  00 00 27 0f 02 00 02 49  00 03 61 67 65 4c 00 04  |..'....I..ageL..|
00000030  6e 61 6d 65 74 00 12 4c  6a 61 76 61 2f 6c 61 6e  |namet..Ljava/lan|
00000040  67 2f 53 74 72 69 6e 67  3b 78 70 00 00 00 1e 74  |g/String;xp....t|
00000050  00 04 4a 61 63 6b                                 |..Jack|
00000056
```
There are a few ways you can get your serial number
```java
import java.io.ObjectStreamClass;
import java.io.Serializable;

public class App {
    public static void main(String[] args) {
        System.out.println(ObjectStreamClass.lookup(Person.class).getSerialVersionUID());
    }
}
class Person implements Serializable {}
```
```
1733576120003020849
```
Using command line
```
serialver -classpath target/classes com.java.test.Person
#com.java.test.Person:    private static final long serialVersionUID = 1733576120003020849L;
```
Here we don't set clearly serialNumberUID so javac generate it for us. Pay attention if we don't implement `Serializable`, `ObjectStreamClass.lookup(Person.class)` will return null, and `serialver` utility will fail
You can also get serial number from binary data itself
```java
/**
 * https://www.javaworld.com/article/2072752/the-java-serialization-algorithm-revealed.html
 */
import java.io.*;

public class App {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/text");
        Person p1 = new Person("Jack", 30);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(p1);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Person p2 = (Person) in.readObject();
            System.out.println(p2);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(ObjectStreamClass.lookup(Person.class).getSerialVersionUID());
        System.out.println(readSerialNumberUID(Person.class, file));
    }

    private static long readSerialNumberUID(Class clazz, File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            String className = clazz.getName();
            StringBuilder hex = new StringBuilder();
            StringBuilder content = new StringBuilder();
            int b, i = 0;
            boolean serialStart = false;
            while ((b = in.read()) != -1) {
                content.append((char) b);
                if (serialStart && i < 8) {
                    // don't use Integer.toHexString, it removes leading zeros and in case of 15 return just f instead of 0f
                    hex.append(String.format("%02X", b));
                    i++;
                } else if (content.toString().contains(className)) {
                    serialStart = true;
                }
            }
            return Long.parseLong(hex.toString(), 16);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

class Person implements Serializable{
    private static final long serialVersionUID = 9999;
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString(){
        return "Person[name="+name+", age="+age+"]";
    }
}
```
We can serialize and deserialize single as well as list of objects. In case of list of objects there is no way to determine end-of-file with `readObject`, so we are using exception to catch it and swallow. This is the only case where it's appropriate to swallow exception.
```java
import java.util.*;
import java.io.*;

public class App {
    public static void main(String[] args) {
        serializeSingleObject();
        serializeList();
    }

    private static void serializeSingleObject(){
        File file = new File("src/main/java/com/java/test/text");

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            Person p1 = new Person("Mike", 30);
            out.writeObject(p1);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            Person p2 = (Person) in.readObject();
            System.out.println("p2 => " + p2);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void serializeList() {
        File file = new File("src/main/java/com/java/test/text");

        List<Person> people = new ArrayList<>();
        people.add(new Person("Jack", 25));
        people.add(new Person("Mike", 35));
        people.add(new Person("Melanie", 30));
        people.add(new Person("David", 20));

        List<Person> deserialized = new ArrayList<>();

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (var person : people) {
                out.writeObject(person);
            }
        } catch (IOException ex) {
            System.out.println("write ERR: " + ex);
        }

        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object obj = in.readObject();
                if (obj instanceof Person) {
                    deserialized.add((Person) obj);
                }
            }
        } catch (EOFException ex) {
            System.out.println("end of file");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("read ERR: " + ex);
        }
        System.out.println("deserialized => " + deserialized);
    }
}
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Person[name="+name+",age="+age+"]";
    }
}
```
```
p2 => Person[name=Mike,age=30]
end of file
deserialized => [Person[name=Jack,age=25], Person[name=Mike,age=35], Person[name=Melanie,age=30], Person[name=David,age=20]]
```
If our object is composite and includes other objects, they all must implement `Serializable` or be declared `transient` or `static`. Otherwise we would get error.
Since Body object inside Person doesn't implement `Serializible` we got error trying to serialize. 
If we change it to `transient private Body body;` it won't be serialized => `Person [name=Mike, age=30, null]`.
If we change it to `class Body implements Serializable` => `Person [name=Mike, age=30, body=Body[weight=75]]`.
If body inside person is null, we won't get serialization error.
```java
import java.io.*;

public class App {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/text");

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            Person p1 = new Person("Mike", 30, new Body(80));
            out.writeObject(p1);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            Person p2 = (Person) in.readObject();
            System.out.println("p2 => " + p2);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private Body body;

    public Person(String name, int age, Body body) {
        this.name = name;
        this.age = age;
        this.body = body;
    }

    public String toString() {
        return "Person[name="+name+",age="+age+",body="+body+"]";
    }
}
class Body{
    private int weight;
    public Body(int weight){
        this.weight = weight;
    }
    @Override
    public String toString(){
        return "Body[weight=" + weight + "]";
    }
}
```
```
Exception in thread "main" java.lang.RuntimeException: java.io.NotSerializableException: com.java.test.Body
```
There are 3 ways we can customize serialization:
* 1. define `serialPersistentFields` array with fields to be serialized
* 2. define `writeObject` and `readObject` for custom serialization (if you want even more fine-grained control you can use `writeFields` and `readFields`)
* 3. implement `Externalizable` interface
Set `serialPersistentFields` to define what fields to serialize. Name should be private and match exactly.
```java
import java.io.*;

public class App {
    public static void main(String[] args){
        File file = new File("src/main/java/com/java/test/text");

        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            Person p1 = new Person("Mike", 30, 80);
            System.out.println(p1);
            out.writeObject(p1);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }

        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            Person p2 = (Person)in.readObject();
            System.out.println(p2);
        } catch (IOException | ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }
}

class Person implements Serializable {
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("name", String.class),
        new ObjectStreamField("age", int.class),
    };
    private String name;
    private int age;
    private int weight;
    public Person(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", weight=" + weight + "]";
    }
}
```
```
Person [name=Mike, age=30, weight=80]
Person [name=Mike, age=30, weight=0]
```
Define custom `writeObject` and `readObject`
By default java serialize all non-static and non-transient fields. If we want to serialize them too, or just have a custom logic we should implement 2 methods.
Order of reading should correspond with order of writing. weight was written first so it should be read first.
By default in every class that implements `Serializable` java inserts 2 methods 
```java
private void writeObject(ObjectOutputStream out) throws IOException{
    out.defaultWriteObject();
}
private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
    in.defaultReadObject();
}
```
If you need more custom logic, you should implement them in your code and add any logic. 
If you want to prevent serialization just implement them and throw `NotSerializableException`.
```java
import java.io.*;

public class App {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/text");

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            Person p1 = new Person("Mike", 30);
            p1.weight = 80;
            p1.currentObject = 100;
            out.writeObject(p1);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            Person p2 = (Person) in.readObject();
            System.out.println("p2 => " + p2);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public transient int weight = 1;
    public static int currentObject = 1;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Person[name="+name+", age="+age+", weight="+weight+", currentObject="+currentObject+"]";
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        // utilize default serialization
        out.defaultWriteObject();
        out.writeInt(weight);
        out.writeInt(currentObject);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // utilize default serialization
        in.defaultReadObject();
        weight = in.readInt();
        currentObject = in.readInt();
    }
}
```
```
p2 => Person[name=Mike, age=30, weight=80, currentObject=100]
```
We can also use `writeFields` and `readFields`.
```java
import java.io.*;

public class App {
    public static void main(String[] args){
        File file = new File("src/main/java/com/java/test/text");

        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            Person p1 = new Person("Mike", 30);
            out.writeObject(p1);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }

        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            Person p2 = (Person)in.readObject();
            System.out.println(p2);
        } catch (IOException | ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }
}

class Person implements Serializable {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("name", name);
        fields.put("age", age);
        out.writeFields();
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream.GetField fields = in.readFields();
        name = (String)fields.get("name", "defaultName");
        age = fields.get("age", 18);
    }
}
```
```
Person [name=Mike, age=30]
```
We can also use custom serialization with `Externalizable` interface. In this case you have to override 2 methods + add no-arg constructor
```java
import java.io.*;

public class App {
    public static void main(String[] args){
        File file = new File("src/main/java/com/java/test/text");

        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            Person p1 = new Person("Mike", 30);
            out.writeObject(p1);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }

        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            Person p2 = (Person)in.readObject();
            System.out.println(p2);
        } catch (IOException | ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }
}


class Person implements Externalizable {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

    // you need to have no-arg constructor, otherwise you will get exception: InvalidClassException: com.java.test.Person; no valid constructor
    public Person() {
        System.out.println("Person no-arg constructor called");
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException{
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        name = (String)in.readObject();
        age = in.readInt();
    }
}
```
```
Person no-arg constructor called
Person [name=Mike, age=30]
```
**The interesting note is although you can change state in no-arg constructor
```java
public Person() {
    System.out.println("Person no-arg constructor called");
    name = "cool";
    age = 1;
}
```
Java will overwrite all state that you set here, by using readExternal.
Notice that different form `Serializable`, in case of `Externalizable` if another class is extending your class, it should also reimplement this interface.
The main advantage of `Externalizable` is that it doesn't call chain of metadata-parentMetadata-data-parentData is not called, but only your methods are called, that's why you need to have no-arg constructor, cause we don't write meta-info.
If your class extends from non serializable class it should have default constructor, otherwise can't reconstruct object
```java
import java.io.*;

public class App {
    public static void main(String[] args){
        File file = new File("src/main/java/com/java/test/text");

        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            Person p1 = new Person("Mike", 30);
            out.writeObject(p1);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }

        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            Person p2 = (Person)in.readObject();
            System.out.println(p2);
        } catch (IOException | ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }
}

class Human{
    protected String type;
    public Human(String type){
        this.type = type;
    }
    public Human(){}
}
class Person extends Human implements Serializable {
    private String name;
    private int age;
    public Person(String name, int age) {
        super("person");
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", type="+type+"]";
    }
}
```
Rules of changing serialized classes:
* if you don't set serial version and  doing something innocuous (like adding new field) when deserialized you will get error, cause once you change your class, jvm will regenerate serial number
* if you add new field and initialize them, since deserialization not running constructor and initialization it would be reconstructed to default value (0 for primitive, false for boolean, null for reference)
* if some fields are removed from new class version, they just ignored during deserialization.
Don't confuse:
* java serialization - native serialization out-of-the-box designed by java (all you need is to implement `Serializable`). Convert object into binary representation.
* SBE (simple binary encoding) - low-latency high-speed custom serialization library. Also stores objects in binary format. There are other options available (XML/JSON - text based, Protobuffer/Thrift/Avro - binary), yet SBE is faster low-latency apps. It's specifically designed for financial streamig data, where most fields are either int/log or enums, and it's best for this purpose where all fields have fixed size. We can store Strings, but they stored in the end of the message (and if your message contains mostly strings of variables sizes, then SBE doesn't give you much speed improvements)
SBE design:
* xml schema - set layout & data type of message (which fields would be used)
* compiler take the schema and generate optimized java file with java code, you use SBEtool to generate encoder/decoder java classes, that would convert binary back and forth
* message - generated on runtime based on java file
* no garbage - sbe use direct buffer, so no GC happening during message creation
* use array as underlying storage, so cpu cache prefetching can expedite the process
SBE better then java serialization:
* no extra work to serialize parent classes, pay attention to transient, class initializers and so on
Here we just have simple set of fields as message, and all fields need to be serialized
* When deserizlise, we create many objects, then GC may start to clean
Here we use non-heap memory, so no GC pause
* Here we use best java practices like prefetching so it expedite overall serialize/deserialize process

###### XML serialization
JAXB - java architecture xml binding - ability to dump object into xml and construct object from xml first add following into your pom.xml
```
<dependency>
  <groupId>javax.xml.bind</groupId>
  <artifactId>jaxb-api</artifactId>
  <version>2.1</version>
</dependency>
<dependency>
  <groupId>com.sun.xml.bind</groupId>
  <artifactId>jaxb-impl</artifactId>
  <version>2.2.11</version>
</dependency>
<dependency>
  <groupId>com.sun.xml.bind</groupId>
  <artifactId>jaxb-core</artifactId>
  <version>2.2.11</version>
</dependency>
```
```java
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

public class App {
    public static void main(String[] args){
        File file = new File("src/main/java/com/java/test/text");
        Person p1 = new Person("John", 30);

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(p1, file);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Person p2 = (Person)unmarshaller.unmarshal(file);
            System.out.println(p2);
        } catch (JAXBException ex){
            throw new RuntimeException(ex);
        }
    }
}

@XmlRootElement
class Person{
    @XmlElement
    private String name;
    @XmlElement
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person(){
        System.out.println("Person no-arg constructor");
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}
```
```
Person no-arg constructor
Person [name=John, age=30]
```
xml file
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<person>
    <name>John</name>
    <age>30</age>
</person>
```
When constructing object from xml, no-arg constructor is called.

###### JSON serialization
Add to pom.xml
```
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.5</version>
</dependency>
```
```java
import com.google.gson.Gson;

public class App {
    public static void main(String[] args){
        Gson gson = new Gson();
        Person p1 = new Person("John", 30);
        String json = gson.toJson(p1);
        System.out.println(json);
        Person p2 = gson.fromJson(json, Person.class);
        System.out.println(p2);
    }
}

class Person{
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person(){
        System.out.println("Person no-arg constructor");
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}
```
```
{"name":"John","age":30}
Person no-arg constructor
Person [name=John, age=30]
```
As you see, again we constructing object from json, we call no-arg constructor. Yet here we can comment no-arg constructor and it will work fine.


#### IO and NIO
###### InputStream/OutputStream and Reader/Writer
There are 2 types of streams in java. Those with name `InputStream/OutputStream` and `Reader/Writer`. The difference is that `InputStream/OutputStream` work with all type of binary data (including chars and strings), 
but `Reader/Writer` works only with characters and strings. There is an advantage to use Reader/Writer streams when working with strings, cause you can use writer class to put string into file without worrying underlying encoding logic.
`StringReader` - reader that take `String` as input parameter. Useful when you have a string and need to convert in into `Reader` object.
If we try to open non-existing file with `FileInputStrem/FileReader` we will got `FileNotFoundException`. But if we open it with `FileOutputStream/FileWriter` they will create file.
This is very logical, cause input stream - for reading, you need file first to read, but output - for writing, even if file doesn't exist you can create it and write into it.
```java
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class App{
    public static void main(String[] args) {
        File file = new File("nonExistingFile");
        // delete in case file already exists
        file.delete();
        try(InputStream in = new FileInputStream(file)){
        } catch (IOException ex){
            System.out.println("InputStream => " + ex);
        }
        try(OutputStream out = new FileOutputStream(file)){
        } catch (IOException ex){
            System.out.println("OutputStream => " + ex);
        }
    }
}
```
```
InputStream => java.io.FileNotFoundException: nonExistingFile (No such file or directory)
```
File has 2 separators:
`File.separator` - `/` for linux - separates files (/path/to/your/file)
`File.pathSeparator` - `:` for linux - separates paths (/path/to/jar1.jar:/path/to/jar2.jar:/path/to/jar3.jar)
We can prohibit file writing by setting `setReadOnly()` or `setWritable(false)` on `File` instance.
```java
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception{
        String path = "src/main/java/com/java/test/text";
        File file = new File(path);
        file.setReadOnly();
        // or equivalent
        file.setWritable(false);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){ // we can pass file or path
            writer.write("hello world");
        }
    }
}
```
```
Exception in thread "main" java.io.FileNotFoundException: src/main/java/com/java/test/text (Permission denied)
```
`java.io.File`:
* `mkdir` - create one directory (if one of parent directory missing, return false)
* `mkdirs` - create all non-existent parent directories

###### Console
`Console` method `readPassword` return array of chars instead of strings. Generally it’s better to use `char[]` instead of `String` to store password, cause if one get dump he will get all strings in String pool. But with char array you can remove password by overwriting char with some garbage data.
Pay attention that `Console` object is null when execute from IDE. You need to run it manually from console. In order for `Console` to work, java should be run from interactive console without redirecting input/output.
With `Console` you can both read and write to/from console.
```java
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;

class App{
    public static void main(String[] arr){
        Console console = System.console();
        PrintWriter writer = console.writer();
        Reader reader = console.reader();
        System.out.println("reader class => " + reader.getClass());
        console.format("hello").format("world").format("!");
        // reading using 2 standard methods
        writer.println("enter your username: ");
        String name = console.readLine();
        writer.println("enter your password: ");
        char[] password = console.readPassword();
        //read using standard read of Reader
        try{
            int n = reader.read();
            writer.println("char number of letter => " + n);
            char[] buffer = new char[10];
            reader.read(buffer);
            writer.println("buffer => " + Arrays.toString(buffer));
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
        writer.println(name + "/" + Arrays.toString(password));
    }
}
```
```
reader class => class java.io.Console$LineReader
helloworld!enter your username: admin
enter your password: 
admin [1, 2, 3, 4]
```

###### 5 ways to read file
```java
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class App {
    public static void main(String[] args) {
        File file = new File( "src/main/java/com/java/test/source");
        // read one byte at a time
        try (InputStream in = new FileInputStream(file)) {
            int b;
            while ((b = in.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
        // ready byte by byte with StreamReader+Stream
        try (Reader in = new InputStreamReader(new FileInputStream(file))) {
            int b;
            while ((b = in.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
        // read byte by byte with FileReader
        try (Reader in = new FileReader(file)) {
            int b;
            while ((b = in.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
        // read array of bytes (of fixed size)
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[5];
            while (in.read(buffer) > 0) {
                System.out.print(new String(buffer));
            }
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
        // read line by line
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = in.readLine()) != null) {
                System.out.print(s);
            }
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
    }
}
```
```
hello worldhello worldhello worldhello worldworlhello world
```

###### NIO channels
Java NIO works above IO, channel is like stream but non-blocking (although FileChannel is blocking). We can easily copy content form one file to another.
```java
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class App {
    public static void main(String[] args) {
        try(FileChannel in = FileChannel.open(Paths.get("src/main/java/source"));
            FileChannel out = FileChannel.open(Paths.get("src/main/java/dest"), StandardOpenOption.WRITE)){
            ByteBuffer buf = ByteBuffer.allocate(10);
            while (in.read(buf) != -1) {
                System.out.println(new String(buf.array(), 0, buf.position()));
                // since we read from 0 to n, we should flip so we can write in correct order
                buf.flip();
                out.write(buf);
                buf.clear();
            }
        } catch (IOException ex){
            System.out.println("ERR: " + ex);
        }
    }
}
```
```
hello worl
d, I'm her
e
```
You can also get it from `FileInputStream/FileOutputStream` and `RandomAccessFile`
```java
FileChannel in = new FileInputStream(absoluteInPath).getChannel();
FileChannel out = new FileOutputStream(absoluteOutPath).getChannel();

FileChannel in = new RandomAccessFile(absoluteInPath, "r").getChannel();
FileChannel out = new RandomAccessFile(absoluteOutPath, "rw").getChannel();
```

###### Directory searching
There are 3 ways for searching
```java
import java.nio.file.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class App {
    public static void main(String[] args) throws Exception{
        Path path = Paths.get("src");
        //DirectoryStream - is iterable and return iterator
        DirectoryStream<Path> ds =  Files.newDirectoryStream(path.resolve("main/java"), "*.{txt,word}");
        ds.forEach(System.out::println);
        // convert DirectoryStream to Stream
        Stream<Path> dsStream = StreamSupport.stream(ds.spliterator(), false);
        // just list all files/directories inside current directory, using newDirectoryStream inside
        Stream<Path> list =  Files.list(path.resolve("main/java"));
        System.out.println();
        Stream<Path> walk = Files.walk(path).filter(p->p.toString().endsWith(".txt"));
        walk.forEach(System.out::println);
        System.out.println();
        Stream<Path> find = Files.find(path, 10, (p, a)->p.toString().endsWith(".txt"));
        find.forEach(System.out::println);
    }
}
```
```
src/main/java/test.txt
src/main/java/test.word

src/main/java/test.txt

src/main/java/test.txt
```

###### Path resolve and relativise
There are a few methods relating to `Path`:
* `resolve` - try bo combine 2 paths into one. If second path absolute, it uses it
* `relativise` - try to get relative path of other against current
* `Paths.get` internally use `Path.of` - static function.
```java
import java.nio.file.*;

public class App {
    public static void main(String[] args) {
        System.out.println("subpath(1,3) => " + Paths.get("/a/b/c/d").subpath(1,3));
        // if start<=end we got IllegalArgumentException
        try{
            System.out.println("subpath(1,1) => " + Paths.get("/a/b/c/d").subpath(1,1));
        } catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
        System.out.println();
        System.out.println("normalize(/a/b/../../d) => " + Paths.get("/a/b/../../d").normalize());
        // if we can't normalize path, display it as it is
        System.out.println("normalize(../../d) => " + Paths.get("../../d").normalize());
        System.out.println();
        // resolve - tried to combine 2 paths into one
        System.out.println(Paths.get("a/b/c").resolve(Paths.get("d/e/f")));
        System.out.println(Paths.get("a/b/c").resolve(Paths.get("/d/e/f")));
        System.out.println(Paths.get("/a/b/c").resolve(Paths.get("/d/e/f")));
        System.out.println(Paths.get("a/b/c").resolve(Paths.get("a/b/c/d/e")));
        System.out.println();
        // resolveSibling - same as resolve, just remove last path
        System.out.println(Paths.get("a/b/c").resolveSibling(Paths.get("d/e/f")));
        System.out.println();
        // try to find other pass against current
        System.out.println(Paths.get("a/b/c").relativize(Paths.get("d/e/f")));
        System.out.println(Paths.get("a/b/c").relativize(Paths.get("a/b/c/d/e")));
        System.out.println(Paths.get("a/b/c").relativize(Paths.get("a")));

        System.out.println();
        // pay attention that root is the first element (so not included into names)
        Path path = Paths.get("/home/diman/projects/my/ocpjp/src/main/java/com/java/test/text");
        System.out.println("root => " + path.getRoot());
        for(int i = 0; i < path.getNameCount(); i++){
            System.out.println(i + " => " + path.getName(i));
        }
    }
}
```
```
subpath(1,3) => b/c
java.lang.IllegalArgumentException

normalize(/a/b/../../d) => /d
normalize(../../d) => ../../d

a/b/c/d/e/f
/d/e/f
/d/e/f
a/b/c/a/b/c/d/e

a/b/d/e/f

../../../d/e/f
d/e
../..

root => /
0 => home
1 => diman
2 => projects
3 => my
4 => ocpjp
5 => src
6 => main
7 => java
8 => com
9 => java
10 => test
11 => text
```

`relativize` - is inverse of `resolve`
```java
import java.nio.file.*;

public class App {
    public static void main(String[] args) {
        Path p1 = Paths.get("a/b/c");
        Path p2 = Paths.get("a/b/d");
        Path relative = p1.relativize(p2);
        Path resolved = p1.resolve(relative).normalize();
        System.out.println(relative);
        System.out.println(resolved);
    }
}
```
```
../d
a/b/d
```

###### mark, reset, skip
There are a few methods to move `InputStream` back or forward:
* `mark(int limit)` - mark the current point with number of bytes to save
* `reset()` - return to the marked point
* `skip(int n)` - skip n bytes 
```java
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class App {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/source"); // ABCDEF
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            System.out.println((char) in.read()); // A
            int count = 3;
            in.mark(count);
            for (int i = 0; i < count; i++) {
                System.out.print((char) in.read()); // BCD
            }
            System.out.println((char) in.read()); // E
            in.reset(); // return to the mark position
            System.out.println((char) in.read()); // B
            in.skip(3);
            System.out.println((char) in.read()); // F
            System.out.println(in.read()); // -1, end of stream
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
    }
}
```
```
A
BCDE
B
F
-1
```

###### Files copy, move, delete
There are a few useful methods in `Files` class:
* `Files.copy` - copy one file into another, if another file doesn't exists it's created, if exists `FileAlreadyExistsException` throws
* `Files.move` - move (copy & delete)
* `Files.delete` - delete a file, if file doesn't exist throws `FileNotFoundException`.
Notice that they both don't distinguish between directory & file. So if your destination valid directory, they will think that your directory is existing file and throw `FileAlreadyExistsException`. This is differ from linux command line where if you pass second param as directory, shell automatically copy/move file into this directory. If you pass third param as `StandardCopyOption.REPLACE_EXISTING`, then it will try to replace but realize that i's impossible to overwrite directory with file and throws `DirectoryNotEmptyException`.
If source and dest are the same, no exception for both copy and move is thrown.
```java
import java.nio.file.*;

public class App {
    public static void main(String[] args) throws Exception {
        Path source = Paths.get("src/main/java/source");
        Path dest = Paths.get("src/main/java/com/java/test/dest");

        try{
            Files.copy(dest, source);
        } catch (FileAlreadyExistsException ex){
            System.out.println(ex);
        }
        Files.copy(dest, source, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(dest, source, StandardCopyOption.COPY_ATTRIBUTES); // won't replace throws FileAlreadyExistsException
        Files.copy(dest, source, StandardCopyOption.ATOMIC_MOVE); // not works for copy, throws UnsupportedOperationException

        try{
            Files.move(source, dest);
        } catch (FileAlreadyExistsException ex){
            System.out.println(ex);
        }
        Files.move(source, dest, StandardCopyOption.ATOMIC_MOVE);
        Files.move(source, dest, StandardCopyOption.REPLACE_EXISTING);
    }
}
```

###### Data traversal
We can use standard BFS and DFS to traverse data, or could use standard implementation of `walk` (work as dfs) method
```java
public class App {
    public static void main(String[] args) {
        String absolutePath = "/home/diman/projects/my/mvnjava/files";
        Files.walk(Paths.get(absolutePath)).forEach(System.out::println);
        System.out.println();
        dfs(absolutePath).forEach(System.out::println);
        System.out.println();
        bfs(absolutePath).forEach(System.out::println);
    }
    private static List<String> dfs(String absolutePath) {
        File file = new File(absolutePath);
        List<String> files = new ArrayList<>();
        files.add(file.getAbsoluteFile().toString());
        Deque<File> stack = new ArrayDeque<>(Arrays.asList(file.listFiles()));
        while (stack.size() > 0) {
            var f = stack.pollLast();
            files.add(f.getAbsoluteFile().toString());
            if (f.listFiles() != null) {
                stack.addAll(Arrays.asList(f.listFiles()));
            }
        }
        return files;
    }
    private static List<String> bfs(String absolutePath) {
        File file = new File(absolutePath);
        List<String> files = new ArrayList<>();
        files.add(file.getAbsoluteFile().toString());
        Deque<File> queue = new ArrayDeque<>(Arrays.asList(file.listFiles()));
        while (queue.size() > 0) {
            var f = queue.pollFirst();
            files.add(f.getAbsoluteFile().toString());
            if (f.listFiles() != null) {
                queue.addAll(Arrays.asList(f.listFiles()));
            }
        }
        return files;
    }
}
```
```
/home/diman/projects/my/mvnjava/files
/home/diman/projects/my/mvnjava/files/test
/home/diman/projects/my/mvnjava/files/test/java
/home/diman/projects/my/mvnjava/files/main
/home/diman/projects/my/mvnjava/files/main/scala
/home/diman/projects/my/mvnjava/files/main/java
/home/diman/projects/my/mvnjava/files/main/java/source

/home/diman/projects/my/mvnjava/files
/home/diman/projects/my/mvnjava/files/main
/home/diman/projects/my/mvnjava/files/main/java
/home/diman/projects/my/mvnjava/files/main/java/source
/home/diman/projects/my/mvnjava/files/main/scala
/home/diman/projects/my/mvnjava/files/test
/home/diman/projects/my/mvnjava/files/test/java

/home/diman/projects/my/mvnjava/files
/home/diman/projects/my/mvnjava/files/test
/home/diman/projects/my/mvnjava/files/main
/home/diman/projects/my/mvnjava/files/test/java
/home/diman/projects/my/mvnjava/files/main/scala
/home/diman/projects/my/mvnjava/files/main/java
/home/diman/projects/my/mvnjava/files/main/java/source
```
We can list directory's files & subdirectories with 2 ways (with old `java.io.File` api and new `java.nio.file.Files`)
```java
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        String absolutePath = "src/main/java/com/java/test";
        File file = new File(absolutePath);
        for(var f: file.listFiles()){
            System.out.println(f.getName());
        }
        System.out.println();
        Path path = Paths.get(absolutePath);
        try {
            Files.list(path)
                .map(f->f.getFileName())
                .forEach(System.out::println);
        } catch (IOException ex){
            System.out.println("ERR: " + ex);
        }
    }
}
```
```
dest
source
App.java

dest
source
App.java
```

###### BasicFileAttributes
Can be useful when you want to access a few attributes at the same time, so you don’t have to call `Files.isAttr` a few times (every time you access attribute with Files.get... it read file from filesystem). Moreover there are a few properties that are system attributes and there is no comparable methods in `Files.` api.
```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class App {
    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/com/java/test/text");
        try{
            // throws IOException
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("BasicFileAttributes implementation => " + attributes.getClass().getName());
            // throws IOException
            System.out.println("lastModifiedTime => " + attributes.lastModifiedTime() + " : " + Files.getLastModifiedTime(path));
            System.out.println("isRegularFile => " + attributes.isRegularFile() + " : " + Files.isRegularFile(path));
            System.out.println("isDirectory => " + attributes.isDirectory() + " : " + Files.isDirectory(path));
            System.out.println("isSymbolicLink => " + attributes.isSymbolicLink() + " : " + Files.isSymbolicLink(path));
            // throws IOException
            System.out.println("size => " + attributes.size() + " : " + Files.size(path));
            System.out.println("isOther => " + attributes.isOther());
            System.out.println("lastAccessTime => " + attributes.lastAccessTime());
            System.out.println("creationTime => " + attributes.creationTime());
            System.out.println("fileKey => " + attributes.fileKey());
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
```
```
BasicFileAttributes implementation => sun.nio.fs.UnixFileAttributes$UnixAsBasicFileAttributes
lastModifiedTime => 2022-10-24T08:54:10.634857Z : 2022-10-24T08:54:10.634857Z
isRegularFile => true : true
isDirectory => false : false
isSymbolicLink => false : false
size => 0 : 0
isOther => false
lastAccessTime => 2022-10-24T08:54:10.634857Z
creationTime => 2022-10-24T08:54:10.634857Z
fileKey => (dev=fd01,ino=41683554)
```
You can change time of file with `setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)`
```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

public class App {
   public static void main(String[] args) {
       Path path = Paths.get("src/main/java/com/java/test/text");
       try{
           BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
           BasicFileAttributes attributes = view.readAttributes();
           FileTime time = FileTime.from(Instant.now());
           System.out.println("old time => " + attributes.creationTime());
           if (attributes.size() > 0 && attributes.creationTime().toMillis() > 0) {
               view.setTimes(time, null, null);
           }
           System.out.println("new time => " + Files.getLastModifiedTime(path));
       } catch (IOException ex){
           throw new RuntimeException(ex);
       }
   }
}
```
```
old time => 2019-11-22T11:08:55.116953Z
new time => 2019-11-22T11:10:59.370485Z
```
We can also get `BufferedReader` directly from `Files.newBufferedReader`, and work with string lines instead of bytes. We can also read all lines into memory all at once with `Files.readAllLines`.
```java
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/com/java/test/text");
        try(BufferedReader in = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String s;
            while((s = in.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
        try{
            List<String> allLines = Files.readAllLines(path);
            for(var s: allLines){
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
    }
}
```
```
hello world
hello world
```
`Files.readAllLines` loads the whole file into memory, which is not good, we can use `lines` method, that return a stream which lazily loads lines from file. `BufferedReader` has also method `lines` that returns stream.
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/com/java/test/text");
        try {
            Files.lines(path).forEach(System.out::println);
            System.out.println();
            new BufferedReader(new FileReader(path.toFile())).lines().forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println("ERR: " + ex);
        }
    }
}
```
```
hello world
hello world
```
Don't confuse:
* `java.io.FileNotFoundException`  - file not found, or no permission to access it.
* `java.nio.file.NoSuchFileException` - file not found.
* `java.nio.file.AccessDeniedException` - no permission to access file
```java
import java.io.*;
import java.nio.file.*;

public class App{
    public static void main(String[] args) throws Exception{
        BufferedReader br1 = new BufferedReader(new FileReader(new File("abc"))); // throws FileNotFoundException
        BufferedReader br2 = Files.newBufferedReader(Paths.get("abc")); // throws NoSuchFileException if file not found, AccessDeniedException - no permission to open
    }
}
```
We can write to file using nio. `Files.writeString`.
```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        String str = "hello world";
        Path path = Paths.get("src/main/java/com/java/test/text");
        try{
            Files.writeString(path, str, StandardCharsets.UTF_8);
        } catch (IOException ex){
            System.out.println("ERR: " + ex);
        }
    }
}
```
You can take a String object and turn it into `Reader` stream using `StringReader`.
```java
import java.io.*;

public class App {
    public static void main(String[] args){
        String str = "hello world";
        try(StringReader stringReader = new StringReader(str);){
            int i;
            while ((i = stringReader.read()) != -1){
                System.out.print((char)i);
            }
        } catch (IOException ex){
            System.out.println(ex);
        }
        System.out.println();
        /**
         * It won't work in one try/catch, the reason is that either stringreader or buffered reader can read
         * once string reader will real all chars, buffered reader will read null
         */
        try(StringReader stringReader = new StringReader(str);
            BufferedReader bufferedReader = new BufferedReader(stringReader);){
            System.out.println(bufferedReader.readLine());
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
}
```
```
hello world
hello world
```
Since `Reader` works with chars that is subset of bytes we can easily convert `InputStream` to `Reader` (and `OutputStrem` to `Writer`), but not vice versa
This is due to the fact, that `InsputStrem` is byte[], and `Reader` is char[].
You can easily convert byte to char, but if you convert char to byte you have precision lost.
```
byte b;
char c = b >= 0 ? b : b + 255;
char c = b & 255
```
byte - 1 byte, char - 2 bytes. 
```java
import java.io.*;

public class App {
    public static void main(String[] args){
        String absolutePath = "src/main/java/com/java/test/text";
        File file = new File(absolutePath);
        try(InputStream inputStream = new FileInputStream(file)){
            Reader reader = new InputStreamReader(inputStream);
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
}
```
`RandomAccessFile` - you can read and write at arbitrary position. Second params to constructor should be mode(read/write).
Since it implements `DataOutput` interface, as `DataOutputStream` does, they both have all methods to write like
write, writeUTF, writeBoolean and so on... But there is no method writeString.
```
r   - read, trying to write thows IOException
rw  - read and write
rwd	- read and write syncronously. All updated flushed to the disk immediately
rws	- same as previous + updates to meta data flushed to the disk immediately
```
```java
import java.io.*;
import java.util.Arrays;

public class App {
    public static void main(String[] args){
        String path = "src/main/java/com/java/test/text";
        try(RandomAccessFile raf = new RandomAccessFile(path, "rw")){
            System.out.println("pointer => " + raf.getFilePointer());
            System.out.print("read 3 bytes one by one => ");
            for(int i = 0; i < 3; i++){
                System.out.print((char)raf.read());
            }
            System.out.println();
            System.out.println("pointer => " + raf.getFilePointer());
            // set pointer to position 5
            raf.seek(5);
            System.out.println("pointer => " + raf.getFilePointer());
            byte[] readBytes = new byte[10];
            raf.read(readBytes);
            System.out.println("readBytes => " + new String(readBytes));
            System.out.println("pointer => " + raf.getFilePointer());
            String str = "abc";
            for(int i = 0; i < 3; i++){
                raf.write(str.charAt(i));
            }
            byte[] writeBytes = "hello world".getBytes();
            raf.write(writeBytes);
            raf.seek(0);
            readBytes = new byte[50];
            int readByteLength = raf.read(readBytes);
            System.out.println("final text => " + new String(Arrays.copyOf(readBytes, readByteLength)));
        } catch (IOException ex){
            System.out.println("ERR: " + ex);
        }
    }
}
```
```
pointer => 0
read 3 bytes one by one => Thi
pointer => 3
pointer => 5
readBytes => is very im
pointer => 15
final text => This is very imabchello world                 
```
If file doesn't exists and:
* you are trying to open file for reading with InputStream/Reader/RandomAccessFile(r) - it will throw `FileNotFoundException`
* you are trying to open it for writing with OutputStream/Writer/RandomAccessFile(rw) - it will try to create it, and if can't throw `FileNotFoundException` (the reason can be if parent directory doesn't exists or app has no rights to write to directory).
If you want to be sure that file always exist you can use following code
```java
import java.io.*;

class App{
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/nonExistingDir/nonExistingFile");
        /**
         * createNewFile - may fail if no parent directory exists
         * don't use getParent() - it will return a string
         */
        file.getParentFile().mkdirs();
        try{
            file.createNewFile();
        } catch (IOException ex){
            System.out.println("createNewFile => " + ex);
        }
    }
}
```
Code demonstrating `FileNotFoundException` when trying to open non-existing file, and non-existing directory (output streams can't create it in this case).
```java
import java.io.*;

class App{
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/nonExistingDir/nonExistingFile");
        /**
         * constructor of RandomAccessFile throws FileNotFoundException, but close method throws IOException
         * since FileNotFoundException extends IOException => we catch only IOException
         */
        try(RandomAccessFile raf = new RandomAccessFile(file, "r");){
        } catch (IOException ex){
            System.out.println("RandomAccessFile(r) => " + ex);
        }
        /**
         * constructor of FileInputStream throws FileNotFoundException, but close method throws IOException
         * since FileNotFoundException extends IOException => we catch only IOException
         */
        try(InputStream is = new FileInputStream(file);){
        } catch (IOException ex){
            System.out.println("InputStream => " + ex);
        }
        /**
         * FileReader extends InputStreamReader
         * constructor of FileReader throws FileNotFoundException, but close method in InputStreamReader throws IOException
         * since FileNotFoundException extends IOException => we catch only IOException
         */
        try(Reader reader = new FileReader(file);){
        } catch (IOException ex){
            System.out.println("Reader => " + ex);
        }
        /**
         * constructor of RandomAccessFile throws FileNotFoundException, but close method throws IOException
         * since FileNotFoundException extends IOException => we catch only IOException
         */
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw");){
        } catch (IOException ex){
            System.out.println("RandomAccessFile(rw) => " + ex);
        }
        /**
         * constructor of FileOutputStream throws FileNotFoundException, but close method throws IOException
         * since FileNotFoundException extends IOException => we catch only IOException
         */
        try(OutputStream out = new FileOutputStream(file);){
        } catch (IOException ex){
            System.out.println("OutputStream => " + ex);
        }
        /**
         * FileWriter extends OutputStreamWriter
         * constructor of FileWriter throws FileNotFoundException, but close method in OutputStreamWriter throws IOException
         * since FileNotFoundException extends IOException => we catch only IOException
         */
        try(Writer writer = new FileWriter(file);){
        }catch (IOException ex){
            System.out.println("Writer => " + ex);
        }
        /**
         * PrintStream extends FilterOutputStream, but overrides close and handle IOException
         * so only FileNotFoundException is left unchecked
         */
        try(PrintStream ps = new PrintStream(file);){
        }catch (FileNotFoundException ex){
            System.out.println("PrintStream => " + ex);
        }
        /**
         * PrintWriter extends Writer, but overrides close and handle IOException
         * so only FileNotFoundException is left unchecked
         */
        try(PrintWriter pw = new PrintWriter(file);){
        }catch (FileNotFoundException ex){
            System.out.println("PrintWriter => " + ex);
        }
    }
}
```
```
RandomAccessFile(r) => java.io.FileNotFoundException: src/main/java/com/java/test/nonExistingDir/nonExistingFile (No such file or directory)
InputStream => java.io.FileNotFoundException: src/main/java/com/java/test/nonExistingDir/nonExistingFile (No such file or directory)
Reader => java.io.FileNotFoundException: src/main/java/com/java/test/nonExistingDir/nonExistingFile (No such file or directory)
RandomAccessFile(rw) => java.io.FileNotFoundException: src/main/java/com/java/test/nonExistingDir/nonExistingFile (No such file or directory)
OutputStream => java.io.FileNotFoundException: src/main/java/com/java/test/nonExistingDir/nonExistingFile (No such file or directory)
Writer => java.io.FileNotFoundException: src/main/java/com/java/test/nonExistingDir/nonExistingFile (No such file or directory)
PrintStream => java.io.FileNotFoundException: src/main/java/com/java/test/nonExistingDir/nonExistingFile (No such file or directory)
PrintWriter => java.io.FileNotFoundException: src/main/java/com/java/test/nonExistingDir/nonExistingFile (No such file or directory)
```

Don't confuse:
* DataOutputStream - to print data as bytes. So if you use any method like `writeInt/writeLong` - they will not write actual int/long values, but would write int/long into output, but it would be converted into bytes and store as bytes and if you open file, you will see weird characters.
* PrintWriter - print strings into output.
```java
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.IntStream;

public class App {
  public static void main(String[] args) throws IOException {
    File file = new File("src/main/java/com/java/test/text.txt");

    String value = "ABCD";
    int sum = IntStream.range(0, value.length())
            .limit(4)
            .map(i -> value.charAt(i) << (i * 8))
            .sum();

    int reversed = Integer.reverseBytes(sum);

    try(DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
      System.out.println("Writing to file: originalInt=" + sum + " reversed=" + reversed);
      // Writes an int to the underlying output stream as four bytes, high byte first
      writer.writeInt(reversed);
      writer.flush();
    }
  }
}
```
If you run:
* console output: `Writing to file: originalInt=1145258561 reversed=1094861636`
* file created `text.txt` would store `ABCD`
* pay attention to `Integer.reverseBytes` we have to reverse bytes, otherwise we would write `DCBA`, because `writeInt/writeLon` right into output stream, from right to left. This is how they work.

`PrintStream` vs `PrintWriter` - they both are used to write data to files. Difference the same as Writer/Stream. One for bytes, other for chars. Yet both have methods to write chars. `System.out` and `System.err` using PrintStream to write to console.
PrintWriter has method `printf` - (overloaded can take just String or Locale and String) - that returns PrintWriter with specific locale and print input string param.
```java
import java.io.*;
import java.util.Locale;

class App{
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/text");
        try(PrintStream writer = new PrintStream(file);){
            writer.print(1);
            writer.println("hello");
        }catch (FileNotFoundException ex){
            System.out.println("PrintStream => " + ex);
        }
        try(PrintWriter pw = new PrintWriter(file);){
            pw.print(1);
            pw.println("hello");
            if (pw.checkError()) {
                System.out.println("Error during writing");
            }
            // if we want to set special locale and format
            pw.printf(Locale.US, "").println();
            pw.printf("This is a %s program", "mystr"); // this will print formatted text and return printWriter
        }catch (FileNotFoundException ex){
            System.out.println("PrintWriter => " + ex);
        }
    }
}
```

`DataOutputStream` - is used for writing data to file. It works the same as `PrintStream/Writer`, except functions are names `write-` and all throws `IOException`.
`DataInputStream` - is used for reading data from file. It has the same methods like `DataOutputStream` with `read-`.
Method `size()` can be used to determine current pointer.
```java
import java.io.*;

class App{
    private static int size;
    private static void print(String str, int position) {
        System.out.println(str + (position-size) + ", size => " + position);
        size = position;
    }
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/text");
        try(DataOutputStream writer = new DataOutputStream(new FileOutputStream(file));){
            int size = writer.size();
            writer.writeByte(1);
            print("writeByte(1) => ", writer.size());
            writer.writeShort(2);
            print("writeShort(2) => ", writer.size());
            writer.writeInt(3);
            print("writeInt(3) => ", writer.size());
            writer.writeLong(4L);
            print("writeLong(4L) => ", writer.size());
            writer.writeFloat(1.5F);
            print("writeFloat(1.5F) => ", writer.size());
            writer.writeDouble(2.5);
            print("writeDouble(2.5) => ", writer.size());
            writer.writeBoolean(true);
            print("writeBoolean(true) => ", writer.size());
            writer.write(123);
            print("write(123) => ", writer.size());
            writer.write("bytes".getBytes());
            print("write(bytes) => ", writer.size());
            writer.writeChars("chars");
            print("writeChars(chars) => ", writer.size());
            writer.writeUTF("abc");
            print("writeUTF(abc) => ", writer.size());
        }catch (IOException ex){
            System.out.println("DataOutputStream => " + ex);
        }
        try(DataInputStream reader = new DataInputStream(new FileInputStream(file))){
            System.out.println();
            System.out.println(reader.readByte());
            System.out.println(reader.readShort());
            System.out.println(reader.readInt());
            System.out.println(reader.readLong());
            System.out.println(reader.readFloat());
            System.out.println(reader.readDouble());
            System.out.println(reader.readBoolean());
            System.out.println(reader.read());
            byte[] bytes = new byte[5];
            reader.read(bytes);
            System.out.println(new String(bytes));
            for(int i = 0; i < 5; i++){
                System.out.print(reader.readChar());
            }
            System.out.println();
        }catch (IOException ex){
            System.out.println("DataOutputStream => " + ex);
        }
    }
}
```
```
writeByte(1) => 1, size => 1
writeShort(2) => 2, size => 3
writeInt(3) => 4, size => 7
writeLong(4L) => 8, size => 15
writeFloat(1.5F) => 4, size => 19
writeDouble(2.5) => 8, size => 27
writeBoolean(true) => 1, size => 28
write(123) => 1, size => 29
write(bytes) => 5, size => 34
writeChars(chars) => 10, size => 44
writeUTF(abc) => 5, size => 49

1
2
3
4
1.5
2.5
true
123
bytes
chars
```
Since `BufferedOutputStream` and `DataOutputStream` takes `OutputStream` as constructor parameter, we can pass instances of each other and `FileOutputStream` into their constructors.
Cause we pass the same object, position is stored, and we write from that position.
```java
import java.io.*;
import java.nio.file.Files;

class App{
    public static void main(String[] args) {
        File file = new File("src/main/java/com/java/test/text"); // Contents of file: fosbosdospspw
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("fos".getBytes());
            fos.flush();
            System.out.println("size => " + Files.size(file.toPath()));

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write("bos".getBytes());
            bos.flush();
            System.out.println("size => " + Files.size(file.toPath()));

            DataOutputStream dos = new DataOutputStream(bos);
            dos.write("dos".getBytes());
            bos.flush();
            System.out.println("size => " + Files.size(file.toPath()));

            PrintStream ps = new PrintStream(dos);
            ps.write("ps".getBytes());
            ps.flush();
            System.out.println("size => " + Files.size(file.toPath()));

            PrintWriter pw = new PrintWriter(ps);
            pw.write("pw"); // printwriter can take just string
            pw.flush();
            System.out.println("size => " + Files.size(file.toPath()));
        }catch (IOException ex){
            System.out.println("DataOutputStream => " + ex);
        }
    }
}
```
```
size => 3
size => 6
size => 9
size => 11
size => 13
```
We can copy from one file to another using `FileInputStream` => `FileOutputStream`
```java
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        File source = new File("src/main/java/source");
        File dest = new File( "src/main/java/dest");
        try (FileInputStream is = new FileInputStream(source);
            FileOutputStream os = new FileOutputStream(dest);) {
            int b;
            while ((b = is.read()) != -1) {
                System.out.println(b + " " + (char) b);
                os.write(b);
            }
        } catch (IOException ex) {
            System.out.println("copy ERR: " + ex);
        }
    }
}
```
```
49 1
50 2
51 3
```
Pay attention, that if it contains any data, it would be overwritten. There is no way to write from another position in outputstream, but you can use RandomAccessFile.
But it’s better to use `Buffered` streams, cause `File` reads char by char, that is not effective
```java
import java.io.*;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        File source = new File("src/main/java/source");
        File dest = new File("src/main/java/dest");
        copy(source, dest);
    }
    private static void copy(File source, File dest) {
        try (
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
        ) {
            byte[] buffer = new byte[5];
            int len;
            while ((len = in.read(buffer)) > 0) {
                System.out.println(Arrays.toString(buffer) + " " + len);
                out.write(buffer, 0, len);
            }
        } catch (IOException ex) {
            System.out.println("copy ERR: " + ex);
        }
    }
}
```
```
[104, 101, 108, 108, 111] 5
[32, 119, 111, 114, 108] 5
[100, 33, 111, 114, 108] 2
```
Pay attention that on the 3rd iteration, only 2 elements of buffer has been written (cause it was end of file), yet buffer contains 5 elements, 3 of which from previous write. That means, that buffer filled char by char, without prior clearing.
We can also use `BufferedReader/BufferedWriter` classes to read and write by line
```java
public class App {
    public static void main(String[] args) {
        File projectDir = new File("src/main/java/com/java/test");
        File source = new File(projectDir, "source");
        File dest = new File(projectDir, "dest");
        copy(source, dest);
    }
    private static void copy(File source, File dest) {
        try (
            BufferedReader in = new BufferedReader(new FileReader(source));
            BufferedWriter out = new BufferedWriter(new FileWriter(dest));
        ) {
            String s;
            while((s = in.readLine()) != null){
                System.out.println(s);
                out.write(s);
            }
        } catch (IOException ex) {
            System.out.println("copy ERR: " + ex);
        }
    }
}
```
```
hello world!
```

If you create `BufferedWriter` with `FileWriter`, there is no way to pass encoding. You have to use either `OutputStreamWriter` or `Files.newBufferedWriter`
```java
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException{
        String path = "src/main/java/com/java/test/text";
        Charset charset = StandardCharsets.UTF_8;
        // no way to pass encoding with filewriter
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(path));
        
        BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), charset));
        BufferedWriter bw3 = Files.newBufferedWriter(Paths.get(path), charset);
    }
}
```
This approach is best when working with strings, cause it abstracts away from working with bytes

###### DirectByteBuffer vs HeapByteBuffer
Array is an object, and stores all data in the heap, even array of int, would be stored in the heap.
Buffer - contiguous block of memory of some type, yet compare to array it has the following methods: `capacity,limit,position,mark`.
ByteBuffer provides view into some (undefined) underlying storage of bytes. There are 2 abstract classes: `ByteBuffer extends Buffer` and `MappedByteBuffer extends ByteBuffer` (same as `mmap`), and 2 concrete implementation:
* `DirectByteBuffer extends MappedByteBuffer` (same as `malloc`, created `ByteBuffer.allocateDirect`) - off-heap memory, backed by array of bytes and uses `Unsafe` under-the-hood for memory allocation, so it is not subject to the GC. constructor of `java.nio.DirectByteBuffer` register `Runnable` of type `java.nio.DirectByteBuffer.Deallocator` which clean off-heap memory when GC clean DirectByteBuffer object itself. Under-the-hood it uses `Unsafe` with directly memory manipulation of off-heap memory, so it kind of zero-GC, except the Buffer object itself, which is stored in heap.
* `HeapByteBuffer extends ByteBuffer` (created `ByteBuffer.allocate`) - in-heap memory, backed by array of bytes `byte[]` which in the end resides in heap memory, since the name contains heap word.
Notice that both classes declared as package-private so you can't call them outside `java.nio` package. So you always work with `ByteBuffer` class. Conclusion, if you are fine with on-heap memory, you can just use simple array of bytes, no need to use any buffer, but if you want off-heap memory, use `DirectByteBuffer`, this leads me to conclusion, that it should be faster and more useful then `HeapByteBuffer`. Yet both implement same base class, so they can be used interchangeably.
Nice example how you can manipulate bytes directly with ByteBuffer. Since `long` is 8 bytes, we should set index correctly, to avoid overwriting.
```java
import java.nio.ByteBuffer;

public class App {
    public static void main(String[] args) {
        // create off-heap byte buffer
        ByteBuffer direct = ByteBuffer.allocateDirect(100);
        // long value takes 8 bytes
        for (int i = 0; i < 10; i++){
            direct.putLong(i * 8, (long)i);
        }
        for(int i = 0; i < 100 - 8; i = i + 8){
            System.out.println(i + " => " + direct.getLong(i));
        }
    }
}
```
Don't confuse:
* multithreading lock - lock based on thread
* file lock - lock based on process (so multiple thread can access same file, no locking here)
    * exclusive lock - write lock, prevent all other operations including read
    * shared lock - read lock, more than one process can read, yet while reading no writes are possible
Don't confuse:
* array of bytes `byte[] arr = new byte[100];` - just simple array of bytes
* byte buffer `ByteBuffer buf = ByteBuffer.allocate(10);` - have more methods to manipulate with bytes. You can also wrap array of bytes: `ByteBuffer buf = ByteBuffer.wrap(new byte[100]);`
Below is example how to create 2 types of buffer:
```java
import java.nio.ByteBuffer;

public class App{
    public static void main(String[] args) {
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(10);
        ByteBuffer heapBuffer = ByteBuffer.allocate(10);
        System.out.println("directBuffer => " + directBuffer.getClass().getName());
        System.out.println("heapBuffer => " + heapBuffer.getClass().getName());
    }
}
```
```
directBuffer => java.nio.DirectByteBuffer
heapBuffer => java.nio.HeapByteBuffer
```
Memory mapped file - concept where you map HDD file to virtual memory, and then can treat this file as memory region, read/write, but OS take care to do read/write to actual disk file on the background. For end user - you just work with region of virtual memory. We can use `MappedByteBuffer/FileChannel` to work with memory-mapped file.
there is some difference between old java io and new one:
Java I/O:
* java perform IO by requesting OS to drain from buffer (write operation) or fill a buffer (read operation)
* OS using disk controller to perform DMA call to extract data from HDD to system kernel memory. DMA (Direct memory access) - allows for modern computers to access main memory without CPU. CPU first initiate DMA, then do it's own stuff, once data fetched, cpu receives `interrupt` from DMAC (DMA controller) and process data:
    * once DMA done, OS copy data from temporary buffer in OS kernel space, into java process space
    * Kernel tries to cache data, so if data already there, it just copied to java buffer, otherwise it's fetched from disk
    * mapping kernel space & user space to same physical address, you don't need to do extra work by copy buffer from kernel to user memory space
* all disk IO done on page level, so kernel align virtual memory pages into disk pages
Java new I/O:
* Memory-mapped I/O establish direct mapping between user memory space into disk, so you can treat file on the disk, like a big in-memory array
read file into memory
* you can read/write into this buffer, don't forget to use flip, cause get/put move buffer one position further, so if you don't call flip
after you read from file, nothing would be written, and `BufferOverflowException` would be thrown on first attempt to write
Below code read byte array from file, then fill file with `x` characters. so assume originally `hello` was written in file we got:
```java
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class App{
    public static void main(String[] args) throws Exception{
        final int FILE_SIZE = 5;
        byte[] arr = new byte[FILE_SIZE];
        try(RandomAccessFile file = new RandomAccessFile("src/main/resources/file.txt", "rw")){
            MappedByteBuffer out = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, FILE_SIZE);
            for (int i = 0; i < FILE_SIZE; i++){
                arr[i] = out.get();
            }
            out.flip();
            for (int i = 0; i < FILE_SIZE; i++){
                out.put((byte) 'x');
            }
            System.out.println("content => " + new String(arr));
        }
        try(InputStream is = new FileInputStream("src/main/resources/file.txt")){
            int b = 0;
            while((b=is.read())!=-1){
                System.out.print((char)b);
            }
        }
    }
}
```
```
content => hello
xxxxx
```
Although you can create large map, you are limited by int number. Let's try to open 10GB file. `map` function take long, but inside implementation use int, and check if value not overflow max int value. This is due to historical reasons, cause previously files were small, and 2GB was max file size.
```java
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class App{
    public static void main(String[] args) throws Exception{
        final long FILE_SIZE = 10 * 1024 * 1024 * 1024L;
        try(RandomAccessFile file = new RandomAccessFile("src/main/resources/file.txt", "rw")){
            MappedByteBuffer out = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, FILE_SIZE);
            for (long i = 0; i < FILE_SIZE; i++){
              out.put((byte) 'x');
            }
        }
    }
}
```
```
Exception in thread "main" java.lang.IllegalArgumentException: Size exceeds Integer.MAX_VALUE
	at java.base/sun.nio.ch.FileChannelImpl.map(FileChannelImpl.java:941)
	at com.java.test.App.main(App.java:11)
```

#### Miscellaneous
###### Modules
Module is the same jar file, but with more control. For example in simple jar file you can use any public classes inside packages, but with module you can limit number of packages publicly available.
There are 3 types of modules:
* named module (NM) - one with `module-info.java` file loaded from `--module-path`
* automatic module (AM) - simple jar loaded from `--module-path` (name of the module is the name of the jar itself, hyphens converted into dots and version is removed so mysql-java-connector-1.2.3.jar => mysql.java.connector. You can also set it explicitly by adding to MANIFEST.MF => Automatic-Module-Name: <module name>)
* unnamed module (UM) - simple jar(or modular jar) loaded from `--class-path`
NM can have access to AM, but should require it in it's `module-info.java` file by its name. There is no way NM can access UM, because named module can't set dependency on unnamed module in its `module-info.java`.
AM can access all types from both NM and AM 
UM can access all types from both NM and AM
Bottom-up vs top-down approach: suppose we have 3 jars A.jar => B.jar => C.jar (`=>` - means depend on).
Bottom-up:
* first convert C.jar (A.jar and B.jar can still be run from classpath, since from classpath you can access all packages)
* second convert B.jar (A.jar still can be loaded from classpath and access all packages)
* finally convert A.jar -> now all are named modules run from `--module-path`
Top-down:
* first convert A.jar (add reference to B from its module-info). In this case both A.jar and B.jar is loaded from `--module-path`, B - automatic module, and C.jar loaded from classpath
* second convert B.jar (add reference to C from its module-info). In this case All 3 loaded from --module-path, C - automatic module
* finally convert C.jar
If you converted 1,2,3 to modules, you can still run them from either classpath (in this case they all loaded as simple jars) or from `--module-path` (in this case named module loaded as named module, others as automatic modules).
Inside module-info we can:
* requires one module at a time (requires moduleA, moduleB - illegal)
* exports one package at a time (exports my.com.java.* - illegal)
* provides - only once for one type (provides A with B, C - in case we have multiple implementation) 

Error message: `module X does not 'opens Y' to unnamed module` - means, that your unnamed module, load as simply jar, trying to get access to package inside `X` jar, but this jar already converted into java module. Usually happens when you are using reflection to access internal/restricted packages. Modules explicitly declare in `module-info.java` all open packages. If you try to access something that is not there, you get this error. JPMS (Java Platform Module System) enforces strong encapsulation, preventing unauthorized access to internal APIs of modules. The "opens" directive explicitly allows reflective access to a package within a module. To resolve this issue, you need to explicitly "open" the required package from the named module to the unnamed module using the `--add-opens` JVM argument: `java --add-opens java.base/java.lang=ALL-UNNAMED -jar app.jar`.
Don't confuse:
* `--add-opens` - provides access for deep reflection (including non-public members) at run time only. So if you have the code like `setAccessible(true)` that means you are trying to get access to some private types, that you are not supposed to get access, and you need to use this option. Basically we can say that this option is wider than `--add-exports` because additionally it opens access to private types.
* `--add-exports` - provides access to public API at compile and run time

We can also use custom module inside maven project.
```xml
<dependency>
  <groupId>module</groupId>
  <artifactId>module-service</artifactId>
  <version>1.0</version>
  <scope>system</scope>
  <systemPath>/home/diman/projects/my/mvnjava/compiled/module/module.jar</systemPath>
</dependency>
```
Pay attention that groupId, artifactId and version can be any value.
`ServiceLoader` - used for loading services. There are 2 ways we can use it:
* old jar way - creating file `META-INF/services/NameOfService` and putting name of your implementation there
* new module way - with module definition `provides/uses`
There are also 2 ways to implement loading:
* extend you class from abstract class(or concrete class) or implement interface. In this case implementation should have no-arg constructor. If no no-arg constructor, 
ServiceLoader won't be able to load the implementation, you will get compile error (`foo/module-info.java:5: error: the service implementation does not have a default constructor: SecondFooInterfacePrinter`)
* add `public static NameOfService provider(){}` to your class. In this case these method calls. No need for no-args constructor.
If class both implement type and return this type in `provider()` method - this method has priority.
`Jlink` - you can create custom jre environment, and run your app, where there is no java
You can run your jar with 2 options `-jar` or `-cp`. If you jar is self-contained (all your dependency inside Manifest.mf in `Class-Path`) you can just use `-jar` option with jar name. If not and you have to pass all dependencies as `-cp` values and use main class after.
`javap` - java disassembler, works with bytecode `.class` files.
Usage:
`javap -v compiled/com/java/test/App.class`
```
Classfile /home/diman/projects/my/ocpjp/code/warnings/compiled/com/java/test/App.class
  Last modified 11 Feb 2020; size 624 bytes
  MD5 checksum 8db0bc45b9bb01a9252305822f39524e
  Compiled from "App.java"
public class com.java.test.App
  minor version: 0
  major version: 55
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #12                         // com/java/test/App
  super_class: #4                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 2, attributes: 1
Constant pool:
   #1 = Methodref          #4.#21         // java/lang/Object."<init>":()V
   #2 = Class              #22            // java/util/ArrayList
   #3 = Methodref          #2.#21         // java/util/ArrayList."<init>":()V
   #4 = Class              #23            // java/lang/Object
   #5 = InterfaceMethodref #24.#25        // java/util/List.add:(Ljava/lang/Object;)Z
   #6 = Class              #26            // com/java/test/Printer
   #7 = Methodref          #6.#21         // com/java/test/Printer."<init>":()V
   #8 = Methodref          #6.#27         // com/java/test/Printer.print:()V
   #9 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
  #10 = String             #30            // running app...
  #11 = Methodref          #31.#32        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #12 = Class              #33            // com/java/test/App
  #13 = Utf8               <init>
  #14 = Utf8               ()V
  #15 = Utf8               Code
  #16 = Utf8               LineNumberTable
  #17 = Utf8               main
  #18 = Utf8               ([Ljava/lang/String;)V
  #19 = Utf8               SourceFile
  #20 = Utf8               App.java
  #21 = NameAndType        #13:#14        // "<init>":()V
  #22 = Utf8               java/util/ArrayList
  #23 = Utf8               java/lang/Object
  #24 = Class              #34            // java/util/List
  #25 = NameAndType        #35:#36        // add:(Ljava/lang/Object;)Z
  #26 = Utf8               com/java/test/Printer
  #27 = NameAndType        #37:#14        // print:()V
  #28 = Class              #38            // java/lang/System
  #29 = NameAndType        #39:#40        // out:Ljava/io/PrintStream;
  #30 = Utf8               running app...
  #31 = Class              #41            // java/io/PrintStream
  #32 = NameAndType        #42:#43        // println:(Ljava/lang/String;)V
  #33 = Utf8               com/java/test/App
  #34 = Utf8               java/util/List
  #35 = Utf8               add
  #36 = Utf8               (Ljava/lang/Object;)Z
  #37 = Utf8               print
  #38 = Utf8               java/lang/System
  #39 = Utf8               out
  #40 = Utf8               Ljava/io/PrintStream;
  #41 = Utf8               java/io/PrintStream
  #42 = Utf8               println
  #43 = Utf8               (Ljava/lang/String;)V
{
  public com.java.test.App();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 11: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=4, args_size=1
         0: new           #2                  // class java/util/ArrayList
         3: dup
         4: invokespecial #3                  // Method java/util/ArrayList."<init>":()V
         7: astore_1
         8: new           #4                  // class java/lang/Object
        11: dup
        12: invokespecial #1                  // Method java/lang/Object."<init>":()V
        15: astore_2
        16: aload_1
        17: aload_2
        18: invokeinterface #5,  2            // InterfaceMethod java/util/List.add:(Ljava/lang/Object;)Z
        23: pop
        24: new           #6                  // class com/java/test/Printer
        27: dup
        28: invokespecial #7                  // Method com/java/test/Printer."<init>":()V
        31: astore_3
        32: aload_3
        33: invokevirtual #8                  // Method com/java/test/Printer.print:()V
        36: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        39: ldc           #10                 // String running app...
        41: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        44: return
      LineNumberTable:
        line 15: 0
        line 16: 8
        line 17: 16
        line 19: 24
        line 20: 32
        line 22: 36
        line 23: 44
}
SourceFile: "App.java"
```
`jdeps` - analyze class dependencies
Example output:
```
jarA.jar -> java.base
jarB -> compiled/jarA.jar
jarB -> java.base
moduleC -> java.base
moduleD -> jarB
moduleD -> java.base
moduleD -> moduleC
```
`-jdkinternals` or `--jdk-internals` - Finds class-level dependencies on JDK internal APIs.

###### Random numbers
Random number generation. There are 5 ways to generate number:
* `java.lang.Math.random()` - generate random between 0 and 1. Using internally `new Random().nextDouble()`.
* `java.util.Random` - standard class to get numbers
* `java.util.SplittableRandom` - not thread-safe, but very quick
* `java.util.concurrent.ThreadLocalRandom` - enhanced version of Random designed to generate multithreaded safe randoms.
* `java.security.SecureRandom` - generate cryptographically secure randoms (mostly used in secure app) - use this class for password/token generation. While `Random` takes data from system clock, `SecureRandom` takes from `/dev/(u)random`, so it's more secure and less chance of somebody guess it. 

You can also manually generate random binary with shell  - which uses environmental noise collected from device drivers as seed. There are 2 types:
* `dev/random` - can block on startup when not enough noise collected to build seed. Once initialized, behave the same as `dev/urandom`
* `dev/urandom` - never blocked, always ready to be used
```shell script
# read 1024 random bytes into file
dd if=/dev/urandom of=~/random.bytes count=4 bs=1024
```
All methods in `Random` are on instance (no static calls).
There are 2 constructors one is empty another with seed of `long`. When you use no-arg it generate seed based on nanotime. If you pass same seed into 2 different randoms, you will generate the same sequence of random numbers. This is useful in testing and in deterministic systems.
```java
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Random seedRnd = new Random(12345L);
        Random rnd = new Random();
        System.out.println("nextBoolean => " + rnd.nextBoolean());
        System.out.println("nextInt => " + rnd.nextInt());
        System.out.println("nextInt(10_000) => " + rnd.nextInt(10_000));
        // nextFloat doesn't have overloaded method with bound
        System.out.println("nextFloat => " + rnd.nextFloat());
        // nextDouble doesn't have overloaded method with bound
        System.out.println("nextDouble => " + rnd.nextDouble());
        // nextLong doesn't have overloaded method with bound
        System.out.println("nextLong => " + rnd.nextLong());
        // returns long
        System.out.println("nextGaussian => " + rnd.nextGaussian());
        // ints()/longs()/doubles() => generate unlimited Int/Long/Double-Stream
        System.out.println("ints => " + rnd.ints().limit(2).boxed().collect(Collectors.toList()));
        // ints/longs/doubles - can take up to 3 params, basically limit, randomFrom-randomTo
        System.out.println("ints(10, 1000, 2000) => " + rnd.ints(10, 1000, 2000).boxed().collect(Collectors.toList()));
        System.out.println("longs => " + rnd.longs().limit(2).boxed().collect(Collectors.toList()));
        System.out.println("doubles => " + rnd.doubles().limit(2).boxed().collect(Collectors.toList()));
        // ints(x)/longs(x)/doubles(x) => generate limited (with x number) Int/Long/Double-Stream
        System.out.println("ints(2) => " + rnd.ints(2).boxed().collect(Collectors.toList()));
        System.out.println();
        System.out.println("Math.random() => " + Math.random());
        System.out.println();
        // ThreadLocalRandom extends Random, you should call it ThreadLocalRandom.current()
        System.out.println("ThreadLocalRandom.current().nextInt(10_000) => " + ThreadLocalRandom.current().nextInt(10_000));
        // generate number within specific range
        int min = 100;
        int max = 1000;
        System.out.println("ThreadLocalRandom.current().nextInt(100, 1000) => " + ThreadLocalRandom.current().nextInt(min, max));
        System.out.println("range with Math.random => " + (min + (Math.random()*(max-min))));
        System.out.println("range with rnd.nextInt => " + (min + (rnd.nextInt(max-min))));
    }
}
```
```
nextBoolean => false
nextInt => 654309046
nextInt(10_000) => 7660
nextFloat => 0.18454093
nextDouble => 0.03177527143289571
nextLong => 2135156644553476854
nextGaussian => -0.0013845294597790506
ints => [1466409168, 1889173021]
longs => [-4299584322596045818, 271321753810882511]
doubles => [0.8466390778685853, 0.6014332629144538]
ints(2) => [82114480, 2077684454]

Math.random() => 0.5466358631961437

ThreadLocalRandom.current().nextInt(10_000) => 6351
ThreadLocalRandom.current().nextInt(100, 1000) => 517
range with Math.random => 840.2608870210925
range with rnd.nextInt => 214
```

###### Locale and ResourceBundle
`Locale` - immutable class, once created can't change its locale. There are 3 ways to create `Locale`. Language, language & country, language & country & variant. So `Locale` at least requires language.
`getString(str s)` - retrieve value by key from current or all parent bundles. Loading happens this way:
first => parent bundle loaded, and then more concrete loaded and values from concrete bundle overwrite values from base bundle.
Don't confuse builder method setRegion(not setCountry) and locale method getCountry(not getRegion)
```java
import java.util.Locale;

public class App{
    public static void main(String[] args) {
        // there is no setCountry(String s) in builder
        Locale loc = new Locale.Builder().setLanguage("en").setRegion("US").build();
        System.out.println("getLanguage => " + loc.getLanguage());
        // there is no getRegion() method
        System.out.println("getCountry => " + loc.getCountry());
    }
}
```
```
getLanguage => en
getCountry => US
```
If we want to reassign `ResourceBundle` to other locale, we can't change current locale, we need reassign variable itself.
ResourceBundle loading files in following ways. Suppose we have 4 files and our default locale is `en_HK`
```
myapp.properties
myapp_en.properties
myapp_en_US.properties
myapp_fr.properties
```
Then we have the following order of execution:
* if we don't pass anything `myapp_en` would be loaded, cause default locale is en
* if we pass existing locale (like `fr` or `en_US`) corresponding file would be loaded
* if we pass `en_us` locale 3 bundles would be loaded: `myapp => myapp_en => myapp_en_us`, with values overwriting each other. So if you have same key in all 3 files, key from `myapp_en_us` would be used
* If we pass non-existing locale (like `german`) default locale would be loaded
* If we set default locale as `german`, then `myappp` would be loaded.
`Locale.setDefault` has 2 overloaded methods. One takes `Locale` object, another (Locale.Category, Locale).
`Locale.getDefault` has 2 overloaded methods. () - get all, (Locale.Category)
If we try to `getBundle` resource that doesn't exist, we got `java.util.MissingResourceException: Can't find bundle for base name myapp1, locale fr`
If bundle exists, but there is no value for key, we got `java.util.MissingResourceException: Can't find resource for bundle java.util.PropertyResourceBundle, key lang`
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        String bundle = "myapp";
        String lang = "lang";
        ResourceBundle rb = ResourceBundle.getBundle(bundle);
        System.out.println("default locale => " + rb.getString(lang));
        Locale locale = new Locale("en", "US");
        rb = ResourceBundle.getBundle(bundle, locale);
        System.out.println(locale+" locale => " + rb.getString(lang));
        locale = new Locale("fr");
        rb = ResourceBundle.getBundle(bundle, locale);
        System.out.println(locale+" locale => " + rb.getString(lang));
        //setting locale for which we don't have props
        locale = Locale.GERMAN;
        rb = ResourceBundle.getBundle(bundle, locale);
        System.out.println(locale+" locale => " + rb.getString(lang));
        Locale.setDefault(locale);
        rb = ResourceBundle.getBundle(bundle, locale);
        System.out.println(locale+" locale => " + rb.getString(lang));
    }
}
```
```
default locale => english
en_US locale => us
fr locale => french
de locale => english
de locale => default
```
`getKeys` returns all keys from current bundle and all parent bundles. If keys are same current bundle keys values are used, if in parent bundle there are new keys, they also be included.
```java
import java.util.*;

public class App {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("myapp", new Locale("fr"));
        Enumeration<String> en = rb.getKeys();
        System.out.println("bundle => " + rb.getBaseBundleName());
        System.out.println("locale => " + rb.getLocale());
        System.out.println();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            System.out.println(key + " => " + rb.getString(key));
        }
    }
}
```
```
bundle => myapp
locale => fr

lang => french
country => france
test3 => test3
```
We can create custom bundle where we can call methods like `getString/getObject/getStringArray`
```java
import java.util.*;

public class App extends ListResourceBundle{
    @Override
    public Object[][] getContents(){
        return new Object[][]{
            {"lang", "english"},
            {"people", new String[]{"Jack", "Mike", "John"}},
            {"fruits", new ArrayList<>(List.of("apple", "banana", "mango"))},
            {"person", new Person("Jack", 30)}
        };
    }
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("com.java.test.App");
        String lang = rb.getString("lang");
        System.out.println("lang => " + lang);
        String[] arr = rb.getStringArray("people");
        System.out.println("arr => " + Arrays.toString(arr));
        List<String> list = (List<String>)rb.getObject("fruits");
        System.out.println("list => " + list);
        Person person = (Person)rb.getObject("person");
        System.out.println("person => " + person);
    }
}

class Person{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString(){
        return "Person[name="+name+", age="+age+"]";
    }
}
```
```
lang => english
arr => [Jack, Mike, John]
list => [apple, banana, mango]
person => Person[name=Jack, age=30]
```

###### Assertions
By default, assertions are turned off. You should use `-ea` or `-enableassertions` flag on `java` in order for them to work. Since assertion fail throws `AssertionError`, it's considered a bad practice trying to catch and recover. Although you can do it.
If you want to disable assertion use `-da` or `-disableassertions`. You can enable/disable assertions from specific class/package `-ea:mypackage`. You can have multiple line ea or da. For example if you want to enable them in general but disable for package `java -ea -da:mypackage`. If you want to enable/disable assertions for system classes (classes from JDK) use `-enablesystemasserstions`/`-esa` or `-disablesystemassertions`/`-dsa`If you want to enable/disable assertions for all subpackages use 3 dots `...` (called ellipsis). `java -ea:package1... -da:package2... Main`.
Since assertions can be turned off by the will of user, it's not a good practice to verify `public` methods input params with assertions. It's better to use runtime exceptions for this purpose. Yet you can use assertions in `private` methods. The reason is since data into private methods goes by developer, so in case of error, he would find it during development.
If you want assertions to be enabled you can force user of your program to use it
```java
public class App {
    static {
        boolean ea = false;
        assert ea = true; // intentional side effect
        if(!ea){
            throw new RuntimeException("Assertions must be enabled");
        }
    }
    public static void main(String[] args) {
    }
}
```
If assertions are enabled program would run fine, but if disabled user will get following error
```
Exception in thread "main" java.lang.ExceptionInInitializerError
Caused by: java.lang.RuntimeException: Assertions must be enabled
```

###### Object interning
In case you want to guarantee that class is immutable, and you won't create new objects for the same data you can make constructor private (will ensure nobody can extend the class) and add static method and intern similar objects there.
```java
import java.util.*;

public class App {
    public static void main(String... args){
        Address addr1 = Address.getAddress("Russia", "Moscow");
        Address addr2 = Address.getAddress("Russia", "Moscow");
        System.out.println("addr1 == addr2 => " + (addr1 == addr2));
        System.out.println("addr1.equals(addr2) => " + addr1.equals(addr2));
    }
}

class Address{
    private static Map<Integer, Address> map = new HashMap<>();
    private final String country;
    private final String city;
    private Address(String country, String city){
        this.country = country;
        this.city = city;
    }
    @Override
    public String toString(){
        return country+":"+city;
    }
    @Override
    public int hashCode(){
        return getHashCode(country, city);
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Address) {
            return this.hashCode() == obj.hashCode();
        }
        return false;
    }

    public static Address getAddress(String country, String city){
        int hashCode = getHashCode(country, city);
        if (map.containsKey(hashCode)) {
            return map.get(hashCode);
        }
        Address address = new Address(country, city);
        map.put(hashCode, address);
        return address;
    }

    private static int getHashCode(String country, String city){
        return 31 + country.hashCode() + city.hashCode();
    }
}
```
```
addr1 == addr2 => true
addr1.equals(addr2) => true
```

###### Annotations
`@Override` can only be used for instance methods. Since we don't override static methods and both instance & static variables (we hide them) you can't use this annotation.
```java
class A{
    public void print(){}
    public static void staticPrint(){}
}
class B extends A{
    @Override
    public void print(){}
    @Override // compile error
    public static void staticPrint(){}
}
```
`@SuppressWarnings` - can be used to suppress compiler warnings. `@SafeVarags` - stronger, suppress warnings in both declaration and invocation.
```java
import java.util.*;

public class App {
    @SuppressWarnings("removal")
    public static void main(String[] args) {
        List people = new ArrayList();
        Object human = new Object();
        people.add(human);

        Printer printer = new Printer();
        printer.m1();
        printer.m2();

        List<String> ls = new ArrayList<>();
        m1(ls);
        m2(ls);
    }
    private static void m1(List ls){
        ls.add(new Object());
    }
    @SafeVarargs
    private static void m1(List<String>... ls){}
    @SuppressWarnings("unchecked")
    private static void m2(List<String>... ls){}
}

class Printer{
    @Deprecated
    public void m1(){}
    @Deprecated(forRemoval=true)
    public void m2(){}
}
```
If you compile you will get following errors
```
Warning:(13, 16) java: print() in com.java.test.at.Printer has been deprecated
Information:java: /home/diman/projects/my/ocpjp/src/main/java/com/java/test/App.java uses unchecked or unsafe operations.
Information:java: Recompile with -Xlint:unchecked for details.
```
There are many types supported by different compilers, but according to [jls-9.6.4.5](https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html#jls-9.6.4.5), There are 3 types all compilers should support, `@SuppressWarnings({"unchecked", "deprecation", "removal"})`.
For unchecked compilation fires mostly when we insert, cause there is the possibility to corrupt collection.
```java
void doElements(List l) {
    l.add("string"); //compilation warning
    System.out.println(l.get(0));
    String s = (String)l.get(0);
}
```
```java
public class App {
    public static void main(String[] args) {
        print(List.of("a"));
    }
    public static void print(List<String>... args){
        Object[] arr = args;
        List<Integer> list = new ArrayList<>(List.of(10));
        arr[0] = list;
        String s = args[0].get(0);
    }
}
```
```
Information:java: /home/diman/projects/my/ocpjp/src/main/java/com/java/test/App.java uses unchecked or unsafe operations.
Information:java: Recompile with -Xlint:unchecked for details.
```
```
Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
```
If we add `@SafeVarags` to `print` methods, compilation warnings would be gone. Although it won't make code safe, you will still get `ClassCastException`.
Meta annotations - those that applied to other annotations. Here is the list of `java.lang.annotation` package. Annotation value must be compile time constant non-null value.

`@Retention(SOURCE/CLASS/RUNTIME)` - how long annotation would be available. Source - only for source code. Class - during runtime, but not for reflection. Runtime - during runtime and can get it by reflection.
`@Target` - to which element does annotation applied (like Field, Method, Constructor) 
`@Repetable`
Before java8, we couldn't add same annotation twice
```java
@Role("user")
@Role("admin") // compile error: com.java.test.Role is not a repeatable annotation type
class User{}

@interface Role{
    String value();
}
```
So if you want to add 2 roles, you have to create new wrapper annotation
```java
@Roles(value = {
    @Role("user"),
    @Role("admin")
})
class User{}

@interface Role{
    String value();
}
@interface Roles{
    Role[] value();
}
```
Now we can write it like this
```java
@Role("user")
@Role("admin")
class User{}

@Repeatable(Roles.class)
@interface Role{
    String value();
}
@interface Roles{
    Role[] value(); // name should be exactly value
}
```
But still you have to create wrapper annotation. We can simplify it by moving Roles inside Role and rename it to List
```java
@Role("user")
@Role("admin")
class User{}

@Repeatable(Role.List.class)
@interface Role{
    String value();
    @interface List{
        Role[] value();
    }
}
```
Pay attention that name of the array `Role[]` should be `value`. Otherwise, you will get compile error. Also, if you have other values inside repeatable annotation they should have default values. The reason for these rules is simple. Java allows you to omit wrapper annotation, but inside it creates this wrapper, and so if your array's name is not value, and if you have other fields without default values, java won't be able to create wrapper annotation.
```java
@Role("user") // won't compile
@Role("admin") // won't compile
class User{}

@Repeatable(Roles.class) // won't compile
@interface Role{
    String value();
}
@interface Roles{
    Role[] roles(); // name should be value
    String name();  // should have default value
}
```
When you use `Repeatable` annotation, and trying to get it by reflection, you will get array annotation, inside which would be your annotations with values
```java
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class App{
    public static void main(String[] args) {
        for(var an: User.class.getAnnotations()){
            System.out.println(an);
        }
    }
}

@Role("user")
@Role("admin")
@Group("user")
@Group("admin")
@Service("userService")
class User{}


@Repeatable(Role.List.class)
@interface Role{
    String value();
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        Role[] value();
    }
}

@Repeatable(Groups.class)
@interface Group{
    String value();
}
@Retention(RetentionPolicy.RUNTIME)
@interface Groups{
    Group[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Service{
    String value();
}
```
```
@com.java.test.Role$List(value={@com.java.test.Role(value="user"), @com.java.test.Role(value="admin")})
@com.java.test.Groups(value={@com.java.test.Group(value="user"), @com.java.test.Group(value="admin")})
@com.java.test.Service(value="userService")
```
`@Documented` - guarantee that annotation would be shown in javadoc
```java
@Documented
@interface Role{
    String value();
}

@Role("admin")
class User{}
```
Now javadoc for class User shows that it uses @Role annotation.
```
com.java.test @Role("admin") 
class User
extends Object
```
`@Inherited` - subclasses of annotated class have the same annotation. Pay attention that it doesn't work for interfaces (IAdmin interface extends IUser, it doesn't inherit it annotations).
```java
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class App {
    public static void main(String[] args) {
        System.out.println("User => " + User.class.getAnnotation(Role.class));
        System.out.println("Admin => " + Admin.class.getAnnotation(Role.class));
        System.out.println("IUser => " + IUser.class.getAnnotation(Role.class));
        System.out.println("IAdmin => " + IAdmin.class.getAnnotation(Role.class));
    }
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Role{
    String value();
}

@Role("user")
class User{}

class Admin extends User{}

@Role("user")
interface IUser{}

interface IAdmin extends IUser{}
```
```
User => @com.java.test.Role(value="user")
Admin => @com.java.test.Role(value="user")
IUser => @com.java.test.Role(value="user")
IAdmin => null
```
You can create your own meta annotations. For this set target as `ElementType.ANNOTATION_TYPE`
```java
@Target(ElementType.ANNOTATION_TYPE)
@interface Meta{

}

@Meta
@interface Service{}

@Meta //compile error, can only be applied to other annotations
@Service
class A{}
```
If annotation has one field with `value` name - you can put this value into annotation without explicitly naming it. If name of field not `value` or there are other fields you should explicitly name them.
```java
@interface Service{
    String value();
}
@interface NamedService{
    String name();
}
@interface MultiValueService{
    String value();
    String name();
}

@Service("cool")
@NamedService("cool") // won't compile should be @NamedService(name="cool")
@MultiValueService(value = "cool", name = "abc")
class A{}
```
String arrays as annotation values. If annotation value is String[] we can pass one value just as string, but can't pass array of nulls:
```java
public class App{
    public static void main(String[] args) {
        String[] arr1 = { null };
        String[] arr2 = "param1"; // compile error, string is not array
    }
}

@interface Service{
    String name();
    String[] params();
}

@Service(name = "A", params = "cool")
class MyService1{}

@Service(name = "A", params = { null }) // compile error, can't pass null
class MyService2{}
```
Types of annotation values: according to  [JLS 9.6.1](https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.6.1). 
The annotation member types must be one of: `primitive/String/Enum/Annotation/Class/Array`.
```java
import java.util.List;

enum Days{
    SAT,
    SUN;
}
@interface Service{
    int i();
    String s();
    Days days();
    Deprecated deprecated();
    Class cls();
    int[] arr();
    Object name(); // won't compile
    List params(); // won't compile
}
```
Default values should be named without equal sign
```java
@interface Service{
    String value() default "hello";
    String name()  default = "Jack"; // won't compile, equal sign after default keyword
}
```
`ElementType.METHOD` - can be applied both: above method and inside method before return value
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@interface TransformReturn{}

class MyService{
    // applied to method outside method
    @TransformReturn
    public String m1(){
        return "m1";
    }

    // applied to method inside method
    public @TransformReturn String m2(){
        return "m1";
    }
}
```

###### Reflection API
Reflection - is an ability to modify on the fly the code from the same source code. It’s useful for testing system or when you write your own framework or when you are writing annotations. With the help of reflection you can call any method or set any field directly, even if they are private.
Reflection is done with the help of `Class` class. There are 3 ways we can get class object
```java
public class App {
    public static void main(String[] args){
        Object obj = new Object();
        // get class from object (instance of the class)
        Class<?> c1 = obj.getClass();
        // get class from class itself
        Class<?> c2 = Object.class;
        try{
            // get class by explicitly loading it (this method calls java classloader to load this class)
            Class<?> c3 = Class.forName("java.lang.Object");
        } catch (ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }
}
```
Example
```java
import java.lang.reflect.*;

public class App {
    public static void main(String[] args) {
        Person p = new Person();
        System.out.println("name: " + p.getName() + ", age: " + p.getAge());
        Class<?> clazz = p.getClass();
        System.out.println();
        System.out.println("package: " + clazz.getPackage().getName());
        System.out.println("className: " + clazz.getName());
        System.out.println("superClass: " + clazz.getSuperclass().getName());
        System.out.println("number of annotations: " + clazz.getAnnotations().length);
        // returns only current interfaces of class, if you want to get all interfaces from all paretn you have to use recursion
        System.out.println("number of interfaces: " + clazz.getInterfaces().length);
        System.out.println("number of constructors: " + clazz.getConstructors().length);
        // get all methods from all parent
        System.out.println("total number of methods: " + clazz.getMethods().length);
        // get methods only declared in this class
        System.out.println("declared number of methods: " + clazz.getDeclaredMethods().length);
        System.out.println();
        try {
            Method setAgeMethod = clazz.getDeclaredMethod("setAge", int.class);
            setAgeMethod.invoke(p, 19);
            System.out.println("age: " + p.getAge());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            System.out.println("getDeclaredMethod ERR: " + ex);
        }
        try {
            Field ageField = clazz.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(p, 44);
            System.out.println("age: " + p.getAge());
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            System.out.println("getDeclaredField ERR: " + ex);
        }
    }
}

@Service("personService")
class Person {
    private String name;
    private int age;
    public Person() {
        this("Jack", 30);
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

@interface Service{
    String value();
}
```
```
name: Jack, age: 30

package: com.java.test
className: com.java.test.Person
superClass: java.lang.Object
number of annotations: 0
number of interfaces: 0
number of constructors: 2
total number of methods: 12
declared number of methods: 3

age: 19
age: 44
```
Pay attention, that although `age` field is private, we still can access it and set value directly with the `setAccessible(true)`.
Practical usage of reflection+annotations. Suppose we want dynamically load services + execute `init` method and handle exception in case this method has suppressException true.
```java
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class App {
    private Map<String, Object> map = new HashMap<>();
    public static void main(String[] args){
        App app = new App();
        app.loadService(MyService.class);
        app.loadService(LazyService.class);
        System.out.println(app.map);
    }
    public void loadService(Class<?> clazz){
        Service serviceAnnotation = clazz.getAnnotation(Service.class);
        if(serviceAnnotation != null){
            Object instance;
            try{
                instance = clazz.newInstance();
                map.put(serviceAnnotation.name(), instance);
            } catch(IllegalAccessException|InstantiationException ex){
                throw new RuntimeException(ex);
            }
            for(Method method: clazz.getMethods()){
                Init initAnnotation = method.getAnnotation(Init.class);
                if(initAnnotation != null){
                    try{
                        method.invoke(instance);
                    } catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException ex){
                        if(initAnnotation.suppressException()){
                            System.out.println("Suppressed: " + ex);
                        } else {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        }
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Init{
    boolean suppressException() default false;
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Service{
    String name();
    boolean lazyLoad() default false;
}

@Service(name="MyServiceName")
class MyService{
    @Init
    public void init(){
        System.out.println("init MyService...");
    }
}

@Service(name="LazyServiceName", lazyLoad=true)
class LazyService{
    @Init(suppressException=true)
    public void init(){
        System.out.println("init LazyService...");
        throw new RuntimeException("oops");
    }
}
```
```
init MyService...
init LazyService...
Suppressed: java.lang.reflect.InvocationTargetException
{MyServiceName=com.java.test.MyService@174d20a, LazyServiceName=com.java.test.LazyService@66d2e7d9}
```

###### Get param names
We can also get name of parameters, but for this we should compile with `-parameters` option
```java
import java.lang.reflect.*;

public class App{
    public App(String myName, int myAge){}
    public static void main(String[] args) {
        for(Constructor<?> con: App.class.getConstructors()){
            for(Parameter param: con.getParameters()){
                System.out.println("isNamePresent => " + param.isNamePresent() + ", name => " + param.getName());
            }
        }
    }
}
```
If we just run it from `intelliJ` as it is, we get:
```
isNamePresent => false, name => arg0
isNamePresent => false, name => arg1
```
But if we got to `Settings`=>`Java Compiler`=>`Additional command line parameters` and add there `-parameters`, we get:
```
isNamePresent => true, name => myName
isNamePresent => true, name => myAge
```

###### Compile Time Annotation Processor
Compile time annotation processor - we can create custom annotation and set some rules (for example constructor should have 3 arguments), and use it in compile time. 
Here is basic example, where custom annotation has a name, and that name should correspond to class name.
We have 3 files
```java
package com.java.myapp;
public @interface CustomService {
    String name();
}
```

```java
package com.java.myapp;
@CustomService(name = "My")
public class App {
    public static void main(String[] args) {
        System.out.println("Java APP: hello world");
    }
}
```

```java
package com.java.myapp;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@SupportedAnnotationTypes("com.java.myapp.CustomService")
public class CustomAnnotationProcessor extends AbstractProcessor {
    private static String FIELD_NAME = "name";
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        List<Name> names = new ArrayList<>();
        for (var element : annotations){
            names.add(element.getQualifiedName());
        }
        for(var element: env.getRootElements()) {
            String className = element.getSimpleName().toString();
            for(var annotation: element.getAnnotationMirrors()){
                Name annotationName = ((TypeElement)annotation.getAnnotationType().asElement()).getQualifiedName();
                if(names.contains(annotationName)){
                    for(var method: annotation.getElementValues().entrySet()){
                        if(method.getKey().getSimpleName().toString().equals(FIELD_NAME)){
                            String annotationClassName = method.getValue().getValue().toString();
                            if(!annotationClassName.equals(className)){
                                String error = "Annotation name `" + annotationClassName + "` doesn't correspond to actual class name: `" + className + "`";
                                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, error, element);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
```
Now we can start building. First we need to build `CustomAnnotationProcessor.java`, and then build `App.java` with the help of this processor
```shell script
# compile Annotation and AnnotationProcessor into `compiled` directory
javac -d compiled/ src/com/java/myapp/CustomService.java  src/com/java/myapp/CustomAnnotationProcessor.java
# compile java with the help of AnnotationProcessor
javac -cp compiled -processor com.java.myapp.CustomAnnotationProcessor -d compiled/ src/com/java/myapp/App.java
# run java app
java -cp compiled com.java.myapp.App
```
On the second build with custom processor we got compile error.
When we compile with javac, we should always use `-d` option, cause it creates correct directory structure. If we have file
```java
package my;
public class App {
    public static void main(String[] var0) {
        System.out.println("hello world!");
    }
}
```
if you just run
```
javac App.java 
java App
Error: Could not find or load main class App
Caused by: java.lang.NoClassDefFoundError: my/App (wrong name: App)
```
This is because we compile without package structure. So we should clearly define packages, or using -d option.
```
javac -d . App.java 
java my.App 
hello world!
```

###### JDK Proxy, Cglib, Javassist
There are 3 ways you can create java classes on the fly
* Jdk Proxy - work only with interfaces
* [cglib](https://github.com/cglib/cglib) - work with both classes and interfaces, [adviced not to use as of 2019](https://github.com/cglib/cglib/issues/129)
* [Javassist](https://www.javassist.org) - work with both classes and interfaces
To use cglib and javassist add this to your `pom.xml`
```
<dependency>
  <groupId>cglib</groupId>
  <artifactId>cglib</artifactId>
  <version>3.3.0</version>
</dependency>
<dependency>
  <groupId>org.javassist</groupId>
  <artifactId>javassist</artifactId>
  <version>3.23.1-GA</version>
</dependency>
```
Since cglib considered outdated when you just run it you will got warnings for cglib implementation
```
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by net.sf.cglib.core.ReflectUtils$1 (file:/home/diman/.m2/repository/cglib/cglib/3.3.0/cglib-3.3.0.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of net.sf.cglib.core.ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```                                                                  
You can fix it by adding `--add-opens java.base/java.lang=ALL-UNNAMED` to compiler VM options                                                                                              
```java
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class App {
    public static void main(String[] args) throws Exception {
        Class<?>[] interfaces = {Person.class};
        Person jdkProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), interfaces, (proxy, method, methodArgs) -> getRetVal(method));
        print(jdkProxy);

        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(interfaces);
        // we can set class also
        //enhancer.setSuperclass(PersonIml.class);
        enhancer.setCallback((InvocationHandler) (proxy, method, methodArgs) -> getRetVal(method));
        Person cglibProxy = (Person) enhancer.create();
        print(cglibProxy);

        ProxyFactory factory = new ProxyFactory();
        factory.setInterfaces(interfaces);
        // we can set class also
        //factory.setSuperclass(PersonIml.class);
        Class<?> proxyCls = factory.createClass();
        Object instance = proxyCls.getDeclaredConstructors()[0].newInstance();
        ((ProxyObject)instance).setHandler((proxy, method, proxyMethod, methodArgs) -> getRetVal(method));
        Person javassistProxy = (Person) instance;
        print(javassistProxy);
    }
    private static Object getRetVal(Method method){
        Map<String, Object> map = new HashMap<>();
        map.put("getName", "Jack");
        map.put("getAge", 25);
        String methodName = method.getName();
        if (map.containsKey(methodName)) {
            return map.get(methodName);
        } else {
            throw new RuntimeException("No value for method: " + methodName);
        }
    }
    private static void print(Person p){
        System.out.println("Person[name=" + p.getName() + ", age=" + p.getAge()+"]");
    }
}

interface Person {
    String getName();
    int getAge();
}

class PersonIml implements Person {
    private String name;
    private int age;

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
}
```
```
Person[name=Jack, age=25]
Person[name=Jack, age=25]
Person[name=Jack, age=25]
```

###### JMX (java management extension)
Allows us to manage java without reloading app. So you can call method to class from `jconsole`.
interface should be public, so we put it into separate file `PrinterMBean.java`.
If we want to have some attributes and change them dynamically, we have to add getter/setter to interface
```java
public interface PrinterMBean{
    void print();

    int getValue();
    void setValue(int value);
}
```
The main logic:
```java
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class App{
    public static void main(String[] args) throws Exception {
        PrinterMBean my = new Printer();
        ObjectName objectName = new ObjectName("com.java.test:type=Printer");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(my, objectName);
        Thread.sleep(Integer.MAX_VALUE);
    }
}

class Printer implements PrinterMBean {
    private int value;
    @Override
    public void print(){
        System.out.println("value => " + value);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
}
```
Run it and open `jconsole`, got to `Mbean` tab open package and call print method from there.

###### Custom ClassLoader
In java there are 3 types of classLoaders:
* BootStrap ClassLoader - load all system classes, when you try to call `getClassLoader()` on such classes you will get `null`
* Extension classLoaders => load all extensions classes from `$JAVA_HOME/jre/lib/ext`
* Application classLoaders => load all application classes.
If you want to write custom classLoader:
* you have to extend from `ClassLoader` and override `loadClass` function
* pay attention that `static initializer` is fired twice, first time by default `AppClassLoader` and second time by our custom `MyClassLoader`
* static fields and static initializer initialized once per classloader - just keep this in mind if you have custom classloader
* why do you need:
  * if you want to load classes from different classpath, because default classloader loads classes only from local classpath
  * implement hot reload - if you change the class you can reload it into JVM without restarting the JVM
  * bytecode change and weaving - with custom classloader you can implement AOP with adding logic to the class and reload it into JVM
```java
import java.lang.reflect.Method;
import java.util.ArrayList;

public class App{
  public static void main(String[] args) throws Exception {
    System.out.println("MyService classLoader => " + MyService.class.getClassLoader());
    System.out.println("ArrayList classLoader => " + ArrayList.class.getClassLoader());

    System.out.println();

    MyClassLoader loader = new MyClassLoader();
    Class<?> cls = loader.loadClass("MyService");
    Object obj = cls.getDeclaredConstructors()[0].newInstance();
    Method m = cls.getMethod("print");
    m.invoke(obj);
  }
}

class MyService{
  static {
    System.out.println("MyService classLoader => " + MyService.class.getClassLoader());
  }
  public void print(){
    System.out.println("hello world");
  }
}

class MyClassLoader extends ClassLoader{
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException{
    System.out.println("loading class... " + name);
    String packageName = "com.java.test.";
    return super.loadClass(packageName + name);
  }
}
```
```
MyService classLoader => jdk.internal.loader.ClassLoaders$AppClassLoader@76ed5528
ArrayList classLoader => null

loading class... MyService
MyService classLoader => jdk.internal.loader.ClassLoaders$AppClassLoader@76ed5528
hello world
```

###### Desktop
There are 2 types of packages to work with desktop: `javafx` and `swing`.
Here is simple gui app that enable you to convert you text to upper case.
```java
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("UpperCase Converter");
        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2, 1));

        JPanel lowerCasePanel = new JPanel();
        lowerCasePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TextArea lowerCaseText = new TextArea();
        lowerCaseText.setText("hello");
        lowerCasePanel.add(lowerCaseText);
        JButton clearButton = new JButton("clear");
        JButton convertButton = new JButton("convert");
        lowerCasePanel.add(convertButton);
        lowerCasePanel.add(clearButton);

        JPanel upperCasePanel = new JPanel();
        TextArea upperCaseText = new TextArea();
        upperCaseText.setEditable(false);
        upperCasePanel.add(upperCaseText);
        upperCasePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        frame.add(lowerCasePanel);
        frame.add(upperCasePanel);

        clearButton.addActionListener(e -> {
            lowerCaseText.setText("");
        });

        convertButton.addActionListener(e -> {
            upperCaseText.setText(lowerCaseText.getText().toUpperCase());
        });


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Are you sure", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
```
Starting from `jdk11`, `jafafx` was decoupled from it. So if you want to work with it you have to add following to your pom.xml file
```
<dependency>
  <groupId>org.openjfx</groupId>
  <artifactId>javafx-controls</artifactId>
  <version>14-ea+8</version>
</dependency>
```
But due to this fact you will get error `cannot access class com.sun.javafx.scene.layout.RegionHelper (in module javafx.graphics) because module javafx.graphics does not export com.sun.javafx.scene.layout to unnamed module @0x88be706`
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
```

###### Java Servlet WebApp
You can run servlet application in multiple ways:
Run app using mvn jetty plugin:
* copy to `/src/main` file with folders `/webapp/WEB-INF/web.xml`
* run `mvn jetty install && mvn jetty:run`
Run app using tomcat server by copying `.war` file:
* We have very basic app that include [controller](https://github.com/dgaydukov/cert-ocpjp11/blob/master/code/webapp/tomcat/App.java) and [web.xml](https://github.com/dgaydukov/cert-ocpjp11/blob/master/code/webapp/tomcat/web.xml). We also using simple [script](https://github.com/dgaydukov/cert-ocpjp11/blob/master/code/webapp/tomcat/script.sh) to build it.
* You need first install [tomcat](https://tomcat.apache.org/download-80.cgi). Download latest version and put it under `~Documents/tomcat`
* Run tomcat `cd ~ && ./Documents/tomcat/bin/catalina.sh run`
* Then you have to build basic servlet application. Go to `/code/webapp` and just run `./script.sh`. This will create `build/webapp.war` file
* Copy `webapp.war` to `/tomcat/webapps`. Tomcat will create new dir `webapp` based on your war file
* Go to `http://localhost:8080/webapp/` to see results

###### Java Virtual Methods
Virtual method in OOP is inheritable and overridable method. In java it's just simple instance method.
So we can say that any instance method (not static) that doesn't have `private` modifier is virtual method.
```java
class A {
    // virtual method (public & instance)
    public void m1(){}
    // not virtual, cause static method are hidden, but not overridden
    public static void m2(){}
    // although instance method but not virtual, cause private modifier will not allow to override it
    private void m3(){}
}
```
The concept of virtual methods is very important in polymorphism and OOP. Take a look, that although object of type A, but method is called on type B, that means it's virtual.
```java
public class App{
    public static void main(String[] args) {
        A a = new B();
        a.m1();
    }
}

class A{
    void m1(){
        System.out.println("A");
    }
}

class B extends A{
    void m1(){
        System.out.println("B");
    }
}
```

####### Class Diagram
* ![exception hierarchy](https://github.com/dgaydukov/cert-ocpjp11/blob/master/files/images/exception-hierarchy.png)

* ![collection interface](https://github.com/dgaydukov/cert-ocpjp11/blob/master/files/images/collection-interface.jpg)

* ![map interface](https://github.com/dgaydukov/cert-ocpjp11/blob/master/files/images/map-interface.png)

* ![concurrent collection classes](https://github.com/dgaydukov/cert-ocpjp11/blob/master/files/images/concurrent-collection-classes.png)

###### Remote debugging
How debugging works:
* when you compile java code you can use option `javac -g`, which would include debug information
    * by default line number & source file information is included
    * you can include local variables names with `-g:vars`
    * you can exclude all debug info with `-g:none` - which makes compiled files lighter, yet it's not adviced
* since your compiled jar contains debug information by default, until you explicitly compiled it without it
    * you can debug your jar file
    * when exceptions happens, java can print stacktrace with original line numbers
* make sure when debugging your source code correspond to compiled file
    * otherwise if your source code changed and not the same that was used for compilation you can run into funny problems
    like breakpoints not working at all, or fire in strange places
* bidirectional:
    * debugger sends breaking points to JVM, and it pause execution there
        * you can also modify code during debugging, and such code would be sent to JVM, which would use hot code replacement
        and now, new code would be executed. If you restart debugging, all such temporary code changes would be gone
    * JVM can send variable contents back to debugger so you can inspect them
How remote debugging works:
* JDWP (Java Debug Wire Protocol) - protocol designed for remote debugging of java apps
* JVM Tool Interface - provides debugging abilities to JVM (ability to inspect objects, set breakpoints)
* JDI (java debug interface) - pass debugging requests from debugger to JVM
* when connecting debugger to remote JVM either one can act as a server and other attaches to it
* you should start JVM on remote server
    * `java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005`
    * if server=n, JVM will connect to debugger
    * if suspend=y, JVM will wait until debugger is connected
* then from IDE, choose host & port (you specified port on the server)
    * make sure port on remote host is open for connection (use telnet to check)
    * launch app from IDE
    * make sure that your code version correspond to remote built version

#### New Java Versions
Here we would show all new cool features of LTS (long term support) java versions from 11 (original document was for java 11 certification). Since then several LTS version were released so we would take a closer look. You can look [Java version history](https://en.wikipedia.org/wiki/Java_version_history) for more details.

###### Java 15
[JEP-371](https://openjdk.org/jeps/371) - Hidden classes:
* cannot be used directly by the bytecode or other classes
* Non-discoverable – a hidden class is not discoverable by the JVM during bytecode linkage, nor by programs making explicit use of class loaders
* may be unloaded even though its notional defining class loader is still reachable
* Hidden classes are used by frameworks that generate classes at runtime and use them indirectly via reflection
* Hidden classes are created using the MethodHandles.Lookup::defineHiddenClass method, which takes the bytecode of the class to be defined and options for its behavior, such as whether it should be initialized or participate in an access control nest
* Hidden classes provide a more secure and efficient way for frameworks to generate and manage temporary, internal classes without exposing them to the broader application

###### Java 16
1. [JEP-395](https://openjdk.org/jeps/395) - Introduction of `Record`. But this enhancement also important, cause it relaxed some rules for nested classes. Before we have string rule, that non-static nested classes can't have static members (static variables, function, classes). But since java 16, this rule was relaxed, and now it can.
```
Static members of inner classes

It is currently specified to be a compile-time error if an inner class declares a member that is explicitly or implicitly static, unless the member is a constant variable. This means that, for example, an inner class cannot declare a record class member, since nested record classes are implicitly static.

We relax this restriction in order to allow an inner class to declare members that are either explicitly or implicitly static. In particular, this allows an inner class to declare a static member that is a record class
```
Take a look at the following code, and keep in mind that it won't compile under java 11, but will compile and work fine from java 16
```java
public class App {
  public static void main(String[] args) {
    App app1 = new App();
    App app2 = new App();
    App.A a1 = app1.new A();
    App.A a2 = app1.new A();
    App.A a3 = app2.new A();
    System.out.println("a1.x=" + a1.x + ", a2.x=" + a2.x + ", a3.x="+a3.x);
    a1.x = 10;
    a2.x = 20;
    a3.x = 30;
    System.out.println("a1.x=" + a1.x + ", a2.x=" + a2.x + ", a3.x="+a3.x);
  }

  class A{
    int x = 5;
    void print(){}
    class A1{}
    // 3 lines won't compile in java 11, but would compile in java 16
    public static int y = 5;
    static void run(){}
    static class B{}
  }
}
```
As you can see, although it's static fields, each non-static nested class, will have their own version of static members. This may be confusing a bit, because you would expect that `a1 & a2`, would share same value for `x`. But not, they different. This is why prior to java 16, nested non-static classes couldn have static members. Cause you would expect that `a1 & a2` should share variable `x`, because they both have same parent, but it's not, `static` for nested non-static class behave same way as non-static. So to avoid this confusion, it was compile-time error. But to add `record` support, they removed this rule.
```
a1.x=5, a2.x=5, a3.x=5
a1.x=10, a2.x=20, a3.x=30
```

###### Java 17
Java 17 is LTS version that was released in September 2021, and would be supported until September 2029
Here is a list of feature that were added
1. class `Record` - added in java 14. You often need to work with immutable classes and to make object immutable you have to add:
* private final to each field
* getter to each field
* public constructor where you set value for each final field
* override equals and hashCode and toString
This adds a lot of boilerplate code.
```java
public class App{
    public static void main(String[] args) {
        Person person = new Person("John", 30);
        System.out.println("person => "+person);
    }
}

record Person (String name, int age) {}
```
As you can see now all the boilerplate code is generated inside automatically, we just have nice clean class creation with one line
```
person => Person[name=John, age=30]
```
But you can extend this class by adding custom constructor, static variables, static and virtual methods:
```java
public class App {
  public static void main(String[] args) {
    Person p1 = new Person("John", "Doe", 0, 0);
    p1.print();
    System.out.println("p1 => "+p1);
  }
}

record Person(String firstName, String lastName, int age, int weight){
  public Person{
    if ("John".equals(firstName)){
      age = 30;
      weight = 80;
    }
  }
  public void print(){
    System.out.println("Hello " + firstName);
  }
}
```
```
Hello John
p1 => Person[firstName=John, lastName=Doe, age=30, weight=80]
```

2. Pattern matching with `instanceof` - now you can apply pattern matching on the fly. Check the old way we have to call `instanceof` and then cast. Now you can do it with single line. See how code became nicer and cleaner.
```java
public class App{
  public static void main(String[] args) {
    Dog dog = new Dog();

    // old way
    if (dog instanceof Animal){
      Animal animal = (Animal) dog;
      if (animal.weight() > 100){
        // do something
      }
    }

    // new way
    if (dog instanceof Animal animal && animal.weight() > 100){
      // do something
    }
  }
}

class Animal{
  public int weight(){
    return 0;
  }
}
class Dog extends Animal{}
```

3. Pattern matching for `switch` statement - now this statement is simplified and you can do pattern matching inside the code. Look at the below code how we can simplify java code and remove all the boilerplate
```java
public class App{
    public static void main(String[] args) {
        Object obj;
        obj = Integer.valueOf("50");
        System.out.println(obj + " => " + formatString(obj));
        obj = Integer.valueOf("150");
        System.out.println(obj + " => " + formatString(obj));
        obj = Long.valueOf("50");
        System.out.println(obj + " => " + formatString(obj));
        obj = Double.valueOf("50");
        System.out.println(obj + " => " + formatString(obj));
        obj = null;
        System.out.println(obj + " => " + formatString(obj));
    }
    public static String formatString(Object obj){
        return switch (obj){
            case Integer i when (i > 1 && i < 100) -> "i between 1 and 100";
            case Integer i -> "just integer";
            case Long l -> "just long";
            case Double d -> "just double";
            case null -> "oops";
            default -> "ok";
        };
    }
}
```
```
50 => i between 1 and 100
150 => just integer
50 => just long
50.0 => just double
null => oops
```

4. [JEP-409](https://openjdk.org/jeps/409) - Sealed classes and interfaces (java 17) - special class that can be extended only by classes/interfaces that explicitly stated on sealed class definition. Before there was no restriction. Your class can be extended by any other class. Now you can explicitly put such restriction by name. Sealed classes is an addition to the Java language giving a class author a fine-grained control over which classes can extend it. Before, you could either allow everyone to inherit your class or disallow it completely (using "final"). It also works for interfaces.Sealed classes/interfaces are a way to create a tagged union. Tagged unions are to classes what Java enums are to objects. Java enums let you limit the possible objects a class can instantiate to a specific set of values. This helps you model days of the week like this:
There are several rules when you create sealed class/interface:
* classes in `permits` section should be already defined, otherwise it won't compile
```java
// this code won't compile with error: can't resolve symbol SportCar
sealed interface Car permits SportCar{
    int getSpeed();
}
```
* child classes should be clearly defined as either `sealed` or `no-sealed` - if you try to create subclass without explicitly stating this, you will get compilation error 
```java
sealed interface Car permits SportCar, SuvCar, SuperCar{
    int getSpeed();
}
non-sealed interface SportCar extends Car{}
// compile error: sealed class must have subclasses
sealed interface SuvCar extends Car{}
// compile error: modifier sealed or non-sealed is expected
interface SuperCar extends Car{}
```
* you are not able to extend class until it explicitly in `permits` section
```java
sealed interface Car permits SportCar{
    int getSpeed();
}
non-sealed interface SportCar extends Car{}
// compile error: add SuvCar to permits list of sealed class Car
non-sealed interface SuvCar extends Car{}
```
* you can either extend or implements any class in `permits` section. Below example where we have sealed interface with 2 classes inside `permits` section, yet we define one as interface and another as class. Interface should be either `sealed` or `non-sealed`. But class can also be defined as `final`.
```java
sealed interface Car permits SportCar, SuvCar, SuperCar{
  int getSpeed();
}
non-sealed interface SportCar extends Car{}
non-sealed class SuvCar implements Car{
  @Override
  public int getSpeed() {
    return 0;
  }
}
// final also works for class
final class SuperCar implements Car{
  @Override
  public int getSpeed() {
    return 0;
  }
}
```
* The sealed class and its permitted subclasses must belong to the same module, and, if declared in an unnamed module, to the same package. Otherwise you can get compilation error: Class is not allowed to extend sealed class from another package.
* Sealed classes useful with combination of `switch` pattern matching. If you have several subclasses of `sealed` class, and in `switch` statement omit any of subclasses - you will get compilation error. So you have to explicitly either use all permitted subclasses or use `default` statement inside `switch`
```java
public class App{
    public static void main(String[] args) {
        
    }
    // compile error: switch expression doesn't cover all possible values
    public static String getCode(Car car){
        return switch (car){
            case SportCar s -> "SportCar";
            case SuvCar s -> "SuvCar";
        };
    }
}

sealed interface Car permits SportCar, SuvCar, SuperCar{}
final class SportCar implements Car{}
final class SuvCar implements Car{}
final class SuperCar implements Car{}
```

5. Access code from outside the JVM and manage memory out of the heap (from java 17, [JEP 412](https://openjdk.org/jeps/412)). 
Off-heap memory - stored outside java runtime, compare to on-heap where GC is running, there is no GC in off-heap. You can create off-heap memory, but you have to manually manage object placement and removal from it. Currently there are 3 ways to work with off-heap memory:
* direct byte buffer - max size is 2GB and deallocation is slow. Originally designed for producer/consumer data exchange, that's why it's overall is slow.
* `sun.misc.Unsafe` - very fast but unreliable, can cause exception. Fast - memory access operations are defined as HotSpot JVM intrinsics and optimized by the JIT compiler. Dangerous - it allows access to any memory location, so java can crash JVM by accessing already freed memory. You can use this code to fetch off-heap memory
```java
MemorySegment offHeap  = MemorySegment.allocateNative(
                             MemoryLayout.ofSequence(javaStrings.length,
                                                     CLinker.C_POINTER), ...);
```
A memory segment is an abstraction that models a contiguous region of memory, inside calls `malloc` or `mmap`.
New API is created:
* memory allocation: MemorySegment, MemoryAddress, SegmentAllocator
* memory management: MemoryLayout, MemoryHandles, MemoryAccess
* resource life cycle: ResourceScope
* call external functions: SymbolLookup, CLinker

###### Java 21
Java 21 is LTS version that was released in September 2023, and would be supported until September 2031
1. Pattern matching for Record (JEP 440) - now you can pass into `instanceof` record with list of fields, and use such fields immediately. And same true for `switch` statement (before you can do pattern matching for whole type, now you can also extract record fields immediately inside pattern matching expression):
```java
public class App{
  public static void main(String[] args) {
    Object obj = new Point(1,1);

    if (obj instanceof Point(int x, int y)){
      System.out.println("x="+x+", y="+y);
    }

    switch (obj){
      case Point(int x, int y) -> System.out.println("x="+x+", y="+y);
      default -> System.out.println("default");
    }
  }
}

record Point(int x, int y){}
```

2. Virtual Threads (JEP 444) - normal threads (platform thread) are thin wrappers around OS threads and when they run they capture such OS thread and cause performance issues. But Virtual threads - runs code on specific OS thread, but doesn't capture it for lifetime, so many virtual threads can share OS threads. You can call it from executors `Executors.newVirtualThreadPerTaskExecutor()`.
Compare to standard threads, you can create millions of virtual threads. `Thread.startVirtualThread(Runnable)` - start thread in virtual mode.

3. Sequenced collections - elements inside have defined encounter order:
* interface SequencedCollection<E> extends Collection<E>
* SequencedSet<E> extends SequencedCollection<E>
* interface SequencedMap<K, V> extends Map<K, V>
Before for set you have to use `LinkedHashSet/SortedSet`, now you can use these new collections. Now with this collections you get access to first/last element, by adding/removing either first/last element. And traversal order is guaranteed.

4. Into ZGC (from java 15) - generation support added, now you can use flag and run `java -XX:+UseZGC -XX:+ZGenerational ...`
5. `Vector API` - new API under `jdk.incubator.vector` for quick calculation on vectors if CPU supports it. Some CPU natively supports such compilation, so we can use it for fast compilation. You can achieve same results with scalars. For example if you want to add 2 int arrays and store resulsts in third array, you can do normal loop and create new array. So if CPU supports `SIMD`, then addition of 2 arrays may happen during one CPU cycle.

###### Java 25
Java 25 is LTS version that was released in September 2025, and would be supported until September 2033