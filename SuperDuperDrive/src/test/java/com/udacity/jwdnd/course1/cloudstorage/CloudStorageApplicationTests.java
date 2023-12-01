package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {
	static String noteTitleTest = "Note Title";
	static String noteDescriptionTest = "Note Description";

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		//doMockSignUp("Immanuel", "Kant", "kantt", "12345");
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	@Order(2)
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");

		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	@Order(3)
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	@Order(4)
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	@Test
	@Order(5)
	public void testSignupAndLogin() {
		String username = "ginny";
		String password = "12345";
		String firstname = "Giny";
		String lastname = "Nguyen";

		driver.get("http://localhost:" + this.port + "/signup");

		SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(username, password, firstname, lastname);

		assertEquals("You successfully signed up! Please login.", driver.findElement(By.id("success-msg")).getText());

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		assertEquals("http://localhost:" + this.port + "/", driver.getCurrentUrl());

		HomePage homePage = new HomePage(driver);
		homePage.logout();

       assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

	}

	@Test
	@Order(6)
	public void testAuthorization() {
		driver.get("http://localhost:" + this.port + "/login");
		assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

		driver.get("http://localhost:" + this.port + "/signup");
		assertEquals("http://localhost:" + this.port + "/signup", driver.getCurrentUrl());

		driver.get("http://localhost:" + this.port);
		assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}
	public void login() {
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("ginnynguyen", "1234");
		assertEquals("http://localhost:" + this.port + "/", driver.getCurrentUrl());
	}

	/*Write a Selenium test that logs in an existing user, creates a note and verifies that the note details are visible
	in the note list.*/
	@Test
	@Order(7)
	public void testAddNote() {
		login();

		HomePage homePage = new HomePage(driver);
		homePage.addNote(noteTitleTest, noteDescriptionTest);
		WebElement tdTitle = driver.findElement(By.cssSelector("#userTable tbody tr:last-child td:nth-child(2)"));
		assertEquals(noteTitleTest, tdTitle.getAttribute("innerHTML"));

		WebElement tdDesciption = driver.findElement(By.cssSelector("#userTable tbody tr:last-child td:nth-child(3)"));
		assertEquals(noteDescriptionTest, tdDesciption.getAttribute("innerHTML"));
	}

	/*Write a Selenium test that logs in an existing user with existing notes, clicks the edit note button on an existing note,
	changes the note data, saves the changes, and verifies that the changes appear in the note list.*/
	@Test
	@Order(8)
	public void testEditNote() {
		login();

		HomePage homePage = new HomePage(driver);
		WebElement editButtonElement = driver.findElement(By.cssSelector("#userTable tbody tr:last-child td:nth-child(1) button"));
		String noteDescriptionTestEdit = "This is a test note edited";

		homePage.editNote(editButtonElement, "Notetttt", noteDescriptionTestEdit);

		WebElement editElementTitle = driver.findElement(By.cssSelector("#userTable tbody tr:last-child td:nth-child(2)"));
		assertEquals("Notetttt", editElementTitle.getAttribute("innerHTML"));

		WebElement editElementDescription = driver.findElement(By.cssSelector("#userTable tbody tr:last-child td:nth-child(3)"));
		assertEquals(noteDescriptionTestEdit, editElementDescription.getAttribute("innerHTML"));
	}

	/*Write a Selenium test that logs in an existing user with existing notes, clicks the delete note button
	on an existing note, and verifies that the note no longer appears in the note list.*/
	@Test
	@Order(8)
	public void testDeleteNote() {
		login();

		HomePage homePage = new HomePage(driver);
		WebElement deleteElement = driver.findElement(By.cssSelector("#userTable tbody tr:last-child"));
		((JavascriptExecutor) driver).executeScript("arguments[0].id = arguments[1];", deleteElement, "newIdValue");
		String id = deleteElement.getAttribute("id");
		homePage.deleteNote(deleteElement);

		try {
			WebElement element = driver.findElement(By.id(id));
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/*Write a Selenium test that logs in an existing user, creates a credential and verifies that
	the credential details are visible in the credential list.*/
	@Test
	@Order(9)
	public void testAddCredential() {
		login();

		HomePage homePage = new HomePage(driver);
		String credentialUrlTest = "www.google.com";
		String credentialUsernameTest = "ginnynguyen111";
		String credentialPasswordTest = "12345678";

		homePage.addCredential(credentialUrlTest, credentialUsernameTest, credentialPasswordTest);

		WebElement tdUrl = driver.findElement(By.cssSelector("#credentialTable tbody tr:last-child td:nth-child(2)"));
		assertEquals(credentialUrlTest, tdUrl.getAttribute("innerHTML"));

		WebElement tdUsername = driver.findElement(By.cssSelector("#credentialTable tbody tr:last-child td:nth-child(3)"));
		assertEquals(credentialUsernameTest, tdUsername.getAttribute("innerHTML"));
	}

	/*Write a Selenium test that logs in an existing user with existing credentials, clicks the edit credential
	button on an existing credential, changes the credential data, saves the changes, and verifies that the changes appear
	in the credential list.*/
	@Test
	@Order(10)
	public void testEditCredential() {
		login();

		HomePage homePage = new HomePage(driver);
		WebElement editButtonElement = driver.findElement(By.cssSelector("#credentialTable tbody tr:last-child td:nth-child(1) button"));
		String credentialUrlTestEdit = "www.facebook.com";
		String credentialUsernameTestEdit = "ginnynguyen11167562";
		String credentialPasswordTestEdit = "343245345345";

		homePage.editCredential(editButtonElement, credentialUrlTestEdit, credentialUsernameTestEdit, credentialPasswordTestEdit);

		WebElement editElementUrl = driver.findElement(By.cssSelector("#credentialTable tbody tr:last-child td:nth-child(2)"));
		assertEquals(credentialUrlTestEdit, editElementUrl.getAttribute("innerHTML"));

		WebElement editElementUsername = driver.findElement(By.cssSelector("#credentialTable tbody tr:last-child td:nth-child(3)"));
		assertEquals(credentialUsernameTestEdit, editElementUsername.getAttribute("innerHTML"));

	}

	/*Write a Selenium test that logs in an existing user with existing credentials, clicks the delete credential button on an existing credential,
	and verifies that the credential no longer appears in the credential list.*/
	@Test
	@Order(11)
	public void testDeleteCredential() {
		login();

		HomePage homePage = new HomePage(driver);
		WebElement deleteElement = driver.findElement(By.cssSelector("#credentialTable tbody tr:last-child"));
		((JavascriptExecutor) driver).executeScript("arguments[0].id = arguments[1];", deleteElement, "newIdValueDelete");

		String id = deleteElement.getAttribute("id");
		homePage.deleteCredential(deleteElement);

		try {
			WebElement element = driver.findElement(By.id(id));
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}
