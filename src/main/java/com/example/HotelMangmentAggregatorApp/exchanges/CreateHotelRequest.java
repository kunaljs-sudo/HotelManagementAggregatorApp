package com.example.HotelMangmentAggregatorApp.exchanges;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateHotelRequest {
    private String hotelName;
    private String location;
    private String description;
    private Integer availableRooms;

    @JsonCreator
    public CreateHotelRequest(String hotelName, String location, String description, Integer availableRooms,
            String hotelManagerEmail) {
        this.hotelName = hotelName;
        this.location = location;
        this.description = description;
        this.availableRooms = availableRooms;
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
