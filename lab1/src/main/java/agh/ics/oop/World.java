package agh.ics.oop;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class World {
    static HashSet<String> allowedArgs = new HashSet<>(Arrays.asList("f", "r", "d", "l"));

    public static void main(String[] args) {
        System.out.println("Start");

        List<Direction> directions = Arrays.stream(args)
                .map(String::toLowerCase)
                .filter(element -> allowedArgs.contains(element))
                .map(ArgumentParser::parseArgument).toList();

        for (Direction direction :
                directions) {
            run(direction);
        }

        System.out.println("End");

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
