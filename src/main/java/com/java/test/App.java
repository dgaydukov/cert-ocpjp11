package com.java.test;

public class App {
    public static void main(String[] args) {
        Person p1 = new Person("John", "Doe", 0, 0);
        System.out.println("p1 => "+[1]);
    }
}

record Person(String firstName, String lastName, int age, int weight){
    public Person{
        if ("John".equals(firstName)){
            age = 30;
            weight = 80;
        }
    }
}