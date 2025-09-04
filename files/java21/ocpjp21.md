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
* java has `pass-by-value` semantics because in both cases for primitive & reference type you pass actual value (either primitive value or value of memory address for reference type). Any variable just contain raw-number, and it's JVM job to interpret this number as either real number int/long/char in case for primitive type, or as memory address on the heap in case of reference type. But variable in java would always contain just raw number. So if you pass either primitive or reference param into function and assign new value to this param. Original value won't be changed: in below example both int and `obj` are the same, although were changed inside function. But because of `pass-by-value`, you can't change the value. You can change it inside function scope, but this doesn't affect outside variables.
```java
public class App {
    public static void main(String[] args) {
        int i = 1;
        Object obj = new Object();
        System.out.println("int=" + i + ", object=" + obj);
        call(i, obj);
        System.out.println("int=" + i + ", object=" + obj);
    }

    public static void call(int i, Object obj) {
        i = 0;
        obj = null;
    }
}
```
During method call, variables are copied into local variables of the method, so i & obj, inside `call` are copies of original values of i & obj from `main` method.
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
* `this` is `final` variable, you can't reassign new value to it.
```java
class Person {
    private String name;
    public Person(String name) {
        this.name = name;
    }
    public void reset(){
        //compile error: cannot assign to 'this'
        this = new Person("Jack");
    }
}
```
* initializer can't throw exception, such code won't compile
```java
class Person {
    {
        // compile error: initializer must be able to complete normally
        throw new RuntimeException("oops");
    }
    public Person() {
        throw new RuntimeException("oops");
    }
}
```
* for `final` variable:
  * constructor - you should init it in all constructor, because only 1 constructor is used. If you call one constructor from another, you can init only once
  * initializer - only once, because all initializers are called during object creation

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
* New syntax for multi-line string
```java
String json = """
{
  "data": {
    "key": "value"
  }
}        
""";
```

###### Switch statement
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
Rules:
* switch variable can't be `null`, otherwise you'll get NPE
* `default` can be anywhere even as first label - it's a good practice to put it the last
* fall through - if there is no `break` statement once you reach your case you will fall through until the end. In below code:
    * if `i==1` then `case 1` would be entered and 1,2,3 would be printed
    * if `i==3` then `case 3` would be entered and only 3 would be printed
    * if `i` is any value except 1,2,3, then `default` block would be entered, and because there is no `break` default,1,2,3 would be printed - that's why you should always put `default` into last statement in `switch`
```java
switch (i) {
    default: System.out.println("default");
    case 1: System.out.println("1");
    case 2: System.out.println("2");
    case 3: System.out.println("3");
}
```
To avoid fall through there is new syntax since java14 (with old syntax you have to add `break`)
```java
switch (i) {
    default -> System.out.println("default");
    case 1 -> System.out.println("1");
    case 2 -> System.out.println("2");
    case 3 -> System.out.println("3");
}
```
Compare syntax different between old and new:
```java
switch (i) {
    case 1,2,3 -> System.out.println(i);
    default -> System.out.println("default");
}
switch (i) {
    case 1:
    case 2:
    case 3:
        System.out.println(i);
        break;
    default: System.out.println("default");
}
```
* `yield` - keyword in java14 to return value from `switch expression` : if we want to use `yield` with new syntax, we have to add curly braces
  * since it's expression it must return value always, that's why without `default` below code won't compile with following error: `the switch expression does not cover all possible input values`
```java
public class App {
    public static void main(String[] args) {
        int i = 3;
        // old syntax with yield
        int v1 = switch (i) {
            case 1:
            case 2:
            case 3:
                yield 5;
            default: yield 10;
        };
        // new syntax
        int v2 = switch (i) {
            case 1,2,3 -> 5;
            default -> 10;
        };
        // new syntax with yield
        int v3 = switch (i) {
            case 1,2,3 -> {
                yield 5;
            }
            default -> {
                yield 10;
            }
        };
        System.out.println("v1 = " + v1+", v2 = " + v2 + ", v3 = " + v3);
    }
}
```
* patten matching with `switch`:
  * you have old and new syntax to use patten matching
  * `break` is required for old syntax - if you miss first `break` code won't compile with error `illegal fall-through to a pattern`, yet you can miss last `break` before `default`
  * exhaustive - such switch should include all possible use-cases or `default` keyword
  * order of `case` - it should be in growing order `Integer => Number => Object` if you change the order you get compilation error: `this case label is dominated by a preceding case label`
  * `null` case is allowed in any place but must come before `default`
