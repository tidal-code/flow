package com.tidal.flow.assertions.arrays;

import com.tidal.flow.assertions.BaseAssertion;

public abstract class ArrayAssert<T> extends BaseAssertion<T[]> {
    public abstract ArrayAssert<T> contains(T... values);

    public abstract ArrayAssert<T> notContains(T... values);

    public abstract ArrayAssert<T> hasSize(int size);

    public abstract ArrayAssert<T> isTypeOf(Class<?> klass);

    public abstract ArrayAssert<T> isEmpty();

    public abstract ArrayAssert<T> isNotEmpty();
}
