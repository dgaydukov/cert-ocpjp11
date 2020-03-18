package com.java.test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class App{
    public static void main(String[] args) throws Exception {
        PrinterMBean my = new Printer();
        ObjectName objectName = new ObjectName("com.java.test:type=Printer");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(my, objectName);
        Thread.sleep(Integer.MAX_VALUE);
    }
}



class Printer implements PrinterMBean {
    @Override
    public void print(){
        System.out.println("Printer.print");
    }
}