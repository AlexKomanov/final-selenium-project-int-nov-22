package tests.sanityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SanityScenario {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String PRODUCTS_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private static final String CHECKOUT_PAGE_URL = "https://www.saucedemo.com/cart.html";
    private static final String CHECKOUT_STEP_ONE_PAGE_URL = "https://www.saucedemo.com/checkout-step-one.html";
    private static final String CHECKOUT_OVERVIEW_PAGE_URL = "https://www.saucedemo.com/checkout-step-two.html";
    private static final String CHECKOUT_COMPLETE_PAGE_URL = "https://www.saucedemo.com/checkout-complete.html";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(BASE_URL);

        login(driver);
        validateUrl(driver, PRODUCTS_PAGE_URL);
        validateTitle(driver, "Products");

        addToCartTwoItems(driver);
        clickOnCart(driver);
        validateUrl(driver, CHECKOUT_PAGE_URL);
        validateTitle(driver, "Your Cart");
        validateCartItems(driver, 2);

        clickOnCheckout(driver);
        validateUrl(driver, CHECKOUT_STEP_ONE_PAGE_URL);
        validateTitle(driver, "Checkout: Your Information");

        fillCheckoutForm(driver);
        clickOnContinue(driver);
        validateUrl(driver, CHECKOUT_OVERVIEW_PAGE_URL);
        validateTitle(driver, "Checkout: Overview");

        finishCheckout(driver);
        validateUrl(driver, CHECKOUT_COMPLETE_PAGE_URL);
        validateTitle(driver, "Checkout: Complete!");
        validateSuccessMessage(driver, "Thank you for your order!");
        validateSuccessBody(driver, "Your order has been dispatched, and will arrive just as fast as the pony can get there!");

        driver.close();
        driver.quit();
    }

    private static void login(WebDriver driver) {
        driver.findElement(By.cssSelector("[data-test=\"username\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test=\"login-button\"]")).click();
    }

    private static void addToCartTwoItems(WebDriver driver) {
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']")).click();
    }

    private static void clickOnCart(WebDriver driver) {
        WebElement shoppingCartBadge = driver.findElement(By.cssSelector("[class='shopping_cart_badge']"));
        shoppingCartBadge.click();
    }

    private static void fillCheckoutForm(WebDriver driver) {
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
    }
    private static void clickOnContinue(WebDriver driver) {
    driver.findElement(By.id("continue")).click();
    }
    private static void clickOnCheckout(WebDriver driver) {
        driver.findElement(By.id("checkout")).click();
    }
    private static void finishCheckout(WebDriver driver) {
        driver.findElement(By.id("finish")).click();
    }

    private static void validateUrl(WebDriver driver, String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL is incorrect");
    }

    private static void validateTitle(WebDriver driver, String expectedTitle) {
        String title = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span")).getText();
        Assert.assertEquals(title, expectedTitle, "Title is incorrect");
    }

    private static void validateCartItems(WebDriver driver, int expectedNumberOfItems) {
        WebElement shoppingCartBadge = driver.findElement(By.cssSelector("[class='shopping_cart_badge']"));
        int actualNumberOfItems = Integer.parseInt(shoppingCartBadge.getText());
        Assert.assertEquals(actualNumberOfItems, expectedNumberOfItems, "Number of Items is different.");
    }

    private static void validateSuccessMessage(WebDriver driver, String expectedMessage) {
        String successMessage = driver.findElement(By.cssSelector("#checkout_complete_container > h2")).getText();
        Assert.assertEquals(successMessage, expectedMessage, "Success message is incorrect");
    }

    private static void validateSuccessBody(WebDriver driver, String expectedBody) {
        String successBody = driver.findElement(By.cssSelector("#checkout_complete_container > div")).getText();
        Assert.assertEquals(successBody, expectedBody, "Success Body is incorrect");
    }
}