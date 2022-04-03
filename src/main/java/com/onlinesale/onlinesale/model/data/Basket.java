package com.onlinesale.onlinesale.model.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "basket")
public class Basket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JoinColumn(name = "customer")
    @OneToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @JoinColumn(name = "basketitem")
    @OneToMany(fetch = FetchType.EAGER)
    private Set<BasketItem> basketItems;

    public Basket(UUID id) {
        this.id = id;
    }

    public Basket(Customer customer, Set<BasketItem> basketItems) {
        this.customer = customer;
        this.basketItems = basketItems;
    }

    public Basket(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Basket product = (Basket) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}