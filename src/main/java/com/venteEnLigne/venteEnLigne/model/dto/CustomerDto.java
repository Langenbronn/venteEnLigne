package com.venteEnLigne.venteEnLigne.model.dto;

import com.venteEnLigne.venteEnLigne.model.Gender;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
public class CustomerDto implements Serializable {
    private String firstname;
    private String lastname;
    private Gender gender;

}