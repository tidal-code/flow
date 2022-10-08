package com.tidal.flow.assertions.numbers;

import com.tidal.flow.assertions.NullCheck;
import com.tidal.flow.assertions.stackbuilder.ErrorStack;

public class NumberAssertion extends NumberAssert<Number> {

    public NumberAssertion(String description, Number actual, boolean softAssertion) {
        this.description = description;
        this.actual = actual;
        this.softAssertion = softAssertion;
    }

    /**
     * Method to check if the given number type is greater than the comparison value.
     * @param value the comparison value.
     * @return a self reference.
     */
    @Override
    public NumberAssert<Number> isGreaterThan(Number value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (actual.doubleValue() <= value.doubleValue()) {
            status = false;
        }
        String errorDetails = String.format("Actual value '%s' is not great than the comparison value '%s", actual, value);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given number type is less than the comparison value.
     * @param value the comparison value.
     * @return a self reference.
     */
    @Override
    public NumberAssert<Number> isLessThan(Number value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (actual.doubleValue() >= value.doubleValue()) {
            status = false;
        }
        String errorDetails = String.format("Actual value '%s' is not less than the comparison value '%s'", actual, value);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given number type is a positive value.
     * @return a self reference.
     */
    @Override
    public NumberAssert<Number> isPositive() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.doubleValue() < 0) {
            status = false;
        }
        String errorDetails = String.format("Value '%s' is not a positive value", actual);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given number type is a negative value.
     * @return a self reference.
     */
    @Override
    public NumberAssert<Number> isNegative() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.doubleValue() >= 0) {
            status = false;
        }
        String errorDetails = String.format("Value '%s' is not a negative value", actual);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given number type is the range of the values given.
     * <p>The order of the range does not matter. For example: 3 inRangeOf(0, 4) and 3 inRangeOf(4, 0), both will be evaluated true.</p>
     * @return a self reference.
     */
    @Override
    public NumberAssert<Number> isInRangeOf(Number value1, Number value2) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)
                || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value1, softAssertion)
                || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value2, softAssertion)) {
            return this;
        }
        if (value1.doubleValue() < value2.doubleValue() && (actual.doubleValue() < value1.doubleValue() || actual.doubleValue() > value2.doubleValue())) {
            status = false;
        }
        if (value1.doubleValue() > value2.doubleValue() && (actual.doubleValue() > value1.doubleValue() || actual.doubleValue() < value2.doubleValue())) {
            status = false;
        }
        String errorDetails = String.format("Value %s is not in range or between %s and %s", actual, value1, value2);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }
}
