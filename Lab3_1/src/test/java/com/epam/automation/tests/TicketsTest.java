package com.epam.automation.tests;

import com.epam.automation.constans.CommonConstans;
import com.epam.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TicketsTest extends PreparationSteps implements CommonConstans {

    @Test
    public void ticketSearch() /*тест для поиска билета в одну сторону*/
    {
        StartPage startPage = new StartPage(driver);
        LoadingPage loadingPage = new LoadingPage(driver);
        ResultPage resultPage = new ResultPage(driver);
        startPage.clickCheckBox(ONEWAY_TEXT);
        startPage.fillInAirportInfo(DEPARTURE_TEXT);
        startPage.fillInAirportInfo(ARRIVAL_TEXT);
        startPage.clickDepartureDate(1);
        startPage.selectClassType("Эконом");
        startPage.selectPersonsQuantity(2,1,1);
        startPage.clickSubmit();
        loadingPage.waitForPageLoading();
        Assert.assertTrue(resultPage.verifySortButtonsPresent());
        Assert.assertTrue(resultPage.verifyBagButtonsPresent());
        Assert.assertTrue(resultPage.verifyTicketsPresent());
    }

    @Test
    public void changeCurrency () /*тест для изменения валюты на билеты при покупке*/
    {
        StartPage startPage = new StartPage(driver);
        LoadingPage loadingPage = new LoadingPage(driver);
        ResultPage resultPage = new ResultPage(driver);
        startPage.clickCheckBox(ONEWAY_TEXT);
        startPage.fillInAirportInfo(DEPARTURE_TEXT);
        startPage.fillInAirportInfo(ARRIVAL_TEXT);
        startPage.clickDepartureDate(1);
        startPage.clickSubmit();
        loadingPage.waitForPageLoading();
        resultPage.selectRequiredCurrency(CHANGE_TO_EUR);
        Assert.assertEquals(resultPage.getCurrentCurrency(),CHANGE_TO_EUR); //проверка на совпадение
        resultPage.selectRequiredCurrency(CHANGE_TO_USD);
        Assert.assertEquals(resultPage.getCurrentCurrency(),CHANGE_TO_USD);
        resultPage.selectRequiredCurrency(CHANGE_TO_BYN);
        Assert.assertEquals(resultPage.getCurrentCurrency(),CHANGE_TO_BYN);
    }

    @Test
    public void sortAll() /*тест на сортировку*/
    {
        StartPage startPage = new StartPage(driver);
        LoadingPage loadingPage = new LoadingPage(driver);
        ResultPage resultPage = new ResultPage(driver);
        startPage.clickCheckBox(ONEWAY_TEXT);
        startPage.fillInAirportInfo(DEPARTURE_TEXT);
        startPage.fillInAirportInfo(ARRIVAL_TEXT);
        startPage.clickDepartureDate(2);
        startPage.clickSubmit();
        loadingPage.waitForPageLoading();
        resultPage.removeGlobasLoader();
        resultPage.clickBaggage(WITH_BAG);
        Assert.assertTrue(resultPage.verifyBagStatusInTickets(true));
        resultPage.clickBaggage(WITHOUT_BAG);
        Assert.assertTrue(resultPage.verifyBagStatusInTickets(false));
    }

    @Test
    public void ticketSearchError() /*тест для проверки ошибки поиска*/
    {
        StartPage startPage = new StartPage(driver);
        LoadingPage loadingPage = new LoadingPage(driver);
        startPage.clickCheckBox(ONEWAY_TEXT);
        startPage.fillInAirportInfo(DEPARTURE_ERROR_AIRPORT);
        startPage.fillInAirportInfo(ARRIVAL_TEXT);
        startPage.clickDepartureDate(0);
        startPage.selectClassType("Эконом");
        startPage.clickSubmit();
        loadingPage.waitForErrorMessageLoading();
        Assert.assertTrue(loadingPage.verifyErrorMessage());
    }

    @Test
    public void ticketSearchRoundTrip() /*тест для поиска билета в обе стороны*/
    {
        StartPage startPage = new StartPage(driver);
        LoadingPage loadingPage = new LoadingPage(driver);
        ResultPage resultPage = new ResultPage(driver);
        startPage.fillInAirportInfo(DEPARTURE_TEXT);
        startPage.fillInAirportInfo(ARRIVAL_TEXT);
        startPage.clickCheckBox(ROUND_TRIP_TEXT);
        startPage.clickDepartureDate(1);
        startPage.clickReturnDate(5);
        startPage.selectClassType("Эконом");
        startPage.selectPersonsQuantity(2,1,1);
        startPage.clickSubmit();
        loadingPage.waitForPageLoading();
        Assert.assertTrue(resultPage.verifySortButtonsPresent());
        Assert.assertTrue(resultPage.verifyBagButtonsPresent());
        Assert.assertTrue(resultPage.verifyTicketsPresent());
    }

    @Test
    public void ticketSearchDifficultRoute() /*тест для поиска билета сложный маршрут*/
    {
        StartPage startPage = new StartPage(driver);
        LoadingPage loadingPage = new LoadingPage(driver);
        ResultPage resultPage = new ResultPage(driver);
        startPage.clickCheckBox(COMPLEX_FLIGHT_WAY_TEXT);
        startPage.fillComplexDepartureInAirportInfo(DEPARTURE_TEXT);
        startPage.fillComplexArrivalInAirportInfo(FIRST_COMPLEX_ARRIVAL_AIRPORT);
        startPage.clickDepartureDate(1);
        startPage.fillComplexDepartureInAirportInfo(FIRST_COMPLEX_DEPARTURE_AIRPORT);
        startPage.fillComplexArrivalInAirportInfo(SECOND_COMPLEX_ARRIVAL_AIRPORT);
        startPage.clickFirstDepartureDate(3);
        startPage.clickSubmitAirport();
        startPage.fillComplexDepartureInAirportInfo(SECOND_COMPLEX_DEPARTURE_AIRPORT);
        startPage.fillComplexArrivalInAirportInfo(DEPARTURE_AIRPORT);
        startPage.clickSecondDepartureDate(10);
        startPage.selectClassType("Эконом");
        startPage.selectPersonsQuantity(2,1,1);
        startPage.clickSubmit();
        loadingPage.waitForPageLoading();
        Assert.assertTrue(resultPage.verifySortButtonsPresent());
        Assert.assertTrue(resultPage.verifyBagButtonsPresent());
        Assert.assertTrue(resultPage.verifyTicketsPresent());
    }

    @Test
    public void ticketInputError() /*тест для проверки ошибочного ввода*/
    {
        StartPage startPage = new StartPage(driver);
        startPage.clickCheckBox(ONEWAY_TEXT);
        startPage.fillInAirportInfo(DEPARTURE_ERROR_INPUT_AIRPORT);
        startPage.fillInAirportInfo(ARRIVAL_TEXT);
        startPage.clickDepartureDate(0);
        startPage.selectClassType("Эконом");
        startPage.clickSubmit();
        Assert.assertTrue(startPage.verifyErrorInput());
    }

    @Test
    public void registration() /*тест для проверки регистрации*/
    {
        StartPage startPage = new StartPage(driver);
        startPage.registrationLinkClick();
        startPage.fillInRegistrationPupop();
        Assert.assertTrue(startPage.verifyErrorMessageEmail());
    }

    @Test
    public void reference() /*тест для проверки перехода на страницы*/
    {
        StartPage startPage = new StartPage(driver);
        FAQPage faqPage = new FAQPage(driver);
        CountryPage countryPage = new CountryPage(driver);
        HelpPage helpPage = new HelpPage(driver);
        startPage.openCategoryFromReference(QUESTION_ANSWER);
        Assert.assertEquals("Все об авиабилетах: Топ-вопросы / aviakassa.by", faqPage.getTitle());
        startPage.openCategoryFromReference(DIRECTORY_OF_AIRPORTS);
        Assert.assertEquals(countryPage.getTitle(),"Авиабилеты Беларусь онлайн. Дешевые билеты на самолеты Беларусь / Aviakassa.by");
        startPage.openCategoryFromReference(TECHNICAL_REFERENCE);
        Assert.assertEquals(helpPage.getTitle(),"Техническая справка: Регистрация на сайте / aviakassa.by");
    }
}