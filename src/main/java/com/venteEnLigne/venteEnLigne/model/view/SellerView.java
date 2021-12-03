package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seller")
public class SellerView implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public SellerView() {
    }

    public SellerView(String name) {
        this.name = name;
    }
}