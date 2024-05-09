package com.example.HotelMangmentAggregatorApp.service.userService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HotelMangmentAggregatorApp.dto.UserDTO;
import com.example.HotelMangmentAggregatorApp.entity.User;
import com.example.HotelMangmentAggregatorApp.exception.BadDataProvidedException;
import com.example.HotelMangmentAggregatorApp.exchanges.ChangePasswordRequest;
import com.example.HotelMangmentAggregatorApp.exchanges.UpdateUserRequest;
import com.example.HotelMangmentAggregatorApp.mapper.UserMapper;
import com.example.HotelMangmentAggregatorApp.repositoryService.userRepositoryService.UserRepositoryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Retrieving all users");
        List<UserDTO> allUsers = this.userRepositoryService.getAllUsers().stream()
                .map(user -> UserMapper.mapUser2UserDTO(user))
                .collect(Collectors.toList());
        log.info("users Retrieved successfully");
        return allUsers;
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepositoryService.getUserByEmail(email);
    }

    @Override
    public UserDTO getUserDTOByEmail(String userEmail) {
        User user = this.getUserByEmail(userEmail);
        return UserMapper.mapUser2UserDTO(user);
    }

    @Override
    public UserDTO updateUser(String email, UpdateUserRequest updateUserRequest) {
        if (updateUserRequest == null) {
            log.error("Request Body Contains some error");
            throw new BadDataProvidedException("Request Body Contains some error");
        }
        log.info("Trying to update User data");
        User user = this.getUserByEmail(email);
        if (updateUserRequest.getFirstName() != null && !updateUserRequest.getFirstName().isBlank())
            user.setFirstName(updateUserRequest.getFirstName());

        if (updateUserRequest.getLastName() != null && !updateUserRequest.getLastName().isBlank())
            user.setLastName(updateUserRequest.getLastName());

        try {
            user = this.userRepositoryService.updateUser(user);
        } catch (Exception e) {
            log.error("Something went wrong while updating User data. " + e.getMessage(), e);
            throw new BadDataProvidedException("Something went wrong while updating User data " + e.getMessage());
        }
        log.info("User data updated");

        return UserMapper.mapUser2UserDTO(user);
    }

    @Override
    @Transactional
    public String deleteUser(String email) {
        return this.userRepositoryService.deleteUser(email);
    }

    @Override
    public UserDTO changePassword(String email, ChangePasswordRequest changePasswordRequest) {
        if (changePasswordRequest == null || changePasswordRequest.getPassword() == null
                || changePasswordRequest.getPassword().isBlank()) {
            log.error("Request Body ChangePasswordRequest Contains some invalid values");
            throw new BadDataProvidedException("Request Body ChangePasswordRequest Contains some invalid values");
        }

        User user = this.getUserByEmail(email);
        log.info("Changing to password of User");
        user.setPassword(this.passwordEncoder.encode(changePasswordRequest.getPassword()));
        try {
            user = this.userRepositoryService.updateUser(user);
        } catch (Exception e) {
            log.error("Something went wrong while changing password. " + e.getMessage(), e);
            throw new BadDataProvidedException("Something went wrong while changing password. " + e.getMessage());
        }

        log.info("Password changed Successfully");
        return UserMapper.mapUser2UserDTO(user);
    }

}
