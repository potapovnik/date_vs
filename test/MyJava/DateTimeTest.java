package MyJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void validate() {
    }

    @Test
    void addDays() {
    }

    @Test
    void addMonths() {
    }

    @Test
    void addYears() {
    }

    @Test
    void getDay() {
    }

    @Test
    void getMonth() {
    }

    @Test
    void getYear() {
    }

    @Test
    void dayOfWeek() {
    }

    @Test
    void getNow() {
        DateTime date1 = new DateTime();
        var now = date1.getNow();
        Date date = new Date();

        var expected = date.getTime() / 1000;
        var actual = now.toUInt64();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parse() {
    }

    @Test
    void toStringDate() {
    }

    @Test
    void toUInt64() {
    }

    @Test
    void equals() {
    }
}