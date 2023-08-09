package com.tinqin.bff.api.operations.cart.addcart;

import com.tinqin.bff.api.base.OperationResult;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddCartItemResponse implements OperationResult {
    private final Set<String> items;
}
