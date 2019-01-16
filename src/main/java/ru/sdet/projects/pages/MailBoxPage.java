package ru.sdet.projects.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailBoxPage {

    public MailBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    protected WebDriver driver;

    @FindBy(id = "identifierId")
    private WebElement txtInputLogin;

    @FindBy(id = "identifierNext")
    private WebElement btnLoginNext;

    @FindBy(css = "input[type=password]")
    private WebElement txtInputPassword;

    @FindBy(css = "#passwordNext")
    private WebElement btnPasswordNext;

    @FindBy(css = "input[placeholder='Поиск в почте']")
    public WebElement txtInputSearchParam;




    @FindBy(xpath = "//div[@aria-label='Показать другие сообщения']/span[@class='Dj']/./span/span[@class='ts']")
//    @FindBy(xpath = "//div[not(@style='display:none;')]/.//span/div[@aria-label='Показать другие сообщения' and @role='button']/span/span[2]")
    private WebElement lblCounter;

    @FindBy(xpath = "//div[text()='Написать']")
    private WebElement btnCreateNewMail;

//    @FindBy(xpath = "//textarea[@class='vO']")
    @FindBy(xpath = "//table[@class='aoP aoC']/.//div[@class='aoD hl']/div[text()='Получатели']")
    private WebElement txtEmailFrom;

    @FindBy(xpath = "//textarea[@class='vO']")
    private WebElement txtInputEmailFrom;

    @FindBy(xpath = ".//*[@name=\"subjectbox\"]")
    private WebElement txtInputEmailSubjectBox;

    @FindBy(xpath = "//*[@class=\"Am Al editable LW-avf\"]")
    private WebElement txtInputEmailBody;

    @FindBy(xpath = "//div[text()='Отправить']")
    private WebElement btnSendEmail;

    @FindBy(xpath = "//span[text()='Письмо отправлено.']")
    private WebElement lblSentEmail;

    public void inputLogin(String login) {
        txtInputLogin.sendKeys(login);
    }

    public void clickBtnLoginNext() {
        btnLoginNext.click();
    }

    public void inputPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(txtInputPassword));
        wait.until(ExpectedConditions.elementToBeClickable(txtInputPassword));
        txtInputPassword.sendKeys(password);
    }

    public void clickBtnPasswordNext() {
        btnPasswordNext.click();
    }

    private void setEmailFrom(String nameFrom) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(txtInputEmailFrom));
        txtInputEmailFrom.sendKeys(nameFrom);
    }

    private void setEmailSubject(String emailSubject) {
        txtInputEmailSubjectBox.sendKeys(emailSubject);
    }

    private void inputEmailBody(String emailBody) {
        txtInputEmailBody.sendKeys(emailBody);
    }

    @Step("Проверка отправки письма")
    private void checkEmailSent() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(lblSentEmail));
        Assert.assertTrue(lblSentEmail.isDisplayed());
    }

    @Step("Залогиниться в хардкодную почту")
    public void loginUserMail(String login, String password){
        this.inputLogin(login);
        this.clickBtnLoginNext();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/signin/"));
        this.inputPassword(password);
        this.clickBtnPasswordNext();
    }

    @Step("Поиск писем от заданного отправителя")
    public void searchMailFrom(String userNameFrom){
        this.txtInputSearchParam.sendKeys("from:" + userNameFrom + Keys.ENTER);
    }

    @Step("Поиск кол-ва найденных писем")
    public String getFoundMailCount() {
        return lblCounter.getAttribute("innerHTML");
    }

    @Step("Отправить новое письмо")
    public void sendNewEmail(String nameFrom, String emailSubjecr, String emailBody) {
        btnCreateNewMail.click();
        setEmailFrom(nameFrom);
        setEmailSubject(emailSubjecr);
        inputEmailBody(emailBody);
        btnSendEmail.click();
        checkEmailSent();
    }
}
