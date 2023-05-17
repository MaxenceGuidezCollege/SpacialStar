package org.calma.pig.etc.models;

import org.calma.pig.etc.SpacialStarGrid;
import org.calma.pig.etc.exceptions.NotEnoughItemsInCoordinatesList;
import org.calma.pig.etc.exceptions.TooMuchItemsInCoordinatesList;

import java.util.List;
import java.util.Objects;

public class SpacialStar extends AbstractObject{

    private Coordinates point1;
    private Coordinates point2;
    private Coordinates point3;
    private Coordinates point4;
    private Coordinates point5;

    public SpacialStar(Coordinates point1, Coordinates point2, Coordinates point3, Coordinates point4, Coordinates point5) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
    }
    public SpacialStar(List<Coordinates> points) throws TooMuchItemsInCoordinatesList, NotEnoughItemsInCoordinatesList {
        if(points.size() > 5){
            throw new TooMuchItemsInCoordinatesList();
        }
        else if(points.size() < 5){
            throw new NotEnoughItemsInCoordinatesList();
        }

        this.point1 = points.get(0);
        this.point2 = points.get(1);
        this.point3 = points.get(2);
        this.point4 = points.get(3);
        this.point5 = points.get(4);
    }

    public Coordinates getPoint1() {
        return point1;
    }
    public void setPoint1(Coordinates point1) {
        this.point1 = point1;
    }

    public Coordinates getPoint2() {
        return point2;
    }
    public void setPoint2(Coordinates point2) {
        this.point2 = point2;
    }

    public Coordinates getPoint3() {
        return point3;
    }
    public void setPoint3(Coordinates point3) {
        this.point3 = point3;
    }

    public Coordinates getPoint4() {
        return point4;
    }
    public void setPoint4(Coordinates point4) {
        this.point4 = point4;
    }

    public Coordinates getPoint5() {
        return point5;
    }
    public void setPoint5(Coordinates point5) {
        this.point5 = point5;
    }

    @Override
    public String toString() {
        return "SpacialStar{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", point3=" + point3 +
                ", point4=" + point4 +
                ", point5=" + point5 +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpacialStar star = (SpacialStar) o;
        return Objects.equals(point1, star.point1) && Objects.equals(point2, star.point2) && Objects.equals(point3, star.point3) && Objects.equals(point4, star.point4) && Objects.equals(point5, star.point5);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), point1, point2, point3, point4, point5);
    }
}
