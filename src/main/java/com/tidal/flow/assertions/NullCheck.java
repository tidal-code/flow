package com.tidal.flow.assertions;

import com.tidal.flow.assertions.stackbuilder.ErrorStack;

public class NullCheck {

    private NullCheck() {
    }

    public static <T> boolean assertNull(String parameterName, T value, boolean softAssertion){
        if(value == null){
            String errorDetail = String.format("%s is NULL; verification cannot be performed", parameterName);
            new ErrorStack.Builder(errorDetail, "Verification failure due to null input")
                    .withStackTrace(Thread.currentThread().getStackTrace())
                    .withAssertionStatus(softAssertion, false)
                    .build();
            return true;
        }
        return false;
    }
}
