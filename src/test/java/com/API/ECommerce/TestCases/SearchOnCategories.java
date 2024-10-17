package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class SearchOnCategories extends Environment {
    @Test(priority = 4)
    public void test_SearchOnCategoriesFunction(){

        GetProfile getProfile=new GetProfile();
        getProfile.setBaseURL();
        getProfile.MakeSure_ThatReturn_MyEmail();

        given()
                .baseUri(baseURL)
                .header("Content-Type","application/json")

        .when()
                .get("/categories")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(20000L))
                .contentType("application/json")
                .body("status", equalTo(true))
                .body("data.total",equalTo(6))
                .log().all();
    }
}
