package com.tinqin.bff.api.operations.authentication.loginUser;

import com.tinqin.bff.api.base.OperationResult;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserResponse implements OperationResult {
    private String token;
}
