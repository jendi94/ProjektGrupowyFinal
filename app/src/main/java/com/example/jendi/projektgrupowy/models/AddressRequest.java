package com.example.jendi.projektgrupowy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressRequest {

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("houseNumber")
    @Expose
    private String houseNumber;
    @SerializedName("flatNumber")
    @Expose
    private String flatNumber;
    @SerializedName("postCode")
    @Expose
    private String postCode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}