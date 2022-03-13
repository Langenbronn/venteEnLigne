package com.onlinesale.onlinesale.model.view;

import com.onlinesale.onlinesale.model.Gender;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CustomerView implements Serializable {
    private UUID id;

    private String firstname;
    private String lastname;
    private Gender gender;

    public CustomerView() {
    }

}