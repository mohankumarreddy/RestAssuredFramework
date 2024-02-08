package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentReportManager;

import java.util.Map;

public class RestUtils {

    private static RequestSpecification getRequestSpecification (String endPoint, Object requestPayload,
                                                                 Map<String, String> headers) {

         return RestAssured.given()
                .baseUri(endPoint)
                .headers(headers)
                .auth().none()
                .contentType(ContentType.JSON)
                .body(requestPayload);
    }

    private static RequestSpecification getRequestSpecification (String endPoint,
                                                                 Map<String, String> headers) {

        return RestAssured.given()
                .baseUri(endPoint)
                .headers(headers)
                .auth().none();
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint : " + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method : " + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Request Headers : ");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request Body : ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
    }

    private static void printResponseLogInReport(Response response) {
        ExtentReportManager.logInfoDetails("Response Status : " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response Headers : ");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Response Body : ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
    }

    public static Response performPost(String endPoint, String requestPayload,
                                       Map<String, String> headers) {

        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performPost(String endPoint, Map<String, Object> requestPayload,
                                       Map<String, String> headers) {

        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performGet(String endPoint,
                                      Map<String, String> headers) {

        RequestSpecification requestSpecification = getRequestSpecification(endPoint, headers);
        Response response = requestSpecification.get();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
}
