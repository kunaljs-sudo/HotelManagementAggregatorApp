package com.example.HotelMangmentAggregatorApp.service.authService;

import com.example.HotelMangmentAggregatorApp.exchanges.AuthenticationRequest;
import com.example.HotelMangmentAggregatorApp.exchanges.AuthenticationResponse;
import com.example.HotelMangmentAggregatorApp.exchanges.RegisterRequest;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest registerRequest);

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest);
}
