package com.SPL_middleware.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class CityLinkRateRequest {

    @JsonProperty("origin_country")
    private String originCountry;

    @JsonProperty("origin_state")
    private String originState ;

    @JsonProperty("origin_postcode")
    private String originPostcode ;

    @JsonProperty("destination_country")
    private String destinationCountry ;

    @JsonProperty("destination_state")
    private String destinationState ;

    @JsonProperty("destination_postcode")
    private String destinationPostcode ;

    @JsonProperty("length")
    private double length ;

    @JsonProperty("width")
    private double width ;

    @JsonProperty("height")
    private double height ;

    @JsonProperty("selected_type")
    private int selectedType;

    @JsonProperty("parcel_weight")
    private double parcelWeight ;

    @JsonProperty("document_weight")
    private double documentWeight ;

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginState() {
        return originState;
    }

    public void setOriginState(String originState) {
        this.originState = originState;
    }

    public String getOriginPostcode() {
        return originPostcode;
    }

    public void setOriginPostcode(String originPostcode) {
        this.originPostcode = originPostcode;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }

    public String getDestinationPostcode() {
        return destinationPostcode;
    }

    public void setDestinationPostcode(String destinationPostcode) {
        this.destinationPostcode = destinationPostcode;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(int selectedType) {
        this.selectedType = selectedType;
    }

    public double getParcelWeight() {
        return parcelWeight;
    }

    public void setParcelWeight(double parcelWeight) {
        this.parcelWeight = parcelWeight;
    }

    public double getDocumentWeight() {
        return documentWeight;
    }

    public void setDocumentWeight(double documentWeight) {
        this.documentWeight = documentWeight;
    }

    @Override
    public String toString() {
        return String.format(
                "{\"origin_country\":\"%s\", \"origin_state\":\"%s\", \"origin_postcode\":\"%s\", " +
                        "\"destination_country\":\"%s\", \"destination_state\":\"%s\", \"destination_postcode\":\"%s\", " +
                        "\"length\":%.1f, \"width\":%.1f, \"height\":%.1f, \"selected_type\":%d, " +
                        "\"parcel_weight\":%.1f, \"document_weight\":%.1f}",
                originCountry, originState, originPostcode,
                destinationCountry, destinationState, destinationPostcode,
                length, width, height, selectedType, parcelWeight, documentWeight
        );
    }
}
