package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
public class CustomerDto implements Serializable {
    private String firstname;
    private String lastname;
    private String gender;

}