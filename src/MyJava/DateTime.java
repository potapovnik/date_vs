package MyJava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateTime implements AbstractDateInterface {
    private int sec;
    private int month;
    private int year;

    private int[] dayOfMonth =
            {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int SECONDS_IN_DAY = 86400;
    private int MONTH_IN_YEAR = 12;


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void addDays(int days) {
        var seconds = days * SECONDS_IN_DAY;
        sec += seconds;

        while(sec > SECONDS_IN_DAY * dayInMonth()) {
            sec -= SECONDS_IN_DAY * dayInMonth();
            addMonths(1);
        }
    }

    @Override
    public void addMonths(int months) {
        month += months;
        year += month / MONTH_IN_YEAR;
        month %= MONTH_IN_YEAR;
    }

    @Override
    public void addYears(int years) {
        this.year += years;
    }

    @Override
    public int getDay() {
        return sec / SECONDS_IN_DAY;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int dayOfWeek() {
        int days=this.getDay();
        while (days>7){
            days-=7;
        }
       return  days;
    }

    @Override
    public AbstractDateInterface getNow() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        DateTime now=new DateTime();
        now.year=Integer.parseInt(dateFormat.format(date).substring(0,4));
        now.month=Integer.parseInt(dateFormat.format(date).substring(5,7));
        now.sec=Integer.parseInt(dateFormat.format(date).substring(8,9))*3600*24+Integer.parseInt(dateFormat.format(date).substring(11,13))*3600+Integer.parseInt(dateFormat.format(date).substring(14,16))*60+Integer.parseInt(dateFormat.format(date).substring(17,19));
        return now;
    }

    @Override
    public AbstractDateInterface parse(String date) {
        return null;
    }

    @Override
    public String toStringDate() {
        return new String("день:"+this.getDay()+"месяц:"+month+"год:"+year);
    }

    @Override
    public long toUInt64() {
        return 0;
    }

    private boolean isLoopYear() {
        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }
    private int dayInMonth() {
        var result = dayOfMonth[month - 1];
        if(month == 2 && isLoopYear())
            result += 1;

        return result;
    }

}
