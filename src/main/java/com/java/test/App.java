package com.java.test;

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
final class SportCar implements Car{};
final class SuvCar implements Car{};
final class SuperCar implements Car{};