package dev.tidalcode.flow.assertions.booleans;

import dev.tidalcode.flow.assertions.BaseAssertion;

public abstract class BoolAssert<T> extends BaseAssertion<T> {
    public abstract BaseAssertion<T> isTrue();

    public abstract BaseAssertion<T> isFalse();
}
