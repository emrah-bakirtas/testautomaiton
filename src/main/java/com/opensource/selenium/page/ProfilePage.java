package com.opensource.selenium.page;

import org.openqa.selenium.WebDriver;
import com.opensource.selenium.base.BasePage;
import static com.opensource.selenium.constant.ProfilePageConstant.*;

public class ProfilePage extends BasePage {

	public ProfilePage(WebDriver driver) {
		
		super(driver);
	}
	
	public ProfilePage updatePersonalInformation(String userFirstName,String userLastName, 
												 String email, String city){
		
		clickElement(UP_INFOUPDATELINK_CSS_SELECTOR);
		
		fillInputField(UP_INPUT_FNAME_CSS_SELECTOR, userFirstName, false);
		
		fillInputField(UP_INPUT_LNAME_CSS_SELECTOR, userLastName, false);
		
		fillInputField(UP_INPUT_EMAIL_CSS_SELECTOR, email, false);
		
		fillInputField(UP_INPUT_CITY_CSS_SELECTOR, city, false);
		
		clickElement(UP_UPDATE_SUBMIT_CSS_SELECTOR);

		return this;
	}
	
	public ProfilePage changePassword(String oldPassword, String newPassword){
		
		clickElement(UP_PSWUPDATELINK_SUBMIT_CSS_SELECTOR);
		
		fillInputField(UP_INPUT_OLDPSWD_CSS_SELECTOR, oldPassword, false);
		
		fillInputField(UP_INPUT_PSWD1_CSS_SELECTOR, newPassword, false);
		
		fillInputField(UP_INPUT_PSWD2_CSS_SELECTOR, newPassword, false);
		
		clickElement(UP_PSWDUPDATE_SUBMIT_CSS_SELECTOR);

		return this;
	}
}
