# OCPJP11 (Oracle Certified Professional: Java SE 11 Developer)

### Description
Here is my experience for taking 2 exams 
* [Java SE 11 Programmer I | 1Z0-815](https://education.oracle.com/java-se-11-programmer-i/pexam_1Z0-815)
* [Java SE 11 Programmer II | 1Z0-816](https://education.oracle.com/java-se-11-programmer-ii/pexam_1Z0-816) 

To become certified and get eCertificate you have to pass both of them.
This guide covers more than you need to pass exam, cause I've added more info evan after I've passed the exam. 
But this can be even better, cause with this you will understand java more deeper.

### Why do you need it
There are 2 main reasons to get it.
1. During preparation you will learn a lot of new stuff
2. It may help your career

### Content
* [Java Tips](https://github.com/dgaydukov/cert-ocpjp11/blob/master/files/ocpjp11.md)

### Useful Links
Below is mock exams of good quality and cheap price:
* [Mock exam programmer I](https://enthuware.com/java-certification-mock-exams/oracle-certified-associate/ocp-java-11-exam-i-1z0-815)
* [Mock exam programmer II](https://enthuware.com/java-certification-mock-exams/oracle-certified-professional/ocp-java-11-exam-ii-1z0-816)

### My Results
By passing certification you will get 3 things
* [Digital badge](https://www.youracclaim.com/badges/e012ec2d-fb28-4694-97b8-cf5b2f8eac7d)
* [Oracle Java SE 11 Professional eCertificate](https://github.com/dgaydukov/cert-ocpjp11/blob/master/files/cert.pdf)

### TODO
* divide ocp document into low-latency section & move everything under it from misc section
* core java videos
    * compare chronicle-logger vs async log4j with jmh (implement testing like it high-throughput trading system)
    * https://www.youtube.com/watch?v=CnRtbtis79U (Алексей Шипилёв — Shenandoah: сборщик мусора, который смог)
    * https://www.youtube.com/watch?v=iGRfyhE02lA (Владимир Иванов — G1 Garbage Collector)
    * https://www.youtube.com/watch?v=c1jVn5Sm8Uw (Алексей Шипилёв – Shenandoah GC 2.0)
    * https://www.youtube.com/watch?v=iB2N8aqwtxc (Алексей Шипилёв — Прагматика Java Memory Model)
    * https://www.youtube.com/watch?v=FL7_lxJbX0o (Иван Землянский — Аерон. High performance-транспорт для low latency-микросервисов)
    * https://real-logic.co.uk/about.html (videos by Martin Thompson)
    * https://www.infoq.com/presentations/mechanical-sympathy
    * http://www.coralblocks.com/index.php/state-of-the-art-distributed-systems-with-coralmq (sequencer architecture)
* java low latency
    * The Art of Multiprocessor Programming (check both editions)
    * run time DI (spring) vs compile time DI (dagger)
    * java low latency logging (Log4j2 async use lmax disruptor inside)
    * http://java-performance.info/hashmap-overview-jdk-fastutil-goldman-sachs-hppc-koloboke-trove-january-2015 (goldman sachs using https://github.com/leventov/Koloboke as low latency collections)
    * check all the test for lamx disruptor to get real examples of usage (https://github.com/LMAX-Exchange/disruptor/tree/master/src/test/java/com/lmax/disruptor)
    * aeron vs aeron-cluster
    * netty for low latency (how it compares to lmax/aeron)
    * chronicle queue/map (how it works inside)