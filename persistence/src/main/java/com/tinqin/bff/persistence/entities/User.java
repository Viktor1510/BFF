package com.tinqin.bff.persistence.entities;

import com.tinqin.bff.persistence.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    @OneToMany
    private Set<CartItem> cartItems;
    @Enumerated(EnumType.STRING)
    private Role role;

    public boolean addCartItem(CartItem cartItem){
        return this.cartItems.add(cartItem);
    }

    public boolean removeCartItem(UUID cartItemId){
        Optional<CartItem> item = this.cartItems.stream().filter(e -> e.getItemId().equals(cartItemId)).findFirst();
        if(item.isEmpty()){
            return false;
        }

        return this.cartItems.remove(item.get());
    }

    public boolean removeCartItem(CartItem cartItem){
        return this.cartItems.remove(cartItem);
    }

    public void removeCartItems(List<String> items){
        this.cartItems.stream().filter(e->items.contains(e.getItemId().toString())).forEach(this.cartItems::remove);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
