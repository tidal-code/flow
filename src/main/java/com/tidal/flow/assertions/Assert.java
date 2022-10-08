package com.tidal.flow.assertions;


import com.tidal.flow.assertions.arrays.ArrayAssert;
import com.tidal.flow.assertions.arrays.ArrayAssertion;
import com.tidal.flow.assertions.booleans.BoolAssert;
import com.tidal.flow.assertions.booleans.BoolAssertion;
import com.tidal.flow.assertions.dates.DateAssert;
import com.tidal.flow.assertions.dates.DateAssertion;
import com.tidal.flow.assertions.lists.ListAssert;
import com.tidal.flow.assertions.lists.ListAssertion;
import com.tidal.flow.assertions.maps.MapAssert;
import com.tidal.flow.assertions.maps.MapAssertion;
import com.tidal.flow.assertions.numbers.NumberAssert;
import com.tidal.flow.assertions.numbers.NumberAssertion;
import com.tidal.flow.assertions.strings.StringAssert;
import com.tidal.flow.assertions.strings.StringAssertion;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Assert {

    private Assert() {
    }

    public static StringAssert<String> verify(String description, String actual) {
         return verify(description, actual, false);
    }

    protected static StringAssert<String> verify(String description, String actual, boolean soft) {
        return new StringAssertion(description, actual, soft);
    }

    public static NumberAssert<Number> verify(String description, Number actual) {
        return verify(description, actual, false);
    }

    protected static NumberAssert<Number> verify(String description, Number actual, boolean soft) {
        return new NumberAssertion(description, actual, soft);
    }

    public static BoolAssert<Boolean> verify(String description, boolean actual) {
        return verify(description, actual, false);
    }

    protected static BoolAssert<Boolean> verify(String description, boolean actual, boolean soft) {
        return new BoolAssertion(description, actual, soft);
    }

    public static <T> ArrayAssert<T> verify(String description, T[] actual) {
        return verify(description, actual, false);
    }

    protected static <T> ArrayAssert<T> verify(String description, T[] actual, boolean soft) {
        return new ArrayAssertion<>(description, actual, soft);
    }

    public static <T> ListAssert<T> verify(String description, List<T> actual) {
        return verify(description, actual, false);
    }

    protected static <T> ListAssert<T> verify(String description, List<T> actual, boolean soft) {
        return new ListAssertion<>(description, actual, soft);
    }

    public static <T, V> MapAssert<T, V> verify(String description, Map<T, V> actual) {
        return verify(description, actual, false);
    }

    protected static <T, V> MapAssert<T, V> verify(String description, Map<T, V> actual, boolean soft) {
        return new MapAssertion<>(description, actual, soft);
    }

    public static DateAssert<Date> verify(String description, Date actual) {
        return verify(description, actual, false);
    }

    protected static DateAssert<Date> verify(String description, Date actual, boolean soft) {
        return new DateAssertion(description, actual, soft);
    }
}
