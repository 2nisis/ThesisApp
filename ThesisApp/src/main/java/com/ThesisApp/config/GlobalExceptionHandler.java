package com.ThesisApp.config;

import com.ThesisApp.exceptions.ApplicationAlreadyExistsException;
import com.ThesisApp.exceptions.ApplicationNotFoundException;
import com.ThesisApp.exceptions.HighStandardsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ApplicationNotFoundException.class)
    public ModelAndView handleApplicationNotFoundException(ApplicationNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", exception.getMessage());
        modelAndView.addObject("link", "/professor_dashboard");

        return modelAndView;
    }

    @ExceptionHandler(ApplicationAlreadyExistsException.class)
    public ModelAndView handleApplicationAlreadyExistsException(ApplicationAlreadyExistsException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", exception.getMessage());
        modelAndView.addObject("link", "/student_dashboard");
        return modelAndView;
    }

    @ExceptionHandler(HighStandardsException.class)
    public ModelAndView handleHighStandardsException(HighStandardsException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", exception.getMessage());
        modelAndView.addObject("link", "/professor_dashboard");
        return modelAndView;
    }


}
