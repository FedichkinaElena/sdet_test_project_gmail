package ru.sdet.project.listeners;

//import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;

public class CustomTestListener extends TestListenerAdapter {

//    private ExtentReports extentReports = new ExtentReports();

    @Override
    public void onStart(ITestContext iTestContext) {
            super.onStart(iTestContext);
//            extentReports.init("extent-report.html",true);
    }

//    private Logger log = (Logger) LoggerFactory.getLogger(CustomTestListener.class);

//    @Override
//    public void onTestStart(ITestResult result) {
//        log.info("Test class started: " + result.getTestClass().getName());
//        log.info("Test started: " + result.getName());
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        log.info("Test SUCCESS: " + result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
////        makeScreenshot();
//        log.info("Test FAILED: " + result.getName());
//        if (result.getThrowable()!=null) {
//            result.getThrowable().printStackTrace();
//        }
//    }

//    @Attachment(value = "Page screenshot", type = "image/png")
//    private byte[] makeScreenshot() {
//        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }
}
