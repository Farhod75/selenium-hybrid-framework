package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CourseAPITest {
    @Test(groups = {"api"})
    public void apiTest() {
        String response = given()
                .queryParam("key", "qaclick123")
                .when().get("https://rahulshettyacademy.com/maps/api/place/get/json?place_id=test")
                .asString();
        Assert.assertNotNull(response);
    }
}