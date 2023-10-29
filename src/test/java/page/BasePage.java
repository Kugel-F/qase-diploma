package page;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver = DriverSingleton.getInstance().getDriver();

    protected BasePage() {
        PageFactory.initElements(driver, this);
    }
}
