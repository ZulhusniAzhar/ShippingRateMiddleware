package com.SPL_middleware.assignment.dto.mapper;

import com.SPL_middleware.assignment.dto.JntRateRequest;
import com.SPL_middleware.assignment.dto.RateRequest;
import org.springframework.stereotype.Component;

@Component
public class JntRateRequestMapper implements RateRequestMapper {

    @Override
    public Object map(RateRequest rateRequest) {
        JntRateRequest jntRateRequest = new JntRateRequest();
        jntRateRequest.setShippingRatesType(rateRequest.getShippingRatesType());
        jntRateRequest.setSenderPostcode(rateRequest.getOriginPostcode());
        jntRateRequest.setReceiverPostcode(rateRequest.getDestinationPostcode());
        jntRateRequest.setDestinationCountry(rateRequest.getDestinationCountry());
        jntRateRequest.setShippingType(rateRequest.getShippingType());
        jntRateRequest.setWeight(rateRequest.getParcelWeight());
        jntRateRequest.setLength(rateRequest.getLength());
        jntRateRequest.setWidth(rateRequest.getWidth());
        jntRateRequest.setHeight(rateRequest.getHeight());
        jntRateRequest.setInsurance(rateRequest.getInsurance());
        jntRateRequest.setItemValue(rateRequest.getItemValue());
        return jntRateRequest;
    }
}
