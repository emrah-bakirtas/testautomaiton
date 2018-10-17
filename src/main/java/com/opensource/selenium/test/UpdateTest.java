package com.opensource.selenium.test;

import com.opensource.selenium.base.BaseTest;
import com.opensource.selenium.page.MainPage;

public class UpdateTest extends BaseTest {

    MainPage mp = new MainPage(driver);
/*
	@Ignore
	@Before
	public void before() {
		
		mp.navigateTo();
		
		mp.goToLoginPage().login(config.getUsername(), config.getPassword());
	}

	@Ignore
	@Test
	public void updatePersonalInformationTest(){

		mp.goToProfilePage().updatePersonalInformation(config.getUserFirstName(), 
													   config.getUserLastName(), 
													   config.getUserEmail(), 
													   config.getCity());
	}

	@Ignore
	@Test
	public void updatePasswordTest(){
		
		mp.goToProfilePage().changePassword(config.getPassword(), config.getPassword());
	}
	
	@After
	public void after() {
		
		mp.logOut();
	}
	*/
}
