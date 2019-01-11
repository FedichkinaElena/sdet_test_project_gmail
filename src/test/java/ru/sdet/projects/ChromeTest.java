package ru.sdet.projects;

import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sdet.projects.pages.BasicTestCase;
import ru.sdet.projects.pages.MailBoxPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

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
//        String baseUrl = "http://gmail.com";
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get(baseUrl);
        driver = getWebDriver("chrome");
        chromePage = PageFactory.initElements(driver, MailBoxPage.class);
    }

//    @Test
    public void firstTestChrome() {
//        TestDriver driver = new TestDriver("chrome");
//        WebElement startButton = driver.findElement(By.xpath("//a[text()='Войти']"));
//        startButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"));

        WebElement loginField = driver.findElement(By.id("identifierId"));
        loginField.sendKeys(LOGIN);
        WebElement btnLoginNextStage = driver.findElement(By.id("identifierNext"));
        btnLoginNextStage.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=password]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=password]")));
        driver.findElement(By.cssSelector("input[type=password]")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("#passwordNext")).click();

        //search
        driver.findElement(By.cssSelector("input[placeholder='Поиск в почте']")).sendKeys("from:" + USER_NAME_FROM + Keys.ENTER);
        COUNT = driver.findElement(By.id(":6t")).getText();
        COUNT = COUNT.substring(COUNT.indexOf("из ") + 3);


        driver.findElement(By.xpath("//div[text()='Написать']")).click();
        WebElement emailFrom = driver.findElement(By.xpath("//textarea[@class='vO']"));
        WebElement subjectMail = driver.findElement(By.xpath(".//*[@name=\"subjectbox\"]"));
        WebElement bodyMail = driver.findElement(By.xpath("//*[@class=\"Am Al editable LW-avf\"]"));

        emailFrom.sendKeys(USER_NAME_FROM);
        subjectMail.sendKeys("Тестовое задание. " + TESTER_NAME);
        bodyMail.sendKeys("От " + USER_NAME_FROM + " найдено " + COUNT + " писем.");
        driver.findElement(By.xpath("//div[text()='Отправить']")).click();

    }

    @Test
    public void chromeTest() {
        Assert.assertTrue(driver.getCurrentUrl().contains("accounts.google.com/signin/"));
        //login
        loginUserMail();
//        chromePage.inputLogin(LOGIN);
//        chromePage.clickBtnLoginNext();
//        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"));
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=password]")));
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=password]")));
//
//        chromePage.inputPassword(PASSWORD);
//        chromePage.clickBtnPasswordNext();

        //search
        chromePage.txtInputSearchParam.sendKeys("from:" + USER_NAME_FROM + Keys.ENTER);
//        driver.findElement(By.xpath("//*[@id=':8c']/./span/span[contains(@class,'ts')]")).getAttribute("innerHTML");
        COUNT = chromePage.getFoundMailCount();
        chromePage.sendNewEmail(
                EMAIL_TO,
                "Тестовое задание. " + TESTER_NAME,
                "От " + USER_NAME_FROM + " найдено " + COUNT + " писем.\nБраузер Chrome");
    }

    @Step("Залогиниться в хардкодную почту")
    private void loginUserMail(){
        chromePage.inputLogin(LOGIN);
        chromePage.clickBtnLoginNext();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"));
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=password]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=password]")));

        chromePage.inputPassword(PASSWORD);
        chromePage.clickBtnPasswordNext();
    }
}
