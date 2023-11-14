package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

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
    @FindBy(xpath = "//i[@class='fa fa-ellipsis-h']")
    WebElement suiteMenu;
    @FindBy(xpath = "//div[@class='Cr3S77']//i[@class='far fa-pencil']")
    WebElement suiteEdit;

    public String getProjectTitle() {
        log.info("Get new project title");
        return Waiter.waitElementVisibleOf(projectTitle).getText();
    }

    public ProjectsPage openProjectsPage() {
        log.info("Open projects page");
        projectsPageLink.click();
        return new ProjectsPage();
    }

    public CurrentProjectPage clickCreateSuiteButton() {
        log.info("Click create new suite button");
        Waiter.waitElementVisibleOf(createNewSuiteButton).click();
        return this;
    }

    public boolean isSuiteDisplayed(String title) {
        log.warn("Check new suite is displayed");
        return isWebElementDisplayed(driver.findElement(By.xpath(String.format(SUITE_TITLE, title))));
    }

    public boolean isSuiteNotDisplayed(String title) {
        log.warn("Check new suite is not been displayed");
        return isWebElementNotDisplayed(driver.findElement(By.xpath(String.format(SUITE_TITLE, title))));
    }

    public CurrentProjectPage openSuiteDropdown(String label) {
        log.info("Open suite dropdown menu");
        Waiter.waitElementVisibleOf(driver.findElement(By.xpath(String.format(SUITE_DROPDOWN, label)))).click();
        return this;
    }

    public CurrentProjectPage clickDeleteSuiteButton() {
        log.info("Click delete suite button");
        deleteSuiteButton.click();
        return this;
    }

    public CurrentProjectPage clickSubmitDeleteButton() {
        log.info("Click submit deleting button");
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
        return isWebElementDisplayed(createNewCaseButton);
    }

    public String getNewTestCaseTitle() {
        log.info("Get new create new test case message");
        return newTestCaseTitle.getText();
    }

    public List<WebElement> getTestCasesList() {
        log.info("Get test cases list");
        return Waiter.waitElementVisibleALLOf(testCasesList);
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

    public boolean isCaseTitleNotDisplayed() {
        log.warn("Check test case is not displayed");
        return isWebElementNotDisplayed(newTestCaseTitle);
    }

    public CurrentProjectPage clickSuiteMenu() {
        log.info("Click Suite menu");
        Waiter.waitElementVisibleOf(suiteMenu).click();
        return this;
    }

    public SuiteModalPage clickSuiteEdit() {
        log.info("Click suite edit");
        suiteEdit.click();
        return new SuiteModalPage();
    }
}
