package com.automation;

import com.automation.api.TrelloApiClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class APITests {

    private String boardId;
    private String listId;
    private String cardId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = TrelloApiClient.getBaseUrl();
    }

    @Test(priority = 1, description = "Create a new Trello Board via REST API")
    public void createBoard() {
        Response response = given()
                .spec(TrelloApiClient.getRequestSpec())
                .queryParam("name", "Automation Test Board")
                .queryParam("defaultLists", false)
                .when()
                .post("/boards")
                .then()
                .statusCode(200)
                .body("name", equalTo("Automation Test Board"))
                .body("prefs.permissionLevel", equalTo("private"))
                .body("id", notNullValue())
                .extract().response();

        boardId = response.path("id");
    }

    @Test(priority = 2, dependsOnMethods = "createBoard", description = "Create lists within the newly created Trello Board")
    public void createLists() {
        String[] listNames = {"To-Do", "In Progress", "Code Review", "Done"};

        for (String listName : listNames) {
            Response response = given()
                    .spec(TrelloApiClient.getRequestSpec())
                    .queryParam("name", listName)
                    .queryParam("idBoard", boardId)
                    .when()
                    .post("/lists")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(listName))
                    .body("idBoard", equalTo(boardId))
                    .body("closed", equalTo(false))
                    .extract().response();

            if (listName.equals("To-Do")) {
                listId = response.path("id");
            }
        }
    }

    @Test(priority = 3, dependsOnMethods = "createLists", description = "Create a new card inside the To-Do list")
    public void createCard() {
        Response response = given()
                .spec(TrelloApiClient.getRequestSpec())
                .queryParam("name", "Task 1: Automated QA Validation")
                .queryParam("idList", listId)
                .when()
                .post("/cards")
                .then()
                .statusCode(200)
                .body("name", equalTo("Task 1: Automated QA Validation"))
                .body("idList", equalTo(listId))
                .body("idBoard", equalTo(boardId))
                .extract().response();

        cardId = response.path("id");
    }

    @Test(priority = 4, dependsOnMethods = "createCard", description = "Move created card across board workflow lists")
    public void moveCardToLists() {
        String[] listsToMove = {"In Progress", "Code Review", "Done"};

        for (String listName : listsToMove) {
            // Find target list ID by name
            String targetListId = given()
                    .spec(TrelloApiClient.getRequestSpec())
                    .when()
                    .get("/boards/{boardId}/lists", boardId)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract()
                    .response()
                    .path("find {it.name == '" + listName + "'}.id");

            // Move card to target list
            given()
                    .spec(TrelloApiClient.getRequestSpec())
                    .queryParam("idList", targetListId)
                    .when()
                    .put("/cards/{id}", cardId)
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(cardId))
                    .body("idList", equalTo(targetListId));
        }
    }
}
