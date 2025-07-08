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