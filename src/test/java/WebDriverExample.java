import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverExample {
    private WebDriver driver = new ChromeDriver();

    @Before
    public void runDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/artem/IdeaProjects/chromedriver");
        driver.navigate().to("http://automationpractice.com/index.php");
    }

    @Test
    public void sighIn() {
        runDriver();
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys("qaqasqa1231@qa.qa");
        driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).sendKeys("FirstName");
        driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).sendKeys("LastName");
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("Password123456789");
        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Firstname");
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Lastname");
        driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys("myAddress, 123");
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("CityFake");
        driver.findElement(By.xpath("//*[@id=\"postcode\"]")).sendKeys("12345");
        driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]")).sendKeys("+380800000000");
        driver.findElement(By.xpath("//*[@id=\"alias\"]")).sendKeys("FakeAlias");
        driver.findElement(By.xpath("//*[@id=\"submitAccount\"]")).click();
    }

    @After
    public void finishDriver() {
        String actualMsg = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[@class='alert alert-danger']")).getAttribute("innerHTML");
        String expectedError = "This country requires you to choose a State.";
        if (actualMsg.contains(expectedError)) {
            driver.close();
            System.out.println("Test passed");
        } else {
            System.out.println(actualMsg);
        }
    }
}