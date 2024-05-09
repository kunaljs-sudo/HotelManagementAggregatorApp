package com.example.HotelMangmentAggregatorApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HotelMangmentAggregatorApp.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
