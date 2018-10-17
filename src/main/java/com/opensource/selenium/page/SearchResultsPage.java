package com.opensource.selenium.page;

import org.openqa.selenium.WebDriver;
import com.opensource.selenium.base.BasePage;

import static com.opensource.selenium.constant.SearchResultsPageConstant.*;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {

        super(driver);
    }

    public MoviePage goToMoviePage() {

        clickElement(SR_RESULT_IMG_CSS_SELECTOR, 0);

        return new MoviePage(driver);
    }
}
