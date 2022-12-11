package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class App extends Application {
    AbstractWorldMap map;

    @Override
    public void init() throws Exception {
        super.init();

        var args = getParameters().getRaw();
        var argsArray = args.toArray(new String[0]);

        ArrayList<MoveDirection> directions = new OptionsParser().parse(argsArray);

        this.map = new GrassField(10);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));

        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid, 400, 400);

        var lowerLeft = this.map.getLowerLeft();
        var upperRight = this.map.getUpperRight();

        int[] yRange = IntStream.rangeClosed(lowerLeft.y, upperRight.y).toArray();
        int[] xRange = IntStream.rangeClosed(lowerLeft.x, upperRight.x).toArray();

        int x = 0;
        int y = yRange.length;

        grid.add(new Label("y/x"), 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(20));
        grid.getRowConstraints().add(new RowConstraints(20));

        int i = 0;
        while (true) {
            Label label;

            if (i < yRange.length) {
                label = new Label("%d".formatted(yRange[i]));

                grid.add(label, 0, y--);

                grid.getRowConstraints().add(new RowConstraints(20));
            }

            if (i < xRange.length) {
                label = new Label("%d".formatted(xRange[i]));

                grid.add(label, 1 + x++, 0);

                grid.getColumnConstraints().add(new ColumnConstraints(20));
            }

            if (i >= yRange.length - 1 && i >= xRange.length - 1)
                break;

            i++;
        }

        for (x = lowerLeft.x; x <= upperRight.x; x++) {
            for (y = upperRight.y; y >= lowerLeft.y; y--) {
                var currentMapPosition = new Vector2d(x, y);
                if (this.map.isOccupied(currentMapPosition)) {

                    Object gameElement = this.map.objectAt(currentMapPosition);
                    grid.add(new Label(gameElement.toString()), x - lowerLeft.x + 1, upperRight.y - y + 1);
                    System.out.println("Mapa: %d, %d;".formatted(currentMapPosition.x, currentMapPosition.y));
                }
            }
        }

        for (Node n : grid.getChildren()) {
            GridPane.setHalignment(n, HPos.CENTER);
            GridPane.setValignment(n, VPos.CENTER);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
