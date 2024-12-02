package Task2;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        System.out.println("Добро пожаловать в калькулятор для расчета площади и объема фигур!");

        while(true) {
            try{
                Figure figure = inputHandler.askFigure();

                if(figure != null) {
                    System.out.printf("Площадь фигуры: %.2f%n", figure.getArea());
                    System.out.printf("Объем фигуры: %.2f%n", figure.getVolume());
                    return;
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка");
                e.printStackTrace();
                System.out.println("Попробуйте снова\n\n\n\n\n");
            }
        }
    }
}
