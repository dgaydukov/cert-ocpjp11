package com.java.test;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class App {
    public static void main(String[] args) {
        // reflection
        {
            try {
                Secret secret = new Secret();
                Field f = secret.getClass().getDeclaredField("printer");
                f.setAccessible(true);
                Printer printer = (Printer) f.get(secret);
                printer.print("Access through reflection");
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                System.out.println(ex);
            }
        }

        // using unsafe
        {
            try {
                Secret secret = new Secret();
                Field f = secret.getClass().getDeclaredField("printer");
                Unsafe unsafe = getUnsafe();
                Printer printer = (Printer) unsafe.getObject(secret, unsafe.objectFieldOffset(f));
                printer.print("Access through sun.misc.Unsafe object");
            } catch (NoSuchFieldException ex) {
                System.out.println(ex);
            }
        }
    }

    public static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return  (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}

class Secret {
    private Printer printer =  new Printer();
}
class Printer {
    public void print(String msg) {
        System.out.println(msg);
    }
}