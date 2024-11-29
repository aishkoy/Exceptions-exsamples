package Task1;

public class Worker {
    private String name;
    private String surname;
    private int yearOfBirth;
    private int yearOfWork;
    private static final int CURRENT_YEAR = 2024;

    public Worker(String name, String surname, int yearOfBirth, int yearOfWork) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.yearOfWork = yearOfWork;

    }

    public int calculateWorkExperience() {
        return CURRENT_YEAR - yearOfWork;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setYearOfWork(int yearOfWork) {
        this.yearOfWork = yearOfWork;
    }

    @Override
    public String toString(){
        return  String.format(
                "Сотрудник: %s %s\nГод рождения: %d\nГод начала работы: %d\nОбщий стаж: %d лет",
                name, surname, yearOfBirth, yearOfWork, calculateWorkExperience()
        );
    }
}
