package com.tinqin.bff.core.processors.cart;

import com.example.zoostore.ZooStoreRestExport;
import com.tinqin.bff.api.operations.cart.addcart.AddCartItemOperation;
import com.tinqin.bff.api.operations.cart.addcart.AddCartItemRequest;
import com.tinqin.bff.api.operations.cart.addcart.AddCartItemResponse;
import com.tinqin.bff.core.exception.ItemNotFoundException;
import com.tinqin.bff.persistence.entities.CartItem;
import com.tinqin.bff.persistence.entities.User;
import com.tinqin.bff.persistence.repositories.CartItemRepository;
import com.tinqin.bff.persistence.repositories.UserRepository;
import com.zoostore.storage.api.operations.getStorageItemsByIds.GetStorageItemsByIdsResponse;
import com.zoostore.storage.api.operations.getStorageItemsByIds.GetStorageItemsByIdsSingleItem;
import com.zoostore.storage.restexport.StorageRestExport;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddCartItemProcessor implements AddCartItemOperation {
    private final StorageRestExport storageRestExport;
    private final ZooStoreRestExport zooStoreRestExport;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public AddCartItemResponse process(AddCartItemRequest input) {
        UUID itemId= UUID.fromString(input.getItemId());
        try {
            zooStoreRestExport.getItem(itemId.toString());
        }catch (FeignException e){
            throw new ItemNotFoundException(itemId);
        }

        GetStorageItemsByIdsResponse storageItem;
        try
        {
            storageItem=storageRestExport.getItemsByIds(Set.of(itemId.toString()));
        }catch (FeignException e)
        {
            throw new ItemNotFoundException(itemId);
        }

        GetStorageItemsByIdsSingleItem singleItem = storageItem.getItemIds()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(itemId));

        Optional<CartItem> existingCartItemOptional = this.cartItemRepository
                .findById(UUID.fromString(input.getItemId()));


        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user =userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Email:"+email+"is not valid!"));

        CartItem cartItem  = CartItem.builder()
                .itemId(itemId)
                .quantity(input.getQuantity())
                .price(singleItem.getPrice())
                .user(user)
                .build();

        if(existingCartItemOptional.isPresent())
        {
            cartItem=existingCartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity()+input.getQuantity());
        }

        user.addCartItem(cartItem);
        cartItemRepository.save(cartItem);
        userRepository.save(user);

        return AddCartItemResponse
                .builder()
                .items(user.getCartItems().stream()
                        .map(this::mapCartItem)
                        .collect(Collectors.toSet()))
                .build();
    }

    private String mapCartItem(CartItem cartItem){
        return cartItem.getItemId().toString()+"\n" + cartItem.getQuantity() + "\n " + cartItem.getPrice();
    }
}
