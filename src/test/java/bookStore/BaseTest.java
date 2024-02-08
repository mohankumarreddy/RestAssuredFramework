package bookStore;

import utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class BaseTest {

    public static Map<String,Object> dataFromJSONFile;

    static {
        String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        try {
            dataFromJSONFile = JsonUtils.getJsonDataAsMap("bookstore/"+env+
                    "/bookstoreApiData.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
