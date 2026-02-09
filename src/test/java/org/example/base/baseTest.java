package org.example.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.asserts.assertActions;
import org.example.endpoints.apiConstants;
import org.example.modules.payloadManager;
import org.example.pojos.response.tokenresponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class baseTest {

    //Common To All TestCases
    public RequestSpecification rs;
    public Response r;
    public ValidatableResponse vr;

    @BeforeClass
    public void setup(){
        System.out.println("Start Test.");
        payloadManager pm = new payloadManager();
        assertActions aA = new assertActions();

        rs = RestAssured.given().log().all();
        rs.baseUri(apiConstants.BASE_URL);
        rs.contentType(ContentType.JSON);


    }

    @AfterClass
    public void teardown(){
        System.out.println("End Test.");
    }

    public String getToken(){

        rs = RestAssured.given().log().all();
        rs.contentType(ContentType.JSON);
        rs.baseUri(apiConstants.BASE_URL);
        rs.basePath(apiConstants.AUTH_URL);
        rs.body(payloadManager.createSerializedTokenPayload());

        r = RestAssured.given(rs).when().log().all().post();

        String token = payloadManager.tokenResponseJava(r.asString());
        return token;

    }

}
