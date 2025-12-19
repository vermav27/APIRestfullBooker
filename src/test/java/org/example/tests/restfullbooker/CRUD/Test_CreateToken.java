package org.example.tests.restfullbooker.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.asserts.assertActions;
import org.example.base.baseTest;
import org.example.endpoints.apiConstants;
import org.example.modules.payloadManager;
import org.testng.annotations.Test;

public class Test_CreateToken extends baseTest {

    @Test(groups = "reg",priority = 1)
    @Owner("Vineet Verma")
    @Description("TC-01-Verify the token is being generated.")
    public void testCreateToken_POST_positive(){

        rs.basePath(apiConstants.AUTH_URL);
        r = RestAssured.given(rs)
                .when().body(payloadManager.createSerializedTokenPayload()).log().all()
                .post();

        //Extract Token
        String token = payloadManager.tokenResponseJava(r.asString());

        assertActions.verifyTokenIsNotNull(token);



    }

}
