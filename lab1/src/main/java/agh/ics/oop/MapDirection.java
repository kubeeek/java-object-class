package agh.ics.oop;

enum MapDirection {
    NORTH, SOUTH, WEST, EAST;

    public String toString() {
        switch(this) {
            case NORTH: return "Północ";
            case SOUTH: return "Południe";
            case WEST: return "Zachód";
            case EAST: return "Wschód";
        }
        return null;
    }

    MapDirection next() {
        switch(this) {
            case NORTH: return MapDirection.EAST;
            case SOUTH: return MapDirection.WEST;
            case WEST: return MapDirection.NORTH;
            case EAST: return MapDirection.SOUTH;
        }
        return null;
    }

    MapDirection previous() {
        switch(this) {
            case NORTH: return MapDirection.WEST;
            case SOUTH: return MapDirection.EAST;
            case WEST: return MapDirection.SOUTH;
            case EAST: return MapDirection.NORTH;
        }
        return null;
    }

    Vector2d toUnitVector() {
        switch(this) {
            case NORTH: return new Vector2d(0, 1);
            case SOUTH: return new Vector2d(0, -1);
            case WEST: return new Vector2d(-1, 0);
            case EAST: return new Vector2d(1, 0);
        }
        return null;
    }
}
