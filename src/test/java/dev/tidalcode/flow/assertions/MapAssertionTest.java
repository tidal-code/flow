package dev.tidalcode.flow.assertions;

import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static dev.tidalcode.flow.assertions.Assert.verify;

public class MapAssertionTest {

    @Test
    public void mapEqualsTest(){
        verify("maps should be equal", getMap()).isEqualTo(getMapSimilar());
    }

    @Test
    public void mapContainsKeyTest() {
        verify("Map should contain key 'hello'", getMap()).containsKeys("hello");
    }

    @Test
    public void mapContainsKeyValueStringTypeTest() {
        verify("Map should contain key value paid 'hello', 'world'", getMap()).containsKeyAndValue("hello", "world");
    }

    @Test
    public void mapContainsKeyValueIntTypeTest() {
        verify("Map should contain key value paid '1', '2'", getDiffMapIntType()).containsKeyAndValue(1, 2);
    }

    @Test
    public void propertiesMapTest() {
        verify("Map should contain key value paid '1', '2'", getPropertiesMap()).containsKeyAndValue("class", "test_button_class");
    }

    @Test (expected = AssertionError.class)
    public void mapContainsKeyValueNullTypeTest() {
        verify("Test will null key not existing in map", getDiffMapIntType()).containsKeyAndValue(null, 2);
    }

    @Test (expected = AssertionError.class)
    public void mapContainsKeyValueNullValueTypeTest() {
        verify("Test with null value not existing", getDiffMapIntType()).containsKeyAndValue(1, null);
    }

    @Test (expected = AssertionError.class)
    public void mapContainsKeyValueNullValueExistingTypeTest() {
        verify("Test with null value existing in map", getDiffMapIntType()).containsKeyAndValue(7, null);
    }

    @Test
    public void mapContainsKeyValueNullResultingKeyExistingTypeTest() {
        verify("Test with null key", getDiffMapIntType()).containsKeyAndValue(null, 8);
    }

    @Test
    public void sameMapTest(){
        verify("Same map type test", getMap()).isSameTypeAs(getMapSimilar());
    }

    @Test(expected = AssertionError.class)
    public void mapContainsKeyFalseTest() {
        verify("Map should contain key 'happy'", getMap()).containsKeys("Java");
    }

    @Test (expected = AssertionError.class)
    public void mapContainsKeysTest() {
        verify("Map should contain key 'hello'", getMap()).containsKeys("hellow", "happyy");
    }


    @Test
    public void nullMapTest() {
        try {
            Soft.verify("Map should contain values 'hello, world'", getNullMap()).containsValues("hello", "happy").containsKeys("world", "learning").isNotNull();
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("null map test", e.getMessage())
                    .contains("Description: Verification failure due to null input")
                    .contains("dev.tidalcode.assertions.flow.MapAssertionTest.nullMapTest");
        }

    }

    @Test (expected = AssertionError.class)
    public void emptyMapTest() {
        Soft.verify("Map should not be empty", getMap()).isNotEmpty();
        new ErrorStack().execute();
    }

    @Test
    public void emptyMapEmptyTest() {
        try {
            Soft.verify("Map should not be empty", getEmptyMap()).isNotEmpty();
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("empty map test", e.getMessage())
                    .contains("Verification Failed: Map '{}' is an empty map")
                    .contains("Description: Map should not be empty")
                    .contains("dev.tidalcode.assertions.flow.MapAssertionTest.emptyMapEmptyTest");
        }
    }

    @Test
    public void emptyMapFailTest() {
        try {
            Soft.verify("Map should not be empty", getMap()).isEmpty();
        } catch (AssertionError e) {
            verify("empty map fail test", e.getMessage())
                    .contains("Verification Failed: Map '{happy=learning, hello=world}' is not an empty map")
                    .contains("Description: Map should not be empty")
                    .contains("dev.tidalcode.assertions.flow.MapAssertionTest.emptyMapFailTest");
        }
    }

    public Map<String, String> getNullMap() {
        return null;
    }

    public Map<String, String> getMap() {
        Map<String, String> theMap = new HashMap<>();
        theMap.put("hello", "world");
        theMap.put("happy", "learning");

        return theMap;
    }

    public Map<String, String> getMapSimilar() {
        Map<String, String> theMap = new HashMap<>();
        theMap.put("hello", "world");
        theMap.put("happy", "learning");

        return theMap;
    }

    public Map<String, String> getEmptyMap() {
        return new HashMap<>();
    }

    public Map<String, String> getMapTwo() {
        Map<String, String> theMap = new HashMap<>();
        theMap.put("hello", "world");
        theMap.put("happy", "learning");
        theMap.put("test", "automation");

        return theMap;
    }

    public Map<Integer, Integer> getDiffMapIntType() {
        Map<Integer, Integer> theMap = new HashMap<>();
        theMap.put(1, 2);
        theMap.put(3, 4);
        theMap.put(5, 6);
        theMap.put(7, null);
        theMap.put(null, 8);

        return theMap;
    }

    public Map<String, Integer> getDiffMapStringIntType() {
        Map<String, Integer> theMap = new HashMap<>();
        theMap.put("first", 2);
        theMap.put("second", 4);
        theMap.put("third", 6);

        return theMap;
    }

    private Map<String, String> getPropertiesMap(){
        Map<String, String> theMap = new HashMap<>();
        //{class=test_button_class, id=test_button_id, name=test_button, onclick=changeValue(), style=border: 1px dotted green;, type=button}
        theMap.put("class", "test_button_class");
        theMap.put("style", "border: 1px dotted green");

        return theMap;
    }
}
