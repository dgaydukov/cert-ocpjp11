package com.java.foo;

import com.java.service.InterfacePrinter;

public class SecondFooInterfacePrinter implements InterfacePrinter{
    @Override
    public void print(String str){
        System.out.println("second foo interface impl => " + str);
    }
}