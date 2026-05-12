# Content

1. [New features 11-25](#new-features-11-25)
* 1.1 [Java 15](#java-15)
* 1.2 [Java 16](#java-16)
* 1.3 [Java 17](#java-17)
* 1.4 [Java 21](#java-21)
* 1.5 [Java 25](#java-25)


#### New features 11-25
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
1. [JEP-395](https://openjdk.org/jeps/395) - Introduction of `Record`. But this enhancement also important, cause it relaxed some rules for nested classes. Before we have strict rule, that non-static nested classes can't have static members (static variables, function, classes). But since java 16, this rule was relaxed, and now it can.
```
Static members of inner classes
It is currently specified to be a compile-time error if an inner class declares a member that is explicitly or implicitly static, unless the member is a constant variable. This means that, for example, an inner class cannot declare a record class member, since nested record classes are implicitly static.
We relax this restriction in order to allow an inner class to declare members that are either explicitly or implicitly static. In particular, this allows an inner class to declare a static member that is a record class
```
Take a look at the following code, and keep in mind that it won't compile under java 11, but will compile and work fine from java 16
```java
public class Test {
  public static void main(String[] args) {
    var inner1 = new Outer().new Inner();
    var inner2 = new Outer().new Inner();
    inner1.x = 10;
    inner2.x = 20;
    System.out.println(inner1.x + " => " + inner2.x);
  }
}

class Outer {
  class Inner {
    // 3 lines won't compile in java 11, but would compile in java 16
    static int x = 5;
    static void run(){}
    static class B{}
  }
}
```
As you can see, although we have 2 different instances of inner class, they still share static variable. This maybe confusing, so that is why prior to java 16, nested non-static classes couldn't have static members. Cause you would expect that `a1 & a2` should share variable `x`, because they both have same parent, but it's not, `static` for nested non-static class behave same way as non-static. So to avoid this confusion, it was compile-time error. But to add `record` support, they removed this rule.
```
20 => 20
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
    if (dog instanceof Animal a && a.weight() > 100){
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

4. [JEP-409](https://openjdk.org/jeps/409) - Sealed classes and interfaces (java 17) - special class that can be extended only by classes/interfaces that explicitly stated on sealed class definition. Before there was no restriction. Your class can be extended by any other class. Now you can explicitly put such restriction by name. Sealed classes is an addition to the Java language giving a class author a fine-grained control over which classes can extend it. Before, you could either allow everyone to inherit your class or disallow it completely (using `final`). It also works for interfaces.
* Sealed classes/interfaces are a way to create a tagged union - are to classes what Java enums are to objects. Java enums let you limit the possible objects a class can instantiate to a specific set of values. This helps you model days of the week. Just like enums you can use it inside `switch` without `default` keyword.
There are several rules when you create `sealed` type:
* classes in `permits` section should be already defined, otherwise it won't compile
```java
// this code won't compile with error: can't resolve symbol SportCar
sealed interface Car permits SportCar {
    int getSpeed();
}
```
* you should either use `permits` or declare classes in the same file
```java
sealed interface Car {
    int getSpeed();
}

non-sealed interface Suv extends Car{}
```
* child classes should be clearly defined as either `final/sealed/non-sealed` - if you try to create subclass without explicitly stating this, you will get compilation error 
```java
sealed class Car permits SportCar, SuvCar, SuperCar, Truck{}
final class SportCar extends Car{}
non-sealed class SuvCar extends Car{}
// compile error: sealed class must have subclasses
sealed class SuperCar extends Car{}
// compile error: modifier sealed or non-sealed is expected
class Truck extends Car{}
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
* modules:
  * if sealed class is in the named module - permitted subclasses must belong to the same module - otherwise it won't compile
  * if sealed class is in the unnamed module - permitted subclasses must belong to the same package - otherwise compilation error: `Class is not allowed to extend sealed class from another package`
* so if your codebase is a valid module extending classes may be anywhere inside it, but if it's just a java project - you have to put extended classes into the same package as sealed class: `java: class com.java.test.Test in unnamed module cannot extend a sealed class in a different package`
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

2. Virtual Threads (JEP 444) - normal threads (platform thread) are thin wrappers around OS threads and when they run, they capture such OS thread and cause performance issues. But Virtual threads - code runs on specific OS thread, but doesn't capture it for lifetime, so many virtual threads can share OS threads. You can call it from executors `Executors.newVirtualThreadPerTaskExecutor()`.
Compare to standard threads, you can create millions of virtual threads. `Thread.startVirtualThread(Runnable)` - start thread in virtual mode.

3. Sequenced collections - elements inside have defined encounter order:
* interface SequencedCollection<E> extends Collection<E>
  * there is no method `get` only `getFirst/getLast` to fetch first/last elements, but no random access to any element inside
* SequencedSet<E> extends SequencedCollection<E>
* interface SequencedMap<K, V> extends Map<K, V>
Before for set you have to use `LinkedHashSet/SortedSet`, now you can use these new collections. Now with this collections you get access to first/last element, by adding/removing either first/last element. And traversal order is guaranteed.

4. Into ZGC (from java 15) - generation support added, now you can use flag and run `java -XX:+UseZGC -XX:+ZGenerational ...`
5. `Vector API` - new API under `jdk.incubator.vector` for quick calculation on vectors if CPU supports it. Some CPU natively supports such compilation, so we can use it for fast compilation. You can achieve same results with scalars. For example if you want to add 2 int arrays and store results in third array, you can do normal loop and create new array. So if CPU supports `SIMD`, then addition of 2 arrays may happen during one CPU cycle.

###### Java 25
Java 25 is LTS version that was released in September 2025, and would be supported until September 2033:
1. Unnamed Variables & Patterns - You can now use a single underscore _ for variables you don't intend to use (java22)
2. Markdown in Javadoc: Developers can now write Javadoc comments using Markdown syntax (using ///) instead of clunky HTML tags (java23)
3. ZGC Generational Mode by Default (java23)
4. Flexible Constructor Bodies (JEP 513) - can write code before calling `super/this` in a constructor, to add validation logic or any other piece of code
5. Compact Source Files & Instance Main Methods (JEP 512) - now you can write `void main()` inside file and it would work
6. Module Import Declarations (JEP 511) - now you can import modules too `import module java.base;`
    * you don't need to add any imports, they automatically loaded using this feature
7. Primitive Types in Patterns (JEP 507) - now you can use primitive types inside `instanceof/switch` for pattern matching (before only java classes were allowed)
   * Not permanent feature: `use --enable-preview to enable primitive patterns`
8. Compact Object Headers (JEP 519) - reduce headers size on 64-bit system, gain 20% of heap memory usage
9. AOT Method Profiling (JEP 515) - faster warm-up
10. Generational Shenandoah (JEP 521) - this GC now support generations
11. Scoped Values (JEP 506) - modern, lightweight, and safer alternative to `ThreadLocal`
    * Not permanent feature: `use --enable-preview to enable primitive patterns`
12. Key Derivation Function (KDF) API (JEP 510) - new standard API for cryptographic algorithms
13. Stream Gatherers (JEP 485) - Stream API added new function `gather()`, which allows custom intermediate operations
```java
void main(){
    List<String> words = List.of("apple", "banana", "cherry", "date", "elderberry");
    List<List<String>> batches = words.stream()
            .gather(Gatherers.windowFixed(2))
            .toList();
    System.out.println(batches);
}
```
```
[[apple, banana], [cherry, date], [elderberry]]
```