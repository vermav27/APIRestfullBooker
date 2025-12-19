package org.example.tests.restfullbooker.CRUD;


import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.asserts.assertActions;
import org.example.base.baseTest;
import org.example.endpoints.apiConstants;
import org.example.pojos.response.bookingresponse;
import org.testng.annotations.Test;
import org.example.modules.payloadManager;

public class Test_CreateBooking extends baseTest {

    @Test(groups = "reg",priority = 1)
    @Description("TC-01-Verify the booking is being created.")
    @Owner("Vineet Verma")
    public void testCreateBooking_POST_positive(){

        //Given
        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL);
        r = RestAssured
                .given(rs)
                //when
                .when()
                .body(payloadManager.createValidPayloadBookingAsString()).log().all().post();

        //Then
        //Extraction
        bookingresponse booking = payloadManager.bookingResponseJava(r.asString());

        //Assertion
        assertActions.verifyResponseCode(r,200);
        assertActions.verifyStringKeyNotNull(booking.getBookingid());
        assertActions.verifyResponseBody(booking.getBooking().getFirstname(),payloadManager.firstname,"Verifying the firstname.");
    }

}
