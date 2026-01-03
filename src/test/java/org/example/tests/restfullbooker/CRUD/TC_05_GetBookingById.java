package org.example.tests.restfullbooker.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.asserts.assertActions;
import org.example.base.baseTest;
import org.example.endpoints.apiConstants;
import org.example.modules.payloadManager;
import org.example.pojos.request.booking;
import org.example.pojos.response.bookingresponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;


public class TC_05_GetBookingById extends baseTest {

    @Test
    @Description("TC-05 - Verify that get booking by ID returns correct booking details")
    @Owner("Vineet Verma")
    public void testReturnBooking(ITestContext iTestContext){

        String bid = (String)iTestContext.getAttribute("bid");
        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL + "/" + bid);
        r = RestAssured.given(rs)
                .when().log().all()
                .get();

        booking bookingById = payloadManager.bookingResponse_withoutId(r.asString());
        String expectedFirstName = payloadManager.firstname;
        String expectedLastName = payloadManager.lastname;
        String actualFirstName = bookingById.getFirstname();
        String actualLastName = bookingById.getLastname();

        //Assertion
        assertActions.verifyFirstName(actualFirstName,expectedFirstName);
        assertActions.verifyLastName(actualLastName,expectedLastName);
        assertActions.verifyResponseCode(r,200);

    }

}
