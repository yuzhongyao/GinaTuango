package com.example.XingCheng.data.entities;

import com.example.XingCheng.data.models.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id
    private int id;
    private String name;
    private String description;
    private Category category;
    private String[] images;
    
    private double stock;
    private double price;

    public Item(int id, String name, String description, Category category, double stock, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

}
