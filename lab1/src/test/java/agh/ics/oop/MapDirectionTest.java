package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    MapDirection mapDirection;

    @BeforeEach
    void setUp() {
        mapDirection = MapDirection.SOUTH;
    }

    @Test
    void testNext() {
        assertEquals(MapDirection.WEST, mapDirection.next());
        mapDirection = MapDirection.WEST;

        assertEquals(MapDirection.NORTH, mapDirection.next());
        mapDirection = MapDirection.NORTH;

        assertEquals(MapDirection.EAST, mapDirection.next());
        mapDirection = MapDirection.EAST;

        assertEquals(MapDirection.SOUTH, mapDirection.next());
    }

    @Test
    void testPrevious() {
        assertEquals(MapDirection.EAST, mapDirection.previous());
        mapDirection = MapDirection.EAST;

        assertEquals(MapDirection.NORTH, mapDirection.previous());
        mapDirection = MapDirection.NORTH;

        assertEquals(MapDirection.WEST, mapDirection.previous());
        mapDirection = MapDirection.WEST;

        assertEquals(MapDirection.SOUTH, mapDirection.previous());
    }
}
