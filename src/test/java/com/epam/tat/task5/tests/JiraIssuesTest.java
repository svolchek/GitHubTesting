package com.epam.tat.task5.tests;


import com.epam.tat.task5.drivers.ChromeDriver;
import com.epam.tat.task5.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static  org.testng.Assert.*;

public class JiraIssuesTest {
    @BeforeTest
    public void init() {
        WebDriver driver = ChromeDriver.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://github.com");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.signIn();
        homePage=loginPage.loginUser("svolchek","swiss30Made");
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
    public void addGist(){
        String fileName = "Gist.java"; //choose the file in resources dir
        HomePage homePage = new HomePage(ChromeDriver.getDriver());
        GistPage gistPage = homePage.addNewGist();
        gistPage.createGist("PrettyGist", fileName);
        assertEquals(gistPage.gistName(),fileName);
    }
    @Test (description = "Import new repository", priority = 2)
    public void importRepository(){
        String repositoryURL = "https://github.com/vitalliuss/helloci.git";
        String repositoryName = "h34GHa";
        HomePage homePage = new HomePage(ChromeDriver.getDriver());
        ImportPage importPage = homePage.importRepository();
        homePage= importPage.addRepository(repositoryURL,repositoryName);
        RepositoriesPage repositoriesPage = homePage.checkRepository();
        assertTrue(repositoriesPage.findRepo(repositoryName));
    }

    @Test (description = "Add new biography to the profile", priority = 3)
    public void editBio(){
        String newBio = "Hello. My name is Sergei";
        HomePage homePage=new HomePage(ChromeDriver.getDriver());
        ProfilePage profilePage = homePage.editProfile();
        homePage=profilePage.addBio(newBio);
        profilePage = homePage.editProfile();
        boolean isBiographyCorrect = false;
        isBiographyCorrect=profilePage.chekBio(newBio);
        homePage = profilePage.returnToHomePage();
        assertTrue(isBiographyCorrect);
    }
    @Test (description = "Create new repository", priority = 4)
    public void newRepository(){
        String repositoryName = "nRepo";
        String description = "One day little red riding hood decided to visit her granny-wolf in the forest";
        HomePage homePage=new HomePage(ChromeDriver.getDriver());
        NewRepositoryPage newRepositoryPage = homePage.createNewRepository();
        homePage = newRepositoryPage.createRepository(repositoryName,description);
        RepositoriesPage repositoriesPage =homePage.checkRepository();
        assertTrue(repositoriesPage.findRepo(repositoryName));
    }

    @Test(description = "Delete repository", priority = 5)
    public void deleteRepository(){
        String repositoryName = "nRepo";
        HomePage homePage=new HomePage(ChromeDriver.getDriver());
        RepositoriesPage repositoriesPage =homePage.checkRepository();
        SingleRepositoryPage singleRepositoryPage = repositoriesPage.clickRepositoryLink(repositoryName);
        RepositorySettingsPage repositorySettingsPage = singleRepositoryPage.moveToSettingMenu();
        homePage = repositorySettingsPage.deleteRepository(repositoryName);
        repositoriesPage=homePage.checkRepository();
        assertFalse(repositoriesPage.findRepo(repositoryName));
    }


}
