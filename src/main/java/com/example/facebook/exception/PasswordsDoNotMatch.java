package com.example.facebook.exception;

public class PasswordsDoNotMatch extends Exception{

    public PasswordsDoNotMatch(String message) {
        super(message);
    }

    public PasswordsDoNotMatch(String message, Throwable cause) {
        super(message, cause);
    }

}
