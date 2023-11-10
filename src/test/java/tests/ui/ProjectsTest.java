package tests.ui;

import jdk.jfr.Description;
import model.ui.Project;
import model.ui.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ui.CurrentProjectPageService;
import service.ui.LoginPageService;
import service.ui.ProjectModalPageService;
import service.ui.ProjectsPageService;
import tests.base.BaseTest;

import static utils.DataGenerator.generateNewTitle;
import static utils.DataGenerator.generateRandomStringExpression;

public class ProjectsTest extends BaseTest {

    private ProjectModalPageService projectModalPageService;

    @BeforeMethod
    @Description("Sign into the www.qase.io")
    public void setUp() {
        LoginPageService loginPageService = new LoginPageService();
        User user = new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!");
        loginPageService.openLoginPage()
                .signIn(user);
    }

    @Test(description = "Create new test project with public access", priority = 1)
    @Description("Create public test project")
    public void checkCreateNewPublicProjectTest() {
        Project project = Project.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .accessType("Public")
                .build();
        projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        String actualProjectTitle = currentProjectPageService.getProjectTitle();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProjectByTitle(project.getName());
        Assert.assertEquals(actualProjectTitle, project.getName(), "Project don't created");
    }

    @Test(description = "Create new test project with private access", priority = 2)
    @Description("Create new private project")
    public void checkCreateNewPrivateProjectAsOwnerTest() {
        Project project = Project.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .accessType("Private")
                .membersAccessType("Add members from specific group")
                .build();
        projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPrivateProject(project);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        String actualProjectTitle = currentProjectPageService.getProjectTitle();
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProjectByTitle(project.getName());
        Assert.assertEquals(actualProjectTitle, project.getName(), "Project don't created");
    }

    @Test(description = "Remove project from project page", priority = 3)
    @Description("Remove project")
    public void checkRemoveProjectTest() {
        Project project = Project.builder()
                .name(generateNewTitle())
                .description(generateRandomStringExpression())
                .accessType("Public")
                .build();
        projectModalPageService = new ProjectModalPageService();
        projectModalPageService.createNewPublicProject(project);
        ProjectsPageService projectsPageService = new ProjectsPageService();
        projectsPageService.removeProjectByTitle(project.getName());
        boolean isProjectNotDisplayed = projectsPageService.isProjectNotDisplayed(project.getName());
        Assert.assertTrue(isProjectNotDisplayed, "Projects have not been deleted");
    }
}
