package com.Ottplay.testcases;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ottplay.actions.TestActions;
import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;
import com.ottplay.locators.MoviesPage;

public class Verify_ReadSynopsys_SubscribedUser extends BaseTest {
	
@Test(testName = "Verify_ReadSynopsys_UnSubscribedUser")
	
	public void Verify_ReadSynopsys_functionality_for_UnSubscribedUser() throws Throwable {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Pass jsExecutor to the TestActions constructor
        TestActions testAction = new TestActions(jsExecutor);
        HomePage homePage = new HomePage();
        MoviesPage moviesPage = new MoviesPage();
        Thread.sleep(2000);
        homePage.getPremiumLink().click();
        extentTest.log(Status.INFO, "Clicking on Premium link...");
        testAction.scrollDown();
        //Thread.sleep(1000);
        testAction.scrollAndClickRandomElement(homePage.getMovieShowCards());
        //Thread.sleep(2000);
        testAction.scrollDown();
        extentTest.log(Status.INFO, "Clicking on a random movie/show card...");
        moviesPage.getreadMore().click();
        extentTest.log(Status.INFO, "Clicking on ReadMore");
        assertTrue(moviesPage.geSynopsisTitle().isDisplayed() && moviesPage.getSynopsisDescription().isDisplayed() );
        extentTest.log(Status.INFO, "Synopsys is displayed..");



    }

	
@Test(testName = "Verify_ReadSynopsys_SubscribedUser")
	
	public void Verify_ReadSynopsys_functionality_for_SubscribedUser() throws Throwable {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Pass jsExecutor to the TestActions constructor
        TestActions testAction = new TestActions(jsExecutor);
        HomePage homePage = new HomePage();
        MoviesPage moviesPage = new MoviesPage();
        testAction.loginWithRetry();
        Thread.sleep(2000);
        homePage.getPremiumLink().click();
        extentTest.log(Status.INFO, "Clicking on Premium link...");
        Thread.sleep(2000);
        testAction.scrollAndClickRandomElement(homePage.getMovieShowCards());
        Thread.sleep(2000);
        testAction.scrollDown();
        extentTest.log(Status.INFO, "Clicking on a random movie/show card...");
        moviesPage.getreadMore().click();
        extentTest.log(Status.INFO, "Clicking on ReadMore");
        assertTrue(moviesPage.geSynopsisTitle().isDisplayed() && moviesPage.getSynopsisDescription().isDisplayed() );
        extentTest.log(Status.INFO, "Synopsys is displayed..");



    }
}
