package ru.sdet.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ChromeTest {
    private static WebDriver driver;
    private static LoginPage chromePage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        driver = SetDriver.getWebDriver("chrome");
        chromePage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void chromeTest() {
        String login = System.getProperty("at.login");
        String password = System.getProperty("at.password");
        String userNameFrom = System.getProperty("at.userName.from");
//        LoginPage chromePage = PageFactory.initElements(driver, LoginPage.class);

        // логин в почту заданного пользователя
        chromePage.loginAs(login, password);

        MailBoxPage mailBoxPage = PageFactory.initElements(driver, MailBoxPage.class);
        // поиск писем от заданного отправителя
        mailBoxPage.typeSearchParam(userNameFrom);
        // поиск кол-ва найденных писем
        String count = mailBoxPage.getMailCount();
        // отправка нового письма
        mailBoxPage.sendNewEmail(
                System.getProperty("at.email.to"),
                "Тестовое задание. " + System.getProperty("at.tester.name"),
                "От " + System.getProperty("at.username.from") + " найдено " + count + " писем.\nБраузер Chrome");
    }
}
