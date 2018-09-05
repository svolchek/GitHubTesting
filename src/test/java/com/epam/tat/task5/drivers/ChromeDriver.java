package com.epam.tat.task5.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigProperties;

import java.util.concurrent.TimeUnit;

public class ChromeDriver {
    private static ChromeDriver driver;
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private ChromeDriver(){
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
        webDriver = new org.openqa.selenium.chrome.ChromeDriver();
        webDriverWait = new WebDriverWait(webDriver,10);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    public static WebDriver getDriver(){
        if(driver==null) {driver = new ChromeDriver();};
        return driver.webDriver;
    }
    public static WebDriverWait getWaiter (){
        return driver.webDriverWait;

    }

}
