package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateProduct extends Environment {
    @Test(priority = 5)
    public void tst_CreateProductFunction(){

        SearchOnCategories searchOnCategories=new SearchOnCategories();
        searchOnCategories.setBaseURL();
        searchOnCategories.test_SearchOnCategoriesFunction();

        Response response=
        given()
                .baseUri(baseURL)
                .header("Content-Type","application/json")

        .when()
                .post("/products/search")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(200000L))
                .body("$",hasKey("data"))
                .body("data.total",equalTo(21))
                .log().all()
                .extract().response();

          JsonPath jsonPath = response.jsonPath();

//        Check if the data exists and has the required structure
        if (jsonPath.get("data") != null &&
                jsonPath.get("data.data") != null &&
                jsonPath.getList("data.data").size() > 0)
            {

            productId = JsonPath.from(response.asString()).getInt("data.data[2].id");
            productPrice= JsonPath.from(response.asString()).getInt("data.data[2].price");
            productName= JsonPath.from(response.asString()).getString("data.data[2].name");
            productDiscount= JsonPath.from(response.asString()).getInt("data.data[2].discount");
                System.out.println(productId());
           }
        else {
            System.out.println("Data not found");
        }



    }
}
