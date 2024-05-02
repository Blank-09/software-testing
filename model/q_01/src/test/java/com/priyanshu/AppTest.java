package com.priyanshu;

import java.time.Duration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

    private final String FIRST_CRY_URL = "https://www.firstcry.com/";

    WebDriver driver;
    Actions actions;
    Wait<WebDriver> wait;
    Workbook workbook;

    @BeforeTest
    public void setupDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        this.driver = new ChromeDriver(options);
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @BeforeTest
    public void setupWorkbook() throws Exception {
        this.workbook = new XSSFWorkbook("./assets/sheets/firstcry.xlsx");
    }

    /**
     * Test Case 1: Verify the search bar functionality
     */

    @Test(priority = 1)
    public void testSearchBar() {
        // Step 1
        driver.get(FIRST_CRY_URL);

        // Step 2
        WebElement search = driver.findElement(By.id("search_box"));
        search.click();

        // Step 3
        String searchText = workbook.getSheetAt(0).getRow(1).getCell(0).getStringCellValue();
        search.sendKeys(searchText + "\n");

        // Step 4
        String url = driver.getCurrentUrl();

        if (url.contains("kids-toys")) {
            System.out.println("Search Successful");
        } else {
            System.out.println("Search Failure");
        }
    }

    /**
     * Test Case 2: Verify the FILTER BY functionality
     */

    @Test(priority = 2)
    public void testFilterBy() throws Exception {
        // Step 1
        driver.get(FIRST_CRY_URL);

        // Step 2
        WebElement search = driver.findElement(By.id("search_box"));
        search.click();

        // Step 3
        String searchText = workbook.getSheetAt(0).getRow(2).getCell(0).getStringCellValue();
        search.sendKeys(searchText + "\n");

        // Step 4
        WebElement filter = wait.until(d -> d.findElement(
                By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filter);
        Thread.sleep(3000);

        // Step 5
        String keyword = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();

        if (keyword.contains("Ethnic Wear")) {
            System.out.println("Filter By Successful");
        } else {
            System.out.println("Filter By Failure");
        }

    }

    /**
     * Test Case 3: Verify the Product functionality
     */

    @Test(priority = 3)
    public void testProduct() {
        // Step 1
        driver.get(FIRST_CRY_URL);

        // Step 2
        WebElement search = driver.findElement(By.id("search_box"));
        search.click();

        // Step 3
        String searchText = workbook.getSheetAt(0).getRow(3).getCell(0).getStringCellValue();
        search.sendKeys(searchText + "\n");

        // Step 4
        WebElement product = wait.until(d -> d.findElement(
                By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[1]/div[2]/a")));
        String productName = product.getText();

        // Step 5
        product.click();

        // switch to new tab
        String currentTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
            }
        }

        String productName2 = wait.until(d -> d.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[7]/div[3]/h1"))).getText();

        if (productName.equals(productName2)) {
            System.out.println("Product Found");
        } else {
            System.out.println("Product Not Found");
        }

    }

    @AfterTest
    public void wrapup() throws Exception {
        driver.quit();
        workbook.close();
    }
}
