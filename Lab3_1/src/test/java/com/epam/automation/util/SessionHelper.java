package com.epam.automation.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;

public class SessionHelper {
    public static DesiredCapabilities getBrowserCaps(String browser) //параметры, которые передаются и используются
    {
        DesiredCapabilities capabilities = null;
        final String CHROMEDRIVER_WIN_PATH="src/test/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_WIN_PATH);
        ChromeOptions chrOpt = new ChromeOptions();
        chrOpt.addArguments("test-type"); //для теста
        chrOpt.addArguments("disable-plugins");
        chrOpt.addArguments("disable-extensions");
        chrOpt.addArguments("start-maximized"); //открытие на весь экран
        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chrOpt);

        return capabilities;
    }
}