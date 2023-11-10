package service.api;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import model.api.TestCase;

@Log4j2
public class TestCasesAdapter extends BaseAdapter {

    private static final String TEST_CASE_CREATE_POINT = "/case/%s";
    private static final String TEST_CASE_PARAMS_POINT = "/case/%s/";

    public Response createNewTestCase(String url, TestCase testCase) {
        log.info("POST - create new test case: " + testCase + " with url: " + url);
        return post(String.format(TEST_CASE_CREATE_POINT, url), convertor.toJson(testCase));
    }

    public Response updateNewTestCase(String code, int id, TestCase testCase) {
        log.info("PATCH - update test case: " + testCase + " with project code: " + code + " and case id: " + id);
        return patch(String.format(TEST_CASE_PARAMS_POINT, code) + id, convertor.toJson(testCase));
    }

    public Response deleteTestCase(String code, int id) {
        log.info("DELETE test case with id: " + id + " in project with code: " + code);
        return delete(String.format(TEST_CASE_PARAMS_POINT, code) + id);
    }
}
