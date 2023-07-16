package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
}
