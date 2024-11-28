package dev.tidalcode.flow.assertions.dates;

import dev.tidalcode.flow.assertions.BaseAssertion;
import dev.tidalcode.flow.assertions.NullCheck;
import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;

import java.time.Instant;
import java.util.Date;

public class DateAssertion extends DateAssert<Date> {

    public DateAssertion(String description, Date actual, boolean softAssertion) {
        this.description = description;
        this.actual = actual;
        this.softAssertion = softAssertion;
    }

    /**
     * Method to check if the given date is a date in the future.
     * @return a self reference.
     */
    @Override
    public DateAssert<Date> isInTheFuture() {
        if (NullCheck.assertNull(BaseAssertion.ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.before(Date.from(Instant.now()))) {
            status = false;
        }
        String errorDetails = String.format("Date '%s' is not a future date", actual);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given date is a date in the past.
     * @return a self reference.
     */
    @Override
    public DateAssert<Date> isInThePast() {
        if (NullCheck.assertNull(BaseAssertion.ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.after(Date.from(Instant.now()))) {
            status = false;
        }
        String errorDetails = String.format("Date '%s' is not a date in the past", actual);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given date is <b>before</b> the comparison date.
     * @param value the comparison value against the actual value.
     * @return a self reference.
     */
    @Override
    public DateAssert<Date> isBefore(Date value) {
        if (NullCheck.assertNull(BaseAssertion.ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(BaseAssertion.EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (!actual.before(value)) {
            status = false;
        }
        String errorDetails = String.format("Date '%s' is not after date '%s'", actual, value);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given date is <b>after</b> the comparison date.
     * @param value the comparison value against the actual value.
     * @return a self reference.
     */
    @Override
    public DateAssert<Date> isAfter(Date value) {
        if (NullCheck.assertNull(BaseAssertion.ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(BaseAssertion.EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (!actual.after(value)) {
            status = false;
        }
        String errorDetails = String.format("Date '%s' is not after date '%s'", actual, value);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }
}
