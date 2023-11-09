package tests.api;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.api.Project;
import model.api.Run;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.api.ProjectAdapter;
import service.api.RunAdapter;

import static utils.Constants.*;

public class RunTest {

    @BeforeMethod(description = "Create new project")
    public void setUp() {
        Project project = Project.builder()
                .title(new Faker().name().title())
                .code("TEST")
                .build();
        new ProjectAdapter().createNewProject(project);
    }

    @Test(description = "Create new test run", priority = -1)
    @Description("Create new run")
    public void checkNewRunTest() {
        Run run = Run.builder()
                .title(new Faker().name().title())
                .build();
        int resultId = new RunAdapter().createNewRun("TEST", run).body().path("result.id");
        Assert.assertEquals(resultId, ONE, "Test run are not been created");
    }

    @Test(description = "Get all runs", priority = 2)
    @Description("Get all runs")
    public void checkGetAllRunsTest() {
        Run firstRun = Run.builder()
                .title(new Faker().name().title())
                .build();
        new RunAdapter().createNewRun("TEST", firstRun);
        Run secondRun = Run.builder()
                .title(new Faker().name().title())
                .build();
        new RunAdapter().createNewRun("TEST", secondRun);
        int resultTotal = new RunAdapter().getAllRuns("TEST").body().path("result.total");
        Assert.assertEquals(resultTotal, TWO, "Wrong runs amount is displayed");
    }

    @AfterMethod(description = "Remove project")
    public void cleanUp() {
        new ProjectAdapter().deleteByCode("TEST");
    }
}
