package com.example.facebook.exception;

public class NotOldEnough extends Exception{

    public NotOldEnough(String message) {
        super(message);
    }

    public NotOldEnough(String message, Throwable cause) {
        super(message, cause);
    }

}
