package ru.sdet.project;

import org.junit.AfterClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SetDriver {

protected static WebDriver driver;
//    @Parameters({"browserName", "browserVersion"})
//    @BeforeClass
//    public static void setUp(@Optional String browserName, String browserVersion){
//        WebDriverInstansiator.setDriver(browserName, browserVersion);
//    }

        public static WebDriver getWebDriver(String browserName) throws MalformedURLException {
            DesiredCapabilities capability = null; //new DesiredCapabilities();
            String baseUrl = System.getProperty("at.base.url");
            if(browserName.equals("firefox")){
                capability = DesiredCapabilities.firefox();
                capability.setBrowserName("firefox");
                capability.setPlatform(Platform.WINDOWS);
            }else{
                capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.WINDOWS);
            }
            driver = new RemoteWebDriver(new URL("http://192.168.0.27:4443/wd/hub"), capability);

//        if (driverType.equalsIgnoreCase("remote")) {
//            try {
////                DesiredCapabilities capability = new DesiredCapabilities();
//                capability.setBrowserName(browserName);
////                capability.setVersion(Any);
//                capability.setPlatform(Platform.WINDOWS);
//                driver = new RemoteWebDriver(new URL("http://192.168.0.27:4443/wd/hub"), capability);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//        }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.navigate().to(baseUrl);
//        driver.get(baseUrl);
            return driver;
        }

        @AfterClass
        public static void tearDown() throws Exception {
            driver.close();
        }
}
