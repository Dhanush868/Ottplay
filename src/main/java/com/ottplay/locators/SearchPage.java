package com.ottplay.locators;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ottplay.base.BaseTest;

public class SearchPage extends BaseTest {
	
	WebDriverWait wait;
	WebDriverWait wait1;
	
	
	@FindBy(xpath = "//div[@class='searchDropdown_trendingSearches__listCards__aPYNH']//h3")
    List<WebElement> getTrendingSearches;
    @FindBy(xpath = "(//input[@id='searchFeild'])[1]")
    WebElement SearchesInput;
    @FindBy(xpath = "//input[@id='searchFeild']/../..//span[@class='ottplay-135']")
    WebElement SearchButton;
    @FindBy(xpath = "//a[@class='searchResult_listCards__ksDZy']//div[2]//div//div//div")
    List<WebElement> SearchlistcartsTitle;
    @FindBy(css = ".searchDropdown_seeAllTitle__niBOx")
    WebElement SearchSeeALL;
    
    
    
    
    @FindBy(xpath = "//button[@name='All']")
    WebElement searchFilterALL;
    @FindBy(xpath = "//span[normalize-space()='Movie']")
    WebElement SearchFilterMovie;
    @FindBy(xpath = "//span[normalize-space()='Show']")
    WebElement SearchFilterShow;
    @FindBy(xpath = "//span[normalize-space()='Sport']")
    WebElement SearchFilterSport;
    @FindBy(className = "searchDropdown_trendingRecentHistory__body__list__item__aXpbi")
    List<WebElement> searchHistoryList;
    @FindBy(xpath = "//div[contains(text(),'Recent History')]")
    WebElement searchHistoryTitle;
    @FindBy(xpath = "//div[contains(text(),'Trending Searches')]")
    WebElement trendingSearchTitle;
    
    
    
    
    
	
	
	public SearchPage() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1 = new WebDriverWait(driver, Duration.ofSeconds(40));

    }

	
	
	
	    
	
	
	    public WebElement trendingSearchTitle() {
	        return wait.until(ExpectedConditions.visibilityOf(trendingSearchTitle));
        }
	    public WebElement searchHistoryTitle() {
		    return wait.until(ExpectedConditions.visibilityOf(searchHistoryTitle));
	    }
	    public List<WebElement> searchHistoryList() {
			return searchHistoryList;
	    }
	    
	    
	    
	    
        public WebElement SearchFilterSport() {
			return SearchFilterSport;
		}
        public WebElement SearchFilterShow() {
			return SearchFilterShow;
		}
        public WebElement SearchFilterMovie() {
	        return SearchFilterMovie;
        }
	    public WebElement searchFilterALL() {
		    return searchFilterALL;
	    }
	    
	    
	    
	    
	    public WebElement SearchSeeALL() {
			return SearchSeeALL;
		}
	    public List<WebElement> SearchlistcartsTitle() {
			return SearchlistcartsTitle;
		}
	    public WebElement SearchButton() {
			return SearchButton;
		}
	    public WebElement SearchesInput() {
			return SearchesInput;
		}
	    public List<WebElement> getTrendingSearches() {
			return getTrendingSearches;
		}
	
	
	
	
	
}
