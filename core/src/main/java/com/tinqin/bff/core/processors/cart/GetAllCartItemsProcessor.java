package com.tinqin.bff.core.processors.cart;

import com.tinqin.bff.api.operations.cart.getallcartitems.GetAllCartItemsOperation;
import com.tinqin.bff.api.operations.cart.getallcartitems.GetAllCartItemsRequest;
import com.tinqin.bff.api.operations.cart.getallcartitems.GetAllCartItemsResponse;
import com.tinqin.bff.persistence.entities.CartItem;
import com.tinqin.bff.persistence.entities.User;
import com.tinqin.bff.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllCartItemsProcessor implements GetAllCartItemsOperation {
    private final UserRepository userRepository;
    @Override
    public GetAllCartItemsResponse process(GetAllCartItemsRequest input) {
        String email=((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user= userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Email:"+email+" is not valid!"));

        return GetAllCartItemsResponse
                .builder()
                .cartItems(user.getCartItems().stream().map(this::MapItemToSingleItem).toList())
                .build();
    }

    private String MapItemToSingleItem(CartItem cartItem){
        return cartItem.getQuantity() +" "+cartItem.getPrice()+"\n";
    }
}
