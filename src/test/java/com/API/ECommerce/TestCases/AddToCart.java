package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddToCart extends Environment {
    @Test(priority = 6)
    public void test_AddToCardFunction(){

        CreateProduct createProduct=new CreateProduct();
        createProduct.setBaseURL();
        createProduct.tst_CreateProductFunction();

        Response response=
        given()
                .baseUri(baseURL)
                .header("Content-Type","application/json")
                .queryParam("product_id",productId())
                .header("Authorization",Token_login())

        .when()
                .post("/carts")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(20000L))
                .body("status",equalTo(true))
                .body("message",equalTo("Added Successfully"))
                .body("$",hasKey("data"))
                .body("data.product.id",equalTo(productId()))
                .body("data.product.price",equalTo(productPrice()))
                .body("data.product.name",equalTo(productName()))
                .body("data.product.discount",equalTo(productDiscount()))
                .body("data.quantity",equalTo(1))
                .log().all()
                .extract().response();
        Cart_id= JsonPath.from(response.asString()).getInt("data.id");
        Product_Price= JsonPath.from(response.asString()).getInt("data.product.price");
        System.out.println(Cart_id());

    }
}
