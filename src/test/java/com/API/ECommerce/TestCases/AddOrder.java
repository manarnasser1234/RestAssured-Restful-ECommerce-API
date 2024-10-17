package com.API.ECommerce.TestCases;


import com.API.ECommerce.base.Environment;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class AddOrder extends Environment {
    @Test(priority = 7)
    public void test_AddOrderFunction(){

        AddToCart addToCart=new AddToCart();
        addToCart.setBaseURL();
        addToCart.test_AddToCardFunction();

        HashMap body=new HashMap();
        body.put("address_id",35);
        body.put("payment_method",1);
        body.put("use_points","false");

        Response response=
        given()
                .baseUri(baseURL)
                .headers("Content-Type","application/json","Authorization",Token_login(),"Accept",Cart_id())
                .body(body)
        .when()
                .post("/orders")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(20000L))
                .body("status",equalTo(true))
                .body("message",equalTo("تمت الإضافة بنجاح"))
                .body("data.cost",equalTo(Product_Price()))
                .log().all()
                .extract().response();
         Order_iD=JsonPath.from(response.asString()).getInt("data.id");
         Order_discount=JsonPath.from(response.asString()).getInt("data.discount");
         Order_points=JsonPath.from(response.asString()).getInt("data.points");
         Order_total=JsonPath.from(response.asString()).getFloat("data.total");


        System.out.println(Order_iD);


    }
}
