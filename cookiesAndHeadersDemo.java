import io.restassured.http.*;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class cookiesAndHeadersDemo {

    @Test(priority = 1)
    void getheaders() {
        given()
                .when()
                .get("https://google.com")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("server", "gws")
                .header("Content-Encoding", "gzip");

    }

    @Test(priority = 2)
    void getHeadersValue() {
        Response response = given()
                .when()
                .get("https://google.com");
        Headers myheaders = response.getHeaders();
        for(Header h : myheaders) {
            System.out.println(h.getName() +  ": " + h.getValue());
        }
    }

    //@Test(priority = 1)
    void testCookies() {
        given()
                .when()
                .get("https://www.google.com")
        .then();
    }

    //@Test(priority = 2)
    void getCookieValue() {
        Response response = given()
                .when()
                .get("https://www.google.com");

        Map<String, String> values = response.getCookies();
        for(String key : values.keySet()) {

            System.out.println(key + ": " + response.getCookie(key));
        }
        //System.out.println("Value of cookie: " + values.keySet());
    }
}