package agh.ics.oop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class World {
    public static void main(String[] args) {
        /*
            duplikacje zwierząt można sprawdzić za pomocą np. HashSetu, gdzie kluczem byłyby współrzędne czyli wektor.
            Jeżeli klucz istnieje w zbiorze, to znaczy, że jest tam zwierzę.
         */
        Animal animal = new Animal();
        System.out.println(animal);

        List<MoveDirection> directions = OptionsParser.parse(args);

        for (MoveDirection direction :
                directions) {
            run(animal, direction);
        }
        System.out.println(animal);
    }

    public static void run(Animal animal, MoveDirection direction) {
        String message = switch (direction) {
            case FORWARD -> "idzie do przodu";
            case BACKWARD -> "idzie do tyłu";
            case RIGHT -> "obraca się w prawo";
            case LEFT -> "obraca się w lewo";
        };

        animal.move(direction);
        String line = String.format("Zwierze %s.", message);
        System.out.println(line);
    }
}
