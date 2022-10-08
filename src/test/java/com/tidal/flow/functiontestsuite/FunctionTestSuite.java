package com.tidal.flow.functiontestsuite;


import com.tidal.flow.assertions.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayAssertionTest.class,
        ArraySoftAssertionTest.class,
        BoolAssertionSoftTest.class,
        BoolAssertionsTest.class,
        DateAssertionSoftTest.class,
        DateAssertionTest.class,
        ListAssertionsSoftTest.class,
        ListAssertionsTest.class,
        MapAssertionSoftTest.class,
        NumberAssertionTest.class,
        StringAssertionTest.class
})
public class FunctionTestSuite {
}
