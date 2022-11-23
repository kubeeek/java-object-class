package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {
    private final Vector2d position;

    Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return this.getPosition().equals(position);
    }

    @Override
    public String toString() {
        return "*";
    }
}
