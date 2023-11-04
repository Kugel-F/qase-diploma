package utils;

import driver.DriverSingleton;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@NoArgsConstructor
@Getter
public class Waiter {

    public final static int WAIT_5_SECONDS = 5;
    public final static int WAIT_10_SECONDS = 10;

    public static WebElement waitElementToBeClickable(WebElement element) {

        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_10_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitListElementsInvisibleOf(List<WebElement> elements) {

        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_5_SECONDS))
                .until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public static WebElement waitVisibleOf(WebElement element) {

        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_10_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }
}
