module com.java.bar{
    requires com.java.service;

    provides com.java.service.InterfacePrinter with com.java.bar.BarInterfacePrinter;
}