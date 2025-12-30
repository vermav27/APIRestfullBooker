package org.example.tests.restfullbooker.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.asserts.assertActions;
import org.example.base.baseTest;
import org.example.endpoints.apiConstants;
import org.example.modules.payloadManager;
import org.example.pojos.response.allBookingResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TC_04_ReturnBookingIds extends baseTest {

    @Test(groups = "reg")
    @Description("TC-04 - Verify that get all bookings endpoint returns list of booking IDs")
    @Owner("Vineet Verma")
    public void testReturnBookingIds(){

        rs.basePath(apiConstants.CREATE_UPDATE_BOOKING_URL);
        r = RestAssured
                .given(rs)
                .when()
                .get();

        allBookingResponse allResponse[] = payloadManager.allBookingResponse(r.asString());

        //verify that any random id is not null
        assertActions.verifySomeRandomIdIsNotNull(allResponse);

        //Retrieving ids in list
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for(allBookingResponse aBR:allResponse){
            ids.add(aBR.getBookingid());
        }
        System.out.println(ids);

    }

}
