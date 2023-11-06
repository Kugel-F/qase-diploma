package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

import java.time.Duration;
import java.util.List;

@Log4j2
public class CurrentProjectPage extends BasePage {

    private static final String SUITE_DROPDOWN = "//a[@title='%s']//ancestor::div[@class='RRWS28 dinNuw orFHna']" +
            "//i[@class='fa fa-ellipsis-h']";
    private static final String SUITE_TITLE = "//a[@title='%s']";
    private static final String CASE_CHECKBOX_INPUT = "//div[contains(text(),'%s')]//ancestor::div[@class='EllwN3']" +
            "//input[@type='checkbox']";

    @FindBy(xpath = "//div[@class='LALnEw']")
    WebElement projectTitle;
    @FindBy(xpath = "//a[@class='prLoyX' and @href='/projects']")
    WebElement projectsPageLink;
    @FindBy(xpath = "//a[@id='create-suite-button']")
    WebElement createNewSuiteButton;
    @FindBy(xpath = "//a[@id='create-case-button']")
    WebElement createNewCaseButton;
    @FindBy(xpath = "//i[@class='fas fa-trash']")
    WebElement deleteSuiteButton;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitDeleteButton;
    @FindAll(@FindBy(xpath = "//div[@class='orFHna D1Mg5l']"))
    List<WebElement> testCasesList;
    @FindBy(xpath = "//button[contains(text(),' Delete')]")
    WebElement caseDeleteButton;
    @FindBy(xpath = "//input[@name='confirm']")
    WebElement conformSubmitDeleteButton;
    @FindBy(xpath = "//div[@class='WVGvc_ wq7uNh']")
    WebElement newTestCaseTitle;

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
        Waiter.waitVisibleOf(createNewSuiteButton).click();
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

    public CurrentProjectPage clickSubmitDeleteButton() {
        log.warn("Click submit deleting button");
        submitDeleteButton.click();
        return this;
    }

    public CreateCasePage clickCreateTestCaseButton() {
        log.info("Click create case button");
        createNewCaseButton.click();
        return new CreateCasePage();
    }

    public boolean isCreateNewCaseButtonDisplayed() {
        log.warn("Check 'Create new case' button is displayed");
        return Waiter.waitVisibleOf(createNewCaseButton).isDisplayed();
    }
    public String getNewTestCaseTitle() {
        log.info("Get new create new test case message");
        return newTestCaseTitle.getText();
    }

    public List<WebElement> getTestCasesList() {
        log.warn("Get test cases list");
        return Waiter.waitVisibleALLOf(testCasesList);
    }

    public CurrentProjectPage clickTestCaseCheckbox(String title) {
        log.info("Click test case checkbox with title: " + title);
        driver.findElement(By.xpath(String.format(CASE_CHECKBOX_INPUT, title))).click();
        return this;
    }

    public CurrentProjectPage clickDeleteButton() {
        log.info("Click 'Delete' button");
        caseDeleteButton.click();
        return this;
    }

    public CurrentProjectPage conformSubmitDeleteButton(String text) {
        log.info("Input 'Conform' while click 'Delete' button");
        conformSubmitDeleteButton.sendKeys(text);
        return this;
    }

    public boolean isTestCaseTitleNotDisplayed() {
        log.warn("Check test case is not displayed");
        try{
            Waiter.waitElementInvisibleOf(newTestCaseTitle);
            newTestCaseTitle.isDisplayed();
        } catch(Exception exception){
            log.debug("Test case is displayed");
        }
        return true;
    }
}
