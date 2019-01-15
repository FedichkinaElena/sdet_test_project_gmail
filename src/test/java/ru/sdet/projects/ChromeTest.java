package ru.sdet.projects;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sdet.projects.pages.BasicTestCase;
import ru.sdet.projects.pages.MailBoxPage;

import java.net.MalformedURLException;

public class ChromeTest extends BasicTestCase {
//    private static WebDriver driver;
    private static MailBoxPage chromePage;

    @BeforeClass
    public static void setup() throws MalformedURLException {
        driver = getWebDriver("chrome");
        chromePage = PageFactory.initElements(driver, MailBoxPage.class);
    }

    @Test
    public void chromeTest() {
        String login = System.getProperty("at.login");
        String password = System.getProperty("at.password");
        String userNameFrom = System.getProperty("at.userName.from");

        // логин в почту заданного пользователя
        chromePage.loginUserMail(login, password);
        // поиск писем от заданного отправителя
        chromePage.searchMailFrom(userNameFrom);
        // поиск кол-ва найденных писем
        String count = chromePage.getFoundMailCount();
        // отправка нового письма
        chromePage.sendNewEmail(
                System.getProperty("at.email.to"),
                "Тестовое задание. " + System.getProperty("at.tester.name"),
                "От " + System.getProperty("at.username.from") + " найдено " + count + " писем.\nБраузер Chrome");
    }
}
