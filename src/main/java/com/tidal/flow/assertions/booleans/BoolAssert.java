package com.tidal.flow.assertions.booleans;

import com.tidal.flow.assertions.BaseAssertion;

public abstract class BoolAssert<T> extends BaseAssertion<T> {
    public abstract BaseAssertion<T> isTrue();

    public abstract BaseAssertion<T> isFalse();
}
