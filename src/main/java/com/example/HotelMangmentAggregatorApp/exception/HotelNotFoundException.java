package com.example.HotelMangmentAggregatorApp.exception;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException() {
        super();
    }

    public HotelNotFoundException(String msg) {
        super(msg);
    }

}
