package org.calma.pig.etc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SpacialStarApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane controlPanel = (GridPane) FXMLLoader
                .load(getClass().getResource("fxml/controlPanel.fxml"));

        Grid grid = new Grid(150); //150 is the recommended value.

        SplitPane root = new SplitPane(controlPanel, grid);
        root.setOrientation(Orientation.VERTICAL);

        Scene scene = new Scene(root, 960, 540);
        stage.getIcons().add(new Image("org/calma/pig/etc/images/logo.png"));
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Étoile spaciale");
        stage.show();
    }
}
