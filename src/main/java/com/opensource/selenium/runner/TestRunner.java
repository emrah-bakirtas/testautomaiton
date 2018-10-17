package com.opensource.selenium.runner;

import com.opensource.selenium.base.BaseTest;
import com.opensource.selenium.test.ExampleTest;
import com.opensource.selenium.util.FileUtil;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.Before;

import java.util.ArrayList;

public class TestRunner {

    private static FileUtil fileUtil = FileUtil.getInstance();
    private static TestSuite suite = new TestSuite();
    private static final String packagePath = "com.opensource.selenium.main.";

    @Before
    public static TestSuite suite() throws ClassNotFoundException {

        ArrayList<String[]> tests = fileUtil.getTestList();
        int testItemCount = tests.size();

        for (int i = 0; i < testItemCount; i++){

            String testSuite = tests.get(i)[0];
            String testCase = tests.get(i)[1];
            String preTestSuite = tests.get(i)[2];
            String preTestCase = tests.get(i)[3];

            String testSuiteAndCase = testSuite + "_" + testCase;
            String preTestSuiteAndCase = preTestSuite + "_" + preTestCase;

            Class<?> clazz = Class.forName(packagePath + testSuite);

            if (preTestSuite != null && preTestCase != null){

                BaseTest.testsWithPreTests.put(testSuiteAndCase, preTestSuiteAndCase);
            }

            else {

                BaseTest.testsWithPreTests.put(testSuiteAndCase, null);
            }

            BaseTest.testCases.add(testCase);
            suite.addTest(new JUnit4TestAdapter(clazz));
        }

        return suite;
    }
}
