package tests.loginTests.positiveTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginWithStandardUserTest {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String PRODUCTS_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @DataProvider(name = "userCredentials")
    public static Object[][] userCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"visual_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
        };
    }

    @Test(dataProvider = "userCredentials")
    public void loginTest(String username, String password) {
        WebDriver driver = new ChromeDriver();
        driver.get(BASE_URL);

        validateUrl(driver, BASE_URL);

        login(driver, username, password);

        validateUrl(driver, PRODUCTS_PAGE_URL);
        validateTitle(driver, "Products");

        driver.close();
        driver.quit();
    }

    private static void login(WebDriver driver, String username, String password) {
        driver.findElement(By.cssSelector("[data-test=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[data-test=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("[data-test=\"login-button\"]")).click();
    }

    private static void validateUrl(WebDriver driver, String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL is incorrect");
    }

    private static void validateTitle(WebDriver driver, String expectedTitle) {
        String title = driver.findElement(By.cssSelector("[class=\"title\"]")).getText();
        Assert.assertEquals(title, expectedTitle, "Title is incorrect");
    }
}