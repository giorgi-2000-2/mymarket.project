package org.example.utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.example.utils.ExtentReportManager.getTest;

public class APIManager {

    public APIManager(String baseuri) {
        RestAssured.baseURI = baseuri;
    }

    public static Response get(String endpoint, Map<String, String> headers, int postid, int statuscode) {
        return given()
                .pathParam("id", postid)
                .accept(ContentType.JSON)
                .headers(headers)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statuscode)
                .log().all()
                .extract()
                .response();
    }

    public static Response post(String endpoint, Map<String, String> headers, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response getParams(String endpoint, Map<String, String> headers,
                                         Map<String, Object> params) {
        return given()
                .headers(headers)
                .queryParams(params)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response put(String endpoint, Map<String, String> headers, int id, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response delete(String endpoint, Map<String, String> headers, int id, int statuscode) {
        return given()
                .pathParam("id", id)
                .headers(headers)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .statusCode(statuscode)
                .log().all()
                .extract()
                .response();
    }



    public static void assertWithLog(int actual, int expected, String description) {
        if (actual == expected) {
            Assert.assertEquals(actual, expected);
            getTest().pass(description + " — წარმატებულია: " + actual);
        } else {
            Assert.assertEquals(actual, expected);
            getTest().fail(description + " — მოსალოდნელი: " + expected + ", მიღებული: " + actual);
        }
    }

    public static void assertWithLog(String actual, String expected, String description) {
        if (actual.equals(expected)) {
            Assert.assertEquals(actual, expected);
            getTest().pass(description + " — წარმატებულია: " + actual);
        } else {
            Assert.assertEquals(actual, expected);
            getTest().fail(description + " — მოსალოდნელი: " + expected + ", მიღებული: " + actual);
        }

    }



    private Map<String, Object> buildBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", ConfigReader.get("DEF.TITLE"));
        body.put("price", ConfigReader.get("DEF.PRICE"));
        body.put("description", ConfigReader.get("DEF.DESCRIPTION"));
        body.put("categoryId", ConfigReader.get("DEF.CATID"));
        body.put("images", new String[]{ConfigReader.get("DEF.IMAGE")});
        return body;
    }



}