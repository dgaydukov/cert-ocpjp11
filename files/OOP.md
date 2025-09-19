# OOP

### Contents
* [Description](#description)
* [Composition over inheritance](#composition-over-inheritance)
* [Lombok Delegate](#lombok-delegate)
* [Decorator pattern](#decorator-pattern)

### Description
In this file I'll add all the tricks and hacks, including all best practices related to OOP. This is very broad topics, so I'll not add all the details and basic concepts, only the most advanced.

### Composition over inheritance
There are 2 main approach how you can build your app:
* inheritance - the downside is that you create tight coupling. Moreover, in real world you have composition. For example car consist of multiple not directly related parts
* composition - better than inheritance, cause it gives you more flexible and remove tight coupling. There are several ways how you can achieve this in java:
  * use of multiple interfaces
  * use of `@Delegate` interface from Lombok

### Lombok Delegate
Lombok has one nice annotation `@Delegate` that can allows you to kind of extend one class with functionality of another, without using any kind of inheritance. Take a look at this program:
```java
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

public class Test {
    public static void main(String[] args) {
        Person person = new Person("John", 30);
        person.print("I'm a person with name=" + person.getName() + " and age=" + person.getAge());
        // won't compile
        Printer printer = (Printer) person;
    }
}

@Getter
@RequiredArgsConstructor
class Person {
    @Delegate
    private final Printer printer = new Printer();

    private final String name;
    private final int age;
}

class Printer {
    public void print(String msg) {
        System.out.println(msg);
    }
}
```
Here we extend logic of `Person` by adding `Printer` behavior to it.
```
I'm a person with name=John and age=30
```
Keep in mind that delegation doesn't automatically add inheritance, here if we try to assign `person` to `Printer` - such code won't compile, there is no inheritance connection between these 2 classes. What is happening under-the-hood - is just copy of public methods from delegated class to the calling class - below is example how it works if you delombok the code. So it creates all public functions and proxy calls to another class.
```java
@Getter
@RequiredArgsConstructor
class Person {
    private final Printer printer = new Printer();
    
    public void print(String msg) {
        printer.print(msg);
    }

    private final String name;
    private final int age;
}

class Printer {
    public void print(String msg) {
        System.out.println(msg);
    }
}
```

### Decorator pattern
* design pattern that adds additional behavior to the object
* but doing so without modifying original class or even without inheritance (if we have interface)
* so you have completely new object which is type of interface but has additional behavior
* AOP design pattern is based on decorators where you add logic before/after the method call, and supply your proxy object instead of original object - so adding additional features without modifying original class
* lombok `@Delegate` annotation is similar or one of use-cases of decorator - the only difference with lombok you don't have the ability to extend the behavior, what it does - just add same logic into new class without adding additional behavior.
```java
public class Test {
    public static void main(String[] args) {
        Printer console = new ConsolePrinter();
        Printer decorator = new DecoratedPrinter(console);
        decorator.print("hello");
    }
}

interface Printer {
    void print(String msg);
}

class ConsolePrinter implements Printer {
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
}

class DecoratedPrinter implements Printer {
    private final Printer printer;
    public DecoratedPrinter(Printer printer) {
        this.printer = printer;
    }
    @Override
    public void print(String msg) {
        System.out.println("decorated printer before the call...");
        printer.print(msg);
        System.out.println("decorated printer after the call");
    }
}
```
Here we have created a decorator, that kind of extends our `ConsolePrinter` and adding additional behavior, but without modifying original class. Also because both original class and decorator extends the same interface they can substitute each other in the code where interface is used.
```
decorated printer before the call...
hello
decorated printer after the call
```