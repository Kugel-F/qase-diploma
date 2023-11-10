package tests.api;

import jdk.jfr.Description;
import model.api.Project;
import model.api.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.api.ProjectAdapter;
import service.api.TestCasesAdapter;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.Constants.*;
import static utils.DataGenerator.*;

public class CasesTest {

    @Test(description = "Check creating new test case", priority = 1)
    @Description("Create new test case")
    public void checkCreateNewTestCaseTest() {
        Project project = Project.builder()
                .title(generateNewTitle())
                .code(generateNewCode())
                .build();
        new ProjectAdapter().createNewProject(project);
        TestCase testCase = TestCase.builder()
                .title(generateNewTitle())
                .description(generateRandomStringExpression())
                .preconditions(generateRandomStringExpression())
                .postconditions(generateRandomStringExpression())
                .severity(TWO)
                .priority(ONE)
                .build();
        int resultId = new TestCasesAdapter().createNewTestCase(project.getCode(), testCase)
                .body().path("result.id");
        new ProjectAdapter().deleteByCode(project.getCode());
        Assert.assertTrue(resultId > ZERO, "Test case has not been created");
    }

    @Test(description = "Check new test case can be updated", priority = 2)
    @Description("Update new test case")
    public void checkUpdateTestCaseTest() {
        Project project = Project.builder()
                .title(generateNewTitle())
                .code(generateNewCode())
                .build();
        new ProjectAdapter().createNewProject(project);
        TestCase testCase = TestCase.builder()
                .title(generateNewTitle())
                .description(generateRandomStringExpression())
                .priority(ONE)
                .severity(TWO)
                .build();
        int resultTestCaseId = new TestCasesAdapter().createNewTestCase(project.getCode(), testCase).body()
                .path("result.id");
        TestCase testCaseToUpdate = TestCase.builder()
                .description(generateRandomStringExpression())
                .priority(ZERO)
                .severity(THREE)
                .build();
        boolean resulStatus = new TestCasesAdapter().updateNewTestCase(project.getCode(), resultTestCaseId,
                        testCaseToUpdate)
                .body().path("status");
        new ProjectAdapter().deleteByCode(project.getCode());
        Assert.assertTrue(resulStatus, "Test case has not been updated");
    }

    @Test(description = "Check new test case can be deleted", priority = 3)
    @Description("Delete new test case")
    public void checkDeleteTestCaseTest() {
        Project project = Project.builder()
                .title(generateNewTitle())
                .code(generateNewCode())
                .build();
        new ProjectAdapter().createNewProject(project);
        TestCase testCase = TestCase.builder()
                .description(generateRandomStringExpression())
                .title(generateNewTitle())
                .build();
        int testcaseId = new TestCasesAdapter().createNewTestCase(project.getCode(), testCase).body()
                .path("result.id");
        int statusCode = new TestCasesAdapter().deleteTestCase(project.getCode(), testcaseId).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Test case has not been deleted");
    }
}