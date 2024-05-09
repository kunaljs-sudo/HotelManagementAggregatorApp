package com.example.HotelMangmentAggregatorApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelMangmentAggregatorApp.dto.BookingDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTOManager;
import com.example.HotelMangmentAggregatorApp.dto.UserDTO;
import com.example.HotelMangmentAggregatorApp.entity.User;
import com.example.HotelMangmentAggregatorApp.exchanges.CreateHotelRequest;
import com.example.HotelMangmentAggregatorApp.service.bookingService.BookingService;
import com.example.HotelMangmentAggregatorApp.service.hotelService.HotelService;
import com.example.HotelMangmentAggregatorApp.service.userService.UserService;

@RestController
@RequestMapping(AdminController.endPoint)

public class AdminController {
    public static final String endPoint = "/admin";

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @GetMapping("/allUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userEmail}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUser(@PathVariable String userEmail) {
        return userService.getUserByEmail(userEmail);
    }

    @GetMapping("/allHotels")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<HotelDTOManager> getAllHotels() {
        return hotelService.getAllHotelsForADMIN();
    }

    @PostMapping("/createHotel")
    @PreAuthorize("hasAuthority('ADMIN')")
    public HotelDTOManager createHotel(@RequestBody CreateHotelRequest createHotelRequest) {
        return hotelService.createHotel(createHotelRequest);
    }

    @PutMapping("/deleteHotel/{hotelId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteHotel(@PathVariable Integer hotelId) {
        return hotelService.deleteHotel(hotelId);
    }

    @GetMapping("/allBookings")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

}
