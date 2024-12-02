package Task2;

public class Cylinder extends Circle {
    private final double height;

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    @Override
    public double getVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public double getArea() {
        return 2 * Math.PI * radius * (radius + height);
    }
}
