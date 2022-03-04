package com.onlinesale.onlinesale.model.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "stock")
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "quantity")
    private int quantity;

    @JoinColumn(name = "product")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @JoinColumn(name = "seller")
    @ManyToOne(fetch = FetchType.EAGER)
    private Seller seller;

    public Stock(UUID id) {
        this.id = id;
    }

    public Stock(int quantity) {
        this.quantity = quantity;
    }

    public Stock(int quantity, Product product, Seller seller) {
        this.quantity = quantity;
        this.product = product;
        this.seller = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Stock product = (Stock) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}