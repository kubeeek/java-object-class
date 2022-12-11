package agh.ics.oop;


import java.util.Random;

public class GrassField extends AbstractWorldMap implements IPositionChangeListener {
    private final int grassCount;
    private final int max;
    private MapBoundary mapBoundary;

    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        this.max = (int) Math.sqrt(grassCount * 10);
        this.mapBoundary = new MapBoundary();

        this.init();
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException {
        super.place(animal);

        this.mapBoundary.positionChanged(animal, animal.getPosition(), animal.getPosition());

        return true;
    }

    private void init() {
        for (int i = 0; i < grassCount; i++) {
            generateNewGrass();
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        var element = this.mapElementsMap.get(position);

        return element instanceof Animal;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (this.mapElementsMap.containsKey(position))
            return !(this.mapElementsMap.get(position) instanceof Animal);

        return true;
    }


    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        var element = super.objectAt(position);


        return element;
    }

    @Override
    public Vector2d getLowerLeft() {
        return new Vector2d(this.mapBoundary.xSet.first().getPosition().x, this.mapBoundary.ySet.first().getPosition().y);
    }

    @Override
    public Vector2d getUpperRight() {
        return new Vector2d(this.mapBoundary.xSet.last().getPosition().x, this.mapBoundary.ySet.last().getPosition().y);
    }

    @Override
    public String toString() {
        this.lowerLeft = getLowerLeft();
        this.upperRight = getUpperRight();

        return super.toString();
    }

    @Override
    public void positionChanged(AbstractWorldMapElement object, Vector2d oldPosition, Vector2d newPosition) {
        this.mapBoundary.positionChanged(object, oldPosition, newPosition);

        // eat grass first, then move animal
        AbstractWorldMapElement grassElement = this.objectAt(newPosition);

        if (grassElement instanceof Grass) {
            this.deleteObject(newPosition);
            this.deleteObject(oldPosition);
            this.placeAt((Animal) object, newPosition);
            this.generateNewGrass();
        } else
            super.positionChanged(object, oldPosition, newPosition);
    }

    private void generateNewGrass() {
        Random random = new Random();
        Vector2d randomPosition = new Vector2d(random.nextInt(max), random.nextInt(max));

        while (this.mapElementsMap.containsKey(randomPosition)) {
            randomPosition = new Vector2d(random.nextInt(max), random.nextInt(max));

        }
        mapElementsMap.put(randomPosition, new Grass(randomPosition));

    }
}
