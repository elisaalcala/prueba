package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
    	WebDriverManager.edgedriver().setup();
    	EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless"); 
        options.addArguments("--disable-gpu");
       
        driver = new EdgeDriver(options);
        driver.get("https://localhost:8443/login");
        WebElement detailsButtonField = driver.findElement(By.id("details-button"));
        detailsButtonField.click();
        WebElement proceedLinkField = driver.findElement(By.id("proceed-link"));
        proceedLinkField.click();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginWithValidCredentials() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("johndoe"); 
        passwordField.sendKeys("pass"); 
        loginButton.click();
        
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));
        
        assertTrue(driver.getCurrentUrl().endsWith("/dailywork"));
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("invalidUsername");
        passwordField.sendKeys("invalidPassword");
        loginButton.click();

        assertTrue(driver.getCurrentUrl().endsWith("/login"));
        
        
    }
}
