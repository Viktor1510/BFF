package com.tinqin.bff.core.processors.cart;

import com.tinqin.bff.api.operations.cart.emptycart.EmptyCartItemOperation;
import com.tinqin.bff.api.operations.cart.emptycart.EmptyCartItemRequest;
import com.tinqin.bff.api.operations.cart.emptycart.EmptyCartItemResponse;
import com.tinqin.bff.persistence.entities.CartItem;
import com.tinqin.bff.persistence.entities.User;
import com.tinqin.bff.persistence.repositories.CartItemRepository;
import com.tinqin.bff.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmptyCartItemProcessor implements EmptyCartItemOperation {

    private final UserRepository userRepository;

    private final CartItemRepository cartItemRepository;

    @Override
    public EmptyCartItemResponse process(EmptyCartItemRequest input) {
        String email= ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user= userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Email:"+email+" is not valid!"));
        cartItemRepository.deleteAll(user.getCartItems());
        return EmptyCartItemResponse.builder().build();
    }
}
