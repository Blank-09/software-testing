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

    private final String BARNES_AND_NOBLE_URL = "https://www.barnesandnoble.com/";
    protected Logger logger = LogManager.getLogger();

    private WebDriver driver;
    private Wait<WebDriver> wait;
    private Actions actions;
    private ExtentReports reports;

    private String searchValue;

    @BeforeTest
    public void setupDriver() {
        logger.info("Setting up driver...");

        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);

        logger.info("Maximizing window...");
        driver.manage().window().maximize();
        logger.info("Driver setup complete.");
    }

    @BeforeTest
    public void setupExcel() throws Exception {
        logger.info("Setting up Excel...");

        Workbook workbook = new XSSFWorkbook("./sheets/data.xlsx");
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);

        this.searchValue = cell.getStringCellValue();
        workbook.close();
        logger.info("Excel setup complete.");
    }

    @BeforeTest
    public void setupReport() {
        logger.info("Setting up report...");

        ExtentSparkReporter spark = new ExtentSparkReporter("./src/report/index.html");
        spark.config().setDocumentTitle("Barnes & noble Testing Report");
        this.reports = new ExtentReports();
        this.reports.attachReporter(spark);

        logger.info("Report setup complete.");
    }

    /**
     * Testcase 1
     */

    @Test(priority = 1)
    public void testSearchBar() throws Exception {
        logger.info("Starting testSearchBar...");

        // Step 1
        logger.info("Navigating to " + BARNES_AND_NOBLE_URL);
        driver.get(BARNES_AND_NOBLE_URL);

        // Step 2
        logger.info("Click on the “All” dropdown on the left side of the search bar and select “Books”.");

        WebElement dropdown = wait.until(d -> d.findElement(By.className("rhf-search-options")));
        dropdown.click();

        WebElement bookLink = wait.until(d -> d.findElement(By.linkText("Books")));
        bookLink.click();

        // Step 3
        logger.info("On the search bar input the value \"" + searchValue + "\".");

        WebElement searchBar = wait.until(d -> d.findElement(By.className("rbt-input-main")));
        searchBar.sendKeys(searchValue);

        // Step 4
        logger.info("Click on the search button.");
        WebElement searchButton = wait.until(d -> d.findElement(By.className("rhf-search-btn")));
        searchButton.click();

        // Step 5
        logger.info("Verify the search results contain the keyword \"" + searchValue + "\" in it.");
        WebElement keywords = wait.until(d -> d.findElement(By.className("terms")));

        ExtentTest searchTest = reports.createTest("Verify Result ");

        if (keywords.getText().equals(searchValue)) {
            searchTest.log(Status.PASS, "The keyword '" + searchValue + "' is found");
            logger.info("The keyword '" + searchValue + "' is found");
        } else {
            searchTest.log(Status.FAIL, "The keyword '" + searchValue + "' is not found");
            logger.error("The keyword '" + searchValue + "' is not found");
        }

        takeScreenshot("./src/screenshot/search");
        logger.info("testSearchBar complete.");
    }

    /**
     * Testcase 2
     */

    @Test(priority = 2)
    public void testAddToCart() throws Exception {
        logger.info("Starting testAddToCart...");

        // Step 1
        logger.info("Navigating to " + BARNES_AND_NOBLE_URL);
        driver.get(BARNES_AND_NOBLE_URL);

        // Step 2
        logger.info("Hover over the Audiobooks. Under the Bestsellers section, click on the URL 'Audiobooks Top 100'.");
        WebElement audioBooks = wait.until(d -> d.findElement(By.linkText("Audiobooks")));
        actions.moveToElement(audioBooks).perform();

        WebElement top100AudioBooks = wait.until(d -> d.findElement(By.linkText("Audiobooks Top 100")));
        top100AudioBooks.click();

        // Step 3
        logger.info("Scroll down and click the 'ADD TO CART' button.");
        WebElement addToCart = wait.until(d -> d.findElement(By.xpath(
                "/html/body/main/div[2]/div[1]/div/div[2]/div/div[2]/section[2]/ol/li[1]/div/div[2]/div[5]/div[2]/div/div/form/input[11]")));
        actions.scrollByAmount(0, 300);
        Thread.sleep(5000);
        addToCart.click();

        // Step 4
        logger.info("Verify the pop-up displays the label 'Item Successfully Added To Your Cart'.");

        WebElement successLabel;
        ExtentTest verifySuccess = reports.createTest("Verify Add to Cart");

        try {
            successLabel = wait.until(d -> d.findElement(By.cssSelector(".modal .item-add-msg")));
        } catch (Exception e) {
            verifySuccess.log(Status.FAIL, "Item failed to be Added To Your Cart");
            logger.error("Item failed to be Added To Your Cart. Because the modal did not appear.");
            return;
        }

        if (successLabel.getText().equals("Item Successfully Added To Your Cart")) {
            verifySuccess.log(Status.PASS, "Item Successfully Added To Your Cart");
            logger.info("Item Successfully Added To Your Cart");
        } else {
            verifySuccess.log(Status.FAIL, "Item failed to be Added To Your Cart");
            logger.error("Item failed to be Added To Your Cart");
        }

        takeScreenshot("./src/screenshot/modal");
        logger.info("testAddToCart complete.");
    }

    @AfterTest
    public void wrapUp() {
        logger.info("Wrapping up...");
        driver.quit();
        reports.flush();
        logger.info("Wrap up complete.");
    }

    private void takeScreenshot(String filename) throws IOException {
        logger.info("Taking screenshot of " + filename);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        FileUtils.copyFile(screenshotFile, new File(filename + "_" + timestamp + ".png"));
        logger.info("Screenshot taken.");
    }
}
