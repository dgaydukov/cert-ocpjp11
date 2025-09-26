# Modular App

### Description
We have very simple example with 2 modules: `app` which depends on `printer`. You can run `script.sh` to run the compilation.

### Investigate module content with jdeps
```shell
# if module doesn't depends on any other module, below 3 lines works the same
jdeps --module-path=compiled/printer.jar compiled/printer.jar
jdeps --class-path=compiled/printer.jar compiled/printer.jar
jdeps compiled/printer.jar

# if module depends on another, only this proper call would work
jdeps --module-path="compiled/printer.jar;compiled/app.jar" compiled/app.jar
```

### Empty module-info file
In our example we have module definition files with full details:
* module app
```java
module module.app{
    requires module.printer;
}
```
* module printer
```java
module module.printer{
    exports com.java.printer to module.app;
}
```
Yet we can omit `exports/requires` and leave body empty. In this case we would have to use command line params when we compile and run the app:
```
--add-modules - add module to module graph (need to use it if we using --add-reads)
--add-reads - read packages from module (the same as requires in module-info)
--add-exports - export packages to module (the same as exports in module-info)
--add-opens - only when you run, superset on add-exports, allows deep reflectional access to private types
```
Below is the full code how you should compile and run the app, if module definitions were empty:
```shell
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
```