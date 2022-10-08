package com.tidal.flow.assertions.arrays;

import com.tidal.flow.assertions.NullCheck;
import com.tidal.flow.assertions.stackbuilder.ErrorStack;

import java.util.Arrays;
import java.util.Objects;

public class ArrayAssertion<T> extends ArrayAssert<T> {

    public ArrayAssertion(String description, T[] actual, boolean softAssertion) {
        this.description = description;
        this.actual = actual;
        this.softAssertion = softAssertion;
    }

    /**
     * Method to verify the given array contains the values provided.
     * @param values - comparison values to be evaluated for presence in the array
     * @return a self reference
     */
    @SafeVarargs
    @Override
    public final ArrayAssert<T> contains(T... values) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, values, softAssertion)) {
            return this;
        }
        Arrays.asList(values).forEach(v -> {
            status = Arrays.asList(actual).contains(v);
            String errorDetails = String.format("Value '%s' is not contained in array '%s'", v, Arrays.toString(actual));
            ErrorStack.builder(errorDetails, description)
                    .withStackTrace(Thread.currentThread().getStackTrace())
                    .withAssertionStatus(softAssertion, status)
                    .build();
        });
        return this;
    }

    /**
     * Method to check the given array does not contain the values
     * @param values comparison values to be evaluated for absence in the array
     * @return a self reference
     */
    @SafeVarargs
    @Override
    public final ArrayAssert<T> notContains(T... values) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, values, softAssertion)) {
            return this;
        }
        Arrays.asList(values).forEach(v -> {
            status = !Arrays.asList(actual).contains(v);
            String errorDetails = String.format("Value '%s' is contained or present in array '%s'", v, Arrays.toString(actual));
            ErrorStack.builder(errorDetails, description)
                    .withStackTrace(Thread.currentThread().getStackTrace())
                    .withAssertionStatus(softAssertion, status)
                    .build();
        });
        return this;
    }

    /**
     * Evaluates the size of the array.
     * @param size array size value to compared
     * @return a self reference
     */
    @Override
    public ArrayAssert<T> hasSize(int size) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.length != size) {
            status = false;
        }
        String errorDetails = String.format("Array actual size %d is not equal to expected size %d", actual.length, size);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public ArrayAssert<T> isTypeOf(Class<?> klass) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, klass, softAssertion)) {
            return this;
        }
        if (actual[0].getClass() != klass) {
            status = false;
        }
        String errorDetails = String.format("Array expected type was '%s' but it was '%s'", klass, actual[0].getClass());
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Evaluates the given array is empty or not
     * @return A self reference to this object
     */
    @Override
    public ArrayAssert<T> isEmpty() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.length != 0 && !Arrays.stream(actual).allMatch(Objects::isNull)) {
            status = false;
        }
        String errorDetails = String.format("Array expected to be empty, but it size was %d", actual.length);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public ArrayAssert<T> isNotEmpty() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.length == 0 || !Arrays.stream(actual).allMatch(Objects::isNull)) {
            status = false;
        }
        String errorDetails = String.format("Input array '%s' is supposed not to be empty", Arrays.toString(actual));
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }
}
