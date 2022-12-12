package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox {
    private final Image image;
    private final ImageView imageView;
    private final Label label;
    public final VBox vBox;

    public GuiElementBox(IMapElement mapElement) throws FileNotFoundException {
        getClass().getResourceAsStream(mapElement.toImagePath());
        this.image = new Image(new FileInputStream(mapElement.toImagePath()));
        this.imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        if(mapElement instanceof Animal)
            this.label = new Label("Z(%d, %d)".formatted(mapElement.getPosition().x, mapElement.getPosition().y));
        else
            this.label = new Label("Trawa");

        this.vBox = new VBox(this.imageView, this.label);
        this.vBox.setAlignment(Pos.CENTER);

    }
}
