package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginAgain extends Environment {
    @Test(priority = 12)
    public void test_LoginAgainFunction(){

        Logout logout=new Logout();
        logout.setBaseURL();
        logout.test_LogoutFunction();

        HashMap body= new HashMap();
        body.put("email","manar61@gmail.com");
        body.put("password","Hello1243@");

        given()
                .baseUri(baseURL)
                .headers("Content-Type","application/json","Authorization",Token_login())
                .body(body)
        .when()
                .post("/login")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(20000L))
                .body("status",equalTo(false))
                .body("message",equalTo("لم نتمكن من تسجيل الدخول برجاء التأكد من البيانات المدخلة"))
                .body("data",equalTo(null))
                .log().all();
    }
}
