package com.java.foo;

import com.java.service.AbstractClassPrinter;

public class FooAbstractClassPrinter extends AbstractClassPrinter{
    @Override
    public void print(String str){
        System.out.println("foo abstract class impl => " + str);
    }
}