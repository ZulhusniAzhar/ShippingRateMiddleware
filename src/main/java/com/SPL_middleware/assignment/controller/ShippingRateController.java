package com.SPL_middleware.assignment.controller;

import com.SPL_middleware.assignment.dto.RateRequest;
import com.SPL_middleware.assignment.dto.RateResponse;
import com.SPL_middleware.assignment.service.RateCalcService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abc/logistics")
public class ShippingRateController {
        private final RateCalcService rateCalcService;

        public ShippingRateController(RateCalcService rateCalcService) {
                this.rateCalcService = rateCalcService;
        }

        @PostMapping("/rate")
        public List<RateResponse> getRates(@RequestBody RateRequest request) {
                return rateCalcService.getAllRates(request);
        }
}
