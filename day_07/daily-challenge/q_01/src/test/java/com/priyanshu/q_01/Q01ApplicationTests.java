package com.priyanshu.q_01;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

class Q01ApplicationTests {

    WebDriver driver;
    Actions actions;
    Wait<WebDriver> wait;

    @BeforeTest
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        this.driver = new ChromeDriver(options);
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testEconomicTimes() throws Exception {
        driver.get("https://www.ixigo.com/");

        // Step 1
        WebElement roundTripButton = wait.until(
                d -> d.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[1]/div[1]/div/button[2]")));
        roundTripButton.click();

        // Step 2
        WebElement inputFromDiv = wait.until(
                d -> d.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div")));
        inputFromDiv.click();

        WebElement inputFrom = wait.until(d -> d.findElement(
                By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input")));
        inputFrom.sendKeys("IDP");
        Thread.sleep(2000);

        // Step 3
        By firstOptionSelector = By.className("Autocompleter_animate__zqRDe");
        wait.until(d -> ExpectedConditions.visibilityOfElementLocated(firstOptionSelector));
        WebElement firstOption = driver
                .findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/li"));
        firstOption.click();
        Thread.sleep(2000);

        // Step 4
        WebElement inputToDiv = wait.until(
                d -> d.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div")));
        inputToDiv.click();

        // Step 5
        WebElement inputTo = wait.until(d -> d.findElement(
                By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input")));
        inputTo.sendKeys("DEL");

        // Step 5
        By firstOptionToSelector = By.className("Autocompleter_animate__zqRDe");
        wait.until(d -> ExpectedConditions.visibilityOfElementLocated(firstOptionToSelector));

        Thread.sleep(3000);

        WebElement firstToOption = wait.until(d -> d
                .findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[3]/div[1]/li")));
        firstToOption.click();

        // Step 6
        WebElement departure = wait.until(d -> d
                .findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div")));
        departure.click();

        Thread.sleep(5000);

        // Step 7
        WebElement departureInput = wait.until(d -> d.findElement(By.cssSelector("[aria-label=\"1 May 2024\"]")));
        ((JavascriptExecutor) driver).executeScript("return arguments[0].parentElement.click();", departureInput);

        Thread.sleep(5000);

        // Step 8
        WebElement returnInput = wait.until(d -> d.findElement(By.cssSelector("[aria-label=\"10 May 2024\"]")));
        ((JavascriptExecutor) driver).executeScript("return arguments[0].parentElement.click();", returnInput);

        // Step 9
        WebElement travelClass = wait
                .until(d -> d.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[1]/div")));
        travelClass.click();

        Thread.sleep(5000);

        // Step 10
        // Increment Adult by 1
        driver.findElement(By.xpath(
                "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/button[2]"))
                .click();

        // Increment Child by 1
        driver.findElement(By.xpath(
                "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/button[2]"))
                .click();

        // Select Business option
        driver.findElement(By.xpath(
                "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[5]/div/div[3]"))
                .click();

        // Click on the Done button
        driver.findElement(By.xpath(
                "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[2]/button"))
                .click();

        Thread.sleep(5000);

        // Step 11
        WebElement submit = wait
                .until(d -> d.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/button")));
        submit.click();

        Thread.sleep(10_000);

    }

    @AfterTest
    public void wrapUp() {
        driver.quit();
    }

}
