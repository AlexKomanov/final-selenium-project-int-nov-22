package tests.loginTests.positiveTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.utils.TestData;
import tests.utils.TestUtils;

public class LoginWithStandardUserTest {
    @Test(dataProvider = "userCredentials", dataProviderClass = TestData.class, testName = "Validate User Credentials")
    public void loginTest(String username, String password) {
        WebDriver driver = new ChromeDriver();

        driver.get(TestUtils.BASE_URL);
        validateUrl(driver, TestUtils.BASE_URL);
        login(driver, username, password);
        validateUrl(driver, TestUtils.PRODUCTS_PAGE_URL);
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