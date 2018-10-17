package com.opensource.selenium.page;

import org.openqa.selenium.WebDriver;

import com.opensource.selenium.base.BasePage;
import static com.opensource.selenium.constant.MainPageConstant.*;

public class MainPage extends BasePage {
	
	public <T> MainPage(WebDriver driver) {
        
    	super(driver);
    }
	
	public LoginPage goToLoginPage(){
		
		clickElement(I_SIGNIN_LINK_CSS_SELECTOR);

		return new LoginPage(driver);
	}
	
	public SearchResultsPage searchAMovie(String movieName){
		
		fillInputField(I_SEARCHBOX_CSS_SELECTOR, movieName, true);

		return new SearchResultsPage(driver);
	}
	
	public ProfilePage goToProfilePage(){
		
		clickElement(I_USERNAME_LINK_CSS_SELECTOR);

		return new ProfilePage(driver);
	}
	
	public MainPage logOut(){
		
		clickElement(I_SIGNOUT_LINK_CSS_SELECTOR);

		return this;
	}
}
