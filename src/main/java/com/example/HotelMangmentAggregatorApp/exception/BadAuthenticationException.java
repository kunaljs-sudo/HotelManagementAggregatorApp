package com.example.HotelMangmentAggregatorApp.exception;

public class BadAuthenticationException extends RuntimeException {
    public BadAuthenticationException() {
        super();
    }

    public BadAuthenticationException(String msg) {
        super(msg);
    }
}
