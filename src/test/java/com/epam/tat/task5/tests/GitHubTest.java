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
        WebDriver driver = Driver.getRemoteDriver();
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
//        HomePage homePage = new HomePage(Driver.getDriver());
//        ImportPage importPage = homePage.importRepository();
//        homePage= importPage.addRepository(repositoryURL,importRepositoryName);
        RepositoriesPage repositoriesPage =
                new HomePage(Driver.getDriver())
                        .importRepository()
                        .addRepository(repositoryURL,importRepositoryName)
                        .checkRepository();
        assertTrue(repositoriesPage.findRepo(importRepositoryName));
    }

    @Test (description = "Add new biography to the profile", priority = 3)
    @Parameters({"newBiography"})
    public void editBio(String newBiography){

        boolean isBiographyCorrect =
                new HomePage(Driver.getDriver())
                        .editProfile()
                        .addBio(newBiography)
                        .editProfile()
                        .chekBio(newBiography);

        assertTrue(isBiographyCorrect);
    }
    @Test (description = "Create new repository", priority = 4)
    @Parameters({"repositoryName", "repositoryDescription"})
    public void newRepository(String repositoryName, String repositoryDescription){
        RepositoriesPage repositoriesPage =
                new HomePage(Driver.getDriver())
                        .createNewRepository()
                        .createRepository(repositoryName,repositoryDescription)
                        .checkRepository();
        assertTrue(repositoriesPage.findRepo(repositoryName));
    }

    @Test(description = "Delete repository", dataProvider = "Repositories to be deleted",priority = 5)
    public void deleteRepository(String repositoryName){
        RepositoriesPage repositoriesPage =
                new HomePage(Driver.getDriver())
                    .checkRepository()
                    .clickRepositoryLink(repositoryName)
                    .moveToSettingMenu()
                    .deleteRepository(repositoryName)
                    .checkRepository();
        assertFalse(repositoriesPage.findRepo(repositoryName));
    }
    @DataProvider(name = "Repositories to be deleted")
    Object[][] deleteRepoDataProvider(){
        return new Object[][]{{"helloCI1410"},{"nRepository"}};
    }


}
