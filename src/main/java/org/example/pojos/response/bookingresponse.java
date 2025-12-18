package org.example.pojos.response;
import  org.example.pojos.request.booking;

public class bookingresponse {

    private String bookingid;
    private booking booking;

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public booking getBooking() {
        return booking;
    }

    public void setBooking(booking booking) {
        this.booking = booking;
    }
}
