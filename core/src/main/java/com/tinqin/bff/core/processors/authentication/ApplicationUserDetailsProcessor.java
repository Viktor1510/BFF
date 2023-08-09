package com.tinqin.bff.core.processors.authentication;


import com.tinqin.bff.persistence.entities.User;
import com.tinqin.bff.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsProcessor implements UserDetailsService  {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found!"));

        return new org.springframework.security.core.userdetails.User( user.getEmail(),
                user.getPassword(),
                Set.of(new SimpleGrantedAuthority("ROLE_USER")));

    }


}
