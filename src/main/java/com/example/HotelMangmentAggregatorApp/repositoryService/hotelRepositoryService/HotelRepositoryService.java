package com.example.HotelMangmentAggregatorApp.repositoryService.hotelRepositoryService;

import java.util.List;
import java.util.Optional;

import com.example.HotelMangmentAggregatorApp.entity.Hotel;

public interface HotelRepositoryService {

    public List<Hotel> getAllHotels();

    public Optional<Hotel> getHotel(Integer hotelId);

    public Hotel getHotelById(Integer hotelId);

    public Hotel createHotel(Hotel hotel);

    public Hotel updateHotel(Hotel hotel);

    public String deleteHotel(Integer hotelId);
}
