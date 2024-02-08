package bookStore;

import io.restassured.response.Response;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;

public class BookStoreAPIs {

    public Response createActivities(Map<String, Object> createActivitiesPayload) {

        String url = (String) BaseTest.dataFromJSONFile.get("baseURI");
        String endPoint = url+"/api/v1/Activities";
        return RestUtils.performPost(endPoint, createActivitiesPayload, new HashMap<>());

    }

    public Response getAllActivities() {

        String endPoint = (String) BaseTest.dataFromJSONFile.get("baseURI");
        return RestUtils.performGet(endPoint, new HashMap<>());
    }
}
