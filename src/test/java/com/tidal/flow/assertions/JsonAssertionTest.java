package com.tidal.flow.assertions;

import org.junit.Test;

import static com.tidal.flow.assertions.Assert.verify;

public class JsonAssertionTest {

    @Test(expected = VerificationError.class)
    public void invalidJsonTest() {
        String invalidJson = "[{name=ME910C1-AU, url=, version=30.00.407-P0B.400103}, {name=Captis Bootloader, url=, version=0.7}, {name=Config Schema, url=, version=1.00.00}]";
        verify("Invalid Json Test", invalidJson).isValidJson();
    }

    @Test
    public void validJsonTest() {
        String invalidJson = "[{\"name\":\"ME910C1-AU\",\"version\":\"30.00.407-P0B.400103\",\"url\":\"\"},{\"name\":\"Captis Bootloader\",\"version\":\"0.7\",\"url\":\"\"},{\"name\":\"Config Schema\",\"version\":\"1.00.00\",\"url\":\"\"}]";
        verify("Valid Json Test", invalidJson).isValidJson();
    }

    @Test
    public void matchingJsonTest() {
        String jsonExpected = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"age\": 34,\n" +
                "        \"samples\":[1, 2, 3]" +
                "    }\n" +
                "}";
        String jsonActual = "{   \n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"age\": 34,\n" +
                "        \"samples\":[1, 2, 3]," +
                "        \"fullName\": \"John Miles\"\n" +
                "    }\n" +
                "}";
        verify("Json matching test", jsonExpected).matchesJson(jsonActual);
    }

    @Test
    public void matchingJsonTestForArrays() {
        String jsonExpected = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"age\": 34,\n" +
                "        \"samples\":[1, 2, 3]" +
                "    }\n" +
                "}";
        String jsonActual = "{   \n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"age\": 34,\n" +
                "        \"samples\":[1, 2, 3]," +
                "        \"fullName\": \"John Miles\"\n" +
                "    }\n" +
                "}";
        verify("Json matching test", jsonExpected).matchesJson(jsonActual);
    }

    @Test (expected = VerificationError.class)
    public void misMatchJsonTest() {
        String jsonExpected = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"age\": 34\n" +
                "    }\n" +
                "}";
        String jsonActual = "{   \n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"age\": 35,\n" +
                "        \"fullName\": \"John Miles\"\n" +
                "    }\n" +
                "}";
        verify("Json matching test", jsonExpected).matchesJson(jsonActual);
    }
}
