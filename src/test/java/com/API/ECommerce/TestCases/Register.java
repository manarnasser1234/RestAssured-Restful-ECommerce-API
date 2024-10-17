package com.API.ECommerce.TestCases;


import com.API.ECommerce.base.Environment;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Register extends Environment {



    @Test(priority = 1)
    public void test_post_registerAsUserFunction() {
        RegisterUtiles.Register register=new RegisterUtiles.Register();
        HashMap body=new HashMap();

        body.put( "name","manar nasser");
        body.put("email",register.Register1());
        body.put("password","Hello123@");
        body.put("phone",register.Register3());
        Response response=
                given()
                        .baseUri(baseURL)
                        .header("Content-Type","application/json")
                        .body(body)
                .when()
                        .post("/register")
                .then()
                        .assertThat().statusCode(200)
                        .time(lessThan(20000L))
                        .contentType("application/json")
                        .body("status", equalTo(true))
                        .body("message", equalTo("تم التسجيل بنجاح"))
                        .body("data", hasKey("name"))
                        .body("data", hasKey("email"))
                        .body("data", hasKey("phone"))
                        .body("data", hasKey("id"))
                        .body("data", hasKey("image"))
                        .body("data", hasKey("token"))
                        .body("data.name", isA(String.class))
                        .body("data.email", isA(String.class))
                        .body("data.phone", isA(String.class))
                        .body("data.image",isA(String.class))
                        .body("data.token",isA(String.class))
                        .body("data.id",isA(Integer.class))
                        .body("data.name", equalTo("manar nasser"))
                        .body("data.email", equalTo(register.Register1()))
                        .body("data.phone", equalTo(register.Register3()))
                        .log().all()
                        .extract().response();
        token= JsonPath.from(response.asString()).getString("data.token");
        email=JsonPath.from(response.asString()).getString("data.email");
        phone=JsonPath.from(response.asString()).getString("data.phone");
        id=JsonPath.from(response.asString()).getInt("data.id");

        System.out.println(token);
        System.out.println(email);

    }

}

