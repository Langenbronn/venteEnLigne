package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private String firstname;
    private String lastname;
    private String gender;

}