package service.api;

import io.restassured.response.Response;
import model.api.Suite;

public class SuiteAdapter extends BaseAdapter {

    public static final String SUITE_API_ENDPOINT = "/suite/%s";
    public static final String SUITE_API_DELETE_PARAMS_ENDPOINT = "/suite/%s/";

    public Response createNewSuiteTest(String projectCode, Suite suite) {
        return post(String.format(SUITE_API_ENDPOINT, projectCode), convertor.toJson(suite));
    }

    public Response getAllTestSuiteByProjectCode(String code) {
        return get(String.format(SUITE_API_ENDPOINT, code));
    }

    public Response deleteSuite(String code, int id) {
        return delete(String.format(SUITE_API_DELETE_PARAMS_ENDPOINT, code) + id);
    }
}
