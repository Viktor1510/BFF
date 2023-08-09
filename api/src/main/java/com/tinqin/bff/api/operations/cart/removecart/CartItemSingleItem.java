package com.tinqin.bff.api.operations.cart.removecart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@AllArgsConstructor

public class CartItemSingleItem {
    private String id;
    private String itemId;
    private Integer quantity;
    private BigDecimal price;
}
