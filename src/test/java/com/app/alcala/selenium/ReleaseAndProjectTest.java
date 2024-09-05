package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
public class ReleaseAndProjectTest {
    
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
	options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void testRelaseAndProjectActions() {
    	
    	userRepository.save(new User("testReleaseAndProject", passwordEncoder.encode("test"), "USER"));
		Employee employeeTest = new Employee();
		employeeTest.setUserEmployee("testReleaseAndProject");
		employeeService.save(employeeTest);
		Team teamTest = new Team();
		teamTest.setNameTeam("teamTestReleaseAndProject");
		Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();
		teamTest.setEmployeeMap(employeeMap);
		teamService.save(teamTest);
		teamTest.getEmployeeMap().put(employeeTest.getEmployeeId(), employeeTest);
		employeeTest.setNameTeam("teamTestReleaseAndProject");
		employeeTest.setTeam(teamTest);
		employeeService.save(employeeTest);
		teamService.save(teamTest);
    	
    	
        driver.get("https://localhost:"+this.port+"/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("testReleaseAndProject");
        passwordField.sendKeys("test");
        loginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));

        WebElement button = driver.findElement(By.cssSelector("button[data-bs-target='#createModalRelease']"));
        button.click();

        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createModalRelease")));
        assertTrue(modal.isDisplayed(), "Modal did not open on button click");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate initialDate = LocalDate.of(2023, 6, 1);
        LocalDate requirementsDate = LocalDate.of(2023, 6, 10);
        LocalDate assignmentDate = LocalDate.of(2023, 6, 15);
        LocalDate developDate = LocalDate.of(2023, 7, 1);
        LocalDate tstDate = LocalDate.of(2023, 7, 15);
        LocalDate uatDate = LocalDate.of(2023, 7, 20);
        LocalDate proDate = LocalDate.of(2023, 8, 1);

        driver.findElement(By.id("createInitialDate")).sendKeys(initialDate.format(formatter));
        driver.findElement(By.id("createRequirementsDate")).sendKeys(requirementsDate.format(formatter));
        driver.findElement(By.id("createPrjAssignmentDate")).sendKeys(assignmentDate.format(formatter));
        driver.findElement(By.id("createDevelopDate")).sendKeys(developDate.format(formatter));
        driver.findElement(By.id("createTstDate")).sendKeys(tstDate.format(formatter));
        driver.findElement(By.id("createUatDate")).sendKeys(uatDate.format(formatter));
        driver.findElement(By.id("createProDate")).sendKeys(proDate.format(formatter));

        WebElement saveButton = driver.findElement(By.id("saveReleaseButton"));
        saveButton.click();

        wait.until(ExpectedConditions.urlMatches(Pattern.compile("https://localhost:" + this.port + "/releases/(\\d+)").toString()));

        String currentUrl = driver.getCurrentUrl();

        Pattern pattern = Pattern.compile("https://localhost:" + this.port + "/releases/(\\d+)");
        Matcher matcher = pattern.matcher(currentUrl);

        String releaseId = null;

        if (matcher.find()) {
            releaseId = matcher.group(1);
            System.out.println("El ID de la release creado es: " + releaseId);
        } else {
            System.out.println("No se encontró el ID de la releases en la URL.");
        }

        assertTrue(currentUrl.matches("https://localhost:" + this.port + "/releases/\\d+"),
                "La URL de redirección no es la esperada: " + currentUrl);

        System.out.println("El último número de la URL es: " + releaseId);

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editButton")));
        editButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editModalRelease")));

        LocalDate newProDate = LocalDate.of(2024, 3, 1);

        WebElement proDateField = driver.findElement(By.id("editProDate"));
        proDateField.clear();
        proDateField.sendKeys(newProDate.format(formatter));

        WebElement updateButton = driver.findElement(By.id("updateReleaseButton"));
        updateButton.click();
        
