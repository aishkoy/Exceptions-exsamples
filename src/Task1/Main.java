package Task1;

public class Main {
    public static void main(String[] args) {
        BasicQuestionnaire bq = new BasicQuestionnaire();
        String name = bq.askName();
        String surname = bq.askSurname();
        int yearOfBirth = bq.askYearOfBirth();
        int yearOfWork = bq.askYearOfWork();

        while (!bq.isYearOfWorkValid()) {
            System.out.println("Ввод года начала работы не может быть раньше, чем вам исполнилось 18. Введите корректные данные!");
            yearOfBirth = bq.askYearOfBirth();
            yearOfWork = bq.askYearOfWork();
        }

        Worker worker = new Worker(name, surname, yearOfBirth, yearOfWork);
        System.out.println("\nРезультат:");
        System.out.println(worker);
    }
}