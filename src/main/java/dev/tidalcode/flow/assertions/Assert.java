package dev.tidalcode.flow.assertions;


import dev.tidalcode.flow.assertions.arrays.ArrayAssert;
import dev.tidalcode.flow.assertions.arrays.ArrayAssertion;
import dev.tidalcode.flow.assertions.booleans.BoolAssert;
import dev.tidalcode.flow.assertions.booleans.BoolAssertion;
import dev.tidalcode.flow.assertions.dates.DateAssert;
import dev.tidalcode.flow.assertions.dates.DateAssertion;
import dev.tidalcode.flow.assertions.lists.ListAssert;
import dev.tidalcode.flow.assertions.lists.ListAssertion;
import dev.tidalcode.flow.assertions.maps.MapAssert;
import dev.tidalcode.flow.assertions.maps.MapAssertion;
import dev.tidalcode.flow.assertions.numbers.NumberAssert;
import dev.tidalcode.flow.assertions.numbers.NumberAssertion;
import dev.tidalcode.flow.assertions.strings.StringAssert;
import dev.tidalcode.flow.assertions.strings.StringAssertion;

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
