package Task2;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        System.out.println("Добро пожаловать в калькулятор для расчета площади и объема фигур!");

        Figure figure = inputHandler.askFigure();
        System.out.printf("Площадь фигуры: %.2f%n", figure.getArea());
        System.out.printf("Объем фигуры: %.2f%n", figure.getVolume());
    }
}
