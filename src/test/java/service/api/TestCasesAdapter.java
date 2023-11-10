package service.api;

import io.restassured.response.Response;
import model.api.TestCase;

public class TestCasesAdapter extends BaseAdapter {

    private static final String TEST_CASE_CREATE_POINT = "/case/%s";
    private static final String TEST_CASE_PARAMS_POINT = "/case/%s/";

    public Response createNewTestCase(String url, TestCase testCase) {
        return post(String.format(TEST_CASE_CREATE_POINT, url), convertor.toJson(testCase));
    }

    public Response updateNewTestCase(String code, int id, TestCase testCase) {
        return patch(String.format(TEST_CASE_PARAMS_POINT, code) + id, convertor.toJson(testCase));
    }

    public Response deleteTestCase(String code, int id) {
        return delete(String.format(TEST_CASE_PARAMS_POINT, code) + id);
    }
}