        wait.until(ExpectedConditions.textToBe(By.id("proDate"), "01/03/2024"));
        WebElement proDateEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("proDate")));
        assertEquals("01/03/2024", proDateEdit.getText());

        WebElement createProjectButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.id("createProjectButton")));
        createProjectButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createModalProject")));

        WebElement projectTitleField = driver.findElement(By.id("createProjectTitle"));
        projectTitleField.sendKeys("Título del Nuevo Proyecto");

        WebElement projectDescriptionField = driver.findElement(By.id("createProjectDescription"));
        projectDescriptionField.sendKeys("Descripción del Nuevo Proyecto");

        WebElement projectTypeField = driver.findElement(By.id("createProjectType"));
        projectTypeField.sendKeys("Alto");

        WebElement projectPriorityField = driver.findElement(By.id("createProjectPriority"));
        projectPriorityField.sendKeys("Alta");

        WebElement projectEnvironmentField = driver.findElement(By.id("createProjectEnvironment"));
        projectEnvironmentField.sendKeys("TST");

        WebElement projectTeamField = driver.findElement(By.id("createProjectTeamNameAssign"));
        projectTeamField.sendKeys("teamTestReleaseAndProject");

        WebElement createButton = driver.findElement(By.id("saveProjectButton"));
        createButton.click();

        wait.until(ExpectedConditions.urlMatches(Pattern.compile("https://localhost:" + this.port + "/projects/(\\d+)").toString()));

        String currentUrl2 = driver.getCurrentUrl();

        Pattern pattern2 = Pattern.compile("https://localhost:" + this.port + "/projects/(\\d+)");
        Matcher matcher2 = pattern2.matcher(currentUrl2);

        String projectId = null;

        if (matcher2.find()) {
            projectId = matcher2.group(1);
            System.out.println("El ID de project creado es: " + projectId);
        } else {
            System.out.println("No se encontró el ID de project en la URL.");
        }

        assertTrue(currentUrl2.matches("https://localhost:" + this.port + "/projects/\\d+"),
                "La URL de redirección no es la esperada: " + currentUrl2);

        System.out.println("El último número de la URL es: " + projectId);

        WebElement assignButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("assignButton")));
        assignButton.click();

        WebElement assignModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("assignModalProject")));
        assertTrue(assignModal.isDisplayed(), "El modal de asignación no se ha abierto");

        WebElement assignToMeButton = driver.findElement(By.id("assignToMeButtonProject"));
        assignToMeButton.click();

        WebElement saveAssignButton = driver.findElement(By.id("saveAssignButtonProject"));
        saveAssignButton.click();

        wait.until(ExpectedConditions.invisibilityOf(assignModal));

        WebElement assignedEmployee = driver.findElement(By.id("employeeUserAssign"));
        assertEquals("testReleaseAndProject", assignedEmployee.getText(), "El proyecto no se ha asignado correctamente");

        WebElement changeStatusButton = driver.findElement(By.id("changeStatusButtonProject"));
        changeStatusButton.click();

        WebElement newStatusOption = driver.findElement(By.id("Closed"));
        newStatusOption.click();
        
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("ticketStatus"), "Closed"));
        WebElement statusDisplay = driver.findElement(By.id("ticketStatus"));
        assertEquals("Closed", statusDisplay.getText());

        WebElement editButtonProject = driver.findElement(By.id("editButton"));
        editButtonProject.click();

        WebElement editModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editModalProject")));
        assertTrue(editModal.isDisplayed());

        WebElement descriptionField = driver.findElement(By.id("editProjectDescription"));
        descriptionField.clear();
        descriptionField.sendKeys("Nueva descripción del proyecto");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButtonProject"));
        saveChangesButton.click();

        driver.get("https://localhost:" + this.port + "/projects/" + projectId);
        
//        wait.until(ExpectedConditions.attributeToBe(By.id("descripcionProject"), "value",
//                "Nueva descripción del proyecto"));
//        WebElement updatedDescriptionField = driver.findElement(By.id("descripcionProject"));
//        assertEquals("Nueva descripción del proyecto", updatedDescriptionField.getAttribute("value"));

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));

        deleteButton.click();

        WebElement deleteConfirmationModal = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteModalProject")));
        assertTrue(deleteConfirmationModal.isDisplayed());

        WebElement confirmDeleteButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.id("deleteTicketButtonProject")));
        confirmDeleteButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteModalProject")));

        assertFalse(driver.getPageSource().contains("Test Project Title"));

        driver.get("https://localhost:" + this.port + "/releases/" + releaseId);

        WebElement deleteButtonRelease = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
        deleteButtonRelease.click();

        WebElement deleteConfirmationModalRelease = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteModalRelease")));
        assertTrue(deleteConfirmationModalRelease.isDisplayed());

        WebElement confirmDeleteButtonRelease = wait
                .until(ExpectedConditions.elementToBeClickable(By.id("deleteTicketButtonRelease")));
        confirmDeleteButtonRelease.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteModalRelease")));

		Optional<User> user = userRepository.findByName("testReleaseAndProject");
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

}
