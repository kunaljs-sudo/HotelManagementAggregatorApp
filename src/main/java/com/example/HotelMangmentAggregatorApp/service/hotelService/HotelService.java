package com.example.HotelMangmentAggregatorApp.service.hotelService;

import java.util.List;

import com.example.HotelMangmentAggregatorApp.dto.BookingDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTOManager;
import com.example.HotelMangmentAggregatorApp.exchanges.CreateHotelRequest;
import com.example.HotelMangmentAggregatorApp.exchanges.UpdateHotelRequest;

public interface HotelService {
    public List<HotelDTO> getAllAvailableHotelsForCustomers();

    public List<HotelDTOManager> getAllHotelsForADMIN();

    public HotelDTO getHotelDTO(Integer hotelId);

    public HotelDTOManager getHotelForManager(Integer hotelId, String loggedInUserEmail);

    public HotelDTOManager createHotel(CreateHotelRequest createHotelRequest);

    public BookingDTO bookHotel(Integer hotelId, String loggedInUserEmail);

    public HotelDTOManager updateHotelData(Integer hotelId, String userEmail, UpdateHotelRequest updateHotelRequest);

    public String deleteHotel(Integer hotelId);

}
