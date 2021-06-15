package com.example.facebook.exception;

public class PasswordIsNotProvided extends Exception{

    public PasswordIsNotProvided(String message) {
        super(message);
    }

    public PasswordIsNotProvided(String message, Throwable cause) {
        super(message, cause);
    }

}
