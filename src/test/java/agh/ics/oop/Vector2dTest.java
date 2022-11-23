package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d vector2d;

    @BeforeEach
    void setUp() {
        this.vector2d = new Vector2d(1, 1);
    }

    @Test
    void testEquals() {
        Vector2d equal = new Vector2d(1, 1);
        Vector2d notEqual = new Vector2d(0, 1);

        assertEquals(equal, this.vector2d);
        assertNotEquals(notEqual, this.vector2d);
    }

    @Test
    void toStringTest() {
        assertEquals("(1, 1)", this.vector2d.toString());
    }

    @Test
    void precedesTest() {
        Vector2d less = new Vector2d(0, 0);
        Vector2d more = new Vector2d(5, 5);

        assertTrue(this.vector2d.precedes(more));
        assertFalse(this.vector2d.precedes(less));

    }

    @Test
    void followsTest() {
        Vector2d less = new Vector2d(0, 0);
        Vector2d more = new Vector2d(5, 5);

        assertTrue(this.vector2d.follows(less));
        assertFalse(this.vector2d.follows(more));
    }

    @Test
    void upperRightTest() {
        Vector2d other = new Vector2d(0, 2);
        Vector2d upperRightCorner = new Vector2d(1, 2);

        assertEquals(upperRightCorner, this.vector2d.upperRight(other));
    }

    @Test
    void lowerLeftTest() {
        Vector2d other = new Vector2d(0, 2);
        Vector2d lowerLeftCorner = new Vector2d(0, 1);

        assertEquals(lowerLeftCorner, this.vector2d.lowerLeft(other));
    }

    @Test
    void addTest() {
        Vector2d additionOperand = new Vector2d(-1, 1);
        Vector2d additionResult = new Vector2d(0, 2);

        assertEquals(additionResult, this.vector2d.add(additionOperand));
    }

    @Test
    void subtractTest() {
        Vector2d substitutionOperand = new Vector2d(-1, 1);
        Vector2d substitutionResult = new Vector2d(2, 0);

        assertEquals(substitutionResult, this.vector2d.subtract(substitutionOperand));
    }

    @Test
    void oppositeTest() {
        Vector2d opposite = new Vector2d(-1, -1);

        assertEquals(opposite, this.vector2d.opposite());
    }
}
