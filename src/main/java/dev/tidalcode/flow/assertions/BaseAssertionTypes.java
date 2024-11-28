package dev.tidalcode.flow.assertions;

/**
 * The BaseAssertionTypes interface needs to be implemented as the common assertion type class for all assertion types
 * @param <T> binds the assertion type to the correct assertion class
 */
public interface BaseAssertionTypes<T> {
     void isEqualTo(T value);

     void isNotEqualTo(T value);

     void isNotNull();

     void isInstanceOf(Class<?> klass);
}
