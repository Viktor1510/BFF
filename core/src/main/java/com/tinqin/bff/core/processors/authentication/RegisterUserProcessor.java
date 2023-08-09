package com.tinqin.bff.core.processors.authentication;


import com.tinqin.bff.api.operations.authentication.registeruser.RegisterUserOperation;
import com.tinqin.bff.api.operations.authentication.registeruser.RegisterUserRequest;
import com.tinqin.bff.api.operations.authentication.registeruser.RegisterUserResponse;
import com.tinqin.bff.persistence.entities.User;
import com.tinqin.bff.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RegisterUserProcessor implements RegisterUserOperation {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public RegisterUserResponse process(RegisterUserRequest input) {
        User user= User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .phoneNumber(input.getPhoneNumber())
                .build();
        userRepository.save(user);

        return RegisterUserResponse.builder()
                .id(String.valueOf(user.getId()))
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
