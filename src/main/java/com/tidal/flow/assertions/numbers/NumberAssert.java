package com.tidal.flow.assertions.numbers;

import com.tidal.flow.assertions.BaseAssertion;

public abstract class NumberAssert<T> extends BaseAssertion<T> {
    public abstract NumberAssert<T> isGreaterThan(Number value);

    public abstract NumberAssert<T> isLessThan(Number value);

    public abstract NumberAssert<T> isPositive();

    public abstract NumberAssert<T> isNegative();

    public abstract NumberAssert<T> isInRangeOf(Number value1, Number value2);
}
