package com.Ottplay.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ottplay.actions.TestActions;
import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;
import com.ottplay.locators.MoviesPage;

public class Verify_add_to_watchlist_button_functionality extends BaseTest {
	
	@Test(testName = "Verify add to watchlist button functionality for subscribed user")
	
	public void Verify_add_to_watchlist_button_functionality_subscribed_user() throws Throwable {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Pass jsExecutor to the TestActions constructor
        TestActions testAction = new TestActions(jsExecutor);
        HomePage homePage = new HomePage();
        MoviesPage moviesPage = new MoviesPage();
        testAction.loginWithRetry();
        extentTest.log(Status.INFO, "Logging in...");
        homePage.getPremiumLink().click();
        extentTest.log(Status.INFO, "Clicking on Premium link...");
        Thread.sleep(2000);
        testAction.scrollAndClickRandomElement(homePage.getMovieShowCards());
        Thread.sleep(2000);
        testAction.scrollDown();
        extentTest.log(Status.INFO, "Clicking on a random movie/show card...");
        Thread.sleep(2000);
        String title = moviesPage.gettitleDetailsPage();
        moviesPage.getWatchlistbtn().click();
        extentTest.log(Status.INFO, title+" Clicking on the Watchlist button");
        Thread.sleep(2000);
        homePage.getProfileBtn().click();
        extentTest.log(Status.INFO, "Clicking on the MyProfile");
        homePage.getwatchlistMyprofile().click();
        extentTest.log(Status.INFO, "Navigating to the watchlist page...");
        assertTrue(testAction.compareTitle(homePage.getlikedMovieShowCards(), title),
                "Liked movie/show cards titles do not match");
        System.out.println(testAction.compareTitle(homePage.getlikedMovieShowCards(), title));
        extentTest.log(Status.PASS, "Test passed successfully");
		
	}
	
@Test(testName = "Verify add to watchlist button functionality Unsubscribed user")
	
	public void Verify_add_to_watchlist_button_functionality_Unsubscribed_user() throws Throwable {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Pass jsExecutor to the TestActions constructor
        TestActions testAction = new TestActions(jsExecutor);
        HomePage homePage = new HomePage();
        MoviesPage moviesPage = new MoviesPage();
        homePage.getPremiumLink().click();
        extentTest.log(Status.INFO, "Clicking on Premium link...");
        Thread.sleep(2000);
        testAction.scrollAndClickRandomElement(homePage.getMovieShowCards());
        Thread.sleep(2000);
        testAction.scrollDown();
        extentTest.log(Status.INFO, "Clicking on a random movie/show card...");
         Thread.sleep(2000);
        moviesPage.getWatchlistbtn().click();
        Thread.sleep(1000);
        assertTrue(homePage.loginPageTitle().isDisplayed());
		
	}


}
