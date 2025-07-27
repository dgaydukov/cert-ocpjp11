# Java Version

### Contents
* [JDK Releases](#jdk-releases)
* [Check your Java version](#check-your-java-version)
* [Switch Java versions on MacOS](#switch-java-versions-on-macos)

### JDK Releases
There are multiple versions of JDK builds by different vendors:
* [OracleJDK](https://www.oracle.com/ae/java/technologies/downloads/archive/) - build made by Oracle itself. Oracle JDK is Oracle's commercially licensed version of Java SE. While certain versions or usage scenarios might be free for development and testing, commercial deployment generally requires a paid license under the Oracle Binary Code License Agreement.
* [OpenJDK](https://jdk.java.net/archive/) - build made by [OpenJDK community](https://openjdk.org/) where Oracle is major contributor. So the difference with OracleJDK is licensing. Has a faster release cycle, with new feature releases every six months. OpenJDK is the official open-source reference implementation of Java SE. It is licensed under the GNU General Public License (GPL)
* [Amazon Corretto](https://docs.aws.amazon.com/corretto/) - build by Amazon
* Azul Zulu - build by Azul Systems for low latency systems with their low latency GC
* Microsoft Build of OpenJDK
* Red Hat
* GraalVM
* Eclipse Temurin

### Check your Java version
If you run this code:
```java
System.out.println(System.getProperty("java.version"));
System.out.println(System.getProperty("java.vendor"));
System.out.println(System.getProperty("java.vm.name"));
System.out.println(System.getProperty("java.home"));
```
And get this output:
```shell
21.0.7
Oracle Corporation
Java HotSpot(TM) 64-Bit Server VM
/Library/Java/JavaVirtualMachines/jdk-21.0.7.jdk/Contents/Home
```
But if you run console command `java --version`, you can see all the output from console including versions and JDK build.
Java come as part of JDK (java development kit) which can have multiple builds by multiple companies. The default one is OpenJDK build by Oracle, but other builds exists. You can get your build by running this command `java --version` - you will see not only version but JDK build. I have several builds installed, so if I run on my MacOS terminal, I can see this:
```shell
# java 8 (AdoptOpenJDK)
openjdk version "1.8.0_292"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_292-b10)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.292-b10, mixed mode)
# java 11 (Homebrew)
openjdk version "11.0.25" 2024-10-15
OpenJDK Runtime Environment Homebrew (build 11.0.25+0)
OpenJDK 64-Bit Server VM Homebrew (build 11.0.25+0, mixed mode)
# java 21 (Homebrew)
openjdk 21.0.5 2024-10-15
OpenJDK Runtime Environment Homebrew (build 21.0.5)
OpenJDK 64-Bit Server VM Homebrew (build 21.0.5, mixed mode, sharing)

# java8 (OpenJDK)
java version "1.8.0_441"
Java(TM) SE Runtime Environment (build 1.8.0_441-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.441-b07, mixed mode)
# java11 (OpenJDK)
java version "11.0.26" 2025-01-21 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.26+7-LTS-187)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.26+7-LTS-187, mixed mode)
# java17 (OpenJDK)
java version "17.0.14" 2025-01-21 LTS
Java(TM) SE Runtime Environment (build 17.0.14+8-LTS-191)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.14+8-LTS-191, mixed mode, sharing)
# java 21 (OpenJDK)
java version "21.0.7" 2025-04-15 LTS
Java(TM) SE Runtime Environment (build 21.0.7+8-LTS-245)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.7+8-LTS-245, mixed mode, sharing)
```

Don't confuse: OpenJDK vs HotSpot:
* From Java 11 forward, Oracle JDK builds and OpenJDK builds will be essentially identical
* HotSpot is an implementation of the JVM concept. It was originally developed by Sun, and now it is owned by Oracle
* So it's 2 different concept: HotSpot is a codename for JVM, and OpenJDK is a codename for Oracle JDK, which includes HotSpot
* There are [many others JVM](https://en.wikipedia.org/wiki/List_of_Java_virtual_machines)

### Switch Java versions on MacOS:
* You can use `jenv` to switch between different java versions
* see [instruction](https://gist.github.com/gramcha/81dcec3f1e4ce8cffd7f248d3e2a42a7)
* Then you can download JDK from official oracle website and unzip them into `/Library/Java/JavaVirtualMachines` directory
* Perform below operations to manage several JDK versions:
```shell
# check available JDK installation (get from here /Library/Java/JavaVirtualMachines)
/usr/libexec/java_home -V

# add JDK to jenv (add all available versions this way)
jenv add /Library/Java/JavaVirtualMachines/jdk1.8.0_441.jdk/Contents/Home
# view available JDK versions
jenv versions
# switch to specific JDK version
jenv global 1.8.0.441

# check java and maven version
java -version
mvn -version
```
How `jenv` works:
* for java to properly work we need to set up 2 env variables (this is true for all OS like Linux, MacOS, Windows):
    * `JAVA_HOME` - points to JDK installation directory. You can directly assign new value to it, like: `JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.0.7.jdk/Contents/Home`
    * `PATH` - should contain `$JAVA_HOME/bin` so we can run java commands like `java`, `javac`, `mvn`, etc. Here you must append java path to home, not to overwrite it, do like this this: `PATH=$JAVA_HOME/bin:$PATH`
* so if you want to have multiple JDK versions, you can just play with `JAVA_HOME` and `PATH` variables, and you can switch between different java versions. But doing this can be cumbersome, so `jenv` is a tool that helps you to manage these variables and switch between different JDK versions easily. If you have `jenv` you can clearly see that both `JAVA_HOME` and `PATH` variables are set up by `jenv`, so you don't have to do it manually.
```shell
echo $JAVA_HOME
/Users/diman/.jenv/versions/oracle64-21.0.7

echo $PATH
/Users/diman/.jenv/bin:{OTHER_PATH...}
```
As you see, this utility under-the-hood manage 2 env vars `JAVA_HOME` and `PATH`, so you can switch between different JDK versions easily.
