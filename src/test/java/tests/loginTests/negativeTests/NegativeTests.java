package tests.loginTests.negativeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test(testName = "Validate scenario with locked_out_user")
    public void loginWithLockerUser() {
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.saucedemo.com/";
        driver.get(baseUrl);


        driver.findElement(By.cssSelector("[data-test=\"username\"]")).sendKeys("locked_out_user");
        driver.findElement(By.cssSelector("[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test=\"login-button\"]")).click();

        String actualErrorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        driver.close();
        driver.quit();
    }

}
