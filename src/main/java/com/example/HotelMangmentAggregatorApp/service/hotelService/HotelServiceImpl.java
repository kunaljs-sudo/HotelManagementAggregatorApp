package com.example.HotelMangmentAggregatorApp.service.hotelService;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HotelMangmentAggregatorApp.dto.BookingDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTOManager;
import com.example.HotelMangmentAggregatorApp.entity.Hotel;
import com.example.HotelMangmentAggregatorApp.entity.Role;
import com.example.HotelMangmentAggregatorApp.entity.User;
import com.example.HotelMangmentAggregatorApp.exception.BadAuthenticationException;
import com.example.HotelMangmentAggregatorApp.exception.BadDataProvidedException;
import com.example.HotelMangmentAggregatorApp.exchanges.CreateHotelRequest;
import com.example.HotelMangmentAggregatorApp.exchanges.UpdateHotelRequest;
import com.example.HotelMangmentAggregatorApp.mapper.HotelMapper;
import com.example.HotelMangmentAggregatorApp.repositoryService.hotelRepositoryService.HotelRepositoryService;
import com.example.HotelMangmentAggregatorApp.repositoryService.userRepositoryService.UserRepositoryService;
import com.example.HotelMangmentAggregatorApp.service.bookingService.BookingService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepositoryService hotelRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private BookingService bookingService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<HotelDTO> getAllAvailableHotelsForCustomers() {
        log.info("Fecthing all Hotels for Customer");
        List<HotelDTO> hotels = this.hotelRepositoryService.getAllHotels().stream()
                .filter(hotel -> hotel.getAvailableRooms() > 0)
                .map(hotel -> HotelMapper.mapHotel2DTO(hotel))
                .collect(Collectors.toList());
        log.info("Data retrieved Successfully");
        return hotels;

    }

    @Override
    public List<HotelDTOManager> getAllHotelsForADMIN() {
        log.info("Fecthing all Hotels for ADMIN");
        List<HotelDTOManager> hotels = this.hotelRepositoryService.getAllHotels().stream()
                .map(hotel -> HotelMapper.mapHotel2DTOForNonCustomer(hotel))
                .collect(Collectors.toList());
        log.info("Data retrieved Successfully");
        return hotels;
    }

    @Override
    public HotelDTO getHotelDTO(Integer hotelId) {
        Hotel hotel = this.hotelRepositoryService.getHotelById(hotelId);
        return HotelMapper.mapHotel2DTO(hotel);
    }

    @Override
    public HotelDTOManager getHotelForManager(Integer hotelId, String loggedInUserEmail) {

        Hotel hotel = this.hotelRepositoryService.getHotelById(hotelId);
        User loggedInUser = this.userRepositoryService.getUserByEmail(loggedInUserEmail);
        // if loggedInUser is customer then unauthorised
        if (loggedInUser.getRole() == Role.CUSTOMER) {
            log.info(loggedInUserEmail + " is not authorised to access details of Hotel: " + hotel.getHotelName());
            throw new BadAuthenticationException(
                    "You are not authorised to access details of Hotel: " + hotel.getHotelName());
        }
        return HotelMapper.mapHotel2DTOForNonCustomer(hotel);
    }

    @Override
    public HotelDTOManager createHotel(CreateHotelRequest createHotelRequest) {
        if (createHotelRequest == null) {
            log.error("Bad Data Provided");
            throw new BadDataProvidedException("Bad Data Provided");
        }

        log.info("Building Hotel object");
        Hotel hotel = Hotel.builder()
                .hotelName(createHotelRequest.getHotelName())
                .location(createHotelRequest.getLocation())
                .description(createHotelRequest.getDescription())
                .availableRooms(
                        createHotelRequest.getAvailableRooms() == null ? 0
                                : createHotelRequest.getAvailableRooms())
                .bookings(new HashSet<>())
                .build();
        log.info("Saving Hotel to DataBase..");
        try {
            hotel = this.hotelRepositoryService.createHotel(hotel);
        } catch (Exception e) {
            log.error("Error occurred while creating Hotel: " + e.getMessage(), e);
            throw new BadDataProvidedException("Error occurred while creating Hotel: " + e.getMessage());
        }
        return HotelMapper.mapHotel2DTOForNonCustomer(hotel);
    }

    @Override
    public HotelDTOManager updateHotelData(Integer hotelId, String userEmail, UpdateHotelRequest updateHotelRequest) {
        if (updateHotelRequest == null) {
            log.error("Bad Data Provided for UpdateHotelRequest");
            throw new BadDataProvidedException("Bad Data Provided for UpdateHotelRequest");
        }

        Hotel hotel = this.hotelRepositoryService.getHotelById(hotelId);
        User loggedInUser = this.userRepositoryService.getUserByEmail(userEmail);
        // if loggedInUser is customer then unauthorised
        if (loggedInUser.getRole() == Role.CUSTOMER) {
            log.info(userEmail + " is unauthorised to update details of Hotel: " + hotel.getHotelName());
            throw new BadAuthenticationException("You are not manager of this Hotel: " + hotel.getHotelName());
        }
        hotel.setHotelName(updateHotelRequest.getHotelName());
        hotel.setLocation(updateHotelRequest.getLocation());
        hotel.setDescription(updateHotelRequest.getDescription());

        log.info("Updating Data...");
        try {
            hotel = this.hotelRepositoryService.updateHotel(hotel);
        } catch (Exception e) {
            log.error("Error occurred while udpating Hotel Detials: " + e.getMessage(), e);
            throw new BadDataProvidedException("Error occurred while udpating Hotel Detials: " + e.getMessage());
        }

        log.info("Detials Updated Successfully");
        return HotelMapper.mapHotel2DTOForNonCustomer(hotel);
    }

    @Override
    @Transactional
    public String deleteHotel(Integer hotelId) {
        return this.hotelRepositoryService.deleteHotel(hotelId);
    }

    @Override
    public BookingDTO bookHotel(Integer hotelId, String loggedInUserEmail) {
        log.info("Trying to book hotel from HotelService", HotelServiceImpl.class);
        return bookingService.createBooking(hotelId, loggedInUserEmail);
    }

}
