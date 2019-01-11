package ru.sdet.projects.pages;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import ru.sdet.projects.pages.WebDriverInstansiator;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
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
        String baseUrl = "http://gmail.com";
        if (driverType.equalsIgnoreCase("firefox")) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setVersion("64.0.2");
            capability.setPlatform(Platform.WINDOWS);
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
//            driver = new FirefoxDriver();
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4443/wd/hub"), capability);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
//            driver = new RemoteWebDriver(new URL("http://localhost:4443/wd/hub"), capability);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(baseUrl);

        } else {
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(baseUrl);
        }
//        try {
//            driver = new RemoteWebDriver(new URL("http://localhost:4443/wd/hub"), capability);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        return driver;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.close();
    }

}
