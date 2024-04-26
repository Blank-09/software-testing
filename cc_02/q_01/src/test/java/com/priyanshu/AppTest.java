package com.priyanshu;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AppTest {

    final String BARNES_AND_NOBLE_URL = "https://www.barnesandnoble.com/";
    Logger logger = LogManager.getLogger(getClass());

    WebDriver driver;
    Wait<WebDriver> wait;
    Actions actions;
    ExtentReports reports;

    String searchValue;

    @BeforeTest
    public void setupDriver() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);

        driver.manage().window().maximize();
    }

    @BeforeTest
    public void setupExcel() throws Exception {
        Workbook workbook = new XSSFWorkbook("./sheets/data.xlsx");
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);
        
        this.searchValue = cell.getStringCellValue();
        workbook.close();
    }

    @BeforeTest
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("./src/report/index.html");
        spark.config().setDocumentTitle("Barnes & noble Testing Report");
        this.reports = new ExtentReports();
        this.reports.attachReporter(spark);
    }

    /**
     * Testcase 1
     */

    @Test
    public void testSearchBar() throws Exception {
        // Step 1
        driver.get(BARNES_AND_NOBLE_URL);

        // Step 2
        WebElement dropdown = wait.until(d -> d.findElement(By.className("rhf-search-options")));
        dropdown.click();

        WebElement bookLink = wait.until(d -> d.findElement(By.linkText("Books")));
        bookLink.click();

        // Step 3
        WebElement searchBar = wait.until(d -> d.findElement(By.className("rbt-input-main")));
        searchBar.sendKeys(searchValue);

        // Step 4
        WebElement searchButton = wait.until(d -> d.findElement(By.className("rhf-search-btn")));
        searchButton.click();

        // Step 5
        WebElement keywords = wait.until(d -> d.findElement(By.className("terms")));

        ExtentTest searchTest = reports.createTest("Verify Result ");

        if (keywords.getText().equals(searchValue)) {
            searchTest.log(Status.PASS, "The keyword 'Chetan Bhagat' is found");
        } else {
            searchTest.log(Status.FAIL, "The keyword 'Chetan Bhagat' is not found");
        }

        takeScreenshot("./src/screenshot/search");
    }

    /**
     * Testcase 2
     */

    @Test
    public void testAddToCart() throws Exception {
        // Step 1
        driver.get(BARNES_AND_NOBLE_URL);

        // Step 2
        WebElement audioBooks = wait.until(d -> d.findElement(By.linkText("Audiobooks")));
        actions.moveToElement(audioBooks).perform();

        WebElement top100AudioBooks = wait.until(d -> d.findElement(By.linkText("Audiobooks Top 100")));
        top100AudioBooks.click();

        // Step 3
        WebElement addToCart = wait.until(d -> d.findElement(By.xpath(
                "/html/body/main/div[2]/div[1]/div/div[2]/div/div[2]/section[2]/ol/li[1]/div/div[2]/div[5]/div[2]/div/div/form/input[11]")));
        actions.scrollByAmount(0, 300);
        Thread.sleep(5000);
        addToCart.click();

        // Step 4
        WebElement successLabel = wait.until(d -> d.findElement(By.cssSelector(".modal .item-add-msg")));

        ExtentTest verifySuccess = reports.createTest("Verify Add to Cart");

        if (successLabel.getText().equals("Item Successfully Added To Your Cart")) {
            verifySuccess.log(Status.PASS, "Item Successfully Added To Your Cart");
        } else {
            verifySuccess.log(Status.FAIL, "Item failed to be Added To Your Cart");
        }

        takeScreenshot("./src/screenshot/modal");
    }

    @AfterTest
    public void wrapUp() {
        driver.quit();
        reports.flush();
    }

    private void takeScreenshot(String filename) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        FileUtils.copyFile(screenshotFile, new File(filename + "_" + timestamp + ".png"));
    }
}
