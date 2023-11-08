package tests.sanityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SanityScenario {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.saucedemo.com/";
        driver.get(baseUrl);

        String actualUrl = driver.getCurrentUrl();

        if (baseUrl.equals(actualUrl)) {
            System.out.println("URL is correct");
        }
        else {
            System.out.println("URL is incorrect");
        }

        driver.findElement(By.cssSelector("[data-test=\"username\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test=\"login-button\"]")).click();


        String productsPageUrl = "https://www.saucedemo.com/inventory.html";
        actualUrl = driver.getCurrentUrl();

        if (productsPageUrl.equals(actualUrl)) {
            System.out.println("URL is correct");
        }

        else {
            System.out.println("URL is not correct");
        }


        String title = driver.findElement(By.cssSelector("[class=\"title\"]")).getText();
        String expectedTitle = "Products";

        if (title.equals(expectedTitle)) {
            System.out.println("title is correct");
        }

        else {
            System.out.println("title is not correct");
        }

        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']")).click();

        WebElement shoppingCartBadge = driver.findElement(By.cssSelector("[class='shopping_cart_badge']"));

        /*  Optional validation
        String actualNumberOfItems = shoppingCartBadge.getText();
        String expectedNumberOfItems = "2";
*/

        int actualNumberOfItems = Integer.parseInt(shoppingCartBadge.getText());
        int expectedNumberOfItems = 2;

        Assert.assertEquals(actualNumberOfItems, expectedNumberOfItems, "Number of Items is different.");


        shoppingCartBadge.click();


        driver.close();
        driver.quit();

    }
}
