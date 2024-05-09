package com.example.HotelMangmentAggregatorApp.config;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.HotelMangmentAggregatorApp.entity.User;
import com.example.HotelMangmentAggregatorApp.exception.UserNameNoFoundException;
import com.example.HotelMangmentAggregatorApp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApplicationConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService getUserDetailsService() {
        Function<String, UserDetails> userDetailsResolver = username -> {

            Optional<User> optionaluserDetails = userRepository.findByEmail(username);
            if (optionaluserDetails.isEmpty()) {
                log.error("userEmail provided is incorrect...please check again", UserNameNoFoundException.class);
                throw new UserNameNoFoundException("userEmail provided is incorrect...please check again");
            }
            return optionaluserDetails.get();
        };

        return username -> userDetailsResolver.apply(username);
    }

    @Bean
    public AuthenticationProvider daoAuthenticationConfigurer() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getUserDetailsService());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
