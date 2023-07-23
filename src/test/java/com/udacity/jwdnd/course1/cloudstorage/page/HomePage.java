package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver webDriver;
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void addNote(String noteTitle, String noteDescription) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
        this.navNotesTab.click();

        WebElement element0 = wait.until(ExpectedConditions.elementToBeClickable(By.id("openAddNoteModel")));
        element0.click();

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ById.id("note-title")));
        element.sendKeys(noteTitle);

        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ById.id("note-description")));
        element2.sendKeys(noteDescription);

        WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("note-save-changes")));
        element3.click();
    }


    public void editNote(WebElement editElement, String nodeTitleEdit, String noteDescriptionEdit) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
        this.navNotesTab.click();

        WebElement element0 = wait.until(ExpectedConditions.elementToBeClickable(editElement));
        element0.click();

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ById.id("note-title")));
        element.clear();
        element.sendKeys(nodeTitleEdit);

        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ById.id("note-description")));
        element2.clear();
        element2.sendKeys(noteDescriptionEdit);

        WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("note-save-changes")));
        element3.click();
    }

    public void deleteNote(WebElement deleteElement) {
        WebElement deleteButotn = deleteElement.findElement(By.cssSelector("td:nth-child(1) a.btn-danger"));

        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
        this.navNotesTab.click();

        WebElement element0 = wait.until(ExpectedConditions.elementToBeClickable(deleteButotn));
        element0.click();

    }

    public void addCredential(String credentialUrlTest, String credentialUsernameTest, String credentialPasswordTest) {
        this.navCredentialsTab.click();

        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("openAddCredentialModal")));
        element1.click();

        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        element2.sendKeys(credentialUrlTest);

        WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
        element3.sendKeys(credentialUsernameTest);

        WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
        element4.sendKeys(credentialPasswordTest);

        WebElement element5 = wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-save-changes")));
        element5.click();
    }

    public void editCredential(WebElement editButtonElement, String credentialUrlTestEdit, String credentialUsernameTestEdit, String credentialPasswordTestEdit) {
        this.navCredentialsTab.click();

        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(editButtonElement));
        element1.click();

        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        element2.clear();
        element2.sendKeys(credentialUrlTestEdit);

        WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
        element3.clear();
        element3.sendKeys(credentialUsernameTestEdit);

        WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
        element4.clear();
        element4.sendKeys(credentialPasswordTestEdit);

        WebElement element5 = wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-save-changes")));
        element5.click();
    }

    public void deleteCredential(WebElement deleteElement) {
        WebElement deleteButotn = deleteElement.findElement(By.cssSelector("td:nth-child(1) a.btn-danger"));

        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
        this.navCredentialsTab.click();

        WebElement element0 = wait.until(ExpectedConditions.elementToBeClickable(deleteButotn));
        element0.click();

    }
}
