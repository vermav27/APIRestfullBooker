package org.example.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.asserts.assertActions;
import org.example.endpoints.apiConstants;
import org.example.modules.payloadManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class baseTest {

    //Common To All TestCases
    public RequestSpecification rs;
    public Response r;
    public ValidatableResponse vr;

    @BeforeTest
    public void setup(){
        System.out.println("Start Test.");
        payloadManager pm = new payloadManager();
        assertActions aA = new assertActions();

        rs = RestAssured.given().log().all();
        rs.baseUri(apiConstants.BASE_URL);
        rs.contentType(ContentType.JSON);

    }

    @AfterTest
    public void teardown(){
        System.out.println("End Test.");
    }

}
