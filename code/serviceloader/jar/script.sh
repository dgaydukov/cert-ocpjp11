#!/bin/bash
# With ServiceLoader you can load both interface and abstract and concrete classes
clear
rm -rf ./compiled

#compile interface
javac -d compiled/service service/*.java
jar --create --file compiled/service/service.jar compiled/service/com/java/service/*.class

#compile first interface provider (foo impl)
javac -d compiled/foo foo/*.java -cp compiled/service
mkdir -p compiled/foo/META-INF/services
echo "com.java.foo.FooAbstractClassPrinter" > compiled/foo/META-INF/services/com.java.service.AbstractClassPrinter
echo "com.java.foo.FooConcreteClassPrinter" > compiled/foo/META-INF/services/com.java.service.ConcreteClassPrinter
echo "com.java.foo.FooInterfacePrinter" > compiled/foo/META-INF/services/com.java.service.InterfacePrinter
echo "com.java.foo.SecondFooInterfacePrinter" >> compiled/foo/META-INF/services/com.java.service.InterfacePrinter
jar --create --file compiled/foo/foo.jar -C compiled/foo .

#compile second interface provider (bar impl)
javac -d compiled/bar bar/*.java -cp compiled/service
mkdir -p compiled/bar/META-INF/services
echo "com.java.bar.BarInterfacePrinter" > compiled/bar/META-INF/services/com.java.service.InterfacePrinter
jar --create --file compiled/bar/bar.jar -C compiled/bar .

# run app
javac -d compiled/app app/App.java -cp compiled/service
java -cp "compiled/app:compiled/service:compiled/foo:compiled/bar" com.java.app.App