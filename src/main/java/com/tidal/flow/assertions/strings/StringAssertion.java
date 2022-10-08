package com.tidal.flow.assertions.strings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tidal.flow.assertions.NullCheck;
import com.tidal.flow.assertions.stackbuilder.ErrorStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAssertion extends StringAssert<String> {

    public StringAssertion(String description, String actual, boolean softAssertion) {
        this.description = description;
        this.actual = actual;
        this.softAssertion = softAssertion;
    }

    /**
     * Method to check if the given input string is not null or empty.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> isNotNullOrEmpty() {
        if (actual == null || actual.equals("")) {
            status = false;
        }
        String errorDetails = "Given value is either null or empty";
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given input string is null or empty.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> isNullOrEmpty() {
        if (actual != null) {
            if(!actual.isEmpty()){
                status = false;
            }
        }
        String errorDetails = "Given value is not null or empty";
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the given string contains the value
     * @param value to be checked.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> contains(String value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)
                || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (!actual.contains(value)) {
            status = false;
        }
        String errorDetails = String.format("'%s' does not contain value '%s'", actual, value);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the actual value starts with the comparison value.
     * @param value to be checked with the 'startsWith' condition.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> startsWith(String value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)
                || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (!actual.startsWith(value)) {
            status = false;
        }
        String errorDetails = String.format("'%s' does not start with '%s'", actual, value);

        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the actual value ends with the comparison value.
     * @param value to be checked with the 'endsWith' condition.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> endsWith(String value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)
                || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (!actual.endsWith(value)) {
            status = false;
        }
        String errorDetails = String.format("'%s' does not end with '%s'", actual, value);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the actual value matches the given pattern.
     * @param charPattern the pattern to be matched against.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> matchesPattern(String charPattern) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)
                || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, charPattern, softAssertion)) {
            return this;
        }
        Pattern pattern = Pattern.compile(charPattern);
        Matcher matcher = pattern.matcher(actual);
        if (!matcher.matches()) {
            status = false;
        }
        String errorDetails = String.format(" Pattern '%s' could not be matched with given value '%s'", charPattern, actual);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check if the 'actual' value contains the comparison values 'ignoring' case.
     * @param value the value to be compared against.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> equalsIgnoringCase(String value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)
                || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }
        if (!actual.equalsIgnoreCase(value)) {
            status = false;
        }
        String errorDetails = String.format("'%s' is not equals to '%s' [Case Ignored]", actual, value);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check the character count in the 'actual' value.
     * @param value the total count to be verified against.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> hasCharCount(int value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        if (actual.length() != value) {
            status = false;
        }
        String errorDetails = String.format("Expected character count was '%d' but actual was '%d'", value, actual.length());
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to find the total count of alpha-numeric characters ignoring other characters.
     * @param value - the total count to be verified against.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> hasAlphaNumericCharCount(int value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        int actualLength = actual.replaceAll("[^0-9a-zA-Z]", "").length();
        if (actualLength != value) {
            status = false;
        }
        String errorDetails = String.format("Expected character count was '%d' but actual was '%d'", value, actualLength);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to verify if the 'actual' value contains only letters.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> containsOnlyLetters() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        int parsedLength = actual.replaceAll("[ (\n)a-zA-Z]", "").length();
        if(parsedLength > 0){
           status = false;
        }
        String errorDetails = String.format("String '%s' contains characters other than letters", actual.replaceAll("[\n\t\r]", " "));
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to verify if the 'actual' value contains only numbers.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> containsOnlyNumbers() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        int parsedLength = actual.replaceAll("[ 0-9]", "").length();
        if(parsedLength > 0){
            status = false;
        }
        String errorDetails = String.format("String '%s' contains characters other than numbers", actual.replaceAll("[\n\t\r]", " "));
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }

    /**
     * Method to check the count of number characters in the given string.
     * @param value - the count to be verified against.
     * @return a self reference.
     */
    @Override
    public StringAssert<String> containsNumbersOfSize(int value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }
        int parsedLength = actual.replaceAll("[^0-9]", "").length();
        if(parsedLength != value){
            status = false;
        }
        String errorDetails = String.format("Expected count of numbers in actual string '%s' was '%s'; but got '%s'", actual, value, parsedLength);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }


    /**
     * Method to verify if the given string is a valid Json
     * @return a self reference
     */
    @Override
    public StringAssert<String> isValidJson() {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion)) {
            return this;
        }

        String jsonValidationError = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(actual);
        } catch (JsonProcessingException e) {
            jsonValidationError = e.getMessage();
            status = false;
        }

        String errorDetails = String.format("Supplied string: %s \n is not a valid json. \n %s", actual, jsonValidationError);
        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;

    }

    /**
     * Method to verify the given json strings would match.
     * @param value the json string to be verified.
     * @return a self reference
     */
    @Override
    public StringAssert<String> matchesJson(String value) {
        if (NullCheck.assertNull(ACTUAL_TYPE_DESCRIPTION, actual, softAssertion) || NullCheck.assertNull(EXPECTED_TYPE_DESCRIPTION, value, softAssertion)) {
            return this;
        }

        String errorDetails = "";
        boolean jsonParseException_Actual = false;
        boolean jsonParseException_Value = false;

        JsonNode mapperForActual = null;
        JsonNode mapperForValue = null;

        ObjectMapper mapper = new ObjectMapper();

        try{
            mapperForActual = mapper.readTree(actual);
        } catch (JsonProcessingException e) {
            jsonParseException_Actual = true;
            errorDetails = String.format("Matching Json comparison cannot be performed as 'actual' input json is not valid. \n %s", e.getMessage());
        }

        try{
            mapperForValue = mapper.readTree(value);
        } catch (JsonProcessingException e) {
            jsonParseException_Value = true;
            errorDetails = String.format("Matching Json comparison cannot be performed as 'expected' input json is not valid. \n %s", e.getMessage());
        }

        if(jsonParseException_Actual || jsonParseException_Value){
            status = false;
        } else {
            status = mapperForActual.equals(mapperForValue);
            errorDetails = String.format("Actual value %s is not equal to comparison value %s", actual, value);
        }

        ErrorStack.builder(errorDetails, description)
                .withStackTrace(Thread.currentThread().getStackTrace())
                .withAssertionStatus(softAssertion, status)
                .build();
        return this;
    }


}
