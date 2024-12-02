package Task2;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.OptionalDouble;
import java.util.Scanner;

public class InputHandler {
    private final static Scanner sc = new Scanner(System.in);
    private final static String[] VALID_FIGURES = {"параллелепипед", "сфера", "цилиндр", "конус"};


    public Figure askFigure() {
        try {
            String figureName = getFigureName();
            return getFigure(figureName);
        } catch (ParseException e) {
            System.out.println("Ошибка при парсинге числа: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String getFigureName() {
        System.out.println("Введите название фигуры (параллелепипед/сфера/цилиндр/конус):  ");
        String figureName = sc.nextLine().strip().toLowerCase();

        for (String figure : VALID_FIGURES) {
            if (figure.equals(figureName)) {
                return figureName;
            }
        }

        throw new InvalidFigureNameException("Неверное название фигуры: " + figureName);
    }

    private Figure getFigure(String figureName) throws ParseException {
        if (figureName == null) {
            return null;
        }

        System.out.println("Введите параметры: ");

        switch (figureName) {
            case "параллелепипед":
                OptionalDouble[] params = getParametersForParallelepiped();
                validateParameters(params);
                return new Parallelepiped(params[0].getAsDouble(), params[1].getAsDouble(), params[2].getAsDouble());
            case "сфера":
                OptionalDouble radius = getParametersForSphere();
                validateParameters(new OptionalDouble[]{radius});
                return new Sphere(radius.getAsDouble());
            case "цилиндр":
                OptionalDouble[] cylinderParams = getParametersForCylinderOrCone();
                validateParameters(cylinderParams);
                return new Cylinder(cylinderParams[0].getAsDouble(), cylinderParams[1].getAsDouble());
            case "конус":
                OptionalDouble[] coneParameters = getParametersForCylinderOrCone();
                validateParameters(coneParameters);
                return new Cone(coneParameters[0].getAsDouble(), coneParameters[1].getAsDouble());

            default:
                throw new InvalidFigureNameException("Неверное название фигуры: " + figureName);
        }
    }

    private void validateParameters(OptionalDouble[] parameters) {
        for (OptionalDouble parameter : parameters) {
            if (parameter.isEmpty()) {
                throw new IllegalArgumentException("Введено некорректное число!!");
            }
            if (parameter.getAsDouble() <= 0) {
                throw new IllegalArgumentException("Параметры фигуры могут быть только положительными!!");
            }
        }
    }

    private OptionalDouble[] getParametersForParallelepiped() throws ParseException {
        System.out.print("Введите высоту: ");
        OptionalDouble height = tryParse(sc.nextLine().strip());
        System.out.print("Введите длину: ");
        OptionalDouble a = tryParse(sc.nextLine().strip());
        System.out.print("Введите ширину: ");
        OptionalDouble b = tryParse(sc.nextLine().strip());
        return new OptionalDouble[]{height, a, b};
    }

    private OptionalDouble[] getParametersForCylinderOrCone() throws ParseException {
        System.out.print("Введите радиус: ");
        OptionalDouble radius = tryParse(sc.nextLine().strip());
        System.out.print("Введите высоту: ");
        OptionalDouble height = tryParse(sc.nextLine().strip());
        return new OptionalDouble[]{radius, height};
    }

    private OptionalDouble getParametersForSphere() throws ParseException {
        System.out.print("Введите радиус: ");
        return tryParse(sc.nextLine().strip());
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
