package com.SPL_middleware.assignment.dto.mapper;

import com.SPL_middleware.assignment.dto.JntRateRequest;
import com.SPL_middleware.assignment.dto.CityLinkRateRequest;
import com.SPL_middleware.assignment.dto.RateRequest;
import org.springframework.stereotype.Component;

@Component
public class CityLinkRateRequestMapper implements RateRequestMapper {
    @Override
    public Object map(RateRequest rateRequest) {
        CityLinkRateRequest cityLinkRateRequest = new CityLinkRateRequest();
        cityLinkRateRequest.setOriginCountry(rateRequest.getOriginCountry());
        cityLinkRateRequest.setOriginState(rateRequest.getOriginState());
        cityLinkRateRequest.setOriginPostcode(rateRequest.getOriginPostcode());
        cityLinkRateRequest.setDestinationCountry(rateRequest.getDestinationCountry());
        cityLinkRateRequest.setDestinationState(rateRequest.getDestinationState());
        cityLinkRateRequest.setDestinationPostcode(rateRequest.getDestinationPostcode());
        cityLinkRateRequest.setSelectedType(rateRequest.getSelectedType());
        cityLinkRateRequest.setLength(rateRequest.getLength());
        cityLinkRateRequest.setWidth(rateRequest.getWidth());
        cityLinkRateRequest.setHeight(rateRequest.getHeight());
        cityLinkRateRequest.setParcelWeight(rateRequest.getParcelWeight());
        cityLinkRateRequest.setDocumentWeight(rateRequest.getDocumentWeight());
        return cityLinkRateRequest;
    }
}
