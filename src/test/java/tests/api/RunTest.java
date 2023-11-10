package tests.api;

import jdk.jfr.Description;
import model.api.Project;
import model.api.Run;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.api.ProjectAdapter;
import service.api.RunAdapter;

import static utils.Constants.ONE;
import static utils.Constants.TWO;
import static utils.DataGenerator.generateNewCode;
import static utils.DataGenerator.generateNewTitle;

public class RunTest {

    @Test(description = "Create new test run", priority = -1)
    @Description("Create new run")
    public void checkNewRunTest() {
        Project project = Project.builder()
                .title(generateNewTitle())
                .code(generateNewCode())
                .build();
        new ProjectAdapter().createNewProject(project);
        Run run = Run.builder()
                .title(generateNewTitle())
                .build();
        int resultId = new RunAdapter().createNewRun(project.getCode(), run).body().path("result.id");
        new ProjectAdapter().deleteByCode(project.getCode());
        Assert.assertEquals(resultId, ONE, "Test run are not been created");
    }

    @Test(description = "Get all runs", priority = 2)
    @Description("Get all runs")
    public void checkGetAllRunsTest() {
        Project project = Project.builder()
                .title(generateNewTitle())
                .code(generateNewCode())
                .build();
        new ProjectAdapter().createNewProject(project);
        Run firstRun = Run.builder()
                .title(generateNewTitle())
                .build();
        new RunAdapter().createNewRun(project.getCode(), firstRun);
        Run secondRun = Run.builder()
                .title(generateNewTitle())
                .build();
        new RunAdapter().createNewRun(project.getCode(), secondRun);
        int resultTotal = new RunAdapter().getAllRuns(project.getCode()).body().path("result.total");
        new ProjectAdapter().deleteByCode(project.getCode());
        Assert.assertEquals(resultTotal, TWO, "Wrong runs amount is displayed");
    }
}
