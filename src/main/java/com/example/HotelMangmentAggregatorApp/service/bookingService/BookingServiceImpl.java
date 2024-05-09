package com.example.HotelMangmentAggregatorApp.service.bookingService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotelMangmentAggregatorApp.dto.BookingDTO;
import com.example.HotelMangmentAggregatorApp.entity.Booking;
import com.example.HotelMangmentAggregatorApp.entity.BookingStatus;
import com.example.HotelMangmentAggregatorApp.entity.Hotel;
import com.example.HotelMangmentAggregatorApp.entity.Role;
import com.example.HotelMangmentAggregatorApp.entity.User;
import com.example.HotelMangmentAggregatorApp.exception.BadAuthenticationException;
import com.example.HotelMangmentAggregatorApp.exception.BadDataProvidedException;
import com.example.HotelMangmentAggregatorApp.exception.HotelBookingException;
import com.example.HotelMangmentAggregatorApp.mapper.BookingMapper;
import com.example.HotelMangmentAggregatorApp.repositoryService.bookingRepositoryService.BookingRepositoryService;
import com.example.HotelMangmentAggregatorApp.repositoryService.hotelRepositoryService.HotelRepositoryService;
import com.example.HotelMangmentAggregatorApp.repositoryService.userRepositoryService.UserRepositoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepositoryService bookingRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private HotelRepositoryService hotelRepositoryService;

    @Override
    public List<BookingDTO> getAllBookings() {
        log.info("Getting all Bookings: ADMIN Only Access");
        return this.bookingRepositoryService.getAllBookings().stream()
                .map(booking -> BookingMapper.mapBooking2DTO(booking))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getAllBookingsByUserName(String email) {
        log.info("Getting all of " + email + " Bookings: ADMIN & CUSTOMER Only Access");
        User user = this.userRepositoryService.getUserByEmail(email);
        log.info("Successfully Retrieved Data");
        return user.getBookingMade().stream().map(booking -> BookingMapper.mapBooking2DTO(booking))
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingDTO(Integer bookingId, String loggedInUserEmail) {
        Booking booking = this.bookingRepositoryService.getBookingObject(bookingId);
        if (!booking.getUserBooked().getEmail().equals(loggedInUserEmail)) {
            log.error(loggedInUserEmail + " trying to access Booking which does not belng to him",
                    BadAuthenticationException.class);
            throw new BadAuthenticationException("You are only allowed to see you bookings");
        }
        return BookingMapper.mapBooking2DTO(this.bookingRepositoryService.getBookingObject(bookingId));
    }

    @Override
    public BookingDTO createBooking(Integer hotelId, String userEmail) {
        User loggedInUser = this.userRepositoryService.getUserByEmail(userEmail);
        if (loggedInUser.getRole() != Role.CUSTOMER) {
            log.error("Only a customer can make Booking");
            throw new BadDataProvidedException("Only a customer can make Booking");
        }
        log.info("Fecthing Hotel Deatils...");
        Hotel hotel = this.hotelRepositoryService.getHotelById(hotelId);
        log.info("Data retrieved Successfully");
        if (hotel.getAvailableRooms() == 0) {
            log.error("Unable to book ... No rooms available...");
            throw new HotelBookingException("Unable to book ... No rooms available...");
        }
        Booking booking = Booking.builder()
                .userBooked(loggedInUser)
                .hotelBooked(hotel)
                .bookingDate(new Date())
                .bookingStatus(BookingStatus.BOOKED)
                .build();
        try {
            booking = this.bookingRepositoryService.createBooking(booking);
        } catch (Exception e) {
            log.error("Error while creating booking.  ", e);
            throw new BadDataProvidedException("Error while creating booking.  " + e.getMessage());
        }
        return BookingMapper.mapBooking2DTO(booking);
    }

    @Override
    public String cancelBooking(Integer bookingId, String managerEmail) {
        Booking booking = this.bookingRepositoryService.getBookingObject(bookingId);
        if (booking.getBookingStatus() == BookingStatus.CHECKED_IN) {
            log.error("Unable to cancel booking as Customer has already checked-in", HotelBookingException.class);
            throw new HotelBookingException("Unable to cancel booking as Customer has already checked-in");
        }
        return this.bookingRepositoryService.deleteBooking(bookingId);
    }

    @Override
    public BookingDTO checkIn(Integer bookingId) {
        log.info("Trying to check-in");
        Booking booking = this.bookingRepositoryService.getBookingObject(bookingId);

        if (booking.getBookingStatus() != BookingStatus.BOOKED) {
            log.error("Unable to check-in" + booking.getUserBooked().getEmail() + "is not in Booked state");
            throw new HotelBookingException("Unable to check-in you are not in Booked state");
        }

        Hotel hotel = this.hotelRepositoryService.getHotelById(booking.getHotelBooked().getHotelId());
        if (hotel.getAvailableRooms() == 0) {
            log.error("Unable to check-in ... No rooms available...");
            throw new HotelBookingException("Unable to check-in ... No rooms available...");
        }

        // Update the available rooms count in the hotel object
        log.info("Rooms available to check-in..Checking-In....");
        try {
            // decrease the available rooms by 1
            log.info("Updating _hotel table with updated number of rooms");
            hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
            // update hotel table
            this.hotelRepositoryService.updateHotel(hotel);

            // update booking table
            booking.setCheckInDate(new Date());
            booking.setBookingStatus(BookingStatus.CHECKED_IN);

            log.info("Updating _booking table with update Check-in status and check-in date");
            Booking checkedInBooking = this.bookingRepositoryService.updateBooking(booking);
            log.info("checkedIn bookingId: " + bookingId);
            return BookingMapper.mapBooking2DTO(checkedInBooking);
        } catch (Exception e) {
            log.error("Unable to check-in, something went wrong" + e.getMessage(), e);
            throw new HotelBookingException("Unable to check-in, something went wrong" + e.getMessage());
        }

    }

    @Override
    public BookingDTO checkOut(Integer bookingId) {
        log.info("Trying to check-out");
        Booking booking = this.bookingRepositoryService.getBookingObject(bookingId);
        if (booking.getBookingStatus() != BookingStatus.CHECKED_IN) {
            log.error("Unable to check-out" + booking.getUserBooked().getEmail() + "is not in Checked-in state");
            throw new HotelBookingException("Unable to check-out you are not in Checked-in state");
        }
        Hotel hotel = this.hotelRepositoryService.getHotelById(booking.getHotelBooked().getHotelId());

        // Update the available rooms count in the hotel object
        log.info("Checking-Out....");

        try {
            // increase the available rooms by 1
            log.info("Updating _hotel table with updated number of rooms");
            hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
            // update hotel table
            this.hotelRepositoryService.updateHotel(hotel);

            // update booking table
            booking.setCheckOutDate(new Date());
            booking.setBookingStatus(BookingStatus.CHECKED_OUT);

            log.info("Updating _booking table with update Check-out status and check-out date");
            Booking checkedOutBooking = this.bookingRepositoryService.updateBooking(booking);
            log.info("checkedOut bookingId: " + bookingId);
            return BookingMapper.mapBooking2DTO(checkedOutBooking);
        } catch (Exception e) {
            log.error("Unable to check-out, something went wrong" + e.getMessage(), e);
            throw new HotelBookingException("Unable to check-out, something went wrong" + e.getMessage());
        }
    }

}
