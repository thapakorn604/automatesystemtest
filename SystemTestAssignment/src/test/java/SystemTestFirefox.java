import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by CAMT on 5/2/2017.
 */
@RunWith(JUnitParamsRunner.class)
public class SystemTestFirefox {


    public Object paramLogin1(){
        return new Object[] [] {
                {"admin","password"},{"mitsuha","admin"}

        };
    }
    public Object paramLogin2(){
        return new Object[] [] {

                {"","admin"}

        };
    }
    public Object paramLogin3(){
        return new Object[] [] {

                {"admin",""}
        };
    }
    public Object paramLogin4(){
        return new Object[] [] {

                {"admin","admin"},{"user","user"}
        };
    }

    @Test
    public void testInvalidURL() throws Exception{
        System.setProperty("webdriver.gecko.driver","/D:\\Users\\lenovo\\Git\\SystemTestAssignment\\src\\test\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:4200/notfoundpage");
        driver.findElement(By.cssSelector("img"));
        assertEquals("", driver.findElement(By.cssSelector("img")).getText());
    }
    @Test
    @Parameters(method = "paramLogin1")
    public void testLoginNew1(String username,String password) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","/D:\\Users\\lenovo\\Git\\SystemTestAssignment\\src\\test\\resources\\geckodriver.exe");        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:4200/login");
        assertEquals("List", driver.findElement(By.linkText("List")).getText());
        assertEquals("View", driver.findElement(By.linkText("View")).getText());
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        Thread.sleep(2000);
        assertEquals("Unauthorized", driver.findElement(By.cssSelector("div.alert.alert-error")).getText());






    }
    @Test
    @Parameters(method="paramLogin2")
    public void testLoginNew2(String username,String password) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","D:\\Users\\lenovo\\Git\\SystemTestAssignment\\src\\test\\resources\\geckodriver.exe");        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:4200/login");
        assertEquals("List", driver.findElement(By.linkText("List")).getText());
        assertEquals("View", driver.findElement(By.linkText("View")).getText());
        driver.navigate().refresh();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        Thread.sleep(2000);
        assertEquals("Username is required", driver.findElement(By.className("help-block")).getText());
    }
    @Test
    @Parameters(method="paramLogin3")
    public void testLoginNew3(String username,String password) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","/D:\\Users\\lenovo\\Git\\SystemTestAssignment\\src\\test\\resources\\geckodriver.exe");        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:4200/login");
        assertEquals("List", driver.findElement(By.linkText("List")).getText());
        assertEquals("View", driver.findElement(By.linkText("View")).getText());
        driver.navigate().refresh();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        Thread.sleep(2000);
        assertEquals("Password is required", driver.findElement(By.className("help-block")).getText());
    }

    @Test
    public void testPriorityLogin() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","/D:\\Users\\lenovo\\Git\\SystemTestAssignment\\src\\test\\resources\\geckodriver.exe");        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        Thread.sleep(5000);
        assertEquals("List", driver.findElement(By.linkText("List")).getText());
        assertEquals("Add", driver.findElement(By.linkText("Add")).getText());
        assertEquals("View", driver.findElement(By.linkText("View")).getText());
        assertEquals("Course list", driver.findElement(By.linkText("Course list")).getText());
        assertEquals("Add Course", driver.findElement(By.linkText("Add Course")).getText());
        Thread.sleep(5000);
        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("user");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        Thread.sleep(5000);
        assertEquals("List", driver.findElement(By.linkText("List")).getText());
        assertEquals("Add", driver.findElement(By.linkText("Add")).getText());
        assertEquals("View", driver.findElement(By.linkText("View")).getText());
        assertEquals("Course list", driver.findElement(By.linkText("Course list")).getText());



    }

    @Test
    public void testCheckCourseInformation(){
        System.setProperty("webdriver.gecko.driver","/D:\\Users\\lenovo\\Git\\SystemTestAssignment\\src\\test\\resources\\geckodriver.exe");        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("953331", driver.findElement(By.xpath("//td[2]")).getText());
        assertEquals("953323", driver.findElement(By.xpath("//tr[2]/td[2]")).getText());
        assertEquals("953499", driver.findElement(By.xpath("//tr[3]/td[2]")).getText());
        assertEquals("CBSD", driver.findElement(By.xpath("//td[3]")).getText());
        assertEquals("Software Construction", driver.findElement(By.xpath("//tr[2]/td[3]")).getText());
        assertEquals("Software Project", driver.findElement(By.xpath("//tr[3]/td[3]")).getText());



    }

}



