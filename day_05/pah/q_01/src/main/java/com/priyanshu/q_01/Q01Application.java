package com.priyanshu.q_01;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Q01Application {

	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Actions actions = new Actions(driver);

		driver.get("https://magento.softwaretestingboard.com/");

		WebElement search = driver.findElement(By.id("search"));

		search.click();
		search.sendKeys("Shoes");

		driver.findElement(By.cssSelector("[type=submit]")).click();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./screenshots/image.png"));

		wait.until((d) ->
		d.findElement(By.cssSelector("[data-ui-id=\"page-title-wrapper\"]")));

		WebElement title =
		driver.findElement(By.cssSelector("[data-ui-id=\"page-title-wrapper\"]"));
		if (title.getText().contains("Shoes")) {
		System.out.println("It contains Shoes");
		} else {
		System.out.println("It does not contains Shoes");
		}

		wait.until((d) -> d.findElement(By.id("ui-id-5")));
		actions.moveToElement(driver.findElement(By.id("ui-id-5"))).perform();

		wait.until((d) -> d.findElement(By.id("ui-id-17")));
		actions.moveToElement(driver.findElement(By.id("ui-id-17"))).perform();

		wait.until((d) -> d.findElement(By.id("ui-id-20")));
		driver.findElement(By.id("ui-id-20")).click();

		driver.get("https://magento.softwaretestingboard.com/");
		WebElement btnTees = driver.findElement(By.className("home-t-shirts"));
		actions.scrollToElement(btnTees);
		btnTees.click();

	}

}
