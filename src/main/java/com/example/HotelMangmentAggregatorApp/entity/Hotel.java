package com.example.HotelMangmentAggregatorApp.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity(name = "_hotel")
@Table(indexes = { @Index(name = "indx_hotelName", columnList = "hotelName") })
@Builder
public class Hotel {
    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelId;

    @Column(nullable = false)
    private String hotelName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private Integer availableRooms;

    @OneToMany(mappedBy = "hotelBooked", fetch = FetchType.EAGER)
    private Set<Booking> bookings;

    public Hotel() {
    }

    public Hotel(Integer hotelId, String hotelName, String location, String description, Integer availableRooms,
            Set<Booking> bookings) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
        this.description = description;
        this.availableRooms = availableRooms;

        if (availableRooms == null)
            this.availableRooms = 0;
        else
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

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

}
