package dev.tidalcode.flow.assertions.strings;

import dev.tidalcode.flow.assertions.BaseAssertion;

public abstract class StringAssert<T> extends BaseAssertion<T> {
    public abstract StringAssert<T> isNotNullOrEmpty();

    public abstract StringAssert<T> isNullOrEmpty();

    public abstract StringAssert<T> contains(String value);

    public abstract StringAssert<T> startsWith(String value);

    public abstract StringAssert<T> endsWith(String value);

    public abstract StringAssert<T> matchesPattern(String pattern);

    public abstract StringAssert<T> equalsIgnoringCase(String value);

    public abstract StringAssert<T> hasCharCount(int value);

    public abstract StringAssert<T> hasAlphaNumericCharCount(int value);

    public abstract StringAssert<T> containsOnlyLetters();

    public abstract StringAssert<T> containsOnlyNumbers();

    public abstract StringAssert<T> containsNumbersOfSize(int value);

    public abstract StringAssert<T> isValidJson();

    public abstract StringAssert<T> matchesJson(String json);
}
