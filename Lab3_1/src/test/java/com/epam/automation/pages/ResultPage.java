package com.epam.automation.pages;

import com.epam.automation.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/*класс для страницы, где показан результат поиска*/
public class ResultPage extends Page {

    @FindBy(xpath = "//ul[contains(@class, 'nav-currency')]")
    private WebElement textCurrencies; // выбор валюты
    @FindBy(xpath = "//a[contains(@data-uil, 'sort-price')]")
    private WebElement sortByPriceButton; // кнопка сортировки по цене
    @FindBy(xpath = "//a[contains(@data-uil, 'sort-rating')]")
    private WebElement sortByRatingButton; //кнопка сортировки по рейтингу
    @FindBy(xpath = "//a[contains(@data-uil, 'sort-duration')]")
    private WebElement sortByDurationButton; // кнопка сортировки по длительности
    @FindBy(xpath = "//a[contains(@data-uil, 'bag_with')]")
    private WebElement withBagButton; // кнопка в сортировке багажа "с багажом"
    @FindBy(xpath = "//a[contains(@data-uil, 'bag_without')]")
    private WebElement withoutBagButton;  // кнопка в сортировке багажа "без багажа"
    @FindBy(xpath = "//a[contains(@data-uil, 'bag_all')]")
    private WebElement bagAllButton; // кнопка в сортировке багажа "все"
    @FindAll(@FindBy(xpath = "//section[contains(@class, 'item-block section__margin')]")) // все элементы
    private List<WebElement> availableTickets;  // список билетов

    private final static String SELECTED_CURRENCY_XPATH = ".//div[contains(@class, 'checked')]/following-sibling::label"; // проверка всех цен на билеты
    private static final String CURRENCY_SELECTION_XPATH = ".//div[contains(@class, 'iradio_minimal')]/input [contains(@id, '%s')]/../.."; // список нажатия при выборе валюты
    private static final String BAG_STATUS_XPATH = "//span[contains (@class, 'baggage-text')]"; // ссылки для багажа
    private static String GLOBUS_LOADER = "//div[contains(@class, 'globus-loader')]"; // картинка при изменении на страницы

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    /*используется для проверки, что перевелось*/
    public String getCurrentCurrency() {
        return textCurrencies.findElement(By.xpath(SELECTED_CURRENCY_XPATH)).getText(); //доступ к элементу в списке
    }

    /*используется метод для нажатия на ссылку*/
    public void selectRequiredCurrency(String currency) {
        System.out.println("test change");
        waiter.waitForElement(textCurrencies);
        textCurrencies.findElement(By.xpath(String.format(CURRENCY_SELECTION_XPATH, currency))).click();
    }

    /*метод возвращает false, если хоть одной кнопки нет*/
    public boolean verifySortButtonsPresent() {
        boolean result = true;
        result &= sortByPriceButton.isDisplayed();
        result &= sortByRatingButton.isDisplayed();
        result &= sortByDurationButton.isDisplayed();
        return result;
    }

    /*метод возвращает false, если хоть одной кнопки нет*/
    public boolean verifyBagButtonsPresent() {
        boolean result = true;
        result &= withBagButton.isDisplayed();
        result &= withoutBagButton.isDisplayed();
        result &= bagAllButton.isDisplayed();
        return result;
    }

    /*метод для проверки появления билетов*/
    public boolean verifyTicketsPresent() {
        return availableTickets.size() > 0;
    }

    /*метод для проверки багажа*/
    public boolean verifyBagStatusInTickets(boolean isBagPresent) {
        boolean result = true; //
        for (int i = 0; i < availableTickets.size(); i++) {
            WebElement bagStatusElement = availableTickets.get(i).findElement(By.xpath(BAG_STATUS_XPATH));
            String bagStatusAttribute = bagStatusElement.getAttribute(BAGGAGE_ATTRIBUTE);
            result &= (!bagStatusAttribute.equals(WITHOUT_BAG_TEXT) == isBagPresent); //с сумкой или без
        }
        return result;
    }

    /*страница ожидающая страницу с картинкой*/
    private void waitForGlobusLoader() {
        new Waiter(20, 0.5).start(() -> driver.findElements(By.xpath(GLOBUS_LOADER)).size() > 0);
        new Waiter(20, 0.5).start(() -> driver.findElements(By.xpath(GLOBUS_LOADER)).size() == 0);
    }

    public void removeGlobasLoader() {
        clickBaggage(WITH_BAG);
        clickBaggage(ALL_BAG);
    }

    /*метод для выбора билета с багажом или без*/
    public void clickBaggage(String baggage) {
        switch (baggage) {
            case WITH_BAG:
                withBagButton.click(); //что он дождать должен получается?
                waitForGlobusLoader();
                break;
            case WITHOUT_BAG:
                withoutBagButton.click();
                waitForGlobusLoader();
                break;
            case ALL_BAG:
                bagAllButton.click();
                waitForGlobusLoader();
                break;
            default:
                break;
        }
    }
}
