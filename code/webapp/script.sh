#!/bin/bash
echo "run build"
rm -rf build
javac -cp .:/home/diman/Downloads/tomcat/lib/servlet-api.jar -d build/WEB-INF/classes App.java
cp web.xml build/WEB-INF/web.xml
cd build
jar -cvf webapp.war *
echo "done"