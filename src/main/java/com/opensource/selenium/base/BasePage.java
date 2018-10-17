package com.opensource.selenium.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opensource.selenium.util.Configuration;

public class BasePage {

    protected WebDriver driver;
    protected Configuration config;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;
        this.config = Configuration.getInstance();
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * navigate to url
     */
    public void navigateTo() {
        driver.get(config.getAppUrl());
    }

    /**
     * Use this method to find element by By
     *
     * @return A WebElement, or an empty if nothing matches
     */
    protected WebElement findElement(By by, int... index) {
        return index.length == 0
                ? driver.findElement(by)
                : driver.findElements(by).get(index[0]);
    }

    /**
     * Use this method to find elements by By
     *
     * @return A list of WebElements, or an empty if nothing matches
     */
    protected List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    /**
     * Use this method click to element
     */
    protected void clickElement(By by, int... index) {
        untilElementAppear(by);

        WebElement element = index.length == 0
                ? findElement(by)
                : findElement(by, index[0]);

        element.click();
    }

    /**
     * Use this method to simulate typing into an element if it is enable.
     * Send enter if pressEnter is true, do nothing otherwise.
     * Note : Before sending operation, element is cleared.
     */
    protected void fillInputField(By by, String text, boolean pressEnter) {
        untilElementAppear(by);

        WebElement element = findElement(by);

        if (element.isEnabled()) {
            element.clear();
            element.sendKeys(text);

            if (pressEnter) {
                element.sendKeys(Keys.ENTER);
            }
        }
    }

    /**
     * Get the visible (i.e. not hidden by CSS) innerText of this element.
     *
     * @return The innerText of this element.
     */
    protected String getTextOfElement(By by, int... index) {
        untilElementAppear(by);

        return index.length == 0
                ? findElement(by).getText()
                : findElement(by, index[0]).getText();
    }

    /**
     * Wait until element appears
     */
    protected void untilElementAppear(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Wait until element disappears
     */
    protected void untilElementDisappear(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Return true if element exist, false otherwise.
     *
     * @return True if element exists, false otherwise.
     */
    protected boolean isElementExist(By by) {
        return findElements(by).size() > 0;
    }
}
