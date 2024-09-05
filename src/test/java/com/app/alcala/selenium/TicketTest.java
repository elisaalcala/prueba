package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class TicketTest {
    
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void testTicketActions() {
    	

		userRepository.save(new User("testTicket", passwordEncoder.encode("test"), "USER"));
		Employee employeeTest = new Employee();
		employeeTest.setUserEmployee("testTicket");
		employeeService.save(employeeTest);
		Team teamTest = new Team();
		teamTest.setNameTeam("teamTestTicket");
		Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();
		teamTest.setEmployeeMap(employeeMap);
		teamService.save(teamTest);
		teamTest.getEmployeeMap().put(employeeTest.getEmployeeId(), employeeTest);
		employeeTest.setNameTeam("teamTestTicket");
		employeeTest.setTeam(teamTest);
		employeeService.save(employeeTest);
		teamService.save(teamTest);
		
		
		userRepository.save(new User("testAssign", passwordEncoder.encode("test"), "USER"));
		Employee employeeTestAssign = new Employee();
		employeeTestAssign.setUserEmployee("testAssign");
		employeeService.save(employeeTestAssign);
		Team teamTestAssign = new Team();
		teamTestAssign.setNameTeam("teamTestAssign");
		Map<Long, Employee> employeeMapAssign = new HashMap<Long, Employee>();
		teamTestAssign.setEmployeeMap(employeeMapAssign);
		teamService.save(teamTestAssign);
		teamTestAssign.getEmployeeMap().put(employeeTestAssign.getEmployeeId(), employeeTestAssign);
		employeeTestAssign.setNameTeam("teamTestAssign");
		employeeTestAssign.setTeam(teamTestAssign);
		employeeService.save(employeeTestAssign);
		teamService.save(teamTestAssign);
		
		
    	driver.get("https://localhost:" + this.port + "/login");
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.sendKeys("testTicket");
		passwordField.sendKeys("test");
		loginButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));

		assertTrue(driver.getCurrentUrl().endsWith("/dailywork"));
        
        WebElement button = driver.findElement(By.cssSelector("button[data-bs-target='#createModal']"));
        button.click();

        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createModal")));
        assertTrue(modal.isDisplayed(), "Modal did not open on button click");
        
        WebElement titleField = driver.findElement(By.id("createTicketTitle"));
        WebElement descriptionField = driver.findElement(By.id("createTicketDescription"));
        WebElement priorityField = driver.findElement(By.id("createTicketPriority"));
        WebElement environmentField = driver.findElement(By.id("createTicketEnvironment"));
        WebElement teamNameAssignField = driver.findElement(By.id("createTicketTeamNameAssign"));
        WebElement saveButton = driver.findElement(By.id("saveTicketButton"));

        assertTrue(titleField.isDisplayed());
        assertTrue(descriptionField.isDisplayed());
        assertTrue(priorityField.isDisplayed());
        assertTrue(environmentField.isDisplayed());
        assertTrue(teamNameAssignField.isDisplayed());
        assertTrue(saveButton.isDisplayed());

        titleField.sendKeys("Test Ticket Title");
        descriptionField.sendKeys("This is a description for the test ticket.");
        priorityField.sendKeys("Alta");
        environmentField.sendKeys("PRO");
        
        List<WebElement> teamOptions = teamNameAssignField.findElements(By.tagName("option"));
        for (WebElement option : teamOptions) {
            if (option.getText().equals("teamTestAssign")) {
                option.click();
                break;
            }
        }

        saveButton.click();
        
        wait.until(ExpectedConditions.urlMatches(Pattern.compile("https://localhost:" + this.port + "/tickets/(\\d+)").toString()));

        String currentUrl = driver.getCurrentUrl();

        Pattern pattern = Pattern.compile("https://localhost:" + this.port + "/tickets/(\\d+)");
        Matcher matcher = pattern.matcher(currentUrl);

        String ticketId = null;
        
        if (matcher.find()) {
            ticketId = matcher.group(1); 
            System.out.println("El ID del ticket creado es: " + ticketId);
        } else {
            System.out.println("No se encontró el ID del ticket en la URL.");
        }

        assertTrue(currentUrl.matches("https://localhost:" + this.port + "/tickets/\\d+"), "La URL de redirección no es la esperada: " + currentUrl);

        System.out.println("El último número de la URL es: " + ticketId);

    	
    	driver.get("https://localhost:" + this.port + "/login");
    	
		WebElement usernameField2 = driver.findElement(By.id("username"));
		WebElement passwordField2 = driver.findElement(By.id("password"));
		WebElement loginButton2 = driver.findElement(By.id("loginButton"));

        usernameField2.sendKeys("testAssign");
        passwordField2.sendKeys("test");
        loginButton2.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));
        
        driver.get("https://localhost:" + this.port + "/tickets/" + ticketId);

        WebElement assignButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("assignButton")));
        assignButton.click();
        
        WebElement assignModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("assignModal")));
        assertTrue(assignModal.isDisplayed(), "El modal de asignación no se ha abierto");

        WebElement assignToMeButton = driver.findElement(By.id("assignToMeButton"));
        assignToMeButton.click();

        WebElement saveAssignButton = driver.findElement(By.id("saveAssignButton"));
        saveAssignButton.click();

        wait.until(ExpectedConditions.invisibilityOf(assignModal));

        WebElement assignedEmployee = driver.findElement(By.id("employeeUserAssign"));
        assertEquals("testAssign", assignedEmployee.getText(), "El ticket no se ha asignado correctamente");

        WebElement changeStatusButton = driver.findElement(By.id("changeStatusButton"));
        changeStatusButton.click();

        WebElement newStatusOption = driver.findElement(By.id("Resolved"));
        newStatusOption.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("ticketStatus"), "Resolved"));
        WebElement statusDisplay = driver.findElement(By.id("ticketStatus"));
        assertEquals("Resolved", statusDisplay.getText());

        WebElement editButton = driver.findElement(By.id("editButton"));
        editButton.click();

        WebElement editModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editModal")));
        assertTrue(editModal.isDisplayed());

        WebElement descriptionFieldEdit = driver.findElement(By.id("editTicketDescription"));
        descriptionFieldEdit.clear();
        descriptionFieldEdit.sendKeys("Nueva descripción del ticket");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();
        
    	driver.get("https://localhost:" + this.port + "/tickets/" + ticketId);

