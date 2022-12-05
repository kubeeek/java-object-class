package agh.ics.oop;

import java.util.HashMap;
import java.util.HashSet;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeListener {
    protected final HashMap<Vector2d, AbstractWorldMapElement> mapElementsMap = new HashMap<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition()))
            return false;

        mapElementsMap.put(animal.getPosition(), animal);

        return true;
    }

    public boolean placeAt(Animal animal, Vector2d position) {
        if (isOccupied(position))
            return false;

        mapElementsMap.put(position, animal);

        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.mapElementsMap.containsKey(position);
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        return this.mapElementsMap.get(position);
    }

    @Override
    public AbstractWorldMapElement deleteObject(Vector2d position) {
        return this.mapElementsMap.remove(position);
    }

    @Override
    public String toString() {
        return this.mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public void positionChanged(AbstractWorldMapElement object, Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement mapElement = this.objectAt(oldPosition);

        if (mapElement instanceof Animal) {
            this.deleteObject(oldPosition);
            this.placeAt((Animal) object, newPosition);
        }
    }
}