package com.tidal.flow.assertions;

import com.tidal.flow.assertions.stackbuilder.ErrorStack;
import org.junit.Test;


public class ArraySoftAssertionTest {

    @Test
    public void stringArrayContainsTest(){
        Soft.verify("array should contain 'hello' and 'world", getArray()).contains("hello", "world");
        new ErrorStack().execute();
    }

    @Test
    public void stringArrayNotContainsTest(){
        Soft.verify("array should not contain 'hellos' and 'worlds", getArray()).notContains("hellos", "worlds");
        new ErrorStack().execute();
    }

    @Test
    public void arraySizeTest(){
        Soft.verify("array should have a size of 2", getArray()).hasSize(2);
        new ErrorStack().execute();
    }

    @Test(expected = AssertionError.class)
    public void arrayTypeTest(){
        Soft.verify("array should be a string array", getArray()).isTypeOf(Integer.class);
        new ErrorStack().execute();
    }

    @Test
    public void emptyArrayTest(){
        Soft.verify("array should be empty", getEmptyArray()).isEmpty();
        new ErrorStack().execute();
    }

    @Test
    public void nonEmptyArrayTest(){
        Soft.verify("array should be empty", getEmptyArray()).isNotEmpty();
        new ErrorStack().execute();
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
}
