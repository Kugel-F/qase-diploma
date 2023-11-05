package tests.UI;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.CurrentProjectPageService;
import service.LoginPageService;
import service.ProjectsPageService;
import tests.base.BaseTest;

public class ProjectsTest extends BaseTest {

    private ProjectsPageService projectsPageService;
    private CurrentProjectPageService currentProjectPageService;

    @BeforeMethod
    @Description("Sign into the www.qase.io")
    public void setUp() {
        LoginPageService loginPageService = new LoginPageService();
        User user = new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!");
        loginPageService.openLoginPage()
                .signIn(user);
    }

    @Test(description = "Create new test project with public access")
    @Description("Create public test project")
    public void checkCreateNewPublicProjectTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        projectsPageService = new ProjectsPageService();
        projectsPageService.createNewPublicProject(project);
        currentProjectPageService = new CurrentProjectPageService();
        String actualProjectTitle = currentProjectPageService.getProjectTitle();
        projectsPageService.removeProject(project.getName());
        Assert.assertEquals(actualProjectTitle, project.getName(), "Project don't created");
    }

    @Test(description = "Create new test project with private access")
    @Description("Create new private project")
    public void checkCreateNewPrivateProjectAsOwnerTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test private project with owner's group members")
                .accessType("Private")
                .membersAccessType("Add members from specific group")
                .build();
        projectsPageService = new ProjectsPageService();
        projectsPageService.createNewPrivateProject(project);
        currentProjectPageService = new CurrentProjectPageService();
        String actualProjectTitle = currentProjectPageService.getProjectTitle();
        projectsPageService.removeProject(project.getName());
        Assert.assertEquals(actualProjectTitle, project.getName(), "Project don't created");
    }

    @Test(description = "Remove project from project page")
    @Description("Remove project")
    public void checkRemoveProjectTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        projectsPageService = new ProjectsPageService();
        projectsPageService.createNewPublicProject(project);
        currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openProjectsPage();
        projectsPageService.removeProject(project.getName());
        boolean isProjectNotDisplayed = projectsPageService.isProjectNotDisplayed(project.getName());
        Assert.assertTrue(isProjectNotDisplayed, "Projects have not been deleted");
    }
}
