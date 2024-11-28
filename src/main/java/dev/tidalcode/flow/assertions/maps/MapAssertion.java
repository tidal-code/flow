package dev.tidalcode.flow.assertions.maps;

import dev.tidalcode.flow.assertions.NullCheck;
import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;

import java.util.*;

public class MapAssertion<T, V> extends MapAssert<T, V> {

    public MapAssertion(String description, Map<T, V> actual, boolean softAssertion) {
        this.description = description;
        this.actual = actual;
        this.softAssertion = softAssertion;
    }

    /**
     * Method to check; whether the given map contains the specified keys.
     *
     * @param keys the 'keys' to check in the map given.
     * @return a self reference.
     */
    @Override
    @SafeVarargs
    public final MapAssert<T, V> containsKeys(T... keys) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, keys, softAssertion)) {
            return this;
        }
        Set<T> keysSet = actual.keySet();
        Arrays.stream(keys).forEach(k -> {
            status = keysSet.contains(k);
            String errorDetails = String.format("Map '%s' does not contain key '%s'", actual, k);
            ErrorStack.builder(errorDetails, description)
                    .withStackTrace(Thread.currentThread().getStackTrace())
                    .withAssertionStatus(softAssertion, status)
                    .build();
        });
        return this;
    }

    /**
     * Method to check, whether the given map contains the specified values.
     *
     * @param values the 'values' to check in the given map.
     * @return a self reference.
     */
    @Override
    @SafeVarargs
    public final MapAssert<T, V> containsValues(V... values) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, values, softAssertion)) {
            return this;
        }
        Collection<V> actualValues = actual.values();
        Arrays.stream(values).forEach(v -> {
            status = actualValues.contains(v);
            String errorDetails = String.format("Map '%s' does not contain value '%s'", actual, v);
            ErrorStack.builder(errorDetails, description)
                    .withStackTrace(Thread.currentThread().getStackTrace())
                    .withAssertionStatus(softAssertion, status)
                    .build();
        });
        return this;
    }

    /**
     * Method to check, whether the given map contains the specified values.
     *
     * @param key   the key to fetch the value from the map.
     * @param value the value to be compared.
     * @return a self reference.
     */
    @Override
    public final MapAssert<T, V> containsKeyAndValue(T key, V value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }

        if (value.getClass() == String.class && !actual.get(key).equals(value)) {
            status = false;
        } else if (actual.get(key) != value) {
            status = false;
        }

        String errorDetails = String.format("Failed to find the value '%s' with key '%s' from the map '%s'", value, key, actual);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();

        return this;
    }

    /**
     * Method to check if the given map is empty.
     *
     * @return a self reference.
     */
    @Override
    public MapAssert<T, V> isEmpty() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (!actual.isEmpty()) {
            status = false;
        }
        String errorDetails = String.format("Map '%s' is not an empty map", actual);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given map is not empty
     *
     * @return a self reference.
     */
    @Override
    public MapAssert<T, V> isNotEmpty() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.isEmpty()) {
            status = false;
        }
        String errorDetails = String.format("Map '%s' is an empty map", actual);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to compare if the two maps are of the same type.
     *
     * @param value comparison map type.
     * @return a self reference.
     */
    @Override
    public MapAssert<T, V> isSameTypeAs(Map<?, ?> value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }

        Map<T, V> actualMapCopy = actual;
        Map<?, ?> valueMapCopy = value;

        T t = null;
        final Optional<T> actualKeyFirst = actual.keySet().stream().findFirst();
        if (actualKeyFirst.isPresent()) {
            t = actualKeyFirst.get();
        }

        Object compareKey = null;
        final Optional<?> valueOneKeySet = value.keySet().stream().findFirst();
        if (valueOneKeySet.isPresent()) {
            compareKey = valueOneKeySet.get();
        }

        V v = null;
        final Optional<V> actualValueFirst = actual.values().stream().findFirst();
        if (actualValueFirst.isPresent()) {
            v = actualValueFirst.get();
        }

        Object compareValue = null;
        final Optional<?> valueTwoValue = value.values().stream().findFirst();
        if (valueTwoValue.isPresent()) {
            compareValue = valueTwoValue.get();
        }

        if (NullCheck.assertNull(String.format("Actual map %s key ", actualMapCopy), t, softAssertion)
                || NullCheck.assertNull(String.format("Comparison map %s key", valueMapCopy), compareKey, softAssertion)) {
            return this;
        }


        if (t.getClass() != compareKey.getClass() || v.getClass() != compareValue.getClass()) {
            status = false;
        }
        String errorDetails = String.format("Maps '%s' and '%s' are not of the same type", actual, value);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }
}
