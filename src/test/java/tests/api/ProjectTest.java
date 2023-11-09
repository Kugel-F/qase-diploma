package tests.api;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.api.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.api.ProjectAdapter;

import static java.net.HttpURLConnection.HTTP_OK;

public class ProjectTest {

    @Test(description = "Get project's status code")
    @Description("Get all the projects status code")
    public void checkGetAllProjectsStatusCode() {
        int statusCode = new ProjectAdapter().getAllProjects().statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Wrong status code is displayed");
    }

    @Test(description = "Create new project", priority = -1)
    @Description("Check creation new project")
    public void checkCreationNewProjectTest() {
        Project project = Project.builder()
                .title(new Faker().name().title())
                .code("TEST")
                .build();
        String statusResultCode = new ProjectAdapter().createNewProject(project).body().path("result.code");
        new ProjectAdapter().deleteByCode(project.getCode());
        Assert.assertEquals(statusResultCode, project.getCode(), "Wrong code is displayed");
    }

    @Test(description = "Delete project")
    @Description("Check deleting new project")
    public void checkDeleteByCodeTest() {
        Project project = Project.builder()
                .title(new Faker().name().title())
                .code("TEST")
                .build();
        new ProjectAdapter().createNewProject(project);
        int statusCode = new ProjectAdapter().deleteByCode(project.getCode()).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Project has not been deleted");
    }

    @Test(description = "Select project by code")
    @Description("Select project by code")
    public void checkSelectProjectByCodeTest() {
        Project firstProject = Project.builder()
                .title(new Faker().name().title())
                .code("TEST")
                .build();
        new ProjectAdapter().createNewProject(firstProject);
        Project secondProject = Project.builder()
                .title(new Faker().name().title())
                .code("CODE")
                .build();
        new ProjectAdapter().createNewProject(secondProject);
        String selectProjectTitle = new ProjectAdapter().getProjectsByParams(secondProject.getCode()).body()
                .path("result.title");
        new ProjectAdapter().deleteByCode(firstProject.getCode());
        new ProjectAdapter().deleteByCode(secondProject.getCode());
        Assert.assertEquals(selectProjectTitle, secondProject.getTitle(), "Project has not been selected");
    }
}
