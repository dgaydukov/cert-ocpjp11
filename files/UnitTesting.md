# Unit Testing

### Contents
* [Junit Parametrized Tests](#junit-parametrized-tests)
* [Mockito multiple returns](#mockito-multiple-returns)


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

###### Mockito multiple returns
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