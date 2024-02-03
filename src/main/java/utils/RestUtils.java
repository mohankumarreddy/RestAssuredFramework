package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {

    public static Response performPost(String endPoint, String requestPayload,
                                       Map<String, String> headers) {

        return RestAssured.given()
                .baseUri(endPoint)
                .headers(headers)
                .auth().none()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .post()
                .then().log().all().extract().response();
    }
}
