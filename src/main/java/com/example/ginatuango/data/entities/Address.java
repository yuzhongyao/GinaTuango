package com.example.ginatuango.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;
    @Column
    private String street;
    @Column
    private String postal;
    @Column
    private String city;
    @Column
    private String province;
    @Column
    private String country;

    public Address() {
    }

    public Address(int id, String street, String postal, String city, String province, String country){
        this.address_id=id;
        this.street=street;
        this.postal=postal;
        this.city=city;
        this.province=province;
        this.country=country;
    }

    public int getId() {
        return address_id;
    }

    public void setId(int id) {
        this.address_id = id;
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

    @Override
    public String toString() {
        return getStreet() + ", " +
                getCity() + ", " +
                getProvince() + " " +
                getPostal() + ", " +
                getCountry();

    }
}
