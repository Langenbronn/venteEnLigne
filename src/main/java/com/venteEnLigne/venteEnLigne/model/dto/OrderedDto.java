package com.venteEnLigne.venteEnLigne.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderedDto implements Serializable {
    private Long idCustomer;
    private List<Long> idOrderedItems;
}