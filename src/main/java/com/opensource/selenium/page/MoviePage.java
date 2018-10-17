package com.opensource.selenium.page;

import org.openqa.selenium.WebDriver;

import com.opensource.selenium.base.BasePage;
import static com.opensource.selenium.constant.MoviePageConstant.*;

public class MoviePage extends BasePage {

	public MoviePage(WebDriver driver) {
		
		super(driver);
	}
	
	public MoviePage voteForTheMovie(String movieScore){
		
		if (isElementExist(FI_EDITSCORE_LINK_CSS_SELECTOR)){
			
			clickElement(FI_EDITSCORE_LINK_CSS_SELECTOR);
		}
		
		clickElement(SCORE_BOX94851_CSS_SELECTOR);
		
		fillInputField(SCORE_BOX94851_CSS_SELECTOR, movieScore, false);
		
		clickElement(FI_SUBMIT_LINK_CSS_SELECTOR);

		return this;
	}
}
