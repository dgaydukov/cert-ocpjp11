#!/bin/bash
clear
rm -rf ./compiled


echo && echo "__Building jarA__"
javac -d compiled/jarA jarA/*.java
jar --create --file compiled/jarA.jar --main-class com.java.jara.JarAApp -C compiled/jarA .
jar --list --file compiled/jarA.jar
# 3 ways to run app
java --class-path compiled/jarA     com.java.jara.JarAApp
java --class-path compiled/jarA.jar com.java.jara.JarAApp
java -jar compiled/jarA.jar


echo && echo "__Building jarB__"
javac -d compiled/jarB jarB/JarBApp.java --class-path compiled/jarA.jar
jar --create --file compiled/jarB.jar --manifest jarB/MANIFEST.MF -C compiled/jarB .
jar --list --file compiled/jarB.jar

# 3 ways to run app
java --class-path "compiled/jarB;compiled/jarA" com.java.jarb.JarBApp
java --class-path "compiled/jarB.jar;compiled/jarA.jar" com.java.jarb.JarBApp
java -jar compiled/jarB.jar


echo && echo "__Building moduleC__"
#3 ways to compile module
#1. javac -d outDir sourceDir/*
#2. javac -d outDir --module-source-path sourceDir sourceDir/*
#3. javac -d outDir --module-source-path sourceDir --module sourceDir
javac -d compiled/moduleC moduleC/*.java
jar --create --file compiled/moduleC.jar --main-class com.java.modulec.ModuleCApp -C compiled/moduleC .
jar --list --file compiled/moduleC.jar
# 4 ways to run app
# we can run module as simple jar (just pass main class) or as module (pass --module option)
java --class-path compiled/moduleC     com.java.modulec.ModuleCApp
java --class-path compiled/moduleC.jar com.java.modulec.ModuleCApp
java -jar compiled/moduleC.jar
# run as module
java --module-path compiled/moduleC.jar --module moduleC/com.java.modulec.ModuleCApp
echo && echo "__Building moduleC_with module-source-path__"
# for module-source-path to work, module name should correspond to directory name
javac -d compiled/moduleC2 --module-source-path ./ --module moduleC
java --module-path compiled/moduleC2 --module moduleC/com.java.modulec.ModuleCApp
javac -d compiled/moduleC3 --module-source-path ./ moduleC/*.java
java --module-path compiled/moduleC3 --module moduleC/com.java.modulec.ModuleCApp


echo && echo "__Building moduleD__"
javac -d compiled/moduleD moduleD/ModuleDApp.java moduleD/module-info.java --module-path "compiled/moduleC.jar;compiled/jarB.jar"
jar --create --file compiled/moduleD.jar --main-class com.java.moduled.ModuleDApp -C compiled/moduleD .
jar --list --file compiled/moduleD.jar
java --module-path "compiled/moduleD.jar;compiled/moduleC.jar;compiled/jarB.jar;compiled/jarA.jar" --module moduleD/com.java.moduled.ModuleDApp
java --class-path compiled/jarA.jar --module-path "compiled/moduleD.jar;compiled/moduleC.jar;compiled/jarB.jar" --module moduleD/com.java.moduled.ModuleDApp

echo && echo "__View dependencies moduleD__"
# standard view
#jdeps --class-path compiled/jarA.jar --module-path compiled/moduleD.jar:compiled/moduleC.jar:compiled/jarB.jar compiled/moduleD.jar
#echo && echo "recursive summary"


jdeps -s -recursive --module-path "compiled/moduleD.jar;compiled/moduleC.jar;compiled/jarB.jar;compiled/jarA.jar" compiled/moduleD.jar
jdeps -s -recursive --class-path compiled/jarA.jar --module-path "compiled/moduleD.jar;compiled/moduleC.jar;compiled/jarB.jar" compiled/moduleD.jar