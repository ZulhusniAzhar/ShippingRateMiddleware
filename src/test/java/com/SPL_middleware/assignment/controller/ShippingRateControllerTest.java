package com.SPL_middleware.assignment.controller;

import com.SPL_middleware.assignment.dto.RateRequest;
import com.SPL_middleware.assignment.dto.RateResponse;
import com.SPL_middleware.assignment.service.RateCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ShippingRateControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RateCalcService rateCalcService;

    @InjectMocks
    private ShippingRateController shippingRateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shippingRateController).build();
    }

    @Test
    void testGetRatesSuccess() throws Exception {
//        RateRequest request = new RateRequest();
//        request.setOriginCountry("MY");
//        request.setOriginState("Melaka");
//        request.setOriginPostcode("83300");
//        request.setDestinationCountry("MY");
//        request.setDestinationState("Kuala Lumpur");
//        request.setDestinationPostcode("52000");
//        request.setLength(12);
//        request.setWidth(12);
//        request.setHeight(34);
//        request.setSelectedType(2);
//        request.setParcelWeight(16);
//        request.setDocumentWeight(24);
//        request.setShippingRatesType("domestic");
//        request.setShippingType("EZ");
//        request.setItemValue(0);
//        request.setCouriers(List.of("citylink", "jnt"));

        RateResponse response = new RateResponse("CityLink",53.0);

        List<RateResponse> mockResponse = Collections.singletonList(response);
        when(rateCalcService.getAllRates(any())).thenReturn(mockResponse);

        mockMvc.perform(post("/abc/logistics/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "origin_country": "MY",
              "origin_state": "Melaka",
              "origin_postcode": "83300",
              "destination_country": "MY",
              "destination_state": "Kuala Lumpur",
              "destination_postcode": "52000",
              "length": 12,
              "width": 12,
              "height": 34,
              "selected_type": 2,
              "parcel_weight": 16,
              "document_weight": 24,
              "shipping_rates_type": "domestic",
              "shipping_type": "EZ",
              "item_value": 0,
              "couriers": ["citylink", "jnt"]
            }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courier").value("CityLink"))
                .andExpect(jsonPath("$[0].rate").value(53.0));



    }
    @Test
    void testGetRates_Failure_InvalidRequest() throws Exception {
        doThrow(new RuntimeException("Invalid request data"))
                .when(rateCalcService).getAllRates(any());

        mockMvc.perform(post("/abc/logistics/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Empty JSON
                .andExpect(status().isBadRequest()); // Expect 400

    }

}

