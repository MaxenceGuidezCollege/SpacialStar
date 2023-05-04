package org.calma.pig.etc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SpacialStarApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 960, 540);
        stage.getIcons().add(new Image("org/calma/pig/etc/images/logo.png"));
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Étoile spaciale");
        stage.show();
    }
}
