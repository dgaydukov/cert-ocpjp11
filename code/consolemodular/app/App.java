package com.java.app;

import com.java.printer.Printer;

public class App{
    public static void main(String[] args) {
        System.out.println("App running...");
        Printer p = new Printer();
        p.print("hello world");
    }
}