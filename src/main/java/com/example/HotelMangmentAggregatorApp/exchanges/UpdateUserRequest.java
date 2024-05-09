package com.example.HotelMangmentAggregatorApp.exchanges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UpdateUserRequest {

    private String firstName;
    private String lastName;

    public UpdateUserRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
