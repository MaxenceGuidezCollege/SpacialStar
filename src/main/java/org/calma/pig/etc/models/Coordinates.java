package org.calma.pig.etc.models;

import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Coordinates extends AbstractObject {

    private SimpleDoubleProperty x;
    private SimpleDoubleProperty y;

    public Coordinates(double x, double y) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }
    public Coordinates(int id, String name, double x, double y) {
        super(id, name);
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }

    public double getX() {
        return x.get();
    }
    public SimpleDoubleProperty getXProperty() {
        return x;
    }
    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }
    public SimpleDoubleProperty getYProperty() {
        return y;
    }
    public void setY(double y) {
        this.y.set(y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), x, y);
    }
}
