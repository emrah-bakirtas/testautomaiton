package com.opensource.selenium.page;

import org.openqa.selenium.WebDriver;

import com.opensource.selenium.base.BasePage;
import static com.opensource.selenium.constant.LoginPageConstant.*;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
	}


	
	public MainPage login(String username, String password){
		
		fillInputField(SI_INPUT_UNAME_CSS_SELECTOR, username, false);
		
		fillInputField(SI_INPUT_PSWD_CSS_SELECTOR, password, true);

		return new MainPage(driver);
	}
}
