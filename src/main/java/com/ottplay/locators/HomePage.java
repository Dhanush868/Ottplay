package com.ottplay.locators;

import java.time.Duration;
import java.util.List;

import org.checkerframework.checker.units.qual.radians;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ottplay.base.BaseTest;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class HomePage extends BaseTest {
	WebDriverWait wait;
	WebDriverWait wait1;

    @FindBy(xpath = "(//div[text()='Login/Register'])[1]")
    WebElement loginRegisterBtn;

    @FindBy(xpath = "//input[@id='phoneEmail']")
    WebElement phoneEmailTextField;

    @FindBy(xpath = "//input[@id='btSubmit']")
    WebElement continueButton;

    @FindBy(xpath = "//input[@id='upass']")
    WebElement passwordTextField;

    @FindBy(id = "btnLogin")
    WebElement getLoginbtn;

    @FindBy(xpath = "(//div[@class='ottplay-6'])[1]")
    WebElement profileElement;

    @FindBy(xpath = "//span[text()='Logout']")
    WebElement getLogoutbtn;

    @FindBy(xpath = "//li[contains(@class,'contentTypeLi') and (normalize-space()='Show' or normalize-space()='Movie')]")
    List<WebElement> movieShowCards;

    @FindBy(xpath = "(//span[@class='ottplay-109 false false'][normalize-space()='Premium'])[1]")
    WebElement premiumLink;

    @FindBy(xpath = "//img[@alt='blankimg']/../..")
    WebElement myProfilebtn;

    @FindBy(xpath = "//p[@class='MuiTypography-root title title-med-screen MuiTypography-body2 MuiTypography-colorTextSecondary']/a")
    List<WebElement> likedmovieShowCards;
    @FindBy(xpath = "//span[contains(text(),'Watchlist')]")
    WebElement watchlistMyprofile;
    
    @FindBy(xpath = "//div[@class='title']")
    WebElement loginPageTitle;

    @FindBy(css = "li.glide__slide img")
    List<WebElement> bannersHomePage;
    
    @FindBy(id = "slideForward")
    WebElement slideForward;
    
    @FindBy(id = "slideBack")
    WebElement slideBack;
    
    
    
    //==================================================================================================================================
    @FindBy(xpath = "(//span[normalize-space()='My Languages']/ancestor::div[@class='MuiAccordion-root']//input[@type='checkbox'])[1]")
    WebElement myLanguageToggle;
    @FindBy(xpath = "(//span[contains(text(),\"Clear\")])[1]")
    WebElement myLanguageClear;
    @FindBy(xpath = "//span[normalize-space()='My Languages']/../..")
    WebElement myLanguageProfile;
    @FindBy(xpath = "//div[contains(@class,'crownIcon')]")
    WebElement crownIcononHomePage;
    @FindBy(xpath = "//div[contains(@class,'free-stream')][normalize-space()='Free']/..//div[contains(@class,'playIcon')]")
    WebElement playIcononHomePageonFreeCart;
    @FindBy(xpath = "//div[contains(@class,'free-stream')][normalize-space()='Free']")
    WebElement freetagonCart;
    @FindBy(xpath = "//button[@class=\"MuiButtonBase-root MuiCardActionArea-root cardActionArea\"]//div[contains(@class,'playIcon')]")
    WebElement playicon;
    
    
    //==================================================================================================================================
    @FindBy(xpath = "(//span[text()=\"More\"])[1]")
    WebElement moreButton;
    @FindBy(xpath = "(//a[normalize-space()='Movies'])[1]")
    WebElement moviesButton;
    @FindBy(xpath = "(//a[normalize-space()='Shows'])[1]")
    WebElement showButton;
    
    @FindBy(xpath = "//img[@alt='OTTplay Logo']")
    WebElement homepageOttplayLogo;
    
    
    
    @FindBy(xpath = "//input[@id='otp1']")
    WebElement OtpTextfield;
    @FindBy(xpath = "//button[@id='sms']")
    WebElement smsButton;
    @FindBy(xpath = "//input[@id='verifySignUpOTP']")
    WebElement verifyOtpButton;
    @FindBy(xpath = "//button[@id='call']")
    WebElement callButton;
    @FindBy(xpath = "//div[text()=\"Validate OTP\"]")
    WebElement ValidateOTPPageTitle;
    @FindBy(xpath = "//div[@id='emailError']")
    WebElement incorrectOTPError;
    
    
    
    //==================================================================================================================================
    // Initializing the Page Objects:
    public HomePage() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1 = new WebDriverWait(driver, Duration.ofSeconds(40));

    }
    //==================================================================================================================================

    
    
    
    public boolean isClickable(WebElement element) {
        try {
            return element.isEnabled() && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public WebElement incorrectOTPError() {
		return incorrectOTPError;
	}
    public WebElement ValidateOTPPageTitle() {
		return ValidateOTPPageTitle;
	}
    public WebElement callButton() {
		return callButton;
	}
    public WebElement verifyOtpButton() {
		return verifyOtpButton;
	}
    public WebElement smsButton() {
		return smsButton;
	}
    public WebElement OtpTextfield() {
		return OtpTextfield;
	}
    
    
    public WebElement homepageOttplayLogo() {
		return wait.until(ExpectedConditions.visibilityOf(homepageOttplayLogo));
	}
    public WebElement showButton() {
		return wait.until(ExpectedConditions.visibilityOf(showButton));
	}
    public WebElement moviesButton() {
		return wait.until(ExpectedConditions.visibilityOf(moviesButton));
	}
    public WebElement moreButton() {
		return moreButton;
	}
    
    public WebElement playicon() {
  		return playicon;
  	}
    public WebElement freetagonCart() {
  		return freetagonCart;
  	}
    public WebElement playIcononHomePageonFreeCart() {
  		return playIcononHomePageonFreeCart;
  	}
    public WebElement crownIcononHomePage() {
		return crownIcononHomePage;
	}
    public WebElement myLanguageProfile() {
		return myLanguageProfile;
	}
    public WebElement myLanguageClear() {
		return myLanguageClear;
	}
    public WebElement myLanguageToggle() {
		return myLanguageToggle;
	}
    public WebElement slideBack() {
		return slideBack;
	}
    public WebElement slideForward() {
		return slideForward;
	}
    public List<WebElement> bannersHomePage() {
		return bannersHomePage;
	}
    public WebElement loginPageTitle() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.visibilityOf(loginPageTitle));
    }
    public WebElement getwatchlistMyprofile() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.elementToBeClickable(watchlistMyprofile));
    }

    public List<WebElement> getlikedMovieShowCards() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.visibilityOfAllElements(likedmovieShowCards));
    }

    public WebElement getMyProfilebtn() {
        // Wait for the element to be clickable
        return wait1.until(ExpectedConditions.elementToBeClickable(myProfilebtn));
    }

    public WebElement getPremiumLink() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.elementToBeClickable(premiumLink));
    }

    public List<WebElement> getMovieShowCards() {
        // Wait for the element to be visible
        return wait.until(ExpectedConditions.visibilityOfAllElements(movieShowCards));
    }

    public WebElement getProfileBtn() {
        // Wait for the element to be present in the DOM
        return wait1.until(ExpectedConditions.visibilityOf(profileElement));

    }

    public WebElement getLogoutbtn() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.elementToBeClickable(getLogoutbtn));
    }

    public WebElement getLoginbtn() {
        
        return wait1.until(ExpectedConditions.elementToBeClickable(getLoginbtn));
    }

    public WebElement getPasswordTextField() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.elementToBeClickable(passwordTextField));
    }

    public WebElement getContinueButton() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.elementToBeClickable(continueButton));
    }

    public WebElement getPhoneEmailTextField() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.elementToBeClickable(phoneEmailTextField));
    }

    public WebElement getLoginRegisterBtn() {
        // Wait for the element to be clickable
        return wait.until(ExpectedConditions.elementToBeClickable(loginRegisterBtn));
    }
}
