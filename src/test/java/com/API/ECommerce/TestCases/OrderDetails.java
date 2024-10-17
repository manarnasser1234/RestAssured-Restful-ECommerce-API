package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import com.beust.ah.A;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OrderDetails extends Environment {
    @Test(priority = 8)
    public void test_OrderDetailsFunction(){

        AddOrder addOrder=new AddOrder();
        addOrder.setBaseURL();
        addOrder.test_AddOrderFunction();

        Response response=
        given()
                .baseUri(baseURL)
                .headers("Content-Type","application/json","Authorization",Token_login(),"Accept",Order_iD())
        .when()
                .get("/orders/"+ Order_iD())
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(20000L))
                .body("status",equalTo(true))
                .body("message",equalTo(null))
                .body("$",hasKey("data"))
                .body("data.id",equalTo(Order_iD()))
                .body("data.cost",equalTo(Product_Price()))
                .body("data.discount",equalTo(Order_discount()))
                .body("data.points",equalTo(Order_points()))
                .body("data.total",equalTo(Order_total()))
                .body("data.status",equalTo("جديد"))
                .log().all()
                .extract().response();
         product_Date=JsonPath.from(response.asString()).getString("data.date");
        assertThat(product_Date,equalTo(product_Date()));




    }
}
