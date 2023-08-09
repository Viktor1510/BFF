package com.tinqin.bff.api.operations.cart.addcart;

import com.tinqin.bff.api.base.OperationRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddCartItemRequest implements OperationRequest {
    private String itemId;
    private Integer quantity;
}
