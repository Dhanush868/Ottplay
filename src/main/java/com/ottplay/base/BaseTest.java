package com.ottplay.base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Properties;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ottplay.utils.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public static WebDriver driver;
    public static String screenshotsSubFolderName;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static Properties prop;

    @BeforeTest
    public void setup(ITestContext context) {
        String filePath = "C:/Users/dhanu/eclipse-workspace/Ottplay/src/test/resources/properties";
        prop = loadPropertiesFromFile(filePath);

        String browser = prop.getProperty("browser", "chrome").toLowerCase();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
               // chromeOptions.addArguments("--disable-cache");
                //chromeOptions.addArguments("--delete-cookies");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("browser.cache.disk.enable", false);
                firefoxOptions.addPreference("network.cookie.lifetimePolicy", 2);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                System.out.println("Browser name is invalid");
                break;
        }
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));

        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
        String author = context.getCurrentXmlTest().getParameter("author");

        extentTest = extentReports.createTest(context.getName());
        extentTest.assignAuthor(author);
        extentTest.assignDevice(device);
        navigateToURLWithRetry(prop.getProperty("url"));
        waitForPageToLoad();
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeSuite
    public void initialiseExtentReports() {
        ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("AllTests.html");
        sparkReporter_all.config().setReportName("All Tests report");

        ExtentSparkReporter sparkReporter_failed = new ExtentSparkReporter("FailedTests.html");
        sparkReporter_failed.filter().statusFilter().as(new Status[] { Status.FAIL }).apply();
        sparkReporter_failed.config().setReportName("Failure report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter_all, sparkReporter_failed);
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @AfterSuite
    public void generateExtentReports() throws Exception {
        if (driver != null) {
            Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
            String device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion();
            extentReports.setSystemInfo("Browser Version", device);
        }

        extentReports.flush();
        Desktop.getDesktop().browse(new File("AllTests.html").toURI());
        Desktop.getDesktop().browse(new File("FailedTests.html").toURI());
    }

    @AfterMethod
    public void checkStatus(Method m, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = captureScreenshot(result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".jpg");
            String screenshot = getBase64Image(screenshotPath);
            extentTest.addScreenCaptureFromBase64String(screenshot);
            extentTest.fail(result.getThrowable());
            extentTest.fail(MarkupHelper.createLabel(result.getName() + " is failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass(MarkupHelper.createLabel(m.getName() + " is passed", ExtentColor.GREEN));
        }

        extentTest.assignCategory(m.getAnnotation(Test.class).groups());
    }

    public String captureScreenshot(String fileName) {
        if (screenshotsSubFolderName == null) {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            screenshotsSubFolderName = myDateObj.format(myFormatObj);
        }

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./Screenshots/" + screenshotsSubFolderName + "/" + fileName);
        try {
            FileUtils.copyFile(sourceFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot saved successfully");
        return destFile.getAbsolutePath();
    }

    private String getBase64Image(String imagePath) {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Properties loadPropertiesFromFile(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void navigateToURLWithRetry(String url) {
        int retryCount = 20;
        for (int i = 0; i < retryCount; i++) {
            try {
                System.out.println("Navigating to URL: " + url);
                driver.get(url);
                System.out.println("URL navigated successfully");
                break; // If successful, exit the loop
            } catch (Exception e) {
                System.out.println("Failed to navigate to URL. Retrying...");
            }
        }
    }
}
