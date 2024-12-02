package Task2;


import java.text.ParseException;
import java.util.Scanner;

public class InputHandler {
    private final static Scanner sc = new Scanner(System.in);

    public Figure askFigure() {
        try {
            String figureName = getFigureName();
            return getFigure(figureName);
        } catch (InvalidFigureNameException | ParseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return askFigure();
        }
    }

    private String getFigureName() {
        System.out.println("""
                Введите номер фигуры\s
                1. - параллелепипед
                2. - сфера
                3. - цилиндр
                4. - конус): \s""");

        String figureNum = sc.nextLine().strip();

        if(figureNum.isBlank() || !figureNum.matches("[1-4]")) {
            throw new InvalidFigureNameException("Неверный номер фигуры!");
        }
        return figureNum;
    }

    private Figure getFigure(String figureName) throws ParseException {
        System.out.println("Введите параметры: ");

        switch (figureName) {
            case "1":
                return getParallelepiped();
            case "2":
                return getSphere();
            case "3":
                return getCylinder();
            case "4":
                return getCone();
            default:
                throw new InvalidFigureNameException("Неверный номер фигуры!!");
        }
    }

    private double validateParameters(double parameter) {
        if (parameter <= 0) {
            throw new IllegalArgumentException("Параметры фигуры могут быть только больше нуля!!");
        }
        return parameter;
    }

    private Figure getParallelepiped() throws ParseException {
        double height = getParameter("Введите высоту: ");
        double width = getParameter("Введите ширину: ");
        double length = getParameter("Введите длину: ");
        return new Parallelepiped(height, width, length);
    }

    private Figure getSphere() throws ParseException{
        double radius = getParameter("Введите радиус: ");
        return new Sphere(radius);
    }

    private Figure getCylinder() throws ParseException {
        double radius = getParameter("Введите радиус: ");
        double height = getParameter("Введите высоту: ");
        return new Cylinder(radius, height);
    }

    private Figure getCone() throws ParseException {
        double radius = getParameter("Введите радиус: ");
        double height = getParameter("Введите высоту: ");
        return new Cone(radius, height);
    }

    private double getParameter(String prompt) throws ParseException {
        try{
            System.out.print(prompt);
            double value = tryParse(sc.nextLine().strip());
            return validateParameters(value);
        } catch ( IllegalArgumentException | ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return getParameter(prompt);
        }
    }

    private static double tryParse(String s) throws ParseException {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Невозможно преобразовать " + s + "в число");
        }
    }
}
