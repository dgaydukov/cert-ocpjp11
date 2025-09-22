#!/bin/bash
# simple class without package to see the flag that force assertion checks

# cleanup
clear
rm -rf ./compiled

echo && echo "Compile and Run"
javac -d compiled App.java
java -cp compiled -ea App
java -cp compiled -enableassertions App

echo && echo "Run as single file"
java -ea App.java
java -enableassertions App.java