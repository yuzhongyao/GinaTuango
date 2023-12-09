package com.example.ginatuango.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int image_id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "url")
    private String url;

    // Constructors, getters, and setters


    public Image() {
    }

    public Image(int id, Item item, String imageUrl) {
        this.image_id = id;
        this.item = item;
        this.url = imageUrl;
    }

    public int getId() {
        return image_id;
    }

    public void setId(int id) {
        this.image_id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getImageUrl() {
        return url;
    }

    public void setImageUrl(String imageUrl) {
        this.url = imageUrl;
    }
}
