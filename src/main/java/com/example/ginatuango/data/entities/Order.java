package com.example.ginatuango.data.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDate sale_date;

    public Order() {
    }

    public Order(int id, User user, LocalDate date) {
        this.order_id = id;
        this.user = user;
        this.sale_date = date;
    }

    public int getId() {
        return order_id;
    }

    public void setId(int id) {
        this.order_id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return sale_date;
    }

    public void setDate(LocalDate date) {
        this.sale_date = date;
    }
}
