package com.java.test;


import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class App {
    public int x;
    private static final VarHandle VALUE;
    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            VALUE = l.findVarHandle(App.class, "x", int.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
    public static void main(String[] args) {
        App app = new App();

        VALUE.setVolatile(app, 10);
        int x = (Integer) VALUE.getVolatile(app);
        System.out.println(x);
    }
}