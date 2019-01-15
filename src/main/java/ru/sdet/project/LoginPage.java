package ru.sdet.project;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    By txtInputLoginLocator = By.id("identifierId");
    By btnLoginNextLocator = By.id("identifierNext");
    By txtInputPasswordLocator = By.cssSelector("input[type=password]");
    By btnPasswordNextLocator = By.cssSelector("#passwordNext");

    private final WebDriver driver;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains("https://accounts.google.com/signin/")) {
            throw new IllegalStateException("This is not the login page");
        }
    }


    public LoginPage typeLogin(String username) {
        driver.findElement(txtInputLoginLocator).sendKeys(username);
        return this;
    }

    public LoginPage clickBtnLoginNext() {
        driver.findElement(btnLoginNextLocator).click();
        return new LoginPage(driver);
    }


    public LoginPage typePassword(String password) {
        driver.findElement(txtInputPasswordLocator).sendKeys(password);
        return this;
    }

    public LoginPage clickBtnPasswordNext() {
        driver.findElement(btnPasswordNextLocator).click();
//        return new LoginPage(driver);
        return this;
    }

    public LoginPage submitBtnPasswordNext() {
        driver.findElement(btnPasswordNextLocator).submit();
        return this;
//        return new LoginPage(driver);
    }

    @Step("Залогиниться в почту")
    public LoginPage loginAs(String username, String password) {
        typeLogin(username);
        clickBtnLoginNext();
        typePassword(password);
        return clickBtnPasswordNext();
    }
}