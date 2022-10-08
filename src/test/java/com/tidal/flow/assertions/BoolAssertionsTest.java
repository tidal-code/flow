package com.tidal.flow.assertions;

import com.tidal.flow.assertions.stackbuilder.ErrorStack;
import org.junit.After;
import org.junit.Test;

import static com.tidal.flow.assertions.Assert.verify;

public class BoolAssertionsTest {

    @After
    public void cleanup(){
        new ErrorStack().execute();
    }

    @Test
    public void falseValueTrueTestFail(){
        try{
            verify("Output should be true", true).isFalse();
        } catch (Exception e){
            verify("falseValueTrueTestFail", e.getMessage())
                    .contains("Verification Failed: Value expected to be false but was true")
                    .contains("Description: Output should be true")
                    .contains("com.tidal.flow.assertions.BoolAssertionsTest.falseValueTrueTestFail");
        }
    }

    @Test
    public void instanceOfTest(){
        verify("Output should be true", true).isInstanceOf(Boolean.class);
    }

    @Test
    public void falseValueTestPass(){
        verify("Output should be false", false).isFalse();
    }

    @Test
    public void trueValueTestPass(){
        verify("Output should be true", true).isTrue();
    }

    @Test
    public void trueValueFalseTestFail(){
        try{
            verify("Output should be true", true).isFalse();
        }catch (VerificationError e){
            verify("Output should be true", e.getMessage())
                    .contains("Verification Failed: Value expected to be false but was true")
                    .contains("Description: Output should be true")
                    .contains("com.tidal.flow.assertions.BoolAssertionsTest.trueValueFalseTestFail");
        }
    }
}
