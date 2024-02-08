package bookStore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class BookstoreTests extends BookStoreAPIs {

    @Test
    public void getActivities() throws IOException{

        Response res = getAllActivities();
        Assert.assertEquals(res.statusCode(), 200);
    }

    @Test
    public void postActivities() throws IOException {

        // payload using Map
        Map<String, Object> requestPayload = Payloads.getCreateActivitiesUsingMap("0","string",
                "2024-02-03T03:16:47.549Z",true);

        Response res = createActivities(requestPayload);
        Assert.assertEquals(res.statusCode(), 200);
    }
}
