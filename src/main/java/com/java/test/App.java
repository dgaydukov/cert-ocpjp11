package com.java.test;

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