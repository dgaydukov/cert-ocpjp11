package com.java.calculator;

import com.java.printer.Printer;

public class Calculator{
    private Printer printer = new Printer();

    public int add(int a, int b){
        printer.print("Adding 2 arguments: "+a+", "+b);
        return a+b;
    }
}