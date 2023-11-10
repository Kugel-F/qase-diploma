package tests.api;

import jdk.jfr.Description;
import model.api.Project;
import model.api.Suite;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.api.ProjectAdapter;
import service.api.SuiteAdapter;

import static utils.Constants.TWO;
import static utils.Constants.ZERO;
import static utils.DataGenerator.*;

public class SuiteTest {

    @Test(description = "Create suite test", priority = 1)
    @Description("Check create suite test")
    public void checkCreateSuiteIdTest() {
        Project project = Project.builder()
                .title(generateNewTitle())
                .code(generateNewCode())
                .build();
        new ProjectAdapter().createNewProject(project);
        Suite suite = Suite.builder()
                .title(generateNewTitle())
                .description("Test suite")
                .build();
        int resultId = new SuiteAdapter().createNewSuiteTest(project.getCode(), suite).body().path("result.id");
        new ProjectAdapter().deleteByCode(project.getCode());
        Assert.assertTrue(resultId > ZERO, "Wrong id is displayed");
    }

    @Test(description = "Find all test suit by project's name", priority = 3)
    @Description("Find all test suite ")
    public void checkGetTestSuiteByProjectCode() {
        Project project = Project.builder()
                .title(generateNewTitle())
                .code(generateNewCode())
                .build();
        new ProjectAdapter().createNewProject(project);
        Suite firstSuite = Suite.builder()
                .title(generateNewTitle())
                .description(generateRandomStringExpression())
                .build();
        new SuiteAdapter().createNewSuiteTest(project.getCode(), firstSuite);
        Suite secondSuite = Suite.builder()
                .title(generateNewTitle())
                .description("Second suite")
                .build();
        new SuiteAdapter().createNewSuiteTest(project.getCode(), secondSuite);
        int resultTotal = new SuiteAdapter().getAllTestSuiteByProjectCode(project.getCode())
                .body().path("result.total");
        new ProjectAdapter().deleteByCode(project.getCode());
        Assert.assertEquals(resultTotal, TWO, "Wrong suits are displayed");
    }

    @Test(description = "Delete created suite", priority = 2)
    @Description("Delete new created suite")
    public void checkNewSuiteUpdate() {
        Project project = Project.builder()
                .title(generateNewTitle())
                .code(generateNewCode())
                .build();
        new ProjectAdapter().createNewProject(project);
        Suite suite = Suite.builder()
                .title(generateNewTitle())
                .build();
        int suiteId = new SuiteAdapter().createNewSuiteTest(project.getCode(), suite).body().path("result.id");
        boolean resultId = new SuiteAdapter().deleteSuite(project.getCode(), suiteId).body().path("status");
        new ProjectAdapter().deleteByCode(project.getCode());
        Assert.assertTrue(resultId, "Test suite don't been deleted");
    }
}
