package dev.tidalcode.flow.assertions;

import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;

public abstract class BaseAssertion<T> implements BaseAssertionTypes<T> {
    protected String description;
    protected T actual;
    protected boolean softAssertion;
    protected boolean status = true;

    protected static final String ACTUAL_TYPE_DESCRIPTION = "Actual, input value is";
    protected static final String EXPECTED_TYPE_DESCRIPTION = "Expected, input value is";

    /**
     * Checks object equality between two comparison values.
     * @param value the comparison value
     */
    @Override
    public void isEqualTo(T value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return;
        }
        if (!actual.equals(value)) {
            status = false;
        }

        if(actual.getClass() != value.getClass()){
            status = false;
        }

        String errorDetail = String.format("Actual value '%s' is not equal to comparison value '%s'", actual, value);
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
    }

    /**
     * Checks the inequality between two comparison values
     * @param value - the comparison value
     */
    @Override
    public void isNotEqualTo(T value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return;
        }
        if (actual.equals(value)) {
            status = false;
        }

        String errorDetail = String.format("Actual value '%s' should not be equal to comparison value '%s'", actual, value);
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
    }

    /**
     * Checks if the actual value is not a null value
     */
    @Override
    public void isNotNull() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return;
        }
        if (actual == null) {
            status = false;
        }
        String errorDetail = "Actual value is a null value";
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
    }

    /**
     * Checks the class type of the given value
     * @param klass - comparison class type
     */
    @Override
    public void isInstanceOf(Class<?> klass) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, klass, softAssertion)) {
            return;
        }
        if (klass != actual.getClass()) {
            status = false;
        }
        String errorDetail = String.format("Expected class type was '%s' but instead got '%s'", klass, actual.getClass());
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
    }

}
