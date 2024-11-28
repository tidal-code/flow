package dev.tidalcode.flow.assertions;

import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.tidalcode.flow.assertions.Assert.verify;

public class BoolAssertionsTest {

    Logger logger = LoggerFactory.getLogger(BoolAssertionSoftTest.class);

    @After
    public void cleanup(){
        new ErrorStack().execute();
    }

    @Test
    public void falseValueTrueTestFail(){
        try{
            verify("Output should be true", true).isFalse();
        } catch (AssertionError e){
            logger.error(e.getMessage());
            verify("falseValueTrueTestFail", e.getMessage())
                    .contains("Reason: Value expected to be false but was true")
                    .contains("Description: Output should be true")
                    .contains("dev.tidalcode.assertions.flow.BoolAssertionsTest.falseValueTrueTestFail");
        }
    }

    @Test
    public void instanceOfTest(){
        verify("Output should be true", true).isInstanceOf(Boolean.class);
    }

    @Test
    public void falseValueTestPass(){
        verify("Output should be false",  false).isFalse();
    }

    @Test
    public void trueValueTestPass(){
        verify("Output should be true", true).isTrue();
    }

    @Test
    public void trueValueFalseTestFail(){
        try{
            verify("Output should be true", true).isFalse();
        }catch (AssertionError e){
            verify("Output should be true", e.getMessage())
                    .contains("Reason: Value expected to be false but was true")
                    .contains("Description: Output should be true")
                    .contains("dev.tidalcode.assertions.flow.BoolAssertionsTest.trueValueFalseTestFail");
        }
    }
}
