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

    public Cart(int cart_id, User user) {
        this.cart_id = cart_id;
        this.user = user;
    }

    public Cart(){}

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
