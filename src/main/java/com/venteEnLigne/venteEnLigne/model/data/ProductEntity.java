package com.venteEnLigne.venteEnLigne.model.data;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique=true)
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "categorie")
    private String categorie;
    @Column(name = "description")
    private String description;

    public ProductEntity(String name, double price, String categorie, String description) {
        this.name = name;
        this.price = price;
        this.categorie = categorie;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductEntity product = (ProductEntity) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}