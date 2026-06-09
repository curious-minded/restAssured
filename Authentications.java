import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONTokener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class Authentications {
    Dotenv dotenv = Dotenv.load();
    private static String BASE_URL1 = "https://postman-echo.com/basic-auth";
    private static String BASE_URL2 = "https://api.github.com/user/repos";

    //@Test(priority = 1)
    void testBasicAuth() {
        given()
                .auth().basic("postman", "password")
                .when()
                .get(BASE_URL1)
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    //@Test(priority = 2)
    void testDigestAuth() {
        given()
                .auth().digest("postman", "password")
                .when()
                .get(BASE_URL1)
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    //@Test(priority = 3)
    void testPreemptiveAuth() {
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get(BASE_URL1)
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    //@Test(priority = 1)
    void testBearerTokenAuth() {
        String token = dotenv.get("GITHUB_TOKEN");
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .get(BASE_URL2)
                .then()
                .statusCode(200)
                .log().all();
    }

    //@Test(priority = 1)
    void testOAuth1Auth() {
        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
                .when()
                .get(BASE_URL2)
                .then()
                .statusCode(200)
                .log().all();

    }

    //@Test(priority = 1)
    void testOAuth2() {
        String token = dotenv.get("GITHUB_TOKEN");
        given()
                .auth().oauth2(token)
                .when()
                .get(BASE_URL2)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 1)
    void testAPIKeyAuth() {
        String key = dotenv.get("NASA_KEY");
        given()
                .queryParam("api_key", key)
                .when()
                .get("https://api.nasa.gov/planetary/apod")
                .then()
                .statusCode(200)
                .log().all();
    }
}
