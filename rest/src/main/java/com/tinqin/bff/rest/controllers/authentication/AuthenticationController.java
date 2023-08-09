package com.tinqin.bff.rest.controllers.authentication;

import com.tinqin.bff.api.operations.authentication.loginUser.LoginUserOperation;
import com.tinqin.bff.api.operations.authentication.loginUser.LoginUserRequest;
import com.tinqin.bff.api.operations.authentication.loginUser.LoginUserResponse;
import com.tinqin.bff.api.operations.authentication.registeruser.RegisterUserOperation;
import com.tinqin.bff.api.operations.authentication.registeruser.RegisterUserRequest;
import com.tinqin.bff.api.operations.authentication.registeruser.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private RegisterUserOperation registerUserOperation;

    private LoginUserOperation loginUserOperation;

    @PostMapping(path="/user")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest request){
        return new ResponseEntity<>(this.registerUserOperation.process(request), HttpStatus.CREATED);
    }

    @PostMapping(path="/loggingUser")
    public ResponseEntity<LoginUserResponse> loginUser(@RequestBody LoginUserRequest request){
        return ResponseEntity.ok(this.loginUserOperation.process(request));
    }

}
