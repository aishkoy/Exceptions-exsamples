package Task2;

public class Sphere extends Circle {
    public Sphere(double radius) {
        super(radius);
    }


    @Override
    public double getVolume() {
        return 4/3.0 * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public double getArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }
}
