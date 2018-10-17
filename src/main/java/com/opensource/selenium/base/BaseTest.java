package com.opensource.selenium.base;

import com.opensource.selenium.util.Configuration;
import com.opensource.selenium.util.FileUtil;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    protected static FileUtil fileUtil = FileUtil.getInstance();
    protected static Configuration config = Configuration.getInstance();
    protected static HashMap<String, String> data = null;

    private static final int DEFAULT_WAIT = 10;
    private static String result;

    public static String testSuite = null;
    public static String testCase = null;
    public static String input = null;
    public static String output = null;
    public static HashMap<String, String> testsWithPreTests = new HashMap<>();
    public static ArrayList<String> testCases = new ArrayList<>();

    @BeforeClass
    public static void setUp() {

        System.setProperty("webdriver.chrome.driver", config.getChromedriverPath());

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before
    public void before() {

        result = "true";

        testSuite = getClass().getSimpleName();
        testCase = testCases.get(0);
        testCases.remove(0);

        String preTestSuiteAndCase = testsWithPreTests.get(testSuite + "_" + testCase);

        if (null != preTestSuiteAndCase) {

            String preTestSuite = preTestSuiteAndCase.split("_")[0];
            String preTestCase = preTestSuiteAndCase.split("_")[1];
            String[][] preTestResult = fileUtil.readInputFromResultExcel(preTestSuite, preTestCase);
            String preTestSuccess = preTestResult[0][0];
            String preTestOutput = preTestResult[0][1];

            if ("true".equalsIgnoreCase(preTestSuccess)) {

                BaseTest.input = preTestOutput;
                result = "true";
            } else if ("false".equalsIgnoreCase(preTestSuccess)) {

                result = "not run";
                throw new RuntimeException("PreTest (" + preTestSuite + " " + preTestCase + ") for " + testSuite + " " + testCase + " is not success!");
            } else {

                result = "not run";
                throw new RuntimeException("Run " + preTestSuite + " " + preTestCase + " first before " + testSuite + " " + testCase + "!");
            }
        }

        data = fileUtil.readDataFromExcel(testSuite, testCase);
    }

    @AfterClass
    public static void tearDown() {

        input = null;
        driver.quit();
        fileUtil.writeResultToExcel(testSuite, testCase, result, output);
        output = null;
    }

    @Rule
    public TestRule testWatcher = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {

            if (!"not run".equalsIgnoreCase(result)) {

                result = "false";
            }
        }
    };
}