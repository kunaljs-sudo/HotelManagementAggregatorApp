package com.example.HotelMangmentAggregatorApp.repositoryService.bookingRepositoryService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotelMangmentAggregatorApp.entity.Booking;
import com.example.HotelMangmentAggregatorApp.exception.BadDataProvidedException;
import com.example.HotelMangmentAggregatorApp.repository.BookingRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingRepositoryServiceImpl implements BookingRepositoryService {

    @Autowired
    private BookingRepository bookingRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBooking(Integer bookingId) {
        return this.bookingRepository.findById(bookingId);
    }

    @Override
    public Booking getBookingObject(Integer bookingId) {
        log.info("Fetching Booking details of id: " + bookingId);
        Optional<Booking> optionalBooking = this.bookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            log.error("BookingId provided is incorrect: " + bookingId);
            throw new BadDataProvidedException("BookingId provided is incorrect: " + bookingId);
        }
        log.info("Successfully Retrieved Data");
        return optionalBooking.get();
    }

    @Override
    public Booking createBooking(Booking booking) {
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return this.bookingRepository.save(booking);
    }

    @Override
    public String deleteBooking(Integer bookingId) {
        this.bookingRepository.deleteById(bookingId);
        return "Booking Deleted Successfully";
    }

}
