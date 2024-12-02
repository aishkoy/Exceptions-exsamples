package Task1;

import Task1.exceptions.InvalidAgeException;
import Task1.exceptions.InvalidWorkAgeException;
import Task1.exceptions.InvalidYearException;
import Task1.exceptions.StringContainsNumberException;
import Task1.interfaces.Questionnaire;
import Task1.interfaces.ValidateData;

import java.util.Scanner;

public class BasicQuestionnaire implements Questionnaire, ValidateData {
    private int yearOfBirth;
    private int yearOfWork;
    private static final int CURRENT_YEAR = 2024;

    private static final Scanner sc = new Scanner(System.in);

    @Override
    public String askName() {
        while (true) {
            try {
                print("Введите ваше имя: ");
                String name = sc.nextLine().strip();
                validateName(name);
                return name;
            } catch (StringContainsNumberException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public String askSurname() {
        while (true) {
            try {
                print("Введите вашу фамилию: ");
                String surname = sc.nextLine().strip();
                validateName(surname);
                return surname;
            } catch (StringContainsNumberException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public int askYearOfBirth() {

        try {
            print("Введите ваш год рождения: ");
            String input = sc.nextLine().strip();
            this.yearOfBirth = parseAndValidYear(input, "год рождения");
            return yearOfBirth;
        } catch (NumberFormatException | InvalidYearException | InvalidAgeException e) {
            System.out.println(e.getMessage());
            return askYearOfBirth();
        }

    }

    @Override
    public int askYearOfWork() {
        try {
            print("Введите год начала работы: ");
            String input = sc.nextLine().strip();
            this.yearOfWork = parseAndValidYear(input, "год начала работы");
            return yearOfWork;
        } catch (NumberFormatException | InvalidYearException | InvalidAgeException e) {
            System.out.println(e.getMessage());
            return askYearOfWork();
        }
    }

    @Override
    public boolean isYearOfWorkValid() {
        try {
            if (this.yearOfWork - this.yearOfBirth < 18) {
                throw new InvalidWorkAgeException("Вы не могли устроиться на работу раньше 18и лет!!");
            }
            return true;
        } catch (InvalidWorkAgeException e) {
            return false;
        }
    }

    @Override
    public void validateName(String value) {
        if (value.isEmpty()) {
            throw new StringContainsNumberException("Поле не может быть пустым!");
        }
        if (!value.matches("[а-яА-Яa-zA-Z]+")) {
            throw new StringContainsNumberException(
                    "Допустимы только буквы!"
            );
        }
    }

    @Override
    public int parseAndValidYear(String year, String fieldName) {
        try {
            int yearOfBirth = Integer.parseInt(year);
            if (yearOfBirth > CURRENT_YEAR) {
                throw new InvalidYearException(String.format("Ошибка: %s не может быть из будущего!", fieldName));
            }
            if (yearOfBirth < 0) {
                throw new InvalidYearException(String.format("Ошибка: %s не может быть отрицательным!", fieldName));
            }
            if (CURRENT_YEAR - yearOfBirth >= 100) {
                throw new InvalidAgeException((String.format("Ошибка: невозможный %s", fieldName)));
            }
            return yearOfBirth;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format("Ошибка: %s должен состоять только из цифр!!", fieldName));
        }
    }


    private static void print(String str) {
        System.out.print(str);
    }
}
