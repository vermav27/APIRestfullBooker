package org.example.tests.restfullbooker.E2E;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.asserts.assertActions;
import org.example.base.baseTest;
import org.example.endpoints.apiConstants;
import org.example.modules.payloadManager;
import org.example.pojos.request.booking;
import org.example.pojos.response.bookingresponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class E2E_1_CRUD extends baseTest {

    //Health Test
    //Create Booking
    //Get Booking
    //Update Booking
    //Delete Booking

    @Test(groups = "reg",priority = 1)
    @Description("TC-01-Verify the health of the restfull api.")
    @Owner("Vineet Verma")
    public void tc01_HealthTest(){

        rs.basePath(apiConstants.PING_URL);
        r = RestAssured.given(rs).log().all().get();
        String fromResponse = r.asString();

        //Verification
        assertActions.verifyPingResponseBody(fromResponse);
        assertActions.verifyResponseCode(r,201);

    }

    @Test(groups = "reg",priority = 2)
    @Description("TC-02-Verify the create booking api.")
    @Owner("Vineet Verma")
    public void tc02_CreateBookingTest(ITestContext iTestContext){

        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL);
        r = RestAssured.given(rs).body(payloadManager.createValidPayloadBookingAsString()).log().all().post();

        //Extractions
        bookingresponse from_createBooking = payloadManager.bookingResponseJava(r.asString());

        String expectedFirstname = payloadManager.firstname;
        String expectedLastName = payloadManager.lastname;
        String actualFirstName = from_createBooking.getBooking().getFirstname();
        String actualLastName = from_createBooking.getBooking().getLastname();
        String bookingId = from_createBooking.getBookingid();
        iTestContext.setAttribute("bid",bookingId);

        //Assertions
        assertActions.verifyBookingIdIsNotNull(r);
        assertActions.verifyFirstName(actualFirstName,expectedFirstname);
        assertActions.verifyLastName(actualLastName,expectedLastName);

    }

    @Test(groups = "reg",priority = 3)
    @Description("TC-03-Verify the get booking api.")
    @Owner("Vineet Verma")
    public void tc03_GetBookingTest(ITestContext iTestContext){

        String bookingId = (String)iTestContext.getAttribute("bid");
        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId );
        r = RestAssured.given(rs).log().all().when().get();

        booking from_Response = payloadManager.bookingResponse_withoutId(r.asString());
        String expectedFirstname = payloadManager.firstname;
        String expectedLastName = payloadManager.lastname;
        String actualFirstName = from_Response.getFirstname();
        String actualLastName = from_Response.getLastname();

        //Assertion
        assertActions.verifyResponseCode(r,200);
        assertActions.verifyFirstName(actualFirstName,expectedFirstname);
        assertActions.verifyLastName(actualLastName,expectedLastName);

    }

    @Test(groups = "reg",priority = 4)
    @Description("TC-04-Verify the update booking api.")
    @Owner("Vineet Verma")
    public void tc04_UpdateBookingTest(ITestContext iTestContext){

        String bookingId = (String)iTestContext.getAttribute("bid");
        String token_val = getToken();
        iTestContext.setAttribute("tokenValue",token_val);

        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId);

        r = RestAssured.given(rs)
                .cookie("token",token_val)
                .body(payloadManager.createUpdatePayloadBookingAsString())
                .when().log().all()
                .put();

        //Extraction
        booking booking_fromResponse = payloadManager.bookingResponse_withoutId(r.asString());
        String expectedFirstname = payloadManager.firstname;
        String expectedLastName = payloadManager.lastname;
        String actualFirstName = booking_fromResponse.getFirstname();
        String actualLastName = booking_fromResponse.getLastname();

        //Assertions
        assertActions.verifyResponseCode(r,200);
        assertActions.verifyFirstName(actualFirstName,expectedFirstname);
        assertActions.verifyLastName(actualLastName,expectedLastName);

    }

    @Test(groups = "reg",priority = 5)
    @Description("TC-05-Verify the delete booking api.")
    @Owner("Vineet Verma")
    public void tc05_DeleteBookingTest(ITestContext iTestContext){

        String bookingToDelete = (String) iTestContext.getAttribute("bid");
        String tokenForDelete = (String) iTestContext.getAttribute("tokenValue");

        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingToDelete);

        r = rs.cookie("token",tokenForDelete).log().all().delete();
        String response = r.asString();

        //Assertion
        assertActions.verifyResponseCode(r,201);
        assertActions.verifyResponseBody(response,"Created","Verify deletion.");

    }

}
