# Java command-line tools

### Content
* [Building and running java](#building-and-running-java)

### Building and running java
There are 3 commands available for java cmd:
* `javac` stands for java compiler - tool to compile java file into binary file with `.class` extension
* `jar` stands for java archiver - tool to build special executable file with `.jar` extension
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