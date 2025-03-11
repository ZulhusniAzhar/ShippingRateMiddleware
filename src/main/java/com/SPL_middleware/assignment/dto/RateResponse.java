package com.SPL_middleware.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class RateResponse {

    @JsonProperty("courier")
    private String courier;

    @JsonProperty("rate")
    private Double rate;

    public RateResponse(String courier, double rate) {
        this.courier = courier;
        this.rate = rate;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return String.format("{\"courier\": \"%s\", \"rate\": %.2f}", courier, rate);
    }
}
