package com.epam.automation.pages;

import com.epam.automation.constans.CommonConstans;
import com.epam.automation.util.FluentWaitUtil;
import com.epam.automation.util.Waiter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class Page implements CommonConstans {

    protected final static Integer WAIT_3_SEC = 3;
    protected final static Integer WAIT_5_SEC = 5;
    protected final static Integer WAIT_10_SEC = 10;
    protected final static Integer WAIT_15_SEC = 15;
    protected final static Integer WAIT_1_MINUTES = 60;

    @FindBy(xpath = "//li[contains(@class, 'nav-contacts__item-03')]//a [contains(@class, 'dropdown js-dropdown')]")
    private WebElement reference;

    private static final String REFERENCE_CATEGORIE_LINK_XPATH = "./following-sibling::ul//a[contains(text(), '%s')]";


    protected WebDriver driver;
    protected FluentWaitUtil waiter; //для ожидания
    Logger logger = Logger.getLogger("TestLogger"); //создание лог файла

    public Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this); //позволяет создавать страницы
        waiter = new FluentWaitUtil(driver); //создаётся страница и закидывается страница
    }


    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() //заголовок вкладки
    {
        return driver.getTitle();
    }

    public void clickOnElementWithJS(WebElement webElement)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", webElement);
    }

    public void scrollToElementWithJS(WebElement webElement)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public boolean isElementPresent(By by) //проверка на присутствие элемента на странице
    {
        return driver.findElements(by).size() > 0;
    }


    public void referenceExpand(){
        reference.click();
        new Waiter().start(() -> reference.getAttribute("class").contains("active")); //раскрытие списка
    }

    public void clickCategoryInReference(String categoryName){
        reference.findElement(By.xpath(String.format(REFERENCE_CATEGORIE_LINK_XPATH, categoryName))).click();
    }

}