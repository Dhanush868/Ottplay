package com.Ottplay.testcases;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ottplay.actions.TestActions;
import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;
import com.ottplay.locators.MoviesPage;
import com.ottplay.locators.SearchPage;

public class VerifySearchFunctionality extends BaseTest{
	
@Test(testName = "Verify Search Functionality")
	
	public void VerifySearchFunctionalityfor() throws Throwable {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // Pass jsExecutor to the TestActions constructor
        TestActions testAction = new TestActions(jsExecutor);
        HomePage homePage = new HomePage();
        MoviesPage moviespage = new MoviesPage();
        SearchPage searchPage = new SearchPage();
        Thread.sleep(2000);
        homePage.moreButton().click();
        extentTest.info("Clicking on more");
        homePage.moviesButton().click();
        extentTest.info("Clicking on movies");
        Thread.sleep(2000);
        testAction.scrollDown();
        String title = testAction.getRandomElement(moviespage.moviestitlesText()).getText();
        searchPage.SearchesInput().click();
        extentTest.info("Clicking on search");
        Thread.sleep(5000);
        searchPage.SearchesInput().sendKeys(title);
        extentTest.info("searching "+title);
        Thread.sleep(5000);
        searchPage.SearchSeeALL().click();
        extentTest.info("Clicking on search see all");
        testAction.waitForPageToLoad();
        assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), title));
        searchPage.SearchFilterMovie().click();
        assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), title));
        extentTest.info(title+" movie is available in search results");
        testAction.clickElementByText(searchPage.SearchlistcartsTitle(), title);

        extentTest.info("==========================================================");

        homePage.moreButton().click();
        extentTest.info("Clicking on more");
        homePage.showButton().click();
        extentTest.info("Clicking on movies");
        Thread.sleep(2000);
        testAction.scrollDown();
        String showtitle = testAction.getRandomElement(moviespage.moviestitlesText()).getText();
        searchPage.SearchesInput().click();
        extentTest.info("Clicking on search");
        Thread.sleep(5000);
        searchPage.SearchesInput().sendKeys(showtitle);
        extentTest.info("searching "+showtitle);
        Thread.sleep(5000);
        searchPage.SearchSeeALL().click();
        extentTest.info("Clicking on search see all");
        testAction.waitForPageToLoad();
        assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), showtitle));
        searchPage.SearchFilterShow().click();
        assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), showtitle));
        extentTest.info(showtitle+" movie is available in search results");
        testAction.clickElementByText(searchPage.SearchlistcartsTitle(), showtitle);

        extentTest.info("==========================================================");

        

        searchPage.SearchesInput().clear();
        driver.navigate().refresh();
        Thread.sleep(2000);
        searchPage.SearchesInput().click();
        Thread.sleep(2000);
        String trendingtitle = testAction.getRandomElement(searchPage.getTrendingSearches()).getText();
        extentTest.info("Trending search is visible");
        searchPage.SearchesInput().sendKeys(trendingtitle);
        searchPage.SearchSeeALL().click();
        testAction.waitForPageToLoad();
        Thread.sleep(2000);
        assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), trendingtitle));
        testAction.clickElementByText(searchPage.SearchlistcartsTitle(), trendingtitle);

        
       }
        
        @Test(testName = "Verify Search History Functionality")
    	
    	public void VerifySearchHistoryFunctionality() throws Throwable {
        
        	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            // Pass jsExecutor to the TestActions constructor
            TestActions testAction = new TestActions(jsExecutor);
            HomePage homePage = new HomePage();
            MoviesPage moviespage = new MoviesPage();
            SearchPage searchPage = new SearchPage();
            Thread.sleep(2000);
            homePage.moreButton().click();
            homePage.moviesButton().click();
            Thread.sleep(2000);
            testAction.scrollDown();
            String title = testAction.getRandomElement(moviespage.moviestitlesText()).getText();
            searchPage.SearchesInput().click();
            Thread.sleep(5000);
            searchPage.SearchesInput().sendKeys(title);
            Thread.sleep(5000);
            searchPage.SearchSeeALL().click();
            testAction.waitForPageToLoad();
            assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), title));
            searchPage.SearchFilterMovie().click();
            assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), title));
            extentTest.info(title+" movie is available in search results");
            testAction.clickElementByText(searchPage.SearchlistcartsTitle(), title);

            homePage.moreButton().click();
            homePage.showButton().click();
            Thread.sleep(2000);
            testAction.scrollDown();
            String showtitle = testAction.getRandomElement(moviespage.moviestitlesText()).getText();
            searchPage.SearchesInput().click();
            Thread.sleep(5000);
            searchPage.SearchesInput().sendKeys(showtitle);
            Thread.sleep(5000);
            searchPage.SearchSeeALL().click();
            testAction.waitForPageToLoad();
            assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), showtitle));
            searchPage.SearchFilterShow().click();
            assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), showtitle));
            extentTest.info(showtitle+" movie is available in search results");
            testAction.clickElementByText(searchPage.SearchlistcartsTitle(), showtitle);
            searchPage.SearchesInput().clear();
            driver.navigate().refresh();
            Thread.sleep(2000);
            searchPage.SearchesInput().click();
            Thread.sleep(2000);
            String trendingtitle = testAction.getRandomElement(searchPage.getTrendingSearches()).getText();
            searchPage.SearchesInput().sendKeys(trendingtitle);
            searchPage.SearchSeeALL().click();
            testAction.waitForPageToLoad();
            Thread.sleep(2000);
            assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), trendingtitle));
            testAction.clickElementByText(searchPage.SearchlistcartsTitle(), trendingtitle);
            searchPage.SearchesInput().click();
            Thread.sleep(5000);
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(testAction.compareTitle(searchPage.searchHistoryList(), trendingtitle));
            softAssert.assertTrue(testAction.compareTitle(searchPage.searchHistoryList(), title));
            softAssert.assertTrue(testAction.compareTitle(searchPage.SearchlistcartsTitle(), showtitle));
            softAssert.assertAll();
              
        
        }
        
      
}
