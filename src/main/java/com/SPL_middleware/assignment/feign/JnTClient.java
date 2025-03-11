package com.SPL_middleware.assignment.feign;

import com.SPL_middleware.assignment.config.FeignConfig;
import com.SPL_middleware.assignment.dto.JntRateRequest;
import feign.Headers;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "JnTClient", url = "https://www.jtexpress.my", configuration = FeignConfig.class)
public interface JnTClient {

    @GetMapping(value = "/shipping-rates")
    Response fetchToken(@RequestHeader("Cookie") String cookie);

    @PostMapping(value = "/shipping-rates", consumes = "application/x-www-form-urlencoded")
    @Headers({
            "Content-Type: application/x-www-form-urlencoded; charset=UTF-8",
//            "Origin: https://www.jtexpress.my",
//            "X-Requested-With: XMLHttpRequest"
    })
    Response getRate(
            @RequestHeader("Cookie") String cookie,
            @RequestBody String requestBody
    );}
