package org.calma.pig.etc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.calma.pig.etc.exceptions.NotEnoughItemsInCoordinatesList;
import org.calma.pig.etc.exceptions.TooMuchItemsInCoordinatesList;
import org.calma.pig.etc.models.Coordinates;
import org.calma.pig.etc.models.SpacialStar;
import org.calma.pig.etc.repositories.coordinates.ICoordinatesRepository;
import org.calma.pig.etc.repositories.coordinates.InMemoryCoordinatesRepository;

import java.util.List;

public class SpacialStarGrid extends Grid{

    private SpacialStar star;

    public SpacialStarGrid(int columns) throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        super(columns);

        ICoordinatesRepository coordinatesRepository = new InMemoryCoordinatesRepository();
        List<Coordinates> points = coordinatesRepository.findAll();

        this.star = new SpacialStar(points);

        drawStar();

        this.widthProperty().addListener(evt -> {
            drawStar();
        });
        this.heightProperty().addListener(evt -> {
            drawStar();
        });
    }
    public SpacialStarGrid(int columns, SpacialStar star) {
        super(columns);

        this.star = star;

        drawStar();

        this.widthProperty().addListener(evt -> {
            drawStar();
        });
        this.heightProperty().addListener(evt -> {
            drawStar();
        });
    }

    public SpacialStar getStar() {
        return star;
    }
    public void setStar(SpacialStar star) {
        this.star = star;
    }

    public void drawStar(){
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        double cellSize = this.cellSize;

        Coordinates point1 = star.getPoint1();
        Coordinates point2 = star.getPoint2();
        Coordinates point3 = star.getPoint3();
        Coordinates point4 = star.getPoint4();
        Coordinates point5 = star.getPoint5();

        gc.setLineWidth(5);
        gc.setStroke(Color.BLACK);

        gc.strokeLine(point1.getX() * cellSize,
                point1.getY() * cellSize,
                point2.getX() * cellSize,
                point2.getY() * cellSize);

        gc.strokeLine(point2.getX() * cellSize,
                point2.getY() * cellSize,
                point3.getX() * cellSize,
                point3.getY() * cellSize);

        gc.strokeLine(point3.getX() * cellSize,
                point3.getY() * cellSize,
                point4.getX() * cellSize,
                point4.getY() * cellSize);

        gc.strokeLine(point4.getX() * cellSize,
                point4.getY() * cellSize,
                point5.getX() * cellSize,
                point5.getY() * cellSize);

        gc.strokeLine(point5.getX() * cellSize,
                point5.getY() * cellSize,
                point1.getX() * cellSize,
                point1.getY() * cellSize);

    }
}
