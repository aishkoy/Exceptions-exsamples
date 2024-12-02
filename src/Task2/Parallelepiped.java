package Task2;

public class Parallelepiped extends Figure {
    private final double height;
    private final double width;
    private final double length;

    public Parallelepiped(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    @Override
    public double getVolume() {
        return height * width * length;
    }

    @Override
    public double getArea() {
        return 2 * ((height * width) + (length * height) + (length * width));
    }
}
