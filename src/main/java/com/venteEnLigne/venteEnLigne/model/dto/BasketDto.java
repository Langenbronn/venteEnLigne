package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class BasketDto implements Serializable {
    private UUID idCustomer;
}