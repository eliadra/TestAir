package com.epam.automation.pages;


import com.epam.automation.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

/*класс для начальной страницы сайта*/
public class StartPage extends Page {

    @FindBy(xpath = "//div[contains(@class, 'iradio_minimal')]") //находит один элемент
    private WebElement oneWayCheckBox; // checkBox в одну сторону
    @FindBy(xpath = "//div[contains(@class, 'iradio_minimal')]//input[contains(@id, 'round_trip')]/..")
    private WebElement roundTripWayCheckBox; // checkBox в обе стороны
    @FindBy(xpath = "//div[contains(@class, 'iradio_minimal')]//input[contains(@id, 'complex_flight')]/..")
    private WebElement complexFlightWayCheckBox; // checkBox сложный маршрут
    @FindBy(xpath = "//input[contains(@id, 'from_name')]")
    private WebElement departureAirportInput; // ввод города откуда
    @FindBy(xpath = "//input[contains(@id, 'from_name1')]")
    private WebElement departureFirstComplexAirportInput; // ввод города откуда в сложном маршруте
    @FindBy(xpath = "//input[contains(@id, 'from_name2')]")
    private WebElement departureSecondComplexAirportInput; // ввод города откуда в сложном маршруте
    @FindBy(xpath = "//ul[contains(@id, 'ui-id-1')]")
    private WebElement departureAirportDropdown; // Dropdown в одну сторону откуда
    @FindBy(xpath = "//ul[contains(@id, 'ui-id-2')]")
    private WebElement arrivalAirportDropdown; // Dropdown в одну сторону куда
    @FindBy(xpath = "//ul[contains(@id, 'ui-id-4')]")
    private WebElement departureFirstComplexAirportDropdown; // Dropdown в сложном маршруте откуда
    @FindBy(xpath = "//ul[contains(@id, 'ui-id-5')]")
    private WebElement arrivalFirstComplexAirportDropdown; // Dropdown в сложном маршруте куда
    @FindBy(xpath = "//ul[contains(@id, 'ui-id-35')]") //ui-id-6
    private WebElement departureSecondComplexAirportDropdown; //Dropdown в сложном маршруте откуда
    @FindBy(xpath = "//ul[contains(@id, 'ui-id-36')]") //ui-id-7
    private WebElement arrivalSecondComplexAirportDropdown; // Dropdown в сложном маршруте куда
    @FindBy(xpath = "//a[contains(@class, 'add_flight')]")
    private WebElement submitButtonAirport; // кнопка добавления перелёта
    @FindBy(xpath = "//fieldset[contains(@id, 'base_direction2')]")
    private WebElement visibleTextbox; //появление новых textbox откуда-куда в сложном маршруте
    @FindBy(xpath = "//input[contains(@id, 'to_name')]")
    private WebElement arrivalAirportInput; //ввод города куда
    @FindBy(xpath = "//input[contains(@id, 'to_name1')]")
    private WebElement arrivalFirstComplexAirportInput; //ввод города куда в сложном маршруте
    @FindBy(xpath = "//input[contains(@id, 'to_name2')]")
    private WebElement arrivalSecondComplexAirportInput; //ввод города куда в сложном маршруте
    @FindBy(xpath = "//input[contains(@id, 'departure_date')]")
    private WebElement departureDateInput;  //дата отправления
    @FindBy(xpath = "//input[contains(@id, 'departure_date1')]")
    private WebElement departureFirstDateInput;  //дата отправления  в сложном маршруте
    @FindBy(xpath = "//input[contains(@id, 'departure_date2')]")
    private WebElement departureSecondDateInput; //дата отправления  в сложном маршруте
    @FindBy(xpath = "//input[contains(@id, 'departure_date_1')]")
    private WebElement returnDateInput; //дата возвращения в обе стороны
    @FindBy(xpath = "//div[contains(@id, 'ui-datepicker-div')]")
    private WebElement departureDateDropdown; // вывод календаря
    @FindBy(xpath = "//input[contains(@class, 'preson_quant')]")
    private WebElement personQuantityInput; // выбор кто едет
    @FindBy(xpath = "//div[contains(@class, 'persons_select_popup')]")
    private WebElement personSelectDropDown; // DropDown пассажиров
    @FindBy(xpath = "//div[contains(@id, 'Class_Select_chosen')]//a[contains(@class, 'chosen-single')]")
    private WebElement classSelectContainer; // выбор каким классом ехать
    @FindBy(xpath = "//input[contains(@data-uil, 'submit_search')]")
    private WebElement submitButton; //кнопка поиска
    @FindBy(xpath = "//samp[@for='from_name']")
    private WebElement errorMessageInput; // сообщение при пустой строке ввода откуда
    @FindBy(xpath = "//a[contains(@class, 'js-login')]")
    private WebElement registrationLink; // ссылка "Мой билет"
    @FindBy(xpath = "//div[contains(@class, 'popup_login')]")
    private WebElement registrationPopup; // появляющееся окошке авторизации
    @FindBy(xpath = "//div[contains(@class, 'form-item')]//input[contains(@id, 'email')]")
    private WebElement emailInput; // ввод email в окошке авторизации
    @FindBy(xpath = "//div[contains(@class, 'form-item')]/input[contains(@id, 'pass')]")
    private WebElement passwordInput; // ввод password в окошке авторизации
    @FindBy(xpath = "//div[contains(@class, 'login-popup__footer')]//input[contains(@class, 'form-submit')]")
    private WebElement logIn; // кнопка "вход на сайт"
    @FindBy(xpath = "//samp[contains(@class, 'error _idx_0')]")
    private WebElement errorMessageEmail; // бокс с сообщением о неправильно регистрации
    @FindBy(xpath = "//div[contains(@id, 'reg_popup')]/following-sibling::img[contains(@class, 'login_loader')]")
    private WebElement loaderImage; // картинка при обработке данных авторизации
    @FindBy(xpath = "//li[contains(@class, 'nav-contacts__item-03')]//a [contains(@class, 'dropdown js-dropdown')]")
    private WebElement reference; // справка

