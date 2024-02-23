package com.example.ginatuango.data.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "shoppingCarts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




}
