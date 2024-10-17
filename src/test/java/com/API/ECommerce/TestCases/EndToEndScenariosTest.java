package com.API.ECommerce.TestCases;

import com.API.ECommerce.base.Environment;
import org.testng.annotations.Test;

public class EndToEndScenariosTest extends Environment {

        @Test(priority = 13)
        public void EndToEndScenariosFN(){

        LoginAgain loginAgainTest=new LoginAgain();
        loginAgainTest.setBaseURL();
        loginAgainTest.test_LoginAgainFunction();

    }


}
