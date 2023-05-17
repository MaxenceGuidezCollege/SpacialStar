package org.calma.pig.etc.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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
    private Text error;


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
    void initialize() throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {

        this.sizeSmallBtn = 30;
        this.sizeBigBtn = 50;
        this.coordinatesRepository = new InMemoryCoordinatesRepository();

        //TODO : Voir ligne 298.
        this.grid = new SpacialStarGrid(150); //150 is the recommended value.
        grid.setId("grid");

        initTextField();

        this.error.setText("");
        this.error.setVisible(false);

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

        String x1 = oneX.getText();
        String y1 = oneY.getText();

        String x2 = twoX.getText();
        String y2 = twoY.getText();

        String x3 = threeX.getText();
        String y3 = threeY.getText();

        String x4 = fourX.getText();
        String y4 = fourY.getText();

        String x5 = fiveX.getText();
        String y5 = fiveY.getText();

        Coordinates point1 = new Coordinates(Double.parseDouble(x1), Double.parseDouble(y1));
        Coordinates point2 = new Coordinates(Double.parseDouble(x2), Double.parseDouble(y2));
        Coordinates point3 = new Coordinates(Double.parseDouble(x3), Double.parseDouble(y3));
        Coordinates point4 = new Coordinates(Double.parseDouble(x4), Double.parseDouble(y4));
        Coordinates point5 = new Coordinates(Double.parseDouble(x5), Double.parseDouble(y5));

        actualCoord.add(point1);
        actualCoord.add(point2);
        actualCoord.add(point3);
        actualCoord.add(point4);
        actualCoord.add(point5);

        return actualCoord;
    }

    public boolean verifierInputs(){
        String x1 = oneX.getText();
        String y1 = oneY.getText();

        String x2 = twoX.getText();
        String y2 = twoY.getText();

        String x3 = threeX.getText();
        String y3 = threeY.getText();

        String x4 = fourX.getText();
        String y4 = fourY.getText();

        String x5 = fiveX.getText();
        String y5 = fiveY.getText();

        boolean valideVide = true;
        boolean valideNumeric = true;

        if(!isNumeric(x1) || !isNumeric(y1)
                || !isNumeric(x2) || !isNumeric(y2)
                || !isNumeric(x3) || !isNumeric(y3)
                || !isNumeric(x4) || !isNumeric(y4)
                || !isNumeric(x5) || !isNumeric(y5)){
            valideNumeric = false;
        }
        else{
            valideNumeric = true;
        }

        if(x1 == null || x1.equals("")
                || y1 == null || y1.equals("")
                || x2 == null || x2.equals("")
                || y2 == null || y2.equals("")
                || x3 == null || x3.equals("")
                || y3 == null || y3.equals("")
                || x4 == null || x4.equals("")
                || y4 == null || y4.equals("")
                || x5 == null || x5.equals("")
                || y5 == null || y5.equals("")){
            valideVide = false;
        }
        else{
            valideVide = true;
        }

        if(!valideVide){
            this.error.setText("Values cannot be null or empty !");
            this.error.setVisible(true);
            return false;
        }
        else if (!valideNumeric) {
            this.error.setText("Values must be numeric (double) !");
            this.error.setVisible(true);
            return false;
        }
        else{
            this.error.setText("");
            this.error.setVisible(false);
            return true;
        }
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void setGridToControl(SpacialStarGrid grid){
        this.grid = grid;
    }

    public SpacialStarGrid getGrid() {
        return grid;
    }

    public void drawNewStar() throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        List<Coordinates> points = new ArrayList<>();
        if(this.verifierInputs()){
            points = this.getPoints();
        }

        SpacialStar star = new SpacialStar(points);

        //TODO : La grille est null, je ne comprends pas pourquoi, pourtant je la set
        // dans SpacialGridApplication.java (ligne 24) mais malgré cela, ça ne veux pas.
        // NULL ? POURQUOI ?
        // J'ai rajouter la ligne 87 et 88 pour qu'il n'y ai plus le bugs. Mais ça ne règle pas le problème
        // CAR dans ce cas ci je créé une nouvelle grille différente de celle dans SpacialGridApplication.java (ligne 24).
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

    @FXML
    void drawNewStarBigger(MouseEvent event) throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        oneY.setText((Double.parseDouble(oneY.getText()) - 1) + "");
        twoX.setText((Double.parseDouble(twoX.getText()) + 1) + "");
        threeX.setText((Double.parseDouble(threeX.getText()) + 1) + "");
        threeY.setText((Double.parseDouble(threeY.getText()) + 1) + "");
        fourX.setText((Double.parseDouble(fourX.getText()) - 1) + "");
        fourY.setText((Double.parseDouble(fourY.getText()) + 1) + "");
        fiveX.setText((Double.parseDouble(fiveX.getText()) - 1) + "");

        drawNewStar();
    }
    @FXML
    void drawNewStarSmaller(MouseEvent event) throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        oneY.setText((Double.parseDouble(oneY.getText()) + 1) + "");
        twoX.setText((Double.parseDouble(twoX.getText()) - 1) + "");
        threeX.setText((Double.parseDouble(threeX.getText()) - 1) + "");
        threeY.setText((Double.parseDouble(threeY.getText()) - 1) + "");
        fourX.setText((Double.parseDouble(fourX.getText()) + 1) + "");
        fourY.setText((Double.parseDouble(fourY.getText()) - 1) + "");
        fiveX.setText((Double.parseDouble(fiveX.getText()) + 1) + "");

        drawNewStar();
    }
}
