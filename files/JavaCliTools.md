# Java Command Line Tools

### Description
Here we would talk about command line tools for java that resides under `bin` directory of JDK installation. Some tools are not part of JDK anymore, so I would specifically mention them. They are mostly GUI tools.

### List of JDK Tools
1. Compilation & Code Generation
* `javac` – Java compiler
* `javadoc` – Generate API documentation from source code
* `javap` – Disassemble class files / inspect bytecode

2. Running Java Applications
* `java` – Launch Java applications
* `jshell` – Interactive Java REPL
* `jrunscript` – Script shell for Java scripting languages

3. Packaging & Modules
* `jar` – Package and manage JAR files
* `jarsigner` – Sign and verify JAR files
* `jlink` – Create custom runtime images with `.jmod` files
* `jmod` – allows you to create Java module files `.jmod`, intermediate format (neither `.jar` nor native binaries)
* `jpackage` – Create platform-specific packages
* `jimage` – Inspect/modify runtime image files

4. Monitoring & Profiling
* `jconsole` – GUI for JVM monitoring (memory, threads, CPU)
* `jcmd` – Send diagnostic commands to a running JVM: you can attach gc logs, or jfr recording to already running application without the need to restart it
* `jstat` – JVM statistics monitoring
* `jstatd` – Remote JVM statistics daemon
* `jfr` – Start/record Java Flight Recorder sessions
* `jhsdb` – Java HotSpot Debugger (debug core dumps and live JVMs): used in combination with `jstack/jmap/iinfo/jsnap` to get more details.
* `jinfo` – Show JVM configuration info: like system properties, JVM flags for running application. You can also modify JVM flags dynamically, but only some of them.
* `jmap` – Print memory usage / heap info / generate heap dumps
* `jstack` – Print thread stack traces: useful to understand if you have deadlocks or long-running threads

5. Debugging & Analysis
* `jdb` – Command-line debugger
* `jdeps` – Analyze class dependencies
* `jdeprscan` – Scan for deprecated APIs
* `serialver` – Print serialVersionUID of classes

6. Networking & Misc
* `jps` – List running Java processes
* `rmiregistry` – RMI registry daemon
* `jwebserver` – Simple HTTP server
* `keytool` – Manage keys and certificates 

7. Other tools - not available under `bin` directory, you have to download them separately:
* `VisualVM` - originally part of JDK, since java9 it is a separate download - graphical monitoring, profiling, and troubleshooting tool for Java applications. It was removed from java9, because only CLI tools are available, and complex GUI tools was a bit too much for JDK distribution. You can download it from [VisualVM](https://visualvm.github.io/). You can download MacOS `dmg` file and install it into `/Applications` directory.
You can use it to view jfr files, heap dumps, thread dumps, and monitor running applications.
```java
public class App {
    public static void main(String[] args) {
        int size = 100_000_000;
        for (int i = 0; i < 10; i++) {
            Object[] arr = new Object[size];
            for (int j = 0; j < size; j++) {
                arr[j] = new Object();
            }
        }
    }
}
```
run with `java -XX:StartFlightRecording=filename=recording.jfr App.java` => then you can open `recording.jfr` file in VisualVM.
* `JMC` (JDK Mission Control) - GUI tool to view/analyze jfr files, just like `VisualVM` used for development/production debugging and monitoring. Since java11 is distributed separately, but before was part of JDK. You can download it from [JMC](https://www.oracle.com/java/technologies/javase/jmc.html). You download archive, unpack and launch GUI tool (compare to VisualVM no need to install it).
* `GCViewer` - you can use https://github.com/chewiebug/GCViewer tools to visualize GC `java -jar gcviewer-1.36.jar`
