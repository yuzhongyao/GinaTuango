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

    public CartItem(){}

    public CartItem( int cart_id, Item item, double quantity, ItemSaleType type) {

        this.cart_id = cart_id;
        this.item = item;
        this.quantity = quantity;
        this.type = type;
    }

    public int getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(int cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public ItemSaleType getType() {
        return type;
    }

    public void setType(ItemSaleType type) {
        this.type = type;
    }
}
