package client;

import config.APIConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class NewsAPIClient {

    public APIConfig config;

    public NewsAPIClient() {
        config = new APIConfig();
    }

    // Create GET requests
    public Response get(String endpoint, HashMap<String, String> params, String authHeaderName, String authHeaderValue) {
        RestAssured.defaultParser = Parser.JSON;

        return given().headers(authHeaderName, authHeaderValue).params(params)
                .when().get(endpoint)
                .then().contentType(ContentType.JSON).extract().response();
    }

    public List<String> getSources() {
        RestAssured.defaultParser = Parser.JSON;
        String sourcesEndpoint = config.url + config.serviceRoute + config.headlinesResource + config.sourcesResource;

        List<String> sources = new ArrayList<>();
        Response response = given().headers("Authorization", config.apiKey)
                .when().get(sourcesEndpoint)
                .then().contentType(ContentType.JSON).extract().response();

        sources = response.body().path("sources.name");

        return sources;
    }
//    public ValidatableResponse post() {
//
//    }

}
