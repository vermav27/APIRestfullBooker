package org.example.tests.restfullbooker.CRUD;


import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.asserts.assertActions;
import org.example.base.baseTest;
import org.example.endpoints.apiConstants;
import org.testng.annotations.Test;


public class Test_PingRequest extends baseTest {

    @Test(groups = "reg",priority = 1)
    @Owner("Vineet Verma")
    @Description("TC-01-Verify the ping request.")
    public void testPing_GET_positive(){

        rs.basePath(apiConstants.PING_URL);
        r = RestAssured.given(rs)
                .when().log().all()
                .get();

        String pingResponse = r.asString();

        assertActions.verifyPingResponseBody(pingResponse);
        assertActions.verifyResponseCode(r,201);

    }


}
