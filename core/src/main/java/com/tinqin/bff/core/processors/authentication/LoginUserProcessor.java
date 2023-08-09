package com.tinqin.bff.core.processors.authentication;

import com.tinqin.bff.api.operations.authentication.loginUser.LoginUserOperation;
import com.tinqin.bff.api.operations.authentication.loginUser.LoginUserRequest;
import com.tinqin.bff.api.operations.authentication.loginUser.LoginUserResponse;
import com.tinqin.bff.persistence.entities.User;
import com.tinqin.bff.persistence.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserProcessor implements LoginUserOperation {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public LoginUserResponse process(LoginUserRequest input) {
        User user=userRepository.findByEmail(input.getEmail()).orElseThrow();
        String token=jwtService.generateToken(user);
        return LoginUserResponse.builder().token(token).build();
    }
}
