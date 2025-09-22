package com.java.moduled;

import com.java.modulec.ModuleCApp;
import com.java.printer.ModuleCPrinter;
import com.java.jarb.JarBApp;

public class ModuleDApp{
    public static void main(String[] args) {
        System.out.println("ModuleDApp running...");
        JarBApp b = new JarBApp();
        b.print("ModuleDApp");
        ModuleCApp c = new ModuleCApp();
        ModuleCPrinter printer = new ModuleCPrinter();
        printer.print("hello world");
    }
    public void print(String str){
        System.out.println("ModuleDApp print: " + str);
    }
}