package ru.sdet.project;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * Factory to instantiate a WebDriver object. It returns an instance of the driver (local invocation) or an instance of RemoteWebDriver
 */
public class WebDriverFactory {

    /* Browsers constants */
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

    /* Platform constants */
    public static final String WINDOWS = "windows";

    public WebDriverFactory(){}

    /*
     * Factory method to return a RemoteWebDriver instance given the url of the
     * Grid hub and a Browser instance.
     *
     * @param gridHubUrl : grid hub URI
     *
     * @param browser : Browser object containing info around the browser to hit
     *
     * @param username : username for BASIC authentication on the page to test
     *
     * @param password : password for BASIC authentication on the page to test
     *
     * @return RemoteWebDriver
     */
    public static WebDriver getInstance(String gridHubUrl, String browserName) {
        WebDriver webDriver = null;
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);

        // In case there is no Hub
        if (gridHubUrl == null || gridHubUrl.length() == 0) {
            return getInstance(browserName);
        }

        if (CHROME.equalsIgnoreCase(browserName)) {
            capability = DesiredCapabilities.chrome();
            capability.setPlatform(Platform.extractFromSysProperty(WINDOWS));
        } else if (FIREFOX.equalsIgnoreCase(browserName)) {
            capability = DesiredCapabilities.firefox();
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capability.setPlatform(Platform.extractFromSysProperty(WINDOWS));
        }
        // Create Remote WebDriver
        try {
            webDriver = new RemoteWebDriver(new URL(gridHubUrl), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return webDriver;
    }

    /*
     * Factory method to return a WebDriver instance given the browser to hit
     *
     * @param browser : String representing the local browser to hit
     *
     * @param username : username for BASIC authentication on the page to test
     *
     * @param password : password for BASIC authentication on the page to test
     *
     * @return WebDriver instance
     */
    public static WebDriver getInstance(String browserName) {
        WebDriver webDriver = null;
        if (CHROME.equalsIgnoreCase(browserName)) {
            setChromeDriver();
            webDriver = new ChromeDriver();
        } else if (FIREFOX.equalsIgnoreCase(browserName)) {
            setFirefoxDriver();
            webDriver = new FirefoxDriver();
        }
        return webDriver;
    }

    /*
     * Helper method to set version and platform for a specific browser
     *
     * @param capability : DesiredCapabilities object coming from the selected
     * browser
     * @param version : browser version
     * @param platform : browser platform
     * @return DesiredCapabilities
     */
    private static DesiredCapabilities setVersionAndPlatform(
            DesiredCapabilities capability, String version, String platform) {
        if (WINDOWS.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.WINDOWS);
        } else {
            capability.setPlatform(Platform.ANY);
        }
        if (version != null) {
            capability.setVersion(version);
        }
        return capability;
    }

    /*
     * Helper method to set ChromeDriver location into the right system property
     */
    private static void setChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
    }

    /*
     * Helper method to set FirefoxDriver location into the right system property
     */
    private static void setFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
    }
}
