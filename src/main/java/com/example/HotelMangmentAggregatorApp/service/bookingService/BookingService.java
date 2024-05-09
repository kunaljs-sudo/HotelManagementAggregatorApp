package com.example.HotelMangmentAggregatorApp.service.bookingService;

import java.util.List;

import com.example.HotelMangmentAggregatorApp.dto.BookingDTO;

public interface BookingService {

    public List<BookingDTO> getAllBookings();

    public List<BookingDTO> getAllBookingsByUserName(String email);

    public BookingDTO getBookingDTO(Integer bookingId, String userEmail);

    public BookingDTO createBooking(Integer hotelId, String userEmail);

    public String cancelBooking(Integer bookingId, String managerEmail);

    public BookingDTO checkIn(Integer bookingId);

    public BookingDTO checkOut(Integer bookingId);

}
