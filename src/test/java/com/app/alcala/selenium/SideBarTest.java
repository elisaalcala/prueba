package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
public class SideBarTest {

    private static WebDriver driver;


    @BeforeAll
    public static void setUp() {
        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); 
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
       
        driver = new ChromeDriver(options);
        driver.get("https://localhost:8443/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("details-button")));
        WebElement detailsButtonField = driver.findElement(By.id("details-button"));
        detailsButtonField.click();
        WebElement proceedLinkField = driver.findElement(By.id("proceed-link"));
        proceedLinkField.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("johndoe");
        passwordField.sendKeys("pass");
        loginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSideBarNavigation() {
        navigateAndVerify("dailywork-link", "/dailywork");
        navigateAndVerify("mywork-link", "/mywork");
        navigateAndVerify("releases-link", "/releases");
        navigateAndVerify("projects-link", "/projects");
        navigateAndVerify("tickets-link", "/tickets");
    }

    private void navigateAndVerify(String linkId, String expectedUrlSuffix) {
        WebElement link = driver.findElement(By.id(linkId));
        link.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains(expectedUrlSuffix));
        assertTrue(driver.getCurrentUrl().endsWith(expectedUrlSuffix));
    }


}
