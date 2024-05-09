package com.example.HotelMangmentAggregatorApp.exception;

public class UserNameNoFoundException extends RuntimeException {
    public UserNameNoFoundException() {
        super();
    }

    public UserNameNoFoundException(String msg) {
        super(msg);
    }
}
