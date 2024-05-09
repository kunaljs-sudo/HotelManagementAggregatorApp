package com.example.HotelMangmentAggregatorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelMangmentAggregatorApp.exchanges.AuthenticationRequest;
import com.example.HotelMangmentAggregatorApp.exchanges.AuthenticationResponse;
import com.example.HotelMangmentAggregatorApp.exchanges.RegisterRequest;
import com.example.HotelMangmentAggregatorApp.service.authService.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        log.info("Register user request :" + registerRequest.toString());
        return ResponseEntity.ok().body(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) {
        log.info("Login user request: ");
        return ResponseEntity.ok().body(authService.authenticate(authRequest));
    }
}
