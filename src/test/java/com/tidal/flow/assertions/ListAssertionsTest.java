package com.tidal.flow.assertions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tidal.flow.assertions.Assert.verify;


public class ListAssertionsTest {

    @Test
    public void listContainsListTest(){
        verify("The list should contain list", getList()).containsList(getList());
    }

    @Test
    public void listContainsASubListTest(){
        verify("The list should contain sublist", getList()).containsList(getSubList());
    }


    @Test
    public void superListTest(){
        try {
            verify("The list should not contain 'hello', 'world'", getSubList()).isSuperListOf(getList());
        } catch(VerificationError e){
            verify("Verification Message Test", e.getMessage())
                    .contains("Verification Failed: List '[hello, world]' expected to be a super list of '[hello, world, welcome]''")
                    .contains("Description: The list should not contain 'hello', 'world'")
                    .contains("com.tidal.flow.assertions.ListAssertionsTest.superListTest");
        }
    }

    @Test
    public void instanceOfTest(){
        verify("the input must be an instance of list", getList()).isInstanceOf(ArrayList.class);
    }

    @Test(expected = VerificationError.class)
    public void typeOfTest(){
        verify("the input must be an instance of String list", getList()).ofType(Integer.class);
    }

    @Test
    public void listSizeFalseTest(){
        verify("The list should have a size of three", getList()).hasSize(3);
    }


    @Test
    public void listSizeLessThan(){
        try{
            verify("The list should have a size less than two", getList()).hasSizeLessThan(2).contains("hello", "world");
        } catch (VerificationError e){
            verify("Verification Message Test", e.getMessage()).contains("Verification Failed: List size expected to be less than '2' but was '2'")
                    .contains("Description: The list should have a size less than two")
                    .contains("com.tidal.flow.assertions.ListAssertionsTest.listSizeLessThan");
        }
    }

    private List<String> getList(){
        List<String> theList = new ArrayList<>();
        theList.add("hello");
        theList.add("world");
        theList.add("welcome");

        return theList;
    }

    private static List<String> getSubList(){
        List<String> theList = new ArrayList<>();
        theList.add("hello");
        theList.add("world");

        return theList;
    }

}
