package com.java.test;


import java.util.Objects;

public class App{
    public static void main(String[] args) {
        final Short s1 = 1;
        int s2 = -s1;
    }
}


//import com.lmax.disruptor.RingBuffer;
//import com.lmax.disruptor.dsl.Disruptor;
//import com.lmax.disruptor.util.DaemonThreadFactory;
//
//public class App {
//    public static void main(String[] args) {
//        System.out.println("__START__");
//        int bufferSize = 4;
//        Disruptor<MyEvent> disruptor = new Disruptor<>(MyEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
//        disruptor.handleEventsWith(
//                (event, sequence, endOfBatch) -> System.out.println("1. thread=" + Thread.currentThread().getName() + ", sequence=" + sequence + ", event=" + event),
//                (event, sequence, endOfBatch) -> System.out.println("2. thread=" + Thread.currentThread().getName() + ", sequence=" + sequence + ", event=" + event)
//        ).then((event, sequence, endOfBatch) -> {
//            System.out.println("3. thread=" + Thread.currentThread().getName() + ", sequence=" + sequence + ", event=" + event);
//            Thread.sleep(5000);
//        });
//        RingBuffer<MyEvent> ringBuffer = disruptor.start();
//        for (int i = 0; i < 100; i++) {
//            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer), i);
//            System.out.println("publishEvent=" + i + " thread=" + Thread.currentThread().getName());
//        }
//        System.out.println("__DONE__");
//    }
//}
//
//
//class MyEvent{
//    private int value;
//    public void setValue(int value){
//        this.value = value;
//    }
//    @Override
//    public String toString(){
//        return "MyEvent[value=" + value + "]";
//    }
//}