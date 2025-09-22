#!/bin/bash
# simple class without package to see the flag that force assertion checks

echo "Compile and Run"
javac -d compiled App.java
java -cp compiled -ea App
java -cp compiled -enableassertions App

echo "Run as single file"
java -ea App.java
java -enableassertions App.java