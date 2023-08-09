package com.tinqin.bff.api.operations.cart.removecart;

import com.tinqin.bff.api.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveCartResponse implements OperationResult {
    private String[] existingItems;
}
