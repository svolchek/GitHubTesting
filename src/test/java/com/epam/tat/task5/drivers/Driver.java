package com.epam.tat.task5.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigProperties;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static Driver driver;
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private Driver(){
        //System.setProperty("webdriver.gecko.driver", ConfigProperties.getTestProperty("geckodriver"));
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
        webDriver = new ChromeDriver(); System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
        //webDriver = new FirefoxDriver();
        webDriverWait = new WebDriverWait(webDriver,10);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    public static WebDriver getDriver(){
        if(driver==null) {driver = new Driver();};
        return driver.webDriver;
    }
    public static WebDriverWait getWaiter (){
        return driver.webDriverWait;

    }

}
