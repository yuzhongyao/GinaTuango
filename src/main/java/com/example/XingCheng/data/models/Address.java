package com.example.XingCheng.data.models;

public class Address {
    private int id;
    private String street;
    private String postal;
    private String city;
    private String province;
    private String country;

    public Address(){
    }

    public Address(int id, String street, String postal, String city, String province, String country){
        this.id=id;
        this.street=street;
        this.postal=postal;
        this.city=city;
        this.province=province;
        this.country=country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
