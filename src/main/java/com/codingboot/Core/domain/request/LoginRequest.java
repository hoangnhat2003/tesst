package com.codingboot.Core.domain.request;

import com.codingboot.Core.domain.annotation.Email;
import com.codingboot.Core.domain.annotation.Password;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Password
    @NotNull
    @NotBlank
    private String password;
}
