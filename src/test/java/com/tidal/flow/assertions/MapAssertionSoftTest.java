package com.tidal.flow.assertions;

import com.tidal.flow.assertions.stackbuilder.ErrorStack;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapAssertionSoftTest {

    @Test (expected = AssertionError.class)
    public void mapContainsKeysFalseTest(){
        Soft.verify("Map should contain key 'hello, happy, java, javascript'", getMap()).containsKeys("hellow", "happy", "java", "javascript");
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void mapContainsValuesFalseTest(){
        Soft.verify("Map should contain values 'hello, world'", getMap()).containsValues("hellow", "happyy").containsKeys("wow", "woww").isNotNull();
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void nullMapTest(){
        Soft.verify("Map should contain values 'hello, world'", getNullMap()).containsValues("hello", "happy").containsKeys("world", "learning").isNotNull();
        new ErrorStack().execute();
    }

    @Test
    public void emptyMapTest(){
        Soft.verify("Map should not be empty", getMap()).isNotEmpty();
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void emptyMapEmptyTest(){
        Soft.verify("Map should not be empty", getEmptyMap()).isNotEmpty();
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void emptyMapFailTest(){
        Soft.verify("Map should not be empty", getMap()).isEmpty();
        new ErrorStack().execute();
    }

    @Test
    public void mapTypeTest(){
        Soft.verify("Map should be of same type", getMap()).isSameTypeAs(getMapTwo());
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void mapTypeTestFail(){
        Soft.verify("Map should be of same type", getMap()).isSameTypeAs(getDiffMapIntType());
        new ErrorStack().execute();
    }

    @Test (expected = AssertionError.class)
    public void mapTypeTestFailDiffType(){
        Soft.verify("Map should be of same type", getMap()).isSameTypeAs(getDiffMapStringIntType());
        new ErrorStack().execute();
    }

    public Map<String, String> getNullMap(){
        return null;
    }

    public Map<String, String> getMap(){
        Map<String, String> theMap = new HashMap<>();
        theMap.put("hello", "world");
        theMap.put("happy", "learning");

        return theMap;
    }

    public Map<String, String> getEmptyMap(){
        return new HashMap<>();
    }

    public Map<String, String> getMapTwo(){
        Map<String, String> theMap = new HashMap<>();
        theMap.put("hello", "world");
        theMap.put("happy", "learning");
        theMap.put("test", "automation");

        return theMap;
    }

    public Map<Integer, Integer> getDiffMapIntType(){
        Map<Integer, Integer> theMap = new HashMap<>();
        theMap.put(1, 2);
        theMap.put(3, 4);
        theMap.put(5, 6);

        return theMap;
    }

    public Map<String, Integer> getDiffMapStringIntType(){
        Map<String, Integer> theMap = new HashMap<>();
        theMap.put("first", 2);
        theMap.put("second", 4);
        theMap.put("third", 6);

        return theMap;
    }
}
