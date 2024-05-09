package com.example.HotelMangmentAggregatorApp.dto;

import java.util.List;

import lombok.Builder;

@Builder
public class UserDTO {
    private Integer userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private List<Integer> bookings;

    public UserDTO() {
    }

    public UserDTO(Integer userId, String email, String firstName, String lastName, String password, String role,
            List<Integer> bookings) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.bookings = bookings;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Integer> getBookings() {
        return bookings;
    }

    public void setBookings(List<Integer> bookings) {
        this.bookings = bookings;
    }

}
