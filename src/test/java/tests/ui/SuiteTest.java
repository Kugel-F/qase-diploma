package tests.ui;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.ui.Project;
import model.ui.Suite;
import model.ui.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ui.*;
import tests.base.BaseTest;

import static page.SuiteModalPage.DESCRIPTION_LABEL;
import static page.SuiteModalPage.PRECONDITIONS_LABEL;

public class SuiteTest extends BaseTest {

    private SuiteModalPageService suiteModalPageService;
    private CurrentProjectPageService currentProjectPageService;

    @BeforeMethod
    @Description("Sign in www.qase.io")
    public void setUp() {
        LoginPageService loginPageService = new LoginPageService();
        loginPageService.openLoginPage();
        loginPageService.signIn(new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!"));
    }

    @Test(description = "Create new suite in current project", priority = 1)
    @Description("Create new suite")
    public void checkCreateNewSuiteTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        ProjectModalPageService projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        suiteModalPageService = new SuiteModalPageService();
        Suite suite = Suite.builder()
                .name(new Faker().name().title())
                .description("New test suite")
                .preconditions("Some preconditions").build();
        suiteModalPageService.createNewSuite(suite, DESCRIPTION_LABEL, PRECONDITIONS_LABEL);
        currentProjectPageService = new CurrentProjectPageService();
        boolean isSuiteDisplayed = currentProjectPageService.isSuiteDisplayed(suite.getName());
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProject(project.getName());
        Assert.assertTrue(isSuiteDisplayed, "Suite has not been created");
    }

    @Test(description = "Delete new created suite", priority = 2, invocationCount = 2)
    @Description("Delete new suite")
    public void checkDeleteNewSuiteTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        ProjectModalPageService projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        suiteModalPageService = new SuiteModalPageService();
        Suite suite = Suite.builder()
                .name(new Faker().name().title())
                .description("New test suite")
                .preconditions("Some preconditions").build();
        suiteModalPageService.createNewSuite(suite, DESCRIPTION_LABEL, PRECONDITIONS_LABEL);
        currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.deleteNewSuite(suite.getName());
        boolean isSuitNotDisplayed = currentProjectPageService.isSuiteNotDisplayed(suite.getName());
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProject(project.getName());
        Assert.assertTrue(isSuitNotDisplayed, "Suite has not been deleted");
    }

    @Test(description = "Edit suite name", priority = 3)
    @Description("Edit suite name")
    public void checkEditSuiteNameTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        ProjectModalPageService projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        suiteModalPageService = new SuiteModalPageService();
        Suite suite = Suite.builder()
                .name(new Faker().name().title())
                .description("New test suite")
                .preconditions("Some preconditions").build();
        suiteModalPageService.createNewSuite(suite, DESCRIPTION_LABEL, PRECONDITIONS_LABEL);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openSuiteModalPage();
        String newSuiteName = new Faker().name().title();
        suiteModalPageService.editSuiteTitle(newSuiteName);
        boolean isSuiteDisplayed = currentProjectPageService.isSuiteDisplayed(newSuiteName);
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProject(project.getName());
        Assert.assertTrue(isSuiteDisplayed, "Suite's name has not been changed");
    }
}
