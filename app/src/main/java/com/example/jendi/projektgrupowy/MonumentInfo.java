package com.example.jendi.projektgrupowy;

public class MonumentInfo {

    private String name;
    private String function;
    private String creationDate;
    private String archivalSource;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String postCode;
    private String city;
    private String country;

    public MonumentInfo() {
        this.name = "Nazwa: ";
        this.function = "Funkcja: ";
        this.creationDate = "Data utworzenia: ";
        this.archivalSource = "Źródło: ";
        this.street = "Ulica: ";
        this.houseNumber = "Numer domu: ";
        this.flatNumber = "Numer mieszkania: ";
        this.postCode = "Kod pocztowy: ";
        this.city = "Miasto: ";
        this.country = "Kraj: ";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name += name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function += function;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate += creationDate;
    }

    public String getArchivalSource() {
        return archivalSource;
    }

    public void setArchivalSource(String archivalSource) {
        this.archivalSource += archivalSource;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street += street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber += houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber += flatNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode += postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city += city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country += country;
    }
}
