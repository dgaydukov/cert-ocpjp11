module com.java.foo{
    requires com.java.service;

    // if module provides several implementation they should follow with comma (2 provides statement for the same interface won't compile)
    provides com.java.service.InterfacePrinter with com.java.foo.FooInterfacePrinter, com.java.foo.SecondFooInterfacePrinter;
    provides com.java.service.AbstractClassPrinter with com.java.foo.FooAbstractClassPrinter;
    provides com.java.service.ConcreteClassPrinter with com.java.foo.FooConcreteClassPrinter;
}