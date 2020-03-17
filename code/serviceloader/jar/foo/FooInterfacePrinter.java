package com.java.foo;

import com.java.service.InterfacePrinter;

public class FooInterfacePrinter implements InterfacePrinter{
    @Override
    public void print(String str){
        System.out.println("foo interface impl => " + str);
    }
}