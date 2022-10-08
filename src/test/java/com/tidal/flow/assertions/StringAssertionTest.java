package com.tidal.flow.assertions;

import com.tidal.flow.assertions.stackbuilder.ErrorStack;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tidal.flow.assertions.Assert.verify;

public class StringAssertionTest {



    @Test
    public void stringEqualsTest() {
        verify("String should be equal to 'hello world'", "hello world").isEqualTo("hello world");
    }

    @Test
    public void instanceOfTest() {
        verify("String should be equal to 'hello world'", "hello world").isInstanceOf(String.class);
    }

    @Test (expected = VerificationError.class)
    public void stringStartsWithTest() {
        verify("String should start with 'world'", "hello world").startsWith("world");
    }

    @Test
    public void stringNullTest() {
        verify("String should be null", getNull()).isNullOrEmpty();
    }

    @Test
    public void stringEmptyTest() {
        verify("String should be null", "").isNullOrEmpty();
    }

    @Test
    public void stringMatchesPatternTest() {
        verify("String should match pattern", "hello world").matchesPattern(".*world.*");
    }

    @Test (expected = VerificationError.class)
    public void stringMatchesPatternFailingTest() {
        verify("String should match pattern", "hello world").matchesPattern(".*wXorld.*");
    }

    @Test
    public void stringEqualsIgnoringCase() {
        verify("String should match ignoring case", "hello world").equalsIgnoringCase("HELLO WORLD");
    }

    @Test
    public void stringCharCountTest() {
        verify("String char count should be 11", "hello world").hasCharCount(11);
    }

    @Test(expected = VerificationError.class)
    public void stringCharCountFailingTest() {
        verify("String char count should be 11", "hello world").hasCharCount(12);
    }

    @Test
    public void stringCharCountIgnoringWhiteSpacesTest() {
        verify("String char count should be 11", "hello \n 2343 world -+ _ 1234 &^&*").hasAlphaNumericCharCount(18);
    }

    @Test (expected = VerificationError.class)
    public void stringSoftAssertMultipleTest() {
        Soft.verify("String should be equal to 'hello world'", "hello world").startsWith("world").endsWith("hello").isEqualTo("hello");
        new ErrorStack().execute();
    }

    @Test (expected = VerificationError.class)
    public void stringStartsWithSoftAssertion() {
        Soft.verify("String should start with word 'world'", "hello world").startsWith("world");
        new ErrorStack().execute();
    }

    @Test (expected = VerificationError.class)
    public void stringEndsWithSoftAssertion() {
        Soft.verify("String should end with 'hello'", "hello world").endsWith("hello");
        new ErrorStack().execute();
    }

    @Test (expected = VerificationError.class)
    public void stringEndsWithTest() {
        Soft.verify("String should end with 'hello'", getList()).contains("how");
        new ErrorStack().execute();
    }


    @Test (expected = VerificationError.class)
    public void stringEndsWithNullAssertion() {
        Soft.verify("String should end with 'hello'", getNull()).endsWith("hello");
        new ErrorStack().execute();
    }

    @Test //(expected = VerificationError.class)
    public void stringEndsWithNull() {
        Soft.verify("String should end with 'hello'", "hello").endsWith("");
        new ErrorStack().execute();
    }

    @Test (expected = VerificationError.class)
    public void stringEndsWithNullIsNotNull() {
        Soft.verify("The string output should not be null'", getNull()).isNotNull();
        new ErrorStack().execute();
    }

    @Test (expected = VerificationError.class)
    public void stringEndsWithNullIsNotNullOrEmpty() {
        Soft.verify("The string output should not be null", getNull()).isNotNullOrEmpty();
        new ErrorStack().execute();
    }

    @Test (expected = VerificationError.class)
    public void stringContainsOtherChars() {
        Soft.verify("The string should contain only letters", "hello \n 99 world").containsOnlyLetters();
        new ErrorStack().execute();
    }

    @Test (expected = VerificationError.class)
    public void stringContainsOnlyNumbers() {
        Soft.verify("The string should contain only numbers", "232323 abcd").containsOnlyNumbers();
        new ErrorStack().execute();
    }

    @Test (expected = VerificationError.class)
    public void stringContainsNumbersOfSize() {
        Soft.verify("The string should contain numbers of size 6", "232323 abcd").containsNumbersOfSize(7);
        new ErrorStack().execute();
    }

    static String getNull() {
        return null;
    }

    private List<String> getList(){
        List<String> theList = new ArrayList<>();
        theList.add("hello");
        theList.add("world");

        return theList;
    }
}
