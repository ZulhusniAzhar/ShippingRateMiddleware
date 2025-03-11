package com.SPL_middleware.assignment.service;

import com.SPL_middleware.assignment.dto.mapper.CityLinkRateRequestMapper;
import com.SPL_middleware.assignment.dto.mapper.JntRateRequestMapper;
import com.SPL_middleware.assignment.dto.mapper.RateRequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogisticServiceFactory {

    private final JntLogisticService jntLogisticService;
    private final CityLinkLogisticService cityLinkLogisticService;

    private final JntRateRequestMapper jntRateRequestMapper;
    private final CityLinkRateRequestMapper cityLinkRateRequestMapper;

    public LogisticServiceFactory(
            JntLogisticService jntLogisticService,
            CityLinkLogisticService cityLinkLogisticService,
            JntRateRequestMapper jntRateRequestMapper,
            CityLinkRateRequestMapper cityLinkRateRequestMapper) {

        this.jntLogisticService = jntLogisticService;
        this.cityLinkLogisticService = cityLinkLogisticService;
        this.jntRateRequestMapper = jntRateRequestMapper;
        this.cityLinkRateRequestMapper = cityLinkRateRequestMapper;
    }

    public LogisticService getLogisticService(String courier) {
        switch (courier.toLowerCase()) {
            case "jnt":
                return jntLogisticService;
            case "citylink":
                return cityLinkLogisticService;
            default:
                throw new IllegalArgumentException("Unsupported courier: " + courier);
        }
    }

    public RateRequestMapper getRateRequestMapper(String courier) {
        switch (courier.toLowerCase()) {
            case "jnt":
                return jntRateRequestMapper;
            case "citylink":
                return cityLinkRateRequestMapper;
            default:
                throw new IllegalArgumentException("Unsupported courier: " + courier);
        }
    }

}
