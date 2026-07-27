package com.automation.api;

import com.automation.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class TrelloApiClient {

    public static String getBaseUrl() {
        return ConfigManager.get("trello.api.base.url", "https://api.trello.com/1");
    }

    public static String getApiKey() {
        return ConfigManager.get("trello.api.key", "your_api_key_here");
    }

    public static String getApiToken() {
        return ConfigManager.get("trello.api.token", "your_api_token_here");
    }

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .addQueryParam("key", getApiKey())
                .addQueryParam("token", getApiToken())
                .setContentType(ContentType.JSON)
                .build();
    }
}
