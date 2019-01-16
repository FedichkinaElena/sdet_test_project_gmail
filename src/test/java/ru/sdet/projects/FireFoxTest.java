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

    @BeforeClass
    public static void setup() throws MalformedURLException {
        driver = getWebDriver("firefox");
        firefoxPage = PageFactory.initElements(driver, MailBoxPage.class);
    }

    @Test
    public void firefoxTest() {
        String login = System.getProperty("at.login");
        String password = System.getProperty("at.password");
        String userNameFrom = System.getProperty("at.userName.from");

        // логин в почту заданного пользователя
        firefoxPage.loginUserMail(login, password);
        // поиск писем от заданного отправителя
        firefoxPage.searchMailFrom(userNameFrom);
        // поиск кол-ва найденных писем
        String count = firefoxPage.getFoundMailCount();
        // отправка нового письма
        firefoxPage.sendNewEmail(
                System.getProperty("at.email.to"),
                "Тестовое задание. " + System.getProperty("at.tester.name"),
                "От " + System.getProperty("at.userName.from") + " найдено " + count + " писем.\nБраузер Firefox");
    }
}
