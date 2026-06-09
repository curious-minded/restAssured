import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class pathQueryParams {

    private static String BASE_URL = "http://localhost:3000";

    @Test(priority = 1)
    void testParams() {
        given()
        .pathParam("path", "users")
        .queryParam("id", "u001")
        .when().get(BASE_URL + "/{path}")
        .then().statusCode(200). body("[0].name", equalTo("Sarthak"))
        .log().all();
    }
}