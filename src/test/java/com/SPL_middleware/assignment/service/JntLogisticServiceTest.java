package com.SPL_middleware.assignment.service;

import com.SPL_middleware.assignment.dto.JntRateRequest;
import com.SPL_middleware.assignment.dto.RateResponse;
import com.SPL_middleware.assignment.feign.JnTClient;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JntLogisticServiceTest {

    String mockHTML=" <div class=\"table-responsive shadow d-block d-sm-none\">\n" +
            "            <table class=\"table table-bordered mb-0\">\n" +
            "                <tbody>\n" +
            "                    <tr class=\"d-flex\">\n" +
            "                        <th class=\"col-4\">Goods Type</th>\n" +
            "                        <td class=\"col-4\">Parcel</td>\n" +
            "                        <td class=\"col-4\">Document</td>\n" +
            "\n" +
            "                    </tr>\n" +
            "                    <tr class=\"d-flex\">\n" +
            "                        <th class=\"col-4\">Shipping Rates</th>\n" +
            "                        <td class=\"col-4\">\n" +
            "                            32.00\n" +
            "                        </td>\n" +
            "                        <td class=\"col-4\">\n" +
            "                            N/A\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr class=\"d-flex\">\n" +
            "                        <th class=\"col-4\">Insurance Charges</th>\n" +
            "                        <td class=\"col-4\">0.00</td>\n" +
            "                        <td class=\"col-4\">0.00</td>\n" +
            "                    </tr>\n" +
            "                                            <tr class=\"d-flex\">\n" +
            "                            <th class=\"col-4\">Total (incl. Tax)</th>\n" +
            "                            <td class=\"col-4\">\n" +
            "                                33.92\n" +
            "                            </td>\n" +
            "                            <td class=\"col-4\">\n" +
            "                                N/A\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "                                    </tbody>\n" +
            "            </table>\n" +
            "        </div>\n" +
            "        <div class=\"table-responsive shadow d-none d-sm-block\">\n" +
            "            <table class=\"table table-bordered mb-0\">\n" +
            "                <thead>\n" +
            "                    <tr class=\"\">\n" +
            "                        <th class=\" col-3\">Goods Type\n" +
            "                        </th>\n" +
            "                        <th class=\"col-3\">Shipping Rates</th>\n" +
            "                        <th class=\"col-3\">Insurance Charges</th>\n" +
            "                                                    <th class=\"col-3\">Total (incl. Tax)</th>\n" +
            "                                            </tr>\n" +
            "                </thead>\n" +
            "                <tbody>\n" +
            "                    <tr class=\"\">\n" +
            "                        <td class=\" col-3\">Parcel</td>\n" +
            "                        <td class=\"col-3\">\n" +
            "                            32.00\n" +
            "                        </td>\n" +
            "                        <td class=\"col-3\">0.00</td>\n" +
            "                                                    <td class=\"col-3\">\n" +
            "                                33.92\n" +
            "                            </td>\n" +
            "                                            </tr>\n" +
            "                    <tr class=\"\">\n" +
            "                        <td class=\" col-3\">Document\n" +
            "                        </td>\n" +
            "                        <td class=\"col-3\">\n" +
            "                            N/A\n" +
            "                        </td>\n" +
            "                        <td class=\"col-3\">0.00</td>\n" +
            "                                                    <td class=\"col-3\">\n" +
            "                                N/A\n" +
            "                            </td>\n" +
            "                                            </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </div>\n" +
            "    ";

    @Mock
    private JnTClient jnTClient;

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private JntLogisticService jntLogisticService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRate_Success() throws Exception {
        // Mock request
        JntRateRequest request = new JntRateRequest();
        request.setShippingRatesType("standard");
        request.setSenderPostcode("12345");
        request.setReceiverPostcode("67890");
        request.setDestinationCountry("MY");
        request.setWeight(1.5);
        request.setLength(10.0);
        request.setWidth(5.0);
        request.setHeight(5.0);
        request.setInsurance("");
        request.setItemValue(100.0);

        // Scenario: CACHE IS NULL
        when(cacheService.getFromCache(anyString())).thenReturn(null);

        Request mockRequest = Request.create(
                Request.HttpMethod.GET,
                "http://example.com",
                new HashMap<>(),
                null,
                null,
                null
        );
        // Mock token response
        Response tokenResponse = Response.builder()
                .status(200)
                .headers(Collections.singletonMap("Set-Cookie", Collections.singletonList("cookie_data")))
                .body("<input name=\"_token\" value=\"mockToken\">", StandardCharsets.UTF_8)
                .request(mockRequest)
                .build();


        when(jnTClient.fetchToken(anyString())).thenReturn(tokenResponse);

        Response htmlResponse = Response.builder()
                .status(200)
                .body(mockHTML, StandardCharsets.UTF_8)
                .request(mockRequest)
                .build();


        when(jnTClient.getRate(anyString(), anyString())).thenReturn(htmlResponse);

        assertNotNull(request, "JntRateRequest should not be null");

        // Call the method
        RateResponse rateResponse = jntLogisticService.getRate(request);

        // Assertions
        assertNotNull(rateResponse);
        assertEquals("J&T", rateResponse.getCourier());
//        assertEquals(33.92, rateResponse.getRate());

    }
}
