package APIChaining;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import static io.restassured.RestAssured.given;

public class getUser {
    private static final String BASE_URL =
            "https://gorest.co.in/public/v2/users";

    private static final Dotenv dotenv = Dotenv.load();

    @Test
    public void get(ITestContext context){
        String token = dotenv.get("GOREST_TOKEN");
        int id = (int) context.getAttribute("id");
        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .when()
                .get(BASE_URL + "/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}