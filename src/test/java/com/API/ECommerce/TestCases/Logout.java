package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Logout extends Environment {
    @Test(priority = 11)
    public void test_LogoutFunction(){

        DeleteCart deleteCart=new DeleteCart();
        deleteCart.setBaseURL();
        deleteCart.test_DeleteCartFunction();

        given()
                .baseUri(baseURL)
                .headers("Content-Type","application/json","Authorization",Token_login())
        .when()
                .post("/logout")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(20000L))
                .body("status",equalTo(true))
                .body("message",equalTo("تم تسجيل الخروج بنجاح"))
                .body("$",hasKey("data"))
                .body("data.token",equalTo(Token_login()))
                .log().all();
    }
}
