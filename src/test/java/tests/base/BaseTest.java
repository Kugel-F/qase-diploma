package tests.base;

import driver.DriverSingleton;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
@Log4j2
public class BaseTest {

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {

        DriverSingleton.getInstance().closeDriver();
        log.info("Close browser");
    }
}
