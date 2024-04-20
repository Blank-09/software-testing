package com.priyanshu.infosysspringboardautomation;

import static org.fusesource.jansi.Ansi.ansi;

import java.time.Duration;
import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.priyanshu.infosysspringboardautomation.automation.InfosysAutomate;
import com.priyanshu.infosysspringboardautomation.brave.BraveDriver;
import com.priyanshu.infosysspringboardautomation.brave.BraveOptions;
import com.priyanshu.infosysspringboardautomation.utils.Color;

public class InfosysSpringboardAutomationApplication {

	private static final String INFOSYS_SPRINGBOARD_LOGIN_URL = "https://infyspringboard.onwingspan.com/web/en/login";

	public static void main(String[] args) throws Exception {
		AnsiConsole.systemInstall();
		Scanner sc = new Scanner(System.in);

		System.out.println("\n" + ansi().fgBlack().bgBrightGreen().a("  Infosys Springboard Automation  \n").reset());

		// get the course url
		System.out.println(Color.question("Which course would you like to automate? (URL)"));
		String courseUrl = sc.nextLine(); // Sample URL: https://infyspringboard.onwingspan.com/web/en/app/toc/lex_auth_0130944324885217282357_shared/overview
		System.out.println();
		sc.close();

		// Configuring Brave Browser
		BraveOptions options = new BraveOptions();
		options.addArguments("--app=" + INFOSYS_SPRINGBOARD_LOGIN_URL); // Open the URL in app mode
		options.addArguments("--start-maximized"); // Starts Chrome in maximized mode

		// Create a new instance of the BraveDriver
		WebDriver driver = new BraveDriver(options);
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(45)); // Increase the timeout if the internet is slow
		
		// Navigate to Infosys Springboard login page
		System.out.println(Color.info("Navigating to " + INFOSYS_SPRINGBOARD_LOGIN_URL));
		driver.get(INFOSYS_SPRINGBOARD_LOGIN_URL);

		InfosysAutomate infosys = new InfosysAutomate(driver, wait);
		infosys.login();

		System.out.println(Color.info("Navigating to " + courseUrl));
		driver.get(courseUrl);

		// Click the Start Button
		By startBtnSelector = By.className("start-btn");
		WebElement startBtn = wait.until(d -> d.findElement(startBtnSelector));
		startBtn.click();

		System.out.println(Color.info("Starting the course"));

		// Selectors
		By nextBtnSelector = By.className("navigation-btn-frwd");
		By videoSelector   = By.cssSelector("video.vjs-tech");

		System.out.println(Color.info("Waiting for the course to start...\n"));

		// Loop ends when the course is completed by throwing an exception
		// Or if the content is not a video
		// Or when the user stops the program
		while (true) {
			wait.until(d -> d.findElement(videoSelector));
			Thread.sleep(3000);

			// Get the title of the video
			String title = driver.getTitle().split(" - ")[0];
			System.out.println(Color.info("Title: " + title));
			System.out.println(Color.info("  - Video Started"));

			// play the video
			WebElement video = driver.findElement(videoSelector);
			((JavascriptExecutor) driver).executeScript("arguments[0].play()", video);

			System.out.println(Color.info("  - Watching video"));
			Thread.sleep(3000);

			// Skip the video
			((JavascriptExecutor) driver).executeScript("arguments[0].currentTime = arguments[0].duration - 1", video);
			System.out.println("\n" + Color.success("Video Completed\n"));
			Thread.sleep(3000);

			// click the next button
			driver.findElement(nextBtnSelector).click();
		}
	}

}
