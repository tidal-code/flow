package com.tidal.flow.assertions;

import com.tidal.flow.assertions.stackbuilder.ErrorStack;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.tidal.flow.assertions.Assert.verify;

public class BoolAssertionSoftTest {

    Logger logger = LoggerFactory.getLogger(BoolAssertionSoftTest.class);

    @Test
    public void trueValueFalseSoftTest() {
        try {
            Soft.verify("Output should be true", true).isFalse();
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("trueValueFalseTest", e.getMessage())
                    .contains("Verification Failed: Value expected to be false but was true")
                    .contains("Description: Output should be true")
                    .contains("com.tidal.flow.assertions.BoolAssertionSoftTest.trueValueFalseSoftTest");
        }
    }

    @Test
    public void trueValueFalseSoftTestFail() {
        try{
            Soft.verify("Output should be true", true).isFalse().isEqualTo(false);
            new ErrorStack().execute();
        } catch(AssertionError e){
            verify("trueValueFalseSoftTestPass", e.getMessage())
                    .contains("Verification Failed: Value expected to be false but was true")
                    .contains("Description: Output should be true")
                    .contains("com.tidal.flow.assertions.BoolAssertionSoftTest.trueValueFalseSoftTestFail");
        }

    }

    @Test
    public void trueValueFalseSoftTestPass() {
        try {
            try {
                Soft.verify("Output should be true", true).isFalse();
                verify("Output should be equal to true", true).isFalse();
            } catch (AssertionError e) {
                verify("trueValueFalseSoftTestPass", e.getMessage())
                        .contains("Reason: Value expected to be false but was true")
                        .contains("Description: Output should be equal to true")
                        .contains("com.tidal.flow.assertions.BoolAssertionSoftTest.trueValueFalseSoftTestPass");
                new ErrorStack().execute();
            }

        } catch (AssertionError e) {
            logger.error(e.getMessage());
            verify("trueValueFalseSoftTestPass", e.getMessage())
                    .contains("Verification Failed: Value expected to be false but was true")
                    .contains("Description: Output should be true")
                    .contains("com.tidal.flow.assertions.BoolAssertionSoftTest.trueValueFalseSoftTestPass");
        }
    }
}
