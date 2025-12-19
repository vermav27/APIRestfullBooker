package org.example.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.example.pojos.request.auth;
import org.example.pojos.request.booking;
import org.example.pojos.request.bookingdates;
import org.example.pojos.response.bookingresponse;
import org.example.pojos.response.tokenresponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class payloadManager {
    static Gson gson;

    static Faker fake_data = new Faker();
    public static String firstname = fake_data.name().firstName();
    public static String lastname = fake_data.name().lastName();
    public static Integer totalPrice = fake_data.number().numberBetween(1,999);
    public static Boolean depositPaid = fake_data.bool().bool();

    //Getting Today's Date
    public static LocalDate today = LocalDate.now();

    //Setting Check In Check Out Date
    public static LocalDate checkInDate = today.plusDays(4);
    public static LocalDate checkOutDate = checkInDate.plusDays(3);

    //Setting Date Format
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String checkinFormated = checkInDate.format(formatter);
    public static String checkoutFormated = checkOutDate.format(formatter);

    public static String additionalNeeds = fake_data.food().fruit();

    //Serialization
    public static String createValidPayloadBookingAsString(){


        booking booking = new booking();
        booking.setFirstname(firstname);
        booking.setLastname(lastname);
        booking.setTotalprice(totalPrice);
        booking.setDepositpaid(depositPaid);

        bookingdates bookingdates = new bookingdates();
        bookingdates.setCheckin(checkinFormated);
        bookingdates.setCheckout(checkoutFormated);

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds(additionalNeeds);
        System.out.println(booking);

        //java Object to JSON
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;

    }

    //Deserialization
    public static bookingresponse bookingResponseJava(String responseString){
        gson = new Gson();
        bookingresponse bookingresponse = gson.fromJson(responseString, org.example.pojos.response.bookingresponse.class);
        return bookingresponse;
    }

    public static String createInvalidPayloadBookingAsString(){

        /*Faker fake_data = new Faker();

        Integer totalPrice = fake_data.number().numberBetween(1,999);
        Boolean depositPaid = fake_data.bool().bool();

        //Getting Today's Date
        LocalDate today = LocalDate.now();

        //Setting Check In Check Out Date
        LocalDate checkInDate = today.plusDays(4);
        LocalDate checkOutDate = checkInDate.plusDays(3);

        //Setting Date Format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String checkinFormated = checkInDate.format(formatter);
        String checkoutFormated = checkOutDate.format(formatter);*/

        booking booking = new booking();
        booking.setFirstname("我爱爱人");
        booking.setFirstname("我爱爱人");
        booking.setTotalprice(totalPrice);
        booking.setDepositpaid(depositPaid);

        bookingdates bookingdates = new bookingdates();
        bookingdates.setCheckin(checkinFormated);
        bookingdates.setCheckout(checkoutFormated);

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("我爱爱人");

        //java Object to JSON
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;

    }

    public static String createSerializedTokenPayload(){

        auth auth =new auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        //Serialize
        gson = new Gson();
        String jsonString_auth = gson.toJson(auth);
        return jsonString_auth;

    }

    public static String tokenResponseJava(String responseString){
        gson = new Gson();
        tokenresponse tokenresponse = gson.fromJson(responseString,org.example.pojos.response.tokenresponse.class);
        return tokenresponse.getToken();
    }


}
