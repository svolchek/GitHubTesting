package com.epam.tat.task5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RepositorySettingsPage {
    WebDriver driver;

    public RepositorySettingsPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//summary[@class='btn btn-danger boxed-action'])[3]")
    WebElement delButton;

    public HomePage deleteRepository(String repositoryName){
        delButton.click();
        WebElement alertInput = driver.findElement(By.xpath("(//input[@name='verify'])[2]"));
        alertInput.sendKeys(repositoryName);
        WebElement acceptDeleteButton = driver.findElement(By.xpath("(//form[@class='js-normalize-submit']//button)[2]"));
        acceptDeleteButton.click();
        return new HomePage(driver);

    }
}
