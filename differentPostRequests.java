import io.restassured.http.ContentType;
import org.testng.annotations.Test;
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
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;

public class differentPostRequests {
    private static final String BASE_URL = "http://localhost:3000/users";
    private static String id;

    //@Test(priority = 1)
    void postUsingHashmap() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Prashant");
        data.put("email", "prashant@pw.live");
        data.put("dept", "QA and automation");

        Response response = given().contentType(ContentType.JSON).body(data)
        .when().post(BASE_URL);

        response.then().statusCode(201).body("name", equalTo("Prashant"));
        id = response.jsonPath().getString("id");
    }

  //@Test(priority = 1)
    void postUsingJSONObject() {

        JSONObject data = new JSONObject();

        data.put("name", "Sarthak");
        data.put("email", "sarthak@pw.live");
        data.put("location", "Noida");

        String[] skills = {"QA", "Automation"};
        data.put("skills", skills);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(data.toString())
                .when()
                        .post(BASE_URL);

        response.then().statusCode(201).body("name", equalTo("Sarthak"))
        .log().all();

        id = response.jsonPath().getString("id");
    }

    //@Test(priority = 1)
    void postUsingPOJO() {
        User user = new User();
        user.setName("Sarthak");
        user.setEmail("sarthak@pw.live");
        user.setLocation("Noida");
        user.setSkills(Arrays.asList("QA", "automation"));

        Response response = given().contentType(ContentType.JSON).body(user)
        .when().post(BASE_URL);

        response.then().statusCode(201). body("name", equalTo("Sarthak")).body("location", equalTo("Noida"))
        .log().all();

        id = response.jsonPath().getString("id");
    }

    @Test(priority = 1)
    void postUsingExternalFile() throws IOException{
        File f = new File("./temp.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(data.toString())
                .when()
                        .post(BASE_URL);

        response.then().statusCode(201)
        .log().all();

        fr.close();
    }

    //@Test(priority = 2)
    void getUser() {
        Response response = given()
        .when().get(BASE_URL + "/" + id);

        response.then().statusCode(200).body("name", equalTo("Sarthak"));
        String name = response.jsonPath().getString("name");
        System.out.println("name: " + name);
    }

    //@Test(priority = 2)
    void deleteAll() {
        Response response = given()
        .when().get(BASE_URL);

        List<Map<String, Object>> users = response.jsonPath().getList("");
        for(Map<String, Object> user : users){
            String userId = user.get("id").toString();

            given()
            .when().delete(BASE_URL + "/" + userId)
            .then().statusCode(200);
        }
    }

    //@Test(priority = 2)
    void testDelete() {
        given()
        .when().delete(BASE_URL + "/" + id)
        .then().statusCode(200)
        .log().all();
    }
} 