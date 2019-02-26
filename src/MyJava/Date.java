package MyJava;

public interface Date {
    int dateTimeCmp(Date a, Date b);
    boolean validate();
    void addDays(int days);
    void  addMonths(int months);
    void addYears(int years);
    int getDay();
    int getMonth();
    int getYear();
    int dayOfWeek();
    Date getNow();
    Date Parser(String date);
    String toStringDate();
    long ToUInt64();

}
