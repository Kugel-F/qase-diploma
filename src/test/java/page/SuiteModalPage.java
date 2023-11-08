package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class SuiteModalPage extends BasePage {

    public static final String DESCRIPTION_LABEL = "Description";
    public static final String PRECONDITIONS_LABEL = "Preconditions";
    private static final String SUITE_INPUT_DETAILS = "//label[contains(text(),'%s')]" +
            "//ancestor::div[@class='qaOPP6']//p";

    @FindBy(xpath = "//input[@id='title']")
    WebElement suiteNameInput;
    @FindBy(xpath = "//span[text()='Create']//ancestor::button")
    WebElement submitCreateSuiteButton;
    @FindBy(xpath = "//div[@class='Thgbhj euhZGB cfvQxI']")
    WebElement parentSuiteDropDown;
    @FindBy(xpath = "//input[@aria-autocomplete='list']")
    WebElement parentRootOption;
    @FindBy(xpath = "//span[@class='ZwgkIF' and contains(text(),'Save')]")
    WebElement saveButton;

    public SuiteModalPage inputSuiteName(String name) {
        log.info("Input new suite name: " + name);
        suiteNameInput.sendKeys(name);
        return this;
    }

    public SuiteModalPage inputSuiteDetails(String label, String text) {
        log.info("Input new suite" + label + ": " + text);
        driver.findElement(By.xpath(String.format(SUITE_INPUT_DETAILS, label))).sendKeys(text);
        return this;
    }

    public SuiteModalPage openParentSuiteDropDown() {
        log.info("Open paren suite dropdown");
        parentSuiteDropDown.click();
        return this;
    }

    public SuiteModalPage chooseParentRootOption() {
        log.info("Choose parent suite root option");
        parentRootOption.sendKeys(Keys.ENTER);
        return this;
    }

    public CurrentProjectPage submitNewSuite() {
        log.info("Submit new suite creation");
        submitCreateSuiteButton.click();
        return new CurrentProjectPage();
    }

    public SuiteModalPage deleteSuiteName() {
        log.info("Delete exist suite name");
        suiteNameInput.sendKeys(Keys.COMMAND + "a" + Keys.DELETE);
        return this;
    }

    public CurrentProjectPage clickSaveButton() {
        log.info("Click save button");
        saveButton.click();
        return new CurrentProjectPage();
    }
}
