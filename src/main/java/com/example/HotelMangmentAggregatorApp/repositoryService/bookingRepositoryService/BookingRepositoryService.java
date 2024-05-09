package com.example.HotelMangmentAggregatorApp.repositoryService.bookingRepositoryService;

import java.util.List;
import java.util.Optional;

import com.example.HotelMangmentAggregatorApp.entity.Booking;

public interface BookingRepositoryService {
    public List<Booking> getAllBookings();

    public Optional<Booking> getBooking(Integer bookingId);

    public Booking getBookingObject(Integer bookingId);

    public Booking createBooking(Booking booking);

    public Booking updateBooking(Booking booking);

    public String deleteBooking(Integer bookingId);

}
