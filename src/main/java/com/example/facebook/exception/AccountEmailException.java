package com.example.facebook.exception;

public class AccountEmailException extends Exception{

    public AccountEmailException(String message) {
        super(message);
    }

    public AccountEmailException(String message, Throwable cause) {
        super(message, cause);
    }

}