    private static final String FIRST_AVAILABLE_AIRPORT_XPATH = ".//a"; //нажатие на первую ссылку аэропорта
    private static final String FIRST_AVAILABLE_DATE_XPATH = ".//a[contains(@class, 'ui-state-default')]"; //нажатие на дату в календаре
    private static final String ADULTS_INPUT_XPATH = ".//input[contains(@name, 'adt')]"; // добавление взрослого
    private static final String CHILDREN_INPUT_XPATH = ".//input[contains(@name, 'chd')]"; // добавление детей
    private static final String BABIES_INPUT_XPATH = ".//input[contains(@name, 'inf')]"; // добавление младенца
    private static final String PLUS_BUTTON_FOR_QUANTITY_XPATH = "./parent::li/following-sibling::li/span"; // кнопка + в DropDown выбора пассажира
    private static final String CLASS_SELECT_DROPDOWN_XPATH = "//div[contains(@class, 'chosen-container') and contains(@class, 'chosen-with-drop')]"; // DropDown выбора класса
    private static final String REQUIRED_CLASS_TYPE_XPATH = "//following-sibling::div//li[contains(@class, 'active-result') and contains(text(), '%s')]"; // кликаем на каким классом лететь
    private static final String REFERENCE_CATEGORIE_LINK_XPATH = "./following-sibling::ul//a[contains(text(), '%s')]"; //ссылка для перехода на другие страницы в "справке"

    public StartPage(WebDriver driver) {
        super(driver); //вызов родительского класса
    }

    /*метод для ввода данных в textbox*/
    public void fillInAirportInfo(String airportType) {
        switch (airportType) {
            case DEPARTURE_TEXT:
                System.out.println("Input '" + DEPARTURE_AIRPORT + "' to " + DEPARTURE_TEXT);
                departureAirportInput.click();
                departureAirportInput.sendKeys(DEPARTURE_AIRPORT); //отправка текста
                waiter.waitForVisibilityOfElement(departureAirportDropdown); //стал видимым
                departureAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click(); //первый элемент списка и кликаем на него
                break;
            case DEPARTURE_ERROR_AIRPORT:
                System.out.println("Input '" + DEPARTURE_ERROR_AIRPORT + "' to " + DEPARTURE_TEXT);
                departureAirportInput.click();
                departureAirportInput.sendKeys(DEPARTURE_ERROR_AIRPORT); //отправка текста
                waiter.waitForVisibilityOfElement(departureAirportDropdown); //стал видимым
                departureAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click();
                break;
            case DEPARTURE_ERROR_INPUT_AIRPORT:
                System.out.println("Input '" + DEPARTURE_ERROR_INPUT_AIRPORT + "' to " + DEPARTURE_TEXT);
                departureAirportInput.click();
                departureAirportInput.sendKeys(" "); //отправка текста
                break;
            case ARRIVAL_TEXT:
                System.out.println("Input '" + ARRIVAL_AIRPORT + "' to " + ARRIVAL_TEXT);
                arrivalAirportInput.click();
                arrivalAirportInput.sendKeys(ARRIVAL_AIRPORT);
                waiter.waitForVisibilityOfElement(arrivalAirportDropdown);
                arrivalAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click();
                break;
            default:
                break;
        }
    }

