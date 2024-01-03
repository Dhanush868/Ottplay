package com.Ottplay.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.ottplay.actions.TestActions;
import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;

public class VerifyBannersOnHomePage extends BaseTest{
	
@Test(testName = "Verify Banners On HomePage For Subscribed User")
	
	public void VerifyBannersOnHomePageForSubscribedUser() throws Throwable {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Pass jsExecutor to the TestActions constructor
        TestActions testAction = new TestActions(jsExecutor);
        HomePage homePage = new HomePage();
        testAction.loginWithRetry();
        Thread.sleep(2000);
        assertTrue(testAction.verifyAllImagesAccessible(homePage.bannersHomePage()));
        extentTest.info("Banner accessible (not broken)");
        homePage.slideBack().click();
        extentTest.info("Back slider button is working");
        homePage.slideForward().click();
        extentTest.info("Forword slider button is working");
        
        
   }
@Test(testName = "Verify Banners On HomePage For UnSubscribed User")

   public void VerifyBannersOnHomePageForUnSubscribedUser() throws Throwable {
	
	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    // Pass jsExecutor to the TestActions constructor
       TestActions testAction = new TestActions(jsExecutor);
       HomePage homePage = new HomePage();
       Thread.sleep(2000);
       assertTrue(testAction.verifyAllImagesAccessible(homePage.bannersHomePage()));
       extentTest.info("Banner accessible (not broken)");
       homePage.slideBack().click();
       extentTest.info("Back slider button is working");
       homePage.slideForward().click();
       extentTest.info("Forword slider button is working");
    
    
}
}
