package com.example.facebook.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFound extends UsernameNotFoundException {

    public EmailNotFound(String message) {
        super(message);
    }

    public EmailNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}
