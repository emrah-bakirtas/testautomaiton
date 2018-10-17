package com.opensource.selenium.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FileUtil {

    private static FileUtil instance;
    private static Configuration config = Configuration.getInstance();

    public static FileUtil getInstance() {

        if (instance == null) {

            createInstance();
        }

        return instance;
    }

    private static synchronized void createInstance() {

        if (instance == null) {

            instance = new FileUtil();
        }
    }

    public HashMap<String, String> readDataFromExcel(String testSuite, String testCase) {

        HashMap data = new HashMap();
        DataFormatter formatter = new DataFormatter();

        try {

            FileInputStream file = new FileInputStream(new File(config.getExcelInputPath()));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(testSuite);

            if (sheet != null) {

                Iterator<Row> rowIterator = sheet.iterator();
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                cellIterator.next();
                int count = 1;

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    if (testCase.equalsIgnoreCase(cell.getStringCellValue())) {

                        break;
                    }

                    count++;
                }

                while (rowIterator.hasNext()) {

                    row = rowIterator.next();
                    String key = formatter.formatCellValue(row.getCell(0));
                    String value = formatter.formatCellValue(row.getCell(count));
                    data.put(key, value);
                }

                file.close();
            } else {

                throw new RuntimeException("Test sheet not found!");
            }
        } catch (Exception e) {

            throw new RuntimeException(e.getStackTrace() + "\n" + e.getMessage());
        }

        return data;
    }

    public ArrayList<String[]> getTestList() {

        ArrayList<String[]> testWithPreTestList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try {

            FileInputStream file = new FileInputStream(new File(config.getExcelInputPath()));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(config.getRunSheetName());

            if (sheet != null) {

                Iterator<Row> rowIterator = sheet.iterator();
                Row row = rowIterator.next();

                while (rowIterator.hasNext()) {

                    row = rowIterator.next();
                    String[] testWithPreTest = new String[4];

                    String firstColumnValue = formatter.formatCellValue(row.getCell(0));
                    String secondColumnValue = formatter.formatCellValue(row.getCell(1));
                    String fourthColumnValue = formatter.formatCellValue(row.getCell(3));
                    String fifthColumnValue = formatter.formatCellValue(row.getCell(4));

                    if (!firstColumnValue.equals("") && !secondColumnValue.equals("")) {

                        testWithPreTest[0] = firstColumnValue;
                        testWithPreTest[1] = secondColumnValue;

                        if (!fourthColumnValue.equals("") && !fifthColumnValue.equals("")) {

                            testWithPreTest[2] = fourthColumnValue;
                            testWithPreTest[3] = fifthColumnValue;
                        } else {

                            testWithPreTest[2] = null;
                            testWithPreTest[3] = null;
                        }

                    } else {

                        throw new RuntimeException("Test suite or test case not found!");
                    }

                    testWithPreTestList.add(testWithPreTest);
                }

                file.close();
            } else {

                throw new RuntimeException("RunTest sheet not found!");
            }
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return testWithPreTestList;
    }

    public void writeResultToExcel(String testSuite, String testCase, String result, String output) {

        DataFormatter formatter = new DataFormatter();

        try {
            FileInputStream fis = new FileInputStream(new File(config.getExcelOutputPath()));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(config.getResultSheetName());

            int rowNum = 0;

            if (sheet != null) {

                Iterator<Row> rowIterator = sheet.iterator();

                while (rowIterator.hasNext()) {

                    Row row = rowIterator.next();
                    String testSuiteCell = formatter.formatCellValue(row.getCell(0));
                    String testCaseCell = formatter.formatCellValue(row.getCell(1));

                    if ((testSuiteCell.equalsIgnoreCase(testSuite) && testCaseCell.equalsIgnoreCase(testCase)) || testSuiteCell.equals("")) {

                        break;
                    }

                    rowNum++;
                }
            } else {

                throw new RuntimeException("Results sheet not found!");
            }

            fis.close();

            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            Cell testSuiteCell = row.createCell(colNum++);
            Cell testCaseCell = row.createCell(colNum++);
            Cell successCell = row.createCell(colNum++);
            Cell outputCell = row.createCell(colNum++);
            CellStyle style = workbook.createCellStyle();

            if ("true".equalsIgnoreCase(result)) {

                style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            } else if ("false".equalsIgnoreCase(result)) {

                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            } else {

                style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            }
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);

            testSuiteCell.setCellValue(testSuite);
            testCaseCell.setCellValue(testCase);
            successCell.setCellValue(result);
            successCell.setCellStyle(style);
            outputCell.setCellValue(output);

            FileOutputStream fos = new FileOutputStream(config.getExcelOutputPath());
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[][] readInputFromResultExcel(String testSuite, String testCase) {

        DataFormatter formatter = new DataFormatter();
        String[][] testResults = new String[1][2];

        try {

            FileInputStream file = new FileInputStream(new File(config.getExcelOutputPath()));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(config.getResultSheetName());

            if (sheet != null) {

                Iterator<Row> rowIterator = sheet.iterator();
                Row row = rowIterator.next();

                while (rowIterator.hasNext()) {

                    row = rowIterator.next();
                    String testSuiteCell = formatter.formatCellValue(row.getCell(0));
                    String testCaseCell = formatter.formatCellValue(row.getCell(1));

                    if (testSuite.equalsIgnoreCase(testSuiteCell) && testCase.equalsIgnoreCase(testCaseCell)) {

                        testResults[0][0] = formatter.formatCellValue(row.getCell(2));
                        testResults[0][1] = formatter.formatCellValue(row.getCell(3));

                        break;
                    }
                }

                file.close();
            }
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return testResults;
    }
}
