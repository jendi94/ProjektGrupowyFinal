package com.example.jendi.projektgrupowy.models;

import com.google.gson.annotations.Expose ;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Monument {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("function")
    @Expose
    private String function;
    @SerializedName("creationDate")
    @Expose
    private Date creationDate;
    @SerializedName("archivalSource")
    @Expose
    private String archivalSource;
    @SerializedName("approved")
    @Expose
    private Boolean approved;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("author")
    @Expose
    private Author author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getArchivalSource() {
        return archivalSource;
    }

    public void setArchivalSource(String archivalSource) {
        this.archivalSource = archivalSource;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
