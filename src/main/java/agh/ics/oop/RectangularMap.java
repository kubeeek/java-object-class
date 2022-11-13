package agh.ics.oop;

import java.util.HashSet;

public class RectangularMap implements IWorldMap {
    private final Vector2d upperRight;
    private final Vector2d lowerLeft;

    private HashSet<Animal> mapObjectSet = new HashSet<>();

    RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean isWithinMap = position.follows(lowerLeft) && position.precedes(upperRight);
        return isWithinMap;
    }

    @Override
    public boolean place(Animal animal) {
        if(isOccupied(animal.getPosition()))
            return false;

        mapObjectSet.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.mapObjectSet.stream().anyMatch(element -> element.isAt(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapObjectSet.stream().filter(element -> element.isAt(position)).toList().get(0);
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);

        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }
}
