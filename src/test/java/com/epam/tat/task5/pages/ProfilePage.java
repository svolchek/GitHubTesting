package com.epam.tat.task5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.ConfigProperties;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//button[@class='btn width-full js-user-profile-bio-toggle']")
    WebElement bioButton;
    @FindBy(xpath ="//a[@class='header-logo-invertocat']" )
    WebElement homeLogo;

    public HomePage addBio(String biography){
        bioButton.click();
        WebElement inputField = driver.findElement(By.tagName("textarea"));
        WebElement saveButton= driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']"));
        inputField.clear();
        inputField.sendKeys(biography);
        saveButton.click();
        //homeLogo.click();
        driver.navigate().to(ConfigProperties.getTestProperty("url"));
        return new HomePage(driver);
    }
    public  boolean chekBio(String biography){
        WebElement bioText = driver.findElement(By.xpath("//div[@class='d-inline-block mb-3 js-user-profile-bio-contents']/div"));
        return biography.equalsIgnoreCase(bioText.getText().trim());
    }
    public HomePage returnToHomePage(){
        driver.navigate().to(ConfigProperties.getTestProperty("url"));

        //      homeLogo.click();
        return new HomePage(driver);
    }


}
