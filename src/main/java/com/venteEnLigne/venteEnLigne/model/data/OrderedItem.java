package com.venteEnLigne.venteEnLigne.model.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ordereditem")
public class OrderedItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @JoinColumn(name = "stock")
    @OneToOne(fetch = FetchType.EAGER)
    private Stock stock;

    @JoinColumn(name = "ordered")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ordered ordered;

    @JoinColumn(name = "basket")
    @ManyToOne(fetch = FetchType.EAGER)
    private Basket basket;

    public OrderedItem(int quantity, double price, Stock stock,Ordered ordered) {
        this.quantity = quantity;
        this.price = price;
        this.stock = stock;
        this.ordered = ordered;
    }

    public OrderedItem(int quantity, double price, Stock stock,Basket basket) {
        this.quantity = quantity;
        this.price = price;
        this.stock = stock;
        this.basket = basket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderedItem product = (OrderedItem) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}