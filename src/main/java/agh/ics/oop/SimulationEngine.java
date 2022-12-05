package agh.ics.oop;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private final ArrayList<MoveDirection> directions;
    private final AbstractWorldMap map;
    private final ArrayList<Vector2d> positions;

    SimulationEngine(ArrayList<MoveDirection> directions, AbstractWorldMap map, ArrayList<Vector2d> positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    @Override
    public void run() {
        ArrayList<Animal> animalList = new ArrayList<>();
        ArrayDeque<MoveDirection> directionQueue = new ArrayDeque<>();

        for (var position :
                positions) {
            Animal animal = new Animal(this.map, position);
            map.place(animal);
            animalList.add(animal);
        }

        for (var direction : directions) {
            directionQueue.add(direction);
        }

        int counter = 0;
        while (!directionQueue.isEmpty()) {
            int index = counter % animalList.size();
            Animal animal = animalList.get(index);
            animal.move(directionQueue.poll());
            counter++;
        }

    }
}
