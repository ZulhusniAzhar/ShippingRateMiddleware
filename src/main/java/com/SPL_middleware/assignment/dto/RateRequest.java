package com.SPL_middleware.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class RateRequest {
        @NotBlank
        @JsonProperty("origin_country")
        private String originCountry;

        @NotBlank
        @JsonProperty("origin_state")
        private String originState;

        @NotBlank
        @JsonProperty("origin_postcode")
        private String originPostcode;

        @NotBlank
        @JsonProperty("destination_country")
        private String destinationCountry;

        @NotBlank
        @JsonProperty("destination_state")
        private String destinationState;

        @NotBlank
        @JsonProperty("destination_postcode")
        private String destinationPostcode;

        @Positive
        @JsonProperty("length")
        private double length;

        @Positive
        @JsonProperty("width")
        private double width;

        @Positive
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

        @NotEmpty
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

        public void setOriginCountry(String originCountry) {
                this.originCountry = originCountry;
        }

        public void setOriginState(String originState) {
                this.originState = originState;
        }

        public void setOriginPostcode(String originPostcode) {
                this.originPostcode = originPostcode;
        }

        public void setDestinationCountry(String destinationCountry) {
                this.destinationCountry = destinationCountry;
        }

        public void setDestinationState(String destinationState) {
                this.destinationState = destinationState;
        }

        public void setDestinationPostcode(String destinationPostcode) {
                this.destinationPostcode = destinationPostcode;
        }

        public void setLength(double length) {
                this.length = length;
        }

        public void setWidth(double width) {
                this.width = width;
        }

        public void setHeight(double height) {
                this.height = height;
        }

        public void setSelectedType(int selectedType) {
                this.selectedType = selectedType;
        }

        public void setParcelWeight(double parcelWeight) {
                this.parcelWeight = parcelWeight;
        }

        public void setDocumentWeight(double documentWeight) {
                this.documentWeight = documentWeight;
        }

        public void setCouriers(List<String> couriers) {
                this.couriers = couriers;
        }
}
