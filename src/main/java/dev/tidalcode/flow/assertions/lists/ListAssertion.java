package dev.tidalcode.flow.assertions.lists;

import dev.tidalcode.flow.assertions.NullCheck;
import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListAssertion<T> extends ListAssert<T> {

    public ListAssertion(String description, List<T> actual, boolean softAssertion) {
        this.description = description;
        this.actual = actual;
        this.softAssertion = softAssertion;
    }

    @Override
    @SafeVarargs
    public final ListAssert<T> contains(T... values) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, values, softAssertion)) {
            return this;
        }
        Arrays.stream(values).forEach(e -> {
            status = actual.contains(e);
            String errorDetail = String.format("Element '%s' not in the list %s", e, actual);
            new ErrorStack.Builder(errorDetail, description)
                    .withStackTrace(Thread.currentThread().getStackTrace())
                    .withAssertionStatus(softAssertion, status)
                    .build();
        });
        return this;
    }

    @Override
    public ListAssert<T> containsList(List<T> value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        value.forEach(v -> {
            status = actual.contains(v);
            String errorDetail = String.format("List '%s' does not contain element '%s' from list '%s'", actual, v, value);
            new ErrorStack.Builder(errorDetail, description)
                    .withStackTrace(Thread.currentThread().getStackTrace())
                    .withAssertionStatus(softAssertion, status)
                    .build();
        });
        return this;
    }

    @Override
    @SafeVarargs
    public final ListAssert<T> notContains(T... values) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, values, softAssertion)) {
            return this;
        }
        Arrays.stream(values).forEach(e -> {
            if (actual.contains(e)) {
                status = false;
            }
            String errorDetail = String.format("Element '%s' is contained in the list %s", e, actual);
            new ErrorStack.Builder(errorDetail, description)
                    .withStackTrace(Thread.currentThread().getStackTrace())
                    .withAssertionStatus(softAssertion, status)
                    .build();
        });
        return this;
    }

    @Override
    public ListAssert<T> isEmpty() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (!actual.isEmpty()) {
            status = false;
        }
        String errorDetail = String.format("List '%s' is not empty", actual);
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public ListAssert<T> isNotEmpty() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.isEmpty()) {
            status = false;
        }
        String errorDetail = String.format("List '%s' is empty", actual);
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public ListAssert<T> ofType(Class<?> value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if(actual.get(0).getClass() != value){
            status = false;
        }
        String errorDetail = String.format("The list '%s' and '%s' are not of the same type'", actual.get(0).getClass(), value);
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public final ListAssert<T> hasSize(int value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.size() != value) {
            status = false;
        }
        String errorDetail = String.format("Expected size of the list is '%d' but was '%d'", value, actual.size());
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public final ListAssert<T> hasSizeGreaterThan(int value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.size() <= value) {
            status = false;
        }
        String errorDetail = String.format("List size expected to be greater than '%d' but was '%d'", value, actual.size());
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public final ListAssert<T> hasSizeLessThan(int value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.size() <= value) {
            status = false;
        }
        String errorDetail = String.format("List size expected to be less than '%d' but was '%d'", value, actual.size());
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public ListAssert<T> isSubListOf(List<T> value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (Collections.indexOfSubList(value, actual) == -1) {
            status = false;
        }
        String errorDetail = String.format("List '%s' expected to be a sublist of '%s''", actual, value);
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    @Override
    public ListAssert<T> isSuperListOf(List<T> value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (Collections.indexOfSubList(actual, value) == -1) {
            status = false;
        }
        String errorDetail = String.format("List '%s' expected to be a super list of '%s''", actual, value);
        new ErrorStack.Builder(errorDetail, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }
}
