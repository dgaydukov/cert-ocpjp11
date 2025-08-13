package com.java.test;


public class App {
    public static void main(String[] args) {
        int a = 1, b = 1;
        Object obj = a == b ? 5.0 : "hello";
        System.out.println(obj.getClass().getName());
    }
}