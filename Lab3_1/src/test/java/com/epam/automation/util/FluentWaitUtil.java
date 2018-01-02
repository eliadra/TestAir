package com.epam.automation.util;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class FluentWaitUtil {
    private WebDriver driverWait;
    private Wait<WebDriver> wait;
    private Wait<WebDriver> waitFluent;
    private final int WAIT_15_SEC = 15;


    public FluentWaitUtil(WebDriver driver) // конструктор
    {
        this.driverWait = driver;
        wait = new WebDriverWait(this.driverWait, WAIT_15_SEC).ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .withMessage("Element was not found by locator!");
        waitFluent = new FluentWait(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
    }

    public WebElement waitForElementIsClickable(WebElement element)  //ожидание того, что элемент может быть нажатым
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public WebElement waitForElement(WebElement element) /*проверка на существование элемента*/    {
        waitFluent.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public WebElement waitForElementNotPresent (WebElement element)    {
        waitFluent.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
        return element;
    }

    public WebElement waitForVisibilityOfElement(WebElement element) //виден ли элемент
    {
        waitFluent.until(ExpectedConditions.visibilityOf(element));
        return element;
    }
}