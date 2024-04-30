package com.priyanshu.automation;

import java.time.Duration;

import org.openqa.selenium.By;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.priyanshu.automation.secret.Secret;

// @SpringBootApplication
public class AutomationApplication {

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(AutomationApplication.class, args);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--app=https://skcet530.examly.io/login");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();

		// driver.get("https://skcet530.examly.io/login");
		System.out.println(driver.getTitle());
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

		// Login
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(d -> d.findElement(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("727722euit137@skcet.ac.in\n");

		wait.until(d -> d.findElement(By.id("password")));
		driver.findElement(By.id("password")).sendKeys(Secret.password + "\n");

		// wait for the page to load
		wait.until(d -> d.findElement(By.id("mycsearch")));

		// driver.get("https://skcet530.examly.io/mycourses/details?id=11f165cc-f8d3-4466-9d2d-a743208faefd&type=mylabs");

		// wait.until(d -> d.findElement(By.id("pannel0108")));
		// driver.findElement(By.id("pannel0108")).click();

		driver.get(
				"https://skcet530.examly.io/test?id=96681a4a-dd0b-4a92-8ad9-bd1a53ef5becf02b364f-aaa4-4500-9bfb-e6e4ff00e064");

		wait.until(d -> d.findElement(By.id("tt-start-confirm")));
		driver.findElement(By.id("tt-start-confirm")).click();

		wait.until(d -> d.findElement(By.id("tt-start-accept")));
		driver.findElement(By.id("tt-start-accept")).click();

		Wait<WebDriver> waitlong = new WebDriverWait(driver, Duration.ofSeconds(60));
		waitlong.until(d -> d.findElement(By.className("ace_text-input")));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		driver.findElement(By.className("ace_text-input")).sendKeys("""
#include <iostream>
#include <vector>

using namespace std;

int main() { \nint n;
cin >> n;

vector<int> arr(n, 0);
for (int i = 0; i < n; i++) { \ncin >> arr[i];
cout << arr[i] << ' ';
}

cout << endl;

bool isOdd = false, isEven = false;
for (int i = 0; i < n; i++) { \nif (arr[i] % 2 == 1) isOdd = true;
else isEven = true;
}

if (isOdd && isEven) { \ncout << \"Mixed\";
} else if (isOdd) { \ncout << \"Odd\";
} else { \ncout << \"Even\";
}
}""");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("programme-compile")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// check if the button is not disabled
		wait.until(d -> !d.findElement(By.id("programme-compile")).getAttribute("disabled").equals("true"));
		driver.findElement(By.id("tt-footer-submit-answer")).click();

		// driver.quit();
	}

}
