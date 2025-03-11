package com.SPL_middleware.assignment.dto;

import lombok.Getter;
import lombok.Setter;

public class CityLinkRateResponse {
    private Req req;

    public Req getReq() {
        return req;
    }

    public static class Req {
        private CityLinkRateData data;

        public CityLinkRateData getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Req{" +
                    "data=" + data +
                    '}';
        }
    }

    public static class CityLinkRateData {
        private double rate;

        public double getRate() {
            return rate;
        }

        @Override
        public String toString() {
            return "CityLinkRateData{" +
                    "rate=" + rate +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CityLinkRateResponse{" +
                "req=" + req +
                '}';
    }
}
