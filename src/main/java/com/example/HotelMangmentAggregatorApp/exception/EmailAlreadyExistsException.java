package com.example.HotelMangmentAggregatorApp.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super();
    }

    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }

}
