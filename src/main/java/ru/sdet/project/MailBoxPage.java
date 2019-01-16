package ru.sdet.project;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MailBoxPage {

    By txtSearchParamLocator = By.cssSelector("input[placeholder='Поиск в почте']");
    By lblCounterLocator = By.xpath("//div[@aria-label='Показать другие сообщения']/span[@class='Dj']/./span/span[@class='ts']");
    By btnCreateNewMailLocator = By.xpath("//div[text()='Написать']");
//    By txtEmailFromLocator = By.xpath("//table[@class='aoP aoC']/.//div[@class='aoD hl']/div[text()='Получатели']");
    By txtInputEmailFromLocator = By.xpath("//textarea[@class='vO']");
    By txtInputEmailSubjectBoxLocator = By.xpath(".//*[@name=\"subjectbox\"]");
    By txtInputEmailBodyLocator = By.xpath("//*[@class=\"Am Al editable LW-avf\"]");
    By btnSendEmailLocator = By.xpath("//div[text()='Отправить']");
    By lblSentEmailLocator = By.xpath("//span[text()='Письмо отправлено.']");

    private final WebDriver driver;

    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains("///https://accounts.google.com/signin/")) {
            throw new IllegalStateException("This is not the mail page");
        }
    }

    @Step("Поиск писем от заданного отправителя")
    public MailBoxPage typeSearchParam(String searchParam) {
        WebElement element = driver.findElement(txtSearchParamLocator);
        element.sendKeys("from:" + searchParam + Keys.ENTER);
        return this;
    }

    @Step("Поиск кол-ва найденных писем")
    public String getMailCount() {
        return driver.findElement(lblCounterLocator).getAttribute("innerHTML");
    }

    public MailBoxPage clickBtnCreateNewMail() {
        driver.findElement(btnCreateNewMailLocator).click();
        return new MailBoxPage(driver);
    }

    public MailBoxPage typeTxtEmailFrom(String emailFrom) {
        driver.findElement(txtInputEmailFromLocator).sendKeys(emailFrom);
        return this;
    }

    public MailBoxPage typeTxtMailSubject(String mailSubject) {
        driver.findElement(txtInputEmailSubjectBoxLocator).sendKeys(mailSubject);
        return this;
    }

    public MailBoxPage typeTxtMailBody(String mailBody) {
        driver.findElement(txtInputEmailBodyLocator).sendKeys(mailBody);
        return this;
    }

    public MailBoxPage clickBtnSendEmail() {
        driver.findElement(btnSendEmailLocator).click();
        return new MailBoxPage(driver);
    }

    @Step("Проверка отправки письма")
    public void checkSentEmail() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblSentEmailLocator));
    }

    @Step("Отправить новое письмо")
    public void sendNewEmail(String nameFrom, String emailSubjecr, String emailBody) {
        clickBtnCreateNewMail();
        typeTxtEmailFrom(nameFrom);
        typeTxtMailSubject(emailSubjecr);
        typeTxtMailBody(emailBody);
        clickBtnSendEmail();
        checkSentEmail();
    }
}