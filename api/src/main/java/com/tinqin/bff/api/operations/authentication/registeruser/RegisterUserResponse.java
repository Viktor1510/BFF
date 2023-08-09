package com.tinqin.bff.api.operations.authentication.registeruser;

import com.tinqin.bff.api.base.OperationResult;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserResponse implements OperationResult {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
