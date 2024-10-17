package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteCart extends Environment {
    @Test(priority = 10)
    public void test_DeleteCartFunction(){

        CancelOrder cancelOrder=new CancelOrder();
        cancelOrder.setBaseURL();
        cancelOrder.test_CancelOrderFunction();

        given()
                .baseUri(baseURL)
                .header("Content-Type","application/json")
        .when()
                .get("/carts/"+ Cart_id())
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(20000L))
                .body("status",equalTo(false))
                .body("message",equalTo("غير مصرح لك"))
                .body("data",equalTo(null))
                .log().all();
    }
}
