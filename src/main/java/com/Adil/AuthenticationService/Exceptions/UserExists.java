package com.Adil.AuthenticationService.Exceptions;

public class UserExists extends RuntimeException{
    public UserExists(String message)
    {
        super(message);
    }
}
