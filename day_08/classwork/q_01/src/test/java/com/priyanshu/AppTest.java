package com.priyanshu;

import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

    String DBANK_DEMO_URL = "http://dbankdemo.com/bank/login";

    WebDriver driver;
    Actions actions;
    Wait<WebDriver> wait;

    String username, password;

    @BeforeTest
    public void setupDriver() {
        this.driver = new ChromeDriver();
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @BeforeTest
    public void setupExcel() throws Exception {
        Workbook workbook = new XSSFWorkbook("credentials.xlsx");
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);

        this.username = row.getCell(0).toString();
        this.password = row.getCell(1).toString();

        workbook.close();
    }

    /**
     * Testcase 1
     */

    @Test
    public void dBankDemoTestingLogin() {
        // Step 1
        driver.get(DBANK_DEMO_URL);

        // Step 2, 3 & 4
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();

        // Step 5
        if (driver.getCurrentUrl().contains("home")) {
            System.out.println("Login Success");
        } else {
            System.out.println("Login Success");
        }
    }

    /**
     * Testcase 2
     */

    @Test
    public void dBankDemoTestingDeposit() {
        // Step 1
        driver.get(DBANK_DEMO_URL);

        // Step 2, 3 & 4
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();

        // Step 5
        driver.findElement(By.linkText("Deposit")).click();

        WebElement selectedAccount = driver.findElement(By.id("selectedAccount"));
        Select select = new Select(selectedAccount);
        select.selectByIndex(2);

        // Step 6
        WebElement amountInput = driver.findElement(By.id("amount"));
        amountInput.sendKeys("5000");

        // Step 7
        By submitButtonSelector = By.cssSelector(".card-footer button[type=submit]");
        WebElement submitButton = driver.findElement(submitButtonSelector);
        submitButton.click();

        // Step 8
        WebElement amountCell = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[4]"));
        String amount = amountCell.getText();
        
        actions.scrollToElement(amountCell).perform();

        if (amount.equals("$5000.00")) {
            System.out.println("Done");
        } else {
            System.out.println("Failed");
        }
    }

    
    /**
     * Testcase 3
     */

    @Test
    public void dBankDemoTestingWithdraw() {
        // Step 1
        driver.get(DBANK_DEMO_URL);

        // Step 2, 3 & 4
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();

        // Step 5
        driver.findElement(By.linkText("Withdraw")).click();

        // Step 6
        WebElement selectedAccount = driver.findElement(By.id("selectedAccount"));
        Select select = new Select(selectedAccount);
        select.selectByIndex(2);

        WebElement amountInput = driver.findElement(By.id("amount"));
        amountInput.sendKeys("3000");

        // Step 7
        WebElement amountCell = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[4]"));
        String amount = amountCell.getText();

        actions.scrollToElement(amountCell).perform();

        if (amount.equals("$-3000.00")) {
            System.out.println("Done");
        } else {
            System.out.println("Failed");
        }
    }
 

    @AfterTest
    public void wrapUp() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }
}
