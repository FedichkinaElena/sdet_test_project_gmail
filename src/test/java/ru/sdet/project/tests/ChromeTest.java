package ru.sdet.project.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.sdet.project.BasicTestCase;
import ru.sdet.project.MailBoxPage;

import java.net.MalformedURLException;

public class ChromeTest extends BasicTestCase {
//    private static WebDriver driver;
    private static MailBoxPage chromePage;
//    private static String USER_NAME_FROM = "Филинин Илья";
//    private static String EMAIL_TO = "ilya.filinin@simbirsoft.com";

    @BeforeTest
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
                "От " + System.getProperty("at.userName.from") + " найдено " + count + " писем.\nБраузер Chrome");
    }
}
