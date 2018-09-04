package com.epam.tat.task5.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GistPage {
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
    public void addGistText(String fileName){
        driver.findElement(By.className("CodeMirror")).click();
        Actions act = new Actions(driver);
        URL resource = GistPage.class.getResource("/"+fileName);

        try (Stream<String> stream = Files.lines(Paths.get(resource.toURI()))) {
            stream.forEach(s->{ act.sendKeys(s).build().perform();
                act.sendKeys(Keys.ENTER).build().perform();});
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public String gistName(){
        WebElement gistName = driver.findElement(By.xpath("//strong[@itemprop='name']/a"));
        System.out.println(gistName.getText());
        return gistName.getText();

    }



    public void addGist(){
        addButton.click();
    }

    public void createGist(String description, String fileName){
        enterDescription(description);
        enterFileName(fileName);
        addGistText(fileName);
        addGist();
    }

}
