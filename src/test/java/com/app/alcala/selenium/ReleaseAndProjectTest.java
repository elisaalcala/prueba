package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class ReleaseAndProjectTest {

    private static WebDriver driver;
    
    @BeforeAll
    public static void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
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
    
    public String test1_CreateRelease() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
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
        
        wait.until(ExpectedConditions.urlMatches(Pattern.compile("https://localhost:8443/releases/\\d+").toString()));

        String currentUrl = driver.getCurrentUrl();

        Pattern pattern = Pattern.compile("https://localhost:8443/releases/(\\d+)");
        Matcher matcher = pattern.matcher(currentUrl);

        String releaseId = null;
        
        if (matcher.find()) {
            releaseId = matcher.group(1); 
            System.out.println("El ID de la release creado es: " + releaseId);
        } else {
            System.out.println("No se encontró el ID de la releases en la URL.");
        }

        assertTrue(currentUrl.matches("https://localhost:8443/releases/\\d+"), "La URL de redirección no es la esperada: " + currentUrl);

        System.out.println("El último número de la URL es: " + releaseId);
        
        return releaseId;
    }

    
    public void test2_EditRelease(String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editButton")));
        editButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editModalRelease")));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate newProDate = LocalDate.of(2024, 3, 1);

        WebElement proDateField = driver.findElement(By.id("editProDate"));
        proDateField.clear();
        proDateField.sendKeys(newProDate.format(formatter));

        WebElement updateButton = driver.findElement(By.id("updateReleaseButton"));
        updateButton.click();
        
        driver.get("https://localhost:8443/releases/"+ id);
        WebElement proDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("proDate")));
        assertEquals("01/03/2024", proDate.getText());
    
    }
    
    public String test3_CreateProject(String id) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        WebElement createProjectButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("createProjectButton")));
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
        projectTeamField.sendKeys("Development");

        WebElement createButton = driver.findElement(By.id("saveProjectButton"));
        createButton.click();
        
        
        wait.until(ExpectedConditions.urlMatches(Pattern.compile("https://localhost:8443/projects/\\d+").toString()));

        String currentUrl = driver.getCurrentUrl();

        Pattern pattern = Pattern.compile("https://localhost:8443/projects/(\\d+)");
        Matcher matcher = pattern.matcher(currentUrl);

        String projectId = null;
        
        if (matcher.find()) {
        	projectId = matcher.group(1); 
            System.out.println("El ID de project creado es: " + projectId);
        } else {
            System.out.println("No se encontró el ID de project en la URL.");
        }

        assertTrue(currentUrl.matches("https://localhost:8443/projects/\\d+"), "La URL de redirección no es la esperada: " + currentUrl);

        System.out.println("El último número de la URL es: " + projectId);
        
        return projectId;

    }
    
    public void test4_AssignProject() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        assertEquals("johndoe", assignedEmployee.getText(), "El proyecto no se ha asignado correctamente");
    }

    public void test5_StatusProject() {
        WebElement changeStatusButton = driver.findElement(By.id("changeStatusButtonProject"));
        changeStatusButton.click();

        WebElement newStatusOption = driver.findElement(By.id("Closed")); 
        newStatusOption.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("ticketStatus"), "Closed"));
        WebElement statusDisplay = driver.findElement(By.id("ticketStatus"));
        assertEquals("Closed", statusDisplay.getText());
    }

    public void test6_EditProject() {
        WebElement editButton = driver.findElement(By.id("editButton"));
        editButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement editModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editModalProject")));
        assertTrue(editModal.isDisplayed());

        WebElement descriptionField = driver.findElement(By.id("editProjectDescription"));
        descriptionField.clear();
        descriptionField.sendKeys("Nueva descripción del proyecto");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButtonProject"));
        saveChangesButton.click();
        
        wait.until(ExpectedConditions.attributeToBe(By.id("descripcionProject"), "value", "Nueva descripción del proyecto"));
        WebElement updatedDescriptionField = driver.findElement(By.id("descripcionProject")); 
        assertEquals("Nueva descripción del proyecto", updatedDescriptionField.getAttribute("value"));
    }

    public void test7_DeleteProject(String id) {
        driver.get("https://localhost:8443/projects/" + id);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
        
        deleteButton.click();

        WebElement deleteConfirmationModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteModalProject")));
        assertTrue(deleteConfirmationModal.isDisplayed());

        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteTicketButtonProject")));
        confirmDeleteButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteModalProject")));

        assertFalse(driver.getPageSource().contains("Test Project Title")); 
    }
    public void test8_DeleteRelease(String id) {
    	
        driver.get("https://localhost:8443/releases/" + id);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
        
        deleteButton.click();

        WebElement deleteConfirmationModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteModalRelease")));
        assertTrue(deleteConfirmationModal.isDisplayed());

        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteTicketButtonRelease")));
        confirmDeleteButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteModalRelease")));

    }
    
    @Test
    public void test_release_and_project() {
    	String id = test1_CreateRelease();
//    	String idProject = test3_CreateProject(id);
//    	test4_AssignProject();
//    	test5_StatusProject();
//    	test6_EditProject();
//    	test7_DeleteProject(idProject);
    	test8_DeleteRelease(id);
    }
}

