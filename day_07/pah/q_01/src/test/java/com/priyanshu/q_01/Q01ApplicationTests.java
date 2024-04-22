package com.priyanshu.q_01;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
        this.driver = new ChromeDriver();
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testEconomicTimes() throws Exception {
        // Step 1
        driver.get("https://groww.in/");

        // Step 2
        WebElement footer = wait.until(d -> d.findElement(By.className("fm77ProductsLinks")));
        actions.scrollToElement(footer).perform();

        // Step 3
        WebElement calculatorLink = wait.until(d -> d.findElement(By.linkText("Calculators")));
        calculatorLink.click();

        // Step 4
        wait.until(d -> d.getCurrentUrl().equals("https://groww.in/calculators"));

        // Step 5
        WebElement homeLoanEMI = wait.until(d -> d.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/a[15]")));
        actions.scrollToElement(homeLoanEMI);
        homeLoanEMI.click();

        // Step 6
        WebElement loanAmount = wait.until(d -> d.findElement(By.id("LOAN_AMOUNT")));
        loanAmount.clear();
        loanAmount.sendKeys("2300000");

        // Step 7
        WebElement rateOfInterest = driver.findElement(By.id("RATE_OF_INTEREST"));
        rateOfInterest.clear();
        rateOfInterest.sendKeys("6.5");

        // Step 8
        WebElement loanTenure = driver.findElement(By.id("LOAN_TENURE"));
        loanTenure.clear();
        loanTenure.sendKeys("25");

        // Step 9
        WebElement result = driver.findElement(By.className("tb10Table"));
        System.out.println(result.getText());

    }

    @AfterTest
    public void wrapUp() {
        driver.quit();
    }

}
