package service;

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

    @Step("Check new suite is displayed")
    public boolean isSuiteIsNotDisplayed(String title) {
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
                .submitDeleteSuiteButton();
        return this;
    }
}
