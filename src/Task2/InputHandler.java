package Task2;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.OptionalDouble;
import java.util.Scanner;

public class InputHandler {
    private final static Scanner sc = new Scanner(System.in);

    public Figure askFigure() {
        try {
            String figureName = getFigureName();
            return getFigure(figureName);
        } catch (InvalidFigureNameException | ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return askFigure();
        }
    }

    private String getFigureName() {
        System.out.println("Введите номер фигуры \n" +
                "1. - параллелепипед\n" +
                "2. - сфера\n" +
                "3. - цилиндр\n" +
                "4. - конус):  ");

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

    private double validateParameters(OptionalDouble parameter) {
        if (parameter.getAsDouble() <= 0) {
            throw new NegativeValueException("Параметры фигуры могут быть только больше нуля!!");
        }
        return parameter.getAsDouble();
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
            OptionalDouble value = tryParse(sc.nextLine().strip());
            return validateParameters(value);
        } catch (NegativeValueException | IllegalArgumentException | ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return getParameter(prompt);
        }
    }

    private static OptionalDouble tryParse(String s) throws ParseException {
        try {
            NumberFormat nf = NumberFormat.getInstance();
            double value = nf.parse(s).doubleValue();
            return OptionalDouble.of(value);
        } catch (ParseException e) {
            throw new ParseException("Невозможно преобразовать " + s + "в число", e.getErrorOffset());
        }
    }
}
