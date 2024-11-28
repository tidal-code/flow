package dev.tidalcode.flow.assertions;

import org.junit.Test;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import static dev.tidalcode.flow.assertions.Assert.verify;


public class DateAssertionTest {

    @Test
    public void testDateAfter(){
        verify("Date should be after", Date.from(Instant.now())).isAfter(getPreviousDate());
    }

    @Test (expected = AssertionError.class)
    public void testDateAfterFailing(){
        verify("Date should be after failing", getPreviousDate()).isAfter(Date.from(Instant.now()));
    }

    @Test
    public void testDateBefore(){
        verify("Date should be before", Date.from(Instant.now())).isBefore(getNextDate());
    }

    @Test (expected = AssertionError.class)
    public void testDateBeforeFail(){
        verify("Date should be before failing", getNextDate()).isBefore( Date.from(Instant.now()));
    }

    @Test
    public void futureDateTest(){
        verify("Date should be in future", getNextDate()).isInTheFuture();
    }

    @Test (expected = AssertionError.class)
    public void futureDateTestFailing(){
        verify("Date should be in future failing", getPreviousDate()).isInTheFuture();
    }

    @Test
    public void pastDateTest(){
        verify("Date should be in future", getPreviousDate()).isInThePast();
    }

    @Test (expected = AssertionError.class)
    public void pastDateTestFailing(){
        verify("Date should be in future failing", getNextDate()).isInThePast();
    }

    private Date getPreviousDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    private Date getNextDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
}
