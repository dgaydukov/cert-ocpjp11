package com.java.app;

import com.java.service.AbstractClassPrinter;
import com.java.service.ConcreteClassPrinter;
import com.java.service.InterfacePrinter;
import java.util.ServiceLoader;

public class App{
    public static void main(String[] args) {
        String str = "hello world";
        System.out.println("...Loading abstract classes impl...");
        ServiceLoader<AbstractClassPrinter> abstractClassLoader = ServiceLoader.load(AbstractClassPrinter.class);
        for(var service: abstractClassLoader){
            service.print(str);
        }
        System.out.println("...Loading concrete classes impl...");
        ServiceLoader<ConcreteClassPrinter> concreteClassLoader = ServiceLoader.load(ConcreteClassPrinter.class);
        for(var service: concreteClassLoader){
            service.print(str);
        }
        System.out.println("...Loading interfacees impl...");
        ServiceLoader<InterfacePrinter> interfaceLoader = ServiceLoader.load(InterfacePrinter.class);
        for(var service: interfaceLoader){
            service.print(str);
        }
    }
}