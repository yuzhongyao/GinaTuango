package com.example.ginatuango.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_item_id;

    @JoinColumn(name = "cart_id")
    private int cart_id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantity")
    private double quantity;

    @ManyToOne
    //sale type (case, 5 pounds, bags, etc)
    @JoinColumn(name = "type_id")
    private ItemSaleType type;



}