```java
public class App {
    public static void main(String[] args) {
        Object obj = 10.0;
        switch (obj) {
            case Integer i:
                System.out.println("i");
                break;
            case Double d:
                System.out.println("double value=" + d);
                break;
            default:
                System.out.println("default value=" + obj);
        }
        switch (obj) {
            case Integer i -> System.out.println("int value=" + i);
            case Double d -> System.out.println("double value=" + d);
            default -> System.out.println("default value=" + obj);
        }
    }
}
```
* pattern matching with guard:
  * `when` - special condition knows as `guard` is put into `case` statement to guard it:
```java
Object obj = 10;
switch (obj) {
    case Integer i when (i > 0 && i < 10) -> System.out.println("0 < i < 10");
    case Integer i when i >= 10 -> System.out.println("i > 10");
    case Object o -> System.out.println("Object=" + o);
}
```
* you can combine `record` with `switch`
```java
public class App {
    public static void main(String[] args) {
        Object obj = new Person(30, "John");
        switch (obj) {
            case Person(int age, String name) -> System.out.println(age + " " + name);
            default -> System.out.println(obj);
        }
    }
}
record Person(int age, String name) {}
```
* switch with pattern matching must be:
  * exhaustive
  * follow the rule on dominance of case labels
  * not exhibit fall through
* if switch statement is expression (return some value) - it must be exhaustive

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

###### Record
* just like type safe enum lead to `enum` creation in java5, same way constant usage of POJO leads to records - because a lot of time you are dealing with object container that you move data to-and-from (like db), you create classes, but they don't have any logic, just store data - records created to solve this problem
* record can't declare instance fields inside, only static
```java
record Person(String name, int age) {
    int v1; // won't compile
    static int v2;
}
```
* it implicitly extends `java.lang.Record` but not allowed to have explicit `extend`:
  * since java doesn't support multiple inheritance, `record` already `extends java.lang.Record`, so it can't extend second class
  * since `record` is immutable data structure - enabling inheritance may add mutable fields into record, also it may add unnecessary complex logic
```java
record Person(int id) extends Object{} // won't compile
```
* record like enum can implement interfaces and inherit their default method
* it's final, you can't extend it, you also can't make it `abstract` or `sealed`
```java
record Person(int id){}
class My extends Person{} // won't compile
```
* component fields - list of params passed on record creation, all `final`
  * canonical constructor - default constructor generated by `record` that set all params. You can also add it yourself (for example you want to have additional validation), and add other constructors as well
  * each constructor should have one line as call to canonical constructor, you can't redefine it, such code won't compile - so when the code reaches second line of such constructor all component fields already set
  * you can remove canonical constructor with 2 arguments, and the below code would compile, because compilator would generate one
  * we can add inline validation into first line of another constructor, but it should be `static`. if you use non-static - compilation error: `cannot reference this before supertype constructor has been called` - yet in canonical constructor you can use instance method for validation
```java
record Person (String name, int age) {
    public Person (String name, int age) {
        if (age > 99) {
            throw new IllegalArgumentException("Age is too big");
        }
        validate(age);
        validateAge(age);
        this.name = name;
        this.age = age;
    }
    private void validate(int age){
        validateAge(age);
    }
    public Person () {
        this("dummy", 99);
    }
    public Person(int age){
        // compile but validateAge should be static
        this("human", validateAge(age));
    }
    private static  int validateAge(int age){
        if (age < 0){
            throw new IllegalArgumentException();
        }
        return age;
    }

    public Person (String name) { // won't compile
        this.name = name;
        this.age = 99;
    }
}
```
If record doesn't implement interface, if you try to check if it's instance of interface, the code won't compile:
* this make sense because `record` is always `final` class
* since for `final` class we know that it will not have any subclasses that can in theory implement this interface - it's knows on compile-time that record doesn't implement ths interface
```java
public class App {
    public static void main(String[] args) {
        BigPrinter bigPrinter = new BigPrinter();
        SmallPrinter smallPrinter = new SmallPrinter();
        System.out.println(bigPrinter instanceof Printer);
        // won't compile
        System.out.println(smallPrinter instanceof Printer);
    }
}

interface Printer {}
class BigPrinter  {}
record SmallPrinter() {}
```

