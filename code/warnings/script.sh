#!/bin/bash

rm -rf compiled
javac -Xlint -d compiled App.java
java -cp compiled com.java.test.App