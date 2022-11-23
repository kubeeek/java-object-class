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
        return !this.mapElementsMap.containsKey(position) && !(this.mapElementsMap.get(position) instanceof Animal);
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        var element = super.objectAt(position);

        if (element != null)
            return element;

        return null;
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
        super.positionChanged(object, oldPosition, newPosition);

        // eat grass
        AbstractWorldMapElement grassElement = this.objectAt(newPosition);

        if (grassElement != null && grassElement instanceof Grass) {
            this.deleteObject(oldPosition, object);
            this.generateNewGrass();
        }
    }

    private void generateNewGrass() {
        Random random = new Random();
        Vector2d randomPosition = new Vector2d(random.nextInt(max), random.nextInt(max));

        while (mapElementsMap.containsKey(randomPosition) || this.mapElementsMap.containsKey(randomPosition)) {
            randomPosition = new Vector2d(random.nextInt(max), random.nextInt(max));

        }
        mapElementsMap.put(randomPosition, new Grass(randomPosition));

    }
}
