package com.venteEnLigne.venteEnLigne.model.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "seller")
public class SellerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique=true)
    private String name;
    @JoinColumn(name = "product")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductEntity> productEntity;

    public SellerEntity() {
    }

    public SellerEntity(String name) {
        this.name = name;
    }
}