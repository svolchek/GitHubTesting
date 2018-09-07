package com.epam.tat.task5.tests;


import com.epam.tat.task5.drivers.Driver;
import com.epam.tat.task5.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.ConfigProperties;

import static  org.testng.Assert.*;

public class GitHubTest {
    @BeforeTest
    public void init() {
        WebDriver driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to(ConfigProperties.getTestProperty("url"));
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.signIn();
        loginPage.loginUser(ConfigProperties.getTestProperty("userLogin"),ConfigProperties.getTestProperty("userPassword"));
    }
    @AfterMethod
    public void moveToHomePage(){
        Driver.getDriver().navigate().to(ConfigProperties.getTestProperty("url"));
    }
    @AfterTest
    public void closeResources(){
        Driver.getDriver().quit();
    }

    @Test(description = "Add new gist", priority = 1)
    @Parameters({"filePath","gistDescription"})
    public void addGist(String filePath, String gistDescription){
        String fileName = filePath; //choose the file in resources dir
        HomePage homePage = new HomePage(Driver.getDriver());
        GistPage gistPage = homePage.addNewGist();
        gistPage.createGist(gistDescription, fileName);
        assertEquals(gistPage.gistName(),fileName);
    }

    @Test (description = "Import new repository", priority = 2)
    @Parameters({"repositoryURL","importRepositoryName"})
    public void importRepository(String repositoryURL, String importRepositoryName){
        HomePage homePage = new HomePage(Driver.getDriver());
        ImportPage importPage = homePage.importRepository();
        homePage= importPage.addRepository(repositoryURL,importRepositoryName);
        RepositoriesPage repositoriesPage = homePage.checkRepository();
        assertTrue(repositoriesPage.findRepo(importRepositoryName));
    }

    @Test (description = "Add new biography to the profile", priority = 3)
    @Parameters({"newBiography"})
    public void editBio(String newBiography){
        HomePage homePage=new HomePage(Driver.getDriver());
        ProfilePage profilePage = homePage.editProfile();
        homePage=profilePage.addBio(newBiography);
        profilePage = homePage.editProfile();
        boolean isBiographyCorrect =profilePage.chekBio(newBiography);
        profilePage.returnToHomePage();
        assertTrue(isBiographyCorrect);
    }
    @Test (description = "Create new repository", priority = 4)
    @Parameters({"repositoryName", "repositoryDescription"})
    public void newRepository(String repositoryName, String repositoryDescription){
        HomePage homePage=new HomePage(Driver.getDriver());
        NewRepositoryPage newRepositoryPage = homePage.createNewRepository();
        homePage = newRepositoryPage.createRepository(repositoryName,repositoryDescription);
        RepositoriesPage repositoriesPage =homePage.checkRepository();
        assertTrue(repositoriesPage.findRepo(repositoryName));
    }

    @Test(description = "Delete repository", dataProvider = "Repositories to be deleted",priority = 5)
    public void deleteRepository(String repositoryName){
        HomePage homePage=new HomePage(Driver.getDriver());
        RepositoriesPage repositoriesPage =homePage.checkRepository();
        SingleRepositoryPage singleRepositoryPage = repositoriesPage.clickRepositoryLink(repositoryName);
        RepositorySettingsPage repositorySettingsPage = singleRepositoryPage.moveToSettingMenu();
        homePage = repositorySettingsPage.deleteRepository(repositoryName);
        repositoriesPage=homePage.checkRepository();
        assertFalse(repositoriesPage.findRepo(repositoryName));
    }
    @DataProvider(name = "Repositories to be deleted")
    Object[][] deleteRepoDataProvider(){
        return new Object[][]{{"helloCI1410"},{"nRepository"}};
    }


}
