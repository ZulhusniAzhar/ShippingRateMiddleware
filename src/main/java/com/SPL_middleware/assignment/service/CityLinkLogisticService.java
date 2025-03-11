package com.SPL_middleware.assignment.service;

import com.SPL_middleware.assignment.dto.CityLinkRateRequest;
import com.SPL_middleware.assignment.dto.CityLinkRateResponse;
import com.SPL_middleware.assignment.dto.RateResponse;
import com.SPL_middleware.assignment.feign.CityLinkClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CityLinkLogisticService implements LogisticService{
    private static final Logger logger = LoggerFactory.getLogger(CityLinkLogisticService.class);
    private final CityLinkClient cityLinkClient;
    private final CacheService cacheService;

    public CityLinkLogisticService(CityLinkClient cityLinkClient, CacheService cacheService) {
        this.cityLinkClient = cityLinkClient;
        this.cacheService = cacheService;
    }

    public RateResponse getRate(Object request) {
        CityLinkRateRequest cityLinkRateRequest=(CityLinkRateRequest) request;
        logger.info("[Request][CityLink]:"+cityLinkRateRequest);

        //Flow
        //1- Check whether same key exist in redis cache
        String cacheKey = generateCacheKey(cityLinkRateRequest);
        Object cachedRate = cacheService.getFromCache(cacheKey);
        if (cachedRate != null) {
            logger.info("Retrieved from CityLink cache: " + cachedRate);
            return new RateResponse("CityLink", (Double) cachedRate);
        }

        //2- Else, call API
        CityLinkRateResponse response = cityLinkClient.getRate(cityLinkRateRequest);
        logger.info("[Response][CityLink]:"+response);

        cacheService.saveToCache(cacheKey, response.getReq().getData().getRate());

        return new RateResponse("CityLink", response.getReq().getData().getRate());
    }

    private String generateCacheKey(CityLinkRateRequest request) {
        return String.format("citylink:rate:%s:%s:%s:%s:%s:%s:%.1f:%.1f:%.1f:%d:%.1f:%.1f",
                request.getOriginCountry(),
                request.getOriginState(),
                request.getOriginPostcode(),
                request.getDestinationCountry(),
                request.getDestinationState(),
                request.getDestinationPostcode(),
                request.getLength(),
                request.getWidth(),
                request.getHeight(),
                request.getSelectedType(),
                request.getParcelWeight(),
                request.getDocumentWeight()
        );
    }

}