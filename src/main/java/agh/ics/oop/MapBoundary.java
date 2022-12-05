package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeListener {
    SortedSet<AbstractWorldMapElement> xSet = new TreeSet<>(MapBoundary::comparatorByX);
    SortedSet<AbstractWorldMapElement> ySet = new TreeSet<>(MapBoundary::comparatorByY);

    static int comparatorByX(AbstractWorldMapElement a, AbstractWorldMapElement b) {
        var posA = a.getPosition();
        var posB = b.getPosition();

        if(posA.x < posB.x)
            return -1;
        if(posA.x > posB.x)
            return 1;

        return 0;
    }

    static int comparatorByY(AbstractWorldMapElement a, AbstractWorldMapElement b) {
        var posA = a.getPosition();
        var posB = b.getPosition();

        if(posA.y < posB.y)
            return -1;
        if(posA.y > posB.y)
            return 1;

        return 0;
    }

    @Override
    public void positionChanged(AbstractWorldMapElement object, Vector2d oldPosition, Vector2d newPosition) {
        this.ySet.remove(object);
        this.xSet.remove(object);

        this.xSet.add(object);
        this.ySet.add(object);
    }
}
