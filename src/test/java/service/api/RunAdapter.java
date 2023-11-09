package service.api;

import io.restassured.response.Response;
import model.api.Run;

public class RunAdapter extends BaseAdapter {

    private static final String RUN_API_CREATE_ENDPOINT = "/run/%s";

    public Response createNewRun(String code, Run run) {
        return post(String.format(RUN_API_CREATE_ENDPOINT, code), convertor.toJson(run));
    }

    public Response getAllRuns(String url) {
        return get(String.format(RUN_API_CREATE_ENDPOINT, url));
    }
}
