package Task1.interfaces;

public interface ValidateData {
    boolean isYearOfWorkValid();
    void validateName(String name);
    int parseAndValidYear(String year, String fieldName);
}
