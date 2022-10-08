package com.tidal.flow.assertions.dates;

import com.tidal.flow.assertions.BaseAssertion;

import java.util.Date;

public abstract class DateAssert<T> extends BaseAssertion<T> {
    public abstract DateAssert<T> isInTheFuture();

    public abstract DateAssert<T> isInThePast();

    public abstract DateAssert<T> isBefore(Date value);

    public abstract DateAssert<T> isAfter(Date value);
}
