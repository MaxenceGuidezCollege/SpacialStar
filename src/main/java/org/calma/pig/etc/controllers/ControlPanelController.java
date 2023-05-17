package org.calma.pig.etc.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.calma.pig.etc.SpacialStarGrid;
import org.calma.pig.etc.exceptions.NotEnoughItemsInCoordinatesList;
import org.calma.pig.etc.exceptions.TooMuchItemsInCoordinatesList;
import org.calma.pig.etc.models.Coordinates;
import org.calma.pig.etc.models.SpacialStar;
import org.calma.pig.etc.repositories.coordinates.ICoordinatesRepository;
import org.calma.pig.etc.repositories.coordinates.InMemoryCoordinatesRepository;

import java.util.ArrayList;
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
    private VBox topPart;
    private int sizeSmallBtn;
    private int sizeBigBtn;
    private ICoordinatesRepository coordinatesRepository;
    private SpacialStarGrid grid;

    @FXML
    void initialize() {

        this.sizeSmallBtn = 30;
        this.sizeBigBtn = 50;
        this.coordinatesRepository = new InMemoryCoordinatesRepository();

        initTextField();

        makeButtons();
        placeButtons();

        topPart.widthProperty().addListener(evt -> {
            placeButtons();
        });
        topPart.heightProperty().addListener(evt -> {
            placeButtons();
        });
    }

    public void initTextField(){
        List<Coordinates> initCoordinates = coordinatesRepository.findAll();

        oneX.setText(initCoordinates.get(0).getX() + "");
        oneY.setText(initCoordinates.get(0).getY() + "");

        twoX.setText(initCoordinates.get(1).getX() + "");
        twoY.setText(initCoordinates.get(1).getY() + "");

        threeX.setText(initCoordinates.get(2).getX() + "");
        threeY.setText(initCoordinates.get(2).getY() + "");

        fourX.setText(initCoordinates.get(3).getX() + "");
        fourY.setText(initCoordinates.get(3).getY() + "");

        fiveX.setText(initCoordinates.get(4).getX() + "");
        fiveY.setText(initCoordinates.get(4).getY() + "");
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
        double colWidth = topPart.getWidth()/4;

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

    public List<Coordinates> getPoints(){
        List<Coordinates> actualCoord = new ArrayList<>();

        Coordinates point1 = new Coordinates(Double.parseDouble(oneX.getText()), Double.parseDouble(oneY.getText()));
        Coordinates point2 = new Coordinates(Double.parseDouble(twoX.getText()), Double.parseDouble(twoY.getText()));
        Coordinates point3 = new Coordinates(Double.parseDouble(threeX.getText()), Double.parseDouble(threeY.getText()));
        Coordinates point4 = new Coordinates(Double.parseDouble(fourX.getText()), Double.parseDouble(fourY.getText()));
        Coordinates point5 = new Coordinates(Double.parseDouble(fiveX.getText()), Double.parseDouble(fiveY.getText()));

        actualCoord.add(point1);
        actualCoord.add(point2);
        actualCoord.add(point3);
        actualCoord.add(point4);
        actualCoord.add(point5);

        return actualCoord;
    }

    public void setGridToControl(SpacialStarGrid grid){
        this.grid = grid;
    }

    public SpacialStarGrid getGrid() {
        return grid;
    }

    public void drawNewStar() throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        SpacialStar star = new SpacialStar(this.getPoints());
        grid.setStar(star);
        grid.drawStar();
    }

    @FXML
    void drawNewStarTop(MouseEvent event) throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        oneY.setText((Double.parseDouble(oneY.getText()) - 1) + "");
        twoY.setText((Double.parseDouble(twoY.getText()) - 1) + "");
        threeY.setText((Double.parseDouble(threeY.getText()) - 1) + "");
        fourY.setText((Double.parseDouble(fourY.getText()) - 1) + "");
        fiveY.setText((Double.parseDouble(fiveY.getText()) - 1) + "");

        drawNewStar();
    }
    @FXML
    void drawNewStarBottom(MouseEvent event) throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        oneY.setText((Double.parseDouble(oneY.getText()) + 1) + "");
        twoY.setText((Double.parseDouble(twoY.getText()) + 1) + "");
        threeY.setText((Double.parseDouble(threeY.getText()) + 1) + "");
        fourY.setText((Double.parseDouble(fourY.getText()) + 1) + "");
        fiveY.setText((Double.parseDouble(fiveY.getText()) + 1) + "");

        drawNewStar();
    }
    @FXML
    void drawNewStarLeft(MouseEvent event) throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        oneX.setText((Double.parseDouble(oneX.getText()) - 1) + "");
        twoX.setText((Double.parseDouble(twoX.getText()) - 1) + "");
        threeX.setText((Double.parseDouble(threeX.getText()) - 1) + "");
        fourX.setText((Double.parseDouble(fourX.getText()) - 1) + "");
        fiveX.setText((Double.parseDouble(fiveX.getText()) - 1) + "");

        drawNewStar();
    }
    @FXML
    void drawNewStarRight(MouseEvent event) throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        oneX.setText((Double.parseDouble(oneX.getText()) + 1) + "");
        twoX.setText((Double.parseDouble(twoX.getText()) + 1) + "");
        threeX.setText((Double.parseDouble(threeX.getText()) + 1) + "");
        fourX.setText((Double.parseDouble(fourX.getText()) + 1) + "");
        fiveX.setText((Double.parseDouble(fiveX.getText()) + 1) + "");

        drawNewStar();
    }

}
