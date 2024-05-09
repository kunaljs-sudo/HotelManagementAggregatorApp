package com.example.HotelMangmentAggregatorApp.mapper;

import java.util.stream.Collectors;

import com.example.HotelMangmentAggregatorApp.dto.UserDTO;
import com.example.HotelMangmentAggregatorApp.entity.User;

public class UserMapper {
    public static UserDTO mapUser2UserDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .bookings(user.getBookingMade().stream().map(b -> b.getBookingId()).collect(Collectors.toList()))
                .build();

    }
}
