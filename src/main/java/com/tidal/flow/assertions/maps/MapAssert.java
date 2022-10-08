package com.tidal.flow.assertions.maps;

import com.tidal.flow.assertions.BaseAssertion;

import java.util.Map;

public abstract class MapAssert<T, V> extends BaseAssertion<Map<T, V>> {
    public abstract MapAssert<T, V> containsKeys(T... keys);

    public abstract MapAssert<T, V> containsValues(V... values);

    public abstract MapAssert<T, V> containsKeyAndValue(T key, V value);

    public abstract MapAssert<T, V> isEmpty();

    public abstract MapAssert<T, V> isNotEmpty();

    public abstract MapAssert<T, V> isSameTypeAs(Map<?, ?> value);

}
