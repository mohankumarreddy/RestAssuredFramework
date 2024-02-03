package bookStore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtils;

import java.util.HashMap;

public class BookstoreTests {

    @Test
    public void getAllActivities() {

        Response res = RestAssured.given()
                .baseUri("https://fakerestapi.azurewebsites.net")
                .auth().none()
                .contentType(ContentType.JSON)
                .param("/api/v1/Activities")
                .when()
                .get()
                .then().log().all().extract().response();
        Assert.assertEquals(res.statusCode(), 200);
    }

    @Test
    public void postActivities() {

        String endPoint = "https://fakerestapi.azurewebsites.net/api/v1/Activities";
        String requestPayload = "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"string\",\n" +
                "  \"dueDate\": \"2024-02-03T03:16:47.549Z\",\n" +
                "  \"completed\": true\n" +
                "}";

        Response res = RestUtils.performPost(endPoint, requestPayload, new HashMap<>());
        Assert.assertEquals(res.statusCode(), 200);
    }
}
