package service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import page.CurrentProjectPage;

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
}
