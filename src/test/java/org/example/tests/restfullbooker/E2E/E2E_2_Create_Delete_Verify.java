package org.example.tests.restfullbooker.E2E;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.asserts.assertActions;
import org.example.base.baseTest;
import org.example.endpoints.apiConstants;
import org.example.modules.payloadManager;
import org.example.pojos.response.bookingresponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class E2E_2_Create_Delete_Verify extends baseTest {

    @Test(groups = "reg",priority = 1)
    @Description("Step 1 - Creating Booking")
    @Owner("Vineet Verma")
    public void createBooking(ITestContext iTestContext){

        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL);

        r = RestAssured
                .given(rs)
                .body(payloadManager.createValidPayloadBookingAsString())
                .when().log().all()
                .post();

        bookingresponse bookingResponse = payloadManager.bookingResponseJava(r.asString());
        String actualFirstName = bookingResponse.getBooking().getFirstname();
        String actualLastName = bookingResponse.getBooking().getLastname();
        String expectedFirstName = payloadManager.firstname;
        String expectedLastName = payloadManager.lastname;
        String bookingId = bookingResponse.getBookingid();
        iTestContext.setAttribute("bid",bookingId);

        //Assertion
        assertActions.verifyResponseCode(r,200);
        assertActions.verifyBookingIdIsNotNull(r);
        assertActions.verifyFirstName(actualFirstName,expectedFirstName);
        assertActions.verifyLastName(actualLastName,expectedLastName);

    }

    @Test(groups = "reg",priority = 2)
    @Description("Step 2 - Delete Booking")
    @Owner("Vineet Verma")
    public void deleteBooking(ITestContext iTestContext){

        String token = getToken();

        String bookingId = (String) iTestContext.getAttribute("bid");

        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId);
        rs.cookie("token",token);

        r = rs.when().log().all().delete();

        String response = r.asString();

        //Assertion
        assertActions.verifyResponseBody(response,"Created","Verify Deletion Of Record");
        assertActions.verifyResponseCode(r,201);

    }

    @Test(groups = "reg",priority = 3)
    @Description("Step 3 - Verify that record is not existing.")
    @Owner("Vineet Verma")
    public void verifyRecordDeletedSuccessfully(ITestContext iTestContext){

        String bookingId = (String) iTestContext.getAttribute("bid");

        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId);

        r = rs.when().log().all().get();

        String response = r.asString();

        //Assertion
        assertActions.verifyResponseBody(response,"Not Found","Verify request returns not found when no record exists.");
        assertActions.verifyResponseCode(r,404);

    }

}