//    	wait.until(ExpectedConditions.attributeToBe(By.id("descriptionTicket"), "value",
//                "Nueva descripción del ticket"));
//        WebElement updatedDescriptionField = driver.findElement(By.id("descriptionTicket"));
//        assertEquals("Nueva descripción del ticket", updatedDescriptionField.getAttribute("value"));

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
        
        deleteButton.click();

        WebElement deleteConfirmationModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteModal")));
        assertTrue(deleteConfirmationModal.isDisplayed());

        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteTicketButton")));
        confirmDeleteButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteModal")));

        assertFalse(driver.getPageSource().contains("Test Ticket Title"));

		Optional<User> user = userRepository.findByName("testTicket");
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
		
		Optional<User> userAssign = userRepository.findByName("testAssign");
		if (userAssign != null) {
			Employee employeeDeleteAssign = employeeService.findByUserEmployee(userAssign.get().getName());
			if (employeeDeleteAssign != null) {
				Team teamDeleteAssign = teamService.findByNameTeam(employeeDeleteAssign.getNameTeam());

				if (teamDeleteAssign != null) {
					teamDeleteAssign.getEmployeeMap().remove(employeeDeleteAssign.getEmployeeId());
					teamService.save(teamDeleteAssign);
				}
				employeeDeleteAssign.setTeam(null);
				employeeService.save(employeeDeleteAssign);
				
				if (teamTestAssign != null) {
					teamService.delete(teamDeleteAssign);
				}
				employeeService.delete(employeeDeleteAssign);
			}
			userRepository.delete(userAssign.get());
		}
		
		
    }

}

