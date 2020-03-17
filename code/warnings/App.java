package com.java.test;

/**
 * https://docs.oracle.com/javase/6/docs/technotes/tools/solaris/javac.html#options
 * Pay attention that "all" type is not default for JDK, it's supported by Intellij & Eclipse IDE but not by javac
 *
 * https://docs.oracle.com/javase/specs/jls/se7/html/jls-9.html
 * By default jsl support only 2 types: {"unchecked", "deprecation"}
 *
 */

import java.util.*;

public class App {
    //uncomment to remove warnings
    @SuppressWarnings({"rawtypes", "unchecked", "deprecation"})
    public static void main(String[] args) {
        List people = new ArrayList();
        Object human = new Object();
        people.add(human);

        Printer printer = new Printer();
        printer.print();

        System.out.println("running app...");
    }
}

class Printer{
    @Deprecated
    public void print(){}
}