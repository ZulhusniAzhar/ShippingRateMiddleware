package com.SPL_middleware.assignment.dto;

import feign.form.FormProperty;


public class JntRateRequest{
    @FormProperty("_token")
    private String token;

    @FormProperty("shipping_rates_type")
    private String shippingRatesType;

    @FormProperty("sender_postcode")
    private String senderPostcode;

    @FormProperty("receiver_postcode")
    private String receiverPostcode;

    @FormProperty("destination_country")
    private String destinationCountry;

    @FormProperty("shipping_type")
    private String shippingType;

    @FormProperty("weight")
    private double weight;

    @FormProperty("length")
    private double length;

    @FormProperty("width")
    private double width;

    @FormProperty("height")
    private double height;

    @FormProperty("insurance")
    private String insurance;

    @FormProperty("item_value")
    private double itemValue;


    @Override
    public String toString() {
        return "JntRateRequest{" +
                "token='" + token + '\'' +
                ", shippingRatesType='" + shippingRatesType + '\'' +
                ", senderPostcode='" + senderPostcode + '\'' +
                ", receiverPostcode='" + receiverPostcode + '\'' +
                ", destinationCountry='" + destinationCountry + '\'' +
                ", shippingType='" + shippingType + '\'' +
                ", weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", insurance='" + insurance + '\'' +
                ", itemValue=" + itemValue +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getShippingRatesType() {
        return shippingRatesType;
    }

    public void setShippingRatesType(String shippingRatesType) {
        this.shippingRatesType = shippingRatesType;
    }

    public String getSenderPostcode() {
        return senderPostcode;
    }

    public void setSenderPostcode(String senderPostcode) {
        this.senderPostcode = senderPostcode;
    }

    public String getReceiverPostcode() {
        return receiverPostcode;
    }

    public void setReceiverPostcode(String receiverPostcode) {
        this.receiverPostcode = receiverPostcode;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
}
