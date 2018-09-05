package com.epam.tat.task5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Gist {
    WebDriver driver;

    public GistPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "gist[description]")
    WebElement descriptionField;
    @FindBy(name ="gist[contents][][name]" )
    WebElement gistFileField;
    @FindBy(name = "gist[public]")
    WebElement addButton;

    public void enterDescription(String description){
        descriptionField.click();
        descriptionField.sendKeys(description);
    }
    public void enterFileName(String fileName){
        gistFileField.click();
        gistFileField.clear();
        gistFileField.sendKeys(fileName);
        gistFileField.sendKeys(Keys.TAB);

    }
