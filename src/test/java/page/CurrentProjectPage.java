package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class CurrentProjectPage extends BasePage {

    private static final String SUITE_DROPDOWN = "//a[@title='%s']//ancestor::div[@class='RRWS28 dinNuw orFHna']" +
            "//i[@class='fa fa-ellipsis-h']";
    private static final String SUITE_TITLE = "//a[@title='%s']";

    @FindBy(xpath = "//div[@class='LALnEw']")
    WebElement projectTitle;
    @FindBy(xpath = "//a[@class='prLoyX' and @href='/projects']")
    WebElement projectsPageLink;
    @FindBy(xpath = "//a[@id='create-suite-button']")
    WebElement createSuiteButton;
    @FindBy(xpath = "//a[@id='create-case-button']")
    WebElement createCaseButton;
    @FindBy(xpath = "//i[@class='fas fa-trash']")
    WebElement deleteSuiteButton;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitDeleteSuiteButton;

    public String getProjectTitle() {
        log.warn("Get new project title");
        return Waiter.waitVisibleOf(projectTitle).getText();
    }

    public ProjectsPage openProjectsPage() {
        log.info("Open projects page");
        projectsPageLink.click();
        return new ProjectsPage();
    }

    public CurrentProjectPage clickCreateSuiteButton() {
        log.warn("Click create new suite button");
        Waiter.waitVisibleOf(createSuiteButton).click();
        return this;
    }

    public boolean isSuiteDisplayed(String title) {
        log.warn("Check new suite is displayed");
        return Waiter.waitVisibleOf(driver.findElement(By.xpath(String.format(SUITE_TITLE, title)))).isDisplayed();
    }

    public boolean isSuiteNotDisplayed(String title) {
        log.warn("Check new suite is not been displayed");
        try {
            Waiter.waitElementInvisibleOf(driver.findElement(By.xpath(String.format(SUITE_TITLE, title))));
        } catch (Exception exception) {
            log.debug("Project is on the page");
        }
        return true;
    }

    public CurrentProjectPage openSuiteDropdown(String label) {
        log.warn("Open suite dropdown menu");
        Waiter.waitVisibleOf(driver.findElement(By.xpath(String.format(SUITE_DROPDOWN, label)))).click();
        return this;
    }

    public CurrentProjectPage clickDeleteSuiteButton() {
        log.warn("Click delete suite button");
        deleteSuiteButton.click();
        return this;
    }

    public CurrentProjectPage submitDeleteSuiteButton() {
        log.warn("Click submit deleting suite button");
        submitDeleteSuiteButton.click();
        return this;
    }

    public CreateCasePage clickCreateTestCaseButton() {
        log.info("Click create case button");
        createCaseButton.click();
        return new CreateCasePage();
    }
}
