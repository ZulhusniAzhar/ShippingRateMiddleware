package com.SPL_middleware.assignment.service;

import com.SPL_middleware.assignment.dto.CityLinkRateRequest;
import com.SPL_middleware.assignment.dto.CityLinkRateResponse;
import com.SPL_middleware.assignment.dto.RateResponse;
import com.SPL_middleware.assignment.feign.CityLinkClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CityLinkLogisticServiceTest {

    @Mock
    private CityLinkClient cityLinkClient;

    @InjectMocks
    private CityLinkLogisticService cityLinkLogisticService;

    @Mock
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRate_Success() {
        // Mock request & response
        CityLinkRateRequest request = new CityLinkRateRequest();
        CityLinkRateResponse response = new CityLinkRateResponse();
        CityLinkRateResponse.Req req=new CityLinkRateResponse.Req();
        CityLinkRateResponse.CityLinkRateData cityLinkRateData=new CityLinkRateResponse.CityLinkRateData();


        cityLinkRateData.setRate(50.0);
        req.setData(cityLinkRateData);
        response.setReq(req);

        // Scenario: CACHE IS NULL
        when(cacheService.getFromCache(anyString())).thenReturn(null);

        // So, API will be called with this response
        when(cityLinkClient.getRate(request)).thenReturn(response);

        // Call the method
        RateResponse rateResponse = cityLinkLogisticService.getRate(request);

        // Assertions
        assertNotNull(rateResponse);
        assertEquals("CityLink", rateResponse.getCourier());
        assertEquals(50.0, rateResponse.getRate());

    }
}
