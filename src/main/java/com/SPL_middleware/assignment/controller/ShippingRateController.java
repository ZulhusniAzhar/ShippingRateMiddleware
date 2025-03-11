package com.SPL_middleware.assignment.controller;

import com.SPL_middleware.assignment.dto.RateRequest;
import com.SPL_middleware.assignment.dto.RateResponse;
import com.SPL_middleware.assignment.service.RateCalcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abc/logistics")
@Tag(name = "Logistics API", description = "Endpoints for logistics rate calculation")
public class ShippingRateController {
        private final RateCalcService rateCalcService;

        public ShippingRateController(RateCalcService rateCalcService) {
                this.rateCalcService = rateCalcService;
        }

        @Operation(summary = "Get All Rate", description = "Fetches rate based on provided couriers")
        @PostMapping("/rate")
        public List<RateResponse> getRates(@Valid @RequestBody RateRequest request) {
                return rateCalcService.getAllRates(request);
        }
}
