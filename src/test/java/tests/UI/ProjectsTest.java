package tests.UI;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CurrentProjectPage;
import service.CurrentProjectPageService;
import service.LoginPageService;
import service.ProjectsServicePage;
import tests.base.BaseTest;

public class ProjectsTest extends BaseTest {

    private ProjectsServicePage projectsServicePage;

    @BeforeMethod
    @Description("Sign into the www.qase.io")
    public void setUp() {
        LoginPageService loginPageService = new LoginPageService();
        User user = new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!");
        loginPageService.openLoginPage()
                .signIn(user);
    }

    @Test(groups = {"Create new project"})
    @Description("Create new test project with public access")
    public void checkCreateNewPublicProjectTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                    .description("Create new test public project")
                .accessType("Public")
                .build();
        projectsServicePage = new ProjectsServicePage();
        projectsServicePage.createNewPublicProject(project);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        String actualProjectTitle = currentProjectPageService.getProjectTitle();
        String expectedProjectTitle = project.getName();
        Assert.assertEquals(actualProjectTitle, expectedProjectTitle, "Project don't created");
    }

    @Test(groups = {"Create new project"})
    @Description("Create new test project with private access")
    public void checkCreateNewPrivateProjectAsOwnerTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test private project with owner's group members")
                .accessType("Private")
                .membersAccessType("Add members from specific group")
                .build();
        projectsServicePage = new ProjectsServicePage();
        projectsServicePage.createNewPrivateProject(project);
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        String actualProjectTitle = currentProjectPageService.getProjectTitle();
        String expectedProjectTitle = project.getName();
        Assert.assertEquals(actualProjectTitle, expectedProjectTitle, "Project don't created");
    }

    @Test
    @Description("Remove project from project page")
    public void checkRemoveProjectTest() {
        Project project = Project.builder()
                .name(new Faker().name().title())
                .description("Create new test public project")
                .accessType("Public")
                .build();
        projectsServicePage = new ProjectsServicePage();
        projectsServicePage.createNewPublicProject(project);
        CurrentProjectPage currentProjectPage = new CurrentProjectPage();
        currentProjectPage.openProjectsPage();
        projectsServicePage.removeProject(projectsServicePage.getProjectTitle());
        boolean isProjectListIsNotBeenDisplayed = projectsServicePage.isProjectListBeenNotDisplayed();
        Assert.assertTrue(isProjectListIsNotBeenDisplayed, "Projects have not been deleted");
    }

    @AfterMethod(onlyForGroups = {"Create new project"})
    @Description("Delete created test project")
    public void cleanUp() {
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openProjectsPage();
        projectsServicePage = new ProjectsServicePage();
        projectsServicePage.removeProject(projectsServicePage.getProjectTitle());
    }
}
