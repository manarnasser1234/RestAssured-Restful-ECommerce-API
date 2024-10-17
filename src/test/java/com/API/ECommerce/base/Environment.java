package com.API.ECommerce.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Environment {
  protected String baseURL;
  protected static String token;
  protected static String email;
  protected static String phone;
  protected static int id;
  protected static String token_login;
  protected static int productId;
  protected static int productPrice;
  protected static String productName;
  protected static int productDiscount;
  protected static int Cart_id;
  protected static int Product_Price;
  protected static int Order_iD;
  protected static int Order_discount;
  protected static int Order_points;
  protected static float Order_total;
  protected static String product_Date;


    @BeforeMethod
    public void setBaseURL(){
        baseURL= "https://student.valuxapps.com/api";
    }

@AfterMethod
  public String Token(){
        return token;
  }
    public String email(){
        return email;
    }
    public String phone(){
        return phone;
    }
    public int id(){
        return id;
    }
    public String Token_login(){
        return token_login;
    }
    public int productId(){
        return productId;
    }
    public int productPrice(){
        return productPrice;
    }
    public String productName(){
        return productName;
    }
    public int productDiscount(){
        return productDiscount;
    }
    public int Cart_id(){
        return Cart_id;
    }
    public int Product_Price(){
        return Product_Price;
    }
    public int Order_iD(){
        return Order_iD;
    }
    public int Order_discount(){
        return Order_discount;
    }
    public int Order_points(){
        return Order_points;
    }
    public float Order_total(){
        return Order_total;
    }
    public String product_Date(){
        return product_Date;
    }









}
