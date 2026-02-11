package org.example.tests.restfullbooker.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.asserts.assertActions;
import org.example.base.baseTest;
import org.example.endpoints.apiConstants;
import org.example.modules.payloadManager;
import org.example.pojos.request.booking;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TC_06_UpdateBookingByValidToken extends baseTest {

    @Test
    @Description("TC-06 - Verify update booking completely with valid token")
    @Owner("Vineet Verma")
    public void testUpdateBooking(ITestContext iTestContext){

        //Start Test
        String tok = (String) iTestContext.getAttribute("tokenVal");
        String bid = (String)iTestContext.getAttribute("bid");
        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL + "/" + bid);
        r = RestAssured.given(rs)
                .cookie("token",tok)
                .body(payloadManager.createUpdatePayloadBookingAsString())
                .when().log().all()
                .put();

        booking booking_fromUpdate = payloadManager.bookingResponse_withoutId(r.asString());
        String expectedFirstName = payloadManager.firstname;
        String expectedLastName = payloadManager.lastname;
        String actualFirstName = booking_fromUpdate.getFirstname();
        String actualLastName = booking_fromUpdate.getLastname();

        assertActions.verifyResponseCode(r,200);
        assertActions.verifyFirstName(actualFirstName,expectedFirstName);
        assertActions.verifyLastName(actualLastName,expectedLastName);
        //End Test

    }

}
