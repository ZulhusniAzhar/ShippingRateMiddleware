package com.SPL_middleware.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class RateRequest {
        @JsonProperty("origin_country")
        private String originCountry;

        @JsonProperty("origin_state")
        private String originState;

        @JsonProperty("origin_postcode")
        private String originPostcode;

        @JsonProperty("destination_country")
        private String destinationCountry;

        @JsonProperty("destination_state")
        private String destinationState;

        @JsonProperty("destination_postcode")
        private String destinationPostcode;

        @JsonProperty("length")
        private double length;

        @JsonProperty("width")
        private double width;

        @JsonProperty("height")
        private double height;

        @JsonProperty("selected_type")
        private int selectedType;

        @JsonProperty("parcel_weight")
        private double parcelWeight;

        @JsonProperty("document_weight")
        private double documentWeight;

        @JsonProperty("shipping_rates_type")
        private String shippingRatesType;

        @JsonProperty("insurance")
        private String insurance;

        @JsonProperty("item_value")
        private double itemValue;

        @JsonProperty("shipping_type")
        private String shippingType ;

        @JsonProperty("couriers")
        private List<String> couriers;

        public String getShippingType() {
                return shippingType;
        }

        public void setShippingType(String shippingType) {
                this.shippingType = shippingType;
        }

        public String getShippingRatesType() {
                return shippingRatesType;
        }

        public void setShippingRatesType(String shippingRatesType) {
                this.shippingRatesType = shippingRatesType;
        }

        public String getInsurance() {
                return insurance;
        }

        public void setInsurance(String insurance) {
                this.insurance = insurance;
        }

        public double getItemValue() {
                return itemValue;
        }

        public void setItemValue(double itemValue) {
                this.itemValue = itemValue;
        }

        public String getOriginCountry() {
                return originCountry;
        }

        public String getOriginState() {
                return originState;
        }

        public String getOriginPostcode() {
                return originPostcode;
        }

        public String getDestinationCountry() {
                return destinationCountry;
        }

        public String getDestinationState() {
                return destinationState;
        }

        public String getDestinationPostcode() {
                return destinationPostcode;
        }

        public double getLength() {
                return length;
        }

        public double getWidth() {
                return width;
        }

        public double getHeight() {
                return height;
        }

        public int getSelectedType() {
                return selectedType;
        }

        public double getParcelWeight() {
                return parcelWeight;
        }

        public double getDocumentWeight() {
                return documentWeight;
        }

        public List<String> getCouriers() {
                return couriers;
        }
}
