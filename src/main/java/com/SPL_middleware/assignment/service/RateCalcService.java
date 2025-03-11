package com.SPL_middleware.assignment.service;

import com.SPL_middleware.assignment.dto.CityLinkRateRequest;
import com.SPL_middleware.assignment.dto.JntRateRequest;
import com.SPL_middleware.assignment.dto.RateRequest;
import com.SPL_middleware.assignment.dto.RateResponse;
import com.SPL_middleware.assignment.dto.mapper.RateRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateCalcService {
    private final LogisticServiceFactory logisticServiceFactory;

    public RateCalcService(LogisticServiceFactory logisticServiceFactory) {
        this.logisticServiceFactory = logisticServiceFactory;
    }

    public List<RateResponse> getAllRates(RateRequest request) {
        List<RateResponse> responses = new ArrayList<>();

        for (String courier : request.getCouriers()) {
            LogisticService logisticService = logisticServiceFactory.getLogisticService(courier);

            if (logisticService != null) {
                RateRequestMapper rateRequestMapper = logisticServiceFactory.getRateRequestMapper(courier);
                Object mappedRequest = rateRequestMapper.map(request);
                RateResponse rateResponse = logisticService.getRate(mappedRequest);
                responses.add(rateResponse);
            }
        }
        return responses;
    }
}