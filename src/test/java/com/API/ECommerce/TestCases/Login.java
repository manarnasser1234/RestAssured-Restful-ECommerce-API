package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class Login extends Environment {
    @Test(priority = 2)
    public void test_loginAsUserFunction(){
      Register register=new Register();
      register.setBaseURL();
      register.test_post_registerAsUserFunction();
        HashMap body=new HashMap();
        body.put("password","Hello123@");

        Response response=
                given()
                        .baseUri(baseURL)
                        .header("Content-Type","application/json")
                        .queryParam("email",email())
                        .body(body)
                .when()
                        .post("/login")
                .then()
                        .assertThat().statusCode(200)
                        .time(lessThan(20000L))
                        .contentType("application/json")
                        .body("status", equalTo(true))
                        .body("message", equalTo("تم تسجيل الدخول بنجاح"))
                        .body("data.id",isA(Integer.class))
                        .body("data.name", isA(String.class))
                        .body("data.email", isA(String.class))
                        .body("data.phone", isA(String.class))
                        .body("data.image",isA(String.class))
                        .body("data.token",isA(String.class))
                        .body("data.id",equalTo(id()))
                        .body("data.name",equalTo("manar nasser"))
                        .body("data.email",equalTo(register.email()))
                        .body("data.phone",equalTo(register.phone()))
                        .body("data.id",equalTo(register.id()))
                        .log().all()
                        .extract().response();
                 token_login= JsonPath.from(response.asString()).getString("data.token");
                 System.out.println(token_login);




    }
}
