package dev.tidalcode.flow.assertions;

import org.junit.Test;

import static dev.tidalcode.flow.assertions.Assert.verify;


public class ArrayAssertionTest {

    @Test
    public void stringArrayContainsTest(){
        verify("array should contain 'hello' and 'world", getArray()).contains("hello", "world");
    }

    @Test (expected = AssertionError.class)
    public void nullArrayTest(){
        verify("array should contain 'hello' and 'world", getNullArray()).contains("hello", "world");
    }

    @Test (expected = AssertionError.class)
    public void stringArrayContainsTestFail(){
        verify("Failing: array should contain 'hello' and 'world", getArray()).contains("hellos", "worlds");
    }

    @Test
    public void stringArrayNotContainsTest(){
        verify("array should not contain 'hellos' and 'worlds", getArray()).notContains("hellos", "worlds");
    }

    @Test (expected = AssertionError.class)
    public void stringArrayNotContainsFailingTest(){
        verify("Failing: array should not contain 'hellos' and 'worlds", getArray()).notContains("hello", "world");
    }

    @Test
    public void arraySizeTest(){
        verify("array should have a size of 2", getArray()).hasSize(2);
    }

    @Test (expected = AssertionError.class)
    public void arraySizeFailingTest(){
        verify("Failing: array should have a size of 2", getArray()).hasSize(3);
    }

    @Test
    public void arrayTypeTest(){
        verify("array should be a string array", getArray()).isTypeOf(String.class);
    }

    @Test (expected = AssertionError.class)
    public void arrayTypeFailingTest(){
        verify("Failing: array should be a string array", getArray()).isTypeOf(Integer.class);
    }

    @Test
    public void emptyArrayTest(){
        verify("array should be empty", getEmptyArray()).isEmpty();
    }

    @Test (expected = AssertionError.class)
    public void emptyArrayFailingTest(){
        verify("Failing: array should be empty", getArray()).isEmpty();
    }

    @Test
    public void nonEmptyArrayTest(){
        verify("array should be empty", getEmptyArray()).isNotEmpty();
    }


    public String[] getArray(){
        String[] theArray = new String[2];
        theArray[0] = "hello";
        theArray[1] = "world";
        return theArray;
    }

    public String[] getEmptyArray(){
        String[] theArray = new String[2];
        theArray[0] = null;
        theArray[1] = null;
        return theArray;
    }

    private String[] getNullArray(){
        return null;
    }
}
