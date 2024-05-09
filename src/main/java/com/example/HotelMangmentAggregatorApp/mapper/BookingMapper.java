package com.example.HotelMangmentAggregatorApp.mapper;

import com.example.HotelMangmentAggregatorApp.dto.BookingDTO;
import com.example.HotelMangmentAggregatorApp.entity.Booking;

public class BookingMapper {
    public static BookingDTO mapBooking2DTO(Booking booking) {
        return BookingDTO.builder()
                .bookingId(booking.getBookingId())
                .userEmail(booking.getUserBooked().getEmail())
                .hotelName(booking.getHotelBooked().getHotelName())
                .bookingDate(booking.getBookingDate())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .bookingStatus(booking.getBookingStatus().name())
                .build();
    }
}
