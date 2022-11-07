package agh.ics.oop;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalWorldIntegrationTest {

    Animal testAnimal;
    OptionsParser testOptionsParser;

    @BeforeEach
    void setUp() {
        testAnimal = new Animal();
        testOptionsParser = new OptionsParser();
    }

    void moveAnimalInSequence(ArrayList<MoveDirection> directions) {
        for (var direction :
                directions) {
            testAnimal.move(direction);
        }
    }

    @Test
    @DisplayName("Animal can rotate and move")
    public void rotateAnimalAndMoveBackTest() {
        String[] inputString = {"right", "right", "forward"};
        var directions = testOptionsParser.parse(inputString);

        moveAnimalInSequence(directions);
        assertTrue(testAnimal.isAt(new Vector2d(2, 1)));

        inputString = new String[]{"left", "left", "forward"};
        directions = testOptionsParser.parse(inputString);

        moveAnimalInSequence(directions);
        assertTrue(testAnimal.isAt(new Vector2d(2, 2)));
    }

    @Test
    @DisplayName("Animal within the map")
    public void moveAnimalOutsideMapTest() {
        String[] inputString = {"right", "right", "forward", "forward", "forward"};
        var directions = testOptionsParser.parse(inputString);

        moveAnimalInSequence(directions);
        assertTrue(testAnimal.isAt(new Vector2d(2, 0)));

        inputString = new String[]{"right", "f", "f", "forward"};
        directions = testOptionsParser.parse(inputString);
        moveAnimalInSequence(directions);

        assertTrue(testAnimal.isAt(new Vector2d(0, 0)));

        inputString = new String[]{"b", "b", "b", "b", "b"};
        directions = testOptionsParser.parse(inputString);
        moveAnimalInSequence(directions);

        assertTrue(testAnimal.isAt(new Vector2d(4, 0)));

    }
}
