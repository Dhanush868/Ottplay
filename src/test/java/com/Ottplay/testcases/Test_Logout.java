package com.Ottplay.testcases;

import org.testng.annotations.Test;

import com.ottplay.actions.TestActions;
import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;

public class Test_Logout extends BaseTest {
	@Test(testName = "Test Logout\r\n"
			+ "", groups = {"smoke","prod checklist"})
	
	public void TestLogout() {
		HomePage homepage=new HomePage();
		TestActions testAction = new TestActions(null);
		testAction.loginWithRetry();
		homepage.getProfileBtn().click();
        extentTest.info("Clicked on Profile Button");
		homepage.getLogoutbtn().click();
        extentTest.info("Clicked on Logout");

		
	}
	

}
