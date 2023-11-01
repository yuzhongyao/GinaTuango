package com.example.ginatuango.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


//Dummy class to bypass required entity for JPA
@Entity
public class Custom {
    @Id
    private int id;

    public Custom() {
    }
}
