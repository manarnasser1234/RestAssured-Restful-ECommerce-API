package RegisterUtiles;

import com.github.javafaker.Faker;

public class Register {

  String email =new Faker().internet().emailAddress();
  String phone =new Faker().phoneNumber().cellPhone();


    public String Register1(){
        return email;

    }

    public String Register3(){
        return phone;

    }



}
