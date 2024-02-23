package tests.loginTests.negativeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeTests {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String LOCKED_OUT_ERROR_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";
    private static final String NO_MATCH_CREDENTIALS_ERROR_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
    private static final String EMPTY_PASSWORD_ERROR_MESSAGE = "Epic sadface: Password is required";
    private static final String EMPTY_USERNAME_ERROR_MESSAGE = "Epic sadface: Username is required";


    @Test(testName = "Validate scenario with locked_out_user")
    public void loginWithLockedOutUser() {
        WebDriver driver = new ChromeDriver();
        driver.get(BASE_URL);

        login(driver, "locked_out_user", "secret_sauce");

        validateErrorMessage(driver, LOCKED_OUT_ERROR_MESSAGE);

        driver.close();
        driver.quit();
    }
    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "incorrect_password", NO_MATCH_CREDENTIALS_ERROR_MESSAGE},
                {"incorrect_username", "secret_sauce", NO_MATCH_CREDENTIALS_ERROR_MESSAGE},
                {"incorrect_username", "incorrect_password", NO_MATCH_CREDENTIALS_ERROR_MESSAGE},
                {"", "secret_sauce", EMPTY_USERNAME_ERROR_MESSAGE},
                {"standard_user", "", EMPTY_PASSWORD_ERROR_MESSAGE},
                {"", "", EMPTY_USERNAME_ERROR_MESSAGE}
        };
    }
    @Test(dataProvider = "loginData", testName = "Validate login scenarios")
    public void loginTest(String username, String password, String errorMessage) {
        WebDriver driver = new ChromeDriver();
        driver.get(BASE_URL);

        login(driver, username, password);

        validateErrorMessage(driver, errorMessage);

        driver.close();
        driver.quit();
    }

    private static void login(WebDriver driver, String username, String password) {
        driver.findElement(By.cssSelector("[data-test=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[data-test=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("[data-test=\"login-button\"]")).click();
    }

    private static void validateErrorMessage(WebDriver driver, String expectedErrorMessage) {
        String actualErrorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is incorrect");
    }
}