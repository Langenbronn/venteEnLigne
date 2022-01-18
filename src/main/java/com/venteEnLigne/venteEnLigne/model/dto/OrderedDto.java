package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class OrderedDto implements Serializable {
    private UUID idCustomer;
    private List<UUID> idOrderedItems;
}