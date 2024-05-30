package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SideBarTest {

    private static WebDriver driver;


    @BeforeAll
    public static void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://localhost:8443/login");

        WebElement detailsButtonField = driver.findElement(By.id("details-button"));
        detailsButtonField.click();
        WebElement proceedLinkField = driver.findElement(By.id("proceed-link"));
        proceedLinkField.click();
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("johndoe");
        passwordField.sendKeys("pass");
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
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
    
    @Test
    public void testModalsOpenOnButtonClick() {
        checkModalOpensOnButtonClick(By.cssSelector("button[data-bs-target='#createModal']"), By.id("createModal"));
        checkModalOpensOnButtonClick(By.cssSelector("button[data-bs-target='#createModalRelease']"), By.id("createModalRelease"));
    }

    private void checkModalOpensOnButtonClick(By buttonSelector, By modalSelector) {
        WebElement button = driver.findElement(buttonSelector);
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalSelector));

        assertTrue(modal.isDisplayed(), "Modal did not open on button click");
        
        WebElement closeButton = modal.findElement(By.cssSelector(".btn-close"));
        closeButton.click();
        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalSelector));
    }

}
