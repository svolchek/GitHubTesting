package com.epam.tat.task5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="login_field")
    WebElement loginField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy (xpath = "//input[@type='submit']")
    WebElement signInButton;

    public void enterUserName(String userName){
        loginField.click();
        loginField.clear();
        loginField.sendKeys(userName);
    }

    public void enterUserPassword (String userPassword){
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(userPassword);
    }

    public HomePage loginUser(String userName, String password){
        enterUserName(userName);
        enterUserPassword(password);
        signInButton.click();
        return new HomePage(driver);
    }

}
