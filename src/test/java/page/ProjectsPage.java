package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

import java.util.List;

@Log4j2
public class ProjectsPage extends BasePage {

    private static final String PROJECT_MENU = "//a[contains(text(),'%s')]//ancestor::tr[@class='PX4acs']" +
            "//button[@class='j4xaa7 bB3U2Y TuZZEp']";
    private static final String PROJECT_TITLE = "//a[@class='MfvNFg' and contains(text(),'%s')]";
    private static final String PROJECT_TITLE_TO_LIST = "//a[@class='MfvNFg']";

    @FindBy(xpath = "//button[@id='createButton']")
    WebElement createNewProjectButton;
    @FindBy(xpath = "//h1[contains(text(),'Projects')]")
    WebElement projectPageTitle;
    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    WebElement removeButton;
    @FindBy(xpath = "//span[contains(text(),'Delete project')]")
    WebElement confirmRemoveButton;

    public boolean isProjectPageTitleDisplayed() {
        log.info("Check project page title is displayed");
        return projectPageTitle.isDisplayed();
    }

    public ProjectModalPage clickCreateNewProjectButton() {
        log.info("Click new project button");
        createNewProjectButton.click();
        return new ProjectModalPage();
    }

    public ProjectsPage clickProjectMenu(String title) {
        log.info("Click project's menu");
        driver.findElement(By.xpath(String.format(PROJECT_MENU, title))).click();
        return this;
    }

    public ProjectsPage removeProject() {
        log.info("Remove current project");
        Waiter.waitElementToBeClickable(removeButton).click();
        return this;
    }

    public ProjectsPage confirmRemoveProject() {
        log.info("Confirm remove project");
        Waiter.waitElementToBeClickable(confirmRemoveButton).click();
        return this;
    }

    public boolean isProjectNotDisplayed(String title) {
        log.info("Check project is not on the page");
        return isWebElementNotDisplayed(driver.findElement(By.xpath(String.format(PROJECT_TITLE, title))));
    }

    public List<WebElement> getTitleList() {
        log.info("Check project is not on the page");
        return driver.findElements(By.xpath(PROJECT_TITLE_TO_LIST));
    }
}
