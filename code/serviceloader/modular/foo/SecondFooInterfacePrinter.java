package com.java.foo;

import com.java.service.InterfacePrinter;

/**
 * Although we implement InterfacePrinter, since we are usign provider() it's called
 */
public class SecondFooInterfacePrinter implements InterfacePrinter{
    // add one param constructor so java won't create default no-arg constructor
    public SecondFooInterfacePrinter(int x){

    }
    @Override
    public void print(String str){
        System.out.println("second foo interface impl => " + str);
    }

    public static InterfacePrinter provider(){
        return new InterfacePrinter() {
            @Override
            public void print(String str) {
                System.out.println("static provider => " + str);
            }
        };
    }
}