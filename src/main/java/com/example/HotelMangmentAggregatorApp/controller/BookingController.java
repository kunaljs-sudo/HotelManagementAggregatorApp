package com.example.HotelMangmentAggregatorApp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelMangmentAggregatorApp.dto.BookingDTO;
import com.example.HotelMangmentAggregatorApp.service.bookingService.BookingService;

@RestController
@RequestMapping(BookingController.endPoint)
public class BookingController {
    public final static String endPoint = "/bookings";

    @Autowired
    private BookingService bookingService;

    @GetMapping("/myBookings")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<BookingDTO> getAllBookingsByUserName(Principal principal) {
        return bookingService.getAllBookingsByUserName(principal.getName());
    }

    @GetMapping("/myBookings/{bookingId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public BookingDTO getBooking(@PathVariable Integer bookingId, Principal principal) {
        return bookingService.getBookingDTO(bookingId, principal.getName());
    }

    @DeleteMapping("{bookingId}/cancelBooking")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String cancelBooking(@PathVariable Integer bookingId, Principal principal) {
        return this.bookingService.cancelBooking(bookingId, principal.getName());
    }

    @PutMapping("{bookingId}/check-in")
    @PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public BookingDTO checkIn(@PathVariable Integer bookingId) {
        return this.bookingService.checkIn(bookingId);
    }

    @PutMapping("{bookingId}/check-out")
    @PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public BookingDTO checkOut(@PathVariable Integer bookingId) {
        return this.bookingService.checkOut(bookingId);
    }

}
