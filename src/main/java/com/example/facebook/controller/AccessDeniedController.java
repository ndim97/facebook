package com.example.facebook.controller;

import com.example.facebook.exception.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedController extends BaseController{

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized() {
        return send("error/unauthorized");
    }
}
