rm -rf output && clear

echo && echo "Building unnamed module: printer"
javac -d output/printer printer/Printer.java
jar --create --verbose --file=output/printer.jar -C output/printer .

echo && echo "Building automatic module: calculator"
javac -d output/calculator --class-path=output/printer.jar calculator/Calculator.java
jar --create --verbose --file=output/calculator.jar -C output/calculator .

echo && echo "Building named module: app"
javac -d output --module-path=output/calculator.jar --module-source-path=. --module=app
jar --create --verbose --file=output/app.jar -C output/app .

echo && echo "Running: both calculator.jar & printer.jar as automatic modules"
java --module-path="output/app.jar;output/calculator.jar;output/printer.jar" --module=app/com.java.app.App
echo && echo "Running: calculator.jar as automatic modules, but printer.jar as unnamed module loaded from --class-path"
java --module-path="output/app.jar;output/calculator.jar" --class-path=output/printer.jar --module=app/com.java.app.App