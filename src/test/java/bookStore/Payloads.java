package bookStore;

import java.util.HashMap;
import java.util.Map;

public class Payloads {

    public static String getCreateActivitiesUsingString(String id, String title,
                                                        String dueDate,
                                             String completed) {

        String payload = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"title\": \""+title+"\",\n" +
                "  \"dueDate\": \""+dueDate+"\",\n" +
                "  \"completed\": "+completed+"\n" +
                "}";
        return payload;
    }

    public static Map<String, Object> getCreateActivitiesUsingMap(String id, String title,
                                                                  String dueDate,
                                                        Boolean completed) {

        Map<String, Object> payload = new HashMap<>();

        payload.put("id", id);
        payload.put("title", title);
        payload.put("dueDate", dueDate);
        payload.put("completed", completed);
        return payload;
    }
}
