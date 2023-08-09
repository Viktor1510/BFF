package com.tinqin.bff.api.operations.cart.removecart;

import com.tinqin.bff.api.base.OperationRequest;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveCartRequest implements OperationRequest {
    String[] items;
}
