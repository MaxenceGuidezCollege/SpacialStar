package org.calma.pig.etc.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.calma.pig.etc.models.coordinates.Coordinates;
import org.calma.pig.etc.repositories.coordinates.ICoordinatesRepository;
import org.calma.pig.etc.repositories.coordinates.InMemoryCoordinatesRepository;

import java.util.List;

public class ControlPanelController {
    @FXML
    private TextField oneX;
    @FXML
    private TextField oneY;

    @FXML
    private TextField twoX;
    @FXML
    private TextField twoY;

    @FXML
    private TextField threeX;
    @FXML
    private TextField threeY;

    @FXML
    private TextField fourX;
    @FXML
    private TextField fourY;

    @FXML
    private TextField fiveX;
    @FXML
    private TextField fiveY;

    @FXML
    private Button rotRight;
    @FXML
    private Button rotLeft;


    @FXML
    private Button xDown;
    @FXML
    private Button xUp;
    @FXML
    private Button yDown;
    @FXML
    private Button yUp;

    @FXML
    private Button homUp;
    @FXML
    private Button homDown;

    @FXML
    private VBox root;
    private int sizeSmallBtn;
    private int sizeBigBtn;
    private ICoordinatesRepository coordinatesRepository;

    @FXML
    void initialize() throws Exception {

        this.sizeSmallBtn = 30;
        this.sizeBigBtn = 50;
        this.coordinatesRepository = new InMemoryCoordinatesRepository();

        initTextField();

        makeButtons();
        placeButtons();

        root.widthProperty().addListener(evt -> {
            placeButtons();
        });
        root.heightProperty().addListener(evt -> {
            placeButtons();
        });
    }

    public void initTextField(){
        List<Coordinates> allCoordinates = coordinatesRepository.findAll();

        oneX.setText(allCoordinates.get(0).getX() + "");
        oneY.setText(allCoordinates.get(0).getY() + "");

        twoX.setText(allCoordinates.get(1).getX() + "");
        twoY.setText(allCoordinates.get(1).getY() + "");

        threeX.setText(allCoordinates.get(2).getX() + "");
        threeY.setText(allCoordinates.get(2).getY() + "");

        fourX.setText(allCoordinates.get(3).getX() + "");
        fourY.setText(allCoordinates.get(3).getY() + "");

        fiveX.setText(allCoordinates.get(4).getX() + "");
        fiveY.setText(allCoordinates.get(4).getY() + "");
    }

    public void makeButtons(){
        setImageInBtn("org/calma/pig/etc/images/rotateRight.png", rotRight, true);
        setImageInBtn("org/calma/pig/etc/images/rotateLeft.png", rotLeft, true);
        setImageInBtn("org/calma/pig/etc/images/arrow.png", yUp, false);
        setImageInBtn("org/calma/pig/etc/images/arrow.png", yDown, false);
        setImageInBtn("org/calma/pig/etc/images/arrow.png", xUp, false);
        setImageInBtn("org/calma/pig/etc/images/arrow.png", xDown, false);
        setImageInBtn("org/calma/pig/etc/images/zoom_out.png", homDown, true);
        setImageInBtn("org/calma/pig/etc/images/zoom_in.png", homUp, true);

        yUp.getGraphic().setRotate(180);
        xUp.getGraphic().setRotate(-90);
        xDown.getGraphic().setRotate(90);
    }

    private void setImageInBtn(String src, Button btn, boolean isBigBtn){
        Image img = new Image(src);
        ImageView iV = new ImageView(img);

        if(isBigBtn){
            iV.setFitWidth(sizeBigBtn - 5);
            iV.setFitHeight(sizeBigBtn - 5);
        }
        else{
            iV.setFitWidth(sizeSmallBtn - 5);
            iV.setFitHeight(sizeSmallBtn - 5);
        }

        btn.setGraphic(iV);
        btn.setPadding(new Insets(0));
    }

    public void placeButtons(){
        double colWidth = root.getWidth()/4;

        rotRight.setLayoutY(10);
        rotRight.setLayoutX(colWidth/2 - sizeBigBtn/2.0);
        rotLeft.setLayoutY(sizeBigBtn + 20);
        rotLeft.setLayoutX(colWidth/2 - sizeBigBtn/2.0);

        yUp.setLayoutY(10);
        yUp.setLayoutX(colWidth/2 - sizeSmallBtn/2.0);
        yDown.setLayoutY(sizeSmallBtn * 2 + 30);
        yDown.setLayoutX(colWidth/2 - sizeSmallBtn/2.0);
        xUp.setLayoutY(sizeSmallBtn + 20);
        xUp.setLayoutX(colWidth/2 + sizeSmallBtn/2.0 + 10);
        xDown.setLayoutY(sizeSmallBtn + 20);
        xDown.setLayoutX(colWidth/2 - sizeSmallBtn * 1.5 - 10);

        homUp.setLayoutY(10);
        homUp.setLayoutX(colWidth/2 - sizeBigBtn/2.0);
        homDown.setLayoutY(sizeBigBtn + 20);
        homDown.setLayoutX(colWidth/2 - sizeBigBtn/2.0);
    }
}
