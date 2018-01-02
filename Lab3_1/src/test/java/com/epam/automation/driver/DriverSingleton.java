package com.epam.automation.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;

public class DriverSingleton /*класс для подключение драйвера*/{

    private static WebDriver webDriver;

    public static WebDriver getDriver(DesiredCapabilities cap) {
        if (webDriver == null) {

            webDriver = new ChromeDriver(cap); //Создаём драйвер
        }
        System.out.println("Webdriver started");
        return webDriver;
    }

    public static void closeDriver()  //Закрываем драйвер
    {
        webDriver.quit();
        webDriver = null;
    }
}