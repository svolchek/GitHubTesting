package com.epam.tat.task5.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.ConfigProperties;

public enum DriverFactory {
    CHROMEDRIVER
            {
                private WebDriver driver;
                public WebDriver getDriver(){
                    if(driver==null){
                        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
                        driver=new ChromeDriver();}
                        return driver;}
            },
    FIREFOXDRIVER
            {
                WebDriver driver;
                public WebDriver getDriver(){
                    if(driver==null){
                        System.setProperty("webdriver.geckodriver.driver", ConfigProperties.getTestProperty("geckodriver"));
                        driver=new FirefoxDriver();}
                    return driver;}
            };
            public abstract  WebDriver getDriver();
}
