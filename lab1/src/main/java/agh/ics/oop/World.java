package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("start");

        for (String arg :
                args) {
            Direction direction = null;

            switch (arg) {
                case "f" -> direction = Direction.FORWARD;
                case "b" -> direction = Direction.BACKWARD;
                case "r" -> direction = Direction.RIGHT;
                case "l" -> direction = Direction.LEFT;
            }

            if (direction != null)
                run(direction);
        }
        System.out.println("end");

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
