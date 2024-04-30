package com.priyanshu.q_01;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

// import java.net.MalformedURLException;
// import java.net.URL;
// import java.time.Duration;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.events.EventFiringDecorator;
// import org.openqa.selenium.support.events.WebDriverListener;
// import org.testng.annotations.AfterMethod;
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.Test;
// import jdk.internal.org.jline.utils.Log;
// import java.util.List;
// import java.util.Set;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.support.ui.Select;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import utils.EventHandler;

public class TestEconomic {
	// public static WebDriver driver;
	// private WebDriverWait wait;
	// public final int IMPLICIT_WAIT_TIME=10;
	// public final int PAGE_LOAD_TIME=10;

	// @BeforeMethod
	// public void beforeMethod() throws MalformedURLException {
	// ChromeOptions chromeOptions = new ChromeOptions();
	// driver = new RemoteWebDriver(new URL("http://34.85.201.58:4481"),
	// chromeOptions);
	// driver.manage().window().maximize();
	// wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	// driver.get("https://economictimes.indiatimes.com/et-now/results");
	// wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	// WebDriverListener listener = new EventHandler();
	// driver = new EventFiringDecorator<>(listener).decorate(driver);
	// System.out.println("Browser");
	// }

	// @Test
	// public void economic()
	// {
	// //Enter your automation scripts here
	// }

	// @AfterMethod
	// public void afterMethod() {
	// driver.quit();
	// }

	// WebDriver driver;
	// Actions actions;

	// @BeforeTest
	// public void beforeTest() {
	// driver = new ChromeDriver();
	// actions = new Actions(driver);
	// }

	// @Test
	// public void testingEconomicTimes() throws Exception {
	// driver.get("https://economictimes.indiatimes.com/et-now/results");
	// Thread.sleep(10000);

	// // move to mutual funds
	// driver.findElement(By.xpath("//nav[@id='topnav']/div[@data-ga-action='Mutual
	// Funds']/a")).click();
	// Thread.sleep(10000);

	// WebElement select = driver.findElement(By.id("amcSelection"));
	// actions.scrollToElement(select).perform();

	// // select.click();
	// //
	// driver.findElement(By.xpath("//select[@id='amcSelection']/option[@value='8']")).click();

	// Select selectEle = new Select(select);
	// selectEle.selectByVisibleText("Canara Robeco");
	// Thread.sleep(3000);

	// select = driver.findElement(By.id("schemenm"));

	// // select.click();
	// //
	// driver.findElement(By.xpath("//select[@id='schemenm']/option[@value='15765']")).click();

	// selectEle = new Select(select);
	// selectEle.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
	// Thread.sleep(3000);

	// driver.findElement(By.xpath("//*[@id='getDetails']")).click();
	// Thread.sleep(5000);

	// // the above actions will create a new tab
	// String current_tab = driver.getWindowHandle(); // current tab
	// for (String s : driver.getWindowHandles()) {
	// if (!s.equals(current_tab)) {
	// // switches to newly opened tab
	// driver.switchTo().window(s);
	// }
	// }

	// // set amount as 1000
	// driver.findElement(By.xpath("//*[@id='installment_amt']/li/span")).click();
	// driver.findElement(By.xpath("//*[@id='installment_amt']/li/ul/li[3]")).click();

	// // set period as 3 years
	// driver.findElement(By.xpath("//*[@id='installment_period']/li/span")).click();
	// driver.findElement(By.xpath("//*[@id='installment_period']/li/ul/li[4]/span")).click();

	// // navigate to returns
	// driver.findElement(By.xpath("//*[@id=\"mfNav\"]/div/ul/li[2]")).click();
	// WebElement details = driver
	// .findElement(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table/tbody/tr[1]"));

	// System.out.println(details.getText());
	// }

	// @AfterTest
	// public void afterTest() {
	// driver.quit();
	// }

}
