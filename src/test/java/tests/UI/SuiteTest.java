package tests.UI;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.Project;
import model.Suite;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.CurrentProjectPageService;
import service.LoginPageService;
import service.ProjectsServicePage;
import service.SuiteModalPageService;
import tests.base.BaseTest;

import static page.SuiteModalPage.DESCRIPTION_LABEL;
import static page.SuiteModalPage.PRECONDITIONS_LABEL;

public class SuiteTest extends BaseTest {

    private SuiteModalPageService suiteModalPageService;

    @BeforeMethod
    @Description("Sign in www.qase.com, create new project")
    public void setUp() {
        LoginPageService loginPageService = new LoginPageService();
        loginPageService.openLoginPage();
        loginPageService.signIn(new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!"));
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        ProjectsServicePage projectsServicePage = new ProjectsServicePage();
        projectsServicePage.createNewPublicProject(project);
    }

    @Test(description = "Create new suite in current project", groups = {"Create new project"})
    @Description("Create new suite")
    public void checkCreateNewSuiteTest() {
        suiteModalPageService = new SuiteModalPageService();
        Suite suite = Suite.builder()
                .name(new Faker().name().title())
                .description("New test suite")
                .preconditions("Some preconditions").build();
        suiteModalPageService.createNewSuite(suite, DESCRIPTION_LABEL, PRECONDITIONS_LABEL);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        boolean isSuiteDisplayed = currentProjectPageService.isSuiteDisplayed(suite.getName());
        System.out.println(isSuiteDisplayed);
        Assert.assertTrue(isSuiteDisplayed, "Suite has not been created");
    }

    @Test(description = "Delete new created suite", groups = {"Create new project"})
    @Description("Delete new suite")
    public void checkDeleteNewSuiteTest() {
        suiteModalPageService = new SuiteModalPageService();
        Suite suite = Suite.builder()
                .name(new Faker().name().title())
                .description("New test suite")
                .preconditions("Some preconditions").build();
        suiteModalPageService.createNewSuite(suite, DESCRIPTION_LABEL, PRECONDITIONS_LABEL);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.deleteNewSuite(suite.getName());
        boolean isSuitNotDisplayed = currentProjectPageService.isSuiteIsNotDisplayed(suite.getName());
        System.out.println(isSuitNotDisplayed);
        Assert.assertTrue(isSuitNotDisplayed, "Suite has not been deleted");
    }
}
