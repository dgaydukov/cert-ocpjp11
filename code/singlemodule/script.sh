#!/bin/bash
clear
rm -rf ./compiled

javac -d compiled ./*.java
jar --create --file compiled/app.jar --main-class com.java.test.App -C compiled .
java --module-path compiled/app.jar --module app/com.java.test.App

jdeps --class-path compiled/app.jar compiled/app.jar
jdeps --module-path compiled/app.jar compiled/app.jar
jdeps compiled/app.jar