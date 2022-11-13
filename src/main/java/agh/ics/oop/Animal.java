package agh.ics.oop;

import static agh.ics.oop.MapDirection.NORTH;

public class Animal {
    private IWorldMap map;
    private MapDirection heading = NORTH;
    private Vector2d position = new Vector2d(2, 2);

    Animal(MapDirection heading, Vector2d position) {
        this.heading = heading;
        this.position = position;
    }

    Animal(IWorldMap map) {
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    void move(MoveDirection direction) {

        switch (direction) {
            case RIGHT -> this.heading = this.heading.next();
            case LEFT -> this.heading = this.heading.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.heading.toUnitVector());
                boolean canMove = map.canMoveTo(newPosition);

                if (canMove)
                    this.position = newPosition;
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.add(this.heading.toUnitVector().opposite());
                boolean canMove = map.canMoveTo(newPosition);

                if (canMove)
                    this.position = newPosition;
            }
        }
    }

    boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return switch (this.heading) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case WEST -> "<";
            case EAST -> ">";
        };
    }
}
