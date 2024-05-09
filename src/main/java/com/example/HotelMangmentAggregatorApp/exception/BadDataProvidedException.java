package com.example.HotelMangmentAggregatorApp.exception;

public class BadDataProvidedException extends RuntimeException {
    public BadDataProvidedException() {
        super();
    }

    public BadDataProvidedException(String msg) {
        super(msg);
    }

}
