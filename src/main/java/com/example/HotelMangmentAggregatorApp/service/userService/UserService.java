package com.example.HotelMangmentAggregatorApp.service.userService;

import java.util.List;

import com.example.HotelMangmentAggregatorApp.dto.UserDTO;
import com.example.HotelMangmentAggregatorApp.entity.User;
import com.example.HotelMangmentAggregatorApp.exchanges.ChangePasswordRequest;
import com.example.HotelMangmentAggregatorApp.exchanges.UpdateUserRequest;

public interface UserService {
    public List<UserDTO> getAllUsers();

    public UserDTO getUserDTOByEmail(String userEmail);

    public User getUserByEmail(String email);

    public UserDTO updateUser(String email, UpdateUserRequest updateUserRequest);

    public UserDTO changePassword(String email, ChangePasswordRequest changePasswordRequest);

    public String deleteUser(String email);

}
