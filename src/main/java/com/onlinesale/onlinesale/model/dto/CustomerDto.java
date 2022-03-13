package com.onlinesale.onlinesale.model.dto;

import com.onlinesale.onlinesale.model.Gender;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private String firstname;
    private String lastname;
    private Gender gender;

}