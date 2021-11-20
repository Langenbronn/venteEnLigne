package com.venteEnLigne.venteEnLigne.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seller")
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Seller() {
    }

    public Seller(String name) {
        this.name = name;
    }
}