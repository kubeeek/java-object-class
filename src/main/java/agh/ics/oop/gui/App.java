package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class App extends Application implements ISimulationObserver {
    private final int cellHeight = 50;
    private final int cellWidth = 50;

    AbstractWorldMap map;

    private GridPane grid;
    private Stage primaryStage;
    private ThreadedSimulationEngine threadedSimulationEngine;
    private ArrayList<Vector2d> positions;

    @Override
    public void init() throws Exception {
        super.init();
        this.grid = new GridPane();

        this.positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        this.map = new GrassField(10);

        this.threadedSimulationEngine = new ThreadedSimulationEngine(this.map, this.positions);
        this.threadedSimulationEngine.addObserver(this);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        HBox userInterface = createUserInterface();
        VBox appInterface = new VBox(userInterface, this.grid);
        this.primaryStage = primaryStage;

        System.out.println("start");

        primaryStage.setScene(new Scene(appInterface, 1200, 800));
        primaryStage.show();

    }

    private HBox createUserInterface() {
        TextField inputField = new TextField();
        Button startButton = new Button("Start");
        HBox userInterface = new HBox(inputField, startButton);

        startButton.setOnAction(click -> {
            ArrayList<MoveDirection> moveDirections = new OptionsParser().parse(inputField.getText().split(" "));
            this.threadedSimulationEngine.setDirections(moveDirections);

            Thread simulationEngineThread = new Thread(this.threadedSimulationEngine);
            simulationEngineThread.start();
        });

        return userInterface;
    }

    public void draw() throws FileNotFoundException {

        var lowerLeft = this.map.getLowerLeft();
        var upperRight = this.map.getUpperRight();

        grid.getChildren().clear();

        drawGrid(lowerLeft, upperRight);
        drawAnimals(lowerLeft, upperRight);
        alignGrid();
    }

    private void drawAnimals(Vector2d lowerLeft, Vector2d upperRight) throws FileNotFoundException {
        int x, y;

        for (x = lowerLeft.x; x <= upperRight.x; x++) {
            for (y = upperRight.y; y >= lowerLeft.y; y--) {
                var currentMapPosition = new Vector2d(x, y);
                if (this.map.isOccupied(currentMapPosition)) {

                    AbstractWorldMapElement mapElement = this.map.objectAt(currentMapPosition);
                    grid.add(new GuiElementBox(mapElement).vBox, x - lowerLeft.x + 1, upperRight.y - y + 1);
                }
            }
        }
    }

    private void drawGrid(Vector2d lowerLeft, Vector2d upperRight) {
        grid.add(new Label("y/x"), 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        grid.getRowConstraints().add(new RowConstraints(cellHeight));

        int[] yRange = IntStream.rangeClosed(lowerLeft.y, upperRight.y).toArray();
        int[] xRange = IntStream.rangeClosed(lowerLeft.x, upperRight.x).toArray();

        int x = 0;
        int y = yRange.length;

        int i = 0;
        while (true) {
            Label label;

            if (i < yRange.length) {
                label = new Label("%d".formatted(yRange[i]));

                grid.add(label, 0, y--);

                grid.getRowConstraints().add(new RowConstraints(cellHeight));
            }

            if (i < xRange.length) {
                label = new Label("%d".formatted(xRange[i]));

                grid.add(label, 1 + x++, 0);

                grid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            }

            if (i >= yRange.length - 1 && i >= xRange.length - 1)
                break;

            i++;
        }
    }

    private void alignGrid() {
        for (Node n : grid.getChildren()) {
            GridPane.setHalignment(n, HPos.CENTER);
            GridPane.setValignment(n, VPos.CENTER);
        }
    }

    @Override
    public void simulationChanged() {

        Platform.runLater(() -> {
            try {
                draw();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
}
