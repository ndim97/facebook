package com.example.facebook.exception;

public class NameIsNotProvided extends Exception{

    public NameIsNotProvided(String message) {
        super(message);
    }

    public NameIsNotProvided(String message, Throwable cause) {
        super(message, cause);
    }

}
