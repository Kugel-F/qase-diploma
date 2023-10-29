package driver;

import org.openqa.selenium.WebDriver;

public class DriverSingleton {

    private static final ThreadLocal<DriverSingleton> instance = new ThreadLocal<>();

    private WebDriver driver;

    public DriverSingleton() {
        driver = WebdriverFactory.getWebDriver();
    }

    public static synchronized DriverSingleton getInstance() {
        if (instance.get() == null) {
            instance.set(new DriverSingleton());
        }
        return instance.get();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        try {
            driver.quit();
            driver = null;
        } finally {
            instance.remove();
        }
    }
}
