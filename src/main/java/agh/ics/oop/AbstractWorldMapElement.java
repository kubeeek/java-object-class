package agh.ics.oop;

public class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position = new Vector2d(0,0);

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    @Override
    public String toImagePath() {
        throw new UnsupportedOperationException();
    }
}