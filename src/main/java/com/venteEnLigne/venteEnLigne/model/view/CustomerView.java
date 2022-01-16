package com.venteEnLigne.venteEnLigne.model.view;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CustomerView implements Serializable {
    private UUID id;

    private String firstname;
    private String lastname;
    private String gender;

    public CustomerView() {
    }

}