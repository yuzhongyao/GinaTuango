package com.example.ginatuango.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int category_id;
    @Column
    private String category_name;

    public Category() {
    }

    public Category(int id, String name) {
        this.category_id = id;
        this.category_name = name;
    }

    public int getId() {
        return category_id;
    }

    public void setId(int id) {
        this.category_id = id;
    }

    public String getName() {
        return category_name;
    }

    public void setName(String name) {
        this.category_name = name;
    }
}
