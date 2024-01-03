package com.Ottplay.testcases;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ottplay.actions.TestActions;
import com.ottplay.base.BaseTest;
import com.ottplay.locators.HomePage;

public class VerifySignUpOtpFunctionality extends BaseTest {

    @Test(testName = "Verify SignUp Otp Functionality")
    public void VerifySignUpOtpFunctionality() throws Throwable {

        // Initialize JavascriptExecutor
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Pass jsExecutor to the TestActions constructor
        TestActions testAction = new TestActions(jsExecutor);
        HomePage homePage = new HomePage();
        SoftAssert softAssert = new SoftAssert();

        // Click on Login/Register button
        homePage.getLoginRegisterBtn().click();
        extentTest.info("Clicked on Login/Register button");

        // Set random mobile number
        testAction.setRandomMobileNumber(homePage.getPhoneEmailTextField());

        // Click on Continue button
        homePage.getContinueButton().click();
        extentTest.info("Clicked on Continue button");

        // Validate OTP Page Title
        softAssert.assertTrue(homePage.ValidateOTPPageTitle().isDisplayed(), "OTP Page title is not displayed");

        // Perform Soft Assertions
        extentTest.info("Verifying that Verify OTP button is not clickable");
        softAssert.assertFalse(homePage.isClickable(homePage.verifyOtpButton()), "Verify OTP button is clickable");

        extentTest.info("Verifying that SMS button is clickable");
        softAssert.assertTrue(homePage.isClickable(homePage.smsButton()), "SMS button is not clickable");

        extentTest.info("Verifying that Call button is clickable");
        softAssert.assertTrue(homePage.isClickable(homePage.callButton()), "Call button is not clickable");

        // Set random OTP in the OTP field
        testAction.setRandomMobileNumber(homePage.OtpTextfield());

        // Click on Verify OTP button
        homePage.verifyOtpButton().click();
        extentTest.info("Clicked on Verify OTP button");

        // Perform additional assertions
        extentTest.info("Verifying that Verify OTP button is clickable");
        softAssert.assertTrue(homePage.isClickable(homePage.verifyOtpButton()), "Verify OTP button is not clickable");
        Thread.sleep(2000);

        extentTest.info("Verifying that Incorrect OTP error is displayed");
        softAssert.assertTrue(homePage.incorrectOTPError().isDisplayed(), "Incorrect OTP error is not displayed");
        Thread.sleep(2000);

        extentTest.info("Verifying that Verify OTP button is not clickable after entering incorrect OTP");
        softAssert.assertTrue(homePage.isClickable(homePage.verifyOtpButton()), "Verify OTP button is clickable");
        Thread.sleep(2000);
/*
        // Click on SMS button
        homePage.smsButton().click();
        extentTest.info("Clicked on SMS button");
        Thread.sleep(2000);

        // Check if OTP textfield is empty
        String elementText = homePage.OtpTextfield().getText();
        extentTest.info("Verifying that OTP text field is empty");
        softAssert.assertTrue(elementText.isEmpty(), "OTP text field is not empty");
        Thread.sleep(2000);

        // Perform more assertions
        extentTest.info("Verifying that SMS button is not clickable");
        softAssert.assertFalse(homePage.isClickable(homePage.smsButton()), "SMS button is clickable");
        Thread.sleep(2000);

        extentTest.info("Verifying that Call button is not clickable");
        softAssert.assertFalse(homePage.isClickable(homePage.callButton()), "Call button is clickable");
        Thread.sleep(2000);

        // Wait for 32 seconds
        Thread.sleep(Duration.ofSeconds(32).toMillis());

        // Perform assertions after waiting
        extentTest.info("Verifying that SMS button is clickable after wait");
        softAssert.assertTrue(homePage.isClickable(homePage.smsButton()), "SMS button is not clickable after wait");

        extentTest.info("Verifying that Call button is clickable after wait");
        softAssert.assertTrue(homePage.isClickable(homePage.callButton()), "Call button is not clickable after wait");

        // Set random mobile number in the OTP field
        testAction.setRandomMobileNumber(homePage.OtpTextfield());

        // Click on Call button
        homePage.callButton().click();
        extentTest.info("Clicked on Call button");

        // Perform final assertions
        extentTest.info("Verifying that SMS button is not clickable after clicking Call button");
        softAssert.assertFalse(homePage.isClickable(homePage.smsButton()), "SMS button is clickable");

        extentTest.info("Verifying that Call button is not clickable after clicking Call button");
        softAssert.assertFalse(homePage.isClickable(homePage.callButton()), "Call button is clickable");
*/
        // Assert all soft assertions
        softAssert.assertAll();
    }
}
