package bookStore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;
import utils.RestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookstoreTests {

    @Test
    public void getAllActivities() throws IOException{

        Response res = RestAssured.given()
                .baseUri("https://fakerestapi.azurewebsites.net/")
                .auth().none()
                .contentType(ContentType.JSON)
                .param("api/v1/Activities")
                .when()
                .get()
                .then().log().all().extract().response();
        Assert.assertEquals(res.statusCode(), 200);
    }

    @Test
    public void postActivities() throws IOException {

        String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        Map<String, String> data = JsonUtils.getJsonDataAsMap("bookstore/"+env+
                "/bookstoreApiData.json");
        String url = data.get("baseURI");
        String endPoint = url+"/api/v1/Activities";

        //String endPoint = "https://fakerestapi.azurewebsites.net/api/v1/Activities";

        // payload using String

        /* String requestPayload = Payloads.getCreateActivitiesUsingString("0","string",
                "2024-02-03T03:16:47.549Z","true");*/

        // payload using Map
        Map<String, Object> requestPayload = Payloads.getCreateActivitiesUsingMap("0","string",
                "2024-02-03T03:16:47.549Z",true);

                /*"{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"string\",\n" +
                "  \"dueDate\": \"2024-02-03T03:16:47.549Z\",\n" +
                "  \"completed\": true\n" +
                "}";*/

        Response res = RestUtils.performPost(endPoint, requestPayload, new HashMap<>());
        Assert.assertEquals(res.statusCode(), 200);
    }
}
