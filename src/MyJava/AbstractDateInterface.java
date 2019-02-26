package MyJava;

public interface AbstractDateInterface {

    public int getDay();
    public int getMonth();
    public int getYear();
    public int dayOfWeek();


    public static int dateTimeCmp(AbstractDateInterface a, AbstractDateInterface b) {
        var result = 0;
        result += a.toUInt64() - b.toUInt64();

        return result;
    }

    public boolean validate();

    public void addDays(int days);
    public void  addMonths(int months);
    public void addYears(int years);



    public AbstractDateInterface getNow();
    public AbstractDateInterface parse(String date);

    public String toStringDate();

    public long toUInt64();
}
