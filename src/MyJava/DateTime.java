package MyJava;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        return sec >= 0 && sec <= SECONDS_IN_DAY
                && month >= 0 && month <= 12
                && year >= 0;
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
        Date date = new Date();
        DateTime now = new DateTime();

        var sec = date.getTime();
        var days = Math.ceil((double) sec / SECONDS_IN_DAY / 1000);
        var year = 1970;
        for(int daysInYear = 365; days > daysInYear ; days -= daysInYear, year++) {
            daysInYear = isLoopYear(year) ? 366 : 365;
        }
        now.year = year;

        var month = 0;
        for(int daysInMonth = dayInMonth(month); days > daysInMonth; days -=daysInMonth, month++) {
            daysInMonth = dayInMonth(month);
        }
        now.month = month;

        now.sec = (int)(days * SECONDS_IN_DAY);

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
        for (int i=0;i<12;i++){
            allDayInYear=allDayInYear+year*dayOfMonth[i];
        }
        allDay+=allDayInYear;
        allSec+=Long.parseLong(String.valueOf(allDay));
        return allSec;
    }

    private boolean isLoopYear() {
        return isLoopYear(year);
    }
    private boolean isLoopYear(int year) {
        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }
    private int dayInMonth() {
        return dayInMonth(month);
    }
    private int dayInMonth(int month) {
        var result = dayOfMonth[month];
        if(month == 1 && isLoopYear())
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
