import io.restassured.http.*;
import io.restassured.path.xml.XmlPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;

import javax.xml.crypto.dsig.XMLObject;

public class parsingXMLData {

    private static String BASE_URL= "https://gorest.in/public/v2/users.xml";

    @Test(priority = 1)
    void parse() {
        Response response = given()
                .when()
                .get(BASE_URL);

        response.then()
                .statusCode(200).header("Content-Type", "application/xml; charset=utf-8");

        String id = response.xmlPath().getString("users.user[1].id");
        String name = response.xmlPath().getString("users.user[1].name");
        String email = response.xmlPath().getString("users.user[1].email");
        String gender = response.xmlPath().getString("users.user[1].gender");
        String status = response.xmlPath().getString("users.user[1].status");

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("Status: " + status);
    }

    @Test(priority = 2)
    void testXMLPath() {
        Response response = given()
                .when()
                .get(BASE_URL);

        XmlPath xmlobj = new XmlPath(response.asString());
        List<String> emails = xmlobj.getList("users.user.email");
        for(String email : emails){
            System.out.println("Email: " + email);
        }
    }
}