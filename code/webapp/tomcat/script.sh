#!/bin/bash
echo "run build"
# export tomcat directory
export TOMCAT_DIR=/home/diman/Documents/apache-tomcat-9.0.62
rm -rf build
# we use tomcat lib/servlet-api.jar to get access to javax.servlet package
javac -cp .:$TOMCAT_DIR/lib/servlet-api.jar -d build/WEB-INF/classes App.java
cp web.xml build/WEB-INF/web.xml
cd build
jar -cvf webapp.war ./*
echo "done"