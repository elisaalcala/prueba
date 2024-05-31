package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.app.alcala.AlcalaApplication;

@SpringBootTest(classes = AlcalaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SideBarTest {

	@LocalServerPort
	int port;

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	public void setUpTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--allow-insecure-localhost");
		options.addArguments("--disable-gpu");

		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void setUp() {

		driver.get("https://localhost:" + this.port + "/login");
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.sendKeys("johndoe");
		passwordField.sendKeys("pass");
		loginButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));

		assertTrue(driver.getCurrentUrl().endsWith("/dailywork"));
		
		navigateAndVerify("dailywork-link", "/dailywork");
		navigateAndVerify("mywork-link", "/mywork");
		navigateAndVerify("releases-link", "/releases");
		navigateAndVerify("projects-link", "/projects");
		navigateAndVerify("tickets-link", "/tickets");
	}

	private void navigateAndVerify(String linkId, String expectedUrlSuffix) {
		WebElement link = driver.findElement(By.id(linkId));
		link.click();
		wait.until(ExpectedConditions.urlContains(expectedUrlSuffix));
		assertTrue(driver.getCurrentUrl().endsWith(expectedUrlSuffix));
	}

}
