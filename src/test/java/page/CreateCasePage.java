package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class CreateCasePage extends BasePage {

    @FindBy(xpath = "//input[@id='title']")
    WebElement testCaseTitle;
    @FindBy(xpath = "//button[@id='save-case']")
    WebElement testCaseSaveButton;
    @FindBy(xpath = "//a[@title='Repository']")
    WebElement backspaceLink;

    public CreateCasePage inputTitle(String title) {
        log.info("Input test case title");
        testCaseTitle.sendKeys(title);
        return this;
    }

    public void clickTestCaseSaveButton() {
        log.info("Click test case button");
        testCaseSaveButton.click();
    }

    public void clickBackSpaceLink() {
        log.info("Click backspaceLink");
        backspaceLink.click();
        new CurrentProjectPage();
    }
}