Records don't follow JavaBean convention:
* they don't have setter, because they are immutable
* they name getter different, they don't use `getFieldName` notation, they just use `name`
```java
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class App {
    public static void main(String[] args) {
        Person p1 = new Person("John", 30);
        System.out.println("p1=" + p1);
        System.out.println("name=" + p1.getName() + ", age=" + p1.getAge());

        Person2 p2 = new Person2("John", 30);
        System.out.println("p2=" + p2);
        System.out.println("name=" + p2.name() + ", age=" + p2.age());
    }
}

@RequiredArgsConstructor
@Getter
@ToString
class Person {
    private final String name;
    private final int age;
}

record Person2(String name, int age) {}
```
We have to use 3 lombok annotations to achieve same result. Also pay attention to getter names, `record` doesn't follow JavaBean standard
```
p1=Person(name=John, age=30)
name=John, age=30
p2=Person2[name=John, age=30]
name=John, age=30
```
* record may have static but not instance initializers
```java
record Person (String name, int age) {
    static {
        System.out.println("static initializer");
    }
    {
        System.out.println("instance initializer"); // won't compile
    }
}
```
* getter method should be named as param and:
  * should be only public
  * should not throw exception
```java
record Person(String name, int age) {
    String name() throws RuntimeException{ // wont' compile
        return name;
    }
}
```
* record serialization:
  * serialized form - sequence of values derived from the record component fields
  * during deserialization canonical constructor is called
  * `serialVersionUID` is 0L by default
  * can't be customized with `writeObject/readObject/readObjectNoData/writeExternal/readExternal`

###### instanceof
There were several enhancement to this expression in java 16, 21:
* flow scoping - variable scope is within the flow it's declared. In this example we use inversion, so variable `str` would be available in the `else` clause, but not inside `if` 
```java
Object obj = null;
if (!(obj instanceof String str)) {
    
} else {
    System.out.println(str);
}
```
Here we have to use `return` to make sure that if `obj` is `String` we would exit, and not proceed. Otherwise, the scope for `str` would end, but you call it after. Basically without `return` code won't compile, because in this case if you jump into `if`, then you can call `str` outside, which should not be possible.
```java
Object obj = null;
if (!(obj instanceof String str)) {
    return;
}
System.out.println(str);
```
* To avoid potential problems for reassign local variable, use `final`
```java
Object obj = null;
if(obj instanceof final String str){
}
```
* record deconstruction - because records have predefined set of instance variables on compile time, you can write code like this to get access to record values
```java
public class App {
    public static void main(String[] args) {
        Object obj = null;
        if (obj instanceof Person(int age, FullName(String firstName, String lastName))) {
            System.out.println(age + ", " + firstName + ", " + lastName);
        }
    }
}

record FullName (String firstName, String lastName) {}
record Person (int age, FullName fullName) {}
```
* generic records - first condition won't compile, because we don't know that it's type of `String`, but for second record, it's explicitly stated that `Address` would be type of `String`, so adding string works
```java
public class App {
    public static void main(String[] args) {
        Object address = new Address<>("street");
        Object person = new Person("John", new Address<>("street"));
        if (address instanceof Address<String>(var value)) { // won't compile
            System.out.println(value);
        }
        if (person instanceof Person(var name, Address<String>(var value))) {
            System.out.println(name+", "+value);
        }
    }
}

record Address<T> (T value){}
record Person(String name, Address<String> address){}
```
* serialization rules:
  * record can be serialized but it also needs to implement `Serializable`
  * you can't customize serialization rule. Compare to the class where you can customize by implementing `Serializable` and add private `writeObject/readObject` or implement `Externalizable` - for record those methods just won't be called - this is because record are immutable and state can't change after it was created. So adding custom methods may tamper with immutability.
  * canonical constructor is called - basically java create new record from scratch, just like you would create it with `new ...`. This is different from class, for which serialization rules are different.
