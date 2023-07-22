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


    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void addNote(String noteTitle, String noteDescription) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.openAddNoteModel));
//        WebDriverWait wait = new WebDriverWait(this.webDriver, 10);
//        WebElement element0  = wait.until(ExpectedConditions.elementToBeClickable(By.id("openAddNoteModel")));
//        element0.click();
        this.navNotesTab.click();

        WebElement element0 = wait.until(ExpectedConditions.elementToBeClickable(By.id("openAddNoteModel")));
        element0.click();

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ById.id("note-title")));
        element.sendKeys(noteTitle);

        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ById.id("note-description")));
        element2.sendKeys(noteDescription);

        WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("note-save-changes")));
        element3.click();

//        this.noteTitle.sendKeys(noteTitle);
//        this.noteDescription.sendKeys(noteDescription);
//        this.noteSubmit.click();
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
}
