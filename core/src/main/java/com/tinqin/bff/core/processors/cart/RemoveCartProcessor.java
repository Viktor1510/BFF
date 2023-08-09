package com.tinqin.bff.core.processors.cart;

import com.tinqin.bff.api.operations.cart.removecart.CartItemSingleItem;
import com.tinqin.bff.api.operations.cart.removecart.RemoveCartOperation;
import com.tinqin.bff.api.operations.cart.removecart.RemoveCartRequest;
import com.tinqin.bff.api.operations.cart.removecart.RemoveCartResponse;
import com.tinqin.bff.core.exception.ItemNotFoundException;
import com.tinqin.bff.persistence.entities.CartItem;
import com.tinqin.bff.persistence.entities.User;
import com.tinqin.bff.persistence.repositories.CartItemRepository;
import com.tinqin.bff.persistence.repositories.UserRepository;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RemoveCartProcessor implements RemoveCartOperation {

     private final UserRepository userRepository;
     private final CartItemRepository cartItemRepository;
    @Override
    public RemoveCartResponse process(RemoveCartRequest input) {
        List<CartItem> items= cartItemRepository.findByItemId(input.getItems());
        String email=((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user= userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Email:"+email+" is not valid!"));
        cartItemRepository.deleteAll(items);

        return  RemoveCartResponse
                .builder()
                .existingItems(user.getCartItems().stream().map(this::MapItemToSingle).toArray(String[]::new))
                .build();
    }

    private CartItemSingleItem MapItemToSingle(CartItem cartItem){
        return  CartItemSingleItem
                .builder()
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }



}
