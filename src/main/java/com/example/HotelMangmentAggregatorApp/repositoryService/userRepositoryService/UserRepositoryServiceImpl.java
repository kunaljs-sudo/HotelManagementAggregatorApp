package com.example.HotelMangmentAggregatorApp.repositoryService.userRepositoryService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HotelMangmentAggregatorApp.entity.User;
import com.example.HotelMangmentAggregatorApp.exception.UserNameNoFoundException;
import com.example.HotelMangmentAggregatorApp.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRepositoryServiceImpl implements UserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public String deleteUser(String userEmail) {
        User user = this.getUserByEmail(userEmail);

        log.info("Trying to delete booking enrties where b.userBooked.userBooked.userId " + user.getUserId());
        // Delete bookings related to the User
        entityManager.createQuery("DELETE from _booking b where b.userBooked.userId = :userId")
                .setParameter("userId", user.getUserId())
                .executeUpdate();

        log.info("Trying to delete user itself");
        // Delete the User itself
        entityManager.createQuery("DELETE FROM _user u WHERE u.userId = :userId")
                .setParameter("userId", user.getUserId())
                .executeUpdate();

        log.info("User Deleted Successfully");
        return "User Deleted successfully";
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Fecthing User by email: " + email);
        Optional<User> optionalUser = this.findByEmail(email);

        if (optionalUser.isEmpty()) {
            log.error("Unable to find user with userEmail: " + email);
            throw new UserNameNoFoundException("Unable to find user with userEmail: " + email);
        }
        log.info("User Retrieved successfully");
        return optionalUser.get();
    }

}
