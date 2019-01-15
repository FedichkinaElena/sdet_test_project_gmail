package ru.sdet.projects.pages;

import org.junit.AfterClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BasicTestCase {
    protected static WebDriver driver;
//    @Parameters({"browserName", "browserVersion"})
//    @BeforeClass
//    public static void setUp(@Optional String browserName, String browserVersion){
//        WebDriverInstansiator.setDriver(browserName, browserVersion);
//    }



    public static WebDriver getWebDriver(String driverType) throws MalformedURLException {
        DesiredCapabilities capability;
//        String baseUrl = "http://gmail.com";
        String baseUrl = System.getProperty("at.base.url");
        if (driverType.equalsIgnoreCase("firefox")) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setVersion("64.0.2");
            capability.setPlatform(Platform.WINDOWS);
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (driverType.equalsIgnoreCase("chrome")) {
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        } //else if (driverType.equalsIgnoreCase("opera")) {
//            capability = DesiredCapabilities.operaBlink();
//            capability.setBrowserName("opera");
//            System.setProperty("webdriver.opera.driver", "src/main/resources/operadriver.exe");
//            driver = new OperaDriver();
//        }
//        try {
//            driver = new RemoteWebDriver(new URL("http://localhost:4443/wd/hub"), capability);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        return driver;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.close();
    }

}
