package com.java.app;

import com.java.calculator.Calculator;

public class App{
    public static void main(String[] args){
        Calculator calc = new Calculator();
        int r = calc.add(1, 2);
        System.out.println("result => "+r);
    }
}