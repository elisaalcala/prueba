package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
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
public class TicketTest {

    private static WebDriver driver;

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    public String test1_CreateTicket() {
    	
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

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("johndoe");
        passwordField.sendKeys("pass");
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));
        
        
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
            if (option.getText().equals("Operations")) {
                option.click();
                break;
            }
        }

        saveButton.click();
        
        wait.until(ExpectedConditions.urlMatches(Pattern.compile("https://localhost:8443/tickets/\\d+").toString()));

        String currentUrl = driver.getCurrentUrl();

        Pattern pattern = Pattern.compile("https://localhost:8443/tickets/(\\d+)");
        Matcher matcher = pattern.matcher(currentUrl);

        String ticketId = null;
        
        if (matcher.find()) {
            ticketId = matcher.group(1); 
            System.out.println("El ID del ticket creado es: " + ticketId);
        } else {
            System.out.println("No se encontró el ID del ticket en la URL.");
        }

        assertTrue(currentUrl.matches("https://localhost:8443/tickets/\\d+"), "La URL de redirección no es la esperada: " + currentUrl);

        System.out.println("El último número de la URL es: " + ticketId);
        
        return ticketId;
    }
    
    public void test2_FindTicket(String id) {
    	
        driver.get("https://localhost:8443/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("janedoe");
        passwordField.sendKeys("adminpass");
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));
        
        driver.get("https://localhost:8443/tickets/" + id);
        
    }
    
    
    public void test3_AssignTicket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        assertEquals("janedoe", assignedEmployee.getText(), "El ticket no se ha asignado correctamente");
        
    }

    public void test4_StatusTicket() {
        WebElement changeStatusButton = driver.findElement(By.id("changeStatusButton"));
        changeStatusButton.click();

        WebElement newStatusOption = driver.findElement(By.id("Resolved"));
        newStatusOption.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("ticketStatus"), "Resolved"));
        WebElement statusDisplay = driver.findElement(By.id("ticketStatus"));
        assertEquals("Resolved", statusDisplay.getText());
    }
    
    public void test5_EditTicket() {
        WebElement editButton = driver.findElement(By.id("editButton"));
        editButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement editModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editModal")));
        assertTrue(editModal.isDisplayed());

        WebElement descriptionField = driver.findElement(By.id("editTicketDescription"));
        descriptionField.clear();
        descriptionField.sendKeys("Nueva descripción del ticket");

        WebElement saveChangesButton = driver.findElement(By.id("saveChangesButton"));
        saveChangesButton.click();
        
        WebElement updatedDescriptionField = driver.findElement(By.id("editTicketDescription"));
        assertEquals("Nueva descripción del ticket", updatedDescriptionField.getAttribute("value"));
    
    }
    
    public void test6_DeleteTicket(String id) {
    	driver.get("https://localhost:8443/tickets/" + id);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
        
        deleteButton.click();

        WebElement deleteConfirmationModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteModal")));
        assertTrue(deleteConfirmationModal.isDisplayed());

        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteTicketButton")));
        confirmDeleteButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteModal")));

        assertFalse(driver.getPageSource().contains("Test Ticket Title"));


    }
    
    
    @Test
    public void test_ticket() {
    	String id = test1_CreateTicket();
    	test2_FindTicket(id);
    	test3_AssignTicket();
    	test4_StatusTicket();
    	test5_EditTicket();
    	test6_DeleteTicket(id);
    }
}

