package tests.api;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.api.Project;
import model.api.Suite;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.api.ProjectAdapter;
import service.api.SuiteAdapter;

import static utils.Constants.*;

public class SuiteTest {

    @BeforeMethod(description = "Create new project")
    public void setUp() {
        Project project = Project.builder()
                .title(new Faker().name().title())
                .code("TEST")
                .build();
        new ProjectAdapter().createNewProject(project);
    }

    @Test(description = "Create suite test", priority = 1)
    @Description("Check create suite test")
    public void checkCreateSuiteIdTest() {
        Suite suite = Suite.builder()
                .title(new Faker().name().title())
                .description("Test suite")
                .build();
        int resultId = new SuiteAdapter().createNewSuiteTest("TEST", suite).body().path("result.id");
        Assert.assertTrue(resultId > ZERO, "Wrong id is displayed");

    }

    @Test(description = "Find all test suit by project's name", priority = 3)
    @Description("Find all test suite ")
    public void checkGetTestSuiteByProjectCode() {
        Suite firstSuite = Suite.builder()
                .title(new Faker().name().title())
                .description("First suite")
                .build();
        new SuiteAdapter().createNewSuiteTest("TEST", firstSuite);
        Suite secondSuite = Suite.builder()
                .title(new Faker().name().title())
                .description("Second suite")
                .build();
        new SuiteAdapter().createNewSuiteTest("TEST", secondSuite);
        int resultTotal = new SuiteAdapter().getAllTestSuiteByProjectCode("TEST").body().path("result.total");
        Assert.assertEquals(resultTotal, TWO, "Wrong suits are displayed");
    }

    @Test(description = "Delete created suite", priority = 2)
    @Description("Delete new created suite")
    public void checkNewSuiteUpdate() {
        Suite suite = Suite.builder()
                .title(new Faker().name().title())
                .build();
        int suiteId = new SuiteAdapter().createNewSuiteTest("TEST", suite).body().path("result.id");
        boolean resultId = new SuiteAdapter().deleteSuite("TEST", suiteId).body().path("status");
        Assert.assertTrue(resultId, "Test suite don't been deleted");
    }

    @AfterMethod(description = "Remove project")
    public void cleanUp() {
        new ProjectAdapter().deleteByCode("TEST");
    }
}
