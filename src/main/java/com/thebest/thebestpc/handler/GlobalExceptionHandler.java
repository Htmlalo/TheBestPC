package com.thebest.thebestpc.handler;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleNotFoundException() {
        return "view/Error404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "view/ErrorGlobal";
    }

}
