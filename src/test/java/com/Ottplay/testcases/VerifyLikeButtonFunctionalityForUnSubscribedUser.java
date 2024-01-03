package com.Ottplay.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ottplay.actions.TestActions;
import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;
import com.ottplay.locators.MoviesPage;

public class VerifyLikeButtonFunctionalityForUnSubscribedUser extends BaseTest{

    @Test(testName = "Verify like button functionality for Unsubscribed user", groups = { "smoke", "prod checklist" })
    public void verifyLikeButtonFunctionalityForUnSubscribedUser() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        HomePage homePage = new HomePage();
        TestActions testAction = new TestActions(jsExecutor);
        MoviesPage moviePage = new MoviesPage();
        homePage.getPremiumLink().click();
        extentTest.log(Status.INFO, "Clicking on Premium link...");
        Thread.sleep(2000);
        testAction.scrollAndClickRandomElement(homePage.getMovieShowCards());
        extentTest.log(Status.INFO, "Clicking on a random movie/show card...");
        Thread.sleep(2000);
        moviePage.getLikebtn().click();
        extentTest.log(Status.PASS, "Clicking on the like button...");

        
    }


}
