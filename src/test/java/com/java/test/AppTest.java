package com.java.test;

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