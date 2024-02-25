package tests.loginTests.negativeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.utils.TestUtils;
import tests.utils.TestData;

public class NegativeTests {
    @Test(testName = "Validate scenario with locked_out_user")
    public void loginWithLockedOutUser() {
        WebDriver driver = new ChromeDriver();

        driver.get(TestUtils.BASE_URL);
        login(driver, "locked_out_user", "secret_sauce");
        validateErrorMessage(driver, TestUtils.LOCKED_OUT_ERROR_MESSAGE);

        driver.close();
        driver.quit();
    }
    @Test(dataProvider = "loginData", dataProviderClass = TestData.class, testName = "Validate login scenarios")
    public void loginTest(String username, String password, String errorMessage) {
        WebDriver driver = new ChromeDriver();

        driver.get(TestUtils.BASE_URL);
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