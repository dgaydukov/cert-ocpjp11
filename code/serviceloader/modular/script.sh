#!/bin/bash

clear
rm -rf ./compiled

echo && echo "__Compile Service__"
javac -d compiled/service service/*.java
jar --create --file compiled/service/service.jar compiled/service/com/java/service/*.class

echo && echo "__Compile Foo ServiceProvider__"
javac -d compiled/foo foo/*.java --module-path compiled/service
jar --create --file compiled/foo/foo.jar -C compiled/foo .

echo && echo "__Compile Bar ServiceProvider__"
javac -d compiled/bar bar/*.java --module-path compiled/service
jar --create --file compiled/bar/bar.jar -C compiled/bar .

echo && echo "__Run App__"
javac -d compiled/app app/*.java --module-path compiled/service
java --module-path compiled/app:compiled/service:compiled/foo:compiled/bar --module com.java.app/com.java.app.App