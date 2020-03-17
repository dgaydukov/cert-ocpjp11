package com.java.bar;

import com.java.service.InterfacePrinter;

public class BarInterfacePrinter implements InterfacePrinter{
    @Override
    public void print(String str){
        System.out.println("bar interface impl => " + str);
    }
}