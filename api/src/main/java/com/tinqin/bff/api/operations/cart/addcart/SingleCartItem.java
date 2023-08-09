package com.tinqin.bff.api.operations.cart.addcart;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleCartItem {
    private Integer quantity;
    private BigDecimal price;
}
