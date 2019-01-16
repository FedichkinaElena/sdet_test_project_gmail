package ru.sdet.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.sdet.project.HomePage;
import ru.sdet.project.MailBoxPage;
import ru.sdet.projects.pages.WebDriverInstansiator;

import java.net.MalformedURLException;

import static ru.sdet.project.HomePage.getWebDriver;
import static ru.sdet.projects.pages.BasicTestCase.getWebDriver;

public class ChromeTest {
    private static WebDriver driver;
    private static LoginPage chromePage;

    @BeforeClass
    public void setup() throws MalformedURLException {
//        WebDriverInstansiator.setDriver( "chrome", null);
        driver = HomePage.getWebDriver("chrome");
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
