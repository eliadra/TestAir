package com.epam.automation.tests;

import com.epam.automation.driver.DriverSingleton;
import com.epam.automation.util.SessionHelper;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;

public class PreparationSteps {

    protected static final String START_PAGE_URL = "http://bilet.aviakassa.by/"; //начальная страница
    protected static DesiredCapabilities capabilities;
  //  protected  static StartPage startPage;
    protected  static WebDriver driver;
    private final static String browserName = "chrome";
    protected static final int DEPARTURE_DATE_DELTA = 2;
    protected static final int RETURN_DATE_DELTA = 5;
    protected static final int RETURN_UNVALID_DATE_DELTA = -5;

    @BeforeSuite
    public static void initTestSuite() throws IOException {
        if ((capabilities = SessionHelper.getBrowserCaps(browserName.toLowerCase())) == null) {
            throw new NoSuchSessionException("Required parameters can't be set");
        }
    }

    @BeforeMethod
    //вызывается перед каждым тестом
    public static void initWebDriver() throws InterruptedException {
        driver = DriverSingleton.getDriver(capabilities);
        System.out.println("Switch-over to PreparationSteps after running webdriver");
        driver.navigate().to(START_PAGE_URL);
        System.out.println("Passed to start URL ");
      //  startPage = new StartPage(driver);
        //System.out.println(startPage.getTitle());
    }

    @AfterMethod
    public static void tearDown() {
        DriverSingleton.closeDriver();
    }
}
