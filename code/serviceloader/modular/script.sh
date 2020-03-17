#!/bin/bash

rm -rf ./compiled

#compile interface
javac -d compiled/service service/*.java
jar --create --file compiled/service/service.jar compiled/service/com/java/service/*.class

#compile first interface provider (foo impl)
javac -d compiled/foo foo/*.java --module-path compiled/service
jar --create --file compiled/foo/foo.jar -C compiled/foo .

#compile second interface provider (bar impl)
javac -d compiled/bar bar/*.java --module-path compiled/service
jar --create --file compiled/bar/bar.jar -C compiled/bar .

# run app
javac -d compiled/app app/*.java --module-path compiled/service
java --module-path compiled/app:compiled/service:compiled/foo:compiled/bar --module com.java.app/com.java.app.App