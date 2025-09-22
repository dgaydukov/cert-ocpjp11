#!/bin/bash

# cleanup
clear
rm -rf ./compiled

echo && echo "__Running app__"
javac -Xlint -d compiled App.java
java -cp compiled App