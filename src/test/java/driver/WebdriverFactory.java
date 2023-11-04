package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

import static utils.Waiter.WAIT_5_SECONDS;

@NoArgsConstructor
@Log4j2
public class WebdriverFactory {

    public static WebDriver getWebDriver() {
        WebDriver driver = null;
        switch (System.getProperty("browser", "chrome")) {
            case "safari": {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            }
            default: {
                try {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    options.addArguments("--incognito");
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-translate");
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--ignore-certificate-errors");
                    driver = new ChromeDriver(options);
                } catch (Exception exception) {
                    log.fatal("Driver didn't start");
                }
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_5_SECONDS));
        return driver;
    }
}
