package com.example.facebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    protected ModelAndView send(String viewName){
        return new ModelAndView(viewName);
    }

    protected ModelAndView send(String viewName, String objectName, Object object){
        ModelAndView modelAndView = new ModelAndView(viewName + ".html");
        modelAndView.addObject(objectName, object);
        return modelAndView;
    }

    protected ModelAndView redirect(String endpoint){
        return new ModelAndView("redirect:" + endpoint);
    }




    protected ModelAndView send(String viewName, String objectName, Object object,
                                String object2Name, Object object2){
        ModelAndView modelAndView = new ModelAndView(viewName + ".html");
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(object2Name, object2);
        return modelAndView;
    }

    protected ModelAndView send(String viewName, String objectName, Object object,
                                String object2Name, Object object2, String object3Name, Object object3){
        ModelAndView modelAndView = new ModelAndView(viewName + ".html");
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(object2Name, object2);
        modelAndView.addObject(object3Name, object3);
        return modelAndView;
    }

    protected ModelAndView send(String viewName, String objectName, Object object,
                                String object2Name, Object object2, String object3Name, Object object3,
                                String object4Name, Object object4){
        ModelAndView modelAndView = new ModelAndView(viewName + ".html");
        modelAndView.addObject(objectName, object);
        modelAndView.addObject(object2Name, object2);
        modelAndView.addObject(object3Name, object3);
        modelAndView.addObject(object4Name, object4);
        return modelAndView;
    }

}
