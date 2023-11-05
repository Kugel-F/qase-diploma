package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class LoginPage extends BasePage {

    public static final String EMAIL_LABEL = "text";
    public static final String PASSWORD_FIELD_LABEL = "password";
    private static final String LOGIN_PAGE_URL = "https://app.qase.io/login";
    private static final String NEED_FULFILL_FIELD_MESSAGE = "//input[@type='%s']" +
            "//ancestor::div[@class='tdishH']//small";
    @FindBy(xpath = "//input[@type='text']")
    WebElement workEmailInput;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordInput;
    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    WebElement signInButton;
    @FindBy(xpath = "//span[@class='ic9QAx']")
    WebElement invalidDataMessage;

    public LoginPage openPage() {
        log.info("Open www.qase.io");
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    public LoginPage emailInput(String workEmail) {
        log.info("Input: " + workEmail);
        workEmailInput.sendKeys(workEmail);
        return this;
    }

    public LoginPage passwordInput(String password) {
        log.info("Input: " + password);
        passwordInput.sendKeys(password);
        return this;
    }

    public ProjectsPage clickSignInButton() {
        log.info("Click 'Sign in' button");
        signInButton.click();
        return new ProjectsPage();
    }

    public String getRequirementFulfillFieldMessageText(String Label) {
        log.info("Get message about fulfilling field");
        return driver.findElement(By.xpath(String.format(NEED_FULFILL_FIELD_MESSAGE, Label))).getText();
    }

    public String getInvalidDataMessageText() {
        log.info("Get invalid data message text");
        return invalidDataMessage.getText();
    }
}
