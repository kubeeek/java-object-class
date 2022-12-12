package agh.ics.oop;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable {

    private final ArrayList<Animal> animalList = new ArrayList<>();
    private final AbstractWorldMap map;
    private final ArrayList<Vector2d> positions;

    ArrayList<MoveDirection> directions;
    ArrayList<ISimulationObserver> observers = new ArrayList<>();

    public SimulationEngine(ArrayList<MoveDirection> directions, AbstractWorldMap map, ArrayList<Vector2d> positions) {
        this(map, positions);

        this.directions = directions;
    }

    public SimulationEngine(AbstractWorldMap map, ArrayList<Vector2d> positions) {
        this.map = map;
        this.positions = positions;

        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            this.map.place(animal);
            this.animalList.add(animal);
        }
    }

    @Override
    public void run() {
        ArrayDeque<MoveDirection> directionQueue = new ArrayDeque<>();


        for (var direction : directions) {
            directionQueue.add(direction);
        }

        notifyListeners();
        int counter = 0;
        while (!directionQueue.isEmpty()) {
            int index = counter % animalList.size();
            var direction = directionQueue.poll();
            Animal animal = animalList.get(index);
            animal.move(direction);

            counter++;

            notifyListeners();

           // System.out.println("Ruch: " + counter + " " + direction + " \n" + this.map);
        }
    }

    public void notifyListeners() {
        for (ISimulationObserver observer : observers) {
            observer.simulationChanged();
        }
    }
}
