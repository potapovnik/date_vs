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
        if (sec<0||sec>SECONDS_IN_DAY||month<0||month>12||year<0){
            return false;
        }
        return true;
    }

    @Override
    public void addDays(int days) {
        if (days>0) {
            var seconds = days * SECONDS_IN_DAY;
            sec += seconds;

            while (sec > SECONDS_IN_DAY * dayInMonth()) {
                sec -= SECONDS_IN_DAY * dayInMonth();
                addMonths(1);
            }
        } else {
            sec-=days*SECONDS_IN_DAY;
            while (sec<0) {
                int i=0;
                while (sec<0){
                    sec+=dayOfMonth[i];
                    i++;
                }
                month-=i;
                if (sec<0){
                    addYears(-1);
                    month=12;
                }
            }
        }
    }

    @Override
    public void addMonths(int months) {
        if (months>0) {
            month += months;
            year += month / MONTH_IN_YEAR;
            month %= MONTH_IN_YEAR;
        }
        else {
            month-=months;
            while (month<0){
                month+=12;
                addYears(-1);
            }
        }
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
        int days=getDay();
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
        DateTime parse=new DateTime();
        parse.year=Integer.parseInt(date.substring(0,4));
        parse.month=Integer.parseInt(date.substring(6,7));
         parse.sec=Integer.parseInt(date.substring(9,10))*SECONDS_IN_DAY;
         return parse;

    }

    @Override
    public String toStringDate() {
        return new String("день:"+getDay()+"месяц:"+month+"год:"+year);
    }

    @Override
    public long toUInt64() {
        Long allSec=Long.parseLong(String.valueOf(sec));
        int allDay=0;
        for (int i=0;i<month;i++){
            allDay+=dayOfMonth[i];
        }
        int allDayInYear=0;
        for (int i=0;i<13;i++){
            allDayInYear=allDayInYear+year*dayOfMonth[i];
        }
        allDay+=allDayInYear;
        allSec+=Long.parseLong(String.valueOf(allDay));
        return allSec;
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
    @Override
    public boolean equals(Object obj) {
        if (obj==this)
            return true;
        if (obj instanceof DateTime){
            return this.toUInt64()==((DateTime) obj).toUInt64();
        }
        return false;
    }
}
