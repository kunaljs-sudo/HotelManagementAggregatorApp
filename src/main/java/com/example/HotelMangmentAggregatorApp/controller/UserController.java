package com.example.HotelMangmentAggregatorApp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelMangmentAggregatorApp.dto.UserDTO;
import com.example.HotelMangmentAggregatorApp.exchanges.ChangePasswordRequest;
import com.example.HotelMangmentAggregatorApp.exchanges.UpdateUserRequest;
import com.example.HotelMangmentAggregatorApp.service.userService.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/myAccount")
    public UserDTO getUser(Principal principal) {
        log.info("Get Request for My Account Detials");
        return userService.getUserDTOByEmail(principal.getName());
    }

    @PutMapping("/myAccount/update")
    public UserDTO updateUser(Principal principal, @RequestBody UpdateUserRequest updateUserRequest) {
        log.info("Put Request for UpdateUserRequest");
        return this.userService.updateUser(principal.getName(), updateUserRequest);
    }

    @PutMapping("/myAccount/changePassword")
    public UserDTO changePassword(Principal principal, @RequestBody ChangePasswordRequest changePasswordRequest) {
        log.info("Put Request for ChangePasswordRequest");
        return this.userService.changePassword(principal.getName(), changePasswordRequest);
    }

    @DeleteMapping("/myAccount/delete")
    public String deleteUser(Principal principal) {
        log.info("Delete reuqesut: " + principal.getName());
        return this.userService.deleteUser(principal.getName());
    }

}
