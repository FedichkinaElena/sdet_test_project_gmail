package ru.sdet.project;

//import org.junit.AfterClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BasicTestCase {
    protected static WebDriver driver;

    public static WebDriver getWebDriver(String driverType) throws MalformedURLException {
        DesiredCapabilities capability;
        if (driverType.equalsIgnoreCase("firefox")) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setVersion("64.0.2");
            capability.setPlatform(Platform.WINDOWS);
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (driverType.equalsIgnoreCase("chrome")) {
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
//        try {
//            driver = new RemoteWebDriver(new URL("http://localhost:4443/wd/hub"), capability);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(System.getProperty("at.base.url"));
        return driver;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.close();
    }

}
