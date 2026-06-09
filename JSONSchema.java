import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JSONSchema {

    private static final String BASE_URL = "http://localhost:3000/users";

    //@Test
    void getUsers() {
        Response response = given()
                .when().get(BASE_URL);
        response.prettyPrint();
    }
    @Test
    void validateSchema() {

        given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        JsonSchemaValidator.matchesJsonSchema(
                                new File("./schema.json")
                        )
                );
    }
}