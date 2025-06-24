package com.java.test;

import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(obj.hashCode()+" => "+System.identityHashCode(obj));
    }
}