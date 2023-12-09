package com.example.ginatuango.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "itemSaleTypes")
public class ItemSaleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int type_id;

    @Column
    private String type_name;

    public ItemSaleType() {
    }

    public ItemSaleType(int id, String type) {
        this.type_id = id;
        this.type_name = type;
    }

    public int getId() {
        return type_id;
    }

    public void setId(int id) {
        this.type_id = id;
    }

    public String getType() {
        return type_name;
    }

    public void setType(String type) {
        this.type_name = type;
    }
}
