package com.example.facebook.controller;

import com.example.facebook.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController{

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NameIsNotProvided.class)
    public ModelAndView nameIsNotProvided(NameIsNotProvided e) {
        return send("error/name-not-provided");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AccountEmailException.class)
    public ModelAndView accountEmailException(AccountEmailException e) {
        return send("error/email-already-exists");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotOldEnough.class)
    public ModelAndView notOldEnough(NotOldEnough e) {
        return send("error/user-not-old-enough");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PasswordIsNotProvided.class)
    public ModelAndView passwordIsNotProvided(PasswordIsNotProvided e) {
        return send("error/password-not-provided");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PasswordsDoNotMatch.class)
    public ModelAndView passwordsDoNotMatch(PasswordsDoNotMatch e) {
        return send("error/passwords-not-match");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFound(UserNotFoundException e) {
        return send("error/user-not-found");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmailNotFound.class)
    public ModelAndView emailNotFound(EmailNotFound e) {
        return send("error/email-not-found");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyPostException.class)
    public ModelAndView postIsEmpty(EmptyPostException e) {
        return send("error/empty-post");
    }
}
