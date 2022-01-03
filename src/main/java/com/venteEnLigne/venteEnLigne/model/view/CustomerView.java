package com.venteEnLigne.venteEnLigne.model.view;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
public class CustomerView implements Serializable {
    private Long id;

    private String firstname;
    private String lastname;
    private String gender;

    public CustomerView() {
    }

}