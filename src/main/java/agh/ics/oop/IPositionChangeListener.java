package agh.ics.oop;

public interface IPositionChangeListener {
    void positionChanged(AbstractWorldMapElement object, Vector2d oldPosition, Vector2d newPosition);
}