package com.java.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class AppTest {
    @Test
    public void testPerson(){
        Clock clock = Mockito.mock(Clock.class);
        Person person = new Person(clock);
        Mockito.doAnswer(new Answer() {
            private int count = 0;
            public Object answer(InvocationOnMock invocation) {
                return (long) ++count;
            }
        }).when(clock).getTimestamp();
        Assertions.assertEquals("run at 1", person.run(), "run mismatch");
        Assertions.assertEquals("swim at 2", person.swim(), "swim mismatch");
        Assertions.assertEquals("dance at 3", person.dance(), "dance mismatch");
    }
}

interface Clock {
    long getTimestamp();
}
class Person {
    private final Clock clock;
    public Person(Clock clock) {
        this.clock = clock;
    }

    public String run(){
        return "run at " + clock.getTimestamp();
    }
    public String swim(){
        return "swim at " + clock.getTimestamp();
    }
    public String dance(){
        return "dance at " + clock.getTimestamp();
    }
}