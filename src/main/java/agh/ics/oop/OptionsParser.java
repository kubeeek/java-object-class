package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public static ArrayList<MoveDirection> parse(String[] arguments) throws IllegalArgumentException {
        ArrayList<MoveDirection> directions = new ArrayList<>();

        for (String argument :
                arguments) {
            MoveDirection direction = null;

            switch (argument) {
                case "f", "forward" -> direction = MoveDirection.FORWARD;
                case "b", "backward" -> direction = MoveDirection.BACKWARD;
                case "r", "right" -> direction = MoveDirection.RIGHT;
                case "l", "left" -> direction = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(argument + " is not legal move specification");
            }

            directions.add(direction);
        }


        return directions;
    }
}