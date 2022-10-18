package agh.ics.oop;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class World {
    static HashSet<String> allowedArgs = new HashSet<>(Arrays.asList("f", "r", "d", "l"));

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }

    public static void run(Direction direction) {
        String message = switch (direction) {
            case FORWARD -> "do przodu";
            case BACKWARD -> "do tyłu";
            case RIGHT -> "w prawo";
            case LEFT -> "w lewo";
        };

        String line = String.format("Zwierze idzie %s.", message);
        System.out.println(line);
    }
}
