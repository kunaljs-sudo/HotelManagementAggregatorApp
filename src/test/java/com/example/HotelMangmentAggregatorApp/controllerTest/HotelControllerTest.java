package com.example.HotelMangmentAggregatorApp.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.HotelMangmentAggregatorApp.HotelMangmentAggregatorAppApplication;
import com.example.HotelMangmentAggregatorApp.controller.HotelController;
import com.example.HotelMangmentAggregatorApp.dto.HotelDTO;
import com.example.HotelMangmentAggregatorApp.entity.Hotel;
import com.example.HotelMangmentAggregatorApp.mapper.HotelMapper;
import com.example.HotelMangmentAggregatorApp.service.hotelService.HotelService;

@SpringBootTest(classes = { HotelMangmentAggregatorAppApplication.class })
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@DirtiesContext
@ActiveProfiles("test")
public class HotelControllerTest {
        private static final String hotelEnpoint = "/hotels";

        @MockBean
        private HotelService hotelService;

        @InjectMocks
        private HotelController hotelController;

        private MockMvc mvc;

        @BeforeEach
        public void setup() {

                MockitoAnnotations.openMocks(this);

                mvc = MockMvcBuilders.standaloneSetup(hotelController).build();
        }

        private List<HotelDTO> getAllHotelsPublicEndPoint() {
                List<Hotel> hotels = new ArrayList<>();

                Hotel hotel1 = Hotel.builder()
                                .hotelId(1)
                                .hotelName("Grand Hotel")
                                .location("New York City, USA")
                                .description(
                                                "The Grand Hotel offers luxurious accommodations with stunning views of the city skyline. Located in the heart of New York City, our hotel provides easy access to top attractions, world-class dining, and vibrant nightlife. Whether you're traveling for business or pleasure, the Grand Hotel promises an unforgettable experience.")
                                .availableRooms(2)
                                .build();

                Hotel hotel2 = Hotel.builder()
                                .hotelId(2)
                                .hotelName("Luxe Resort")
                                .location("Maldives")
                                .description(
                                                "Experience paradise at the Luxe Resort in the Maldives. Nestled among pristine beaches and crystal-clear waters, our resort offers luxurious accommodations, world-class dining, and unparalleled relaxation. Indulge in spa treatments, water sports, and breathtaking sunsets during your stay.")
                                .availableRooms(21)
                                .build();
                hotels.add(hotel1);
                hotels.add(hotel2);
                return hotels.stream().map(hotel -> HotelMapper.mapHotel2DTO(hotel)).collect(Collectors.toList());
        }

        @Test
        public void getAllHotelsForCustomerTest() throws Exception {
                URI uri = UriComponentsBuilder.fromPath(hotelEnpoint).build().toUri();
                assertEquals(uri.toString(), hotelEnpoint);

                when(hotelService.getAllAvailableHotelsForCustomers()).thenReturn(this.getAllHotelsPublicEndPoint());

                MockHttpServletResponse response = mvc.perform(get(uri.toString()).accept(MediaType.APPLICATION_JSON))
                                .andReturn().getResponse();

                assertEquals(HttpStatus.OK.value(), response.getStatus());
                verify(hotelService, times(1)).getAllAvailableHotelsForCustomers();
        }

}
