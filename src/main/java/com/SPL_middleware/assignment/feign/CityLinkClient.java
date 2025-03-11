package com.SPL_middleware.assignment.feign;

import com.SPL_middleware.assignment.dto.CityLinkRateRequest;
import com.SPL_middleware.assignment.dto.CityLinkRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "citylinkClient", url = "https://www.citylinkexpress.com/wp-json/wp/v2")
public interface CityLinkClient {
    @PostMapping(value = "/getShippingRate", consumes = "application/json", produces = "application/json")
    CityLinkRateResponse getRate(@RequestBody CityLinkRateRequest request);
}

