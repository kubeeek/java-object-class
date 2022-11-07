package agh.ics.oop;

class OptionsParser {
    public static MoveDirection parse(String argument) {
        MoveDirection direction = null;

        switch (argument) {
            case "f", "forward" -> direction = MoveDirection.FORWARD;
            case "b", "backward" -> direction = MoveDirection.BACKWARD;
            case "r", "right" -> direction = MoveDirection.RIGHT;
            case "l", "left" -> direction = MoveDirection.LEFT;
        }

        return direction;
    }
}