import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void constructorNullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException emptyList = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", emptyList.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> listOfHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            listOfHorses.add(new Horse("Horse" + i, i, i));
        }
        assertEquals(listOfHorses, new Hippodrome(listOfHorses).getHorses());
    }

    @Test
    void move() {
        List<Horse> mockedHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
        Horse horseMock = Mockito.mock(Horse.class);
            mockedHorses.add(horseMock);
        }
        new Hippodrome(mockedHorses).move();
        for (Horse horse : mockedHorses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            horseList.add(new Horse("Horse" + i,i,i));
        }
        Horse maxDistanceHorse = horseList.stream().max(Comparator.comparing(Horse::getDistance)).get();
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(hippodrome.getWinner(), maxDistanceHorse);
    }
}