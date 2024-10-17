package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetProfile extends Environment {

 @Test(priority = 3)
 public void MakeSure_ThatReturn_MyEmail(){
     Login login=new Login();
     login.setBaseURL();
     login.test_loginAsUserFunction();

     given()
             .baseUri(baseURL)
             .header("Content-Type","application/json")
             .header("Authorization", Token_login())

     .when()
             .get("/profile")
     .then()
             .assertThat().statusCode(200)
             .time(lessThan(20000L))
             .contentType("application/json")
             .body("status", equalTo(true))
             .body("message", equalTo(null))
             .body("data.name",equalTo("manar nasser"))
             .body("data.email",equalTo(login.email()))
             .body("data.phone",equalTo(login.phone()))
             .body("data.id",equalTo(login.id()))
             .body("data.token",equalTo(login.Token_login()))
             .log().all();
 }

}