package service.api;

import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.Constants.*;

public class BaseAdapter {

    private static final String TOKEN_VALUE = "5610a4783ab8cc2c690ff3e1475a4512bc54dc4b32598856f986bfae80ac70c2";
    private static final String BASE_URL = "https://api.qase.io/v1";
    protected Gson convertor = new Gson();

    public Response get(String url) {
        return given()
                .log().all()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .when()
                .get(BASE_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    public Response post(String url, String body) {
        return
                given()
                        .log().all()
                        .header(TOKEN_NAME, TOKEN_VALUE)
                        .header(CONTENT_TYPE, JSON)
                        .body(body)
                        .when()
                        .post(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract().response();
    }

    public Response delete(String url) {
        return
                given()
                        .log().all()
                        .header(TOKEN_NAME, TOKEN_VALUE)
                        .header(CONTENT_TYPE, JSON)
                        .when()
                        .delete(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract().response();
    }

    public Response patch(String url, String body) {
        return
                given()
                        .log().all()
                        .header(TOKEN_NAME, TOKEN_VALUE)
                        .header(CONTENT_TYPE, JSON)
                        .body(body)
                        .when()
                        .patch(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract().response();
    }
}
