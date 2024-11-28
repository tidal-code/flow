package dev.tidalcode.flow.assertions.lists;

import dev.tidalcode.flow.assertions.BaseAssertion;

import java.util.List;

public abstract class ListAssert<T> extends BaseAssertion<List<T>> {
    public abstract ListAssert<T> contains(T... args);

    public abstract ListAssert<T> containsList(List<T> args);

    public abstract ListAssert<T> notContains(T... args);

    public abstract ListAssert<T> isEmpty();

    public abstract ListAssert<T> isNotEmpty();

    public abstract ListAssert<T> ofType(Class<?> klass);

    public abstract ListAssert<T> hasSize(int value);

    public abstract ListAssert<T> hasSizeGreaterThan(int value);

    public abstract ListAssert<T> hasSizeLessThan(int value);

    public abstract ListAssert<T> isSubListOf(List<T> subList);

    public abstract ListAssert<T> isSuperListOf(List<T> value);

}
