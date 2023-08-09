package com.tinqin.bff.rest.controllers.cartitem;

import com.tinqin.bff.api.operations.cart.addcart.AddCartItemOperation;
import com.tinqin.bff.api.operations.cart.addcart.AddCartItemRequest;
import com.tinqin.bff.api.operations.cart.addcart.AddCartItemResponse;
import com.tinqin.bff.api.operations.cart.emptycart.EmptyCartItemOperation;
import com.tinqin.bff.api.operations.cart.emptycart.EmptyCartItemRequest;
import com.tinqin.bff.api.operations.cart.emptycart.EmptyCartItemResponse;
import com.tinqin.bff.api.operations.cart.getallcartitems.GetAllCartItemsOperation;
import com.tinqin.bff.api.operations.cart.getallcartitems.GetAllCartItemsRequest;
import com.tinqin.bff.api.operations.cart.getallcartitems.GetAllCartItemsResponse;
import com.tinqin.bff.api.operations.cart.removecart.RemoveCartOperation;
import com.tinqin.bff.api.operations.cart.removecart.RemoveCartRequest;
import com.tinqin.bff.api.operations.cart.removecart.RemoveCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cartitems")
@RequiredArgsConstructor
public class CartItemController {
    private final AddCartItemOperation addCartItemOperation;
    private final EmptyCartItemOperation emptyCartItemOperation;
    private final GetAllCartItemsOperation getAllCartItemsOperation;

    private final RemoveCartOperation removeCartOperation;

    @PostMapping(path ="/cart")
    public ResponseEntity<AddCartItemResponse> addCartItem(@RequestBody AddCartItemRequest request){
        return ResponseEntity.ok(addCartItemOperation.process(request));
    }

    @DeleteMapping(path ="/cart")
    public ResponseEntity<EmptyCartItemResponse> emptyCartItem(@RequestBody EmptyCartItemRequest request){
        return ResponseEntity.ok(emptyCartItemOperation.process(request));
    }

    @GetMapping(path="/cartitems")
    public ResponseEntity<GetAllCartItemsResponse> getAllCartItems(@RequestBody GetAllCartItemsRequest request){
        return ResponseEntity.ok(getAllCartItemsOperation.process(request));
    }

    @DeleteMapping()
    public ResponseEntity<RemoveCartResponse> removeCartItem(@RequestBody RemoveCartRequest request){
        return ResponseEntity.ok(removeCartOperation.process(request));
    }

}
