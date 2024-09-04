package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.alcala.AlcalaApplication;
import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.User;
import com.app.alcala.repositories.UserRepository;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.service.TeamService;

@SpringBootTest(classes = AlcalaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

	@LocalServerPort
	int port;

	private WebDriver driver;
	private WebDriverWait wait;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TeamService teamService;

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
	public void testLoginWithValidCredentials() {

		userRepository.save(new User("test", passwordEncoder.encode("test"), "USER"));
		Employee employeeTest = new Employee();
		employeeTest.setUserEmployee("test");
		employeeService.save(employeeTest);
		Team teamTest = new Team();
		teamTest.setNameTeam("teamTest");
		Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();
		teamTest.setEmployeeMap(employeeMap);
		teamService.save(teamTest);
		teamTest.getEmployeeMap().put(employeeTest.getEmployeeId(), employeeTest);
		employeeTest.setNameTeam("teamTest");
		employeeTest.setTeam(teamTest);
		employeeService.save(employeeTest);
		teamService.save(teamTest);

		driver.get("https://localhost:" + this.port + "/login");
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.sendKeys("test");
		passwordField.sendKeys("test");
		loginButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));

		assertTrue(driver.getCurrentUrl().endsWith("/dailywork"));

		Optional<User> user = userRepository.findByName("test");
		if (user != null) {
			Employee employeeDelete = employeeService.findByUserEmployee(user.get().getName());
			if (employeeDelete != null) {
				Team teamDelete = teamService.findByNameTeam(employeeDelete.getNameTeam());

				if (teamDelete != null) {
					teamDelete.getEmployeeMap().remove(employeeDelete.getEmployeeId());
					teamService.save(teamDelete);
				}
				employeeDelete.setTeam(null);
				employeeService.save(employeeDelete);
				
				if (teamTest != null) {
					teamService.delete(teamDelete);
				}
				employeeService.delete(employeeDelete);
			}
			userRepository.delete(user.get());
		}

	}

	@Test
	public void testLoginWithInvalidCredentials() {

		driver.get("https://localhost:" + this.port + "/login");
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.sendKeys("invalidUsername");
		passwordField.sendKeys("invalidPassword");
		loginButton.click();

		assertTrue(driver.getCurrentUrl().endsWith("/login"));

	}
}
