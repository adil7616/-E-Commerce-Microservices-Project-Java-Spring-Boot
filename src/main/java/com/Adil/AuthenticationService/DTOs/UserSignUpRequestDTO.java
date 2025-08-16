package com.Adil.AuthenticationService.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestDTO {
    private String email;
    private String password;
}
