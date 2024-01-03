package com.Ottplay.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ottplay.actions.TestActions;
import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;
import com.ottplay.locators.MoviesPage;

public class VerifyLikeButtonFunctionality extends BaseTest {

    @Test(testName = "Verify like button functionality for subscribed user", groups = { "smoke", "prod checklist" })
    public void verifyLikeButtonFunctionality() throws Throwable {
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
        moviesPage.getLikebtn().click();
        extentTest.log(Status.INFO, "Clicking on the like button...");
        String title = moviesPage.gettitleDetailsPage();
        homePage.getProfileBtn().click();
        Thread.sleep(2000);
        homePage.getMyProfilebtn().click();
        extentTest.log(Status.INFO, "Navigating to the profile page...");

        Thread.sleep(2000);
        assertTrue(testAction.compareTitle(homePage.getlikedMovieShowCards(), title),
                "Liked movie/show cards titles do not match");
        System.out.println(testAction.compareTitle(homePage.getlikedMovieShowCards(), title));

        extentTest.log(Status.PASS, "Test passed successfully");

    }
}
