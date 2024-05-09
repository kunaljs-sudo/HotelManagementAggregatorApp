package com.example.HotelMangmentAggregatorApp.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity(name = "_booking")
@Builder
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userBooked;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelBooked;

    @Column(nullable = false)
    private Date bookingDate;

    private Date checkInDate;
    private Date checkOutDate;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    public Booking() {
    }

    public Booking(Integer bookingId, User userBooked, Hotel hotelBooked, Date bookingDate, Date checkInDate,
            Date checkOutDate, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.userBooked = userBooked;
        this.hotelBooked = hotelBooked;
        this.bookingDate = bookingDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = bookingStatus;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public User getUserBooked() {
        return userBooked;
    }

    public void setUserBooked(User userBooked) {
        this.userBooked = userBooked;
    }

    public Hotel getHotelBooked() {
        return hotelBooked;
    }

    public void setHotelBooked(Hotel hotelBooked) {
        this.hotelBooked = hotelBooked;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
