package com.epam.tat.task5.tests;


import com.epam.tat.task5.drivers.ChromeDriver;
import com.epam.tat.task5.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static  org.testng.Assert.*;

public class GitHubTest {
    @BeforeTest
    @Parameters({"gitHubURL","userLogin","userPassword"})
    public void init(String gitHubURL, String userLogin, String userPassword) {
        WebDriver driver = ChromeDriver.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to(gitHubURL);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.signIn();
        loginPage.loginUser(userLogin,userPassword);
    }
    @AfterMethod
    public void moveToHomePage(){
        ChromeDriver.getDriver().navigate().to("http://github.com");
    }
    @AfterTest
    public void closeResources(){
        ChromeDriver.getDriver().quit();
    }

    @Test(description = "Add new gist", priority = 1)
    @Parameters({"filePath","gistDescription"})
    public void addGist(String filePath, String gistDescription){
        String fileName = filePath; //choose the file in resources dir
        HomePage homePage = new HomePage(ChromeDriver.getDriver());
        GistPage gistPage = homePage.addNewGist();
        gistPage.createGist(gistDescription, fileName);
        assertEquals(gistPage.gistName(),fileName);
    }

    @Test (description = "Import new repository", priority = 2)
    @Parameters({"repositoryURL","importRepositoryName"})
    public void importRepository(String repositoryURL, String importRepositoryName){
        HomePage homePage = new HomePage(ChromeDriver.getDriver());
        ImportPage importPage = homePage.importRepository();
        homePage= importPage.addRepository(repositoryURL,importRepositoryName);
        RepositoriesPage repositoriesPage = homePage.checkRepository();
        assertTrue(repositoriesPage.findRepo(importRepositoryName));
    }

    @Test (description = "Add new biography to the profile", priority = 3)
    @Parameters({"newBiography"})
    public void editBio(String newBiography){
        HomePage homePage=new HomePage(ChromeDriver.getDriver());
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
        HomePage homePage=new HomePage(ChromeDriver.getDriver());
        NewRepositoryPage newRepositoryPage = homePage.createNewRepository();
        homePage = newRepositoryPage.createRepository(repositoryName,repositoryDescription);
        RepositoriesPage repositoriesPage =homePage.checkRepository();
        assertTrue(repositoriesPage.findRepo(repositoryName));
    }

    @Test(description = "Delete repository", dataProvider = "Repositories to be deleted",priority = 5)
    public void deleteRepository(String repositoryName){
        HomePage homePage=new HomePage(ChromeDriver.getDriver());
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
