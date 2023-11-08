package tests.loginTests.positiveTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginWithStandardUserTest {


    @Test
    public void loginWithStandardUser() {
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.saucedemo.com/";
        driver.get(baseUrl);

        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, baseUrl);

        driver.findElement(By.cssSelector("[data-test=\"username\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test=\"login-button\"]")).click();


        String productsPageUrl = "https://www.saucedemo.com/inventory.html";
        actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, productsPageUrl);


        String title = driver.findElement(By.cssSelector("[class=\"title\"]")).getText();
        String expectedTitle = "Products";

        Assert.assertEquals(title, expectedTitle);

        driver.close();
        driver.quit();

    }

    @Test
    public void loginWithVisualUser() {
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.saucedemo.com/";
        driver.get(baseUrl);

        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, baseUrl);

        driver.findElement(By.cssSelector("[data-test=\"username\"]")).sendKeys("visual_user");
        driver.findElement(By.cssSelector("[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test=\"login-button\"]")).click();


        String productsPageUrl = "https://www.saucedemo.com/inventory.html";
        actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, productsPageUrl);


        String title = driver.findElement(By.cssSelector("[class=\"title\"]")).getText();
        String expectedTitle = "Products";

        Assert.assertEquals(title, expectedTitle);

        driver.close();
        driver.quit();

    }
}
