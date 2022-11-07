package agh.ics.oop;

import java.util.ArrayList;

class OptionsParser {
    public static ArrayList<MoveDirection> parse(String[] arguments) {
        ArrayList<MoveDirection> directions = new ArrayList<>();

        for (String argument :
                arguments) {
            MoveDirection direction = null;

            switch (argument) {
                case "f", "forward" -> direction = MoveDirection.FORWARD;
                case "b", "backward" -> direction = MoveDirection.BACKWARD;
                case "r", "right" -> direction = MoveDirection.RIGHT;
                case "l", "left" -> direction = MoveDirection.LEFT;
            }

            if (direction != null)
                directions.add(direction);
        }


        return directions;
    }
}