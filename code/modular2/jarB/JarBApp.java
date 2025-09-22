package com.java.jarb;

import com.java.jara.JarAApp;

public class JarBApp{
    public static void main(String[] args) {
        System.out.println("JarBApp running...");
        JarAApp a = new JarAApp();
    }
    public void print(String str){
        JarAApp a = new JarAApp();
        a.print("JarBApp print: " + str);
    }
}