package com.java.app;

import com.java.service.InterfacePrinter;

public class AppInterfacePrinter implements InterfacePrinter{
    @Override
    public void print(String str){
        System.out.println("__APP => " + str);
    }
}