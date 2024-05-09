package com.example.HotelMangmentAggregatorApp.mapper;

import java.util.stream.Collectors;

import com.example.HotelMangmentAggregatorApp.dto.HotelDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTOManager;
import com.example.HotelMangmentAggregatorApp.entity.Hotel;

public class HotelMapper {
    public static HotelDTO mapHotel2DTO(Hotel hotel) {
        return HotelDTO.builder()
                .hotelId(hotel.getHotelId())
                .hotelName(hotel.getHotelName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .availableRooms(hotel.getAvailableRooms())
                .build();
    }

    public static HotelDTOManager mapHotel2DTOForNonCustomer(Hotel hotel) {
        return HotelDTOManager.builder()
                .hotelId(hotel.getHotelId())
                .hotelName(hotel.getHotelName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .availableRooms(hotel.getAvailableRooms())
                .bookings((hotel.getBookings().stream().map(booking -> BookingMapper.mapBooking2DTO(booking))
                        .collect(Collectors.toSet())))
                .build();
    }
}
