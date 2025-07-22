# OOP

### Contents
* [Description](#description)
* [Composition over inheritance](#composition-over-inheritance)
* [Lombok Delegate](#lombok-delegate)

### Description
In this file I'll add all the tricks and hacks, including all best practices related to OOP. This is very broad topics, so I'll not add all the details and basic concepts, only the most advanced.

### Composition over inheritance
There are 2 main approach how you can build your app:
* inheritance - the downside is that you create tight coupling. Moreover in real world you have composition. For example car consist of multiple not directly related parts
* composition - better than inheritance, cause it gives you more flexible and remove tight coupling. There are several ways how you can achieve this in java:
  * use of multiple interfaces
  * use of `@Delegate` interface from Lombok

### Lombok Delegate
Lombok has one nice annotation `@Delegate` that can allows you to kind of extend one class with functionality of another, without using any kind of inheritance. Take a look at this program:
```java
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

public class App {
    public static void main(String[] args) {
        Person person = new Person("John", 30);
        person.print("I'm a person with name=" + person.getName() + " and age=" + person.getAge());
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
```
I'm a person with name=John and age=30
```
Here we extend logic of `Person` by adding `Printer` behavior to it.