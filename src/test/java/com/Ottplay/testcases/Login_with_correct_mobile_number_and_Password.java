package com.Ottplay.testcases;

import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;

public class Login_with_correct_mobile_number_and_Password extends BaseTest{

	@Test(testName = "Login with correct mobile number and Password\r\n"
			+ "", groups = {"smoke","prod checklist","regression"})
	public void LoginTest() throws Exception {
		HomePage homePage = new HomePage();
		String expectedTitle = homePage.getLoginRegisterBtn().getText();
		homePage.getLoginRegisterBtn().click();
		extentTest.info("Clicked on Login Button");
		String validMobileNumber = BaseTest.prop.getProperty("username");
        homePage.getPhoneEmailTextField().sendKeys(validMobileNumber);
        extentTest.info("Entered valid Phone number");
        //homePage.getContinueButton().click();
        //extentTest.info("Clicked on Contine Button");
        String validPassword = BaseTest.prop.getProperty("password");
        homePage.getPasswordTextField().sendKeys(validPassword);
        extentTest.info("Entered valid password");
        homePage.getLoginbtn().click();
        extentTest.info("Clicked on Contine Button");
		String actualTitle = homePage.getProfileBtn().getText();
		assertNotEquals(actualTitle, expectedTitle, "Title is mismatched");
		extentTest.pass("Loggedin successfully");
		
	}

}