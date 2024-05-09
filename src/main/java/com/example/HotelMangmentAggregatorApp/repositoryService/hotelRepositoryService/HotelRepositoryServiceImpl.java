package com.example.HotelMangmentAggregatorApp.repositoryService.hotelRepositoryService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HotelMangmentAggregatorApp.entity.Hotel;
import com.example.HotelMangmentAggregatorApp.exception.HotelNotFoundException;
import com.example.HotelMangmentAggregatorApp.repository.HotelRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HotelRepositoryServiceImpl implements HotelRepositoryService {

    @Autowired
    private HotelRepository hotelRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> getHotel(Integer hotelId) {
        return this.hotelRepository.findById(hotelId);
    }

    @Override
    public Hotel getHotelById(Integer hotelId) {
        log.info("Fecthing Hotel Deatils...");
        Optional<Hotel> optionalHotel = this.hotelRepository.findById(hotelId);
        if (optionalHotel.isEmpty()) {
            log.error("No hotel found for hotelId: " + hotelId);
            throw new HotelNotFoundException("No hotel found for hotelId: " + hotelId);
        }
        log.info("Data retrieved Successfully");
        return optionalHotel.get();
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return this.hotelRepository.save(hotel);
    }

    @Override
    @Transactional
    public String deleteHotel(Integer hotelId) {
        log.info("Trying to delete booking enrties where booking.hotelId: " + hotelId);
        // Delete bookings related to the hotel
        entityManager.createQuery("DELETE FROM _booking b WHERE b.hotelBooked.hotelId = :hotelId")
                .setParameter("hotelId", hotelId)
                .executeUpdate();

        log.info("Trying to delete Hotel where hotel.hotel_id: " + hotelId);
        // Delete the hotel itself
        entityManager.createQuery("DELETE FROM _hotel h WHERE h.hotelId = :hotelId")
                .setParameter("hotelId", hotelId)
                .executeUpdate();
        log.info("Hotel Deleted Successfully");
        return "Hotel Deleted successfully";
    }

}
