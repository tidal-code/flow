package dev.tidalcode.flow.assertions;

import dev.tidalcode.flow.assertions.stackbuilder.ErrorStack;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static dev.tidalcode.flow.assertions.Assert.verify;


public class ListAssertionsSoftTest {

    @After
    public void runVerification() {
        new ErrorStack().execute();
    }

    @Test
    public void listContainsTest() {
        try {
            Soft.verify("The list should contain 'hello' and 'world'", getList()).contains("hellos", "world");
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("falseValueTrueTestFail", e.getMessage())
                    .contains("Verification Failed: Element 'hellos' not in the list [hello, world, welcome]")
                    .contains("Description: The list should contain 'hello' and 'world'")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.listContainsTest");
        }
    }

    @Test
    public void listContainsASubListTest() {
        verify("The list should contain sublist", getList()).containsList(getSubList());
    }

    @Test
    public void listContainsASuperListTest() {
        try {
            Soft.verify("The list should not contain super list", getSubList()).containsList(getList());
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("The list should not contain super list", e.getMessage())
                    .contains("Verification Failed: List '[hello, world]' does not contain element 'welcome' from list '[hello, world, welcome]'")
                    .contains("Description: The list should not contain super list")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.listContainsASuperListTest");
        }
    }

    @Test
    public void listContainsElementsSoftTest() {
        try {
            Soft.verify("The list should contain 'hello', 'world', 'happy', 'learning'", getList()).contains("hello", "world", "happy", "learning");
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("The list should contain 'hello', 'world', 'happy', 'learning'", e.getMessage())
                    .contains("Verification Failed: Element 'happy' not in the list [hello, world, welcome]")
                    .contains("Description: The list should contain 'hello', 'world', 'happy', 'learning'")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.listContainsElementsSoftTest");

            verify("The list should contain 'hello', 'world', 'happy', 'learning'", e.getMessage())
                    .contains("Verification Failed: Element 'learning' not in the list [hello, world, welcome]")
                    .contains("Description: The list should contain 'hello', 'world', 'happy', 'learning'")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.listContainsElementsSoftTest");
        }
    }

    @Test
    public void listNotContainsSoftTest() {
        Soft.verify("The list should not contain 'happy', 'learning'", getList()).notContains("happy", "learning");
    }

    @Test
    public void listNotContainsFalseSoftTest() {
        try {
            Soft.verify("The list should not contain 'hello', 'world'", getList()).notContains("hello");
            Soft.verify("The list should not contain 'hello', 'world'", getList()).notContains("world");
            Soft.verify("The list should not contain 'hello', 'world'", getList()).notContains("hello");
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("The list should not contain 'hello', 'world'", e.getMessage())
                    .contains("Verification Failed: Element 'hello' is contained in the list [hello, world, welcome]")
                    .contains("Description: The list should not contain 'hello', 'world'")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.listNotContainsFalseSoftTest");

            verify("The list should not contain 'hello', 'world'", e.getMessage())
                    .contains("Verification Failed: Element 'world' is contained in the list [hello, world, welcome]")
                    .contains("Description: The list should not contain 'hello', 'world'")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.listNotContainsFalseSoftTest");

            verify("The list should not contain 'hello', 'world'", e.getMessage())
                    .contains("Verification Failed: Element 'world' is contained in the list [hello, world, welcome]")
                    .contains("Description: The list should not contain 'hello', 'world'")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.listNotContainsFalseSoftTest");

        }
    }

    @Test
    public void subListTest() {
        try {
            Soft.verify("The list should not contain 'hello', 'world'", getList()).isSubListOf(getSubList());
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("falseValueTrueTestFail", e.getMessage())
                    .contains("Verification Failed: List '[hello, world, welcome]' expected to be a sublist of '[hello, world]''")
                    .contains("Description: The list should not contain 'hello', 'world'")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.subListTest");
        }
    }


    @Test
    public void listSizeTest() {
        try {
            Soft.verify("The list size is two and contains 'happy learning'", getList()).hasSize(3).contains("happy learning");
            new ErrorStack().execute();
        } catch (AssertionError e) {
            verify("falseValueTrueTestFail", e.getMessage())
                    .contains("Verification Failed: Element 'happy learning' not in the list [hello, world, welcome]")
                    .contains("Description: The list size is two and contains 'happy learning'")
                    .contains("dev.tidalcode.assertions.flow.ListAssertionsSoftTest.listSizeTest");
        }
    }

    @Test
    public void listSizeGreaterThan() {
        Soft.verify("The list should have a size greater than two", getList()).hasSizeGreaterThan(2).contains("hello", "world");
        verify("The list should have a size greater than two", getList()).hasSizeGreaterThan(2).contains("hello", "world");
    }

    private List<String> getList() {
        List<String> theList = new ArrayList<>();
        theList.add("hello");
        theList.add("world");
        theList.add("welcome");

        return theList;
    }

    private List<String> getSubList() {
        List<String> theList = new ArrayList<>();
        theList.add("hello");
        theList.add("world");

        return theList;
    }

}
