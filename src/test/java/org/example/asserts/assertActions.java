package org.example.asserts;

import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class assertActions {

    public static void verifyResponseBody(String actual,String expected,String description){
        //TestNG assertion
        assertEquals(actual,expected,description);
    }

    public static void verifyResponseBody(int actual,int expected,String description){
        //TestNG assertion
        assertEquals(actual,expected,description);
    }

    public static void verifyResponseCode(Response response,int expected){
        //TestNG assertion
        assertEquals(response.getStatusCode(),expected);
    }

    public static void verifyStringKey(String KeyExpect,String KeyActual){
        //assertJ
        assertThat(KeyExpect).isNotNull();
        assertThat(KeyExpect).isNotBlank();
        assertThat(KeyExpect).isEqualTo(KeyActual);
    }

    public static void verifyStringKeyNotNull(String KeyExpect){
        //assertJ
        assertThat(KeyExpect).isNotNull();
    }


}
