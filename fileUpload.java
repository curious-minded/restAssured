import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class fileUpload {
    private static String BASE_URL = "http://localhost:3000/upload";
    @Test
    public void Upload() {
        File file = new File("./sample.txt");
        Response response = given().multiPart("file", file)
                .when()
                .post(BASE_URL);
        response.then().statusCode(200).body("message", equalTo("File uploaded successfully"))
                .log().all();
    }
}