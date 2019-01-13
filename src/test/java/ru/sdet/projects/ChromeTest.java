package ru.sdet.projects;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sdet.projects.pages.BasicTestCase;
import ru.sdet.projects.pages.MailBoxPage;

import java.net.MalformedURLException;

public class ChromeTest extends BasicTestCase {
//    private static WebDriver driver;
    private static MailBoxPage chromePage;
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
        driver = getWebDriver("chrome");
        chromePage = PageFactory.initElements(driver, MailBoxPage.class);
    }

//    @Test
//    public void firstTestChrome() {
////        TestDriver driver = new TestDriver("chrome");
////        WebElement startButton = driver.findElement(By.xpath("//a[text()='Войти']"));
////        startButton.click();
//        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"));
//
//        WebElement loginField = driver.findElement(By.id("identifierId"));
//        loginField.sendKeys(LOGIN);
//        WebElement btnLoginNextStage = driver.findElement(By.id("identifierNext"));
//        btnLoginNextStage.click();
//        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"));
//
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=password]")));
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=password]")));
//        driver.findElement(By.cssSelector("input[type=password]")).sendKeys(PASSWORD);
//        driver.findElement(By.cssSelector("#passwordNext")).click();
//
//        //search
//        driver.findElement(By.cssSelector("input[placeholder='Поиск в почте']")).sendKeys("from:" + USER_NAME_FROM + Keys.ENTER);
//        COUNT = driver.findElement(By.id(":6t")).getText();
//        COUNT = COUNT.substring(COUNT.indexOf("из ") + 3);
//
//
//        driver.findElement(By.xpath("//div[text()='Написать']")).click();
//        WebElement emailFrom = driver.findElement(By.xpath("//textarea[@class='vO']"));
//        WebElement subjectMail = driver.findElement(By.xpath(".//*[@name=\"subjectbox\"]"));
//        WebElement bodyMail = driver.findElement(By.xpath("//*[@class=\"Am Al editable LW-avf\"]"));
//
//        emailFrom.sendKeys(USER_NAME_FROM);
//        subjectMail.sendKeys("Тестовое задание. " + TESTER_NAME);
//        bodyMail.sendKeys("От " + USER_NAME_FROM + " найдено " + COUNT + " писем.");
//        driver.findElement(By.xpath("//div[text()='Отправить']")).click();
//
//    }

    @Test
    public void chromeTest() {
        Assert.assertTrue(driver.getCurrentUrl().contains("accounts.google.com/signin/"));
        // логин в почту заданного пользователя
        chromePage.loginUserMail(chromePage);
        // поиск писем от заданного отправителя
        chromePage.txtInputSearchParam.sendKeys("from:" + System.getProperty("at.username.from") + Keys.ENTER);
        // кол-во писем
        COUNT = chromePage.getFoundMailCount();
        // отправка нового письма
        chromePage.sendNewEmail(
                System.getProperty("at.email.to"),
                "Тестовое задание. " + System.getProperty("at.testername"),
                "От " + System.getProperty("at.username.from") + " найдено " + COUNT + " писем.\nБраузер Chrome");
    }
}
