# Java Version

### Contents
* [JDK JRE JVM](#jdk-jre-jvm)
* [JDK Releases](#jdk-releases)
* [Check your Java version](#check-your-java-version)
* [Switch Java versions on MacOS](#switch-java-versions-on-macos)

### JDK JRE JVM
Don't confuse between:
* JRE (Java Runtime Environment) - everything necessary to run compiled java program, including: JVM, java class library, `java` command. Use it if you only need to run compiled programs.
* JDK (Java Development Kit) - JRE plus tools like `javac/javadoc/jdb`. Use it for creating/compiling new programs
* JVM (Java Virtual Machine) - virtual machine that runs the Java bytecodes. It doesn't understand source code, so you have to use compiler to compile `.java` files into `.class` files with bytecode.

### JDK Releases
There are multiple versions of JDK builds by different vendors:
* [OracleJDK](https://www.oracle.com/ae/java/technologies/downloads/archive/) - build made by Oracle itself. it's paid version.
* [OpenJDK](https://jdk.java.net/archive/) - build made by [OpenJDK community](https://openjdk.org/) where Oracle is major contributor. So the difference with OracleJDK is licensing. It has a faster release cycle, with new feature releases every six months. OpenJDK is the official open-source reference implementation of Java SE. It is licensed under the GNU General Public License (GPL). Available for MacOS. includes only `OpenJDK`, but under-the-hood also uses HotSpot as JVM, because Oracle is main contributor for OpenJDK.
* [Amazon Corretto](https://docs.aws.amazon.com/corretto/) - build by Amazon with LTS support and for free.
* [Microsoft Build of OpenJDK](https://www.microsoft.com/openjdk) - build by Microsoft with LTS support and for free.
* [Eclipse Adoptium](https://adoptium.net/en-GB/temurin/releases) (formerly AdoptOpenJDK) - build by Eclipse Foundation. Eclipse Temurin is the name of the OpenJDK distribution produced by the Eclipse Adoptium project.
* [GraalVM](https://www.graalvm.org/downloads) - (formerly Oracle GraalVM Enterprise) is built on top of the Oracle JDK.
* [Azul Zulu Builds of OpenJDK](https://www.azul.com/downloads/?package=jdk#zulu) - build by Azul Systems for low latency systems with their own JVM & GC. You can download JDK and use for development purposes for free, but for production you have to pay the license
* [Azul Platform Prime](https://www.azul.com/downloads/#prime) - paid build of OpenJDK by Azul with VM codename `Zulu`, with significant improvements of JVM including their famous C4GC (Continuously Concurrent Compacting Collector), but available only for Linux, no MacOS support.
* [Red Hat](https://developers.redhat.com/products/openjdk/download) - build of OpenJDK by Red Hat, but it also doesn't have a build for MacOS, on website they say that they have a build for MacOS called `temurin`, which you can use. And Eclipse Temurin is based on Red Hat build of OpenJDK

|                             | JVM codename | Based on   | License | LTS support   | Available for MacOS |
|-----------------------------|--------------|------------|---------|---------------|---------------------|
| Oracle JDK                  | HotSpot      | OpenJDK    | Paid    | 8, 11, 17, 21 | YES                 |
| OpenJDK                     | HotSpot      | OpenJDK    | Free    | NO            | YES                 |
| Amazon Corretto             | Corretto     | OpenJDK    | Free    | 8, 11, 17, 21 | YES                 |
| Microsoft Build of OpenJDK  | Microsoft    | OpenJDK    | Free    | 11, 17, 21    | YES                 |
| Eclipse Adoptium            | Temurin      | Red Hat    | Free    | 8, 11, 17, 21 | YES                 |
| GraalVM                     | GraalVM      | Oracle JDK | Free    | 11, 17, 21    | YES                 |
| Azul Zulu Builds of OpenJDK | Zulu         | OpenJDK    | Free    | 8, 11, 17, 21 | YES                 |
| Azul Platform Prime         | Zulu         | OpenJDK    | Paid    | 8, 11, 17, 21 | NO                  |
| Red Hat                     | Temurin      | OpenJDK    | Free    | 8, 11, 17, 21 | NO                  |


Don't confuse:
* OpenJDK - community JDK mainly developed and maintained by Oracle with some features from other companies like RedHat, Amazon, Azul. It doesn't have concept of LTS. Every 6 months new version is released, and that's all. Older versions are not updated with any changes, that's why for OpenJDK you have to always use latest version, otherwise you risk some bugs or problems.
* Oracle JDK - proprietary JDK by Oracle based on OpenJDK with concept of LTS (long-term support) where some versions like 8/11/17/21 are maintained and supported for many years. So the main difference from OpenJDK is some additional features and LTS. For example latest LTS is 21, but latest java is 24. So for OpenJDK you have to download java24, because java21 for OpenJDK is already outdated, nobody update and patch it. But for Oracle you can download java 21 and use it long time, because Oracle would update/patch it with changes.
* others - all other JDK from Amazon, Red Hat, Azul and so on, basically is raw OpenJDK with some additional features added on top based on company. For example Azul concentrate a lot on performance so their JDK is the same OpenJDK but with a lot of performance tuning for faster code execution. So we can say that OpenJDK is a baseline for all other JDK.

Which JDK to choose out of 3(open, oracle, amazon):
* Use OpenJDK for free, but upgrade every 6 months to get updates
* Use a paid JDK from Oracle or another vendor
* Use Corretto for free, and get free updates for several years


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
# run this command for multiple JDKs
java -XX:+PrintCommandLineFlags -version

# Oracle JDK 21
-XX:ConcGCThreads=3 -XX:G1ConcRefinementThreads=13 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=2147483648 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=31675383808 -XX:MinHeapSize=6815736 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedOops -XX:+UseG1GC
java version "21.0.7" 2025-04-15 LTS
Java(TM) SE Runtime Environment (build 21.0.7+8-LTS-245)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.7+8-LTS-245, mixed mode, sharing)

# OpenJDK 21
-XX:ConcGCThreads=3 -XX:G1ConcRefinementThreads=13 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=2147483648 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=31675383808 -XX:MinHeapSize=6815736 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedOops -XX:+UseG1GC
openjdk version "21.0.2" 2024-01-16
OpenJDK Runtime Environment (build 21.0.2+13-58)
OpenJDK 64-Bit Server VM (build 21.0.2+13-58, mixed mode, sharing)

# Amazon Corretto 21
-XX:ConcGCThreads=3 -XX:G1ConcRefinementThreads=13 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=2147483648 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=31675383808 -XX:MinHeapSize=6815736 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedOops -XX:+UseG1GC
openjdk version "21.0.8" 2025-07-15 LTS
OpenJDK Runtime Environment Corretto-21.0.8.9.1 (build 21.0.8+9-LTS)
OpenJDK 64-Bit Server VM Corretto-21.0.8.9.1 (build 21.0.8+9-LTS, mixed mode, sharing)

# Microsoft Build of OpenJDK 21
-XX:ConcGCThreads=3 -XX:G1ConcRefinementThreads=13 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=2147483648 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=31675383808 -XX:MinHeapSize=6815736 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedOops -XX:+UseG1GC
openjdk version "21.0.8" 2025-07-15 LTS
OpenJDK Runtime Environment Microsoft-11933201 (build 21.0.8+9-LTS)
OpenJDK 64-Bit Server VM Microsoft-11933201 (build 21.0.8+9-LTS, mixed mode, sharing)

# Eclipse Adoptium JDK 21
-XX:ConcGCThreads=3 -XX:G1ConcRefinementThreads=13 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=2147483648 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=31675383808 -XX:MinHeapSize=6815736 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedOops -XX:+UseG1GC
openjdk version "21.0.8" 2025-07-15 LTS
OpenJDK Runtime Environment Temurin-21.0.8+9 (build 21.0.8+9-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.8+9 (build 21.0.8+9-LTS, mixed mode, sharing)

# GraalVM 21
-XX:ConcGCThreads=3 -XX:+EnableJVMCIProduct -XX:G1ConcRefinementThreads=13 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=2147483648 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=31675383808 -XX:MinHeapSize=6815736 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:ThreadPriorityPolicy=1 -XX:-UnlockExperimentalVMOptions -XX:+UseCompressedOops -XX:+UseG1GC
java version "21.0.8" 2025-07-15 LTS
Java(TM) SE Runtime Environment Oracle GraalVM 21.0.8+12.1 (build 21.0.8+12-LTS-jvmci-23.1-b72)
Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 21.0.8+12.1 (build 21.0.8+12-LTS-jvmci-23.1-b72, mixed mode, sharing)

# Azul Zulu 21
-XX:ConcGCThreads=3 -XX:G1ConcRefinementThreads=13 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=2147483648 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=31675383808 -XX:MinHeapSize=6815736 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedOops -XX:+UseG1GC
openjdk version "21.0.8" 2025-07-15 LTS
OpenJDK Runtime Environment Zulu21.44+17-CA (build 21.0.8+9-LTS)
OpenJDK 64-Bit Server VM Zulu21.44+17-CA (build 21.0.8+9-LTS, mixed mode, sharing)
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
