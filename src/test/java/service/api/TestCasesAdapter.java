package service.api;

import io.restassured.response.Response;
import model.api.Project;
import model.api.TestCase;

public class TestCasesAdapter extends BaseAdapter {

    private static final String TEST_CASE_CREATE_POINT = "/case/TEST";
    private static final String UPDATE_FIRST_TEST_CASE_END_POINT = "/case/TEST/1";
    private static final String TEST_CASE_DELETE_POINT = "/case/%s/";

    public Response createNewTestCase(TestCase testCase) {
        return post(TEST_CASE_CREATE_POINT, convertor.toJson(testCase));
    }

    public Response updateNewTestCase(Project project, TestCase testCase) {
        return patch(convertor.toJson(project), convertor.toJson(testCase), UPDATE_FIRST_TEST_CASE_END_POINT);
    }

    public Response deleteTestCase(String code, int id) {
        return delete(String.format(TEST_CASE_DELETE_POINT, code) + id);
    }
}
