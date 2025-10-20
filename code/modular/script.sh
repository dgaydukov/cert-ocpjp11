rm -rf output && clear

echo "Building unnamed module: printer"
javac -d output/printer printer/Printer.java
jar --create --verbose --file=output/printer.jar -C output/printer .

echo "Building automatic module: calculator"
javac -d output/calculator --class-path=output/printer.jar calculator/Calculator.java
jar --create --verbose --file=output/calculator.jar -C output/calculator .

echo "Building named module: app"
javac -d output --module-path=output/calculator.jar --module-source-path=. --module=app
jar --create --verbose --file=output/app.jar -C output/app .

echo "Running..."
# load all 3 jars as modules, calculator and printer - both automatic modules
java --module-path="output/app.jar;output/calculator.jar;output/printer.jar" --module=app/com.java.app.App
# load calculator as automatic module, but printer as unnamed module
java --module-path="output/app.jar;output/calculator.jar" --class-path=output/printer.jar --module=app/com.java.app.App