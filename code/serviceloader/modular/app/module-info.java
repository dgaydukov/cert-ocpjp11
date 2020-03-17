module com.java.app{
    requires com.java.service;

    /**
     * if we comment out interface/class and try to use ServiceLoader, we got runtime error
     * ServiceConfigurationError: com.java.service.InterfacePrinter: module com.java.app does not declare `uses`
     */
    uses com.java.service.AbstractClassPrinter;
    uses com.java.service.ConcreteClassPrinter;
    uses com.java.service.InterfacePrinter;


    /**
     * Module can be service provider and user at the same time, but it should explicitly deifne provides
     * otherwise there is no way to load service,
     * even Class.forName("com.java.app.AppInterfacePrinter"); won't help
     */
    provides com.java.service.InterfacePrinter with com.java.app.AppInterfacePrinter;
}