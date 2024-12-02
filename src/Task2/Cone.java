package Task2;

public class Cone extends Circle{
    private final double height;
    private final double slantHeight;

    public Cone(double radius, double height) {
        super(radius);
        this.height = height;
        this.slantHeight = getSlantHeight();
    }

    @Override
    public double getVolume() {
        return 1/3.0 * Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * (radius * slantHeight);
    }

    private double getSlantHeight(){
        return Math.sqrt(Math.pow(height, 2) + Math.pow(radius, 2));
    }


}
