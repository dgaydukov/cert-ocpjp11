#!/bin/bash
echo "run build"
rm -rf build
# we use tomcat lib/servlet-api.jar to get access to javax.servlet package
javac -cp .:/home/diman/Documents/tomcat/lib/servlet-api.jar -d build/WEB-INF/classes App.java
cp web.xml build/WEB-INF/web.xml
cd build
jar -cvf webapp.war ./*
echo "done"