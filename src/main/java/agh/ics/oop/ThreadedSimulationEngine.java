package agh.ics.oop;

import java.util.ArrayList;

public class ThreadedSimulationEngine extends SimulationEngine {
    private final int moveDelay = 300;

    public ThreadedSimulationEngine(AbstractWorldMap map, ArrayList<Vector2d> positions) {
        super(map, positions);
    }

    public void setDirections(ArrayList<MoveDirection> directions) {
        this.directions = directions;
    }

    public void addObserver(ISimulationObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void run() {
        super.run();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}