package com.epam.tat.task5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RepositoriesPage {
    WebDriver driver;

    public RepositoriesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//h3/a")
    List<WebElement> repoList;

    public boolean findRepo(String repoName){
         Optional<WebElement> repository = Optional.ofNullable(repoList.stream()
                 .filter(s -> repoName.equalsIgnoreCase(s.getText().trim()))
                 .findAny()
                 .orElse(null));
         return repository.isPresent();
    }
    public SingleRepositoryPage clickRepositoryLink(String repoName){
        Optional<WebElement> repository = Optional.ofNullable(repoList.stream()
                .filter(s -> repoName.equalsIgnoreCase(s.getText().trim()))
                .findAny()
                .orElse(null));
        repository.orElseThrow(NoSuchElementException::new).click();
        return new SingleRepositoryPage(driver);
    }

}
