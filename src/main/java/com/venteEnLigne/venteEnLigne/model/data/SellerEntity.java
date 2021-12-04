package com.venteEnLigne.venteEnLigne.model.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seller")
public class SellerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique=true)
    private String name;

    public SellerEntity() {
    }

    public SellerEntity(String name) {
        this.name = name;
    }
}