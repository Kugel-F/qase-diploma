package page;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Waiter;

public class BasePage {

    protected WebDriver driver = DriverSingleton.getInstance().getDriver();

    protected BasePage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isWebElementNotDisplayed(WebElement element) {
        try {
            Waiter.waitElementInvisibleOf(element);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isWebElementDisplayed(WebElement element) {
        try {
            Waiter.waitElementVisibleOf(element).isDisplayed();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
