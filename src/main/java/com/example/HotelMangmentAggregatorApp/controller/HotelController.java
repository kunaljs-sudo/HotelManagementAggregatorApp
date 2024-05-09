package com.example.HotelMangmentAggregatorApp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelMangmentAggregatorApp.dto.BookingDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTOManager;
import com.example.HotelMangmentAggregatorApp.exchanges.UpdateHotelRequest;
import com.example.HotelMangmentAggregatorApp.service.hotelService.HotelService;

@RestController
@RequestMapping(HotelController.endPoint)
public class HotelController {
    public final static String endPoint = "/hotels";

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<HotelDTO> getAllHotelsForCustomer() {
        return hotelService.getAllAvailableHotelsForCustomers();
    }

    @GetMapping("/{hotelId}")
    @PreAuthorize("permitAll()")
    public HotelDTO getHotel(@PathVariable Integer hotelId) {
        return hotelService.getHotelDTO(hotelId);
    }

    @GetMapping("/hotel/{hotelId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public HotelDTOManager getHotel(@PathVariable Integer hotelId, Principal principal) {
        return hotelService.getHotelForManager(hotelId, principal.getName());
    }

    @PostMapping("/{hotelId}/book")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public BookingDTO bookHotel(@PathVariable Integer hotelId, Principal principal) {
        return hotelService.bookHotel(hotelId, principal.getName());
    }

    @PutMapping("/{hotelId}/update")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public HotelDTOManager updateHotel(@PathVariable Integer hotelId,
            @RequestBody UpdateHotelRequest updateHotelRequest,
            Principal principal) {
        return hotelService.updateHotelData(hotelId, principal.getName(), updateHotelRequest);
    }

}