```java
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("./data.ser"))) {
            Person person = new Person("John", 35);
            PersonRec rec = new PersonRec("John", 35);
            os.writeObject(person);
            os.writeObject(rec);
        }
        System.out.println("reading...");
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("./data.ser"))) {
            Person person = (Person) is.readObject();
            PersonRec rec = (PersonRec) is.readObject();
            System.out.println("person => "+person);
            System.out.println("rec => "+rec);
        }
    }
}
class Person implements Externalizable {
    private String name;
    private int age;
    public Person(String name, int age) {
        System.out.println("class main constructor: name="+name+", age="+age);
        this.name = name;
        this.age = age;
    }
    public Person() {
        System.out.println("class no-arg constructor");
    }
    @Override
    public String toString() {
        return "Person(name="+name+", age="+age+")";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Person class writeExternal");
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Person class readExternal");

    }
}
record PersonRec(String name, int age) implements Externalizable {
    public PersonRec(String name, int age) {
        System.out.println("record canonical constructor: name=" + name + ", age=" + age);
        this.name = name;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Person record writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Person record readExternal");

    }
}
```
For class, we have implemented custom serialization, but because methods are empty, no fields are saved and deserialized, that's why values are null. For record custom serialization methods are not called, and record just serialized like it never implemented those custom methods.
```
class main constructor: name=John, age=35
record canonical constructor: name=John, age=35
Person class writeExternal
reading...
class no-arg constructor
Person class readExternal
record canonical constructor: name=John, age=35
person => Person(name=null, age=0)
rec => PersonRec[name=John, age=35]
```

###### Virtual Threads
Before java21:
* JMV thread are called - platform threads
* JVM called OS to create new thread, OS would create new thread and assign it to JVM
* each thread in JVM is basically a wrapper in OS thread
* OS decided when each thread would actually run (thread scheduling is done by OS, where multiple threads scheduled to run on the same CPU)
* problem: OS thread creation is cost memory and CPU + creating many threads cause OS to do a lot of context switching which slows down the OS
* if you create several thousands threads - OS will run out of memory and kill the app - so the model thread-per-task is good but not practical - java apps like Tomcat, create pool of threads and reuse them, rather then creating new thread for new request. It's so common that it became part of JDK: `Executors.newFixedThreadPool`
From java21:
* you can create virtual thread - they are virtual because OS doesn't aware of it, they are created & managed by JVM
* creation of virtual thread doesn't cost anything - comparing to creation of platform thread - which requires actual thread creation by OS
* OS thread scheduler - schedules platform threads to run on top of CPU
* JVM thread scheduler - schedules virtual threads to run on top of platform threads created by OS
* carrier thread - platform thread on which virtual thread is running
* you can create millions of virtual thread - but only a handful of platform threads would be created and all your virtual threads would be scheduled to run on top of these platform threads
* if virtual thread waits for IO it doesn't block OS platform thread
* now you can fully utilize thread-per-task programming approach
* GC thread - good example of daemon thread, this thread should run only as app is running. and it's utility thread, so if app not running you don't need it anymore
* virtual thread always a daemon, calling `setDaemon(false)` throws `IllegalArgumentException` - because you are supposed to create millions of virtual threads for short-lived tasks, they should run as daemon threads only
* platform thread by default is not daemon, but you can set it as daemon by calling `setDaemon(true)`
* virtual thread has the same priority of `Thread.NORM_PRIORITY`, call to `setPriority` just ignored
Example of virtual and platform threads:
```java
Thread.Builder platformThread = Thread.ofPlatform();
Thread.Builder virtualThread = Thread.ofVirtual();
platformThread.start(()-> System.out.println("platformThread"));
virtualThread.start(()-> System.out.println("virtualThread"));
```
* thread pinning - virtual thread with `synchonized` pinned to the platform thread on which they run, that means if your thread is taking too long, it may starve the system, so virtual thread are designed to run small/fast jobs. But if you use `ReentrantLock` it won't be pinned - this is one more advantage to use explicit locking.