# Java command-line tools

### Content
* [Building and running java](#building-and-running-java)

### Building and running java
There are 3 commands available for java cmd:
* `javac` stands for java compiler - tool to compile java file into binary file with `.class` extension
  * we add `-d {directory}` option to build proper package structure into file - java expects us that package name is corresponding to file structure
* `jar` stands for java archiver - tool to build special executable file with `.jar` extension
  * we use 3 commands: `cvf` => `--create --verbose --file`
* `java` - tool to run jar files

If we have file `App.java` with public class inside
```java
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```
we can run it with this
```shell
# shortcut to run single file
java App.java
# compile java file into class file
javac App.java
# build jar file
jar cvf app.jar App.class 
# run jar => you will get error: no main manifest attribute, in app.jar
java -jar app.jar
# error is because jar is not executable, but you can run this way
java -cp app.jar App
```
Make jar executable
```shell
# add this file META-INF/MANIFEST.MF
Main-Class: App

# call this 
jar -cmvf META-INF/MANIFEST.MF app.jar App.class
# call
java -jar app.jar
```
Rules for class/file -names:
* file can contain only 1 public class with the same name as file - if you declare public class name different from filename you get compilation error: `error: class MyApp is public, should be declared in a file named MyApp.java`
* you can have many classes - all classes would compile into separate `.class` name
* you can have non-public class with different name - it would compile fine
