package agh.ics.oop;

public class Animal {
    private MapDirection heading;
    private Vector2d position;

    Animal() {
        heading = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

    Animal(MapDirection heading, Vector2d position) {
        this.heading = heading;
        this.position = position;
    }

    void move(MoveDirection direction) {

        switch (direction) {
            case RIGHT -> this.heading = this.heading.next();
            case LEFT -> this.heading = this.heading.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.heading.toUnitVector());
                if(!(newPosition.x > 4 || newPosition.y > 4 || newPosition.x < 0 || newPosition.y < 0))
                    this.position = this.position.add(this.heading.toUnitVector());
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.add(this.heading.toUnitVector().opposite());
                if(!(newPosition.x > 4 || newPosition.y > 4 || newPosition.x < 0 || newPosition.y < 0))
                    this.position = this.position.add(this.heading.toUnitVector());
            }
        }
    }

    boolean isAt(Vector2d position) {
        return position == this.position;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "heading=" + heading +
                ", position=" + position +
                '}';
    }
}
