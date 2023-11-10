package service.api;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import model.api.Run;

@Log4j2
public class RunAdapter extends BaseAdapter {

    private static final String RUN_API_CREATE_ENDPOINT = "/run/%s";

    public Response createNewRun(String code, Run run) {
        log.info("POST - create new run: " + run + "by using code: " + code);
        return post(String.format(RUN_API_CREATE_ENDPOINT, code), convertor.toJson(run));
    }

    public Response getAllRuns(String url) {
        log.info("GET all projects by using url: " + url);
        return get(String.format(RUN_API_CREATE_ENDPOINT, url));
    }
}
