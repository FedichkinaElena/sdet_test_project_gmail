package ru.sdet.projects.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailBoxPage {

    public MailBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    @FindBy(id = "identifierId")
    public WebElement txtInputLogin;

    @FindBy(id = "identifierNext")
    public WebElement btnLoginNext;

    @FindBy(css = "input[type=password]")
    public WebElement txtInputPassword;

    @FindBy(css = "#passwordNext")
    public WebElement btnPasswordNext;

    @FindBy(css = "input[placeholder='Поиск в почте']")
    public WebElement txtInputSearchParam;

    @FindBy(xpath = "//div[@aria-label='Показать другие сообщения']/span[@class='Dj']/./span/span[@class='ts']")
//    @FindBy(xpath = "//div[not(@style='display:none;')]/.//span/div[@aria-label='Показать другие сообщения' and @role='button']/span/span[2]")
    private WebElement lblCounter;

    @FindBy(xpath = "//div[text()='Написать']")
    private WebElement btnCreateNewMail;

    @FindBy(xpath = "//textarea[@class='vO']")
    private WebElement txtInputEmailFrom;

    @FindBy(xpath = ".//*[@name=\"subjectbox\"]")
    private WebElement txtInputEmailSubjectBox;

    @FindBy(xpath = "//*[@class=\"Am Al editable LW-avf\"]")
    private WebElement txtInputEmailBody;

    @FindBy(xpath = "//div[text()='Отправить']")
    private WebElement btnSendEmail;

    public String getFoundMailCount() {
        return lblCounter.getAttribute("innerHTML");
    }

    public void inputLogin(String login) {
        txtInputLogin.sendKeys(login);
    }

    public void clickBtnLoginNext() {
        btnLoginNext.click();
    }

    public void inputPassword(String password) {
        txtInputPassword.sendKeys(password);
    }

    public void clickBtnPasswordNext() {
        btnPasswordNext.click();
    }

    private void setEmailFrom(String nameFrom) {
        txtInputEmailFrom.sendKeys(nameFrom);
    }

    private void setEmailSubject(String emailSubject) {
        txtInputEmailSubjectBox.sendKeys(emailSubject);
    }

    private void inputEmailBody(String emailBody) {
        txtInputEmailBody.sendKeys(emailBody);
    }

    @Step("Отправить новое письма")
    public void sendNewEmail(String nameFrom, String emailSubjecr, String emailBody) {
        btnCreateNewMail.click();
        setEmailFrom(nameFrom);
        setEmailSubject(emailSubjecr);
        inputEmailBody(emailBody);
        btnSendEmail.click();
    }
}
