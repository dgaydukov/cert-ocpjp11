# Low latency

### Content
* 1 [Basics](#basics)
* 2 [CPU and Cache](#cpu-and-cache)
* 3 [Compiler Design](#compiler-design)
* 4 [Java memory model](#java-memory-model)
* 5 [GC and Weak References](#gc-and-weak-references)
* 6 [Garbage collector](#garbage-collector)
* 7 [JVM and GC tuning](#jvm-and-gc-tuning)
* 8 [Encoding](#encoding)
* 9 [Unsafe, VarHandle, MethodHandle](#unsafe-varhandle-methodhandle)
* 10 [Linked lists](#linked-lists)
* 11 [Low latency logging](#low-latency-logging)
* 12 [Low latency collections](#low-latency-collections)
* 13 [Java Agent](#java-agent)
* 14 [Java Object Layout](#java-object-layout)

### Basics
When you build low-latency system you should think how to store your data in memory. Not just use objects with getters/setters, but actually create objects that store data in off-heap (using unsafe or direct bytebuffer) and manually store all fields there.
change java version for Linux:
* first install several java versions, so you can switch between them
* you can switch both `java` and `javac` independently, for example you can compile under java8, and run under java11
```
# update java version
sudo update-alternatives --config java
# update java compiler version
sudo update-alternatives --config javac
# switch java version in intelliJ
File => Project Structure => Project SDK (chose either 8 or 11)
```
why MS Windows shows disk size less than they are 100gb = 93gb. Answer is simple, disk 1gb=1000mb, 1mb=1000kb, 1kb=1000bytes, this is the standard in SI (International System of Units), yet we have JEDEC (Joint Electron Device Engineering Council)  where each increasing on 1024, take a look at this table
```
        SI        JEDEC
kilo    10**3     2**10
mega    10**6     2**20
giga    10**7     2**30
tera    10**12    2**40
```
so windows just flip system of unit, 100gb in si became 93gb in JEDEC `100*10**9/2**30=93.13`

### CPU and Cache
there are 3 types of memory:
* memory - main memory of PC called RAM
* registers - cpu internal memory, the fastest memory available. it's size equal for cpu word size. if our cpu architecture is 32bit, then register size - 32bit, if 64bit - register size 64bit. this number should be multiple of memory unit size, since most modern pc are byte-addressable with memory unit size=8bit, or 1 byte, both 32 and 64 equally divide into 8bit. For pc that works mostly with text byte-addressable memory is better - cause min size of char in ascii is 7bi, for pc that works with numbers word-addressable memory is better, cause integer is 4byte
* cpu cache - memory built-in inside cpu (there are several layers inside, but for us it doesn't matter, all we care is that cpu has its own built-in memory). So when cpu needs data, it will read from memory into cache, and at some point flush data from the cache back to memory
* cache line - small memory block that is read from memory or flushed back to memory (you don't need to read/flush whole cache). With cpu cache we have the following problem: how we can store memory location from which we read single byte. if we would store it in cache, and memory unit size is up to 32 bit, so for each byte in cpu cache we should store 4 bytes with memory address. This is not reasonable, so instead cpu cache store cache line - 64bytes and first byte's memory address which is 4bytes, useful cache size - size of only data without memory unit address, most cpu caches shows only this number, so if cache size 256 byte with 4 lines, full size 4 x (64 + 4) = 272 bytes. When cpu needs data, it goes to cache, if data in cache - cache hit, if data not there - cache miss, cpu will load data from memory and overwrite cache. Cache controller - to avoid constant cache miss, this device try to predict which memory cpu will need next and in the background constantly overwrite cache from main memory, using different algos like LRU (least recently used).
  There are 2 types of architecture of cpu:
* instruction set architecture (called just architecture) - a set of instructions, data types, registers that cpu can execute
  instruction sets can be of different architectural complexity:
    * CISC (complex instruction set computer) - has many special instructions that rarely used in practice
      complex instructions are like:
        * transfer multiple register to/from memory
        * complex integer & floating point operations
        * atomic test-and-set
        * SIMD (single instruction multiple data) instructions
    * RISC (reduced instruction set computer) - only frequently used instructions implemented, less common instructions implemented as subroutines
    * VLIW (very long instruction word) - use ILP (instruction-level parallelism) with less hardware than CISC/RISC, compiler responsible for instruction issue and scheduling
        * EPIC (explicitly parallel instruction computing)
          good satire why itanium failed
          [How the Itanium Killed the Computer Industry](https://www.pcmag.com/archive/how-the-itanium-killed-the-computer-industry-236394)
          Donald Knuth said "The Itanium approach...was supposed to be so terrific—until it turned out that the wished-for compilers were basically impossible to write."
          main reason of failure - hard to write such compiler, at least back in 2001
          Don't confuse:
        * ILP - simultaneous execution of sequence of instructions in one clock cycle within single thread
          there are 2 types:
            * hardware (dynamic parallelism) - cpu decides on runtime which instructions to run in parallel, like intel pentium
            * software (static parallelism) - compiler decides during compilation what instructions to run in parallel, like intel itanium
        * concurrency - ability to run multiple threads within single process, where each thread running in separate cpu core
          There are 3 ways to improve performance(actually way more, these 3 are most common):
        * pipelining - execute different substeps of sequential code in parallel
        * execute multiple instructions simultaneously
        * out-of-order execution - execute instructions in different order then they were originally written in programming language
          All 3 require a lot of hardware processing, so VLIW move the burden into compilers. So such cpu provide more computing with less hardware
          complexity but with greater compiler complexity
    * MISC (minimal instruction set computer) - used in educational purposes
        * OISC (one instruction set computer)
* microarchitecture - internal design of cpu. Processors with different microarchitecture can have common instruction set, like Intel and AMD, although they have different internal design, yet share same set of instructions/registers.
  it represented as connection of machine elements which can be anything from registers to complete ALU (arithmetic logic unit). Concept of distinct microarchitecture as compare to instruction set was developed by IBM.
  Instruction may specify:
* opcode - instruction to be performed:
    * add/subtract/multiply/bitwise
* register (defined as register name)
    * set register to constant value
    * copy data from memory/register to memory/register
    * read/write from hardware devices
* literal(value expressed as itself like number 25 or string hello world) or constant values
* addressing mode - define how cpu can identify operand (calculate effective memory address of operand, cause instruction - just bits) in each instruction
  operand can be located in main memory or register. if operand in main memory, then instruction provides location of memory unit.
  so different methods to specify memory address knows as addressing mode.

### Compiler Design
bytecode - kind of IR (intermediate representation) for JIT compilers.
There are 2 main compilers:
* gcc (GNU Compiler Collection) - compiler written mostly in C consists of 2 parts
    * frontend - parsing part, read source code from programming language (gcc for C, g++ for C++, gccgo for Go, gcj for Java)
      and transform it into AST (abstract syntax tree). Compiler optimization and static code analysis applied on this stage.
      and finally turn the tree into IR called RTL (register transfer language) - intermediate language independent on the cpu architecture
    * backend - generate machine code for specific cpu architecture (like RISC or VLIW) from RTL, also include link-time optimization.
* llvm - originally Low Level Virtual Machine, yet this name was removed, cause now it's umbrella project for compilers to many languages
  written in c++, set of tech which can be used to develop frontend for any programming language and backend for any cpu architecture.
  it can accept RTL from gcc, do optimization and generate machine code
  it can generate static machine code or use JIT (just-in-time) similar to java
  Java compilers:
* gcj (not maintained since 2017) can compile java source code to either machine code of JVM bytecode
* llvm java frontend - would translate java source code into bytecode
* javac (written in java) is part of JDK, and transform java source code into bytecode    
  Let's take simple java example and see it's bytecode and machine code
```java
public class App{
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println(c);
    }
}
```
* Generate bytecode
```
cd src/main/java
# generate bytecode - App.class file
javac com/java/test/App.java
# view bytecode
javap -c com/java/test/App.class
# download java disassembler
sudo apt-get install libhsdis0-fcml -y
# run java and print machine code (full list of commands to investigate code https://wiki.openjdk.java.net/display/HotSpot/PrintAssembly)
# by default AT&T assembler syntax is used, we add options to print intel assembler
java -server -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:PrintAssemblyOptions=intel com.java.test.App
```
`javap` - java disassemble tool, where p stands for print, so `javap => java print`
You can view bytecode with intellij plugin `jclasslib bytecode viewer` without using `javac` compiler. just open file and go to `view => Show Bytecode with Jclasslib`
Don't confuse 2 types of compilers:
* JIT (just-in-time) - compile only those part of code that are executing, machine files stored in memory, if some code is never executed during jvm life, then it would never be compiled into machine code also in such approach compile can see how performance is going and optimize some part on runtime. Since java using JIT, there is no machine code files, it compiles it during execution moreover JIT interpret code first time it needs it, and only if this code is revoked several time, compile it into machine code. `-XX:CompileThreshold= (default is 10000) `
* AOT (ahead-of-time) - precompile everything into machine code. machine code files stored in the disk
  hotspot - compiler of jvm:
* OSR (On Stack Replacement) - switch execution during runtime from interpreted to compiled, useful when hotspot identify function as hot
  while it was running. if function use loop - such optimization may be useful. when it occur, jvm is paused and stack frame is replaced
  by compiled frame. By default, each function is interpreted until certain point when it became hot and then it compiled to machine code.
* biased locking - optimization by jvm, when thread release the lock, jvm keeps lock, in case thread would reacquire the lock, so it can happen very fast,  if different thread try to acquire lock, then bias should be removed
* deoptimization - when compiled code may not be called next time, and again unoptimized interpreted code may run
```java
public int getValue(int i){
    if (i==50_000){
        System.out.println(i);
    }
    return i/2;
}
```
compiler may think that this `if` condition may never happen and compile without it, but at some point we may come to this, so hotspot would deoptimize, basically remove compiled code and start interpreting code. you can see it by enable `-XX:+TraceDeoptimization`
x86 assemble - family of backward-compatible languages based on intel8088 cpu from 1972, based on short commands for register names. Many compilers produce assemble as intermediate language before turning it into machine code
There are 2 main syntax types:
* AT&T - dominant in unix, since it was created by AT&T Bells lab. used by GAS, yet this assembler supports also intel syntax.
* intel - common in DOS/Windows used by NASM/MASM/FASM/TASM
  There are some syntax diff
```
# set 5 to eax register
mov eax, 5      # intel
movl $5, %eax   # at&t
```
There are 2 modes how you can run java (hotspot optimize execution based ont the mode):
* `java -client` - less time to analyze/compile code and less optimization
* `java -server` - hotspot try to optimize code for OS peak loads

### Java Memory Model
CPU provides 2 types of memory model:
* strong memory model - all processors see exactly the same value for all memory location
* weak memory model - special cpu instruction called memory barriers, needed to flush/invalidate cache and see main memory values. Recent trend in cpu design favor weak model, cause it allows greater scalability between multiple cores.
  JMM - describes how threads share memory. This make sense for multithreading programming. If you are running single thread, everything is straightforward. Problems arise when multiple threads interact with each other:
* how memory is shared between multiple threads
    * each thread runs in separate cpu which has its own cache - copy of memory
    * so if one thread change value, it's changed in cpu cache, that means memory & second cpu cache has obsolete value
    * cpu cache & memory use cache coherence protocols to replicate changes between cache & memory
* order of execution:
    * compiler may re-order execution of code as part of optimization
      thread1 => `x=1;y=2;` If thread2 reads `y` and it's value is 2, x can still be 0, cause compiler re-order lines of code
* within thread `as-if-serial` semantics should be observed
  compiler may introduce any useful code re-organization as long as within single thread code would work as it was written
  Cache Coherence:
* cpu algo (most popular algo - MESI) that ensures that cache always read the most recent data from cache
* there are 4 states cache line in MESI protocol:
    * invalid - cache line is obsolete, you can't read from it
    * shared - cpu can read, but can't write into it
    * exclusive - cpu can write, once any cpu moved cache line into this state, other cpu mark their lines as invalid
    * modified - once we write data into cache line, it state changes from exclusive to modified.
      Once cpu wants to write data into it cache, it marks cache line as exclusive, wait until all other cpu mark their cache line into invalid, and only then modify its own cache line - as you see cache inconsistency is impossible.
      Take a look at following example:
```java
int x = 1;
int y = 2;
int z = x + y;
```
compiler may change order of line 1 & 2 as it want or run in parallel, but both must be executed before line 3.
Don't confuse:
* parallel code running in multiple threads - multithreading programming
* parallel execution of instructions inside single thread - can be used by cpu inside single cpu to speed up (when java compiler re-organize code, it may do so to run some lines non-dependent in parallel)
  JVM:
* each thread has its own stack where local variables stored:
    * primitive types (byte/short/int/long/boolean) - variable itself stored in the stack
    * complex types - reference to object stored in stack, object itself stored in heap
* heap - contains all objects created by java app
  On hardware we don't have stack/heap, so variables from stack/heap stored in memory, and can be copied into cache
  Rules:
* if 2 or more thread reading an object, until you use `volatile` or `synchronized` there is no guarantee that changes by one thread would be visible to others. This make sense, cause one thread may change value in his cache, but not yet flush it to memory. So in memory and other thread's cache old value reside. `volatile` keyword make sure that cpu cache flush changes to memory immediately after value changed, and all other threads always read from memory
* if 2 or more thread writing to object, even if you use `volatile` we may have condition where 2 threads will flush some results without coordinating with each other. if 2 threads increment value by 1, then value=3, but since each will flush its own copy, final value in memory would be 2
  So to summarize you can say:
* `volatile` - single write + multiple reads
    * happens before - also it prevent code re-ordering
    * all local variables declared before volatile can be re-ordered but all would be executed before volatile (so they can't be reordered and put after volatile) and would be flushed to main memory too
    * so if you have 2 fields and the order should be preserved, only 1 should be volatile. for other variables order would be preserved
    * writes - all values before volatile flushed to memory, reads - once volatile is read, all values after are read from memory
    * overuse of `volatile` - forbid many useful compiler optimization, so your code is slower
* `synchronized`  - multiple writes + multiple reads
  JNI (java native interface) - also prevent code optimization, cause JVM can't read inside, so it assumes the worst case and don't do any optimization. so don't overuse native methods cause it again slow down performance
  Memory barrier or memory fence - special instruction that requires CPU or compiler to enforce ordering on memory operations before & after barrier. Since modern compiler optimize the code, it may result in out-of-order-execution, and it's fine in single-threaded apps, it can be a problem in multithreaded apps, so such barrier prohibit optimization for memory operations.
  There are 4 types of memory barrier:
* LoadLoad - all loads before barrier, happens before loads after barrier
* LoadStore - all loads before barrier, happens before stores after barrier
* StoreStore - all stores before barrier, happens before stores after barrier
* StoreLoad - all loads before barrier, happens before stores after barrier
  Memory basics:
  proc can only access byte, so there is no way to read/write single bit, only whole byte, 8 bit, can be read at a time, that's why although boolean can be stored in single bit `true/false` - it's size still a byte in modern pc. So byte - the smallest addressable unit in computer - also called memory location. each memory location store either binary data or decimal data. Memory address - fixed-length unsigned integer
  Don't confuse:
* physical address - real memory address unit represented as integer. system software or OS request cpu to direct hardware device (memory controller) to use memory bus to get content of single memory unit (8 bits) to access it's content
* logical address - software create virtual memory space in which running program is read/write data for each running process. then MMU create mapping between logical and physical memory, so your program doesn't care to work with main memory. Your program works with virtual memory just like with main memory, and in background OS provides mapping between logical and physical memory. We need this abstraction cause otherwise different programs will write directly into physical memory effectively overwriting each other and constantly getting `memory corrupted` error.
  VM (virtual memory) guarantee:
* one program can't read memory data from another program, otherwise program could hack each-other and cause trouble
* more than one virtual address can refer to same physical address
* virtual memory space can be larger than physical one, by using VM paging - also called swapping
  MMU (memory management unit):
* called also PMMU (paged memory management unit) - cause works based on pages
* perform transition of virtual memory addresses into physical addresses
* divides virtual memory into pages each is power 2 (usually in KB)
* paging - the process to write data from physical memory into disk, so RAM acts as cache to main memory
  if you work with c/c++ and use pointers then 2 cases are possible:
* if you run your program in OS like windows/linux - for sure you are using virtual memory address space
* if you run your program without OS or you are writing OS kernel - then you would access physical memory directly
  There are 2 types of memory address resolution:
1. byte-addressable - each byte has its own address. Data larger than byte stored in sequence of consecutive addresses. Most modern pc are byte-addressable. yet there are many example of cpu architecture that is word-addressable. This is due to historical reason, since computer works mostly with text and single byte should store single character, since back then ASCII was the main format for char encoding, 8bit was enough to store single char, so we have 1 byte = 8 bit. Also for cpu it's simpler to work with byte then word - imagine you need to change symbol:
* byte - you just read it and modify
* word - cpu reads whole word into register, then do iteration find desired symbol and modify it - as you see algo is much complex here
2. word-addressable - minimal memory address size is processor word. CPU word can be of size 16/24/32/64 bit, has its own memory address. For example for 32bit cpu - each 32 bits or 4 bytes would have single address. For 64 - each 64 bits or 8 bytes would have separate address. There were a few decimal-addressable machines, but they are not used nowadays
   Don't confuse:
* address size - size of memory unit, mostly 8 bits in byte-addressable system
* word size - feature of computer architecture, how many bits cpu can process at one time. this also denote the max number of address space cpu can access. so for 32bit architecture - 2**32 bytes or 4gb can be accessed - that's why for this architecture max 4GB RAM is supported. that also means that 32bit architecture - can read/write 4 bytes at once, and 64 - 8 byte at once, yet some earlier 8bit could access 16bit memory and 16bit architecture - 20bit memory via memory segmentation.

### GC and Weak References
GC - happens, when no links point to the object. It happens by java in background process, but can be forced by using `System.gc()`. Pay attention that this method ask java to run GC, but not ensures that it would actually run.
GC compaction - moving all objects into beginning of memory for removing dead objects more quickly, so dead objects removed, alive objects stored contiguously in RAM (GC using mark-compact algorithm for this). This is similar to defragmentation of HDD in modern PC.
```java
public class App {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("run when jvm exits");
        }));
        My my = new My();
        my = null;
        System.gc();
        sleep(1);
        System.out.println("done");
    }
    public static void sleep(long sec){
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            System.out.println("ERR: " + ex);
        }
    }
}
class My{
    public My(){
        System.out.println("object of type My has been created");
    }
    protected void finalize() throws Throwable {
        System.out.println("object of type My has been garbage-collected");
    }
}
```
```
object of type My has been created
object of type My has been garbage-collected
done
run when jvm exits
```
`finalize` - became deprecated since Java9. You should use `Cleaner` instead.
```java
import java.lang.ref.Cleaner;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {
    public static void main(String[] args) {
        Cleaner cleaner = Cleaner.create();
        My my = new My();
        AtomicBoolean flag = new AtomicBoolean();
        cleaner.register(my, ()->{
            System.out.println("object of type My has been garbage-collected");
            flag.set(true);
        });
        my = null;
        // some memory-intensive allocation
        for (int i = 1; i < 1_000; i++) {
            int[] arr = new int[10_000_000];
            if (flag.get()) {
                System.out.println("breaking at step: " + i);
                break;
            }
        }
        System.out.println("done");
    }
}
class My{
    public My(){
        System.out.println("object of type My has been created");
    }
}
```
```
object of type My has been created
object of type My has been garbage-collected
breaking at step: 13
done
```
Java has concept of strong/weak/soft/phantom reference:
* strong - normal object `Object obj = new Object();`. Once you set it to null, any call on such object would cause `NullPointerException`
* weak - becomes null after calling `System.gc()`. Has 2 constructor, 1 can take second param as `ReferenceQueue`
* soft - still holds object after GC called, it would remove it only in urgent need of memory (like risk of `OutOfMemoryError`). Has 2 constructor, 1 can take second param as `ReferenceQueue`
* phantom (always null) - reference to an object that may be already garbage collected. An object is phantom reachable if it is neither strongly, softly, nor weakly reachable, it has been finalized, and some phantom reference refers to it. Have 1 constructor. You pass queue (thread safe `ReferenceQueue`), which store garbage-collected objects, you can use it to poll such objects. This is useful if you don't want to use `finalize`, wih such a reference, you can poll the queue and if it contains object - that means such object was garbage collected, you can add custom finalizing logic. Because this reference is always null, there is only 1 constructor with the queue, because it's the only way to know if your original object was garbage-collected. An obvious question arises – if we cannot access an object from phantom reference, how one can take any cleanup/”finalization” action for it? This is done by a simple workaround – we do not need a whole object to cleanup resource, we just need some representation (handle) of the resource it owns. Thus, altogether with the phantom reference itself we can store some necessary data, taken from an object at the time of phantom reference creation, which will be used afterward. Even if an object will be already garbage collected, our side data will allow us to cleanup resources of our interest.
```java
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class App {
    public static void main(String[] args) {
        WeakReference<Object> weakReference = new WeakReference<>(new Object());
        SoftReference<Object> softReference = new SoftReference<>(new Object());
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(new Object(), queue);
        System.out.println("weakReference => " + weakReference.get());
        System.out.println("softReference => " + softReference.get());
        System.out.println("phantomReference => " + phantomReference.get() + ", queue => " + queue.poll());
        System.gc();
        System.out.println("weakReference => " + weakReference.get());
        System.out.println("softReference => " + softReference.get());
        System.out.println("phantomReference => " + phantomReference.get() + ", queue => " + queue.poll());
    }
}
```
```
weakReference => java.lang.Object@1b28cdfa
softReference => java.lang.Object@eed1f14
phantomReference => null, queue => null
weakReference => null
softReference => java.lang.Object@eed1f14
phantomReference => null, queue => java.lang.ref.PhantomReference@6acbcfc0
```
You can use `WeakHashMap` if you want your keys to be garbage collected after their references has been removed. So when key is garbage-collected, it removed from hashmap automatically.
Use it if you want im-memory cache, but want objects to be removed from cache, once they are not used (once they have been garbage collected)
```java
import java.util.Map;
import java.util.WeakHashMap;

public class App {
    public static void main(String[] args){
        Map<Object, Integer> map = new WeakHashMap<>();
        Object obj = new Object();
        map.put(obj, 1);
        obj = null;
        System.gc();
        // since it's not guarantee that garbage collector would be called immediately, we would iterate for some time
        for(int i = 0; i < 1_000_000; i++){
            if(map.isEmpty()){
                System.out.println("done => " + i);
                break;
            }
        }
    }
}
```
```
done => 185
```
Object Resurrection - a process where you "resurrect" java object that is chosen for garbage collection. Java has two different mechanisms for reacting to the deallocation of an object. The older mechanism, using `finalize`, runs a particular method just before the object is deallocated. The new mechanism, using `PhantomReference`, instead allows you to run a particular method just after the object is deallocated. The idea is before object is garbage collected, JVM would run `finalize` where we would have access to `this` and can reassign this object to some variable, and know it's accessible once again, and can't be garbage collected. But this is not the best approach, so JVM is deprecating `finalize` method, and instead encourage to use `PhantomReference`. Here object already is removed by GC, but you merely notified about it, so you can do some clean up, but not able to actually "resurrect" the object.

### Garbage collector
You can get current default GC by running this command: `java -XX:+PrintCommandLineFlags -version` - this would print default JVM settings, including default GC that would be running with your JVM.
in JLS there is no info about garbage collection, so it totally depends upon VM implementation
Viewing GC logs (using GCViewer tool):
* you can add following line to VM arguments: `-verbose:gc -Xlog:gc:gc.log -Xlog:gc*::time -Xlog:safepoint`
* `-XX:+PrintGCDetails` - old way, before java9 to print GC logs
* `-verbose:gc` - display verbose logs (by default into stdout)
* `-Xlog:gc:gc.log` - send logs to file where `gc.log` - filename, you can specify other path to file
* `-Xlog:gc*::time` - add timestamp to gc logs
* `-Xlog:safepoint` - as combination of `-XX:+PrintGCApplicationConcurrentTime` and `XX:+PrintGCApplicationStoppedTime`
* you can use https://github.com/chewiebug/GCViewer tools to visualize GC `java -jar gcviewer-1.36.jar`
* you can use this tool https://visualvm.github.io/features.html
  You can run app without GC by adding `-XX:+UseEpsilonGC` - this would switch off GC completely, but app may fail with OutOfMemoryException
  GC throughput:
* the percentage of app running vs. gc running (for example 98% GC throughput means that app code running 98% of time and GC running 2% of total time)
* to calculate such throughput you need determine the time spent in garbage collection versus the time spent running the application
* You should use `jstat` utility to calculate throughput
* run `jstat -gcutil -t <jvm-pid> 1000 1` and get `GCT` column
* use this simple formula `1 - (GCT / uptime)` and get your percentage value
  Zero GC - practice where we write code in such a way, that we don't generate objects in the heap, that would be destroyed. There are multiple ways:
* use off-heap memory
* use object pooling - where you pre-allocate required objects on the start and then just reuse them
  JVM:
* heap - stores all objects, size increases/decreases during execution (you can set `-Xms` - initial size, `-Xmx` - max size):
    * YoungGen - young generation - stores newly allocated objects. Contain 3 parts:
        * eden - all newly-created objects goes here. When eden is full `minor GC` runs, and all survivor objects moved into one survivor space. it also checks survivor space and moves all survived objects into one space, so one is always free. Objects that survived for several times moved into OldGen
        * survivor memory space 1
        * survivor memory space 2
    * OldGen - old generation - contains old objects that survived after several rounds of `minor GC`. When OldGen is full, `major GC` is run to clean up - this take longer time
* non-heap - contains PermGen (Permanent Generation replaced with MetaSpace since java8 by [JEP-122](https://openjdk.org/jeps/122))
    * stores fields & methods names, code for methods, constants
    * static variables - only variables, not objects itself (if static variable store object, this object would reside in the heap, and only reference value would be stored in PermGen)
    * size can be set with ` -XX:PermSize` & `-XX:MaxPermSize`
* cache - stored compiled code
* stack - unique per thread, stored local variables and code execution
  GC (garbage collections) - goes through `heap` and destroy all unreferenced objects. it runs as `daemon thread`. Since simple checking of all objects one-by-one is not effective, several algos exist to run GC. Mark & Sweep model - default implementation in java GC:
* mark - identify & mark all object references starting from GC root, the rest is garbage
    * GC root - local/static variables, active threads
    * before destroying object, GC called `finalize` method exactly once
* sweep - search the heap and find all unoccupied space between objects for future object allocation
  There are several types of GC:
* serial - use single thread
* parallel (we can specify number of threads and max pause time) - use multiple threads
* low pause (like CMS) - use multiple threads and initiate `stop the world` in 2 cases:
    * initial marking of gc roots
    * if app changed the state of the heap, while gc was running
* G1 - use multiple threads scan heap by dividing in into many regions and scan regions with most garbage first. yet do STW to run compact. so it concurrent mark + STW compact.
* Z (java11 - experimental for linux only, java14 - ZGC for linux/windows):
    * partition the heap like G1, yet heap chunks can have different size
    * stop the world - no more than 10ms
    * run in java prior to java15 `-XX:+UnlockExperimentalVMOptions -XX:+UseZGC`
      Z & shenandoah insert load barrier into the code if you load object:
      zgc:
    * load barrier - piece of code that generated on the fly and inserted into system
    * check if object was relocated into new memory layout & update reference to this object (this relocation happens by application itself, so it may incur some latency)
      shenandoah
    * append `forward pointer` to each object, which store actual object address, by default it points to object itself
    * once change happen, forward pointer of old object would point to new object
    * insert load barrier into java code
    * this load barrier read forward pointer to determine real address of object
    * write barrier - if object already moved then write to new copy (this code also inserted into your code and may incur latency)
      Yet for CMS, G1, ZGC we still has small pauses to:
* collect root objects
* take snapshot
  This needs to be done atomically, so nothing happens in between, that's why we have tiny STW    
  JVM support following gc types:
* these 2 operate on YoungGen:
    * `-XX:+UseSerialGC` - standard serial mark & sweep algo
    * `-XX:+UseParallelGC` - parallel version of mark & sweep for minor GC (so only for YoungGen)
* these 2 operate on OldGen (it will stop all threads and run gc):
    * `-XX:+UseParallelOldGC` - parallel version of mark & sweep but for both minor/major gc
    * `-XX:+UseConcMarkSweepGC` (removed in java14) (CMS - concurrent mark and sweep) (default in java8) - minimize gc pause by doing major gc concurrently
      Card table - map with reference from old gen into youngGen. The reason since most objects die young, so minor GC only do GC for YoungGen. But how can we know if some oldGen has reference into youngGen. So for this we use special map - card table. One downside of CMS is that it doesn't run compaction. So use if you don't need compaction, or you are fine to run once in a while major GC, in this case CMS will rebuild objects and defragment your heap memory. So your memory would be like swiss cheese with many holes, if you combine it, looks like you have lots of memory, yet if you try to allocate big array you may fail with OutOfMemory error. So G1 in this regard is more advanced one. Cause after some time (days, weeks, months) you will hit a case when all your memory riddled.
      This one doesn't split heap into new/old-gen
* `-XX:+UseG1GC` (default since java9) - garbage first approach, divide heap into many equal-sized regions, first check regions with less live objects
    * string deduplication - g1  can find duplicate strings and point all of them into single object
* `-XX:+UseZGC` - has several steps:
    * short stop the world to mark all root references
    * concurrently traverse object graph to mark all referenced object
    * reference coloring
    * relocation - move objects into space from which unreferenced objects were removed
* `-XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC` - no-op gc, so it doesn't perform any gc, just run until heap is full, then terminate java app
  This can be useful if you have low-latency app with huge memory or for performance testing
* `-XX:+UseShenanodoahGC` -
  SATB - snapshot at the beginning, algo used to mark unreachable objects. We need this algo, cause we run marking at the same time as app is running. So if we don't do this, while we run, app may change reference and we can accidentally remove used object. Example. A->B->C. If we start marking, we go to A, then B, but at the same time B is no longer point to C, A is point to C now. But since we already passed A, we won't know this. So it's better to make snapshot of object graph at the beginning and use it for marking. When we run concurrent compact - we need to move object into new memory space. But since we have multiple threads read/write into this object to avoid situation where 2 threads write into 2 different copies
  we have read/write barrier - where once we create new copy we put pointer into first, and all links that read/write go to new copy through the pointer
  Don't confuse:
* serial GC - use one thread to run GC
* parallel GC - use multiple threads to run GC
  Yet both of them cause `stop-the-world` pause to run GC, parallel pause would be a bit shorter
* concurrent GC - run at the same time as your app running, so don't cause `stop the world`
  So you can be concurrent & parallel at the same time. or concurrent serial - if it uses single thread
  Pros to know how gc works - you can better handle:
* memory leaks - if objects keep referenced, although you don't need them in code, GC can't delete, so your heap would grow until you get `OutOfMemoryError`
* constant `stop the world` - gc stop all app thread to run itself, so if you have low latency app. constant stops can have performance issue, this is big problem with memory leak, cause if memory leak occur you have less memory, and gc runs
* cpu usage - constant `stop the world` cause a lot of cpu consumption
  gc tuning:
* adjust heap size
* reduce rate of object creation - use object pooling instead
* create collections with predefined size - most collections array based and resize can take some time + gc need take care of older array
* use streams instead of copy into memory byte arrays
  Don't confuse (permGen was replaced by MetaSpace since java8):
  PermGen:
* special heap space separated from the main memory heap (yet it was part of heap before java8)
* contains data about bytecode, names, and JIT information
* default - 82MB, but you can customize with `-XX:PermSize/-XX:MaxPermSize`
* removed from JDK 8
  Metaspace:
* replaced the older PermGen memory space starting form JDK 8
* grows automatically by default
* GC triggers cleaning of dead classes, once class metadata usage reaches max metaspace size. Compaction - memory defragmentation, when you arbitrary move objects into available space (space from where unreferenced objects where removed)
  this quite complex and done by copying collector and require gc to update address, cause after moving your object would reside in new address, yet it helps to utilize memory more efficient
  Conclusion: there is no universal GC, you should choose:
* low pause, large overhead - shenandoah
* average pause, average overhead - G1/CMS
* long pause, low overhead - parallel GC
  Overhead - tricks that help to decrease pause, need to be taken care by code like SATB, read/write barriers and so on
  Memory leak - big issue that needs to be resolved before we start gc tuning:
  We can use following code to test memory leak & gc
```java
public class App{
    public static void main(String[] args) throws InterruptedException {
        System.out.println("step 1");
        int n = 100_000;
        for(int i = 0; i < n; i++){
            int[] arr = new int[n];
        }
        System.out.println("step 2");
        Thread.sleep(5000);
        System.out.println("step 3");
    }
}
```
GC logging is mostly fast because it happens during "safe points" when GC is taking full stop, so you lose this time anyway, but only during this window gc logs are added. So we can conclude that gc logs doesn't add additional latency. There is still some latency added mostly because writing to disk (A big part of the GC log tasks is saving the data to a log file). "A big part of the GC log tasks is saving the data to a log file,” notes Deepak Sreedhar, principal software engineer at Azul. “The type of I/O used to store these files can impact the logging performance, not directly the application itself. So, some of the problems that may occur are not related to the performance of the GC logging but to the speed of the I/O. If the logs can’t be saved fast enough in real time, OpenJDK has the option to use asynchronous unified logging with Xlog:async.”
Although garbage collection logs can introduce minimal performance costs, the trade-off is usually worthwhile, as the logs are often invaluable when tuning garbage collection and diagnosing memory issues. Without GC logs enabled, you can lose insights into how the JVM manages memory dynamically at runtime. This information is very useful for monitoring the performance of a Java application, diagnosing memory leaks and tuning the JVM’s garbage collection configuration
Try to avoid:
* heavy code in finalize(), cause gc should wait until it executed
* resize heavy arrays - in this case gc compaction - will need to move such arrays in memory and it heavy operation. For such big arrays - try to pre-fetch them in the initialization

### JVM and GC tuning
You should tune your app in following way:
* first tune the app itself - badly designed app, which using wrong data structures should be refactored first
* tune JVM - once your app is refactored you can try tuning JVM itself
* tune GC - this is last step, that ideally should be avoided, but if you completed first 2 and still need improvements you can try to configure GC settings

JMV tuning:
* how to check: you can easily check any VM options, by running them with `java --version`. By doing this you can immediately see if this particular options is available for your JDK (all below example for Oracle JDK 21):
```shell
# if you run java with ZGC, everything would be fine
java -XX:+UseZGC --version

# if you run with removed GC, you would get error
java -XX:+UseConcMarkSweepGC --version

Unrecognized VM option 'UseConcMarkSweepGC'
Error: Could not create the Java Virtual Machine.
Error: A fatal exception has occurred. Program will exit.

# if you run with unsupported VM flag, you get different error (both Oracle JDK and OpenJDK 21 don't have shenandoah GC by default)
java -XX:+UseShenandoahGC --version

Error occurred during initialization of VM
Option -XX:+UseShenandoahGC not supported

# work for Adoptium JDK 21
java -XX:+UseShenandoahGC --version

openjdk 21.0.8 2025-07-15 LTS
OpenJDK Runtime Environment Temurin-21.0.8+9 (build 21.0.8+9-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.8+9 (build 21.0.8+9-LTS, mixed mode, sharing)
```
* memory - first thing you should do is to tune the memory usage:
    * `-Xms` for minHeapSize and `-Xmx` for maxHeapSize - usually we set it to the same value, so JVM won't spend CPU to adjust memory between min to max
    * `-XX:+AlwaysPreTouch` - by default when you allocate heap you just stand intention, actual memory is added to java process as pages. What may happen is that you set `-Xmx=32GB` but on 15GB your process would be killed. To preload all the RAM immediately you can use this command. Setting xms=xmx and AlwaysPreTouch=true would decrease latency by avoiding heap resizing.
    * `-XX:MetaspaceSize` and `-XX:MaxMetaspaceSize` - set size of meta space where all static data is stored
    * `-XX:NewSize` and `-XX:MaxNewSize` - initial and max size of young generation space
    * `-Xmn` - size of entire young gen + 2 survivor spaces
* Handling `java.lang.OutOfMemoryError`:
    * `-XX:+HeapDumpOnOutOfMemoryError` - dump the heap into the file when JVM crashed with this error
    * `-XX:HeapDumpPath=~/java_pid<pid>.hprof` - path to the file
    * `-XX:OnOutOfMemoryError="<cmd args>;<cmd args>"` - run user-defined commands on this error
    * `-XX:+UseGCOverheadLimit` - GC overhead limit is the feature of AdaptiveSizePolicy, which is used in Parallel GC and CMS, but not in G1
* GC - you can choose any of these GC:
    * `-XX:+UseSerialGC`
    * `-XX:+UseParallelGC` - default for OpenJDK 8
    * `-XX:+UseConcMarkSweepGC` (not available in Oracle JDK 21) - deprecated in Java 9 and removed in Java 14
    * `-XX:+UseG1GC` - best java21 general purpose, default GC for OpenJDK 11,17,21 
    * `-XX:+UseZGC` (from JDK 11) - best java21 ultra low latency
    * `-XX:+UseShenandoahGC` (from JDK 12, not available in Oracle JDK 21) - low-latency collector aiming for concurrent operation with minimal "stop-the-world" pauses. This GC is not supported in Oracle JDK, also not available in OpenJDK builds 17,21,24 that were downloaded from [official OpenJDK site](https://jdk.java.net//), but available in [Adoptium JDK 21](https://adoptium.net/temurin/releases/)
* GC logging:
  * since java9, all GC logs VM options where refactored into unified logging framework using `-Xlog`
* General:
  * `-XX:+IgnoreUnrecognizedVMOptions` - ignore unknown VM options. Useful when you use different JDK so you can use same command on different builds from different vendors. In this case your java command would run everywhere and just ignore unknown VM options, otherwise, it would fail to start.
  * `java -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:+PrintFlagsFinal -version` - print all available VM options
  * `-XX:+PerfDisableSharedMem` - disable writing to IO java internal metrics and expedite the system by this
* GC
  * G1:
    * `-XX:InitiatingHeapOccupancyPercent` (45%) - initial value until reached, G1 is moving objects from young gen to old
    * `-XX:-G1UseAdaptiveIHOP` (true) - if set to false, above param won't be changing dynamically
    * `-XX:MinHeapFreeRatio` (40) - the ratio of free memory that should be achieved
    * `-XX:MaxHeapFreeRatio` (70) - desired max ratio of free memory
    * `-XX:G1NewSizePercent` (5) - size of young gen
    * `-XX:G1MaxNewSizePercent` (60) - max size of young gen
    * `-XX:NewSize` and `-XX:MaxNewSize` - size in MB of young gen
    * `-XX:GCTimeRatio` (12) - ratio of time spent in GC compared to app running, 1 / (1 + GCTimeRatio) = ratio how much app is running
  * Z:
    * `-Xmx` - the most important part is max heap size
    * `-XX:ConcGCThreads` - number of threads GC is using (by default 6)

### Encoding
Don't confuse(endianess - the way we store bytes in memory):
* big endian - big end stored first, if you read left-to-right this make sense, it's also called forward
* little endian - store bytes right-to-left, reasoning - as you increase numbers, you need to add digits to the left, thus keep in mind that only bytes change order, bits inside single byte stay as they are. In big-endian you have to move all digits right. But with little-endian you just add digits. In the old days little-endian has advantage, cause you can read 32 bit variable as 8 or 16bit variable.
  Endianess:
* for cpu is not an issue anymore, cause developers use high-level programming language.
* for network transfer - can be a problem, but only if you use binary representation, since most protocol these days use XML/JSON, no issue with text protocols, yet for SBE, and any binary representation, endianess still an issue, so pay attention to it.
  Don't confuse:
* signed - those who store sign `+/-`. So for 4 bytes int, range would be -2B to +2B
* unsigned - only positive. So for 4 bytes integer => rage 0 to 4B. In java `char` is unsigned, yet byte/int/long - signed
  There are several character encoding:
* ASCII (American Standard Code for Information Interchange)
    * defines 128 characters (0-127)
    * first 32 - non-printable control characters like return or new line
    * nowadays, it's a subset of many other encoding
    * since byte - 8 bits, or 256, but ASCII needs only 128, there were a lot of different implementation to add another 128 bits - so we ended up with many computers that treat upper 128 bits differently and depend on your OS, upper bits could be resolved quite differently and this led to ANSI
* ANSI (American National Standards Institute)
    * general agreement what to do with upper 128 bits
    * first 128 bits would always be ASCII for all computers
    * different systems called code pages (for Hebrew - was one code page, for greek another)
    * 2 problems arise
        * it was impossible to have both Hebrew and Greek on the same computer
        * for asian alphabets (Japan/China) who had thousands of characters, these 128 upper bits were not enough - this was solved by DBCS (double byte character set) where some chars were 1 byte, but some 2, and you have to use windows AnsiNext/AnsiPrev to correctly handle encoding (you couldn't use s++/s--)
* Unicode
    * first attempt to create character set for all possible writing systems including artificial ones like Klingon (invented language from Star Trek)
    * no limit on bits, min size - 2 bytes, even for english
    * there is misconception that each char in Unicode is 16 bits (so totally 65536), yet it's wrong, in Unicode each letter maps to logical concept called `code point`, but can be stored in physical memory quite different
    * `code point` - magic number assigned to each letter in each alphabet by Unicode (english A => `U+0041`, number after `U+` is hex)
    * there is no real limits for `code point`, and Unicode goes far beyond 65536, so not each Unicode letter is 2 byte
    * standard still alive, in latest version-13 - there are 143k characters
    * 2 problems arise
        * for english which can use ascii - you still need 2 bytes for each character, so it's a lot of waste of memory
        * since big/little-endian store bytes in different order, different cpu architecture display Unicode differently - this problem was non-existent in ascii, cause it used single byte onl
    * so to resolve these 2 issues - encodings where created to answer main question how to store code points in memory?
      first encoding support high/low-endian was usc-2 - universal code character set -
      2 bytes called bom-byte (Unicode Byte Order Mark) on the begging on each string were added to determine high/low bytes
      `fe ff` - big endian, `ff fe` - little endian
      Yet developers complain about all these zeros so utf-8 was born
* UTF-8 (Unicode Transformation Format 8-bit)
    * each char in 0-127 stored as 1 byte, but from 128 2,3 and up to 6 bytes were used to store char
    * side effect is that english in UTF-8 is looks exact as in ASCII (each char encoded with 1 byte only, but in Unicode each english char would be encoded with 2 bytes)
    * so `hello => U+0048 U+0065 U+006C U+006C U+006F => 48 65 6C 6C 6F`
    * physical memory - we have the following rule:
      1 byte  -> 0xxxxxxx (size 7 bit) - single byte - store it just as byte
      2 bytes -> 110xxxxx 10xxxxx => (size 8-11 bit), so 110_10 - is a mark of 2 bytes, others used to store chars
      3 bytes -> 1110xxxx 10xxxxx 10xxxxx => (size 12-16 bit), so 1110_10_10  - mark of 3 bytes, others used to store chars
      4 bytes (size 17-21 bit) - so up to 2**21=2m chars
      ... and we can go all the way up to 6 bytes
      6 bytes -> 1111110x 10xxxxx 10xxxxx 10xxxxx 10xxxxx 10xxxxx (size 30-31 bit)
    * this also resolve endian problem;
      big endian - read first few bits and check; 0 - 1 byte, 110-2bytes, 1110-3bytes, 11110-4bytes. so based on few bits we can easily determine if it 1/2/3/4 byte character
      little endian - if we read 0 - 1byte, 10-can be 2/3/4 byte, so go further until you meat a mask like 110/1110/11110
      Don't confuse:
* UTF-8 - use the least possible byte number: 1,2,3,4. Since it's uses 1 byte when it can - it's compatible with ASCII
* UTF-16 - use byte on order 2, like 2 or 4. Since it uses at minimum 2 bytes it's not compatible with ASCII.
  yet it faster than utf-8 which try to determine if it character size, so most programming languages like java using UTF-16, yet it take more memory to store it compare to utf-8
  since string is byte array, and java use UTF-16, each string symbol in java takes either 2 or 4 bytes
  in C strings are mutable and it store end of each string, but to get string length is heavy operation. in java string are fixed-lengths
  that's why java choose immutable strings, so each time you modify a string, new string is created
* UTF-32 - fixed size 4 bytes for each character. this is the fastest one, cause compare to utf-8/16 you don't need to guess char size.
  you just break your string into chunks of 4 bytes each and read it one-by-one. yet it takes 4 times more space then utf-8
  so numbers 8/16/32 - denote min size of single char. this means all 3 can store 4byte char
  Don't confuse:
* character set - list of characters where each char is mapped to numeric value called `code point`.
* encoding - how we encode/map characters into memory (how numeric values `code points` are mapped into bytes)
  so Unicode - is character set only, UCS-2 was encoding designed to it, ASCII is both character set and encoding, and UTF-8/UTF-16/UTF-32 encoding
  Java example how to convert string to bits
```java
public class App{
    public static void main(String[] args) {
        char ch = 'A';
        byte b = (byte) ch;
        System.out.println("char    => " + ch);
        System.out.println("numeric => " + b);
        System.out.println("binary  => " + Integer.toBinaryString(b));
        System.out.println("stringToBinary  => " + stringToBinary("hello"));
    }
    private static String stringToBinary(String str){
        byte[] arr = str.getBytes();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++){
            String binary = String.format("%8s", Integer.toBinaryString(arr[i])).replace(' ', '0');
            sb.append(binary).append(" ");
        }
        return sb.toString();
    }
}
```
```
char    => A
numeric => 65
binary  => 1000001
stringToBinary  => 01101000 01100101 01101100 01101100 01101111
```
String in java using `UTF-16` encoding, yet when you work with `byte[]` you can choose encoding
```java
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class App{
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for(int i=1000;i<1010;i++){
            sb.append((char) i);
        }
        String str = sb.toString();
        byte[] arr = str.getBytes();
        System.out.println("str => " + str);
        System.out.println("bytes => " + Arrays.toString(arr));
        System.out.println("str => " + new String(arr, StandardCharsets.UTF_8));
        System.out.println("str => " + new String(arr, StandardCharsets.US_ASCII));
    }
}
```
```
str => ϨϩϪϫϬϭϮϯϰϱ
bytes => [-49, -88, -49, -87, -49, -86, -49, -85, -49, -84, -49, -83, -49, -82, -49, -81, -49, -80, -49, -79]
str => ϨϩϪϫϬϭϮϯϰϱ
str => ��������������������
```

### Unsafe, VarHandle, MethodHandle
`sun.misc` - special package for 2 low-level classes (you shouldn't use these 2 classes in your code, otherwise your code would be too OS-dependant):
* Unsafe - low-level logic that designed to be used by the core Java library developers. You can't even instantiate it normally (since constructor is private we have to use reflection to get instance).
* Signal - low level system signals handling
  Fatal error - technically it's impossible to get fatal error with java, cause it designed in such a way to handle this. Yet if you use `Unsafe` directly you can get it
```java
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class App{
    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        // put into wrong address
        unsafe.putAddress(1, 10);
    }

    public static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
```
```
# A fatal error has been detected by the Java Runtime Environment:
#
#  SIGSEGV (0xb) at pc=0x00007fa6425266f3, pid=2136, tid=2138
#
# JRE version: OpenJDK Runtime Environment (11.0.9.1+1) (build 11.0.9.1+1-Ubuntu-0ubuntu1.18.04)
# Java VM: OpenJDK 64-Bit Server VM (11.0.9.1+1-Ubuntu-0ubuntu1.18.04, mixed mode, sharing, tiered, compressed oops, g1 gc, linux-amd64)
# Problematic frame:
# V  [libjvm.so+0xea86f3]
#
# Core dump will be written. Default location: Core dumps may be processed with "/usr/share/apport/apport %p %s %c %d %P %E" (or dumping to /home/diman/projects/my/ocpjp11/core.2136)

Process finished with exit code 134 (interrupted by signal 6: SIGABRT)
```
There are several examples where you can use `Unsafe`:
* allocateInstance - allocate memory but doesn't call constructor
* change private fields (by the way reflection use `Unsafe` under-the-hood). But if you use `Unsafe` for this you can modify any object, even if you don't have direct reference to it. For example, you have Object A1 with reference and next to it object A2 in memory. So you can use `unsafe.putInt(obj, 32 + unsafe.objectFieldOffset(secretField), 123);` - this would modify next object in memory (32 - size of object in memory)
* throw any exception (java compiler doesn't validate Unsafe same way as other code, so you can throw any checked exception)
* use off-heap memory (this memory is not managed by java, so GC is not called to clean it, so you just clean it manually). Again it's better to use `ByteBuffer.allocate(100)` which would use `HeapByteBuffer` under-the-hood, which in turn use `Unsafe` to handle memory
* create array bigger than Integer.MAX_VALUE:
    * in java by default you can create array with size up to `Integer.MAX_VALUE` (2**31-1), but with `Unsafe` you can create array bigger than this
    * make sure you have enough RAM, otherwise you will get `OutOfMemoryError`
    * keep in  mind that this off-heap memory won't be garbage collected, so you have to clean it manually `unsafe.freeMemory(startIndex)`
    * directly allocated memory is not initialized with a certain value. In general, you will find garbage from old usages of this memory area such that you have to explicitly initialize your allocated memory if you require a default value `unsafe.setMemory(startIndex, size, (byte) 0)`. When you use off-heap to create array, java runtime takes care of initialization, but if you use `Unsafe.allocateMemory` you have to do it manually
* CAS (compare-and-swap, should be supported by CPU cause it's executed on CPU level) - all classes from `java.util.concurrent.atomic` like `AtomicInteger` using one of 3 `Unsafe` implementation of this algorithm:
    * compareAndSwapInt
    * compareAndSwapLong
    * compareAndSwapObject
* wait with `park/unpark` methods - similar to `Object.wait` but use native OS implementation
* create function sizeOf to get size of objects
* remove strings from memory
* treat variable as volatile without `volatile` keyword using `getXXXVolatile/putXXXVolatile`
* pause thread using `park/unpark` methods - similar to `Object.wait`, but use native OS implementation
  Basic example with class instantiation & throwing checked exception
```java
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class App{
    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = getUnsafe();

        Person p2 = (Person) unsafe.allocateInstance(Person.class);
        System.out.println(p2.getAge());

        unsafe.throwException(new Exception("oops"));
    }

    public static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

class Person{
    private int age;
    public Person(){
        age = 10;
    }
    public int getAge(){
        return age;
    }
}
```
```
0
Exception in thread "main" java.lang.Exception: oops
```
As you see class was created, but constructor not called. Below is example of off-heap byte array (again use `ByteBuffer.allocate` instead).
```java
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class App{
    public static void main(String[] args)  {
        try(OffHeapByteArray buffer = new OffHeapByteArray(100)){
            int position = 5;
            byte value = 10;
            buffer.set(position, value);
            System.out.println(buffer.get(position));
        }
    }
}

class OffHeapByteArray implements AutoCloseable{
    private static final Unsafe UNSAFE = getUnsafe();
    private final int capacity;
    private final long address;

    public OffHeapByteArray(int capacity){
        this.capacity = capacity;
        this.address = UNSAFE.allocateMemory(capacity);
    }

    public void set(int position, byte value) {
        UNSAFE.putByte(address + position, value);
    }

    public int get(int position) {
        return UNSAFE.getByte(address + position);
    }

    public int size(){
        return capacity;
    }

    private static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() {
        UNSAFE.freeMemory(address);
    }
}
```
You can create multi-threading locking using `AtomicInteger.compareAndSet` function, which return boolean and change value only if initial value is same as first argument. Here we use CAS to imitate locking, because CAS is faster than java thread locking:
```java
import java.util.concurrent.atomic.AtomicInteger;

public class App{
    public static void main(String[] args) {
        MyWorkerCache cache = new MyWorkerCache(4);
        cache.start();
    }
}

class MyWorker implements Runnable{
    private int threadId;
    private AtomicInteger sharedValue;
    public MyWorker(int threadId, AtomicInteger sharedValue){
        this.threadId = threadId;
        this.sharedValue = sharedValue;
    }

    @Override
    public void run() {
        System.out.println("Thread " + threadId + " acquiring lock...");
        acquireLock();
        System.out.println("Thread " + threadId + " running");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ex){
            System.out.println("ERROR: Thread " + threadId + ", ex=" + ex);
        }
        System.out.println("Thread " + threadId + " releasing lock...");
        releaseLock();
        System.out.println("Thread " + threadId + " released");
    }

    public void acquireLock(){
        while (!sharedValue.compareAndSet(0, threadId)){
            // spin CPU
        }
    }
    public void releaseLock(){
        while (!sharedValue.compareAndSet(threadId, 0)){
          // spin CPU
        }
    }
}

class MyWorkerCache{
    private int threadNumber;
    private MyWorker[] arr;
    private AtomicInteger shareNumber = new AtomicInteger();
    public MyWorkerCache(int threadNumber){
        this.threadNumber = threadNumber;
        build();
    }
    private void build(){
        arr = new MyWorker[threadNumber];
        for(int i = 0; i < threadNumber; i++){
            arr[i] = new MyWorker(i+1, shareNumber);
        }
    }
    public void start(){
        for(int i = 0; i < threadNumber; i++){
            new Thread(arr[i]).start();
        }
    }
}
```
```
Thread 3 acquiring lock...
Thread 3 running
Thread 4 acquiring lock...
Thread 1 acquiring lock...
Thread 2 acquiring lock...
Thread 3 releasing lock...
Thread 1 running
Thread 3 released
Thread 1 releasing lock...
Thread 1 released
Thread 2 running
Thread 2 releasing lock...
Thread 2 released
Thread 4 running
Thread 4 releasing lock...
Thread 4 released
```
As you see originally all 4 thread runs in parallel, but only 1 get into execution, all others are wait, then one-by-one each thread acquire lock execute, white others wait.

ABA problem:
* common problem for lock-free algo - when you use CAS you try to change value only if previous value hasn't change
* the edge case is that value is changed twice by other thread, but second change return value to original state - by this first thread see that value still holds original value, and proceed with CAS, but in reality this value is not old but new value after 2 changes.
* this problem is scenario based, because sometimes we don't care as long as A=A, but sometimes, it's important, cause if value was modified twice and returned to previous value, it's not the same as value wasn't modified at all
* Imagine a lock-free linked list where nodes are added or removed using CAS operations on pointers. If a node is removed and then a new node is added at the exact same memory address, a thread that had previously read the pointer to the old node might still believe it's referencing the correct, original node when it attempts a CAS operation
* To conclue this ABA problem is only applicable to kind-of linked list implementations, and in many cases for lock-free it's not a problem (for example balance update)
  How to resolve:
* use of GC - when GC run it clean links and modified object may have new address
* Tagged Pointers or double CAS - where we use 2 variables for CAS, the value itself + its version. In this case version would be increased on 2, and CAS would fail - 2 ways:
    * `AtomicStampedReference` - have a reference to object and a version, so we can atomically update 2 objects - both our value + int version
    * `AtomicMarkableReference` - have a reference to object and a boolean, so we can atomically update 2 objects - both our value + boolean flag
    * under-the-hood both using `VarHandle.compareAndSet` where they store object+int (or object+boolean) as pair, and just CAS single object (but this object is a pair of <V, int> or <V, boolean>)
```java
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class App {
  public static void main(String[] args) {
    int balance = 100;
    int version = 0;
    AtomicStampedReference<Integer> account = new AtomicStampedReference<>(balance, version);
    account.compareAndSet(balance, balance + 100, version, version + 1);

    boolean changed = false;
    AtomicMarkableReference<Integer> account2 = new AtomicMarkableReference<>(balance, changed);
    account2.compareAndSet(balance, balance + 100, changed, !changed);
  }
}
```

`ThreadLocal` - special class that allows you to access variable only by local thread. ThreadLocal is a reference to data within a given Thread, you can end up with classloading leaks when using ThreadLocals in application servers using thread pools. The result of following code is always 0, because values set in other threads would only be valid in those other threads
```java
public class App {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Integer> local = new ThreadLocal<>();
        local.set(0);
        for (int i = 1; i <= 10; i++) {
            final int v = i;
            new Thread(()->local.set(v)).start();
        }
        Thread.sleep(1000);
        System.out.println(local.get());
    }
}
```


MethodHandle (java 7) - typed and executable reference to underlying java class method/constructor/field. It's similar but faster than Reflection API, cause there is direct support in JVM.
```java
public class App {
  public static void main(String[] args) throws Throwable {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    MethodType methodType = MethodType.methodType(String.class, int.class);
    MethodHandle getName = lookup.findVirtual(Person.class, "getName", methodType);

    Person person = new Person();
    String name = (String) getName.invokeWithArguments(person, 30);
    System.out.println(name);

  }
}

class Person {
  public String getName(int age) {
    return "John Doe, " + age;
  }
}
```
```
John Doe, 30
```

`VarHandle` (java 9) - wrapper for a field to perform atomic operations on that field. It's very similar to `Unsafe`. And before to achieve this you have to use this class to atomically change values (look into `AtomicInteger` under-the-hood it uses `Unsafe` to atomically modify value), but now you can use `VarHandle` to achieve same results (look into `AtomicReference`, it uses `VarHandle` under-the-hood). You can achieve `volatile` features, even if field is not declared as volatile.
Below is example how before you have to use `Unsafe` and now you can use `VarHandle` for same operation. But before with `Unsafe` you have to use `offset` which is error-prone.
```java
public void lazySet(V newValue) {
    unsafe.putOrderedObject(this, valueOffset, newValue);
}

public boolean compareAndSet(V expect, V update) {
    return unsafe.compareAndSwapObject(this, valueOffset, expect, update);
}
```
Now you can achieve this with `VarHandle` and don't need `offset` anymore, which was error-prone
```java
private static final VarHandle VALUE;
static {
    try {
        MethodHandles.Lookup l = MethodHandles.lookup();
        VALUE = l.findVarHandle(AtomicReference.class, "value", Object.class);
    } catch (ReflectiveOperationException e) {
        throw new Error(e);
    }
}

public void lazySet(V newValue) {
  VALUE.setRelease(this, newValue);
}

public boolean compareAndSet(V expectedValue, V newValue) {
  return VALUE.compareAndSet(this, expectedValue, newValue);
}
```
You can also use `VarHandle` to change variable behavior as it was `volatile`. For example if you have non-volatile variable, you can wrap `VarHandle` around it and change it with  `getVolatile/setVolatile`, and variable would behave as it was volatile. But it would only behave as `volatile` if accessed through `VarHandle` wrapper, if you access it directly it would behave as normal variable, and no memory barriers would be enacted.
```java
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class App {
  public int x;
  private static final VarHandle VALUE;
  static {
    try {
      MethodHandles.Lookup l = MethodHandles.lookup();
      VALUE = l.findVarHandle(App.class, "x", int.class);
    } catch (ReflectiveOperationException e) {
      throw new Error(e);
    }
  }
  public static void main(String[] args) {
    App app = new App();

    VALUE.setVolatile(app, 10);
    int x = (int) VALUE.getVolatile(app);
    System.out.println(x);
  }
}
```

### Linked lists
Linked list disadvantages compared to array:
CPU cache does 2 things: cache frequently used memory & predict which memory would be used next. It uses simple algo - just get nearest memory. But in case of linked list - next element can be in completely different memory chunk.
`RandomAccess` - interface (don't confuse with concrete class `RandomAccessFile` to work with file) - that notify that our list is random access list. So `ArrayList` is implements it, yet `LinkedList` not implementing it, cause here you can't access 5th element, you need to iterate over whole list to get to desired position. So this interface is simply notify that list can be randomly accessed (all lists based on array should be randomly accessed).
There are 3 types of linked list (all of them are linear structure represented as chain of nodes, the difference how nodes are related to each other):
* Singly linked list:
    * each node store 2 fields: value + next node address
    * one-direction (only forward)
* Doubly linked list:
    * called just linked list, java implementation of `LinkedList` using doubly linked list under-the-hood
    * each node store 3 values: value + next node address + prev node address (here name of doubly). Since it store 3 fields, it takes more memory than singly linked list
    * bidirectional (both backward & forward)
    * remove is O(1) and 2 lines of code
```
public void remove(Node node){
    node.prev.next = node.next;
    node.next.prev = node.prev;
}
```
This only works if you know the Node, if you pass just value, you would still do O(n) to find proper Node, so in this case pure delete by value will still take O(n)
Skip list:
* store sorted list of elements in linked list
* we have singly linked list + every node point to second/fourth/8-th and so on. So search is basically same as binary search, we can just do O(log n)
```java
import lombok.Data;

public class App{
    public static void main(String[] args) {
        var list = new SinglyLinkedList();
        for(int i = 0; i < 3; i++){
            list.add(i);
        }
        System.out.println(list);
    }
}

class SinglyLinkedList{
    @Data
    private static class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }

    private int currentIndex;
    private Node firstNode;
    private Node currentNode;
    public int add(int value) {
        Node node = new Node(value);
        if (firstNode == null) {
            firstNode = node;
        } else {
            currentNode.next = node;
        }
        currentNode = node;
        return ++currentIndex;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";
        Node current = firstNode;
        while (current != null) {
            sb.append(delimiter).append(current.getValue());
            delimiter = ",";
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
```
```
[0,1,2]
```
We have method `List.set(index, value)` where you can set value for every index, for array-based lists (like `ArrayList`) it's easy, you just change index of underlying array, yet in `LinkedList`, you iterate whole list until you reach desired position, then you change it. So here complexity is O(n), yet in array-based lists it O(1).
```java
import java.util.LinkedList;
import java.util.List;

public class App{
    public static void main(String[] args) {
        List<String> list = new LinkedList<>(List.of("a","b","c"));
        System.out.println(list);
        list.set(2, "x");
        System.out.println(list);
    }
}
```
```
[a, b, c]
[a, b, x]
```

### Low latency logging
The whole idea is that we use some non-blocking queue, so the executing threads not blocked (waste time) for log writing, it just add log messages to some non-blocking queue, and then we have some logger thread, that actually does logging. You can implement it yourself with any lock-free multi-threading queue, but there are 2 solutions available:
* [chronicle logger](https://github.com/OpenHFT/Chronicle-Logger) - use chronicle queue under the hood
* [async log4j2](https://logging.apache.org/log4j/2.x/manual/async.html) - use lmax disruptor under the hood

### Low latency collections
We have the following collections in java:
* trove
* koloboke
* chronicle (build by including cool features from koloboke)
* agrona
* lmax disruptor
* aeron
  [Trove](https://bitbucket.org/trove4j/trove/src/master/) - one of the first low latency collections:
* started as primitive collections (in JDK you have to use wrappers)
* use open addressing for maps
    * in JDK for maps, we use linked list if 2 or more elements have same hashcode, since java8 we use balanced tree (red-black tree) to store collisions
    * closed addressing (used by JDK) - when you have hash collision you create list for single bucket, when you lookup you go through the list and compare keys
    * here we don't use any list, instead we always put elements into underlying array, in case of hash collision, we put key into another bucket, when we lookup, we found starting array slot, and then go down one-by-one (for linear) comparing keys, until we either found our key or empty spot
- here we use linear resolution where if hashcode already exist, we just put it into next field
* you can add your custom hashing strategy by implementing `TObjectHashingStrategy`
* currently supported by [palantir](https://github.com/palantir/trove)
* doesn't implement java interfaces like `List, Map` so you are stuck with trove concrete implementations
* doesn't support multi-threading, it's just single thread for primitives
* add to your project
```
<dependency>
  <groupId>trove</groupId>
  <artifactId>trove</artifactId>
  <version>1.0.2</version>
</dependency>
```
```java
import gnu.trove.TLongObjectHashMap;

public class App {
    public static void main(String[] args) {
        TLongObjectHashMap longToObjMap = new TLongObjectHashMap();
        longToObjMap.put(1, new Object());
        longToObjMap.put(2, new Object());
        System.out.println("longToObjMap => " + longToObjMap);
    }
}
```
```
longToObjMap => gnu.trove.TLongObjectHashMap@90d2a429
```
[Koloboke](https://github.com/leventov/Koloboke) - low latency library:
* written by a guy who worked with creator of chronicle (actually they both later worked on chronicle)
* support jdk interfaces like `List & Map`
* add to your pom.xml
```
<dependency>
  <groupId>com.koloboke</groupId>
  <artifactId>koloboke-impl-jdk8</artifactId>
  <version>1.0.0</version>
</dependency>
```
You can cast all map interfaces to java Map
```java
import com.koloboke.collect.map.LongObjMap;
import com.koloboke.collect.map.hash.HashLongObjMaps;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // use factory to create desired class
        LongObjMap<Object> map = HashLongObjMaps.getDefaultFactory().newMutableMap();
        map.put(1L, new Object());
        map.put(2L, new Object());
        System.out.println("class=" + map.getClass()+", map=" + map);
        // we can even cast to jdk Map
        Map<Long, Object> castMap = map;
    }
}
```
```
class=class com.koloboke.collect.impl.hash.MutableLHashSeparateKVLongObjMap, map={2=java.lang.Object@4cdf35a9, 1=java.lang.Object@4c98385c}
```
[Chronicle](https://github.com/OpenHFT):
* low latency persisted queue
* chronicle map extends jdk `ConcurrentMap` so you cast any such map to jdk `Map`
* for map use:
    * putReturnsNull(true) - this won't return prev value on put, so it would generate less garbage (cause old value needs to be retreived and returned as object)
    * use special values like `LongValue` instead of `Long`
    * keys & values must be serializable to be stored in file, otherwise nothing would be stored, you get `ClassCastException: class com.java.test.Person cannot be cast to class java.io.Serializable`
```java
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.RollCycles;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;

public class App {
    public static void main(String[] args) {
        SingleChronicleQueueBuilder builder = SingleChronicleQueueBuilder
                .single("chronicle-data")
                .rollCycle(RollCycles.FAST_DAILY);
        try (ChronicleQueue queue = builder.build()) {
            final ExcerptAppender writer = queue.acquireAppender();
            final ExcerptTailer reader = queue.createTailer();

            for (char i = 'a'; i <= 'z'; i++) {
                writer.writeText(Character.toString(i));
            }

            long oldIndex = 0;
            for (int i = 0; i < 5; i++) {
                if (i == 2) {
                    oldIndex = reader.index();
                }
                System.out.println("index=" + reader.index() + ", lastReadIndex=" + reader.lastReadIndex() + ", text=" + reader.readText());
            }
            System.out.println("moving to index=" + oldIndex);
            reader.moveToIndex(oldIndex);
            System.out.println("index=" + reader.index() + ", lastReadIndex=" + reader.lastReadIndex() + ", text=" + reader.readText());
        }
    }
}
```
```
index=0, lastReadIndex=0, text=a
index=82978768158721, lastReadIndex=82978768158720, text=b
index=82978768158722, lastReadIndex=82978768158721, text=c
index=82978768158723, lastReadIndex=82978768158722, text=d
index=82978768158724, lastReadIndex=82978768158723, text=e
moving to index=82978768158722
index=82978768158722, lastReadIndex=82978768158724, text=c
```
As you see queue is very similar to kafka, so you can:
* get index of each message & last read index
* read from specific offset for replaying
  You can easily combine queue with wire to save objects:
```java
import lombok.Data;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.RollCycles;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import net.openhft.chronicle.wire.Marshallable;

public class App {
    public static void main(String[] args) {
        SingleChronicleQueueBuilder builder = SingleChronicleQueueBuilder
                .single("chronicle-data")
                .rollCycle(RollCycles.FAST_DAILY);
        try (ChronicleQueue queue = builder.build()) {
            Person person = new Person();
            person.setName("Jack");
            person.setAge(30);
            // saving person
            queue.acquireAppender().writeDocument(person);
            Person saved = new Person();
            boolean readSuccess = queue.createTailer().readDocument(saved);
            System.out.println("readSuccess=" + readSuccess + ", saved=" + saved);
        }
    }
}

@Data
class Person implements Marshallable {
    private  String name;
    private int age;
}
```
```
readSuccess=true, saved=Person(name=Jack, age=30)
```
There are 2 types of map: in-memory & persisted to file
```java
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        ChronicleMapBuilder<Long, String> builder = ChronicleMap
                .of(Long.class, String.class)
                .entries(10_000)
                .averageValueSize(50);
        Map<Long, String> inMemoryMap = builder.create();
        inMemoryMap.put(1L, "hello");
        System.out.println("inMemoryMap => " + inMemoryMap);
        try {
            Map<Long, String> persistedMap = builder.createPersistedTo(new File("chronicle-data"));
            persistedMap.put(1L, "hello");
            System.out.println("persistedMap => " + persistedMap);
        } catch (IOException ex) {
            System.out.println("ERR => " + ex);
        }
    }
}
```
```
inMemoryMap => {1=hello}
persistedMap => {1=hello}
```
2 ways to store object
```java
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class App {
    public static void main(String[] args) {
        ChronicleMapBuilder<Long, Person> builder = ChronicleMap
                .of(Long.class, Person.class)
                .entries(100_000)
                .averageValueSize(20)
                .putReturnsNull(true);
        try (ChronicleMap<Long, Person> persistedMap =  builder.createPersistedTo(new File("chronicle-data"))){
            persistedMap.put(1L, new Person("jack", 30));
            System.out.println("persistedMap => " + persistedMap);
        } catch (IOException ex) {
            System.out.println("ERR => " + ex);
        }
    }
}

@RequiredArgsConstructor
@Getter
@ToString
class Person implements Serializable {
    private final String name;
    private final int age;
}
```
```
persistedMap => {1=Person(name=jack, age=30)}
```
Using [Chronicle-Values](https://github.com/OpenHFT/Chronicle-Values)
In this case we don't need to use `averageValueSize`, if we use we get `IllegalStateException: Size of interface com.java.test.Person instances is constant and statically known, shouldn't be specified via averageValueSize() or averageValue() methods`
```java
import net.openhft.chronicle.bytes.Byteable;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.core.values.LongValue;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import net.openhft.chronicle.values.MaxUtf8Length;
import net.openhft.chronicle.values.NotNull;
import net.openhft.chronicle.values.Values;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        ChronicleMapBuilder<LongValue, Person> builder = ChronicleMap
                .of(LongValue.class, Person.class)
                .entries(100_000)
                .putReturnsNull(true);
        try (ChronicleMap<LongValue, Person> persistedMap =  builder.createPersistedTo(new File("chronicle-data"))){
            LongValue key = Values.newHeapInstance(LongValue.class);
            key.setValue(1L);

            Person offHeapPerson = Values.newNativeReference(Person.class);
            final long personMaxSize = offHeapPerson.maxSize();
            offHeapPerson.bytesStore(Bytes.allocateDirect(personMaxSize), 0, personMaxSize);
            offHeapPerson.setName("jack");
            offHeapPerson.setAge(30);

            System.out.println("personMaxSize=" + personMaxSize + ", offHeapPerson=" + offHeapPerson);
            persistedMap.put(key, offHeapPerson);
            System.out.println("persistedMap => " + persistedMap);
        } catch (IOException ex) {
            System.out.println("ERR => " + ex);
        }
    }
}

interface Person extends Byteable {
    CharSequence getName();
    void setName(@NotNull @MaxUtf8Length(10) CharSequence name);

    int getAge();
    void setAge(int age);
}
```
```
personMaxSize=15, offHeapPerson=com.java.test.Person$$Native@bbcc4fca
persistedMap => {net.openhft.chronicle.core.values.LongValue$$Heap@f4242=com.java.test.Person$$Heap@bbcc4fca}
```
There is library chronicle-wire for serialization/deserialization:
* you can implement `readMarshallable/writeMarshallable` from `Marshallable` for custom read/write, by default all fields are serialized
* you can use different wires for serialization : `TextWire/BinaryWire/RawWire`, or implement your own by using `Wire`
* it can be used with chronicle maps for key/value serialization, same way as chronicle-values
```java
import lombok.Data;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.wire.BinaryWire;
import net.openhft.chronicle.wire.Marshallable;

public class App {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("jack");
        person.setAge(30);

        Bytes bytes = Bytes.elasticByteBuffer();
        person.writeMarshallable(new BinaryWire(bytes));
        System.out.println("person=" + person + ", bytes=" + bytes);

        Person deserialized = new Person();
        deserialized.readMarshallable(new BinaryWire(bytes));
        System.out.println("deserialized=" + deserialized);
    }
}

@Data
class Person implements Marshallable {
    private  String name;
    private int age;
}
```
```
person=Person(name=jack, age=30), bytes=ÄnameäjackÃage¡
deserialized=Person(name=jack, age=30)
```
[Agrona](https://github.com/real-logic/agrona) - set of data structures for low latency concurrent programming in java. Originally was part of aeron project, but later was moved into separate repository.
To work with it, add dependency to your `pom.xml`
```
<dependency>
  <groupId>org.agrona</groupId>
  <artifactId>agrona</artifactId>
  <version>1.8.0</version>
</dependency>
```
Below is a list of some common classes:
* IdleStrategy - interface to do nothing, common implementation `SleepingMillisIdleStrategy`, using `Thread.sleep` under the hood (yet there are other implementations as well)
```java
import org.agrona.concurrent.IdleStrategy;
import org.agrona.concurrent.SleepingMillisIdleStrategy;

public class App{
    public static void main(String[] args) {
        IdleStrategy idle = new SleepingMillisIdleStrategy(1000);
        System.out.println("start");
        idle.idle();
        System.out.println("end");
    }
}
```
* Queue (for producer/consumer):
    * `ArrayBlockingQueue` (implements `BlockingQueue`) - bounded queue (you have to pass how many elements it would contain), has blocking put/poll that block current thread until there is space in queue (for put) or there are new elements added for poll
      The downside is that this thread blocking can create contention. Once queue is full put would wait until some elements polled.
    * `LinkedBlockingQueue` (implements `BlockingQueue`) - can be both bounded (pass number into construct) - behave same as array blocking queue, and unbounded (don't pass anything into constructor) - in this case you can put as much as memory allow.
      if memory limited you would get `java.lang.OutOfMemoryError`. Also use blocking & create contention.
    * `ConcurrentLinkedQueue` (doesn't implement BlockingQueue, just Queue) - doesn't block thread, but using CAS algorithms to add new elements. But because it's lock-free it put/poll returns immediately if queue is full/empty.
      So if you want some blocking logic here you will have to implement it on your own `while(!queue.offer(val)){Thread.onSpinWait();}`. Since it unbounded it can also throw `java.lang.OutOfMemoryError` if memory limited.
    * `OneToOne/ManyToOne/ManyToMany-ConcurrentArrayQueue` (agrona library, there is no such queue in JDK) - for single/many producers to single/many consumers (again these queues are lock-free and have no blocking methods for put/poll)
* `UnsafeBuffer` - although in java we have `DirectBuffer` it's not atomic, and if you want to write/read into off-heap memory using thread-safe buffer, this class is way to go
```java
import java.nio.ByteOrder;
import org.agrona.concurrent.UnsafeBuffer;

public class App{
    public static void main(String[] args) {
        UnsafeBuffer buffer = new UnsafeBuffer();
        /**
         * first you need to tell where the buffer starts, it's called wrapping the buffer
         */
        final int offset = 0;
        final int length = 10;
        buffer.wrap(new byte[length], offset, length);
        final int address = 0;
        buffer.putLong(address, 11, ByteOrder.BIG_ENDIAN);
        System.out.println(buffer.getLong(address, ByteOrder.BIG_ENDIAN));

    }
}
```
Comparison between chronicle software & real logic:
* chronicle-queue/map, chronicle wire/values (same as SBE for aeron)
* aeron, agrona, SBE
  Under the hood these 2 use good old `Unsafe` class for raw bytes' manipulation:
* chronicle lacking collection library like `agrona`
* SBE - simple binary encoding, we can achieve same in chronicle using one of 2 libraries
    * chronicle-values - turn your objects into off-heap array of bytes and manipulate it
    * chronicle-wire - more generic library for serialization
* chronicle use memory mapped file, kind of IPC queue, while aeron works on UDP/IPC, yet client talk to media-driver via shared memory (since media-driver can be another process or thread - main advantage that when your main process has GC pause, if you media-driver is another process it can go on with its own business, while if it just a thread it would have to wait, so best way to talk between 2 process is memory-mapped files ). As you see in essence we can say that both chronicle & aeron use concept of memory-mapped files.
  Aeron has its own transport that operates above UDP (there is no TCP for aeron, although you can implement it above TCP), yet although UDP is not reliable as TCP, aeron transport takes care of it, and ensure that you would receive ALL the messages in exactly the same ORDER they were originally sent.
  Aeron archive (using `aeron-spy:udp` channel) - record/replay/replicate messages.
  Don't confuse - aeron uses:
* shared memory - to connect client and media driver, so it better to use `/dev/shm/aeronDirName` - as directory param, cause it's unix default implementation of shared memory
* UDP/IPC - to connect 2 separate clients (or better say to connect 2 media drivers)
    * UDP - uses UDP protocol for data transfer
    * IPC - again using same shared memory inside single PC for data transfer between publisher & subscriber (media driver kind of remove itself and leave pub & sub to asynchonously exchange messages through shared memory)
      Top aeron (and generally any low-latency sensitive) principle:
* garbage free
* lock-free algos
* non-blocking IO (don't wait OS comeback for any request for IO)
* keep CPU pipeline hot & avoid CPU stall
* single writer principle - if you write concurrent code, try if you can to make your data structure to be single writer, this would greatly simplify implementation
* pass messages between thread rather then share state
* avoid unnecessary data copies
  Comparison between chronicle & in-memory db (according to chronicle developer):
* redis/memcached work based on loopback/socket principle
* you can't access database memory directly from java, you have to go through some system interfaces
* most popular use case is to use loopback or socket
* but using this you get some latency, cause
    * you have to do 4 system calls:
      you need to interrupt kernel, so your call from java is redirected to db interface
      each such call can cause 100s ns
      4 calls: put request into socket in java, take request from socket in db
      put response into socket in db, take response from socket in java
      each such request would require some system call (os kernel operation)
    * copy data between java process & db process (again all copy happens in kernel calls) which add up latency
* on average in best case scenario you can get 1-10 µs. latency using in-memory db
* with chronicle, you can get 1µs, due to direct access to shared memory, without any system calls
* there are 2 ways to work with shared memory
    * using JNI interface - jni calls add some latency anyway (around 50ns), not the best solution for latency-critical apps
    * directly manipulate shared memory with `java.misc.Unsafe` - this approach used by chronicle
      Event sourcing vs 2PC (two phase commit):
* previously in enterprise we used 2PC to ensure that we read from one system & write to another, yet this approach incur a lot of latency
* now we use messaging system with unique ID per message to ensure data integrity
  so if our app is broken, we restart and retrieve last message we processed, and then go to broker and ask give me next message after this, by this logic all modern brokers work like: kafka, chronicle-queue, aeron, lmax disruptor
  Thread affinity:
* locking is generally bad for latency, use lock-free & CAS
* yet locking and jumping between threads is even worse
    * jumping between threads 100s µs
    * staying on same core - 10s µs
      So if you have to use locking, make sure your threads stay on same core - use java thread affinity libraries to ensure it (java doesn't have this feature out-of-the-box)
* java thread - normal OS thread, so first get thread ID, then use `taskset` to link thread to specific core, yet this core can be taken by OS to run other threads, but we can use `isolcpus` to remove core from linux thread planner and this core will be used by our thread only. Once you configure this java spinlocks `Thread.onSpinWait()` doesn't give huge effect, cause sole purpose of this command is to keep burning spu cycles, so OS won't take away this core from your thread, yet if you configures linux and link thread to core, there is
  no point in this java instruction.
  Thread interleaving:
* interleave - insert something into something (book interleaved with handwritten text - means you insert pages with your handwriting into book)
* means one thread interleave (kind of happen at the same time as another) another - they share memory (variables) that they both modify, and unexpected results may happen
  [LMAX Disruptor](https://lmax-exchange.github.io/disruptor)
  LMAX (London multi asset exchange) - company that launched derivative exchange for retail users in 2010
  Add this to your pom.xml to work with disruptor
```
    <dependency>
      <groupId>com.lmax</groupId>
      <artifactId>disruptor</artifactId>
      <version>3.4.4</version>
    </dependency>
```
Disruptor (kind of `BlockingQueue`) - moves data (messages/events) between threads within same process with support:
Disruptor is a bad naming, cause what is actually is - non-blocking multi-reader/writer queue. Since each reader maintains sequence, you can have many readers that read from queue.
* multicast events - send same message to multiple consumers
* consumer dependency graph - if we have 3 consumers: A depends on B which depends on C, so we don't want C to get new message until both A & B completed handling of this message
* memory pre-allocation - preallocate the storage required for the events within the Disruptor so GC won't run and stall your system
* optionally lock-free - use memory barrier & compare-and-swap algo to get lock-free performance
* not breaking SWP, while queue does
  Disruptor use following concepts inside:
* RingBuffer - place to store message/event
* Sequence (kind of `AtomicLong`) - each consumer & disruptor maintains a sequence to know where current state is
* Sequencer - core of the Disruptor
* Wait Strategy - how consumer wait for events
  SWP (Single Writer Principle) - there are 2 types to handle concurrent writes:
* mutual exclusion - block resource so only 1 thread write at a time (using `synchronized`)
* optimistic concurrency - using CAS algorithms
  But both can create a lot of extra work, so CPU just resolve concurrency instead of doing actual work.
  In such scenario if you can design your system so you have 1 writer - this is best approach, not to spend precious CPU cycles on maintain concurrency
  Yet Disruptor can support multiple writers, in this case CAS used to ensure that sequence updated correctly between several writers (yet even CAS add latency [although not as big as lock](https://lmax-exchange.github.io/disruptor/disruptor.html), but if we have only 1 writer, we can do it without CAS). So when you write to disruptor:
* writer claim next slot in sequence (sequence - simple counter for SWP, or atomic counter for multiple writers)
* once sequence is incremented, entry in ringbuffer available for writing
* writer write data into the entry and update separate counter (cursor in ringbuffer for latest available entry)
* consumer use memory barrier to read the cursor and then go to ringbuffer to read actual data
* each consumer has their own sequence of read slots in ringbuffer - which can be used by writer to avoid overflow
  Having said this we can summarize that disruptor - is basically a simple queue, where you have producer & consumer using lock-free algos, while aeron fully-fledged framework with multiple components like: archive, cluster, udp/icp communication, media driver & shared memory.
  There are 4 main waiting strategy (all implements `WaitStrategy` interface):
* BlockingWaitStrategy (default) - use lock & condition to wake-up thread. The slowest one
* SleepingWaitStrategy (bad for low-latency) - sleep for 1 ns, internally use `Unsafe.park`
* YieldingWaitStrategy (good for low-latency) - internally use `Thread.yield()`
* BusySpinWaitStrategy - keep cpu busy for a while, so OS won't put other thread into this core, so you don't have memory flush
  Under-the-hood `BlockingQueue` use `ReentrantLock` & `Condition` so all blocking operations like `take/put` waits until element in queue or there is space. Queue is a bad choice cause it breaks SWP, cause for both put & take operations you basically modify/write to queue and here contention happens, so disruptor is alternative to queue. In disruptor there is only 1 writer, that put messages into `RingBuffer`, all other are readers, that just read messages based on their sequence number.
  Queue breaks SWP - this can cause false sharing (silent performance killer). False sharing - when 2 threads modify different variables, that happened to be in same cache line (cpu store not single variables but chuck of memory of 64KB in single line, and 2 different variables may end in same chunk). in such scenario, although it 2 different variables, 2 threads would invalidate cache of each other. Because of the 2 cores would need to request variable again from RAM. One solution to false sharing is cache line padding where you add 7 long values to your value, so it stored in separate cache line
  `volatile` keyword used for 2 things (it has nothing to do with false sharing):
* variable visibility - change in one thread would be immediately visible to other threads
* code order - (without it compiler may reorder you code)
  `ringBufferSize` - second param to `Disruptor` constructor. It determine the size of RingBuffer. Producer can write only until size is full. Once all consumer read some sequence, it can be overwritten by producer.
  So producer should know what is latest sequence number that was read by all consumers, and check if buffer size not full, only then they can write.
  You can test it by setting one consumer with `Thread.sleep` and other without. And one without - would read whole ring buffer. But only once second consumer would read messages, new would be added by producer.
  Basic example (2 consumer runs in parallel, third wait for these 2 and run after - dependecy graph)
```java
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class App {
    public static void main(String[] args) {
        System.out.println("__START__");
        int bufferSize = 4;
        Disruptor<MyEvent> disruptor = new Disruptor<>(MyEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith(
                (event, sequence, endOfBatch) -> System.out.println("1. thread=" + Thread.currentThread().getName() + ", sequence=" + sequence + ", event=" + event),
                (event, sequence, endOfBatch) -> System.out.println("2. thread=" + Thread.currentThread().getName() + ", sequence=" + sequence + ", event=" + event)
        ).then((event, sequence, endOfBatch) -> {
            System.out.println("3. thread=" + Thread.currentThread().getName() + ", sequence=" + sequence + ", event=" + event);
            Thread.sleep(5000);
        });
        RingBuffer<MyEvent> ringBuffer = disruptor.start();
        for (int i = 0; i < 100; i++) {
            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer), i);
            System.out.println("publishEvent=" + i + " thread=" + Thread.currentThread().getName());
        }
        System.out.println("__DONE__");
    }
}


class MyEvent{
    private int value;
    public void setValue(int value){
        this.value = value;
    }
    @Override
    public String toString(){
        return "MyEvent[value=" + value + "]";
    }
}
```
It's very similar to [CoralSequencer](https://www.coralblocks.com/index.php/state-of-the-art-distributed-systems-with-coralmq), but it is open, and it on Github.
While CoralSequencer is private and mostly used in banks (there is no way to see it code, yet you can read overall architecture on it's website).
[aeron](https://aeroncookbook.com/aeron/overview/)
```java
import io.aeron.Aeron;
import io.aeron.Publication;
import io.aeron.Subscription;
import io.aeron.driver.MediaDriver;
import io.aeron.logbuffer.FragmentHandler;
import org.agrona.concurrent.IdleStrategy;
import org.agrona.concurrent.SleepingIdleStrategy;
import org.agrona.concurrent.UnsafeBuffer;
import java.nio.ByteBuffer;

public class App{
    private static void startPublisherThread(Publication pub, IdleStrategy idle){
        new Thread(()->{
            while (!pub.isConnected())
            {
                idle.idle();
            }
            for (int i = 0; i < 10; i++){
                final UnsafeBuffer unsafeBuffer = new UnsafeBuffer(ByteBuffer.allocate(256));
                unsafeBuffer.putStringAscii(0, "msg");
                long pos = pub.offer(unsafeBuffer);
                System.out.println("message i=" + i + ", pos=" + pos);
                while (pos < 0) {
                    idle.idle();
                    pos = pub.offer(unsafeBuffer);
                }
            }

        }, "PublisherThread").start();
    }
    private static void startSubscriptionThread(Subscription sub, IdleStrategy idle){
        new Thread(() -> {
            FragmentHandler handler = (buffer, offset, length, header) ->
                    System.out.println("received msg=" + buffer.getStringAscii(offset));
            while (sub.poll(handler, 1) <= 0) {
                idle.idle();
            }
        }, "SubscriptionThread").start();
    }

    public static void main(String[] args)
    {
        final String channel = "aeron:ipc";
        final IdleStrategy idle = new SleepingIdleStrategy(1000);
        try (MediaDriver driver = MediaDriver.launch();
             Aeron aeron = Aeron.connect();
             Subscription sub = aeron.addSubscription(channel, 10);
             Publication pub = aeron.addPublication(channel, 10))
        {
            startPublisherThread(pub, idle);
            startSubscriptionThread(sub, idle);
        }
    }
}
```
aeron cluster:
* framework for building clustered application based on aeron technology
* you write your code (like matching engine) and add cluster interface `ClusteredService` which provides all lifecycle events which your app should handle
* you have clustered app out of the box, where aeron cluster take care of mainating state, storing snapshot, send/receive messages

### Java Agent
Brief introduction:
* agent is jar file specifically designed to modify original app code
* it uses `Instrumentation` API to achieve its goals
* you can use it if you don't/can't modify original code, so you add this agent code to modify your original code on byte code level
* aeron logs using this tool for logging - in order to avoid scattered log statement all around the code, they decided to use java agent instead
* logic is similar to javassist, where you weave agent code into your own, and kind of enhancing the functionality of your code without actually modifying it (again kind of Reflection)
  There are 2 types of loading:
* static (use of `premain` method) - calling it as separate jar in java command: `java -javaagent:agent.jar -jar app.jar`
    * you need to add `Premain-Class : com.java.test.JavaAgent` entry to manifest
* dynamic (use of `agentmain` method) - use java API to integrate call into the code: use `VirtualMachine` java class
```java
VirtualMachine vm = VirtualMachine.attach(PID);
try {
    vm.loadAgent(agentJar);
} finally {
    vm.detach();
}
```
All profilers like `JProfiler` use this technology, they just run as java agents, on top of your app, and collect all requiring info about your app performance. They also modify some bytecode to add logic on top of your app to calculate different performance-related metrics. But they use this param `-agentpath:` to pass their library. This command line argument loads agent library must implement the JVM tool interface (JVMTI). This is special interface that allows program to inspect the state of execution and provides API for programs like debuggers and profilers. This interface was designed by JSR-163.
Jprofiler (use it as example, just to get better idea how profilers work):
* The "JVM tool interface" (JVMTI) is a native interface that a profiler uses to gain access to information and add hooks for inserting its own instrumentation.
* Once loaded, the profiling agent asks the JVMTI to be notified of all kinds of events, such as thread creation or class loading.
* `-agentpath` is a generic VM parameter provided by the JVM for loading any kind of native library that uses the JVMTI interface.
* Java agents, on the other hand, are loaded with the `-javaagent` VM parameter and only have access to a limited set of capabilities.
* After `-agentpath:`, the full path name to the native library is appended. There is an equivalent parameter `-agentlib:` where you only specify the platform-specific library name, but then you have to make sure that the library is contained in the library path. After the path to the library, you can add an equals sign and pass options to the agent, separated by commas
  With the attach functionality in JProfiler, you can select a running JVM and load the profiling agent on the fly. While attach mode is convenient, it has a couple of drawbacks that you should be aware of
* Some features in JProfiler are not available in attach mode. This is mostly because some capabilities of the JVMTI can only be switched on when the JVM is being initialized and are not available in later phases of the JVM's lifecycle
* The VM parameters `-XX:+PerfDisableSharedMem` and `-XX:+DisableAttachMechanism` must not be specified for the JVM
* The SSH connection enables JProfiler to upload the agent package and execute the contained command line tools on the remote machine. You don't need SSH to be set up on your local machine, JProfiler ships with its own implementation. In the most straightforward setup you just define host, username and authentication.
* memory analysis that requires references, such as solving a memory leak, is done in the heap walker. The heap walker takes a snapshot of the entire heap and analyzes it. This is an invasive operation that pauses the JVM - potentially for a long time - and requires a large amount of memory.
* if you run `-XX:+HeapDumpOnOutOfMemoryError`, when JVM catch `OutOfMemoryError`, it would create file *.hprof file which you can open with Jprofiler and analyze
* JFR (JDK Flight Recorder) is an event recorder built into the OpenJDK. It can be thought of as the software equivalent of a Data Flight Recorder (Black Box) in a commercial aircraft. It captures information about the JVM itself, and the application running in the JVM. There is a wide variety of data captured, for example method profiling, allocation profiling and garbage collection related events
* The garbage collector probe has different views than the other probes and also uses a different data source. It does not obtain its data from the profiling interface of the JVM, but uses JFR streaming to analyze GC-related events from the JDK flight recorder. Because of the dependency on JFR event streaming, the GC probe is only available when you profile Java 17 or higher on a Hotspot JVM
* you can run it directly on prod and collect data, there are many comments in the internet where ppl used jprofiler for some time in prod and all is good.
* IntelliJ has a nice profile, but it available only for ultimate version, it's not available for community edition

### Java Object Layout
JOL - native tool from OpenJDK that helps you inspect memory and layout of objects in the heap. To use it add this OpenJDK dependency o your `pom.xml`:
```xml
<dependency>
  <groupId>org.openjdk.jol</groupId>
  <artifactId>jol-core</artifactId>
  <version>0.17</version>
</dependency>
```
If you print `VM.current().details()` you will see general rules for memory layout. One point to notice, that java uses byte to store single bit. So for `boolean[]` each value would be stored in separate byte.
If you run following code, you can see that array of 1024 boolean values take 1040 bytes in memory
```java
import org.openjdk.jol.info.ClassLayout;

public class App {
    public static void main(String[] args) {
        boolean[] bits = new boolean[1024];
        System.out.println(ClassLayout.parseInstance(bits).toPrintable());
    }
}
```
would print
```
[Z object internals:
OFF  SZ      TYPE DESCRIPTION               VALUE
  0   8           (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
  8   4           (object header: class)    0x00001ac8
 12   4           (array length)            1024
 16 1024   boolean [Z.<elements>             N/A
Instance size: 1040 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```
Yet with BitSet we would need only 144 bytes (instead of 1040)
```java
import org.openjdk.jol.info.GraphLayout;
import java.util.BitSet;

public class App {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet(1024);
        System.out.println(GraphLayout.parseInstance(bitSet).toPrintable());
    }
}
```
The result is
```
java.util.BitSet@5910e440d object externals:
          ADDRESS       SIZE TYPE             PATH                           VALUE
        44d795028         24 java.util.BitSet                                (object)
        44d795040        144 [J               .words                         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

```