    /*метод для ввода данных "откуда" в сложном маршруте textbox*/
    public void fillComplexDepartureInAirportInfo(String airportType) {
        switch (airportType) {
            case DEPARTURE_TEXT:
                System.out.println("Input '" + DEPARTURE_AIRPORT + "' to " + DEPARTURE_TEXT);
                departureAirportInput.click();
                departureAirportInput.sendKeys(DEPARTURE_AIRPORT); //отправка текста
                waiter.waitForVisibilityOfElement(departureAirportDropdown); //стал видимым
                departureAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click(); //первый элемент списка и кликаем на него
                break;
            case FIRST_COMPLEX_DEPARTURE_AIRPORT:
                System.out.println("Input '" + FIRST_COMPLEX_DEPARTURE_AIRPORT + "' to " + DEPARTURE_TEXT);
                departureFirstComplexAirportInput.click();
                departureFirstComplexAirportInput.sendKeys(FIRST_COMPLEX_DEPARTURE_AIRPORT); //отправка текста
                waiter.waitForVisibilityOfElement(departureFirstComplexAirportDropdown); //стал видимым
                departureFirstComplexAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click(); //первый элемент списка и кликаем на него
                break;
            case SECOND_COMPLEX_DEPARTURE_AIRPORT:
                System.out.println("Input '" + SECOND_COMPLEX_DEPARTURE_AIRPORT + "' to " + DEPARTURE_TEXT);
                departureSecondComplexAirportInput.click();
                departureSecondComplexAirportInput.sendKeys(SECOND_COMPLEX_DEPARTURE_AIRPORT); //отправка текста
                waiter.waitForVisibilityOfElement(departureSecondComplexAirportDropdown); //стал видимым
                departureSecondComplexAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click(); //первый элемент списка и кликаем на него break;
            default:
                break;
        }
    }

    /*метод для ввода данных "куда" в сложном маршруте textbox*/
    public void fillComplexArrivalInAirportInfo(String airportType) {
        switch (airportType) {
            case FIRST_COMPLEX_ARRIVAL_AIRPORT:
                System.out.println("Input '" + FIRST_COMPLEX_ARRIVAL_AIRPORT + "' to " + ARRIVAL_TEXT);
                arrivalAirportInput.click();
                arrivalAirportInput.sendKeys(FIRST_COMPLEX_ARRIVAL_AIRPORT);
                waiter.waitForVisibilityOfElement(arrivalAirportDropdown);
                arrivalAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click();
                break;
            case SECOND_COMPLEX_ARRIVAL_AIRPORT:
                System.out.println("Input '" + SECOND_COMPLEX_ARRIVAL_AIRPORT + "' to " + ARRIVAL_TEXT);
                arrivalFirstComplexAirportInput.click();
                arrivalFirstComplexAirportInput.sendKeys(SECOND_COMPLEX_ARRIVAL_AIRPORT); //отправка текста
                waiter.waitForVisibilityOfElement(arrivalFirstComplexAirportDropdown);
                arrivalFirstComplexAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click();
                break;
            case DEPARTURE_AIRPORT:
                System.out.println("Input '" + DEPARTURE_AIRPORT + "' to " + ARRIVAL_TEXT);
                arrivalSecondComplexAirportInput.click();
                arrivalSecondComplexAirportInput.sendKeys(DEPARTURE_AIRPORT); //отправка текста
                waiter.waitForVisibilityOfElement(arrivalSecondComplexAirportDropdown);
                arrivalSecondComplexAirportDropdown.findElement(By.xpath(FIRST_AVAILABLE_AIRPORT_XPATH)).click();
                break;
            default:
                break;
        }
    }

    /*метод для ввода даты отправления*/
    public void clickDepartureDate(int dayDelta) {
        departureDateInput.click();
        waiter.waitForVisibilityOfElement(departureDateDropdown);
        departureDateDropdown.findElements(By.xpath(FIRST_AVAILABLE_DATE_XPATH)).get(dayDelta).click();
    }

    /*метод для ввода даты возврата*/
    public void clickReturnDate(int dayDelta) {
        returnDateInput.click();
        waiter.waitForVisibilityOfElement(departureDateDropdown);
        departureDateDropdown.findElements(By.xpath(FIRST_AVAILABLE_DATE_XPATH)).get(dayDelta).click();
        new Waiter().start(() -> departureDateDropdown.getAttribute("style").contains("none"));
    }

