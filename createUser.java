package APIChaining;
import com.github.javafaker.Faker;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class createUser {

    private static final String BASE_URL =
            "https://gorest.co.in/public/v2/users";

    private static final Dotenv dotenv = Dotenv.load();

    @Test
    void create(ITestContext context) {

        String token = dotenv.get("GOREST_TOKEN");

        Faker faker = new Faker();

        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", "male");      // GoRest expects lowercase
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        Response response =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .body(data.toString())
                        .when()
                        .post(BASE_URL);

        response.then()
                .statusCode(201)
                .log().all();

        int id = response.jsonPath().getInt("id");
        context.setAttribute("id", id);
        System.out.println("Created User ID: " + id);
    }
}