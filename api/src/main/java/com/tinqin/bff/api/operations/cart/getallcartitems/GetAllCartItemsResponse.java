package com.tinqin.bff.api.operations.cart.getallcartitems;

import com.tinqin.bff.api.base.OperationResult;
import com.tinqin.bff.api.operations.cart.removecart.CartItemSingleItem;
import lombok.*;

import javax.print.DocFlavor;
import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCartItemsResponse implements OperationResult {
    private List<String> cartItems;
}
