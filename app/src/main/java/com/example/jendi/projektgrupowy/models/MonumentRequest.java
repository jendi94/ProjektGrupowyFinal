package com.example.jendi.projektgrupowy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonumentRequest {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("function")
    @Expose
    private String function;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("archivalSource")
    @Expose
    private String archivalSource;
    @SerializedName("coordinates")
    @Expose
    private CoordinatesRequest coordinates;
    @SerializedName("address")
    @Expose
    private AddressRequest address;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getArchivalSource() {
        return archivalSource;
    }

    public void setArchivalSource(String archivalSource) {
        this.archivalSource = archivalSource;
    }

    public CoordinatesRequest getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesRequest coordinates) {
        this.coordinates = coordinates;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}