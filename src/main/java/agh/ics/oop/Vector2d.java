package agh.ics.oop;

public class Vector2d {
    public int x;
    final public int y;


    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean precedes(Vector2d other) {
        return (this.x <= other.x) && (this.y <= other.y);
    }

    boolean follows(Vector2d other) {
        return (this.x >= other.x) && (this.y >= other.y);
    }

    Vector2d add(Vector2d other) {
        return new Vector2d(other.x + this.x, other.y + this.y);
    }

    Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    Vector2d upperRight(Vector2d other) {
        int x = other.x >= this.x ? other.x : this.x;
        int y = other.y >= this.y ? other.y : this.y;

        return new Vector2d(x, y);
    }

    Vector2d lowerLeft(Vector2d other) {
        int x = other.x <= this.x ? other.x : this.x;
        int y = other.y <= this.x ? other.y : this.y;

        return new Vector2d(x, y);
    }

    Vector2d opposite() {
        return new Vector2d(this.x * -1, this.y * -1);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2d vector2d = (Vector2d) o;

        if (x != vector2d.x) return false;
        return y == vector2d.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


}
