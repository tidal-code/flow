package com.tidal.flow.assertions;

import com.tidal.flow.assertions.stackbuilder.ErrorStack;
import org.junit.Test;

import static com.tidal.flow.assertions.Soft.verify;

public class NumberAssertionTest {


    @Test
    public void numberEqualsTest(){
        verify("Number should be equal to 5", 5).isEqualTo(6);
    }

    @Test(expected = AssertionError.class)
    public void numberNotEqualsTest(){
        verify("Number should not be equal to 5", 5).isNotEqualTo(5);
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void numberIsNotNull(){
        Soft.verify("Number should not be equal to 5", getNull()).isNotNull();
        new ErrorStack().execute();
    }

    @Test
    public void numberGreaterThanTest(){
        Soft.verify("Number should not be greater than 6", 5).isGreaterThan(6);
    }

    @Test (expected = AssertionError.class)
    public void numberGreaterThanHardTest(){
        Assert.verify("Number should not be greater than 6", 6).isGreaterThan(7);
    }

    @Test (expected = AssertionError.class)
    public void numberLessThanTest(){
        Soft.verify("Number should not be less than 4", 5).isLessThan(4);
        new ErrorStack().execute();
    }

    @Test
    public void positiveNumberTest(){
        verify("Number should be positive", -5).isPositive();
    }

    @Test
    public void negativeNumberTest(){
        verify("Number should be negative", 5).isPositive();
    }

    @Test
    public void inRangeOfHigherLowerValueTest(){
        verify("Number should be negative", 5).isInRangeOf(6, 1);
    }

    @Test
    public void inRangeOfLowerHigherValueTest(){
        Soft.verify("Number should be negative", 5).isInRangeOf(1, 6);
    }

    @Test(expected = AssertionError.class)
    public void inRangeOfHigherLowerValueFailTest(){
        Assert.verify("Number should be negative", 5).isInRangeOf(4, 1);
    }

    @Test (expected = AssertionError.class)
    public void inRangeOfLowerHigherValueFailTest(){
        Assert.verify("Number should be negative", 5).isInRangeOf(1, 4);
    }

    private Integer getNull(){
        return null;
    }

}
