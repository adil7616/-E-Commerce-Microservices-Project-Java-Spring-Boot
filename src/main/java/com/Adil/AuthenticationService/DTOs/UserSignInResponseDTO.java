package com.Adil.AuthenticationService.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInResponseDTO {
    private String token;
    private ResponseStatus responseStatus;
}
