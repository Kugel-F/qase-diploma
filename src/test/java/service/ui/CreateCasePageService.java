package service.ui;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.ui.Case;
import page.CreateCasePage;

@Log4j2
public class CreateCasePageService {

    private CreateCasePage createCasePage;

    @Step("Create test case without require attribute")
    public void createTestCaseWithoutAllRequireAttribute(Case aCase) {
        log.info("Create test case without require attribute");
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.clickCreateTestCaseButton();
        createCasePage = new CreateCasePage();
        createCasePage.inputTitle(aCase.getTitle())
                .clickTestCaseSaveButton();
    }

    @Step("Click backspace link")
    public void clickBackspaceButton() {
        log.info("Click backspaceLink");
        createCasePage = new CreateCasePage();
        createCasePage.clickBackSpaceLink();
    }
}
