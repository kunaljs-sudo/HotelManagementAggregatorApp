package com.example.HotelMangmentAggregatorApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HotelMangmentAggregatorApp.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    public Optional<Hotel> findByHotelName(String hotelName);
}
