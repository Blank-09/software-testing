package com.priyanshu.q_01;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Q01Application {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		// WebDriver edgeDriver = new EdgeDriver();
		// WebDriver fireFoxDriver = new FirefoxDriver();
		// WebDriver ieDriver = new InternetExplorerDriver();

		driver.get("https://www.shoppersstop.com/");

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(d -> d.findElement(By.id("profileImage")));

		driver.findElement(By.id("profileImage")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.quit();
	}

}
