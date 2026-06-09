package APIChaining;
import com.github.javafaker.Faker;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import static io.restassured.RestAssured.given;

public class updateUser {

    private static final String BASE_URL =
            "https://gorest.co.in/public/v2/users";

    private static final Dotenv dotenv = Dotenv.load();

    @Test
    void update(ITestContext context) {

        String token = dotenv.get("GOREST_TOKEN");

        Faker faker = new Faker();

        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", "female");      // GoRest expects lowercase
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        int id = (int) context.getAttribute("id");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(data.toString()).pathParam("id", id)
                .when()
                .put(BASE_URL + "/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}