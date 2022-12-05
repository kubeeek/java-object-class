package agh.ics.oop;


import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IPositionChangeListener {
    private final int grassCount;
    private final int max;

    GrassField(int grassCount) {
        this.grassCount = grassCount;
        this.max = (int) Math.sqrt(grassCount * 10);

        this.init();
    }

    private void init() {
        Random random = new Random();

        for (int i = 0; i < grassCount; i++) {
            Vector2d randomPosition = new Vector2d(random.nextInt(max), random.nextInt(max));

            while (mapElementsMap.containsKey(randomPosition)) {
                randomPosition = new Vector2d(random.nextInt(max), random.nextInt(max));
            }

            mapElementsMap.put(randomPosition, new Grass(randomPosition));
        }

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(this.mapElementsMap.containsKey(position))
            return !(this.mapElementsMap.get(position) instanceof Animal);

        return true;
    }


    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        var element = super.objectAt(position);



        return element;
    }

    @Override
    public String toString() {
        for (Map.Entry<Vector2d, AbstractWorldMapElement> mapElement : this.mapElementsMap.entrySet()) {
            if (!(mapElement instanceof Animal))
                continue;

            this.upperRight = this.upperRight.upperRight(mapElement.getValue().getPosition());
            this.lowerLeft = this.lowerLeft.lowerLeft(mapElement.getValue().getPosition());
        }

        return super.toString();
    }

    @Override
    public void positionChanged(AbstractWorldMapElement object, Vector2d oldPosition, Vector2d newPosition) {
        // eat grass first, then move animal
        AbstractWorldMapElement grassElement = this.objectAt(newPosition);

        if (grassElement instanceof Grass) {
            this.deleteObject(newPosition);
        }

        this.deleteObject(oldPosition);
        this.placeAt((Animal) object, newPosition);
        this.generateNewGrass();
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
