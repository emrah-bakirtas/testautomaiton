package com.opensource.selenium.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration instance = new Configuration();

    private Properties configProps = new Properties();

    private String appUrl;
    private String excelInputPath;
    private String excelOutputPath;
    private String runSheetName;
    private String resultSheetName;
    private String chromedriverPath;

    public static Configuration getInstance() {
        return instance;
    }

    private Configuration() {

        try {
            InputStream is = ClassLoader.getSystemResourceAsStream("config.properties");
            configProps.load(is);

            this.appUrl = configProps.getProperty("app.url");
            this.excelInputPath = configProps.getProperty("excel.input.path");
            this.excelOutputPath = configProps.getProperty("excel.output.path");
            this.runSheetName = configProps.getProperty("run.sheet.name");
            this.resultSheetName = configProps.getProperty("result.sheet.name");
            this.chromedriverPath = configProps.getProperty("chromedriver.path");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String getAppUrl() {
        return appUrl;
    }

    public String getExcelInputPath() {
        return excelInputPath;
    }

    public String getExcelOutputPath() {
        return excelOutputPath;
    }

    public String getRunSheetName() {
        return runSheetName;
    }

    public String getResultSheetName() {
        return resultSheetName;
    }

    public String getChromedriverPath() {
        return chromedriverPath;
    }
}
