package tests.UI;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.Case;
import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.*;
import tests.base.BaseTest;

import static utils.Constants.*;

public class CaseTest extends BaseTest {

    private CreateCasePageService createCasePageService;

    @BeforeMethod
    @Description("Sign in www.qase.com")
    public void setUp() {
        LoginPageService loginPageService = new LoginPageService();
        loginPageService.openLoginPage();
        loginPageService.signIn(new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!"));
    }

    @Test(description = "Create new test case without all required attributes")
    @Description("Create test case without required attributes")
    public void checkCreateNewTestCaseWithoutAllRequiredAttributesTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.createNewPublicProject(project);
        Case testCase = Case.builder()
                .title(new Faker().name().title())
                .build();
        createCasePageService = new CreateCasePageService();
        createCasePageService.createTestCaseWithoutAllRequireAttribute(testCase);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        String actualCaseTitle = currentProjectPageService.getNewTestCaseTitle();
        projectsPageService.removeProject(project.getName());
        Assert.assertEquals(actualCaseTitle, testCase.getTitle(), "Test case don't been created");
    }

    @Test(description = "Check current project page backspace possibility")
    @Description("Check backspace possibility")
    public void checkBackspacePossibilityTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.createNewPublicProject(project);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openCreateCaseForm();
        createCasePageService = new CreateCasePageService();
        createCasePageService.clickBackspaceButton();
        boolean isNewCaseButtonDisplayed = currentProjectPageService.isNewCaseButtonDisplayed();
        projectsPageService.removeProject(project.getName());
        Assert.assertTrue(isNewCaseButtonDisplayed, "Current project page is not opened");
    }

    @Test(description = "Create two test cases")
    @Description("Create two test cases")
    public void checkCreateTwoTestCasesTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.createNewPublicProject(project);
        Case firstTestCase = Case.builder()
                .title(new Faker().name().title())
                .build();
        createCasePageService = new CreateCasePageService();
        createCasePageService.createTestCaseWithoutAllRequireAttribute(firstTestCase);
        Case secondTestCase = Case.builder()
                .title(new Faker().name().title())
                .build();
        createCasePageService.createTestCaseWithoutAllRequireAttribute(secondTestCase);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        int actualTestCasesAmount = currentProjectPageService.getTestCasesAmount();
        projectsPageService.removeProject(project.getName());
        Assert.assertEquals(actualTestCasesAmount, TWO, "Wrong test cases amount were created");
    }

    @Test(description = "Check delete test case by name")
    @Description("Delete test case")
    public void checkDeleteTestCaseByNameTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.createNewPublicProject(project);
        Case testCase = Case.builder()
                .title(new Faker().name().title())
                .build();
        createCasePageService = new CreateCasePageService();
        createCasePageService.createTestCaseWithoutAllRequireAttribute(testCase);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.deleteTestCase(testCase.getTitle(), CONFIRM);
        boolean isTestCasesTitleNotDisplayed = currentProjectPageService.isNotDisplayed();
        projectsPageService.removeProject(project.getName());
        Assert.assertTrue(isTestCasesTitleNotDisplayed, "Wrong test cases amount were created");
    }
}
