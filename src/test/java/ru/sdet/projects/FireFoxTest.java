package ru.sdet.projects;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sdet.projects.pages.BasicTestCase;
import ru.sdet.projects.pages.MailBoxPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class FireFoxTest extends BasicTestCase {
    private static WebDriver driver;
    private static MailBoxPage firefoxPage;
    private static String COUNT;
    //    private static String USER_NAME_FROM = "Филинин Илья";
//    private static String EMAIL_TO = "ilya.filinin@simbirsoft.com";
    private static String USER_NAME_FROM = "Алена Федичкина";
    private static String EMAIL_TO = "nika-alenka@mail.ru";
    private static String TESTER_NAME = "Федичкина Елена";
    private static String LOGIN = "testing0tester924@gmail.com";
    private static String PASSWORD = "Axaha0test.";

    @BeforeClass
    public static void setup() throws MalformedURLException {
//        String baseUrl = "http://gmail.com";
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get(baseUrl);
        driver = getWebDriver("firefox");
        firefoxPage = PageFactory.initElements(driver, MailBoxPage.class);
    }

    @Test
    public void firefoxTest() {
        Assert.assertTrue(driver.getCurrentUrl().contains("accounts.google.com/signin/"));

        //login
        firefoxPage.inputLogin(LOGIN);
        firefoxPage.clickBtnLoginNext();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=password]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=password]")));
        firefoxPage.inputPassword(PASSWORD);
        firefoxPage.clickBtnPasswordNext();

        //search
        firefoxPage.txtInputSearchParam.sendKeys("from:" + USER_NAME_FROM + Keys.ENTER);
        COUNT = firefoxPage.getFoundMailCount();
        firefoxPage.sendNewEmail(
                EMAIL_TO,
                "Тестовое задание. " + TESTER_NAME,
                "От " + USER_NAME_FROM + " найдено " + COUNT + " писем.\nБраузер Firefox");
    }
}
