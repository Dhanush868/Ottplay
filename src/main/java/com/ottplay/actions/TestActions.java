package com.ottplay.actions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.bouncycastle.asn1.cmp.Challenge.Rand;
import org.checkerframework.common.value.qual.StringVal;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;

public class TestActions extends BaseTest {
    private JavascriptExecutor jsExecutor;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    // Constructor to initialize the WebDriver
    public TestActions(JavascriptExecutor jsExecutor) {
        this.jsExecutor = jsExecutor;
    }

    public void login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        String validMobileNumber = BaseTest.prop.getProperty("username");
        String validPassword = BaseTest.prop.getProperty("password");
        HomePage homePage = new HomePage();
        
        try {
            homePage.getLoginRegisterBtn().click();
            homePage.getPhoneEmailTextField().sendKeys(validMobileNumber);
            homePage.getContinueButton().click();
            homePage.getPasswordTextField().sendKeys(validPassword);
            homePage.getLoginbtn().click();
            wait.until(ExpectedConditions.visibilityOf(homePage.getProfileBtn()));
        } catch (Exception e) {
            // If an exception occurs, throw it to be caught by the retry mechanism
            throw e;
        }
    }
    
    
    //==================================================================================================================================

    public void scrollAndClickRandomElement(List<WebElement> elements) {
        // Wait for the elements to be visible
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

        // Scroll down five times
        for (int i = 0; i < 5; i++) {
            jsExecutor.executeScript("window.scrollBy(0,200)");

            // Wait for the document to be in a ready state after each scroll
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        }

        // Randomly pick and click on an element, retrying if not clickable
        int maxRetries = 100;
        for (int retry = 0; retry < maxRetries; retry++) {
            Random random = new Random();
            int randomIndex = random.nextInt(elements.size());

            try {
                // Locate the element again to avoid StaleElementReferenceException
                WebElement randomElement = elements.get(randomIndex);

                // Scroll to and click on the random element
                randomElement.click();

                // Break if successfully clicked
                break;
            } catch (StaleElementReferenceException e) {
                // If the element is stale, catch the exception and try with another random element
                System.out.println("StaleElementReferenceException, retrying with another random element.");
            } catch (NoSuchElementException e) {
                // If the element is not found, catch the exception and try with another random element
                jsExecutor.executeScript("window.scrollBy(0,200)");
                System.out.println("Element not found, retrying with another random element.");
            } catch (Exception e) {
                // Handle other exceptions if needed
                e.printStackTrace();
            }
        }
    }

    
    //==================================================================================================================================


    public boolean compareTitle(List<WebElement> elements, String expectedTitle) {
        // Convert expected title to lowercase (or uppercase)
        expectedTitle = expectedTitle.toLowerCase(); // or expectedTitle.toUpperCase()

        // Extract titles from elements and convert to lowercase (or uppercase)
        List<String> actualTitles = elements.stream().map(WebElement::getText)
                                             .map(String::toLowerCase) // or String::toUpperCase
                                             .collect(Collectors.toList());

        // Check if expected title is present in the list of actual titles
        return actualTitles.contains(expectedTitle);
    }


    
    //==================================================================================================================================

    
    
    public void scrollDown() {
        // Scroll down five times
        for (int i = 0; i < 2; i++) {
            jsExecutor.executeScript("window.scrollBy(0,200)");
        }
    }
    
    
    //==================================================================================================================================

    
    
    public void loginWithRetry() {
        int maxRetries = 3; // Set the maximum number of retries
        int currentRetry = 0;

        while (currentRetry < maxRetries) {
            try {
                login();
                // If login is successful, break out of the loop
                break;
            } catch (Exception e) {
                // Log or print the exception for debugging purposes
                e.printStackTrace();

                // Increment the retry count
                currentRetry++;

                // Optionally, you can add a delay before retrying
                // Thread.sleep(retryDelayInSeconds * 1000);
            }
        }
    }
    
    
    
    
    //==================================================================================================================================

    
    
    
    public boolean verifyAllImagesAccessible(List<WebElement> elements) {
        boolean allImagesAccessible = true;

        for (WebElement element : elements) {
            try {
                // Get the src attribute of the image element
                String imageUrl = element.getAttribute("src");
                System.out.println("Image URL: " + imageUrl);

                // Verify the accessibility of the image
                if (!isImageAccessible(imageUrl)) {
                    System.out.println("Image is not accessible: " + imageUrl);
                    allImagesAccessible = false;
                }
            } catch (Exception e) {
                // Handle other exceptions, if needed
                e.printStackTrace();
            }
        }

        return allImagesAccessible;
    }

    private boolean isImageAccessible(String imageUrl) {
        try {
            URL url = new URL(new URI(imageUrl).toASCIIString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check the response code
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException | URISyntaxException e) {
            // Log or print the exception, if needed
            System.out.println("Exception in isImageAccessible: " + e.getMessage());
            return false;
        }
    }

    
    //==================================================================================================================================

    
    
    
    public WebElement getRandomElement(List<WebElement> elements) {
        if (elements.isEmpty()) {
            // Handle the case where the list is empty, return null, or throw an exception
            return null;
        }

        // Get a random index within the size of the list
        int randomIndex = (int) (Math.random() * elements.size());

        // Return the WebElement at the random index
        return elements.get(randomIndex);
    }
    
    
    
    
    
    //==================================================================================================================================

    
    
    public boolean isUrlContainingSearchKeyword(String url, String searchKeyword) {
        try {
            URI uri = new URI(url);
            String query = uri.getQuery();

            // Check if the query parameter contains the search keyword
            return query != null && query.contains("query=" + searchKeyword);
        } catch (URISyntaxException e) {
            // Handle the case where the URL is not valid
            e.printStackTrace(); // Or log the exception
            return false;
        }
    }
    
    
    
    
    //==================================================================================================================================

    
    
    
    
    public void clickElementByText(List<WebElement> elements, String targetText) {
        // Convert target text to lowercase (or uppercase)
        targetText = targetText.toLowerCase(); // or targetText.toUpperCase()

        // Iterate through each WebElement in the list
        for (WebElement element : elements) {
            // Get the text of the current element and convert it to lowercase (or uppercase)
            String elementText = element.getText().toLowerCase(); // or element.getText().toUpperCase()

            // Check if the element text matches the target text
            if (elementText.equals(targetText)) {
                // Click on the element if there is a match
                element.click();
                // Assuming you want to click only on the first matching element, you can break out of the loop
                break;
            }
        }
    }

    
    //==================================================================================================================================
    
    public void setRandomMobileNumber(WebElement element) {
        String randomMobileNumber = generateRandomMobileNumber();

        element.clear();
        element.sendKeys(randomMobileNumber);
    }

    private static String generateRandomMobileNumber() {
        Random random = new Random();

        // Select a random starting digit (9, 8, 7, or 6)
        int startDigit = 6 + random.nextInt(4);  // 6, 7, 8, or 9

        // Generate the remaining 9 digits
        StringBuilder mobileNumber = new StringBuilder(String.valueOf(startDigit));
        for (int i = 0; i < 9; i++) {
            mobileNumber.append(random.nextInt(10)); // Append a random digit (0-9)
        }

        return mobileNumber.toString();
    }
    
    
    
    
    
    //==================================================================================================================================

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   /* public void verifyCrownIconForNonLoggedInUser(List<WebElement> contentCards,
            By crownIconSelector, By freeTagSelector, By playIconSelector) {
            for (WebElement card : contentCards) {
                 WebElement crownIcon = card.findElement(crownIconSelector);
                 WebElement freeTag = card.findElement(freeTagSelector);
                 WebElement playIcon = card.findElement(playIconSelector);

                 if (!crownIcon.isDisplayed() && !freeTag.isDisplayed() && !playIcon.isDisplayed()) {
                  // Crown icon is missing for a non-logged-in user without a free tag
                     String screenshotPath = captureScreenshot("CrownIconVerificationFailure.jpg");
                     String screenshot = getBase64Image(screenshotPath);
                     extentTest.addScreenCaptureFromBase64String(screenshot);
                     extentTest.fail("Crown icon verification failed for content card without login and without a free tag");
               } else if (!crownIcon.isDisplayed() && freeTag.isDisplayed() && playIcon.isDisplayed()) {
                 // Crown icon is missing, but free tag and play icon are present
                    String screenshotPath = captureScreenshot("CrownIconVerificationFailure.jpg");
                    String screenshot = getBase64Image(screenshotPath);
                    extentTest.addScreenCaptureFromBase64String(screenshot);
                    extentTest.fail("Crown icon verification failed for content card with a free tag and play icon");
               }
          }
     }
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}


