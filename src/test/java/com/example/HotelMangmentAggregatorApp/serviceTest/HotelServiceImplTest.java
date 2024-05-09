package com.example.HotelMangmentAggregatorApp.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.example.HotelMangmentAggregatorApp.HotelMangmentAggregatorAppApplication;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTO;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTOManager;
import com.example.HotelMangmentAggregatorApp.entity.Hotel;
import com.example.HotelMangmentAggregatorApp.mapper.HotelMapper;
import com.example.HotelMangmentAggregatorApp.repositoryService.hotelRepositoryService.HotelRepositoryService;
import com.example.HotelMangmentAggregatorApp.repositoryService.userRepositoryService.UserRepositoryService;
import com.example.HotelMangmentAggregatorApp.service.bookingService.BookingService;
import com.example.HotelMangmentAggregatorApp.service.hotelService.HotelServiceImpl;

@SpringBootTest(classes = { HotelMangmentAggregatorAppApplication.class })
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ActiveProfiles("test")
@DirtiesContext
public class HotelServiceImplTest {

        @MockBean
        private HotelRepositoryService hotelRepositoryService;

        @MockBean
        private UserRepositoryService userRepositoryService;

        @MockBean
        private BookingService bookingService;

        @InjectMocks
        private HotelServiceImpl hotelService;

        @BeforeEach
        public void setup() {
                MockitoAnnotations.openMocks(this);
        }

        private List<Hotel> getAllHotelsPublicEndPoint() {
                List<Hotel> hotels = new ArrayList<>();

                Hotel hotel1 = Hotel.builder()
                                .hotelId(1)
                                .hotelName("Grand Hotel")
                                .location("New York City, USA")
                                .description(
                                                "The Grand Hotel offers luxurious accommodations with stunning views of the city skyline. Located in the heart of New York City, our hotel provides easy access to top attractions, world-class dining, and vibrant nightlife. Whether you're traveling for business or pleasure, the Grand Hotel promises an unforgettable experience.")
                                .availableRooms(2)
                                .bookings(new HashSet<>())
                                .build();

                Hotel hotel2 = Hotel.builder()
                                .hotelId(2)
                                .hotelName("Luxe Resort")
                                .location("Maldives")
                                .description(
                                                "Experience paradise at the Luxe Resort in the Maldives. Nestled among pristine beaches and crystal-clear waters, our resort offers luxurious accommodations, world-class dining, and unparalleled relaxation. Indulge in spa treatments, water sports, and breathtaking sunsets during your stay.")
                                .availableRooms(21)
                                .bookings(new HashSet<>())
                                .build();
                hotels.add(hotel1);
                hotels.add(hotel2);
                return hotels;
        }

        @Test
        public void getAllAvailableHotelsForCustomersTest() {
                when(hotelRepositoryService.getAllHotels()).thenReturn(this.getAllHotelsPublicEndPoint());

                List<HotelDTO> returnedHotels = this.getAllHotelsPublicEndPoint().stream()
                                .filter(hotel -> hotel.getAvailableRooms() > 0)
                                .map(hotel -> HotelMapper.mapHotel2DTO(hotel))
                                .collect(Collectors.toList());
                assertEquals(returnedHotels.size(), hotelService.getAllAvailableHotelsForCustomers().size());
                verify(hotelRepositoryService, times(1)).getAllHotels();
        }

        @Test
        public void getAllAvailableHotelsForADMINTest() {
                when(hotelRepositoryService.getAllHotels()).thenReturn(this.getAllHotelsPublicEndPoint());

                List<HotelDTOManager> returnedHotels = this.getAllHotelsPublicEndPoint().stream()
                                .map(hotel -> HotelMapper.mapHotel2DTOForNonCustomer(hotel))
                                .collect(Collectors.toList());
                assertEquals(returnedHotels.size(), hotelService.getAllHotelsForADMIN().size());
                verify(hotelRepositoryService, times(1)).getAllHotels();
        }

}
