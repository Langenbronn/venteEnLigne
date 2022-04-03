package com.onlinesale.onlinesale.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
public class BasketDto implements Serializable {
    private UUID idCustomer;
    private Set<UUID> basketItems;
}