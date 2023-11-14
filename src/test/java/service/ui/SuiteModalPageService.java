package service.ui;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.ui.Suite;
import page.SuiteModalPage;

@Log4j2
public class SuiteModalPageService {

    private SuiteModalPage suiteModalPage;

    @Step("Create new suite")
    public void createNewSuite(Suite suite, String descriptionLabel, String preconditionLabel) {
        log.info("Create new suite");
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openSuiteModalForm();
        suiteModalPage = new SuiteModalPage();
        suiteModalPage.inputSuiteName(suite.getName())
                .openParentSuiteDropDown()
                .chooseParentRootOption()
                .inputSuiteDetails(descriptionLabel, suite.getDescription())
                .inputSuiteDetails(preconditionLabel, suite.getPreconditions())
                .submitNewSuite();
        new SuiteModalPage();
    }

    @Step("Edit suite name")
    public void editSuiteTitle(String name) {
        log.info("Edit suite name: " + name);
        suiteModalPage = new SuiteModalPage();
        suiteModalPage.deleteSuiteName()
                .inputSuiteName(name)
                .clickSaveButton();
    }
}
