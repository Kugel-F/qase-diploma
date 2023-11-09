package service.api;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import model.api.Project;

@Log4j2
public class ProjectAdapter extends BaseAdapter {

    private static final String PROJECT_API_ENDPOINT = "/project";
    private static final String PARAMETER_API_ENDPOINT = PROJECT_API_ENDPOINT + "/%s";

    public Response getAllProjects() {
        return get(PROJECT_API_ENDPOINT);
    }

    public Response getProjectsByParams(String code) {
        return get(String.format(PARAMETER_API_ENDPOINT, code));
    }

    public Response createNewProject(Project project) {
        return post(PROJECT_API_ENDPOINT, convertor.toJson(project));
    }

    public Response deleteByCode(String url) {
        return delete(String.format(PARAMETER_API_ENDPOINT, url));
    }
}
