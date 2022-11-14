package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;


public class AnimalSimulationEngineIntegrationTest {
    OptionsParser testOptionsParser;
    IWorldMap map;

    @BeforeEach
    void setUp() {
        testOptionsParser = new OptionsParser();
        map = new RectangularMap(10, 5);
    }

    @Test
    void testSimulation() {
        String[] args = "f b r l f f r r f f f f f f f f".split(" ");


        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));

        IEngine engine = new SimulationEngine(directions, this.map, positions);
        engine.run();

        Animal testedAnimal = (Animal) this.map.objectAt(new Vector2d(3, 5));
        assertNotNull(testedAnimal);

        testedAnimal = (Animal) this.map.objectAt(new Vector2d(2, 0));
        assertNotNull(testedAnimal);

        testedAnimal = (Animal) this.map.objectAt(new Vector2d(2, 1));
        assertNull(testedAnimal);
    }

    @Test
    void testIfAnimalsCanOverlap() {
        String[] args = "f f f f f f l l l f".split(" ");


        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 2)));

        IEngine engine = new SimulationEngine(directions, this.map, positions);
        engine.run();

        Animal testedAnimal = (Animal) this.map.objectAt(new Vector2d(2, 5));
        assertNotNull(testedAnimal);

        testedAnimal = (Animal) this.map.objectAt(new Vector2d(3, 5));
        assertNotNull(testedAnimal);
    }
}
