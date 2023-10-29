package tests.base;

import driver.DriverSingleton;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] getAllureScreenshot() {
        log.info("Make screenshot while test is failed");
        return ((TakesScreenshot) DriverSingleton.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult itestResult) {
        log.info(String.format("\n" + "          ===== STARTING TEST %s =====", itestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    log.info(String.format("\n" + "          ===== FINISHED TEST %s Duration: %ss =====", iTestResult.getName(),
            getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.fatal(String.format("\n" + "          ===== FAILED TEST %s Duration: %ss =====", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        getAllureScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(String.format("\n" + "\n" + "          ===== SKIPPING TEST %s =====", iTestResult.getName()));
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
