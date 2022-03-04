package com.onlinesale.onlinesale.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class OrderedDto implements Serializable {
    private UUID idCustomer;
}