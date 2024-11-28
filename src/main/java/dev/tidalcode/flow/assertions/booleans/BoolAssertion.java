package dev.tidalcode.flow.assertions.booleans;

import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;

public class BoolAssertion extends BoolAssert<Boolean> {

    public BoolAssertion(String description, boolean actual, boolean softAssertion) {
        this.description = description;
        this.actual = actual;
        this.softAssertion = softAssertion;
    }

    /**
     * Method to check if the given value is true.
     * @return a self reference.
     */
    public BoolAssert<Boolean> isTrue() {
        if (Boolean.FALSE.equals(actual)) {
            status = false;
        }
        new ErrorStack.Builder("Value expected to be true but was false", description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given value is false.
     * @return a self reference.
     */
    public BoolAssert<Boolean> isFalse() {
        if (Boolean.TRUE.equals(actual)) {
            status = false;
        }
        new ErrorStack.Builder("Value expected to be false but was true", description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }
}
