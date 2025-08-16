package com.Adil.AuthenticationService.Exceptions;

public class WrongPassword extends RuntimeException {
    public WrongPassword(String message) {
        super(message);
    }
}
