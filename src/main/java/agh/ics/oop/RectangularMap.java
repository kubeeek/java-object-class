package agh.ics.oop;

import java.util.HashSet;
import java.util.Optional;

public class RectangularMap extends AbstractWorldMap {

    // chciałem HashMap<Vector2d, Animal> bo wyszukiwanie O(1), ale podczas zmiany pozycji dla Animalsa edycja pozycji
    // tworzy nowy obiekt, zamiast mutować stary - zatem klucz w Mapie pozostaje "przestarzały", a interfejs IWorldMap
    // nie dostarcza metod do usuwania by móc podmienić Animalsa w taki sposób aby jego pozycja odzwierciedlała stale klucz w Mapie
    private HashSet<Animal> mapObjectSet = new HashSet<>();

    RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean isWithinMap = position.follows(lowerLeft) && position.precedes(upperRight);

        return isWithinMap && !isOccupied(position);
    }
}
