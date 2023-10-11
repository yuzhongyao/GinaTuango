package com.example.XingCheng.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "url")
    private String imageUrl;

    // Constructors, getters, and setters

    public Image(int id, Item item, String imageUrl) {
        this.id = id;
        this.item = item;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
