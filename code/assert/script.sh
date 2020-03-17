#!/bin/bash

javac -d compiled App.java
java -cp compiled -ea com.test.java.App
java -cp compiled -enableassertions com.test.java.App

# run as single file
java -ea App.java
java -enableassertions App.java