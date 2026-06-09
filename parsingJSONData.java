import io.restassured.http.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;

public class parsingJSONData {

    private static String BASE_URL = "http://localhost:3000/users";

    @Test(priority = 1)
    void parse() {
        /*given()
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL)
                .then()
                .header("Content-Type", "application/json")
                .body("[0].users[1].name", equalTo("Prashant"));*/
        Response response = given()
                .when()
                .get(BASE_URL);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/json");
        String name = response.jsonPath().get("[0].users[1].name").toString();
        System.out.println("name: " + name);
    }

    @Test(priority = 2)
    void testJSONObject() {

        Response response = given()
                .when()
                .get(BASE_URL);

        JSONArray rootArray =
                new JSONArray(response.getBody().asString());

        JSONObject firstObject =
                rootArray.getJSONObject(0);

        JSONArray users =
                firstObject.getJSONArray("users");

        for (int i = 0; i < users.length(); i++) {

            String email =
                    users.getJSONObject(i)
                            .getString("email");

            System.out.println("email: " + email);
        }
    }
}