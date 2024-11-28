package dev.tidalcode.flow.assertions;

import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;
import org.junit.Test;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import static dev.tidalcode.flow.assertions.Soft.verify;

public class DateAssertionSoftTest {

    @Test
    public void testDateAfter(){
        verify("Date should be after", Date.from(Instant.now())).isAfter(getPreviousDate());
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void testDateAfterFailing(){
        verify("Date should be after failing", getPreviousDate()).isAfter(Date.from(Instant.now()));
        new ErrorStack().execute();
    }

    @Test
    public void testDateBefore(){
        verify("Date should be before", Date.from(Instant.now())).isBefore(getNextDate());
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void testDateBeforeFail(){
        verify("Date should be before failing", getNextDate()).isBefore( Date.from(Instant.now()));
        new ErrorStack().execute();
    }

    @Test
    public void futureDateTest(){
        verify("Date should be in future", getNextDate()).isInTheFuture();
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void futureDateTestFailing(){
        verify("Date should be in future failing", getPreviousDate()).isInTheFuture();
        new ErrorStack().execute();
    }

    @Test
    public void pastDateTest(){
        verify("Date should be in future", getPreviousDate()).isInThePast();
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void pastDateTestFailing(){
        verify("Date should be in future failing", getNextDate()).isInThePast();
        new ErrorStack().execute();
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
