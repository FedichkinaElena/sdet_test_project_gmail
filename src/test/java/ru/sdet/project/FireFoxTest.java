package ru.sdet.project;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class FireFoxTest extends BasicTestCase {
    private static WebDriver driver;
    private static MailBoxPage firefoxPage;
//    private static String USER_NAME_FROM = "Филинин Илья";
//    private static String EMAIL_TO = "ilya.filinin@simbirsoft.com";

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
