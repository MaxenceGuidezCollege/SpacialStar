package org.calma.pig.etc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.calma.pig.etc.controllers.ControlPanelController;

public class SpacialStarApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox controlPanel = (VBox) FXMLLoader.load(getClass().getResource("fxml/controlPanel.fxml"));

        SpacialStarGrid grid = new SpacialStarGrid(150); //150 is the recommended value.
        grid.setId("grid");

        ControlPanelController controller = new ControlPanelController();
        controller.setGridToControl(grid);

        SplitPane root = new SplitPane(controlPanel, grid);
        root.setId("root");
        root.setOrientation(Orientation.VERTICAL);
        root.setPadding(new Insets(0));

        Scene scene = new Scene(root, 960, 540);
        stage.getIcons().add(new Image("org/calma/pig/etc/images/logo.png"));
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(500);
        stage.setMinHeight(400);
        stage.setTitle("Étoile spaciale");
        stage.show();
    }
}
