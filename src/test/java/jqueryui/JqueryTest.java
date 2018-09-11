package jqueryui;

import com.epam.tat.task5.drivers.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class JqueryTest {
    WebDriver driver;
    private final String URL = "http://jqueryui.com";
    @BeforeClass
    void init() {
        driver = DriverFactory.CHROMEDRIVER.getDriver();
        driver.manage().window().fullscreen();
        driver.navigate().to(URL);
    }
    @Test
    void wikiTest(){
        driver.navigate().to("https://ru.wikipedia.org");
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        JavascriptExecutor  jsExec = (JavascriptExecutor) driver;
        for (int i = 0; i <100 ; i++) {
        links.forEach(x->{
                            //String bg = x.getCssValue("backgroundColor");
                            jsExec.executeScript("arguments[0].style.backgroundColor = ' "+getColor()+"'",x);});
    }


    }
    @Test
    void testDraggable(){
        driver.navigate().to(URL+"/draggable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement draggable = driver.findElement(By.id("draggable"));

        Actions actions = new Actions(driver);
        for (int i = 0; i < 10; i++) {
            actions.dragAndDropBy(draggable,50,50).perform();
            actions.dragAndDropBy(draggable,-40,-40).perform();
        }
    }
    @Test
    void testAccordion(){
        driver.navigate().to(URL+"/accordion/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        JavascriptExecutor jExec = (JavascriptExecutor) driver;
        jExec.executeScript("document.getElementById('ui-id-5').click()");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testDroppable(){
        driver.navigate().to(URL+"/droppable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        System.out.println(draggable.getText()+" "+droppable.getText());
        Actions action1 =new Actions(driver);
        action1.dragAndDrop(draggable,droppable).perform();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement dropText = driver.findElement(By.xpath("//*[@id='droppable']/p"));
        Assert.assertEquals(dropText.getText().trim(),"Dropped!");
    }
    @Test
    void testResizable(){
        driver.navigate().to(URL+"/resizable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement resizable = driver.findElement(By.id("resizable"));
        WebElement point = driver.findElement(By.xpath("//div[@id='resizable']//following-sibling::div[3]"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(point,100,100).perform();
        Dimension dimension = new Dimension(268,268);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(dimension.equals(resizable.getSize()));
    }
    @Test
    void testSortable(){
        driver.navigate().to(URL+"/sortable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement element = driver.findElement(By.xpath("//ul/li[1]"));
        System.out.println(element.getSize());

        for (int i = 0; i <1000 ; i++) {
            int pos = (int)(Math.random()*7+1);
            WebElement element1 = driver.findElement(By.xpath(String.format("//ul/li[%d]",pos)));
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(element1,0,40*((int)(Math.random()*7-pos))).perform();
        }
    }

    @AfterClass
    void closeResources(){
       driver.quit();
    }

    static String getColor(){
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(0xFFFFFF);
        System.out.printf("%x\n",myRandomNumber);
        String result = Integer.toHexString(myRandomNumber);
        return "#"+result.toUpperCase();
    }


}
