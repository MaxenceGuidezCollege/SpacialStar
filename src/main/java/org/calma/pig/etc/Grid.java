package org.calma.pig.etc;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Grid extends Pane {

    protected Canvas canvas;
    private int rows;
    private int columns;
    private double cellSize;
    private Color lineColor;

    public Grid(int columns) {
        this.columns = columns;
        this.lineColor = Color.LIGHTGRAY.brighter();

        this.canvas = new Canvas();
        this.getChildren().add(this.canvas);
        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.heightProperty().bind(this.heightProperty());

        this.cellSize = findCellSize();
        this.rows = 200;
//        this.rows = findNumberRows();
        drawGrid();

        this.widthProperty().addListener(evt -> {
            this.cellSize = findCellSize();
//            this.rows = findNumberRows();
            drawGrid();
        });
        this.heightProperty().addListener(evt -> {
            this.cellSize = findCellSize();
//            this.rows = findNumberRows();
            drawGrid();
        });
    }

    private void drawGrid() {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        for (int col = 0; col < rows; col++) {
            for (int row = 0; row < columns; row++) {
                double x = col * cellSize;
                double y = row * cellSize;

//                DRAW HORIZONTAL LINES
                gc.setLineWidth(0.5);
                gc.setStroke(lineColor);
                gc.strokeLine(0.5, x + 0.5, canvasWidth + 0.5, x + 0.5);

//                DRAW VERTICAL LINES
                gc.setLineWidth(0.5);
                gc.setStroke(lineColor);
                gc.strokeLine(y + 0.5, 0.5, y + 0.5, canvasHeight + 0.5);
            }
        }
    }

    private double findCellSize(){
        return canvas.getWidth()/columns;
    }

    private int findNumberRows(){
        return (int)(cellSize * canvas.getHeight());
    }
}
