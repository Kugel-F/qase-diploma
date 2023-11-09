package tests.api;

import com.github.javafaker.Faker;
import jdk.jfr.Description;
import model.api.Project;
import model.api.TestCase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.api.ProjectAdapter;
import service.api.TestCasesAdapter;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.Constants.*;

public class CasesTest {

    @BeforeMethod(description = "Create new project")
    public void setUp() {
        Project project = Project.builder()
                .title(new Faker().name().title())
                .code("TEST")
                .build();
        new ProjectAdapter().createNewProject(project);
    }

    @Test(description = "Check creating new test case", priority = 1)
    @Description("Create new test case")
    public void checkCreateNewTestCaseTest() {
        TestCase testCase = TestCase.builder()
                .title(new Faker().name().title())
                .description("Some description")
                .preconditions("Some preconditions")
                .postconditions("Some postconditions")
                .severity(TWO)
                .priority(ONE)
                .build();
        int resultId = new TestCasesAdapter().createNewTestCase(testCase).body().path("result.id");
        Assert.assertTrue(resultId > ZERO, "Test case has not been created");
    }

    @Test(description = "Check new test case can be updated", priority = 2)
    @Description("Update new test case")
    public void checkUpdateTestCaseTest() {
        TestCase testCase = TestCase.builder()
                .title(new Faker().name().title())
                .description("Create new suite case")
                .priority(ONE)
                .severity(TWO)
                .build();
        new TestCasesAdapter().createNewTestCase(testCase);
        TestCase testCaseToUpdate = TestCase.builder()
                .description("update test case description")
                .priority(ZERO)
                .severity(THREE)
                .build();
        boolean resulStatus = new TestCasesAdapter().updateNewTestCase(Project.builder().build(),testCaseToUpdate)
                .body().path("status");
        Assert.assertTrue(resulStatus, "Test case has not been updated");
    }

    @Test(description = "Check new test case can be deleted", priority = 3)
    @Description("Delete new test case")
    public void checkDeleteTestCaseTest() {
        TestCase testCase = TestCase.builder()
                .description("Create new suite case")
                .title(new Faker().name().title())
                .build();
        int testcaseId =  new TestCasesAdapter().createNewTestCase(testCase).body().path("result.id");
        int statusCode = new TestCasesAdapter().deleteTestCase("TEST", testcaseId).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Test case has not been deleted");
    }

    @AfterMethod(description = "Remove project")
    public void cleanUp() {
        new ProjectAdapter().deleteByCode("TEST");
    }
}