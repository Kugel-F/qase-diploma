package service.ui;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import page.CurrentProjectPage;
import page.SuiteModalPage;

@Log4j2
public class CurrentProjectPageService {

    private CurrentProjectPage currentProjectPage;

    @Step("Get new project title")
    public String getProjectTitle() {
        log.info("Get new project title");
        currentProjectPage = new CurrentProjectPage();
        return currentProjectPage.getProjectTitle();
    }

    @Step("Open projects page")
    public CurrentProjectPageService openProjectsPage() {
        log.info("Open projects page");
        currentProjectPage = new CurrentProjectPage();
        currentProjectPage.openProjectsPage();
        return this;
    }

    @Step("Open new suite modal form")
    public SuiteModalPage openSuiteModalForm() {
        log.info("Open new suite modal form");
        currentProjectPage = new CurrentProjectPage();
        currentProjectPage.clickCreateSuiteButton();
        return new SuiteModalPage();
    }

    @Step("Check new suite is displayed")
    public boolean isSuiteDisplayed(String title) {
        log.info("Check suite is displayed");
        currentProjectPage = new CurrentProjectPage();
        return currentProjectPage.isSuiteDisplayed(title);
    }

    @Step("Check new suite is not displayed")
    public boolean isSuiteNotDisplayed(String title) {
        log.info("Check suite is displayed");
        currentProjectPage = new CurrentProjectPage();
        return currentProjectPage.isSuiteNotDisplayed(title);
    }

    @Step("Delete suite")
    public CurrentProjectPageService deleteNewSuite(String label) {
        log.info("Delete suite");
        currentProjectPage = new CurrentProjectPage();
        currentProjectPage.openSuiteDropdown(label)
                .clickDeleteSuiteButton()
                .clickSubmitDeleteButton();
        return this;
    }

    @Step("Open create test case page")
    public CurrentProjectPageService clickCreateTestCaseButton() {
        log.info("Open create test case page");
        currentProjectPage = new CurrentProjectPage();
        currentProjectPage.clickCreateTestCaseButton();
        return this;
    }

    @Step("Open new create case form")
    public CurrentProjectPageService openCreateCaseForm() {
        log.info("Open new case modal form");
        currentProjectPage = new CurrentProjectPage();
        currentProjectPage.clickCreateTestCaseButton();
        return this;
    }

    @Step("Check 'Create new case' button is displayed")
    public boolean isNewCaseButtonDisplayed() {
        log.info("Check 'Create new case' button is displayed");
        currentProjectPage = new CurrentProjectPage();
        return currentProjectPage.isCreateNewCaseButtonDisplayed();
    }

    @Step("Get new create new test case message")
    public String getNewTestCaseTitle() {
        log.info("Get new create new test case message");
        currentProjectPage = new CurrentProjectPage();
        return currentProjectPage.getNewTestCaseTitle();
    }

    @Step("Get test cases amount")
    public int getTestCasesAmount() {
        log.info("Get test cases amount");
        currentProjectPage = new CurrentProjectPage();
        return currentProjectPage.getTestCasesList().size();
    }

    @Step("Delete test case")
    public CurrentProjectPageService deleteTestCase(String title, String text) {
        log.info("Click test case checkbox");
        currentProjectPage = new CurrentProjectPage();
        currentProjectPage.clickTestCaseCheckbox(title)
                .clickDeleteButton()
                .clickSubmitDeleteButton()
                .conformSubmitDeleteButton(text)
                .clickSubmitDeleteButton();
        return this;
    }

    @Step("Check test case is not displayed")
    public boolean isNotDisplayed() {
        log.info("Check test case is not displayed");
        currentProjectPage = new CurrentProjectPage();
        return currentProjectPage.isCaseTitleNotDisplayed();
    }

    @Step("Open suite modal page")
    public CurrentProjectPageService openSuiteModalPage() {
        log.info("Check test case is not displayed");
        currentProjectPage = new CurrentProjectPage();
        currentProjectPage.clickSuiteMenu()
                .clickSuiteEdit();
        return this;
    }
}
