package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerView implements Serializable {
    private Long id;

    private String firstname;
    private String lastname;
    private String gender;

    public CustomerView() {
    }

}