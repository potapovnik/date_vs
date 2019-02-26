package MyJava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateSec implements Date{
    int sec;
    int month;
    int year;

    @Override
    public int dateTimeCmp(Date a, Date b) {
        if (a.getDay()== b.getDay()&& a.getMonth()==b.getMonth()&& a.getYear()==b.getYear())
            return 1;
        return 0;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void addDays(int days) {

    }

    @Override
    public void addMonths(int months) {

    }

    @Override
    public void addYears(int years) {
        this.year+=years;
    }

    @Override
    public int getDay() {
        return (int)Math.ceil(((double) sec)/86400.0);
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
    public Date getNow() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        DateSec now=new DateSec();
        now.year=Integer.parseInt(dateFormat.format(date).substring(0,4));
        now.month=Integer.parseInt(dateFormat.format(date).substring(5,7));
        now.sec=Integer.parseInt(dateFormat.format(date).substring(8,9))*3600*24+Integer.parseInt(dateFormat.format(date).substring(11,13))*3600+Integer.parseInt(dateFormat.format(date).substring(14,16))*60+Integer.parseInt(dateFormat.format(date).substring(17,19));
        return now;
    }

    @Override
    public Date Parser(String date) {
        return null;
    }

    @Override
    public String toStringDate() {
        return new String("день:"+this.getDay()+"месяц:"+month+"год:"+year);
    }

    @Override
    public long ToUInt64() {
        return 0;
    }

}
