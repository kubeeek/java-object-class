package agh.ics.oop;

import agh.ics.oop.Direction;

class ArgumentParser {
    public static Direction parseArgument(String argument) {
        Direction direction = null;

        switch (argument) {
            case "f" -> direction = Direction.FORWARD;
            case "b" -> direction = Direction.BACKWARD;
            case "r" -> direction = Direction.RIGHT;
            case "l" -> direction = Direction.LEFT;
        }

        return direction;
    }
}