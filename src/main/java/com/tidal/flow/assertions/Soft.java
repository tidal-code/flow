package com.tidal.flow.assertions;

import com.tidal.flow.assertions.arrays.ArrayAssert;
import com.tidal.flow.assertions.booleans.BoolAssert;
import com.tidal.flow.assertions.dates.DateAssert;
import com.tidal.flow.assertions.lists.ListAssert;
import com.tidal.flow.assertions.maps.MapAssert;
import com.tidal.flow.assertions.numbers.NumberAssert;
import com.tidal.flow.assertions.strings.StringAssert;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Soft {

    private Soft() {
    }

    public static StringAssert<String> verify(String description, String actual) {
        return Assert.verify(description, actual, true);
    }

    public static NumberAssert<Number> verify(String description, Number actual) {
        return Assert.verify(description, actual, true);
    }

    public static BoolAssert<Boolean> verify(String description, boolean actual) {
        return Assert.verify(description, actual, true);
    }

    public static <T> ArrayAssert<T> verify(String description, T[] actual) {
        return Assert.verify(description, actual, true);
    }

    public static <T> ListAssert<T> verify(String description, List<T> actual) {
        return Assert.verify(description, actual, true);
    }

    public static <T, V> MapAssert<T, V> verify(String description, Map<T, V> actual) {
        return Assert.verify(description, actual, true);
    }

    public static DateAssert<Date> verify(String description, Date actual) {
        return Assert.verify(description, actual, true);
    }
}
