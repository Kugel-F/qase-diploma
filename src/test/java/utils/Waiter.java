package utils;

import driver.DriverSingleton;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@NoArgsConstructor
@Getter
public class Waiter {

    public final static int WAIT_2_SECONDS = 2;
    public final static int WAIT_5_SECONDS = 5;

    public static WebElement waitElementToBeClickable(WebElement element) {

        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_5_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitVisibleOf(WebElement element) {

        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_5_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }
}
