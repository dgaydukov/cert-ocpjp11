#!/bin/bash
:'
--add-modules - add module to module graph (need to use it if we using --add-reads)
--add-reads - read packages from module (the same as requires in module-info)
--add-exports - export packages to module (the same as exports in module-info)
--add-opens - only for java, superset on add-exports, allows deep reflectional access to private types
'


clear
rm -rf ./compiled

echo && echo "__Building printer__"
javac -d compiled/printer printer/Printer.java printer/module-info.java
jar --create --file compiled/printer.jar -C compiled/printer .



echo && echo "__Building app__"
javac -d compiled/app  --module-path compiled/printer.jar \
                       --add-modules com.java.printer \
                       --add-reads com.java.app=com.java.printer \
                       --add-exports com.java.printer/com.java.printer=com.java.app \
                       app/App.java app/module-info.java
jar --create --file compiled/app.jar -C compiled/app .
java --module-path compiled/printer.jar:compiled/app.jar \
                       --add-modules com.java.printer \
                       --add-reads com.java.app=com.java.printer \
                       --add-exports com.java.printer/com.java.printer=com.java.app \
                       --module com.java.app/com.java.app.App