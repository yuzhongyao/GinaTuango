package com.example.ginatuango.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;
    @Column
    private String item_name;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
//    @OneToMany(mappedBy = "item")
//    private Image[] images;

    @Column
    private double stock;
    @Column
    private double price;

    public Item() {
    }

    public Item(int id, String name, String description, Category category, double stock, double price) {
        this.item_id = id;
        this.item_name = name;
        this.description = description;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public int getId() {
        return item_id;
    }

    public void setId(int id) {
        this.item_id = id;
    }

    public String getName() {
        return item_name;
    }

    public void setName(String name) {
        this.item_name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
//    public Image[] getImages() {
//        return images;
//    }
//
//    public void setImages(Image[] images) {
//        this.images = images;
//    }

}
