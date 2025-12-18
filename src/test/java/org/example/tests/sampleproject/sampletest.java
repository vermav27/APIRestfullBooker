package org.example.tests.sampleproject;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sampletest {

    //Create booking , Create token
    //Get Booking
    //Update Booking
    //Delete Booking


    @Test(groups = "qa",priority = 1)
    @Owner("Vineet Verma")
    @Description("TC_INT1 - Step 1 - Verify that booking is getting created")
    public void testCreateBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority = 2)
    @Owner("Vineet Verma")
    @Description("TC_INT1 - Step 2 - Verify user able to get the booking")
    public void testGetBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority = 3)
    @Owner("Vineet Verma")
    @Description("TC_INT1 - Step 3 - Verify booking is updated")
    public void testUpdateBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority = 4)
    @Owner("Vineet Verma")
    @Description("TC_INT1 - Step 4 - Verify booking is deleted")
    public void testDeleteBooking(){
        Assert.assertTrue(true);
    }


}
