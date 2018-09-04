package com.epam.tat.task5.pages;

import com.epam.tat.task5.drivers.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class ImportPage {
    WebDriver driver;

    public ImportPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@type='url']")
    WebElement importURL;
    @FindBy(id = "repository_name")
    WebElement repositoryName;

    public HomePage addRepository(String strURL, String repName){

        repositoryName.click();
        repositoryName.clear();
        repositoryName.sendKeys(repName);
        importURL.click();
        importURL.clear();
        importURL.sendKeys(strURL);
       ChromeDriver.getWaiter().until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[@type='submit']")));
        WebElement beginImport = driver.findElement(By.xpath("//div/button[@type='submit']"));
        beginImport.click();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement homeLogo=driver.findElement(By.xpath("//a[@class='header-logo-invertocat']"));
        homeLogo.click();
        return new HomePage(driver);
    }

}
