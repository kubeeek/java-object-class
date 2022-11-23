package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.MapDirection.NORTH;

public class Animal extends AbstractWorldMapElement {
    private AbstractWorldMap map;
    private MapDirection heading = NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private final List<IPositionChangeListener> listeners = new ArrayList<>();

    Animal(MapDirection heading, Vector2d position) {
        this.heading = heading;
        this.position = position;
    }

    Animal(AbstractWorldMap map) {
        this.map = map;
        this.addListener(this.map);

    }

    Animal(AbstractWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.addListener(this.map);

    }

    void move(MoveDirection direction) {

        switch (direction) {
            case RIGHT -> this.heading = this.heading.next();
            case LEFT -> this.heading = this.heading.previous();
            case FORWARD, BACKWARD-> {
                Vector2d newPosition = null;

                if(direction == MoveDirection.BACKWARD)
                    newPosition = this.position.add(this.heading.toUnitVector().opposite());
                else
                    newPosition = this.position.add(this.heading.toUnitVector());

                boolean canMove = map.canMoveTo(newPosition);

                if (canMove) {
                    positionChanged(this.position, newPosition);
                    this.position = newPosition;

                }
            }
        }
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public Vector2d getPosition() {
        return this.position;
    }

    private void addListener(IPositionChangeListener listener) {
        this.listeners.add(listener);
    }

    private void removeListener(IPositionChangeListener listener) {
        this.listeners.remove(listener);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeListener observer : this.listeners)
            observer.positionChanged(this, oldPosition, newPosition);
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
