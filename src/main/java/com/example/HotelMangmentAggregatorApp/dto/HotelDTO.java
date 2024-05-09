package com.example.HotelMangmentAggregatorApp.dto;

import lombok.Builder;

@Builder
public class HotelDTO {
    private Integer hotelId;
    private String hotelName;
    private String location;
    private String description;
    private Integer availableRooms;

    public HotelDTO() {
    }

    public HotelDTO(Integer hotelId, String hotelName, String location, String description, Integer availableRooms) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
        this.description = description;
        this.availableRooms = availableRooms;
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

}
