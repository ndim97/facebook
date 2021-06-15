package com.example.facebook.exception;

public class EmptyPostException extends Exception{

    public EmptyPostException(String message) {
        super(message);
    }

    public EmptyPostException(String message, Throwable cause) {
        super(message, cause);
    }

}
