import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void getNullName() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "     ", "\n"})
    void getEmptyName(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void getNegativeSpeed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1, 2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void getNegativeDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1, -2));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String name = "Horse";
        Horse horse = new Horse(name, 1, 2);
        assertEquals(horse.getName(), name);
    }

    @Test
    void getSpeed() {
        double speed = 1;
        Horse horse = new Horse("Horse", speed, 2);
        assertEquals(horse.getSpeed(), speed);
    }

    @Test
    void getDistance() {
        double distance = 2;
        Horse horse1 = new Horse("Horse", 1, distance);
        assertEquals(horse1.getDistance(), distance);
        Horse horse2 = new Horse("Horse", 1);
        assertEquals(0, horse2.getDistance());
    }

    @Test
    void moveMockedStatic() {
        try (MockedStatic<Horse> randomDouble = Mockito.mockStatic(Horse.class)) {
            new Horse("Horse", 1, 2).move();
            randomDouble.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.4, 0.6, 1, 10})
    void move(double value) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 2, 4);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            horse.move();
            assertEquals(4 + 2 * value, horse.getDistance());
        }
    }
}