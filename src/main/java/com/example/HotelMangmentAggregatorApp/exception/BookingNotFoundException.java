package com.example.HotelMangmentAggregatorApp.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {
        super();
    }

    public BookingNotFoundException(String msg) {
        super(msg);
    }
}
