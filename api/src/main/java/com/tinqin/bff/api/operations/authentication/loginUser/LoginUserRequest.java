package com.tinqin.bff.api.operations.authentication.loginUser;

import com.tinqin.bff.api.base.OperationRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserRequest implements OperationRequest {
    @Email
  private String email;
    @NotEmpty
  private String password;
}
