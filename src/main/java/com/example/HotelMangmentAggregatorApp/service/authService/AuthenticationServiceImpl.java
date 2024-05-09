package com.example.HotelMangmentAggregatorApp.service.authService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HotelMangmentAggregatorApp.entity.Role;
import com.example.HotelMangmentAggregatorApp.entity.User;
import com.example.HotelMangmentAggregatorApp.exception.EmailAlreadyExistsException;
import com.example.HotelMangmentAggregatorApp.exception.UserNameNoFoundException;
import com.example.HotelMangmentAggregatorApp.exchanges.AuthenticationRequest;
import com.example.HotelMangmentAggregatorApp.exchanges.AuthenticationResponse;
import com.example.HotelMangmentAggregatorApp.exchanges.RegisterRequest;
import com.example.HotelMangmentAggregatorApp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        Role role;
        try {
            role = Role.valueOf(registerRequest.getRole());
        } catch (Exception ex) {
            role = Role.CUSTOMER;
        }
        ;
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            log.error("Email already exists", EmailAlreadyExistsException.class);
            throw new EmailAlreadyExistsException("Email already exists");
        }

        userRepository.save(user);
        String jwtToken = jwtService.generateJwtToken(user);
        log.info("Registered User.." + user.getEmail());
        return AuthenticationResponse.builder().jwtToken(jwtToken).build();

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword());
        authenticationManager.authenticate(authenticationToken);
        Optional<User> optionalUser = userRepository.findByEmail(authRequest.getEmail());
        if (optionalUser.isEmpty()) {
            log.error("UserEmail provided is incorrect...please check again", UserNameNoFoundException.class);
            throw new UserNameNoFoundException("UserEmail provided is incorrect...please check again");
        }

        String jwtToken = jwtService.generateJwtToken(optionalUser.get());
        log.info("Logged in Successfully username: " + optionalUser.get().getEmail());
        return AuthenticationResponse.builder().jwtToken(jwtToken).build();

    }

}
