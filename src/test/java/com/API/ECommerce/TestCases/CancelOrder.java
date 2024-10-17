package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CancelOrder extends Environment {
    @Test(priority = 9)
    public void test_CancelOrderFunction(){

        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setBaseURL();
        orderDetails.test_OrderDetailsFunction();

        given()
                .baseUri(baseURL)
                .headers("Content-Type","application/json","Authorization",Token_login())
        .when()
                .get("/orders/"+ Order_iD() +"/cancel")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(20000L))
                .body("status",equalTo(true))
                .body("message",equalTo("تم التعديل بنجاح"))
                .body("$",hasKey("data"))
                .body("data.id",equalTo(Order_iD()))
                .body("data.cost",equalTo(productPrice()))
                .body("data.total",equalTo(Order_total()))
                .log().all();
    }
}
