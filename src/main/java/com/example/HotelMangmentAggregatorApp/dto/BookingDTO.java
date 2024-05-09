package com.example.HotelMangmentAggregatorApp.dto;

import java.util.Date;

import lombok.Builder;

@Builder
public class BookingDTO {
    private Integer bookingId;
    private String userEmail;
    private String hotelName;
    private Date bookingDate;
    private Date checkInDate;
    private Date checkOutDate;
    private String bookingStatus;

    public BookingDTO() {
    }

    public BookingDTO(Integer bookingId, String userEmail, String hotelName, Date bookingDate, Date checkInDate,
            Date checkOutDate, String bookingStatus) {
        this.bookingId = bookingId;
        this.userEmail = userEmail;
        this.hotelName = hotelName;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
