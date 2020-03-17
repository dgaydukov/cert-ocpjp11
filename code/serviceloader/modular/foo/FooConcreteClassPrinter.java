package com.java.foo;

import com.java.service.ConcreteClassPrinter;

public class FooConcreteClassPrinter extends ConcreteClassPrinter{
    @Override
    public void print(String str){
        super.print(str);
        System.out.println("foo concrete class impl => " + str);
    }
}