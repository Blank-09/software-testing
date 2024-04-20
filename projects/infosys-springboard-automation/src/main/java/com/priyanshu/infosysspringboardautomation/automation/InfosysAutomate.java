package com.priyanshu.infosysspringboardautomation.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.priyanshu.infosysspringboardautomation.utils.Color;

public class InfosysAutomate {

    private WebDriver driver;
    private Wait<WebDriver> wait;

    public InfosysAutomate(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isLoggedIn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Check if the user is logged in by checking if the token is present and not expired
        Object obj = js.executeScript(
                "return Boolean(localStorage.kc) && Date.now() < JSON.parse(atob(JSON.parse(localStorage.kc).idToken.split('.')[1])).exp * 1000");
        return obj != null && (boolean) obj;
    }

    public void login() {

        if (isLoggedIn()) {
            System.out.println(Color.warning("Already logged in!"));
            return;
        }

        By loginBtnSelector = By.className("login-btn-top");
        WebElement loginBtn = wait.until(d -> d.findElement(loginBtnSelector));
        loginBtn.click();

        System.out.println(Color.info("Navigating to Login Page"));

        By googleBtnSelector = By.className("social-link");
        WebElement googleBtn = wait.until(d -> d.findElement(googleBtnSelector));
        googleBtn.click();

        System.out.println(Color.info("Logging in with Google..."));
        
        // You've to login with your Google account here by yourself
        // After logging in, you'll be redirected to the Infosys Springboard page
        new WebDriverWait(driver, Duration.ofSeconds(240))
                .until(d -> d.getCurrentUrl().startsWith("https://infyspringboard.onwingspan.com/web/en/page/home"));

        System.out.println(Color.info("Logged in successfully!"));
    }

}
