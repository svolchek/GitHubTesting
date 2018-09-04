package com.epam.tat.task5.pages;

import com.epam.tat.task5.drivers.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Factory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public  class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "user[login]")
    WebElement loginUserName;
    @FindBy(id = "user[email]")
    WebElement loginUserEmail;
    @FindBy(id = "user[password]")
    WebElement loginUserPassword;
    @FindBy(xpath = "//form/button")
    WebElement signUpButton;
    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    WebElement signInButton;
    @FindBy (xpath = "//span[@class='dropdown-caret mt-1']")
    WebElement addIcon;
    @FindBy(xpath = "//a[@class='header-logo-invertocat']")
    WebElement logoButton;
    @FindBy(xpath = "//details-menu/ul/li/a")
    List<WebElement> menuList;
    @FindBy(xpath = "//span[@class='dropdown-caret']")
    WebElement addMenuIcon;



    public void enterUserName(String userName){
        loginUserName.click();
        loginUserName.clear();
        loginUserName.sendKeys(userName);
    }

    public LoginPage signIn(){
        ChromeDriver.getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Sign in')]")));
        signInButton.click();
        return new LoginPage(driver);
    }

    public GistPage addNewGist(){
        addIcon.click();
        ChromeDriver.getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@id='user-links']//details-menu)[1]")));
        List<WebElement> addItems = driver.findElements(By.xpath("(//*[@id='user-links']//details-menu)[1]/a"));
        Optional<WebElement> newGistButton = addItems.stream().filter(s -> s.getText().trim().equals("New gist")).reduce((a, b) -> null);
        newGistButton.orElseThrow(NoSuchElementException::new).click();
        return new GistPage(driver);
    }
    public ImportPage importRepository(){
        addIcon.click();
        ChromeDriver.getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@id='user-links']//details-menu)[1]")));
        List<WebElement> addItems = driver.findElements(By.xpath("(//*[@id='user-links']//details-menu)[1]/a"));
        Optional<WebElement> newGistButton = addItems.stream().filter(s -> s.getText().trim().equals("Import repository")).reduce((a, b) -> null);
        newGistButton.orElseThrow(NoSuchElementException::new).click();
        return new ImportPage(driver);

    }
    public RepositoriesPage checkRepository(){
        addMenuIcon.click();
        Optional<WebElement> repoButton = menuList.stream().filter(s -> s.getText().trim().equals("Your repositories")).reduce((a, b) -> null);
        repoButton.orElseThrow(NoSuchElementException::new).click();
        return new RepositoriesPage(driver);
    }
    public ProfilePage editProfile(){
        addMenuIcon.click();
        Optional<WebElement> newGistButton = menuList.stream().filter(s -> s.getText().trim().equals("Your profile")).reduce((a, b) -> null);
        newGistButton.orElseThrow(NoSuchElementException::new).click();
        return new ProfilePage(driver);
    }
    public NewRepositoryPage createNewRepository(){
        addIcon.click();
        ChromeDriver.getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@id='user-links']//details-menu)[1]")));
        List<WebElement> addItems = driver.findElements(By.xpath("(//*[@id='user-links']//details-menu)[1]/a"));
        Optional<WebElement> newRepoButton = addItems.stream().filter(s -> s.getText().trim().equals("New repository")).reduce((a, b) -> null);
        newRepoButton.orElseThrow(NoSuchElementException::new).click();
        return new NewRepositoryPage(driver);
    }

}
