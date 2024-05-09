package com.example.HotelMangmentAggregatorApp.exchanges;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AuthenticationResponse {
    private String jwtToken;

    @JsonCreator
    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
