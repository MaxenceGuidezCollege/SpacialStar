package org.calma.pig.etc;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Grid extends Pane {

    protected Canvas canvas;
    private int rows;
    private int columns;
    private int cellSize;

    public Grid(int columns, int rows, int cellSize) {
        this.columns = columns;
        this.rows = rows;
        this.cellSize = cellSize;

        this.canvas = new Canvas();
        this.getChildren().add(this.canvas);

        this.drawGrid();

        this.canvas.widthProperty().bind(widthProperty());
        this.canvas.heightProperty().bind(heightProperty());

        this.widthProperty().addListener(evt -> drawGrid());
        this.heightProperty().addListener(evt -> drawGrid());
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
                gc.setLineWidth(1.0);
                gc.setStroke(Color.BLACK);
                gc.strokeLine(0, x + 0.5, canvasWidth, x + 0.5);

//                DRAW VERTICAL LINES
                gc.setLineWidth(1.0);
                gc.setStroke(Color.BLACK);
                gc.strokeLine(y + 0.5, 0, y + 0.5, canvasHeight);
            }
        }
    }
}
