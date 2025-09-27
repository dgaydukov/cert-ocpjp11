#!/bin/bash


# cleanup
clear
rm -rf ./compiled

echo && echo "__Building printer__"
javac -d compiled/printer printer/Printer.java printer/module-info.java
jar --create --verbose --file=compiled/printer.jar -C compiled/printer .

echo && echo "__Building app__"
javac -d compiled/app  --module-path=compiled/printer.jar app/App.java app/module-info.java
jar --create --verbose --file=compiled/app.jar -C compiled/app .

echo && echo "__Run app__"
java --module-path=compiled/printer.jar:compiled/app.jar --module module.app/com.java.app.App

