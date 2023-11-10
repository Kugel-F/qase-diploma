package service.api;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import model.api.Suite;

@Log4j2
public class SuiteAdapter extends BaseAdapter {

    public static final String SUITE_API_ENDPOINT = "/suite/%s";
    public static final String SUITE_API_DELETE_PARAMS_ENDPOINT = "/suite/%s/";

    public Response createNewSuiteTest(String projectCode, Suite suite) {
        log.info("POST - create new suite: " + suite + " by using project code: " + projectCode);
        return post(String.format(SUITE_API_ENDPOINT, projectCode), convertor.toJson(suite));
    }

    public Response getAllTestSuiteByProjectCode(String code) {
        log.info("GET all tests suite by project code: " + code);
        return get(String.format(SUITE_API_ENDPOINT, code));
    }

    public Response deleteSuite(String code, int id) {
        log.info("DELETE suite by code: " + code + " and id: " + id);
        return delete(String.format(SUITE_API_DELETE_PARAMS_ENDPOINT, code) + id);
    }
}