    /*метод для ввода даты отправления*/
    public void clickFirstDepartureDate(int dayDelta) {
        departureFirstDateInput.click();
        waiter.waitForVisibilityOfElement(departureDateDropdown);
        departureDateDropdown.findElements(By.xpath(FIRST_AVAILABLE_DATE_XPATH)).get(dayDelta).click();
    }

    /*метод для ввода даты отправления*/
    public void clickSecondDepartureDate(int dayDelta) {
        departureSecondDateInput.click();
        waiter.waitForVisibilityOfElement(departureDateDropdown);
        departureDateDropdown.findElements(By.xpath(FIRST_AVAILABLE_DATE_XPATH)).get(dayDelta).click();
    }

    /*метод для нажатия checkbox*/
    public void clickCheckBox(String checkBoxName) {
        switch (checkBoxName) {
            case ONEWAY_TEXT:
                oneWayCheckBox.click();
                break;
            case ROUND_TRIP_TEXT:
                roundTripWayCheckBox.click();
                break;
            case COMPLEX_FLIGHT_WAY_TEXT:
                complexFlightWayCheckBox.click();
                break;
            default:
                break;
        }

    }

    /*для добавления пассажиров*/
    public void selectPersonsQuantity(int adults, int children, int babies) {
        personQuantityInput.click();
        waiter.waitForVisibilityOfElement(personSelectDropDown);
        WebElement adultsInput = personSelectDropDown.findElement(By.xpath(ADULTS_INPUT_XPATH));
        WebElement childrenInput = personSelectDropDown.findElement(By.xpath(CHILDREN_INPUT_XPATH));
        WebElement babiesInput = personSelectDropDown.findElement(By.xpath(BABIES_INPUT_XPATH));
        for (int i = Integer.parseInt(adultsInput.getAttribute("value")); i < adults; i++) // получаем значение value до того момента пока не adults
        {
            adultsInput.findElement(By.xpath(PLUS_BUTTON_FOR_QUANTITY_XPATH)).click(); //кнопки "+"
        }
        for (int i = Integer.parseInt(childrenInput.getAttribute("value")); i < children; i++) {
            childrenInput.findElement(By.xpath(PLUS_BUTTON_FOR_QUANTITY_XPATH)).click();
        }
        for (int i = Integer.parseInt(babiesInput.getAttribute("value")); i < babies; i++) {
            babiesInput.findElement(By.xpath(PLUS_BUTTON_FOR_QUANTITY_XPATH)).click();
        }
    }

    /*метод для выбора каким классом лететь*/
    public void selectClassType(String classType) {
        classSelectContainer.click();
        waiter.waitForElement(driver.findElement(By.xpath(CLASS_SELECT_DROPDOWN_XPATH)));
        driver.findElement(By.xpath(String.format(CLASS_SELECT_DROPDOWN_XPATH + REQUIRED_CLASS_TYPE_XPATH, classType))).click();
    }

    /*нажатие кнопки поиск*/
    public void clickSubmit() {
        submitButton.click();
    }

    /*нажатие кнопки добавления перелёта*/
    public void clickSubmitAirport() {
        submitButtonAirport.click();
        waiter.waitForVisibilityOfElement(visibleTextbox);
    }

    /*проверка на нахождение ошибки при вводе города*/
    public boolean verifyErrorInput() {
        return errorMessageInput.isDisplayed();
    }

    /*нажатие на ссылку "Мой билет"*/
    public void registrationLinkClick() {
        registrationLink.click();
    }

    /*Ввод данных для регистрации*/
    public void fillInRegistrationPupop() {
        waiter.waitForVisibilityOfElement(logIn); //стал видимым
        emailInput.sendKeys(EMAIL);
        passwordInput.sendKeys(PASSWORD);
        logIn.click();
    }

    /*Вылавливание ошибки при регистрации*/
    public boolean verifyErrorMessageEmail() {
        new Waiter().start(() -> loaderImage.getAttribute("style").contains("block")); //смотрит за появлением элемента block
        new Waiter().start(() -> loaderImage.getAttribute("style").contains("none")); //чтобы после элемента не было
        return errorMessageEmail.isDisplayed();
    }

    /*раскрытие списка для перехожа*/
    public void referenceExpand() {
        reference.click();
        new Waiter().start(() -> reference.getAttribute("class").contains("active")); //раскрытие списка
    }

    /*переход страницы*/
    public void openCategoryFromReference(String categoryName) {
        referenceExpand();
        reference.findElement(By.xpath(String.format(REFERENCE_CATEGORIE_LINK_XPATH, categoryName))).click();
        ArrayList<String> openedTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openedTabs.get(openedTabs.size() - 1)); // переход на новую страницу
    }
}
