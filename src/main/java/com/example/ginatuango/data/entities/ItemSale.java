package com.example.ginatuango.data.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "itemSales")
public class ItemSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sale_id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private double quantity;

    @ManyToOne
    //sale type (case, 5 pounds, bags, etc)
    @JoinColumn(name = "type_id")
    private ItemSaleType type;

    @Column
    private LocalDate sale_date;

    @Column
    //cost of 1 item and its sale type
    private Double item_cost;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    private Boolean isOnSale;

    public ItemSale() {
    }

    public ItemSale(int id, Item item, double quantity, ItemSaleType type, LocalDate date, Double cost, Order order, Boolean isOnSale) {
        this.sale_id = id;
        this.item = item;
        this.quantity = quantity;
        this.type = type;
        this.sale_date = date;
        this.item_cost = cost;
        this.order = order;
        this.isOnSale = isOnSale;
    }

    public int getId() {
        return sale_id;
    }

    public void setId(int id) {
        this.sale_id = id;
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

    public LocalDate getDate() {
        return sale_date;
    }

    public void setDate(LocalDate date) {
        this.sale_date = date;
    }

    public Double getCost() {
        return item_cost;
    }

    public void setCost(Double cost) {
        this.item_cost = cost;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }
}
