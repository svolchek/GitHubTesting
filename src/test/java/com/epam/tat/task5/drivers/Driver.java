package com.epam.tat.task5.drivers;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static Driver driver;
    private WebDriver remoteWebDriver;
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private Driver(){
        //System.setProperty("webdriver.gecko.driver", ConfigProperties.getTestProperty("geckodriver"));
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
        //webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
        //webDriver = new FirefoxDriver();
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setPlatform(Platform.WINDOWS);
        try {
            remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        webDriverWait = new WebDriverWait(remoteWebDriver,10);
       remoteWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    public static WebDriver getDriver(){
//        if(driver==null) {driver = new Driver();};
//        return driver.webDriver;
        if(driver==null) {driver = new Driver();};
        return driver.remoteWebDriver;
    }
    public static WebDriver getRemoteDriver(){
        if(driver==null) {driver = new Driver();};
        return driver.remoteWebDriver;
    }
    public static WebDriverWait getWaiter (){
        return driver.webDriverWait;

    }

}
