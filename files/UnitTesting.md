# Unit Testing

### Contents
* [Junit Parametrized Tests](#junit-parametrized-tests)
* [Mockito basics](#mockito-basics)
* [Mockito multiple returns](#mockito-multiple-returns)
* [Mockito: throw exception for all other calls](#mockito-throw-exception-for-all-other-calls)

###### Junit Parametrized Tests
* you can test interface directly by providing it's implementations
* You just create instances of the interface and feed them to your test
* Check below code - as you see we create 2 instances of `Car` class but instead of creating 2 separate tests, we create single parametrized test that covers both scenarios.
```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class AppTest {
    @ParameterizedTest
    @MethodSource("getAllCars")
    public void maxSpeedTest(Car car, int expectedSpeed) {
        Assertions.assertEquals(expectedSpeed, car.speed(), "Max car speed should be: "+expectedSpeed);
    }

    private static Stream<Arguments> getAllCars() {
        return Stream.of(
                Arguments.of(new FamilyCar(), 60),
                Arguments.of(new SportCar(), 120)
        );
    }
}

interface Car {
    int speed();
}
class FamilyCar implements Car {
    @Override
    public int speed() {
        return 60;
    }
}
class SportCar implements Car {
    @Override
    public int speed() {
        return 120;
    }
}
```

###### Mockito basics
Don't confuse (2 ways to mock objects):
* `mock` - completely simulated object that always return null for objects, 0 for primitives, false for boolean, except the cases when explicitly provided with `when().thenReturn()`
* `spy` - real object wrapped into spy object that always return real values and execute methods, except the cases when explicitly provided with `when().thenReturn()` - in these cases method is not executed, and fake results are returned

Don't confuse:
* `doReturn(...) when(...)` - you can always use for stubbing
    * for spy: would return stub object without calling real method
* `when(...) thenReturn(...)` - there are cases where you can't use
    * for spy: will call real method first, but then would return stub object
    * can't be used for void method

###### Mockito: multiple returns
You know that with `Mockito` you can return values with `when/thenReturn`. But what if your method called many times, and you want to return different but consistent values. You can use `Answer` argument.
```java
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
```

###### Mockito: throw exception for all other calls
When you mock object, you can add stub invocations with the construct `when().thenReturn`. And it works fine, but inside the code you may have multiple calls to different methods. For example, you expect that only 2 methods of the mock would be called, and you stub it, but actually other also called with default values (null/0/false) but in test you are not aware about it. Or maybe somebody later add code to call other non-stubbed methods of this dependency. This is bad, because your test is non-deterministic. Ideally it would be that you mock all potentially knows called methods, and throw exception if non-stubbed method is called. You can achieve this with below code. Pay attention we pass `Answer` as second param to our `Mockito.mock` method. With this, all invocations that are not explicitly stubbed, would be handled by this method. In below code we have a call `nextDouble` which is not stubbed. If you remove second param for mock, this code would run fine and test would execute successfully. But adding this logic to throw exception for any call of unstubbed method add determinism into our test.
```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class AppTest {
    @Test
    public void test(){
        Random mock = Mockito.mock(Random.class, invocation -> {
            throw new UnsupportedOperationException("Method not stubbed: " + invocation.getMethod().getName());
        });
        Mockito.doReturn(1).when(mock).nextInt();
        Mockito.doReturn(2L).when(mock).nextLong();
        Printer printer = new Printer(mock);
        Assertions.assertEquals("nextInt=1, nextLong=2", printer.print(), "print mismatch");
    }
}

class Printer {
    private final Random random;

    public Printer(Random random) {
        this.random = random;
    }

    public String print() {
        doWork();
        return "nextInt=" + random.nextInt() + ", nextLong=" + random.nextLong();
    }

    private void doWork() {
        // hidden call to method not explicitly defined in the mock
        random.nextDouble();
    }
}
```