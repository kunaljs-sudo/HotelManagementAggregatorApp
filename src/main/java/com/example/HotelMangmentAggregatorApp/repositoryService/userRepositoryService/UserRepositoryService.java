package com.example.HotelMangmentAggregatorApp.repositoryService.userRepositoryService;

import java.util.List;
import java.util.Optional;

import com.example.HotelMangmentAggregatorApp.entity.User;

public interface UserRepositoryService {

    public List<User> getAllUsers();

    public Optional<User> findByEmail(String userEmail);

    public User getUserByEmail(String email);

    public User createUser(User user);

    public User updateUser(User user);

    public String deleteUser(String userEmail);

}
