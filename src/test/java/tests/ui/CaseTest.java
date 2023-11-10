package tests.ui;

import jdk.jfr.Description;
import model.ui.Case;
import model.ui.Project;
import model.ui.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ui.*;
import tests.base.BaseTest;

import static utils.Constants.CONFIRM;
import static utils.Constants.TWO;
import static utils.DataGenerator.generateNewTitle;
import static utils.DataGenerator.generateRandomStringExpression;

public class CaseTest extends BaseTest {

    private CreateCasePageService createCasePageService;

    @BeforeMethod
    @Description("Sign in www.qase.com")
    public void setUp() {
        LoginPageService loginPageService = new LoginPageService();
        loginPageService.openLoginPage();
        loginPageService.signIn(new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!"));
    }

    @Test(description = "Create new test case without all required attributes", priority = 1)
    @Description("Create test case without required attributes")
    public void checkCreateNewTestCaseWithoutAllRequiredAttributesTest() {
        Project project = Project.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .accessType("Public")
                .build();
        ProjectModalPageService projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        Case testCase = Case.builder()
                .title(generateNewTitle())
                .build();
        createCasePageService = new CreateCasePageService();
        createCasePageService.createTestCaseWithoutAllRequireAttribute(testCase);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        String actualCaseTitle = currentProjectPageService.getNewTestCaseTitle();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProject(project.getName());
        Assert.assertEquals(actualCaseTitle, testCase.getTitle(), "Test case don't been created");
    }

    @Test(description = "Check current project page backspace possibility", priority = 4)
    @Description("Check backspace possibility")
    public void checkBackspacePossibilityTest() {
        Project project = Project.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .accessType("Public")
                .build();
        ProjectModalPageService projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openCreateCaseForm();
        createCasePageService = new CreateCasePageService();
        createCasePageService.clickBackspaceButton();
        boolean isNewCaseButtonDisplayed = currentProjectPageService.isNewCaseButtonDisplayed();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProject(project.getName());
        Assert.assertTrue(isNewCaseButtonDisplayed, "Current project page is not opened");
    }

    @Test(description = "Create two test cases", priority = 3)
    @Description("Create two test cases")
    public void checkCreateTwoTestCasesTest() {
        Project project = Project.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .accessType("Public")
                .build();
        ProjectModalPageService projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        Case firstTestCase = Case.builder()
                .title(generateNewTitle())
                .build();
        createCasePageService = new CreateCasePageService();
        createCasePageService.createTestCaseWithoutAllRequireAttribute(firstTestCase);
        Case secondTestCase = Case.builder()
                .title(generateNewTitle())
                .build();
        createCasePageService.createTestCaseWithoutAllRequireAttribute(secondTestCase);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        int actualTestCasesAmount = currentProjectPageService.getTestCasesAmount();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProject(project.getName());
        Assert.assertEquals(actualTestCasesAmount, TWO, "Wrong test cases amount were created");
    }

    @Test(description = "Check delete test case by name", priority = 2, invocationCount = 2)
    @Description("Delete test case")
    public void checkDeleteTestCaseByNameTest() {
        Project project = Project.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .accessType("Public")
                .build();
        ProjectModalPageService projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        Case testCase = Case.builder()
                .title(generateNewTitle())
                .build();
        createCasePageService = new CreateCasePageService();
        createCasePageService.createTestCaseWithoutAllRequireAttribute(testCase);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.deleteTestCase(testCase.getTitle(), CONFIRM);
        boolean isTestCasesTitleNotDisplayed = currentProjectPageService.isNotDisplayed();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProject(project.getName());
        Assert.assertTrue(isTestCasesTitleNotDisplayed, "Wrong test cases amount were created");
    }
}
