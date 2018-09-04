package com.epam.tat.task5.pages;

import com.epam.tat.task5.drivers.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewRepositoryPage {
    WebDriver driver;

    public NewRepositoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//a[@class='header-logo-invertocat']" )
    WebElement homeLogo;
    @FindBy(id = "repository_name")
    WebElement name;
    @FindBy(id = "repository_description")
    WebElement description;


    public HomePage createRepository(String repoName, String repoDescription){
        name.click();
        name.clear();
        name.sendKeys(repoName);
        description.click();
        description.clear();
        description.sendKeys(repoDescription);
        ChromeDriver.getWaiter().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary first-in-line']")));
        WebElement createButton = driver.findElement(By.xpath("//button[@class='btn btn-primary first-in-line']"));
        createButton.click();
        WebElement homeLogo = driver.findElement(By.xpath("//a[@class='header-logo-invertocat']"));
        return new HomePage(driver);
    }

}
