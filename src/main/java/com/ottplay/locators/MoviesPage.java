package com.ottplay.locators;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ottplay.base.BaseTest;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class MoviesPage extends BaseTest {
    
    WebDriverWait wait;

    @FindBy(css = "ul[class='movieDescription_movieActions__B9luk'] i[class='movieDescription_watchlistIcon__69TiU']")
    WebElement watchlistbtn;
    @FindBy(css = "ul[class='movieDescription_movieActions__B9luk'] i[class='movieDescription_likeIcon__6bt8a']")
    WebElement likebtn;
    @FindBy(css = "ul[class='movieDescription_movieActions__B9luk'] i[class='movieDescription_dislikeIcon___vEJF']")
    WebElement dislikebtn;
    @FindBy(css = "ul[class='movieDescription_movieActions__B9luk'] i[class='movieDescription_shareIcon__2kY_k']")
    WebElement sharebtn;
    @FindBy(xpath = "//div[@class='movieDescription_pageHeadMp__UlfUz']/h1")
    WebElement titleDetailsPage;
    @FindBy(css  = "@FindBy(xpath = \"//div[@class='movieDescription_pageHeadMp__UlfUz']/h1\")\r\n"
            + "    WebElement titleDetailsPage;")
    WebElement likeDislikeSelected;

    @FindBy(xpath = "//a[normalize-space()='Read More']")
    WebElement ReadMore;
    
    @FindBy(xpath = "//h2[normalize-space()='Synopsis']")
    WebElement SynopsisTitle;    
    
    @FindBy(xpath = "//div[@class='ottplay-184']")
    WebElement synopsisDescription;
    
    @FindBy(xpath = "//p[@class='MuiTypography-root title title-med-screen MuiTypography-body2 MuiTypography-colorTextSecondary']")
    List<WebElement> moviestitlesText;
    
    
    
    
    public MoviesPage() {
        PageFactory.initElements(driver, this);
        // Add a wait statement for each element
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       
    }
    
    
    public List<WebElement> moviestitlesText() {
		return moviestitlesText;
	}
    
    public WebElement getSynopsisDescription() {
		return synopsisDescription;
	}
    
    public WebElement geSynopsisTitle() {
		return wait.until(ExpectedConditions.elementToBeClickable(SynopsisTitle));
	}
    public WebElement getreadMore() {
		return wait.until(ExpectedConditions.elementToBeClickable(ReadMore));
	}

    public WebElement likeDislikeSelected() {
        return wait.until(ExpectedConditions.elementToBeClickable(likeDislikeSelected));
    }

    public String gettitleDetailsPage() {
        return titleDetailsPage.getText();
    }

    public WebElement getSharebtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(sharebtn));
    }

    public WebElement getDislikebtn() {
    	return wait.until(ExpectedConditions.elementToBeClickable(dislikebtn));

    }

    public WebElement getLikebtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(likebtn));
    }

    public WebElement getWatchlistbtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(watchlistbtn));
    }
}
