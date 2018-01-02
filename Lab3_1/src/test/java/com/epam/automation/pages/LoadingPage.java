package com.epam.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*страница, которая открывается при поиске в разных компаниях*/
public class LoadingPage extends Page {

    @FindBy(xpath = "//div[contains(@class, 'allert')]")
    private WebElement errorMessage; //сообщение об ошибке

    public LoadingPage(WebDriver driver) {
        super(driver);
    }

    /*метод для ожидания загрузки*/
    public void waitForPageLoading() {
        String currentURL = driver.getCurrentUrl();
        new WebDriverWait(driver, WAIT_1_MINUTES).until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));
    }

    /*метод для ожидания ошибки*/
    public void waitForErrorMessageLoading() {
        waiter.waitForVisibilityOfElement(errorMessage);
    }

    /*метод для проверки появилось ли сообщение об ошибки*/
    public boolean verifyErrorMessage() {
        return errorMessage.isDisplayed();
    }
}
