package com.example.ginatuango.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column
    private String user_name;
    @Column
    private String email;
    @Column
    private String phone;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column
    private String password;
    @Column
    private boolean is_admin;

    public User() {
    }

    public User(int id, String name, String email, String phone, Address address, boolean isAdmin) {
        this.user_id = id;
        this.user_name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.is_admin = isAdmin;
    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String name) {
        this.user_name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isAdmin() {
        return is_admin;
    }


    public void setAdmin(boolean admin) {
        is_admin = admin;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + user_id +
                ", name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", password='" + password + '\'' +
                ", isAdmin=" + is_admin +
                '}';
    }
}
