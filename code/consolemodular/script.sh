#!/bin/bash
:'
--add-modules - add module to module graph (need to use it if we using --add-reads)
--add-reads - read packages from module (the same as requires in module-info)
--add-exports - export packages to module (the same as exports in module-info)
--add-opens - only when you run, superset on add-exports, allows deep reflectional access to private types
'

# cleanup
clear
rm -rf ./compiled

echo && echo "__Building printer__"
javac -d compiled/printer printer/Printer.java printer/module-info.java
jar --create --verbose --file=compiled/printer.jar -C compiled/printer .



echo && echo "__Building app__"
javac -d compiled/app  --module-path=compiled/printer.jar \
                       --add-modules=module.printer \
                       --add-reads module.app=module.printer \
                       --add-exports module.printer/com.java.printer=module.app \
                       app/App.java app/module-info.java
jar --create --verbose --file=compiled/app.jar -C compiled/app .

echo && echo "__Run app__"
java --module-path="compiled/printer.jar;compiled/app.jar" \
                       --add-modules=module.printer \
                       --add-reads module.app=module.printer \
                       --add-exports module.printer/com.java.printer=module.app \
                       --module module.app/com.java.app.App
