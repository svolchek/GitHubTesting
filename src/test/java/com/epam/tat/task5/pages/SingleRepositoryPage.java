package com.epam.tat.task5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingleRepositoryPage {
    WebDriver driver;

    public SingleRepositoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "(//a[@class='js-selected-navigation-item reponav-item'])[6]")
    WebElement settingsButton;

    public RepositorySettingsPage moveToSettingMenu(){
        settingsButton.click();
        return new RepositorySettingsPage(driver);
    }

}
