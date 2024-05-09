package com.example.HotelMangmentAggregatorApp.dto;

import java.util.Set;

import lombok.Builder;

@Builder
public class HotelDTOManager {
    private Integer hotelId;
    private String hotelName;
    private String location;
    private String description;
    private Integer availableRooms;
    private Set<BookingDTO> bookings;

    public HotelDTOManager() {
    }

    public HotelDTOManager(Integer hotelId, String hotelName, String location, String description,
            Integer availableRooms, Set<BookingDTO> bookings) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
        this.description = description;
        this.availableRooms = availableRooms;
        this.bookings = bookings;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }

    public Set<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingDTO> bookings) {
        this.bookings = bookings;
    }

}
