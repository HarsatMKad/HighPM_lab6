package geometry2d;

import Exceptions.IncorrectValueException;

public class Circle implements Figure {
    private final double radius;

    public Circle(double rad) {
        if (rad <= 0) {
            throw new IncorrectValueException("The radius is specified incorrectly");
        }
        radius = rad;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return String.valueOf(radius);
    }

    public double getRadius(){
        return radius;
    }
}
