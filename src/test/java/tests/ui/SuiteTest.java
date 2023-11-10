package tests.ui;

import jdk.jfr.Description;
import model.ui.Project;
import model.ui.Suite;
import model.ui.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ui.*;
import tests.base.BaseTest;

import static page.SuiteModalPage.DESCRIPTION_LABEL;
import static page.SuiteModalPage.PRECONDITIONS_LABEL;
import static utils.DataGenerator.generateNewTitle;
import static utils.DataGenerator.generateRandomStringExpression;

public class SuiteTest extends BaseTest {

    private SuiteModalPageService suiteModalPageService;
    private CurrentProjectPageService currentProjectPageService;

    @BeforeMethod
    @Description("Sign in www.qase.io")
    public void setUp() {
        LoginPageService loginPageService = new LoginPageService();
        loginPageService.openLoginPage();
        loginPageService.signIn(new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!"));
        Project project = Project.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .accessType("Public")
                .build();
        ProjectModalPageService projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
    }

    @Test(description = "Create new suite in current project", priority = 1)
    @Description("Create new suite")
    public void checkCreateNewSuiteTest() {
        suiteModalPageService = new SuiteModalPageService();
        Suite suite = Suite.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .preconditions(generateRandomStringExpression()).build();
        suiteModalPageService.createNewSuite(suite, DESCRIPTION_LABEL, PRECONDITIONS_LABEL);
        currentProjectPageService = new CurrentProjectPageService();
        boolean isSuiteDisplayed = currentProjectPageService.isSuiteDisplayed(suite.getName());
        Assert.assertTrue(isSuiteDisplayed, "Suite has not been created");
    }

    @Test(description = "Delete new created suite", priority = 2, invocationCount = 2)
    @Description("Delete new suite")
    public void checkDeleteNewSuiteTest() {
        suiteModalPageService = new SuiteModalPageService();
        Suite suite = Suite.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .preconditions(generateRandomStringExpression()).build();
        suiteModalPageService.createNewSuite(suite, DESCRIPTION_LABEL, PRECONDITIONS_LABEL);
        currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.deleteNewSuite(suite.getName());
        boolean isSuitNotDisplayed = currentProjectPageService.isSuiteNotDisplayed(suite.getName());
        Assert.assertTrue(isSuitNotDisplayed, "Suite has not been deleted");
    }

    @Test(description = "Edit suite name", priority = 3)
    @Description("Edit suite name")
    public void checkEditSuiteNameTest() {
        suiteModalPageService = new SuiteModalPageService();
        Suite suite = Suite.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .preconditions(generateRandomStringExpression()).build();
        suiteModalPageService.createNewSuite(suite, DESCRIPTION_LABEL, PRECONDITIONS_LABEL);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openSuiteModalPage();
        String newSuiteName = generateNewTitle();
        suiteModalPageService.editSuiteTitle(newSuiteName);
        boolean isSuiteDisplayed = currentProjectPageService.isSuiteDisplayed(newSuiteName);
        Assert.assertTrue(isSuiteDisplayed, "Suite's name has not been changed");
    }

    @AfterMethod()
    @Description("Remove all created projects")
    public void cleanUp() {
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeAllProject();
    }
}
